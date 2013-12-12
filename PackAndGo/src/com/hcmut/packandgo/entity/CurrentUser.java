package com.hcmut.packandgo.entity;

public class CurrentUser {

	private static CurrentUser current_user = null;
	public User info_user;
	private CurrentUser(){
		
	}
	
	public static CurrentUser getCurrentUser(){
		if(current_user == null) {
			current_user = new CurrentUser();
			current_user.info_user = null;
		}
		return current_user;
	}
}
