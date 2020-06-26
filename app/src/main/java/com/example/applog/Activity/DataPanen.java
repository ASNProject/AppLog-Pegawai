package com.example.applog.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.applog.Adapter.AdapterDataPakan;
import com.example.applog.Adapter.AdapterDataPanen;
import com.example.applog.R;
import com.google.android.material.tabs.TabLayout;

public class DataPanen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_panen);
        ViewPager viewPager = findViewById(R.id.viewpagerpanen);
        AdapterDataPanen adapterDataPanen = new AdapterDataPanen(getSupportFragmentManager());
        viewPager.setAdapter(adapterDataPanen);

        TabLayout tabLayout = findViewById(R.id.sliding_tabspanen);
        tabLayout.setupWithViewPager(viewPager);
    }
}
