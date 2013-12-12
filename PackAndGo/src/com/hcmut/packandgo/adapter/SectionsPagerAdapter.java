package com.hcmut.packandgo.adapter;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hcmut.packandgo.fragment.BaseFragment;

public class SectionsPagerAdapter extends FragmentPagerAdapter {
	
	// Fragments collection
	private ArrayList<BaseFragment> fragments;
	
	/**
	 * Constructor
	 * @param fm
	 * @param resources
	 */
	public SectionsPagerAdapter(FragmentManager fm, ArrayList<BaseFragment> fragments) {
		super(fm);
		this.fragments = fragments;
	}

	/**
	 * When one item is invoked, create a fragment corresponding to its position
	 */
	@Override
	public Fragment getItem(int position) {
		return fragments.get(position);
	}

	/**
	 * Get total fragments of this adapter
	 */
	@Override
	public int getCount() {
		return fragments.size();
	}
	
	/**
	 * Get page title corresponding to each fragment
	 */
	@Override
	public CharSequence getPageTitle(int position) {
		return fragments.get(position).getTitle();
	}
}
