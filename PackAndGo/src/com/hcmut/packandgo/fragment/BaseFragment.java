package com.hcmut.packandgo.fragment;

import android.support.v4.app.Fragment;

public class BaseFragment extends Fragment {
	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
