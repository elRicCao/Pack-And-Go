package com.hcmut.packandgo.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

	private String id;
	private String username;
	private String password;
	private String fullname;
	private String sex;
	private String birthday;
	private String email;
	private String workat;
	private String phone;
	private String phone2;
	

	public User() {
		
	}
	public User(Parcel in) {
		String[] data = new String[10];
		in.readStringArray(data);
		
		this.id = data[0];
		this.username = data[1];
		this.password = data[2];
		this.fullname = data[3];
		this.sex = data[4];
		this.birthday = data[5];
		this.email = data[6];
		this.workat = data[7];
		this.phone = data[8];
		this.phone2 = data[9];
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
				this.username,
				this.password,
				this.fullname,
				this.sex,
				this.birthday,
				this.email,
				this.workat,
				this.phone,
				this.phone2
				 });
	}
	
	@SuppressWarnings("rawtypes")
	public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
		public User createFromParcel(Parcel in) {
			return new User(in);
		}

		public User[] newArray(int size) {
			return new User[size];
		}
	};
	
	public String getId() {
		return this.id;
	}
	public String getUsername() {
		return this.username;
	}
	public String getPassword() {
		return this.password;
	}
	public String getFullname() {
		return this.fullname;
	}
	public String getSex() {
		return this.sex;
	}
	public String getBirthday() {
		return this.birthday;
	}
	public String getEmail() {
		return this.email;
	}
	public String getWorkat() {
		return this.workat;
	}
	public String getPhone() {
		return this.phone;
	}
	public String getPhone2() {
		return this.phone2;
	}
	

	public void setId(String id){
		this.id = id;
	}
	public void setUsername(String username){
		this.username = username;
	}
	public void setPassword(String password){
		this.password = password;
	}
	public void setFullname(String fullname){
		this.fullname = fullname;
	}
	public void setBirthday(String birthday){
		this.birthday = birthday;
	}
	public void setSex(String sex){
		this.sex = sex;
	}
	public void setWorkat(String workat){
		this.workat = workat;
	}
	public void setEmail(String email){
		this.email = email;
	}
	public void setPhone(String phone){
		this.phone = phone;
	}
	public void setPhone2(String phone2){
		this.phone2 = phone2;
	}
}
