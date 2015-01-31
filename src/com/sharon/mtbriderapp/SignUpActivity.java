package com.sharon.mtbriderapp;

<<<<<<< HEAD
import java.util.Observable;
import java.util.Observer;
=======
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
>>>>>>> origin/master

//import com.android.json.login.Home;
//import com.android.json.login.Login;
import android.app.Activity;
<<<<<<< HEAD
import android.content.Intent;
=======
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
>>>>>>> origin/master
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class SignUpActivity extends Activity{

<<<<<<< HEAD

	private EditText email ,user ,pass;
	private Button  btnSignUp;
=======
public class SignUpActivity extends Activity{


	private EditText email ,user ,pass;
	private Button  btnSignUp;

	// Progress Dialog
	private ProgressDialog pDialog;

	// JSON parser class
	JSONParser jsonParser = new JSONParser();

	//php login script

	//localhost :  
	//testing on your device
	//put your local ip instead,  on windows, run CMD > ipconfig
	//or in mac's terminal type ifconfig and look for the ip under en0 or en1
	// private static final String LOGIN_URL = "http://xxx.xxx.x.x:1234/webservice/register.php";

	//testing on Emulator:
	private static final String LOGIN_URL = "http://sharonyar.netau.net/write.php";

//	private SharedPreferences settingSharedPref;

>>>>>>> origin/master

	private Observer wsObserver = new Observer() {

<<<<<<< HEAD
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
=======
	//testing from a real server:
	//private static final String LOGIN_URL = "http://www.yourdomain.com/webservice/register.php";

	//ids
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_MESSAGE = "message";

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
				
			//	SavePrefs(email.getText().toString(), user.getText().toString(), pass.getText().toString());
			//	new CreateUser().execute();
			//	WebServerConnection webser = new WebServerConnection();
			//	webser.update()
				
				String userEmail = email.getText().toString();
				String userName = user.getText().toString();
				String userPassword = pass.getText().toString();
				
				Login log = new Login(userEmail,userName,userPassword);
				
			    WebServerConnection webser = new WebServerConnection();
			    webser.getUserDetail(log);
				
				
			}
		});

/*		settingSharedPref = getSharedPreferences(
				"setting_page_details", MODE_PRIVATE);*/

	}

/*	public void SavePrefs(String email,String user,String pass){

		SharedPreferences.Editor mEditor = settingSharedPref.edit();
		mEditor.putString("email", email);
		mEditor.putString("user", user);
		mEditor.putString("pass", pass);
		mEditor.commit();

	}*/


/*	class CreateUser extends AsyncTask<String, String, String> {

		*//**
		 * Before starting background thread Show Progress Dialog
		 * *//*
		boolean failure = false;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(SignUpActivity.this);
			pDialog.setMessage("Creating User...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}
>>>>>>> origin/master

		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub
			// Check for success tag
			int success;
			String email2 = email.getText().toString();
			String username = user.getText().toString();
			String password = pass.getText().toString();
			try {
				// Building Parameters
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("email", email2));
				params.add(new BasicNameValuePair("user", username));
				params.add(new BasicNameValuePair("pass", password));

				//   Log.d("request!", "starting");

				//Posting user data to script 
				JSONObject json = jsonParser.makeHttpRequest(
						LOGIN_URL, "POST", params);

				// full json response
				Log.d("Login attempt", json.toString());

				// json success element
				success = json.getInt(TAG_SUCCESS);
				if (success == 1) {
					Log.d("User Created!", json.toString());  
					Intent i = new Intent(SignUpActivity.this, MainScreenActivity.class);
					finish();
					startActivity(i);
					return json.getString(TAG_MESSAGE);
				}else{
					Log.d("Login Failure!", json.getString(TAG_MESSAGE));
					return json.getString(TAG_MESSAGE);

				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;

<<<<<<< HEAD
=======
		}
		*//**
		 * After completing background task Dismiss the progress dialog
		 * **//*
		protected void onPostExecute(String file_url) {
			// dismiss the dialog once product deleted
			pDialog.dismiss();
			if (file_url != null){
				Toast.makeText(SignUpActivity.this, file_url, Toast.LENGTH_LONG).show();
			}

		}

	}

*/
}


>>>>>>> origin/master
