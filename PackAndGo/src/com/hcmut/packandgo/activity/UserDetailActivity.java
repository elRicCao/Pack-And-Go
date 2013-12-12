package com.hcmut.packandgo.activity;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.hcmut.packandgo.R;
import com.hcmut.packandgo.entity.User;

public class UserDetailActivity extends ActionBarActivity {

	private User user;
	private TextView tvLocation, tvPhone, tvEmail, tvAddress;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_detail);
		
		// Get user
		user = getIntent().getParcelableExtra("User");
		
		// Configure action bar
		ActionBar actionBar = getActionBar();
		actionBar.setTitle(user.getUsername());
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setIcon(R.drawable.ic_action_user);
		
		// Bind layout objects
		tvLocation = (TextView) findViewById(R.id.user_detail_location);
		tvPhone = (TextView) findViewById(R.id.user_detail_phone);
		tvEmail = (TextView) findViewById(R.id.user_detail_email);
		tvAddress = (TextView) findViewById(R.id.user_detail_address);
		
		// Set field value
		// TODO: get info of this user
		tvLocation.setText("Somewhere on Earth");
		tvPhone.setText("0123456789");
		tvEmail.setText("abcdef@xyz.com");
		tvAddress.setText("123, ABC Street, XYZ City");
		
		// TODO: call when phone number clicked
		tvPhone.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
		// TODO: send email when phone number clicked
		tvEmail.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu
		getMenuInflater().inflate(R.menu.user_detail, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO: add event when menu item clicked
		switch(item.getItemId()) {
		
		case R.id.action_edit:
			return true;
			
		case R.id.action_delete:
			return true;
			
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
