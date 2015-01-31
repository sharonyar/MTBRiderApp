package com.sharon.mtbriderapp;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class OpenRide extends Activity implements OnClickListener {

	private EditText name ,date ,hour;
	private Button btnConfirm;

	// Progress Dialog
	private ProgressDialog pDialog;

	// JSON parser class
	JSONParser jsonParser = new JSONParser();

	private static final String WRITE_URL = "http://sharonyar.netau.net/WriteOpenRide/write_open_ride.php";
	//ids
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_MESSAGE = "message";

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.open_ride);

		initialize();

	}
	public void initialize(){

		name = (EditText)findViewById(R.id.editText1);
		date = (EditText)findViewById(R.id.editText2);
		hour = (EditText)findViewById(R.id.editText3);
		btnConfirm = (Button) findViewById(R.id.buttonConfirm);
		btnConfirm.setOnClickListener(this);

	}

	public void onClick(View v) {
		// TODO Auto-generated method stub

		new CreateUser().execute();

	}

	class CreateUser extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		boolean failure = false;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(OpenRide.this);
			pDialog.setMessage("יוצר אירוע רכיבה ...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub
			// Check for success tag
			int success;
			String nameString = name.getText().toString();
			String dateString = date.getText().toString();
			String hourString = hour.getText().toString();
			try {
				// Building Parameters
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("name", nameString));
				params.add(new BasicNameValuePair("date", dateString));
				params.add(new BasicNameValuePair("hour", hourString));

				//   Log.d("request!", "starting");

				//Posting user data to script 
				JSONObject json = jsonParser.makeHttpRequest(
						WRITE_URL, "POST", params);

				// full json response
				Log.d("Login attempt", json.toString());

				// json success element
				success = json.getInt(TAG_SUCCESS);
				if (success == 1) {
					Log.d("User Created!", json.toString());  
					Intent i = new Intent(OpenRide.this, MainScreenActivity.class);
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

		}
		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog once product deleted
			pDialog.dismiss();
			if (file_url != null){
				Toast.makeText(OpenRide.this, file_url, Toast.LENGTH_LONG).show();
			}
		}
	}
}


