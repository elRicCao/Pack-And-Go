package com.hcmut.packandgo.collection;

import java.util.ArrayList;

import com.hcmut.packandgo.entity.Group;

public class GroupCollection {

	private String name;
	private ArrayList<Group> groups;
	
	public GroupCollection(String name) {
		this.setName(name);
		groups = new ArrayList<Group>();
	}
	
	public void add(Group group) {
		groups.add(group);
	}
	
	public Group get(int position) {
		if (position < groups.size())
			return groups.get(position);
		return null;
	}
	
	public int size() {
		return groups.size();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Group> getGroups() {
		return groups;
	}

	public void setGroups(ArrayList<Group> groups) {
		this.groups = groups;
	}

}
