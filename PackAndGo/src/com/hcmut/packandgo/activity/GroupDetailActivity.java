package com.hcmut.packandgo.activity;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;

import com.hcmut.packandgo.R;
import com.hcmut.packandgo.adapter.SectionsPagerAdapter;
import com.hcmut.packandgo.fragment.BaseFragment;
import com.hcmut.packandgo.fragment.GroupInfoFragment;
import com.hcmut.packandgo.fragment.GroupMapFragment;
import com.hcmut.packandgo.fragment.GroupMemberFragment;

public class GroupDetailActivity extends FragmentActivity implements ActionBar.TabListener {

	private SectionsPagerAdapter sectionsPagerAdapter;
	private ViewPager viewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Set up the layout
		setContentView(R.layout.activity_group_detail);

		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setDisplayShowHomeEnabled(false);

		// Create the adapter that will return a fragment for each of the three primary sections of the app.
		ArrayList<BaseFragment> fragments = new ArrayList<BaseFragment>();
		
		GroupInfoFragment infoFragment = new GroupInfoFragment();
		infoFragment.setTitle("Info");
		
		GroupMemberFragment memberFragment = new GroupMemberFragment();
		memberFragment.setTitle("Members");
		
		GroupMapFragment mapFragment = new GroupMapFragment();
		mapFragment.setTitle("Map");
		
		fragments.add(infoFragment);
		fragments.add(memberFragment);
		fragments.add(mapFragment);
		
		sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), fragments);

		// Set up the ViewPager with the sections adapter.
		viewPager = (ViewPager) findViewById(R.id.pager_group_detail);
		viewPager.setAdapter(sectionsPagerAdapter);

		// When swiping between different sections, select the corresponding tab. 
		// We can also use ActionBar.Tab#select() to do this if we have a reference to the Tab.
		viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				actionBar.setSelectedNavigationItem(position);
			}
		});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < sectionsPagerAdapter.getCount(); i++) {
			actionBar.addTab(actionBar.newTab().setText(sectionsPagerAdapter.getPageTitle(i)).setTabListener(this));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.group_detail, menu);
		return true;
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
		viewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
	}

}
