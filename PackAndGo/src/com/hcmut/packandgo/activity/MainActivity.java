package com.hcmut.packandgo.activity;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.hcmut.packandgo.R;
import com.hcmut.packandgo.adapter.SectionsPagerAdapter;
import com.hcmut.packandgo.fragment.BaseFragment;
import com.hcmut.packandgo.fragment.FriendListFragment;
import com.hcmut.packandgo.fragment.GroupListFragment;
import com.hcmut.packandgo.fragment.GroupMapFragment;

public class MainActivity extends FragmentActivity implements ActionBar.TabListener {

	private SectionsPagerAdapter sectionPagerAdapter;
	private ViewPager viewPager;
	
	private static final int TAB_FRIEND_INDEX = 0;
	private static final int ACTION_ADD_INDEX = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Set up the layout
		setContentView(R.layout.activity_main);

		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setDisplayShowHomeEnabled(false);

		// Create the adapter that will return a fragment for each of the three primary sections of the app.
		ArrayList<BaseFragment> fragments = new ArrayList<BaseFragment>();
		
		FriendListFragment friendListFragment = new FriendListFragment();
		friendListFragment.setTitle(getString(R.string.title_friends_section));
		
		GroupListFragment groupListFragment = new GroupListFragment();
		groupListFragment.setTitle(getString(R.string.title_groups_section));
		
//		GroupMapFragment groupMapFragment = new GroupMapFragment();
//		groupMapFragment.setTitle("Map");
		
		fragments.add(friendListFragment);
		fragments.add(groupListFragment);
//		fragments.add(groupMapFragment);
		
		sectionPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), fragments);

		// Set up the ViewPager with the sections adapter.
		viewPager = (ViewPager) findViewById(R.id.pager);
		viewPager.setAdapter(sectionPagerAdapter);

		// When swiping between different sections, select the corresponding tab. 
		// We can also use ActionBar.Tab#select() to do this if we have a reference to the Tab.
		viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				actionBar.setSelectedNavigationItem(position);
			}
		});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < sectionPagerAdapter.getCount(); i++) {
			actionBar.addTab(actionBar.newTab().setText(sectionPagerAdapter.getPageTitle(i)).setTabListener(this));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		if (viewPager.getCurrentItem() == TAB_FRIEND_INDEX)
			menu.getItem(ACTION_ADD_INDEX).setIcon(R.drawable.ic_action_add_person);
		else
			menu.getItem(ACTION_ADD_INDEX).setIcon(R.drawable.ic_action_add_group);
		return true;
	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		return super.onMenuItemSelected(featureId, item);
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
		viewPager.setCurrentItem(tab.getPosition());
		invalidateOptionsMenu();
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
	}

}
