package com.sharon.mtbriderapp;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

public class WebServerConnection
{
	// Creating JSON Parser object
	JSONParser jParser = new JSONParser();

	// url to get users list
	private static String all_users = "http://sharonyar.netau.net/MTBRiderApp.php";

	// JSON Node names
	private static final String TAG_SUCCESS = "success";

	private static final String TAG_SIGNIN = "signIn";
	private static final String TAG_SIGNUP = "signUp";

	private static final String NEW_EMAIL = "email";
	private static final String NEW_USER = "user";
	private static final String NEW_PASS = "pass";

	JSONArray signIn = null;
	JSONArray signUp = null;


	private List<Login> usersList;

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

	public List<Login> getAllUsers()
	{
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
					signIn = json.getJSONArray(TAG_SIGNIN);

					// looping through All Users
					for (int i = 0; i < signIn.length(); i++)
					{
						JSONObject c = signIn.getJSONObject(i);

						// Storing each json item in variable
						String email = c.getString(NEW_EMAIL);
						String pass = c.getString(NEW_PASS);
						String user = c.getString(NEW_USER);

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




	/*	*//**
	 * Background Async Task to Load all fixtures by making HTTP Request
	 * *//*
	class LoadAllFixtures extends AsyncTask<String, String, List<Fixture>>
	{

	  *//**
	  * Before starting background thread Show Progress Dialog
	  * *//*
		@Override
		protected void onPreExecute()
		{
			super.onPreExecute();

		}

	   *//**
	   * getting All fixtures from url
	   * *//*
		protected List<Fixture> doInBackground(String... args)
		{
			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			// getting JSON string from URL
			JSONObject json = jParser.makeHttpRequest(url_all_fixtures, "GET", params);

			// Check your log cat for JSON reponse
			Log.d("All Fixtures: ", json.toString());

			try
			{
				// Checking for SUCCESS TAG
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1)
				{
					// fixtures found
					// Getting Array of fixtures
					fixtures = json.getJSONArray(TAG_FIXTURES);

					// looping through All Fixtures
					for (int i = 0; i < fixtures.length(); i++)
					{
						JSONObject c = fixtures.getJSONObject(i);

						// Storing each json item in variable
						int num = c.getInt(FIXTURE_NUM);
						String date = c.getString(FIXTURE_DATE);
						String home = c.getString(FIXTURE_HOME);
						String away = c.getString(FIXTURE_AWAY);
						int home_scored = c.getInt(FIXTURE_HOMESCORED);
						int away_scored = c.getInt(FIXTURE_AWAYSCORED);

						fixturesList.add(new Fixture(num, date, home, away, home_scored, away_scored));

					}
				} else
				{

				}
			} catch (JSONException e)
			{
				e.printStackTrace();
			}

			return fixturesList;
		}*/

	/**
	 * After completing background task Dismiss the progress dialog
	 * **/
	/*		protected void onPostExecute(List<New> _newsList)
		{

		}*/

//	class InsertNewUser extends AsyncTask<String, String, String> {
		//capture values from EditText
	//	String entry = txtnewidiom.getText().toString();
	//	String meaning = txtmeaning.getText().toString();

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
//		@Override
//		protected void onPreExecute() {
//			super.onPreExecute();
	//		pDialog = new ProgressDialog(SignUpActivity.this);
	//		pDialog.setMessage("Saving the new IDIOM ("+entry+")...");
	//		pDialog.setIndeterminate(false);
	//		pDialog.setCancelable(true);
	//		pDialog.show();
		}

		/**
		 * Inserting the new idiom
		 * */
//		protected String doInBackground(String... args) {


			// Building Parameters
	//		List<NameValuePair> params = new ArrayList<NameValuePair>();
	//		params.add(new BasicNameValuePair("entry", entry));
	//		params.add(new BasicNameValuePair("meaning", meaning));

			// getting JSON Object
			// Note that create product url accepts GET method
	//		JSONObject json = jsonParser.makeHttpRequest(url_insert_new,
	//				"GET", params);

			// check log cat from response
	//		Log.d("Insert New Idiom Response", json.toString());

			// check for success tag
	//		try {
	//			success = json.getInt(TAG_SUCCESS);

//				if (success == 1) {
					// successfully save new idiom
	//			} else {
					// failed to add new idiom
	//			}
	//		} catch (JSONException e) {
	//			e.printStackTrace();
	//		}

			//return null;
	/*		return null;
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








//	/**
//	 * Background Async Task to Load popularity rate by making HTTP Request
//	 * */
//	class UpdatePopularity extends AsyncTask<String, String, String>
//	{
//
//		private List<Place> checkedPlacesList;
//
//		public UpdatePopularity(List<Place> checkedPlacesList)
//		{
//			super();
//			this.checkedPlacesList = checkedPlacesList;
//
//		}
//
//		/**
//		 * Before starting background thread Show Progress Dialog
//		 * */
//		@Override
//		protected void onPreExecute()
//		{
//			super.onPreExecute();
//
//		}
//
//		/**
//		 * getting popularity rate from url
//		 * */
//		protected String doInBackground(String... args)
//		{
//
//			for (int i = 0; i < checkedPlacesList.size(); i++)
//			{
//				
//				String newPopularity = Integer.toString(checkedPlacesList.get(i).getPopularity() + 1);
//
//				// Building Parameters
//				List<NameValuePair> params = new ArrayList<NameValuePair>();
//				params.add(new BasicNameValuePair(TAG_TITLE, checkedPlacesList.get(i).getTitle()));
//				params.add(new BasicNameValuePair(TAG_POPULARITY, newPopularity));
//				
//				// getting JSON string from URL
//				JSONObject json = null;
//				
//				if(checkedPlacesList.get(i) instanceof Site)
//					json = jParser.makeHttpRequest(url_update_site_popularity, "POST", params);
//				else if(checkedPlacesList.get(i) instanceof Event)
//					json = jParser.makeHttpRequest(url_update_event_popularity, "POST", params);
//				else if(checkedPlacesList.get(i) instanceof Store)
//					json = jParser.makeHttpRequest(url_update_store_popularity, "POST", params);
//				else if(checkedPlacesList.get(i) instanceof Restaurant)
//					json = jParser.makeHttpRequest(url_update_restaurant_popularity, "POST", params);
//
//				try
//				{
//					// Checking for SUCCESS TAG
//					int success = json.getInt(TAG_SUCCESS);
//
//					Log.d("success", success + "");
//
//					if (success == 1)
//					{
//						// Check your log cat for JSON reponse
//						Log.d("JSON Parser", json.toString());
//
//					} else
//					{
//						Log.e("error: ", "error");
//					}
//				} catch (JSONException e)
//				{
//					e.printStackTrace();
//				}
//
//			}
//
//			return null;
//		}
//
//		/**
//		 * After completing background task Dismiss the progress dialog
//		 * **/
//		protected void onPostExecute(List<Site> _sitesList)
//		{
//
//		}
//
//	}



