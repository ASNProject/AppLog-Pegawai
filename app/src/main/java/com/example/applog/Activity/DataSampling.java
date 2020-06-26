package com.example.applog.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.applog.Adapter.AdapterDataSampling;
import com.example.applog.R;
import com.google.android.material.tabs.TabLayout;

public class DataSampling extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_sampling);

        ViewPager viewPager = findViewById(R.id.viewpagersampling);
        AdapterDataSampling adapterDataSampling = new AdapterDataSampling(getSupportFragmentManager());
        viewPager.setAdapter(adapterDataSampling);

        TabLayout tabLayout = findViewById(R.id.sliding_tabssampling);
        tabLayout.setupWithViewPager(viewPager);
    }
}
