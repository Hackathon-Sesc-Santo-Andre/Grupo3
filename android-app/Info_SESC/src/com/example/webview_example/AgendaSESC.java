package com.example.webview_example;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressLint("SimpleDateFormat")
public class AgendaSESC {
    private Date agendaDate;
    private SimpleDateFormat dateFormat;
    private String categoriesSelected;
    private static String DATE_FORMAT = "yyyy-m-d";
    public static String categoryIDs = "2,3,18,4,5,6,7,8,9,10,11,12,13,14,15,16,17";
    public static String[] categoryNames = {"teatro", "música", "circo ", "dança",
		"cultura digital", "artes plásticas e visuais", "literatura", "cinema e vídeo",
		"esportes", "corpo e expressão", "natureza e meio ambiente", 
		"saúde e alimentação", "sociedade e cidadania", "infantil", "terceira idade",
		"férias e turismo social", "intergerações"}; 
    
   
    // private int[] facilities; 

    private static String urlHead = "http://www.sescsp.org.br/programacao/ajax/filtro.action?unities=37";
		
	public AgendaSESC(Date agendaDate, String categoriesSelected) {

		dateFormat = new SimpleDateFormat(DATE_FORMAT);
    	if(agendaDate == null)       this.agendaDate = new Date();
    	else                         this.agendaDate = agendaDate;
    	if(categoriesSelected == "") this.categoriesSelected = categoryIDs;
    	else                         this.categoriesSelected = categoriesSelected;
	}
	
	public AgendaSESC() {
		dateFormat = new SimpleDateFormat(DATE_FORMAT);
    	this.agendaDate = new Date();
        this.categoriesSelected = categoryIDs;
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

	public boolean setDate(Date newDate) {
			agendaDate = newDate;
			return true;
    }
	
	/* public boolean setDateString(String date) {
			try {
				agendaDate = dateFormat.parse(date);
				return true;
			} catch (ParseException e) {
				//e.printStackTrace();
				return false;
			} 
	} */
	
	public String getDateString() {
		return dateFormat.format(agendaDate);
	}

	public void selectCategories(String categoriesSelected){
		this.categoriesSelected = categoriesSelected;
	}	
	
	static int getCategoryImageRessource(int category) {
        switch (category) {
          case 2:  return R.drawable.ic_2;
          case 3:  return R.drawable.ic_3;
          case 4:  return R.drawable.ic_4;
          case 5:  return R.drawable.ic_5;
          case 6:  return R.drawable.ic_6;
          case 7:  return R.drawable.ic_7;
          case 8:  return R.drawable.ic_8;
          case 9:  return R.drawable.ic_9;
          case 10: return R.drawable.ic_10;
          case 11: return R.drawable.ic_11;
          case 12: return R.drawable.ic_12;
          case 13: return R.drawable.ic_13;
          case 14: return R.drawable.ic_14;
          case 15: return R.drawable.ic_15;
          case 16: return R.drawable.ic_16;
          case 17: return R.drawable.ic_17;
          case 18: return R.drawable.ic_18;
          default: return R.drawable.sesc_logo;
       }	

	} 
}
