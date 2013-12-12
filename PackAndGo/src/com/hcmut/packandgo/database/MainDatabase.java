package com.hcmut.packandgo.database;

/*
 * addUser(User user):add a user to table User 
 * addGroup(Group group):add a group to table Group
 * addUsertoGroup(int userid,int groupid): add a user to a group.
 * 
 * getUserById(int userid) : return a record user by id
 * getUserByGroupId(int groupid): return users in 1 group by id
 * getAllUser() : return all of users
 * 
 * getGroupById(int groupid):.....
 * getGroupByUserId(int userid) : return groups that a user follow
 * getAllGroup(): return all of group
 * 
 * getFriendById(int userid): return users that a specified user make friend or follow.
 * 
 * updateUser(User user) : changes values record by new user.
 * updateGroup(Group group): change values record by new group.
 * 
 * deleteUser(User user) : delete a specified user
 * deleteGroup(Group group) : delete a specified group
 * 
 * deleteUser() : delete all record users.
 * deleteGroup() : delete all record groups.
 * 
 * in table relation ship : if type_relationship is 1 mean user1 follow user2,is 2 is user1 and user2 is friend
 * */
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hcmut.packandgo.entity.Group;
import com.hcmut.packandgo.entity.User;

public class MainDatabase extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;

	private static final String DATABASE_NAME = "TravelDB";

	private static final String TABLE_USER = "users";

	private static final String KEY_ID_USER = "id_user";
	private static final String KEY_USERNAME_USER = "username_user";
	private static final String KEY_FULLNAME_USER = "fullname_user";
	private static final String KEY_PASSWORD_USER = "password_user";
	private static final String KEY_SEX_USER = "gender_user";
	private static final String KEY_BIRTHDAY_USER = "birthday_user";
	private static final String KEY_EMAIL_USER = "email_user";
	private static final String KEY_WORKAT_USER = "workat_user";
	private static final String KEY_PHONE_USER = "phone_user";
	private static final String KEY_PHONE2_USER = "phone2_user";
	
	private static final String TABLE_GROUP = "groups";

	private static final String KEY_ID_GROUP = "id_group";
	private static final String KEY_GROUPNAME_GROUP = "groupname_group";
	private static final String KEY_LOCATION_GROUP = "location_group";
	private static final String KEY_TIMESTART_GROUP = "timestart_group";
	private static final String KEY_TIMEEND_GROUP = "timeend_group";
	
	
	private static final String TABLE_USER_GROUP = "user_group";

	private static final String KEY_ID_UG = "id_user_group";
	private static final String KEY_IDUSER_UG = "id_user";
	private static final String KEY_IDGROUP_UG = "id_group";
	
	private static final String TABLE_RELATIONSHIP = "relationship";
	
	private static final String KEY_ID_RELATIONSHIP = "id_relationship";
	private static final String KEY_IDUSER1 = "id_user1";
	private static final String KEY_IDUSER2 = "id_user2";
	
	
	

	public MainDatabase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_USER + "("
											+ KEY_ID_USER + " INTEGER PRIMARY KEY, "
											+ KEY_USERNAME_USER + " TEXT,"
											+ KEY_PASSWORD_USER + " TEXT,"
											+ KEY_FULLNAME_USER + " TEXT," 
											+ KEY_SEX_USER + " TEXT," 
											+ KEY_BIRTHDAY_USER + " TEXT," 
											+ KEY_EMAIL_USER + " TEXT,"
											+ KEY_WORKAT_USER + " TEXT,"
											+ KEY_PHONE_USER + " TEXT,"
											+ KEY_PHONE2_USER + " TEXT" 
											+ ")";
		
		String CREATE_TABLE_GROUP = "CREATE TABLE " + TABLE_GROUP + " ( "
											+ KEY_ID_GROUP + " INTEGER PRIMARY KEY, "
											+ KEY_GROUPNAME_GROUP + " TEXT,"
											+ KEY_LOCATION_GROUP + " TEXT,"
											+ KEY_TIMESTART_GROUP + " TEXT,"
											+ KEY_TIMEEND_GROUP + " TEXT" 
											+ ")";
		String CREATE_TABLE_USER_GROUP = "CREATE TABLE " + TABLE_USER_GROUP + "("
				+ KEY_ID_UG + " INTEGER PRIMARY KEY, "
				+ KEY_IDUSER_UG + " TEXT,"
				+ KEY_IDGROUP_UG + " TEXT" 
				+ ")";
		
		String CREATE_TABLE_RELATIONSHIP = "CREATE TABLE " + TABLE_RELATIONSHIP + "("
				+ KEY_ID_RELATIONSHIP + " INTEGER PRIMARY KEY, "
				+ KEY_IDUSER1 + " TEXT,"
				+ KEY_IDUSER2 + " TEXT" 
				+ ")";
		
		db.execSQL(CREATE_TABLE_USER);
		db.execSQL(CREATE_TABLE_GROUP);
		db.execSQL(CREATE_TABLE_USER_GROUP);
		db.execSQL(CREATE_TABLE_RELATIONSHIP);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		db.execSQL("DROP TABLE IF EXISTS "+TABLE_USER);
		db.execSQL("DROP TABLE IF EXISTS "+TABLE_GROUP);
		db.execSQL("DROP TABLE IF EXISTS "+TABLE_USER_GROUP);
		
		onCreate(db);

	}
	
	public void addUser(User user) {
		SQLiteDatabase db = this.getWritableDatabase();
		
		
		ContentValues info_user = new ContentValues();
		info_user.put(KEY_USERNAME_USER, user.getUsername());
		info_user.put(KEY_PASSWORD_USER, user.getPassword());
		info_user.put(KEY_FULLNAME_USER, user.getFullname());
		info_user.put(KEY_SEX_USER, user.getSex());
		info_user.put(KEY_BIRTHDAY_USER, user.getBirthday());
		info_user.put(KEY_EMAIL_USER, user.getEmail());
		info_user.put(KEY_WORKAT_USER,user.getWorkat());
		info_user.put(KEY_PHONE_USER, user.getPhone());
		info_user.put(KEY_PHONE2_USER,user.getPhone2());
		
		db.insert(TABLE_USER, null, info_user);
		db.close();
		
	}
	
	public void addGroup(Group group) {
		SQLiteDatabase db = this.getWritableDatabase();
		
		
		ContentValues info_group = new ContentValues();
		info_group.put(KEY_GROUPNAME_GROUP, group.getGroupname());
		info_group.put(KEY_LOCATION_GROUP, group.getLocation());
		info_group.put(KEY_TIMESTART_GROUP, group.getTime_start());
		info_group.put(KEY_TIMEEND_GROUP, group.getTime_end());
		
		db.insert(TABLE_GROUP, null, info_group);
		db.close();
	}
	

	public void addUsertoGroup(int userid,int groupid){
		SQLiteDatabase db = this.getWritableDatabase();
		
		
		ContentValues info_user_group = new ContentValues();
		info_user_group.put(KEY_IDUSER_UG, userid);
		info_user_group.put(KEY_IDGROUP_UG, groupid);

		
		db.insert(TABLE_USER_GROUP, null, info_user_group);
		db.close();
	}
	
	public void makeFollow(int userid1,int userid2){
		SQLiteDatabase db = this.getWritableDatabase();
		
		
		ContentValues info_user = new ContentValues();
		info_user.put(KEY_IDUSER1, userid1);
		info_user.put(KEY_IDUSER2, userid2);

		
		db.insert(TABLE_RELATIONSHIP, null, info_user);
		db.close();
	}
	
	public User getUserById(int id) {
		
		SQLiteDatabase db = this.getReadableDatabase();
		
		Cursor cursor = db.query(	TABLE_USER, 
									new String[] {	
											 KEY_ID_USER,
											 KEY_USERNAME_USER,
											 KEY_PASSWORD_USER,
											 KEY_FULLNAME_USER, 
											 KEY_SEX_USER, 
											 KEY_BIRTHDAY_USER, 
											 KEY_EMAIL_USER, 
											 KEY_WORKAT_USER, 
											 KEY_PHONE_USER, 
											 KEY_PHONE2_USER
													} ,
									KEY_ID_USER + "=?",
									new String[] { String.valueOf(id) },
									null, null, null,null);
		
		if(cursor != null)
			cursor.moveToFirst();
		
		User user = new User();
		user.setId(cursor.getString(0));
		user.setUsername(cursor.getString(1));
		user.setPassword(cursor.getString(2));
		user.setFullname(cursor.getString(3));
		user.setSex(cursor.getString(4));
		user.setBirthday(cursor.getString(5));
		user.setEmail(cursor.getString(6));
		user.setWorkat(cursor.getString(7));
		user.setPhone(cursor.getString(8));
		user.setPhone2(cursor.getString(9));
		
		return user;
	}
	
	public ArrayList<User> getUserByGroupId(int groupid){
		
		ArrayList<User> list_user = new ArrayList<User>();
		
		SQLiteDatabase db = this.getReadableDatabase();
		
		Cursor cursor_user = db.query(	TABLE_USER_GROUP, 
									new String[] {
											KEY_IDGROUP_UG,
											 KEY_IDUSER_UG
													} ,
									KEY_IDGROUP_UG + "=?",
									new String[] { String.valueOf(groupid) },
									null, null, null,null);
		
		if(cursor_user.moveToFirst()) {
			do {
				User user = this.getUserById(Integer.valueOf(cursor_user.getString(1)));
				list_user.add(user);
		       }
			while(cursor_user.moveToNext());
		}
		return list_user;
	}
	public ArrayList<User> getAllUser() {
		
		ArrayList<User> list_user = new ArrayList<User>();
		
		String selectAll = "SELECT * FROM " +TABLE_USER;
		
		SQLiteDatabase db = this.getWritableDatabase();
		
		Cursor cursor = db.rawQuery(selectAll, null);
		
		if(cursor.moveToFirst()) {
			do {
				User user = new User();
				user.setId(cursor.getString(0));
				user.setUsername(cursor.getString(1));
				user.setPassword(cursor.getString(2));
				user.setFullname(cursor.getString(3));
				user.setSex(cursor.getString(4));
				user.setBirthday(cursor.getString(5));
				user.setEmail(cursor.getString(6));
				user.setWorkat(cursor.getString(7));
				user.setPhone(cursor.getString(8));
				user.setPhone2(cursor.getString(9));
				list_user.add(user);
			}
			while(cursor.moveToNext());
		}
		return list_user;
	}
	
	
	public Group getGroupById(int groupid){
		
		SQLiteDatabase db = this.getReadableDatabase();
		
		Cursor cursor = db.query(	TABLE_GROUP, 
									new String[] {	
											 KEY_ID_GROUP,
											 KEY_GROUPNAME_GROUP,
											 KEY_LOCATION_GROUP,
											 KEY_TIMESTART_GROUP, 
											 KEY_TIMEEND_GROUP
													} ,
									KEY_ID_GROUP + "=?",
									new String[] { String.valueOf(groupid) },
									null, null, null,null);
		
		if(cursor != null)
			cursor.moveToFirst();
		
		Group group = new Group();
		group.setId(cursor.getString(0));
		group.setGroupname(cursor.getString(1));
		group.setLocation(cursor.getString(2));
		group.setTime_start(cursor.getString(3));
		group.setTime_end(cursor.getString(4));
		
		return group;
	}
	public ArrayList<Group> getGroupByUserId(int userid){
		
		ArrayList<Group> list_group = new ArrayList<Group>();
		
		SQLiteDatabase db = this.getReadableDatabase();
		
		Cursor cursor_group = db.query(	TABLE_USER_GROUP, 
									new String[] {	
											 KEY_IDUSER_UG, 
											 KEY_IDGROUP_UG
													} ,
									KEY_IDUSER_UG + "=?",
									new String[] { String.valueOf(userid) },
									null, null, null,null);
		
		if(cursor_group.moveToFirst()) {
			do {
				Group group = this.getGroupById(Integer.valueOf(cursor_group.getString(1)));
				list_group.add(group);
		       }
			while(cursor_group.moveToNext());
		}
		return list_group;
	}
	
	public Group getLastGroup() {
		String selectQuery = "SELECT  * FROM " + TABLE_GROUP;
		
		SQLiteDatabase db = this.getWritableDatabase();
		
		 Cursor cursor = db.rawQuery(selectQuery, null);
		 
		 cursor.moveToLast();
		  
		Group group = new Group();
		group.setId(cursor.getString(0));
		group.setGroupname(cursor.getString(1));
		group.setLocation(cursor.getString(2));
		group.setTime_start(cursor.getString(3));
		group.setTime_end(cursor.getString(4));
		
		return group;
	}
	public ArrayList<Group> getAllGroup(){
		
		ArrayList<Group> list_group = new ArrayList<Group>();
		
		String selectAll = "SELECT * FROM " +TABLE_GROUP;
		
		SQLiteDatabase db = this.getWritableDatabase();
		
		Cursor cursor = db.rawQuery(selectAll, null);
		
		if(cursor.moveToFirst()) {
			do {
				Group group = new Group();
				group.setId(cursor.getString(0));
				group.setGroupname(cursor.getString(1));
				group.setLocation(cursor.getString(2));
				group.setTime_start(cursor.getString(3));
				group.setTime_end(cursor.getString(4));
				list_group.add(group);
			}
			while(cursor.moveToNext());
		}
		return list_group;
	}
	
	public ArrayList<User> getFriendByUserId(int userid){
		
		ArrayList<User> list_user = new ArrayList<User>();
		
		SQLiteDatabase db = this.getReadableDatabase();
		
		Cursor cursor_user = db.query(	TABLE_RELATIONSHIP, 
									new String[] {	
											 KEY_IDUSER1,
											 KEY_IDUSER2
													} ,
									KEY_IDUSER1 + "=?",
									new String[] { String.valueOf(userid) },
									null, null, null,null);
		
		if(cursor_user.moveToFirst()) {
			do {
				User user = this.getUserById(Integer.valueOf(cursor_user.getString(1)));
				list_user.add(user);
		       }
			while(cursor_user.moveToNext());
		}
		return list_user;
	}
	
	public void updateUser(User user) {
		
		SQLiteDatabase db = this.getWritableDatabase();
		
		
		ContentValues info_user = new ContentValues();
		info_user.put(KEY_USERNAME_USER, user.getUsername());
		info_user.put(KEY_PASSWORD_USER, user.getPassword());
		info_user.put(KEY_FULLNAME_USER, user.getFullname());
		info_user.put(KEY_SEX_USER, user.getSex());
		info_user.put(KEY_BIRTHDAY_USER, user.getBirthday());
		info_user.put(KEY_EMAIL_USER, user.getEmail());
		info_user.put(KEY_WORKAT_USER,user.getWorkat());
		info_user.put(KEY_PHONE_USER, user.getPhone());
		info_user.put(KEY_PHONE2_USER,user.getPhone2());
		
		 db.update(	TABLE_USER, 
				 	info_user,
					KEY_ID_USER + " =? ",
					new String[] {  String.valueOf(user.getId() ) }
				 );
		 db.close();
	}
	
	public void updateGroup(Group group){
		
		SQLiteDatabase db = this.getWritableDatabase();
		
		
		ContentValues info_group = new ContentValues();
		info_group.put(KEY_GROUPNAME_GROUP, group.getGroupname());
		info_group.put(KEY_LOCATION_GROUP, group.getLocation());
		info_group.put(KEY_TIMESTART_GROUP, group.getTime_start());
		info_group.put(KEY_TIMEEND_GROUP, group.getTime_end());
		
		 db.update(	TABLE_GROUP, 
				 	info_group,
					KEY_ID_GROUP + " =? ",
					new String[] {  String.valueOf(group.getId() ) }
				 );
		 db.close();
	}
	public void deleteUser(User user) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_USER, KEY_ID_USER + " =? ", new String[] { String.valueOf(user.getId()) });
		db.close();
	}
	
	public void deleteAllUser() {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_USER, null, null);
		db.close();
	}
	
	public void deleteGroup(Group group) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_GROUP, KEY_ID_GROUP + " =? ", new String[] { String.valueOf(group.getId()) });
		db.close();
	}
	
	public void deleteAllGroup() {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_GROUP, null, null);
		db.close();
	}
	
	public void deleteAllUserGroup() {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_USER_GROUP, null, null);
		db.close();
	}
	
	public void deleteAllRelationship() {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_RELATIONSHIP, null, null);
		db.close();
	}

	public void addFakeData() {
		User ob1 = new User();
		ob1.setUsername("lyminhtri");
		ob1.setPassword("123");
		ob1.setEmail("lmtkg1992@gmail.com");
		
		User ob2 = new User();
		ob2.setUsername("phucvinh");
		ob2.setPassword("123");
		ob2.setFullname("TRUONG PHUC VINH");
		
		User ob3 = new User();
		ob3.setUsername("nhatduy");
		ob3.setPassword("123");
		ob3.setPhone("1234567890");
		
		User ob4 = new User();
		ob4.setUsername("danghoang");
		ob4.setPassword("123");
		ob4.setBirthday("1/1/1992");
		
		User ob5 = new User();
		ob5.setUsername("hoaivu");
		ob5.setPassword("1234");
		ob5.setSex("Male");
		
		User ob6 = new User();
		ob6.setUsername("nva");
		ob6.setPassword("1234");
		ob6.setWorkat("TPHCM");

		User ob7 = new User();
		ob7.setUsername("nvb");
		ob7.setPassword("1234");
		ob7.setPhone2("123457667");
		
		this.deleteAllUser();
		this.addUser(ob1);
		this.addUser(ob2);
		this.addUser(ob3);
		this.addUser(ob4);
		this.addUser(ob5);
		this.addUser(ob6);
		this.addUser(ob7);
		
		ob1 = this.getUserById(1);
		ob1.setFullname("LY MINH TRI");
		this.updateUser(ob1);
		
		
		Group gr1 = new Group();
		gr1.setGroupname("BK1");
		gr1.setLocation("Da Lat");
		gr1.setTime_start("1/1/2013");
		gr1.setTime_end("3/1/2013");
		
		Group gr2 = new Group();
		gr2.setGroupname("BK2");
		gr2.setLocation("Vinh Ha Long");
		gr2.setTime_start("1/1/2013");
		gr2.setTime_end("3/1/2013");

		
		Group gr3 = new Group();
		gr3.setGroupname("BK3");
		gr3.setLocation("Can Gio");
		gr3.setTime_start("1/1/2013");
		gr3.setTime_end("3/1/2013");
		
		this.deleteAllGroup();
		this.addGroup(gr1);
		this.addGroup(gr2);
		this.addGroup(gr3);
		
		this.deleteAllUserGroup();
		this.addUsertoGroup(1, 1);
		this.addUsertoGroup(3, 1);
		this.addUsertoGroup(5, 1);
		this.addUsertoGroup(7, 1);
		this.addUsertoGroup(1, 2);
		this.addUsertoGroup(2, 2);
		this.addUsertoGroup(3, 2);
		this.addUsertoGroup(6, 2);
		this.addUsertoGroup(4, 2);
		this.addUsertoGroup(4, 3);
		this.addUsertoGroup(5, 3);
		this.addUsertoGroup(6, 3);
		this.addUsertoGroup(7, 3);
		
		//friend 
		this.deleteAllRelationship();
		this.makeFollow(1,2);
		this.makeFollow(1,3);
		this.makeFollow(1,4);
		this.makeFollow(1,5);
		
	}
}
