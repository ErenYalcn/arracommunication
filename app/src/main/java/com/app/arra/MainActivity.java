package com.app.arra;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        MeowBottomNavigation meowBottomNavigation = findViewById(R.id.bottom_main);

        meowBottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_shop_icon));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_arra_icon));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_profile_icon));

        meowBottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                // your codes
            }
        });
        meowBottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                // your codes
            }
        });
    }



}