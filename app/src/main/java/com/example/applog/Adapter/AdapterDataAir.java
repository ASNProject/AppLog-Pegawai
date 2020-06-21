package com.example.applog.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.applog.Fragment.DetailAir;
import com.example.applog.Fragment.InputDataAir;

public class AdapterDataAir extends FragmentStatePagerAdapter {

    final int pageCount = 2;
    private String tabTitles[] = new String[] {"Input Data", "Detail"};

    public AdapterDataAir(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new InputDataAir();
            case 1:
                return new DetailAir();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return pageCount;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
