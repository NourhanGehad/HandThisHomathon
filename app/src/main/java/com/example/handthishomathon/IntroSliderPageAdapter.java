package com.example.handthishomathon;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class IntroSliderPageAdapter extends FragmentPagerAdapter {
    public IntroSliderPageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return IntroSliderFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return 2;
    }
}
