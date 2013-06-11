package com.example.webview_example;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class OptionsActivity extends Activity {
    private ListView myListView ;  
    
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	
	    setContentView(R.layout.activity_options);
	    
	    
	    
	    // Find the ListView resource.     
	    myListView = (ListView) findViewById( R.id.listView1 );
	    
	    // Create and populate a List of planet names.  
	    String[] planets = new String[] { "teatro", "música", "circo ", "dança",
	    		"cultura digital", "artes plásticas e visuais", "literatura", "cinema e vídeo",
	    		"esportes", "corpo e expressão", "natureza e meio ambiente", 
	    		"saúde e alimentação", "sociedade e cidadania", "infantil", "terceira idade",
	    		"férias e turismo social", "intergerações"};    
	    
	    // Set the ArrayAdapter as the ListView's adapter.  
	    myListView.setAdapter( new ArrayAdapter<String>(this, R.layout.simplerow, planets) );        
	    
	}

}
