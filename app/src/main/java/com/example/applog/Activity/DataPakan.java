package com.example.applog.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.applog.Adapter.AdapterDataPakan;
import com.example.applog.R;
import com.google.android.material.tabs.TabLayout;

public class DataPakan extends AppCompatActivity {
     private ImageView cameras;

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
