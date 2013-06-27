package com.example.webview_example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import com.androidquery.AQuery;
import com.androidquery.auth.FacebookHandle;

public class InfoDetailsActivity extends Activity {
	AQuery a;
	
    FacebookHandle face;  // Facebook Access 
    String APP_ID = "476084092477638"; // App-ID
    String permissoes = "read_stream, user_photos,friends_photos"; // Facebook Permissions
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.info_details_activity);
		
		a = new AQuery(this);
		
		Intent detailsIntent= getIntent(); // gets the previously created intent
		String descriptionText = detailsIntent.getStringExtra("description"); // will return "FirstKeyValue"
		
		a.id(R.id.eventDetails).text(descriptionText);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.info_details, menu);
		return true;
	}

}
