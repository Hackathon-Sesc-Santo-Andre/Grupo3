package com.example.webview_example;

import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;

import com.androidquery.AQuery;

public class OptionsActivity extends Activity {
    private AQuery a;
    private AgendaSESC agendaSESC;
    private Helper h;
    
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	
	    setContentView(R.layout.agenda_options_activity);
	    
	    a = new AQuery(this);
	    //h = new Helper(this);
	    
	    agendaSESC = new AgendaSESC();
	    	    
	    // Find the ListView resource.     
	    //myListView = (ListView) findViewById( R.id.listView1 );
	    // Set the ArrayAdapter as the ListView's adapter.  
	    //myListView.setAdapter( new ArrayAdapter<String>(this, R.layout.agenda_areaselect_row, areas) );        
	    
	    // Use custom adapter in order to fill each list item
	    a.id(R.id.listView1).getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
	    //a.itemSelected(this, "selecttest");
	    //a.adapter(new OptionsArrayAdapter(this, agendaSESC));
	    a.adapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, AgendaSESC.categoryNames));
		
	    
	    //a.clickable(true).itemClicked(this, "clicktest");
	    ListView list = (ListView) findViewById(R.id.listView1);
	    //list.setItemsCanFocus(true);
	    
	    /*list.setOnItemClickListener(new OnItemClickListener() {
	       @Override
	       public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
	    	   //ListView list = (ListView)adapter.findViewById(R.id.listView1);
	    	   //listItem = list.getItemAtPosition(position);
	           //h.msgbox("test"+listItem.toString());
	          //((TextView) view.findViewById(R.id.label)).setText("test");
	    	  CheckBox box = (CheckBox) ((View)adapter.getChildAt(position)).findViewById(R.id.checkbox);
	    	  System.out.print("Status: "+box.isChecked()+" ");
	    	  box.setChecked(false);
	    	  System.out.print("Status: "+box.isChecked()+" ");
	    	  adapter.getChildAt(position).setBackgroundColor(Color.RED);
	    	  
	          h.msgbox("teeeeeeeest");
	       } 
	    });*/
	    
	    
	    
	}
	
	/*public void selecttest(AdapterView parent, View v, int pos, long id) {
		h.msgbox("selecttest");
	}*/
	
	/*public void clicktest(AdapterView parent, View v, int pos, long id) {
		//((TextView) v.findViewById(R.id.label)).getText().toString();
		//v.findViewById(R.id.checkbox).se;
		//h.msgbox(((TextView) v.findViewById(R.id.label)).getText().toString());
		//AQuery ac = new AQuery(v);
		//h.msgbox(ac.id(R.id.label).getText().toString());
		//if(ac.id(R.id.checkbox).isChecked()) ac.id(R.id.checkbox).getCheckBox().setChecked(false);
		//CheckBox cb = ((
		((CheckBox) v.findViewById(R.id.checkbox)).setChecked(false);
		((TextView) v.findViewById(R.id.label)).setText("test");
	}*/
	
	
	/** Called when the activity is first created. */
	@Override
	public void onPause() {
		super.onPause();
		/*Intent intent = new Intent();
		intent.putExtra("chave_do_intent", "OptionsResult!");
		setResult(RESULT_OK);
		finish();*/
	}	
	
	/*public void retornarParaOutraActivity(){
		Intent intent = new Intent();
		intent.putExtra("chave_do_intent", new String[]{"1"});
		setResult(1, intent);
		finish();
	} */
	
	/*public class OptionsArrayAdapter extends ArrayAdapter<String> {
		private final Context context;
		private final List<String> values;
		private final List<String> categories;
		private AQuery a;
	 
		public OptionsArrayAdapter(Context context, AgendaSESC agendaSESC) {
			super(context, R.layout.agenda_options_row, Arrays.asList(agendaSESC.categoryNames));
			this.context = context;
			this.values = Arrays.asList(agendaSESC.categoryNames);
			this.categories = Arrays.asList(agendaSESC.categoryIDs.split(","));
		}
	 
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			// Get Listrow View-Layout
			LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View rowView = inflater.inflate(R.layout.agenda_options_row, parent, false);
			
	 		a = new AQuery(rowView);
	 		//a.clickable(true).clicked(this, "clicktest");
	 		
	 		a.id(R.id.label).text(Html.fromHtml(values.get(position)));
	 		//a.clickable(true).clicked(this, "onclick");
	 		/* TextView textView = (TextView) rowView.findViewById(R.id.label);
			//textView.setText(values.get(position));
			textView.setText(Html.fromHtml(values.get(position))); * /
	 		
	 		//ImageView imageView = (ImageView) rowView.findViewById(R.id.logo);

			
			// Change icon based on category
			int category = Integer.parseInt(categories.get(position));
	        System.out.println("category "+category);
			//a.id(imageView).image("http://"+this.getHost(s)+"/favicon.ico", 
			//		false, false, 0, 0, null, AQuery.FADE_IN);   // Google News
			
			
	        a.id(R.id.logo).image(agendaSESC.getCategoryImageRessource(category));	
	        //a.clickable(true).clicked(this, "onclick");
	        
	        //a.id(R.id.checkbox).clickable(true).clicked(this, "onclick");
			
	        /*ListView myListView = (ListView) findViewById(R.id.infoList);
    	    myListView.setAdapter(new InfoArrayAdapter(this, listContents, listCategories)); */
			 	    
    	    /*
    	       myListView.setOnItemClickListener(new OnItemClickListener() {
    	    	 public void onItemClick(AdapterView a, View v, int position, long id) {
    	             // Show information details
    	             String s =(String) ((TextView) v.findViewById(R.id.label)).getText();
    	             Toast.makeText(InfoActivity.this, ""+detailList.get(position), Toast.LENGTH_LONG).show();
    	                 }
    	    }); 
    	    * /
	        
	        
	        
	        return rowView;
		}
		

	    
	    
	} */
	

}  
