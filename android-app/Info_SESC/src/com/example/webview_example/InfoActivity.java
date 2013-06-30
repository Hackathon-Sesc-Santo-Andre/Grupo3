package com.example.webview_example;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import android.R.integer;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
	private static int MAX_DESC_LEN = 30;
	
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
		
		//String url = "http://192.168.0.100:8080/rest.txt";
		//String url = "http://192.168.43.172/rest.txt";
		//String url = "https://raw.github.com/Hackathon-Sesc-Santo-Andre/Grupo3/master/android-app/rest_saulo.txt";
		String url = "http://192.168.43.5:8080/infosescsa/services/eventos/";
		a.ajax(url, JSONArray.class, this, "jsonCallback");
		//String url = "http://www.google.de/uds/GnewsSearch?q=SESC+Santo+Andre&v=1.0&hl=de";             
		//a.ajax(url, JSONObject.class, this, "jsonCallback");
    	      
	}
	
	public void jsonCallback(String url, JSONArray jsonArray, AjaxStatus status) throws JSONException{
    //public void jsonCallback(String url, JSONObject jsonRoot, AjaxStatus status) throws JSONException{  // Google
	//if(jsonRoot != null) {	 // Google
		//JSONArray jsonArray = jsonRoot.getJSONObject("responseData").getJSONArray("results");   // Google
 
		//h.msgbox("jsonCallback");
		if(jsonArray != null){               
                //msgbox(json.toString());  
    	    	//JSONArray jsonArray;
    	    	
					//jsonArray = json.getJSONArray("responseData");   // Google?
        			String description = "";
        			String title = "";
        			int category_id = 0;
        			
					int length = jsonArray.length();
					List<String> listTitles = new ArrayList<String>(length);
					List<String> listContents = new ArrayList<String>(length);
					List<Integer> listCategories = new ArrayList<Integer>(length);
					
					// define global variable 
					detailList = new ArrayList<String>(length);
					
					for (int i = 0; i < length; i++) //length; i++)
					{
						//listContents.add(jsonArray.getJSONObject(i).getString("unescapedUrl")); //Google			
						//detailList.add(jsonArray.getJSONObject(i).getString("titleNoFormatting")); //Google
						
						if(jsonArray.getJSONObject(i).has("titulo")) {
						    title = jsonArray.getJSONObject(i).getString("titulo") + "<br />" + jsonArray.getJSONObject(i).getString("data").replace("T"," às ");
						    //h.msgbox(title);
						}

						if(jsonArray.getJSONObject(i).has("descricao")) {
							description = jsonArray.getJSONObject(i).getString("descricao");
						} 
						
						
						if(jsonArray.getJSONObject(i).has("categoria")) {
							category_id = jsonArray.getJSONObject(i).getInt("categoria");
						} else { 
							category_id = 0;
						}
						
						// Add content to list
						detailList.add(description);
						//listContents.add(description.split(":",2)[0]);
						listTitles.add(title);
						listContents.add(description);
						listCategories.add(category_id);
					}

					a.id(R.id.infoList).clickable(true).itemClicked(this, "infoItemClick");
					a.adapter(new InfoArrayAdapter(this, listTitles, listContents, listCategories));
	        	    
	        	   
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
	
	
	public void infoItemClick(AdapterView parent, View v, int pos, long id) {

		AQuery ac = new AQuery(v);
		//h.msgbox(ac.id(R.id.label).getText().toString());
		Intent detailsIntent = new Intent(InfoActivity.this, InfoDetailsActivity.class);
		detailsIntent.putExtra("title", ac.id(R.id.infoTitle).getText().toString());
		detailsIntent.putExtra("description", ac.id(R.id.infoDesc).getText().toString());
		//detailsIntent.putExtra("description", ac.id(R.id.label).getText().toString());
		InfoActivity.this.startActivity(detailsIntent);
}
	
	// Animation effect when returning to other activity
	/*@Override
	protected void onPause() {
		super.onPause();
		finish();
		overridePendingTransition (R.anim.up, R.anim.down);
	} */

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
		private final List<String> titles;		
		private final List<String> values;
		private final List<Integer> categories;
		private AQuery a;
	 
		public InfoArrayAdapter(Context context, List<String> listTitles, List<String> listContents, List<Integer> listCategories) {
			super(context, R.layout.info_row, listContents);
			this.context = context;
			this.values = listContents;
			this.titles = listTitles;
			this.categories = listCategories;
		}
	 
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			// Get Listrow View-Layout
			LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View rowView = inflater.inflate(R.layout.info_row, parent, false);
			
	 		a = new AQuery(rowView);
	 		
	 		a.id(R.id.infoTitle).text(Html.fromHtml(titles.get(position)));
	 		a.id(R.id.infoDesc).text(Html.fromHtml(values.get(position)));
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

	@Override
	public void onContentChanged() {
	    super.onContentChanged();

	    View empty = findViewById(R.id.empty);
	    ListView list = (ListView) findViewById(R.id.infoList);
	    list.setEmptyView(empty);
	}
}
