package com.example.applog.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.applog.Adapter.AdapterDataPanen;
import com.example.applog.Adapter.AdapterDataPerlakuan;
import com.example.applog.R;
import com.google.android.material.tabs.TabLayout;

public class DataPerlakuan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_perlakuan);
        ViewPager viewPager = findViewById(R.id.viewpagerperlakuan);
        AdapterDataPerlakuan adapterDataPerlakuan = new AdapterDataPerlakuan(getSupportFragmentManager());
        viewPager.setAdapter(adapterDataPerlakuan);

        TabLayout tabLayout = findViewById(R.id.sliding_tabsperlakuan);
        tabLayout.setupWithViewPager(viewPager);
    }
}
