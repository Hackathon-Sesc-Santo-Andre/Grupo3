package com.example.webview_example;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.AQuery;

public class MainActivity extends Activity {
    
	AQuery a;
    
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_main);
	    
	   
	    a = new AQuery(this);
	    
	    /*a.id(R.id.imageView2).animate(R.anim.splash);    
		a.id(R.id.textView2).animate(R.anim.splash); */
		
		ImageView imageSESC = (ImageView) findViewById(R.id.imageView2);
	    imageSESC.getParent().bringChildToFront(imageSESC);
	    TextView unidadeText = (TextView) findViewById(R.id.textView2);
	    imageSESC.getParent().bringChildToFront(imageSESC);
	    Animation animation = AnimationUtils.loadAnimation(this,R.anim.splash);
	    imageSESC.startAnimation(animation);
	    unidadeText.startAnimation(animation);
	    

	    a.id(R.id.agenda_button).clickable(true).clicked(this, "agenda_button_click");
	    /* Button agenda_button = (Button)findViewById(R.id.agenda_button);  
	    agenda_button.setOnClickListener(new OnClickListener() {
        	
        	@Override
        	public void onClick(View arg0) {
        		  		 
        		MainActivity.this.startActivityForResult(new Intent(MainActivity.this, AgendaActivity.class),-1);
        	}
        	
        });  */ 
	    
	    a.id(R.id.info_button).clickable(true).clicked(this, "info_button_click");
	    /*Button info_button = (Button)findViewById(R.id.info_button);  
	    info_button.setOnClickListener(new OnClickListener() {
        	
        	@Override
        	public void onClick(View arg0) {
        		  		 
        		MainActivity.this.startActivityForResult(new Intent(MainActivity.this, InfoActivity.class),-1);
        	}
        	
        });  */
	    
	    a.id(R.id.map_button).clickable(true).clicked(this, "map_button_click");
	    /*Button map_button = (Button)findViewById(R.id.map_button);  
	    map_button.setOnClickListener(new OnClickListener() {
        	
        	@Override
        	public void onClick(View arg0) {
        		  		 
        		MainActivity.this.startActivity(new Intent(Intent.ACTION_VIEW, 
        			       Uri.parse("http://goo.gl/maps/tMv8Q")));
        	}
        	
        }); */ 
	    
	    a.id(R.id.about_button).clickable(true).clicked(this, "about_button_click");
	    /* Button about_button = (Button)findViewById(R.id.about_button);  
	    about_button.setOnClickListener(new OnClickListener() {
        	
        	@Override
        	public void onClick(View arg0) {
        		//About myAbout = new About(localContext);   		 
        		//myAbout.showDialog();
        		new About(MainActivity.this).showDialog();
        	}
        	
        });  */ 
	    
	}
	
	public void about_button_click() {
		new About(MainActivity.this).showDialog();		
	}

	public void agenda_button_click() {
		MainActivity.this.startActivityForResult(new Intent(MainActivity.this, AgendaActivity.class),-1);
	
	}
	
	public void info_button_click() {
		MainActivity.this.startActivity(new Intent(MainActivity.this, InfoActivity.class));
	}	
	
	public void map_button_click() {
		MainActivity.this.startActivity(new Intent(Intent.ACTION_VIEW, 
			       Uri.parse("http://goo.gl/maps/tMv8Q")));
	}	
	
	   

	
}
