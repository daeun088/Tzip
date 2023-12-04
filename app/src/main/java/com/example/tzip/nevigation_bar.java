package com.example.tzip;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.material.navigation.NavigationBarView;

public class nevigation_bar extends AppCompatActivity {

    Fragment_community fragmentCommunity;
    Fragment_schedule fragmentSchedule;
    Fragment_record fragmentReview;
    Fragment_home fragmentHome;
    Fragment_mypage fragmentMypage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nevigation_bar);

        fragmentSchedule = new Fragment_schedule();
        fragmentReview = new Fragment_record();
        fragmentCommunity = new Fragment_community();
        fragmentHome = new Fragment_home();
        fragmentMypage = new Fragment_mypage();


        getSupportFragmentManager().beginTransaction().replace(R.id.containers, fragmentHome).commit();

        NavigationBarView navigationBarView = findViewById(R.id.bottom_navigationview);
        navigationBarView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.Home) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.containers, fragmentHome).commit();
                    return true;
                } else if (item.getItemId() == R.id.community) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.containers, fragmentCommunity).commit();
                    return true;
                } else if (item.getItemId() == R.id.review) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.containers, fragmentReview).commit();
                    return true;
                } else if (item.getItemId() == R.id.schedule) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.containers, fragmentSchedule).commit();
                    return true;
                } else if (item.getItemId() == R.id.mypage) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.containers, fragmentMypage).commit();
                    return true;
                }


                return false;
            }
        });
    }
}