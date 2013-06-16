package com.example.webview_example;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

public class Helper {
	private Context context;
	public Helper(Context context) {
		this.context = context;
	}
	
	public void showAboutDialog() {
		// Show OK-Dialog
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
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
	
	public void msgbox(String message) {
    	Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

}
