package com.example.webview_example;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import android.R.integer;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;

public class InfoActivity extends Activity {

	AQuery a;
	Helper h;
	List<String> detailList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.info_activity);
		// Show the Up button in the action bar.
		setupActionBar();
		
		a = new AQuery(this);
		h = new Helper(this);
		
		//a = new AQuery(this);
		//a.id(R.id.infoLayoutView).animate(R.anim.up); 
		
		String url = "http://192.168.0.100:8080/rest.txt";
		//String url = "http://192.168.0.213:8080/infosescsa/services/eventos";
		a.ajax(url, JSONArray.class, this, "jsonCallback");
		//String url = "http://www.google.de/uds/GnewsSearch?q=SESC+Santo+Andre&v=1.0&hl=de";             
		//a.ajax(url, JSONObject.class, this, "jsonCallback");
    	      
	}
	
	public void jsonCallback(String url, JSONArray jsonArray, AjaxStatus status) throws JSONException{
    //public void jsonCallback(String url, JSONObject jsonRoot, AjaxStatus status) throws JSONException{  // Google
	//if(jsonRoot != null) {	 // Google
		//JSONArray jsonArray = jsonRoot.getJSONObject("responseData").getJSONArray("results");   // Google
 
		h.msgbox("jsonCallback");
		if(jsonArray != null){               
                //msgbox(json.toString());  
    	    	//JSONArray jsonArray;
    	    	
				    
					//jsonArray = json.getJSONArray("responseData");   // Google?
        			String description = "";
        			int category_id = 0;
        			
					int length = jsonArray.length();
					List<String> listContents = new ArrayList<String>(length);
					List<Integer> listCategories = new ArrayList<Integer>(length);
					
					// define global variable 
					detailList = new ArrayList<String>(length);
					
					for (int i = 0; i < length; i++) //length; i++)
					{
						//listContents.add(jsonArray.getJSONObject(i).getString("unescapedUrl")); //Google			
						//detailList.add(jsonArray.getJSONObject(i).getString("titleNoFormatting")); //Google
						
						if(jsonArray.getJSONObject(i).has("descricao")) {
						    description = jsonArray.getJSONObject(i).getString("descricao");
						} else {
							description = "";
						}	
						if(jsonArray.getJSONObject(i).has("categoria_id")) {
							category_id = jsonArray.getJSONObject(i).getInt("categoria_id");
						} else { 
							category_id = 0;
						}
						
						// Add content to list
						detailList.add(description);
						//listContents.add(description.split(":",2)[0]);
						listContents.add(description);
						listCategories.add(category_id);
					}

									
					a.id(R.id.infoList).adapter(new InfoArrayAdapter(this, listContents, listCategories));
	        	    
	        	   
					/*a.id(R.id.infoList).getListView().setOnItemClickListener(new OnItemClickListener() {
	        	    	 public void onItemClick(AdapterView a, View v, int position, long id) {
	        	             // Show information details
	        	             String s =(String) ((TextView) v.findViewById(R.id.label)).getText();
	        	             Toast.makeText(InfoActivity.this, ""+detailList.get(position), Toast.LENGTH_LONG).show();
	        	                 }
	        	    });*/ 
	        	  
	        	    
	        	    
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
        	  h.msgbox("AJAX ERROR: "+ajaxMessage+" "+ajaxError);

        }
        
	}  
	// } Google
	
	@Override
	protected void onPause() {
		super.onPause();
		finish();
		overridePendingTransition (R.anim.up, R.anim.down);
	}

	/* @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.rest, menu);
		return true;
	} */
	

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

	private class InfoArrayAdapter extends ArrayAdapter<String> {
		private final Context context;
		private final List<String> values;
		private final List<Integer> categories;
		private AQuery a;
	 
		public InfoArrayAdapter(Context context, List<String> listContents, List<Integer> listCategories) {
			super(context, R.layout.info_row, listContents);
			this.context = context;
			this.values = listContents;
			this.categories = listCategories;
		}
	 
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			// Get Listrow View-Layout
			LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View rowView = inflater.inflate(R.layout.info_row, parent, false);
			
	 		a = new AQuery(rowView);
	 		
	 		a.id(R.id.label).text(Html.fromHtml(values.get(position)));
	 		/* TextView textView = (TextView) rowView.findViewById(R.id.label);
			//textView.setText(values.get(position));
			textView.setText(Html.fromHtml(values.get(position))); */
	 		
	 		//ImageView imageView = (ImageView) rowView.findViewById(R.id.logo);

			
			// Change icon based on category
			int category = categories.get(position);
	        System.out.println("category "+category);
			//a.id(imageView).image("http://"+this.getHost(s)+"/favicon.ico", 
			//		false, false, 0, 0, null, AQuery.FADE_IN);   // Google News
			
	        a.id(R.id.logo).image(AgendaSESC.getCategoryImageRessource(category));	


			return rowView;
		}
		
		private String getHost(String url){
		    if(url == null || url.length() == 0)
		        return "";

		    int doubleslash = url.indexOf("//");
		    if(doubleslash == -1)
		        doubleslash = 0;
		    else
		        doubleslash += 2;

		    int end = url.indexOf('/', doubleslash);
		    end = end >= 0 ? end : url.length();

		    return url.substring(doubleslash, end);
		}

		
	}	

}
