package com.example.webview_example;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.auth.FacebookHandle;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

public class InfoDetailsActivity extends Activity {
	
	AQuery a;
	Helper h;
	
	FacebookHandle handle;
	static String PERMISSIONS = "read_stream,read_friendlists,manage_friendlists,manage_notifications,publish_stream,publish_checkins,offline_access,user_photos,user_likes,user_groups,friends_photos";
	String APP_ID = "602936446384967";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_details_activity);
        
		a = new AQuery(this);
		h = new Helper(this);
        
     	Intent detailsIntent= getIntent(); // gets the previously created intent
		String descriptionText = detailsIntent.getStringExtra("description"); // will return "FirstKeyValue"
		
		//a.id(R.id.eventDetails).text(descriptionText);
        a.id(R.id.shareButton).clickable(true).clicked(this, "autenticar");
    }
    
	public void autenticar(){
        handle = new FacebookHandle(this, APP_ID, PERMISSIONS);
        String url = "https://graph.facebook.com/me/feed";
        Toast.makeText(this, handle.getToken(), Toast.LENGTH_LONG).show();;
        url = url + "?access_token=" + handle.getToken();
        a.auth(handle).ajax(url, JSONObject.class, this, "post");
	}
	
    public void post(){
        String url = "https://graph.facebook.com/me/feed";
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("access_token", handle.getToken());
        params.put("message", "Estou participando do Hackathon Sesc Santo AndrŽ ! #changebrazil (Postando da minha app Android)");
        
        a.ajax(url, params, JSONObject.class, new AjaxCallback<JSONObject>() {

            @Override
            public void callback(String url, JSONObject json, AjaxStatus status) {
                show(json);
            }
        });
    }
    

    
    public void show(JSONObject json){
    	Toast.makeText(this, json==null?"Erro. Json null":json.toString(), Toast.LENGTH_LONG).show();
    }
 
    
    
	/*AQuery a;
	Helper h;
	
    FacebookHandle face;  // Facebook Access 
    String APP_ID = "602936446384967"; //"476084092477638"; // App-ID
	static String PERMISSIONS = "read_stream,read_friendlists,manage_friendlists,manage_notifications,publish_stream,publish_checkins,offline_access,user_photos,user_likes,user_groups,friends_photos";

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.info_details_activity);
		
		a = new AQuery(this);
		h = new Helper(this);
        
		face = new FacebookHandle(this, APP_ID, PERMISSIONS);
		
		Intent detailsIntent= getIntent(); // gets the previously created intent
		String descriptionText = detailsIntent.getStringExtra("description"); // will return "FirstKeyValue"
		
		a.id(R.id.eventDetails).text(descriptionText);
		a.id(R.id.shareButton).clickable(true).clicked(this, "autenticar");
	}
	
	@SuppressLint("NewApi")
	public void shareEvent() {
		h.msgbox("share");
		
		Map<String, Object> params = new HashMap<String, Object>();
		String url = "https://graph.facebook.com/me/feed";

		/*params.put("message", "test test test");
		params.put("link", "www.sescsp.org.br/sesc/busca/index.cfm?unidadesdirector=60");
		//Imagem na dialog//
		params.put("picture", "https://si0.twimg.com/profile_images/2611024267/spx1ififwtlhrv77jmou.jpeg");
		//Título//
		params.put("name", "SESC Santo André");	
		//Subtítulo//
		params.put("caption", "SESC Santo André, venha você também!!!"); */
		
		//Posta no mural do usuário//
		/*params.put("name", "Facebook SDK for Android");
	    params.put("caption", "Build great social apps and get more installs.");
	    params.put("description", "The Facebook SDK for Android makes it easier and faster to develop Facebook integrated Android apps.");
	    params.put("link", "https://developers.facebook.com/android");
	    params.put("picture", "https://raw.github.com/fbsamples/ios-3.x-howtos/master/Images/iossdk_logo.png"); * /

    
		a.auth(face).ajax(url, JSONObject.class, this, "autenticar");

	}

	
	public void autenticar() {
        face = new FacebookHandle(this, APP_ID, PERMISSIONS);
        String url = "https://graph.facebook.com/me/feed";
        a.auth(face).ajax(url, JSONObject.class, this, "post");
        h.msgbox("autenticar");
}

	public void post(String origurl, JSONObject json, AjaxStatus status) throws JSONException{
        String url = "https://graph.facebook.com/me/feed";
        Toast.makeText(this, json==null?"autent: Json null":json.toString(), Toast.LENGTH_LONG).show();
        h.msgbox(status.getMessage());
        
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("access_token", face.getToken());
        params.put("message", "Estou participando do Hackathon Sesc Santo AndrŽ ! #changebrazil (Postando da minha app Android)");
        
        a.ajax(url, params, JSONObject.class, new AjaxCallback<JSONObject>() {

            @Override
            public void callback(String url, JSONObject json, AjaxStatus status) {
                show(json);
            }
        });
        h.msgbox("post");
    }
    

    
    public void show(JSONObject json){
     h.msgbox(json==null?"Erro. Json null":json.toString());
     h.msgbox("finish");
     
    }
    
	/*public void fbPostCallback(String url, JSONObject json, AjaxStatus status) throws JSONException{
		//Toast.makeText(MainActivity.this, "retornoFace", Toast.LENGTH_SHORT).show();
		
		if(json != null){
			
			h.msgbox("postado");
			
		}
		else {
      	  String ajaxMessage = status.getMessage();
      	  String ajaxError = status.getError();
      	  h.msgbox("AJAX ERROR: "+ajaxMessage+" "+ajaxError);
		}
	}* /
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.info_details, menu);
		return true;
	}  */
	

}
