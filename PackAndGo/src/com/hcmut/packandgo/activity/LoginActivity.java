package com.hcmut.packandgo.activity;

import com.hcmut.packandgo.R;
import com.hcmut.packandgo.database.MainDatabase;
import com.hcmut.packandgo.entity.CurrentUser;
import com.hcmut.packandgo.entity.User;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class LoginActivity extends Activity {

	private EditText inputEmail;
	private EditText inputPassword;
	
	private MainDatabase database = new MainDatabase(this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		getControl();
		database.addFakeData();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	/**
	 * Binding UI referenced objects and setting up events
	 */
	private void getControl() {
		inputEmail = (EditText) findViewById(R.id.login_input_email);
		inputPassword = (EditText) findViewById(R.id.login_input_password);
		
		findViewById(R.id.login_button_sign_in).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if (atempSignIn()) {
					Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//					Intent intent = new Intent(LoginActivity.this, UserDetailActivity.class);
					startActivity(intent);
					finish();
				}
			}
		});
		
		findViewById(R.id.login_button_register).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
				startActivity(intent);
			}
		});
	}
	
	/**
	 * Check email and password
	 * @return true if log in information is correct, otherwise return false
	 */
	private boolean atempSignIn() {
		
		String email = inputEmail.getText().toString();
		String password = inputPassword.getText().toString();
		
		if (TextUtils.isEmpty(email)) {
			inputEmail.setError("Please enter your email");
			return false;
		}
		if (TextUtils.isEmpty(password)) {
			inputPassword.setError("Please enter your password");
			return false;
		}
		
		for (User user: database.getAllUser()) {
			if (TextUtils.equals(email, user.getUsername()) && TextUtils.equals(password, user.getPassword())) {
				CurrentUser.getCurrentUser().info_user = user;
				return true;
			}
		}
		
		inputEmail.setError("Your username or password is invalid");
		inputEmail.setText(null);
		inputPassword.setText(null);
		inputEmail.requestFocus();
		return false;
	}
}
