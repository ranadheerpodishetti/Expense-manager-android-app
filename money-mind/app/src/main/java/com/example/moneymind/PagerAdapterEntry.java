package com.example.moneymind;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerAdapterEntry extends FragmentPagerAdapter {

    private int numOfTabs;


    public PagerAdapterEntry(FragmentManager fm, int numOfTabs){

        super(fm);
        this.numOfTabs = numOfTabs;
    }
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new ExpenseFragment();
            case 1:
                return new IncomeFragment();
            default:
                return null;
        }

    }
    @Override
    public int getCount() {
        return numOfTabs;
    }
}
