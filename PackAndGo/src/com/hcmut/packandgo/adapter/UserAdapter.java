package com.hcmut.packandgo.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hcmut.packandgo.R;
import com.hcmut.packandgo.entity.User;
import com.hcmut.packandgo.viewtag.UserViewTag;

public class UserAdapter extends ArrayAdapter<User> {

	private LayoutInflater inflater;
	
	public UserAdapter(Context context, List<User> users) {
		super(context, 0, users);
		inflater = LayoutInflater.from(context);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		/* When it loads for the first time, inflate layout and bind layout object */
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item_user, null);
			
			UserViewTag tag = new UserViewTag();
			tag.setUsernameView((TextView) convertView.findViewById(R.id.item_user_name));
			tag.setAvatarView((ImageView) convertView.findViewById(R.id.item_user_avatar));
			
			convertView.setTag(tag);
		}
		
		User friend = getItem(position);
		
		UserViewTag tag = (UserViewTag) convertView.getTag();
		tag.getUsernameView().setText(friend.getUsername());
		// TODO: get image for each user
		tag.getAvatarView().setImageResource(R.drawable.ic_action_user);
		
		return convertView;
	}
}
