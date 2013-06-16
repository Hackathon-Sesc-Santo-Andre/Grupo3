package com.example.webview_example;


import android.R.bool;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;



@SuppressLint({ "SimpleDateFormat", "SetJavaScriptEnabled" })
public class AgendaActivity extends Activity {
	AgendaSESC myAgenda;
	Helper h;
	
	private CustomWebView myWebView;
	private ProgressBar progressBar;
	private Pair<Integer, bool> selectedCategory;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.agenda_activity);	    
	        
		h = new Helper(this);
		myAgenda = new AgendaSESC();

		myWebView = (CustomWebView) findViewById(R.id.webView1);
		progressBar = (ProgressBar) findViewById(R.id.progressBar5);
		
		//myWebView.getSettings().setBuiltInZoomControls(true);
		myWebView.setGestureDetector(new GestureDetector(new CustomGestureDetector()));
		myWebView.setWebViewClient(new myWebClient());
        myWebView.getSettings().setJavaScriptEnabled(true);
        
        h.msgbox("Carregando programação...");
        progressBar.setVisibility(View.VISIBLE);
		myWebView.loadUrl(myAgenda.getURL());

		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
	public boolean onOptionsItemSelected(MenuItem item) {
	    //respond to menu item selection
		
		
		switch (item.getItemId()) {
			case R.id.action_options_activities:
				startActivityForResult(new Intent(this, OptionsActivity.class), -1);
				break;
				
			case R.id.action_options_about:
				new Helper(this).showAboutDialog();
				break;
				
			default:
				// h.msgbox("ID: "+item.getItemId());
		}
		return true;
	
	}


	/*@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		//requestCode=100
		//resultCode=1
		
		String[] dado = data.getExtras().getStringArray("chave_do_intent");
		
		
	}*/

	
	
	
	
	
	// Make ProgressBar disappear when Content loaded
    public class myWebClient extends WebViewClient
    {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            view.loadUrl(url);
            return true;
 
        }
 
        @Override
        public void onPageFinished(WebView view, String url) {

            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);
           	
			myWebView.loadUrl("javascript:dE=document.getElementsByTagName('div');for (var i=0;i<dE.length;i++) { if(dE[i].id == 'box') dE[i].style.width = 'auto'; } dE=document.getElementsByTagName('table');for (var i=0;i<dE.length;i++) { dE[i].width = '100%'} dE=document.getElementsByTagName('img');dE[3].parentNode.removeChild(dE[3]);dE[1].parentNode.removeChild(dE[1]);dE=document.getElementById('print_text');dE.parentNode.removeChild(dE);"); //document.getElementById('text_imprime').innerHTML = '<br>"+myAgenda.getDateString()+"';");
        }
    }
   
	// CustomGestureDetector used in CustomWebview in order to enable WebView to interpret gesture
	private class CustomGestureDetector extends SimpleOnGestureListener {      
	    @Override
	    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
	        if(e1 == null || e2 == null) return false;
	        if(e1.getPointerCount() > 1 || e2.getPointerCount() > 1) return false;
	        else {
	            try { // right to left swipe 
	                if(e1.getX() - e2.getX() > 100 && Math.abs(velocityX) > 800) {
	                    //do your stuff
	                	
	                	myAgenda.nextDay();
	                	h.msgbox("Data: "+myAgenda.getDateString());
	                	
	                	progressBar.setVisibility(View.VISIBLE);
	                	
	                	
	                	//myWebView.loadUrl("about:blank");
	                	//myWebView.loadUrl("javascript:document.open();document.close();");
	                	myWebView.loadUrl(myAgenda.getURL());

	                	
	                    return true;
	                } //left to right swipe 
	                else if (e2.getX() - e1.getX() > 100 && Math.abs(velocityX) > 800) {
	                    
	                	
	                	myAgenda.prevDay();
	                	h.msgbox("Data: "+myAgenda.getDateString());
	                	
	                	
	                	progressBar.setVisibility(View.VISIBLE);
	                	
	                	//myWebView.loadUrl("about:blank");
	                	//myWebView.loadUrl("javascript:document.open();document.close();");
	                	myWebView.loadUrl(myAgenda.getURL());

	            		
	                    return true;
	                    
	                } //bottom to top, go to next document
	                /*else if(e1.getY() - e2.getY() > 100 && Math.abs(velocityY) > 800 
	                        && myWebView.getScrollY() >= myWebView.getScale() * (myWebView.getContentHeight() - myWebView.getHeight())) {
	                    //do your stuff
	                	
	                	h.msgbox("bottom to top");
	                	
	                    return true;
	                } //top to bottom, go to prev document
	                else if (e2.getY() - e1.getY() > 100 && Math.abs(velocityY) > 800 ) {
	                    //do your stuff
	                	
	                	h.msgbox("top to bottom");
	                	
	                    return true;
	                } */
	            } catch (Exception e) { // nothing
	            	//h.msgbox("Data: "+dateFormat.format(showdate));
	            }
	            return false;
	        }
	    }
	}
}
