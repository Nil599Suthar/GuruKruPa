package com.example.gurukrupa.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.gurukrupa.Fragments.Flate_Fragment;
import com.example.gurukrupa.Fragments.Reminder_Fragment;
import com.example.gurukrupa.Fragments.Shop_Fragment;

public class PagerAdapter extends FragmentStatePagerAdapter {

    int mNoOfTabs;

    public PagerAdapter(FragmentManager fm, int NumberOfTabs)
    {
        super(fm);
        this.mNoOfTabs = NumberOfTabs;
    }


    @Override
    public Fragment getItem(int position) {
        switch(position)
        {

            case 0:
                Flate_Fragment flate_fragment = new Flate_Fragment();
                return flate_fragment;
            case 1:
                Shop_Fragment shop_fragment = new Shop_Fragment();
                return  shop_fragment;
            case 2:
                Reminder_Fragment reminder_fragment = new Reminder_Fragment();
                return  reminder_fragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNoOfTabs;
    }
}