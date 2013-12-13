package com.hcmut.packandgo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hcmut.packandgo.R;
import com.hcmut.packandgo.database.MainDatabase;
import com.hcmut.packandgo.entity.CurrentUser;
import com.hcmut.packandgo.entity.User;

public class EditUserActivity extends Activity {
	MainDatabase db = new MainDatabase(this);
	EditText userName, password, email, address, phone;
	Button updateUser, cancel;
	User currentUser;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_user);
		getControl();
		// Get user
		currentUser = CurrentUser.getCurrentUser().info_user;;
		showInfo(currentUser);
		updateUser.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//updateUser
				User copyOfCurrentUser = new User();
				copyOfCurrentUser.setUsername(userName.getText().toString());
				copyOfCurrentUser.setPassword(password.getText().toString());
				copyOfCurrentUser.setEmail(email.getText().toString());
				copyOfCurrentUser.setWorkat(address.getText().toString());
				copyOfCurrentUser.setPhone(phone.getText().toString());
				//copyOfCurrentUser.setId(currentUser.getId);
				db.updateUser(copyOfCurrentUser);
			}
		});
	}
	private void getControl() {
		userName = (EditText) findViewById(R.id.item_user_username_field);
		password= (EditText) findViewById(R.id.item_user_password_field);
		email= (EditText) findViewById(R.id.item_user_email_field);
		address= (EditText) findViewById(R.id.item_user_address_field);
		phone= (EditText) findViewById(R.id.item_user_phone_field);
		updateUser = (Button) findViewById(R.id.item_user_button_update);
		cancel= (Button) findViewById(R.id.item_user_button_cancel);
		
	}
	private void showInfo(User currentUser) {
		userName.setText(currentUser.getFullname());
		email.setText(currentUser.getEmail());
		password.setText(currentUser.getPassword());
		address.setText(currentUser.getWorkat());
		phone.setText(currentUser.getPhone());
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		/*getMenuInflater().inflate(R.menu.register, menu);*/
		return true;
	}

}
