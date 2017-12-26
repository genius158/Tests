package com.yan.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerFragment extends LifeCycleFragment {
    public ViewPagerFragment() {
        TAG = 0 + TAG;
    }

    public static ViewPagerFragment newInstance() {
        Bundle args = new Bundle();
        ViewPagerFragment fragment = new ViewPagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView: ");
        return inflater.inflate(R.layout.fragment_viewpager_test, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.e(TAG, "onViewCreated: ");
        ViewPager viewPager = view.findViewById(R.id.vp);
        final List<Fragment> fragments = new ArrayList<>();
        ViewPagerFragment vpf = ViewPagerFragment.newInstance();
        int index = 1;
        if (getArguments() == null) {
            vpf.TAG = index + "-1" + vpf.TAG;
            fragments.add(vpf);
        } else {
            index = 2;
            LifeCycleFragment lcf1 = new LifeCycleFragment();
            lcf1.TAG = index + "-1" + lcf1.TAG;
            fragments.add(lcf1);
        }
        LifeCycleFragment lcf2 = new LifeCycleFragment();
        lcf2.TAG = index + "-2" + lcf2.TAG;
        fragments.add(lcf2);

        LifeCycleFragment lcf3 = new LifeCycleFragment();
        lcf3.TAG = index + "-3" + lcf3.TAG;
        fragments.add(lcf3);

        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
    }
}
