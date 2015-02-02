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

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class WebServerConnection
{
	// Creating JSON Parser object
	JSONParser jParser = new JSONParser();

	// url to get users list
	private static String all_users = "http://sharonyar.netau.net/MTBRiderApp.php";
	
	private static final String WRITE_URL = "http://sharonyar.netau.net/write.php";
	
	// JSON Node names
	private static final String TAG_SUCCESS = "success";

	private static final String TAG_LOGIN = "login";

	private static final String NEW_EMAIL = "email";
	private static final String NEW_USER = "user";
	private static final String NEW_PASS = "pass";
	
	private SqliteDatabase sqlite;

	JSONArray signIn = null;
	JSONArray signUp = null;
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
	
	private Context context ;
	
	public WebServerConnection(Context c)
	{
		context = c;
		sqlite = new SqliteDatabase(context);
	}

	public void addObserver(Observer observer) {
		readyObservable.deleteObservers();
		readyObservable.addObserver(observer);
	}
	
	
	public List<Login> getAllUsers()
	{
		usersList = new ArrayList<Login>(); 

		try
		{
			usersList = new LoadAllUsers().execute().get();
			
			for(int i = 0 ; i < usersList.size() ; i++){
				Login l = usersList.get(i);
				sqlite.insertUsersToTable(l.getEmail(), l.getUser(), l.getPass());
				
			}
			

		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return usersList;
	}

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
						

						usersList.add(new Login(email, user, pass));

					}			
					
				} 
				else
				{
					usersList = sqlite.selectAllUsers();
				}
			} catch (JSONException e)
			{
				e.printStackTrace();
			}
			return usersList;
		}

	}
	
	public void getUserDetail(Login login){
		signUpUser = login;
		new CreateUser().execute();
	}

	
	class CreateUser extends AsyncTask<Void, String, Integer> {

		boolean failure = false;
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}
		
		@Override
		protected Integer doInBackground(Void... params) {
			
			String email = signUpUser.getEmail();
			String username = signUpUser.getUser();
			String password = signUpUser.getPass();
			
			try {
				// Building Parameters
				List<NameValuePair> loginParams = new ArrayList<NameValuePair>();
				loginParams.add(new BasicNameValuePair("email", email));
				loginParams.add(new BasicNameValuePair("user", username));
				loginParams.add(new BasicNameValuePair("pass", password));

				//Posting user data to script 
				// JSON parser class
				
				JSONParser jsonParser = new JSONParser();
				JSONObject json;
				try{
				json = jsonParser.makeHttpRequest(WRITE_URL, "POST", loginParams);
				}catch(Exception e){
					e.printStackTrace();
					return null;
				}
				
				// full json response
				//Log.d("Login attempt", json.toString());

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
		}
	}
}
