package com.example.applog.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.applog.Fragment.DetailSampling;
import com.example.applog.Fragment.InputSamping;

public class AdapterDataSampling extends FragmentStatePagerAdapter {
    final int pageCount = 2;
    private String tabTitles[] = new String[] {"Input Data", "Detail"};
    public AdapterDataSampling(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new InputSamping();
            case 1:
                return new DetailSampling();
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
