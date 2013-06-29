package com.example.webview_example;

import java.util.Date;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

public class MyFragment extends Fragment {
	public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";

	AgendaSESC myAgenda;
	private WebView myWebView;
	private ProgressBar progressBar;
	AQuery a;
	Helper h;
	
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
		    a = new AQuery(this.getActivity());
		    h = new Helper(this.getActivity());
		
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
	        
	        //h.msgbox("Carregando programação...");
	        progressBar.setVisibility(View.VISIBLE);
	        
	        a.ajax(myAgenda.getURL(), String.class, new AjaxCallback<String>() {

	            @Override
	            public void callback(String url, String html, AjaxStatus status) {
	            	html = "<html lang=\"pt-br\" xmlns=\"http://www.w3.org/1999/xhtml\">"+
	                       "<head><meta charset=\"UTF-8\"><link rel=\"stylesheet\" href=\"/css/sesc.css?v=0.1\" media=\"all\" /><br />"+
	                       "<link rel=\"stylesheet\" href=\"/css/sesc.programacao_aulas.css\" media=\"screen\" /></head>" + 
	            			html +
	            			"</html>";
	            	myWebView.loadDataWithBaseURL("http://www.sescsp.org.br/programacao/ajax/", html, "text/html", null, myAgenda.getURL());     
	            }
	            
	        });
			
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
