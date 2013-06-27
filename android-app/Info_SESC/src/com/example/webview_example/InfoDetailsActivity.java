package com.example.webview_example;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.androidquery.AQuery;
import com.androidquery.auth.FacebookHandle;
import com.androidquery.callback.AjaxStatus;

public class InfoDetailsActivity extends Activity {
	AQuery a;
	Helper h;
	
    FacebookHandle face;  // Facebook Access 
    String APP_ID = "476084092477638"; // App-ID
    String permissions = "email, publish_stream"; // Facebook Permissions
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.info_details_activity);
		
		a = new AQuery(this);
		h = new Helper(this);
        
		face = new FacebookHandle(this, APP_ID, permissions);
		
		Intent detailsIntent= getIntent(); // gets the previously created intent
		String descriptionText = detailsIntent.getStringExtra("description"); // will return "FirstKeyValue"
		
		a.id(R.id.eventDetails).text(descriptionText);
		a.id(R.id.shareButton).clickable(true).clicked(this, "shareEvent");
	}
	
	public void shareEvent() {
		h.msgbox("share");
		
		Map<String, Object> params = new HashMap<String, Object>();
		String url = "https://graph.facebook.com/me/feed";

		params.put("message", "test test test");
		params.put("name", "Facebook SDK for Android");
	    params.put("caption", "Build great social apps and get more installs.");
	    params.put("description", "The Facebook SDK for Android makes it easier and faster to develop Facebook integrated Android apps.");
	    params.put("link", "https://developers.facebook.com/android");
	    params.put("picture", "https://raw.github.com/fbsamples/ios-3.x-howtos/master/Images/iossdk_logo.png");

		a.auth(face).ajax(url, params, JSONObject.class, this, "fbPostCallback");

	}

	public void fbPostCallback(String url, JSONObject json, AjaxStatus status) throws JSONException{
		//Toast.makeText(MainActivity.this, "retornoFace", Toast.LENGTH_SHORT).show();
		
		if(json != null){
			
			h.msgbox("postado");
			
		}
		else {
			h.msgbox("error");
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.info_details, menu);
		return true;
	}
	

}
