package com.hcmut.packandgo.fragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.hcmut.packandgo.R;
import com.hcmut.packandgo.activity.UserDetailActivity;
import com.hcmut.packandgo.adapter.ListViewAdapter;
import com.hcmut.packandgo.database.MainDatabase;
import com.hcmut.packandgo.entity.CurrentUser;
import com.hcmut.packandgo.entity.User;
import com.hcmut.packandgo.view.AlphabetListView;

public class FriendListFragment extends BaseFragment {

	/* Store login user */
	private User currentUser;
	
	public FriendListFragment() {
		this.currentUser = CurrentUser.getCurrentUser().info_user;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		// Inflate layout
		View rootView = inflater.inflate(R.layout.fragment_friend_list, container, false);
		
		// Get database
		MainDatabase database = new MainDatabase(getActivity());
		
		// Get friends of current user, sort and covert to array
		int id = Integer.parseInt(currentUser.getId());
		ArrayList<User> friends = database.getFriendByUserId(id);
		Collections.sort(friends, new Comparator<User>() {
			@Override
			public int compare(User user1, User user2) {
				return user1.getUsername().compareTo(user2.getUsername());
			}
		});
		
		// List of List corresponding to alphabet character
		ArrayList<AlphabetListView> subList = new ArrayList<AlphabetListView>();
		
		// Populate list of alphabet list with friends
		for (char i = 'A'; i < 'Z'; i++) {
			AlphabetListView alphabetListView = new AlphabetListView(getActivity(), i);
			for (User friend: friends) {
				if (Character.toUpperCase(friend.getUsername().charAt(0)) == i) {
					alphabetListView.getUser().add(friend);
				}
			}
			if (alphabetListView.getUser().size() > 0)
				subList.add(alphabetListView);
		}
		
		// Create adapter
		ListViewAdapter adapter = new ListViewAdapter(getActivity(), subList);
		
		// Get list view and set adapter
		ListView friendListView = (ListView) rootView.findViewById(R.id.list_friend);
		friendListView.setAdapter(adapter);
		
		((TextView) rootView.findViewById(R.id.num_contacts)).setText(friends.size() + " contacs");
		
		rootView.findViewById(R.id.view_profile).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), UserDetailActivity.class);
				intent.putExtra("User", currentUser);
				startActivity(intent);
			}
		});
		
		return rootView;
	}
}
