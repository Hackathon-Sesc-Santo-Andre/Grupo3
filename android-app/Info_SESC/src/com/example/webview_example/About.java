package com.example.webview_example;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class About {
	private Context aboutContext;
	public About(Context context) {
		aboutContext = context;
	}
	
	public void showDialog() {
		// Show OK-Dialog
		AlertDialog.Builder builder = new AlertDialog.Builder(aboutContext);
		builder.setMessage("Aplicativo SESC Hackathon 2013 Grupo 3: Rafael, Saulo, Cassia, Steffen e Rodrigo")
		       .setCancelable(false)
		       .setPositiveButton("OK", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		                //do things
		           }
		       });
		AlertDialog alert = builder.create();
		alert.show();
	
	}
}
