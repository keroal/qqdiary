package com.qqdiary.app.module;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentDateInformation {
	private String dateTime;
	private String weather;
	
	private static CurrentDateInformation dateInformation= null;
	
	public static CurrentDateInformation getInstance() {
		if (dateInformation != null) {
			return dateInformation;
		}
		
		return new CurrentDateInformation();
	}
	
	private CurrentDateInformation(){
		this.dateTime = getCurrentDateTime();
		this.weather = "ÇçÌì";
	}
	
	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public static String getCurrentDateTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd E");
		return df.format(new Date());
	}
	
	
	
}
