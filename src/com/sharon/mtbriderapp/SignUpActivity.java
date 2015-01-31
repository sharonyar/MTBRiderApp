package com.sharon.mtbriderapp;

import java.util.Observable;
import java.util.Observer;

//import com.android.json.login.Home;
//import com.android.json.login.Login;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class SignUpActivity extends Activity{


	private EditText email ,user ,pass;
	private Button  btnSignUp;

	private Observer wsObserver = new Observer() {

		@Override
		public void update(Observable observable, Object data) {
			Boolean success = (Boolean) data;
			if (success) {
				Log.d("SignUpActivity", "User Created!");  
				Intent i = new Intent(SignUpActivity.this, MainScreenActivity.class);
				finish();
				startActivity(i);
			}
			else {
				Toast.makeText(SignUpActivity.this, success.toString(), Toast.LENGTH_LONG).show();
			}

		}};


		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_sign_up_screen);

			email = (EditText)findViewById(R.id.etEmail);
			user = (EditText)findViewById(R.id.etUserName);
			pass = (EditText)findViewById(R.id.etPass);

			btnSignUp = (Button)findViewById(R.id.btnSignUp);
			btnSignUp.setOnClickListener(new View.OnClickListener() {


				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub

					String userEmail = email.getText().toString();
					String userName = user.getText().toString();
					String userPassword = pass.getText().toString();

					Login log = new Login(userEmail,userName,userPassword);

					WebServerConnection webser = new WebServerConnection();
					webser.addObserver(wsObserver);
					webser.getUserDetail(log);


				}
			});
		}
}


