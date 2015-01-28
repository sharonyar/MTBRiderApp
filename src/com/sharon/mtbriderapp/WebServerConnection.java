package com.sharon.mtbriderapp;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

public class WebServerConnection
{
	// Creating JSON Parser object
	JSONParser jParser = new JSONParser();

	// url to get users list
	private static String all_users = "http://sharonyar.netau.net/MTBRiderApp.php";
	private static String write = "http://sharonyar.netau.net/write.php";
	// JSON Node names
	private static final String TAG_SUCCESS = "success";

	private static final String TAG_SIGNIN = "signIn";
	private static final String TAG_SIGNUP = "signUp";
	private static final String TAG_LOGIN = "login";

	private static final String NEW_EMAIL = "email";
	private static final String NEW_USER = "user";
	private static final String NEW_PASS = "pass";

//	SharedPreferences prfs = getSharedPreferences("AUTHENTICATION_FILE_NAME", Context.MODE_PRIVATE);
//	String Astatus = prfs.getString("Authentication_Status", "");
	
	
	
	JSONArray signIn = null;
	JSONArray signUp = null;
	JSONArray login = null;


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

/*	private SharedPreferences getSharedPreferences(String string,
			int modePrivate) {
		// TODO Auto-generated method stub
		return null;
	}*/

	public List<Login> getAllUsers()
	{
		return usersList;
	}

	public List<Login> update(List<Login> checkedUsersList)
	{
		new UpdateUser(checkedUsersList).execute();
		return checkedUsersList;
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
						

						usersList.add(new Login(email, user, pass));

					}
				} else
				{

				}
			} catch (JSONException e)
			{
				e.printStackTrace();
			}

			//			sitesList.get(0).setImage(R.drawable.cardo);
			//			sitesList.get(1).setImage(R.drawable.hurva_synagogue);
			//			sitesList.get(2).setImage(R.drawable.burnt_house);
			//			sitesList.get(3).setImage(R.drawable.western_wall);
			//			sitesList.get(4).setImage(R.drawable.alone_on_the_walls);
			//			sitesList.get(5).setImage(R.drawable.city_of_david);
			//			sitesList.get(6).setImage(R.drawable.herodian_quarter);
			//			sitesList.get(7).setImage(R.drawable.old_yishuv_court_museum);


			return usersList;
		}

	}
	
	public void getUserDetail(Login login){
	
		
		
	}
	
/*	public void GetSharedPrefs(){
		
		SharedPreferences settingSharedPref = ;
		
	}*/


	class UpdateUser extends AsyncTask<String, String, String>
	{

		private List<Login> checkedUsersList;

		public UpdateUser(List<Login> checkedUsersList)
		{
			super();
			this.checkedUsersList = checkedUsersList;

		}

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute()
		{
			super.onPreExecute();

		}

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


			try
			{
				// Checking for SUCCESS TAG
				int success = json.getInt(TAG_SUCCESS);

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
		}
	}
}