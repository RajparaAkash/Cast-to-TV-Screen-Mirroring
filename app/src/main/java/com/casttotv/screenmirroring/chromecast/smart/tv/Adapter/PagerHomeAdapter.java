package com.casttotv.screenmirroring.chromecast.smart.tv.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class PagerHomeAdapter extends FragmentStateAdapter {
    private final ArrayList<Fragment> fragments;

    public PagerHomeAdapter(ArrayList<Fragment> arrayList, FragmentManager fragmentManager, Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
        this.fragments = arrayList;
    }

    @Override
    public int getItemCount() {
        return fragments.size();
    }

    @Override
    public Fragment createFragment(int i) {
        Fragment fragment = fragments.get(i);
        return fragment;
    }

}
