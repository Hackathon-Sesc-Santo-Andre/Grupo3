package com.example.webview_example;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.os.Build;

public class InfoActivity extends Activity {

	AQuery a;
	List<String> detailList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_info);
		// Show the Up button in the action bar.
		setupActionBar();
		
		a = new AQuery(this);
		
		String url = "http://192.168.0.100:8080/rest.txt";
		//String url = "http://192.168.0.213:8080/infosescsa/services/eventos";
		a.ajax(url, JSONArray.class, this, "jsonCallback");
		//String url = "http://www.google.de/uds/GnewsSearch?q=SESC+Santo+Andre&v=1.0&hl=de";             
		//a.ajax(url, JSONObject.class, this, "jsonCallback");
        
        /*
	    try
	    {
	    	
	    
	      // Get JSON from Server
	    	HttpClient httpClient = new DefaultHttpClient();
	    	HttpContext localContext = new BasicHttpContext();
	    	HttpGet httpGet = new HttpGet("http://www.cheesejedi.com/rest_services/get_big_cheese.php?puzzle=1");
	    	String text = null;
			try {
				HttpResponse response = httpClient.execute(httpGet,
						localContext);
				HttpEntity entity = response.getEntity();

				InputStream in = entity.getContent();
				StringBuffer out = new StringBuffer();
				int n = 1;
				while (n > 0) {
					byte[] b = new byte[4096];
					n = in.read(b);
					if (n > 0)
						out.append(new String(b, 0, n));
				}
				text = out.toString();
			} catch (Exception e) {
				msgbox(e.getLocalizedMessage());
			}
	 
	      */ 
		
		  //JSONArray jsonArray = new JSONArray(text);
	      
	    	/*
		  String jsonInput = "[\"one\",\"two\",\"three\",\"four\",\"five\",\"six\",\"seven\",\"eight\",\"nine\",\"ten\"]";
	      JSONArray jsonArray = new JSONArray(jsonInput);
	           
	      
	      int length = jsonArray.length();
	      List<String> listContents = new ArrayList<String>(length);
	      for (int i = 0; i < length; i++)
	      {
	        listContents.add(jsonArray.getString(i));
	      }

	      ListView myListView = (ListView) findViewById(R.id.infoList);
	      myListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listContents));
	    }
	    catch (Exception e)
	    {
	      // this is just an example
	    	msgbox(e.getLocalizedMessage());
	    }
		*/
	}
	
	public void jsonCallback(String url, JSONArray jsonArray, AjaxStatus status) throws JSONException{
    //public void jsonCallback(String url, JSONObject jsonRoot, AjaxStatus status) throws JSONException{  // Google
	//if(jsonRoot != null) {	 // Google
		//JSONArray jsonArray = jsonRoot.getJSONObject("responseData").getJSONArray("results");   // Google
 
		msgbox("jsonCallback");
		if(jsonArray != null){               
                //msgbox(json.toString());  
    	    	//JSONArray jsonArray;
    	    	
				    
					//jsonArray = json.getJSONArray("responseData");   // Google?
        			String description = "";
					int length = jsonArray.length();
					List<String> listContents = new ArrayList<String>(length);
					
					// define global variable 
					detailList = new ArrayList<String>(length);
					
					for (int i = 0; i < length; i++) //length; i++)
					{
						//listContents.add(jsonArray.getJSONObject(i).getString("unescapedUrl")); //Google			
						//detailList.add(jsonArray.getJSONObject(i).getString("titleNoFormatting")); //Google
						
						description = jsonArray.getJSONObject(i).getString("descricao");
						detailList.add(description);
						//listContents.add(description.split(":",2)[0]);
						listContents.add(description);
						//msgbox(description);
					}
					//msgbox("listview-creation");
					ListView myListView = (ListView) findViewById(R.id.infoList);
	        	    myListView.setAdapter(new CustomArrayAdapter(this, listContents));
	        	    
	        	    myListView.setOnItemClickListener(new OnItemClickListener() {
	        	    	 public void onItemClick(AdapterView a, View v, int position, long id) {
	        	             // show information details
	        	             String s =(String) ((TextView) v.findViewById(R.id.label)).getText();
	        	             Toast.makeText(InfoActivity.this, ""+detailList.get(position), Toast.LENGTH_LONG).show();
	        	                 }
	        	    });
	        	    
	        	    
					/*
	        	    a.id(R.id.infoList).adapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listContents));
	        	    a.id(R.id.infoList).getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {

	                    public void onItemClick(AdapterView<?> arg0, View arg1, int position,
	                            long arg3) {
	                        final String kategori = (String) ((TextView)arg1).getText();
	                        Toast.makeText(WebServiceActivity.this, kategori,
	                                Toast.LENGTH_LONG).show();
	                    }
	                });*/
	        	    
				     	    
        	
        }else{          
                //ajax error
        		//msgbox("no!");  
        	  String ajaxMessage = status.getMessage();
        	  String ajaxError = status.getError();
        	  msgbox("AJAX ERROR: "+ajaxMessage+" "+ajaxError);

        }
        
	}
        
	// } Google
	
	private void msgbox(String message) {
    	Toast.makeText(InfoActivity.this, message, Toast.LENGTH_SHORT).show();
    }

	/* @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.rest, menu);
		return true;
	} */
	
	private void infoDetails() {
		
		msgbox("Info Details");
	}


	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
