package com.hcmut.packandgo.fragment;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.SupportMapFragment;
import com.hcmut.packandgo.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class GroupMapFragment extends BaseFragment {
	
	MapView mapView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_group_map, container, false);
		
//		GoogleMap map = ((SupportMapFragment) getFragmentManager().findFragmentById(R.id.map_group_fragment)).getMap();
		
		mapView = (MapView) rootView.findViewById(R.id.mapView);
		mapView.onCreate(savedInstanceState);
		
		return rootView;	
	}
}
