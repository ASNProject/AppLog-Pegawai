package com.example.applog.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.example.applog.Adapter.AdapterDataAir;
import com.example.applog.R;
import com.google.android.material.tabs.TabLayout;

public class DataAir extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_air);

        ViewPager viewPager = findViewById(R.id.viewpagerair);
        AdapterDataAir adapterDataAir = new AdapterDataAir(getSupportFragmentManager());
        viewPager.setAdapter(adapterDataAir);

        TabLayout tabLayout1 = findViewById(R.id.sliding_tabsair);
        tabLayout1.setupWithViewPager(viewPager);

    }
}
