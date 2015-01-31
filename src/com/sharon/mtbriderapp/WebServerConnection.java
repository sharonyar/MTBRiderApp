package com.sharon.mtbriderapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutionException;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

<<<<<<< HEAD
=======
import android.content.SharedPreferences;
>>>>>>> origin/master
import android.os.AsyncTask;
import android.util.Log;

public class WebServerConnection
{
	// Creating JSON Parser object
	JSONParser jParser = new JSONParser();

	// url to get users list
	private static String all_users = "http://sharonyar.netau.net/MTBRiderApp.php";
<<<<<<< HEAD

	private static final String WRITE_URL = "http://sharonyar.netau.net/write.php";
	// JSON Node names
	private static final String TAG_SUCCESS = "success";

=======
	private static String write = "http://sharonyar.netau.net/write.php";
	// JSON Node names
	private static final String TAG_SUCCESS = "success";

	private static final String TAG_SIGNIN = "signIn";
	private static final String TAG_SIGNUP = "signUp";
>>>>>>> origin/master
	private static final String TAG_LOGIN = "login";

	private static final String NEW_EMAIL = "email";
	private static final String NEW_USER = "user";
	private static final String NEW_PASS = "pass";

<<<<<<< HEAD
=======
//	SharedPreferences prfs = getSharedPreferences("AUTHENTICATION_FILE_NAME", Context.MODE_PRIVATE);
//	String Astatus = prfs.getString("Authentication_Status", "");
	
	
	
	JSONArray signIn = null;
	JSONArray signUp = null;
>>>>>>> origin/master
	JSONArray login = null;

	public class ReadyObservable extends Observable {
		public void change(Boolean result) {
			setChanged();
			notifyObservers(result); 
		}
	}

	private ReadyObservable readyObservable = new ReadyObservable();

	private Login signUpUser;

	private List<Login> usersList;
	
//	SignUpActivity a = new SignUpActivity();
	
	public WebServerConnection()
	{
		usersList = new ArrayList<Login>(); 

		try
		{
			usersList = new LoadAllUsers().execute().get();

		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

<<<<<<< HEAD
	public void addObserver(Observer observer) {
		readyObservable.deleteObservers();
		readyObservable.addObserver(observer);
	}

=======
/*	private SharedPreferences getSharedPreferences(String string,
			int modePrivate) {
		// TODO Auto-generated method stub
		return null;
	}*/
>>>>>>> origin/master

	public List<Login> getAllUsers()
	{
		return usersList;
	}

<<<<<<< HEAD
=======
	public List<Login> update(List<Login> checkedUsersList)
	{
		new UpdateUser(checkedUsersList).execute();
		return checkedUsersList;
	}


>>>>>>> origin/master
	/**
	 * Background Async Task to Load all users by making HTTP Request
	 * */
	class LoadAllUsers extends AsyncTask<String, String, List<Login>>
	{

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute()
		{
			super.onPreExecute();

		}

		/**
		 * getting All users from url
		 * */
		protected List<Login> doInBackground(String... args)
		{
			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			// getting JSON string from URL
			JSONObject json = jParser.makeHttpRequest(all_users, "GET", params);
			//			JSONObject json1 = jParser.makeHttpRequest(write, "POST", params);

			// Check your log cat for JSON reponse
			Log.d("All SIGNIN: ", json.toString());

			try
			{
				// Checking for SUCCESS TAG
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1)
				{
					// users found
					// Getting Array of users
					login = json.getJSONArray(TAG_LOGIN);

					// looping through All Users
					for (int i = 0; i < login.length(); i++)
					{
						JSONObject c = login.getJSONObject(i);

						// Storing each json item in variable
						String email = c.getString(NEW_EMAIL);
						String user = c.getString(NEW_USER);
						String pass = c.getString(NEW_PASS);
<<<<<<< HEAD
=======
						
>>>>>>> origin/master

						usersList.add(new Login(email, user, pass));

					}
				} else
				{

				}
			} catch (JSONException e)
			{
				e.printStackTrace();
			}

			return usersList;
		}

	}
	
	public void getUserDetail(Login login){
	
		
		
	}
	
/*	public void GetSharedPrefs(){
		
		SharedPreferences settingSharedPref = ;
		
	}*/

	public void getUserDetail(Login login){
		signUpUser = login;
		new CreateUser().execute();
	}

	class UpdateUser extends AsyncTask<String, String, String>
	{

<<<<<<< HEAD
	class CreateUser extends AsyncTask<Void, String, Integer> {

		boolean failure = false;

=======
		private List<Login> checkedUsersList;

		public UpdateUser(List<Login> checkedUsersList)
		{
			super();
			this.checkedUsersList = checkedUsersList;

		}

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
>>>>>>> origin/master
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

<<<<<<< HEAD
		@Override
		protected Integer doInBackground(Void... params) {

			String email = signUpUser.getEmail();
			String username = signUpUser.getUser();
			String password = signUpUser.getPass();
=======
		/**
		 * getting popularity rate from url
		 * */
		protected String doInBackground(String... args)
		{

			//	for (int i = 0; i < checkedUsersList.size(); i++)
			//	{

			//	String newPopularity = Integer.toString(checkedUsersList.get(i).getPopularity() + 1);

			// Building Parameters
			/*List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("email" , "111111"));
			params.add(new BasicNameValuePair("user" , "11111"));
			params.add(new BasicNameValuePair("pass" , "1111"));
			*/
			//			params.add(new BasicNameValuePair(TAG_POPULARITY, newPopularity));

			// getting JSON string from URL
			JSONObject json = null;

			//	if(checkedUsersList.get(i) instanceof Login)
		//	json = jParser.makeHttpRequest(write, "POST", params);

>>>>>>> origin/master

			try {
				// Building Parameters
				List<NameValuePair> loginParams = new ArrayList<NameValuePair>();
				loginParams.add(new BasicNameValuePair("email", email));
				loginParams.add(new BasicNameValuePair("user", username));
				loginParams.add(new BasicNameValuePair("pass", password));

<<<<<<< HEAD
				//Posting user data to script 
				// JSON parser class
				JSONParser jsonParser = new JSONParser();
				JSONObject json = jsonParser.makeHttpRequest(WRITE_URL, "POST", loginParams);

				// full json response
				Log.d("Login attempt", json.toString());

				// json success element
				Integer success = json.getInt(TAG_SUCCESS);

				return success;

			} catch (JSONException e) { 
				e.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onPostExecute(Integer result) {
			Boolean flag = (result == 1 ? true : false);
			readyObservable.change(flag);
			super.onPostExecute(result);
=======
				Log.d("success", success + "");

				if (success == 1)
				{
					// Check your log cat for JSON reponse
					Log.d("JSON Parser", json.toString());


				} else
				{
					Log.e("error: ", "error");
				}
			} catch (JSONException e)
			{
				e.printStackTrace();
			}

			//		}

			return null;
>>>>>>> origin/master
		}
	}
}