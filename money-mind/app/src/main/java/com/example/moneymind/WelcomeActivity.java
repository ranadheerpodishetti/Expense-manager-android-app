package com.example.moneymind;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        TabLayout tabLayout = findViewById(R.id.tabBar);
        TabItem tabDaily = findViewById(R.id.tabDaily);
        TabItem tabWeekly = findViewById(R.id.tabWeekly);
        TabItem tabMonthly = findViewById(R.id.tabMonthly);
        TabItem tabTotal = findViewById(R.id.tabTotal);
        final ViewPager viewPager = findViewById(R.id.viewPager);



        FloatingActionButton addentrybutton2 = findViewById(R.id.fab2_btn);

        addentrybutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),entryActivity2.class));
            }
        });

        com.example.moneymind.PagerAdapter pagerAdapter = new
                com.example.moneymind.PagerAdapter(getSupportFragmentManager(),
                tabLayout.getTabCount());

        viewPager.setAdapter(pagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
