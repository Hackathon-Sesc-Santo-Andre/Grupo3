package com.example.webview_example;

import java.util.Date;

import com.androidquery.AQuery;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MyFragment extends Fragment {
	public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";

	AgendaSESC myAgenda;
	private WebView myWebView;
	private ProgressBar progressBar;
	AQuery a;
	
	public static final MyFragment newInstance(int dayDiff)
	{
		MyFragment f = new MyFragment();
		Bundle bdl = new Bundle(1);
	    bdl.putInt(EXTRA_MESSAGE, dayDiff);
	    f.setArguments(bdl);
	    return f;
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
        Bundle savedInstanceState) {
		View v = null; 
		
		    int dayDifference = getArguments().getInt(EXTRA_MESSAGE);

		  	v = inflater.inflate(R.layout.webview_layout, container, false);
			
		  	Date today = new Date();	
			myAgenda = new AgendaSESC(new Date(today.getTime()+ dayDifference * 1000*60*60*24), "");
			
			myWebView = (WebView) v.findViewById(R.id.webView1);
			progressBar = (ProgressBar) v.findViewById(R.id.progressBar5);
			
			//myWebView.getSettings().setBuiltInZoomControls(true);
			//myWebView.setGestureDetector(new GestureDetector(new CustomGestureDetector()));
			myWebView.setWebViewClient(new myWebClient());
	        myWebView.getSettings().setJavaScriptEnabled(true);
	        
	        //msgbox("Carregando programação...");
	        progressBar.setVisibility(View.VISIBLE);
			myWebView.loadUrl(myAgenda.getURL());
	        //myWebView.loadUrl("http://goo.gl/maps/tMv8Q");

		return v;
		
		/*View v = inflater.inflate(R.layout.myfragment_layout, container, false);
		TextView messageTextView = (TextView)v.findViewById(R.id.textView);
		messageTextView.setText(showDate);
		return v;  */
    }
	
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
           	
			myWebView.loadUrl("javascript:dE=document.getElementsByTagName('div');for (var i=0;i<dE.length;i++) { if(dE[i].id == 'box') dE[i].style.width = 'auto'; } dE=document.getElementsByTagName('table');for (var i=0;i<dE.length;i++) { dE[i].width = '100%'} dE=document.getElementsByTagName('img');dE[3].parentNode.removeChild(dE[3]);dE[1].parentNode.removeChild(dE[1]);dE=document.getElementById('print_text');dE.parentNode.removeChild(dE);document.getElementById('text_imprime').innerHTML = '<br><b>"+myAgenda.getDateString()+"</b>';");

        }
    }
    
    

}
