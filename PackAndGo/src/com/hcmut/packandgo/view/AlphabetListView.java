package com.hcmut.packandgo.view;

import java.util.ArrayList;

import android.content.Context;
import android.widget.ListView;

import com.hcmut.packandgo.entity.User;

public class AlphabetListView extends ListView {

	private char character;
	private ArrayList<User> user;
	
	public AlphabetListView(Context context) {
		super(context);
	}
	
	public AlphabetListView(Context context, char character) {
		super(context);
		this.setCharacter(character);
		user = new ArrayList<User>();
	}

	public char getCharacter() {
		return character;
	}

	public void setCharacter(char character) {
		this.character = character;
	}

	public ArrayList<User> getUser() {
		return user;
	}

	public void setUser(ArrayList<User> user) {
		this.user = user;
	}

}
