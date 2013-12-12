package com.hcmut.packandgo.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Group implements Parcelable {

	private String id;
	private String groupname;
	private String location;
	private String time_start;
	private String time_end;
	

	public Group(){
		
	}
	
	public Group(String groupName, String location, String timeStart, String timeEnd){
		setGroupname(groupName);
		setLocation(location);
		setTime_start(timeStart);
		setTime_end(timeEnd);
	}
	
	
	public Group(Parcel in) {
		String[] data = new String[5];
		in.readStringArray(data);
		
		this.id = data[0];
		this.groupname = data[1];
		this.location = data[2];
		this.time_start = data[3];
		this.time_end = data[4];
		
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeStringArray(new String[] { 
				this.id,
				this.groupname,
				this.location,
				this.time_start,
				this.time_end
				 });
	}
	
	@SuppressWarnings("rawtypes")
	public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
		public Group createFromParcel(Parcel in) {
			return new Group(in);
		}

		public Group[] newArray(int size) {
			return new Group[size];
		}
	};
	
	public String getId(){
		return this.id;
	}
	public String getGroupname(){
		return this.groupname;
	}
	public String getLocation(){
		return this.location;
	}
	public String getTime_start(){
		return this.time_start;
	}
	public String getTime_end(){
		return this.time_end;
	}
	
	
	public void setId(String id){
		this.id = id;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	public void setTime_start(String time_start) {
		this.time_start = time_start;
	}
	public void setTime_end(String time_end) {
		this.time_end = time_end;
	}
}
