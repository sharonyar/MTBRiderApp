package com.sharon.mtbriderapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.app.Activity;

public class MainScreenActivity extends Activity {

	Button btnOpenRide;
	Button btnRecordRide;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_screen);

		btnOpenRide = (Button) findViewById(R.id.open_button);

		btnRecordRide = (Button) findViewById(R.id.record_button);

		btnOpenRide.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MainScreenActivity.this,OpenRide.class);
				startActivity(i);	
			}
		});

		btnRecordRide.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				/*	Intent i = new Intent(MainScreenActivity.this,RecordRide.class);
				startActivity(i);*/
				Toast.makeText(getBaseContext(), "Recording", Toast.LENGTH_LONG).show();	
			}
		});

		ListView events = (ListView)findViewById(R.id.listviewEvents);

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
		
		SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), fillMaps, R.layout.grid_item, from, to);
		events.setAdapter(adapter);
		
		
		
		
		
		
		ListView messages = (ListView)findViewById(R.id.listviewMessages);

		// create the grid item mapping
		String[] fromMessages = new String[] {"message"};
		int[] toMessages = new int[] { R.id.message };

		// prepare the list of all records
		List<HashMap<String, String>> fillMessagesMap = new ArrayList<HashMap<String, String>>();
		for(int i = 0; i < 10; i++){
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("event", "message_" + i);
			fillMessagesMap.add(map);
		}

		// fill in the grid_item layout
		SimpleAdapter adapterMessages = new SimpleAdapter(getBaseContext(), fillMessagesMap, R.layout.message_item, fromMessages, toMessages);
		messages.setAdapter(adapterMessages);
	}

}
