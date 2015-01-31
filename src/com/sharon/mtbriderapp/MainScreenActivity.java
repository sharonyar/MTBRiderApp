package com.sharon.mtbriderapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ListActivity;
import android.util.Log;
import android.view.Menu;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

public class MainScreenActivity extends ActionBarActivity implements OnClickListener {

	Button btnOpenRide;
	Button btnRecordRide;
	//	String openride;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_screen);

		btnOpenRide = (Button) findViewById(R.id.open_button);
		btnRecordRide = (Button) findViewById(R.id.record_button);
		btnOpenRide.setOnClickListener(this);
		btnRecordRide.setOnClickListener(this);


	

	/*	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}*/

	ListView lv= (ListView)findViewById(R.id.listview);

	// create the grid item mapping
	String[] from = new String[] {"event"};
	int[] to = new int[] { R.id.event };

	// prepare the list of all records
	List<HashMap<String, String>> fillMaps = new ArrayList<HashMap<String, String>>();
	for(int i = 0; i < 10; i++){
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("event", "event_" + i);
		fillMaps.add(map);
	}

	// fill in the grid_item layout
	SimpleAdapter adapter = new SimpleAdapter(this, fillMaps, R.layout.grid_item, from, to);
	lv.setAdapter(adapter);
}

@Override
public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.main_screen, menu);
	return true;
}

@Override
public boolean onOptionsItemSelected(MenuItem item) {
	// Handle action bar item clicks here. The action bar will
	// automatically handle clicks on the Home/Up button, so long
	// as you specify a parent activity in AndroidManifest.xml.
	int id = item.getItemId();
	if (id == R.id.action_settings) {
		return true;
	}
	return super.onOptionsItemSelected(item);
}




public void onClick(View v) {

	//openride = etEmail.getText().toString();
	Intent i = null;
	switch(v.getId()){
	case R.id.open_button:
		i = new Intent(this,OpenRide.class);
		break;
	case R.id.record_button:
		i = new Intent(this,OpenRide.class);
		break;
	}
	startActivity(i);

}
}
