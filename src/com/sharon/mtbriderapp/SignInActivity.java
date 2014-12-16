package com.sharon.mtbriderapp;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.LoginFilter.UsernameFilterGeneric;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignInActivity extends Activity implements OnClickListener {

	EditText etEmail , etPass;
	Button btnSignIn;

	String email , password;

	private List<Login> user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_in_screen);

		initialize();

	}
	private void initialize(){

		WebServerConnection web  = new  WebServerConnection();
		user = web.getAllUsers();
		etEmail = (EditText) findViewById(R.id.etEmail);
		etPass = (EditText) findViewById(R.id.etPass);
		btnSignIn = (Button) findViewById(R.id.btnSignIn);
		btnSignIn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		email = etEmail.getText().toString();
		password = etPass.getText().toString();

		int i = 0;

		try{
			while(i>=0){
				if(email.equals(user.get(i).getEmail())&&password.equals(user.get(i).getPass())){
					//	Toast.makeText(getApplicationContext(), user.get(i).getEmail(), Toast.LENGTH_LONG).show();

					Intent ms;
					ms = new Intent(this,MainScreenActivity.class);
					startActivity(ms);
					break;
				}			
				i++;
			}
		}
		catch(Exception e){
			e.printStackTrace();
			Toast.makeText(getBaseContext(), "login unsuccessful", Toast.LENGTH_LONG).show();

		}
	}

}

