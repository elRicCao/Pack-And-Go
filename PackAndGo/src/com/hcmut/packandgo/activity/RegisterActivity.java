package com.hcmut.packandgo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hcmut.packandgo.R;
import com.hcmut.packandgo.database.MainDatabase;
import com.hcmut.packandgo.entity.User;

public class RegisterActivity extends Activity {
	MainDatabase db = new MainDatabase(this);
	EditText userName, password, email, address, phone;
	Button createUser, cancel;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		getControl();
		createUser.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				User newUser = new User();
				newUser.setUsername(userName.getText().toString());
				newUser.setPassword(password.getText().toString());
				newUser.setEmail(email.getText().toString());
				newUser.setWorkat(address.getText().toString());
				newUser.setPhone(phone.getText().toString());
				db.addUser(newUser);
			}
		});
	}
	private void getControl() {
		userName = (EditText) findViewById(R.id.username_field);
		password= (EditText) findViewById(R.id.password_field);
		email= (EditText) findViewById(R.id.email_field);
		address= (EditText) findViewById(R.id.address_field);
		phone= (EditText) findViewById(R.id.phone_field);
		createUser = (Button) findViewById(R.id.register_button_register);
		cancel= (Button) findViewById(R.id.register_button_cancel);
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
		return true;
	}

}
