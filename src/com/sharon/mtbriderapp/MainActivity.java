package com.sharon.mtbriderapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	Button btnSignIn,btnSignUp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initialize();

	}
	public void initialize(){



		btnSignIn = (Button) findViewById(R.id.btnSingIn);
		btnSignUp = (Button) findViewById(R.id.btnSignUp);


		btnSignIn.setOnClickListener(this);
		btnSignUp.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {

		Intent i = null;
		switch(v.getId()){
		case R.id.btnSingIn:
			i = new Intent(this,SignInActivity.class);
			break;
		case R.id.btnSignUp:
			i = new Intent(this,SignUpActivity.class);
			break;
		}
		startActivity(i);

	}

}









