package com.example.webview_example;

import java.util.List;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.AQuery;
 
public class CustomArrayAdapter extends ArrayAdapter<String> {
	private final Context context;
	private final List<String> values;
	private AQuery a;
 
	public CustomArrayAdapter(Context context, List<String> listContents) {
		super(context, R.layout.list_entry, listContents);
		this.context = context;
		this.values = listContents;
		a = new AQuery(context);
	}
 


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		LayoutInflater inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
		View rowView = inflater.inflate(R.layout.list_entry, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.label);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.logo);
		//textView.setText(values.get(position));
		textView.setText(Html.fromHtml(values.get(position)));
		
		// Change icon based on name
		String s = values.get(position);
 
		System.out.println(s);

		a.id(imageView).image("http://"+this.getHost(s)+"/favicon.ico", 
				false, false, 0, 0, null, AQuery.FADE_IN);
		/*
		if (s.equals("WindowsMobile")) {
			imageView.setImageResource(R.drawable.windowsmobile_logo);
		} else if (s.equals("iOS")) {
			imageView.setImageResource(R.drawable.ios_logo);
		} else if (s.equals("Blackberry")) {
			imageView.setImageResource(R.drawable.blackberry_logo);
		} else {
			imageView.setImageResource(R.drawable.android_logo);
		}
 		*/
		
		return rowView;
	}
	
	private static String getHost(String url){
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