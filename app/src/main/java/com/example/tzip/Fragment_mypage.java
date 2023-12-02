package com.example.tzip;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tzip.databinding.FragmentMypageBinding;
import com.example.tzip.databinding.MypageFriendRecyclerviewBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.TotpSecret;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import com.example.tzip.nevigation_bar_test_code;


public class Fragment_mypage extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    FragmentMypageBinding binding;

    private String mParam1;
    private String mParam2;

    public Fragment_mypage() {
        // Required empty public constructor
    }

    private void callEmergecyMessageSettingMethod() {
        if (getActivity() instanceof nevigation_bar_test_code) {
            nevigation_bar_test_code activity = (nevigation_bar_test_code) getActivity();
            activity.setToolbarForEmergencyMessage(); // 액티비티의 메서드 호출
        }
    }

    public static Fragment_mypage newInstance(String param1, String param2) {
        Fragment_mypage fragment = new Fragment_mypage();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMypageBinding.inflate(inflater, container, false);

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("Item=" + i);
        }

        binding.friendList.setLayoutManager(new LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false));
        binding.friendList.setAdapter(new MyAdapter(list));
        binding.friendList.addItemDecoration(new MyItemDecoration());

        binding.moveFriendList.setOnClickListener( v -> {
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            FriendList friendList = new FriendList();
            transaction.replace(R.id.containers, friendList);
            transaction.commit();
        });

        binding.logout.setOnClickListener( v -> {
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(getContext(), "로그아웃이 완료되었습니다.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(), Login.class);
            getActivity().finish();
            startActivity(intent);
            //로그아웃 되었습니다 띄우고 login activity로 이동
        });

        binding.emergencySetting.setOnClickListener( v -> {
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            callEmergecyMessageSettingMethod();
            Fragment_emergency fragmentEmergency = new Fragment_emergency();
            transaction.replace(R.id.containers, fragmentEmergency);
            transaction.commit();
        });

        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        private MypageFriendRecyclerviewBinding binding;

        private MyViewHolder(MypageFriendRecyclerviewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    private class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
        private List<String> list;

        private MyAdapter(List<String> list) {
            this.list = list;
        }
        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            MypageFriendRecyclerviewBinding binding = MypageFriendRecyclerviewBinding.inflate(LayoutInflater.from(parent.getContext()) ,parent, false);
            return new MyViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            String text = list.get(position);
            holder.binding.friendName.setText(text);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    private class MyItemDecoration extends RecyclerView.ItemDecoration {
        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            int index = parent.getChildAdapterPosition(view) + 1;

            if (index % 3 == 0)
                outRect.set(20, 20, 20, 60);
            else
                outRect.set(20, 20, 20, 20);
            ViewCompat.setElevation(view, 20.0f);
        }

        @Override
        public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            Drawable dr = ResourcesCompat.getDrawable(getResources(), R.drawable.setting, null);

            int width = parent.getWidth();
            int height = parent.getHeight();
            int drWidth = dr.getIntrinsicWidth();
            int drHeight = dr.getIntrinsicHeight();
            int left = width / 2 - drWidth / 2;
            int top = height / 2 - drHeight / 2;

            c.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.setting), left, top, null);
        }
    }
}