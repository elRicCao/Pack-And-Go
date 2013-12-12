package com.hcmut.packandgo.fragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.TextView;

import com.hcmut.packandgo.R;
import com.hcmut.packandgo.activity.GroupDetailActivity;
import com.hcmut.packandgo.adapter.GroupAdapter;
import com.hcmut.packandgo.collection.GroupCollection;
import com.hcmut.packandgo.database.MainDatabase;
import com.hcmut.packandgo.entity.CurrentUser;
import com.hcmut.packandgo.entity.Group;
import com.hcmut.packandgo.entity.User;

public class GroupListFragment extends BaseFragment {

	private User currentUser;
	private ArrayList<GroupCollection> groupCollections;

	public GroupListFragment() {
		this.currentUser = CurrentUser.getCurrentUser().info_user;
		groupCollections = new ArrayList<GroupCollection>();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_group_list, container, false);

		MainDatabase database = new MainDatabase(rootView.getContext());

		// Get friends of current user and covert to array
		int id = Integer.parseInt(currentUser.getId());
		ArrayList<Group> groups = database.getGroupByUserId(id);

		GroupCollection incoming = new GroupCollection("INCOMING");
		GroupCollection started = new GroupCollection("STARTED");
		GroupCollection ended = new GroupCollection("ENDED");

		for (Group group : groups) {
			try {
				Date start = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(group.getTime_start());
				Date end = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(group.getTime_end());
				Date current = new Date();
				if (current.before(start)) {
					incoming.add(group);
				} else if (current.after(end)) {
					ended.add(group);
				} else {
					started.add(group);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		groupCollections.add(started);
		groupCollections.add(incoming);
		groupCollections.add(ended);

		((TextView) rootView.findViewById(R.id.num_groups_fragment_group)).setText(groups.size() + " groups");

		ExpandableListView groupListView = (ExpandableListView) rootView.findViewById(R.id.list_group_fragment_group);
		GroupAdapter adapter = new GroupAdapter(getActivity(), groupCollections);
		groupListView.setAdapter(adapter);
		groupListView.expandGroup(0);
		groupListView.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View view, int groupPosition, int childPosition, long id) {
				Group group = (Group) ((GroupCollection) parent.getItemAtPosition(groupPosition)).get(childPosition);
				Intent intent = new Intent(getActivity(), GroupDetailActivity.class);
				intent.putExtra("Group", group);
				startActivity(intent);
				return true;
			}
		});

		return rootView;
	}
}
