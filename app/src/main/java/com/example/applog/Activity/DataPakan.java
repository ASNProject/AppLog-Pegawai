package com.example.applog.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.applog.Adapter.AdapterDataPakan;
import com.example.applog.R;
import com.google.android.material.tabs.TabLayout;

public class DataPakan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pakan);

        ViewPager viewPager = findViewById(R.id.viewpagerpakan);
        AdapterDataPakan adapterDataPakan = new AdapterDataPakan(getSupportFragmentManager());
        viewPager.setAdapter(adapterDataPakan);

        TabLayout tabLayout = findViewById(R.id.sliding_tabspakan);
        tabLayout.setupWithViewPager(viewPager);
    }
}
