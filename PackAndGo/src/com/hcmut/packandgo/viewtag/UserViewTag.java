package com.hcmut.packandgo.viewtag;

import android.widget.ImageView;
import android.widget.TextView;

public class UserViewTag {

	private TextView usernameView;
	private ImageView avatarView;
	
	public TextView getUsernameView() {
		return usernameView;
	}
	public void setUsernameView(TextView usernameView) {
		this.usernameView = usernameView;
	}
	public ImageView getAvatarView() {
		return avatarView;
	}
	public void setAvatarView(ImageView avatarView) {
		this.avatarView = avatarView;
	}
}
