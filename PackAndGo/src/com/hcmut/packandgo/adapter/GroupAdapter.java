package com.hcmut.packandgo.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.TextView;

import com.hcmut.packandgo.R;
import com.hcmut.packandgo.collection.GroupCollection;
import com.hcmut.packandgo.entity.Group;

public class GroupAdapter extends BaseExpandableListAdapter {

	private LayoutInflater inflater;
	private ArrayList<GroupCollection> groupCollections;
	
	public GroupAdapter(Context context, ArrayList<GroupCollection> groupCollections) {
		this.inflater = LayoutInflater.from(context);
		this.groupCollections = groupCollections;
	}

	@Override
	public Object getChild(int position, int childPosition) {
		return groupCollections.get(position).get(childPosition);
	}

	@Override
	public long getChildId(int position, int childPosition) {
		return 0;	
	}

	@Override
	public View getChildView(int position, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
		
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item_group, null);
		}
		
		Group group = (Group) getChild(position, childPosition);
		((TextView) convertView.findViewById(R.id.item_group_name)).setText(group.getGroupname());
		((ImageView) convertView.findViewById(R.id.item_group_avatar)).setImageResource(R.drawable.ic_action_group);
		
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return groupCollections.get(groupPosition).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return groupCollections.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return groupCollections.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return 0;
	}
	

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.list_group, null);
		}
		GroupCollection groupCollection = (GroupCollection) getGroup(groupPosition);
		((CheckedTextView) convertView.findViewById(R.id.group_name_list_group)).setText(groupCollection.size() + " " + groupCollection.getName());
		((CheckedTextView) convertView.findViewById(R.id.group_name_list_group)).setChecked(true);
		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

	
}
