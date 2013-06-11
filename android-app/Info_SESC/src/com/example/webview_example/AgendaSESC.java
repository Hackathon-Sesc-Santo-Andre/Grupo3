package com.example.webview_example;

import android.annotation.SuppressLint;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressLint("SimpleDateFormat")
public class AgendaSESC {
    private Date agendaDate;
    private SimpleDateFormat dateFormat;
    
    // private int[] facilities; 
    private int[] categories;

    private static String urlHead = "http://www.sescsp.org.br/sesc/programa_new/versao_impressao.cfm?UNIDADE_ID=37&BUSCAR_CURSO=0&Y=12&X=44&BUSCAR_SESC_TV=0&ATIVIDADE_ID=0";
		
	public AgendaSESC() {

		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    	agendaDate = new Date();
    	categories = new int[] {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18 };
    	
	}
	
	
	public boolean nextDay() {
		try {
			agendaDate.setTime(agendaDate.getTime()+1000*60*60*24);
			return true;
		}
		catch (Exception e) {
			return false;
		}
		
	}
	
	public boolean prevDay() {
		try {
			agendaDate.setTime(agendaDate.getTime()-1000*60*60*24);
        	return true;
		}
		catch (Exception e) {
			return false;
		}		
		
	}
	
	public String getURL() {

		return urlHead+"&DATA2="+this.getDateString();
   
		
	}


	public String getDateString() {
		return dateFormat.format(agendaDate);
	}
	

}
