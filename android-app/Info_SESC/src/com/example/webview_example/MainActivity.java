package com.example.webview_example;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.net.Uri;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.AQuery;

public class MainActivity extends Activity {
    
	AQuery a;
	Helper h;
    
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.main_activity);
	    	   
	    a = new AQuery(this);
	    h = new Helper(this);
	    
	    /*Integer startColor = Color.parseColor("#bbbbbb");
	    Integer endColor = Color.parseColor("#f0f0f0");
	    GradientDrawable gd = new GradientDrawable(Orientation.TOP_BOTTOM, 
	         new int[] { startColor, endColor });
	    a.id(R.id.mainLayoutView).getView().setBackgroundDrawable(gd);*/
	    
	    
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
	    
	    a.id(R.id.imageButton1).clickable(true).clicked(this, "qrcode_click");
	    
	}
	
	public void about_button_click() {
		new Helper(MainActivity.this).showAboutDialog();		
	}

	public void agenda_button_click() {
		startActivityWithAnim(new Intent(MainActivity.this, AgendaActivity.class));
	}
	
	public void info_button_click() {
		startActivityWithAnim(new Intent(MainActivity.this, InfoActivity.class));
	}	
	
	public void map_button_click() {
		startActivityWithAnim(new Intent(Intent.ACTION_VIEW, 
			       Uri.parse("http://goo.gl/maps/tMv8Q")));
	}	

	public void qrcode_click() {
		h.msgbox("qrcode");
		QRIntentIntegrator qr = new QRIntentIntegrator(this);
		qr.initiateScan();
		
	}	
	
	public void startActivityWithAnim(Intent myIntent) {
		MainActivity.this.startActivity(myIntent);
		overridePendingTransition (R.anim.up, R.anim.down);
	}
	
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
	    QRIntentResult scanResult = QRIntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
	    if (scanResult != null) {
	    	h.msgbox(scanResult.getContents());
	      // handle scan result
	    }
	    // else continue with any other code you need in the method

}


	
}
