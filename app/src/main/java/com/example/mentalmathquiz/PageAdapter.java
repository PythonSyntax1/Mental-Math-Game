package com.example.mentalmathquiz;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PageAdapter extends FragmentPagerAdapter {

    private int numofTabs;

    public PageAdapter(FragmentManager fm, int numofTabs) {
        super(fm);
        this.numofTabs = numofTabs;

    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new OETab();
            case 1:
                return new MCTab();
            case 2:
                return new tab3();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numofTabs;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}
