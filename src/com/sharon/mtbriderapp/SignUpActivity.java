package com.sharon.mtbriderapp;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends Activity implements OnClickListener {

	EditText etEmail , etPass , etUser;
	Button btnSignUp;

	String email , password , username;

	private List<Login> user;
	
	private static String url_insert_new = "http://www.sharonyar.netau.net/mtb.jpg";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up_screen);

		initialize();

		// Edit Text


		// Save button
		//        btnsavenew = (Button) findViewById(R.id.btnsavenew);
		// button click event
		//       btnsavenew.setOnClickListener(this);

	}

	public void initialize(){

		WebServerConnection web  = new  WebServerConnection();
		user = web.getAllUsers();
		etEmail = (EditText) findViewById(R.id.etEmail);
		etPass = (EditText) findViewById(R.id.etPass);
		btnSignUp = (Button) findViewById(R.id.btnSignUp);
		btnSignUp.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		email = etEmail.getText().toString();
	//	username = etUser.getText().toString();
		password = etPass.getText().toString();
		


		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("email", email));
//		params.add(new BasicNameValuePair("username", username));
		params.add(new BasicNameValuePair("password", password));

		if (v.getId()==R.id.btnSignUp){
			//call the InsertNewIdiom thread
			//	new InsertNewIdiom().execute();
		//	if (success==1){
				Toast.makeText(getApplicationContext(), "New idiom saved...", Toast.LENGTH_LONG).show();
		//	}else{
				Toast.makeText(getApplicationContext(), "New idiom FAILED to saved...", Toast.LENGTH_LONG).show();
		//	}
		}
		
		
	}
}


/*import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View.OnClickListener;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNewIdiom extends Activity implements OnClickListener {

    // Progress Dialog
    private ProgressDialog pDialog;

    JSONParser jsonParser = new JSONParser();
    private EditText txtnewidiom;
    private EditText txtmeaning;
    private Button btnsavenew;
    private int success;//to determine JSON signal insert success/fail

    // url to insert new idiom (change accordingly)
    private static String url_insert_new = "http://172.16.16.183/idiomjson/insertnew.php";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_idiom);

        // Edit Text
        txtnewidiom = (EditText) findViewById(R.id.txtnewidiom);
        txtmeaning = (EditText) findViewById(R.id.txtmeaning);

        // Save button
        btnsavenew = (Button) findViewById(R.id.btnsavenew);
        // button click event
        btnsavenew.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btnsavenew){
            //call the InsertNewIdiom thread
            new InsertNewIdiom().execute();
            if (success==1){
                Toast.makeText(getApplicationContext(), "New idiom saved...", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(getApplicationContext(), "New idiom FAILED to saved...", Toast.LENGTH_LONG).show();
            }
        }

    }

 *//**
 * Background Async Task to Create new Idioms
 * *//*
    class InsertNewIdiom extends AsyncTask<String, String, String> {
        //capture values from EditText
        String entry = txtnewidiom.getText().toString();
        String meaning = txtmeaning.getText().toString();

  *//**
  * Before starting background thread Show Progress Dialog
  * *//*
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(AddNewIdiom.this);
            pDialog.setMessage("Saving the new IDIOM ("+entry+")...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

   *//**
   * Inserting the new idiom
   * *//*
        protected String doInBackground(String... args) {


            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("entry", entry));
            params.add(new BasicNameValuePair("meaning", meaning));

            // getting JSON Object
            // Note that create product url accepts GET method
            JSONObject json = jsonParser.makeHttpRequest(url_insert_new,
                    "GET", params);

            // check log cat from response
            Log.d("Insert New Idiom Response", json.toString());

            // check for success tag
            try {
                success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // successfully save new idiom
                } else {
                    // failed to add new idiom
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            //return null;
            return null;
        }

    *//**
    * After completing background task Dismiss the progress dialog
    * **//*
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            pDialog.dismiss();
        }

    }
}*/