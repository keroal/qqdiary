package com.qqdiary.app.module;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * ���ڡ�������Ϣȫ���ࣨ����ģʽ��
 * @author Administrator
 *
 */
public class DayInformationProvider {
	private String dateTime;
	private String weather;
	
	private static DayInformationProvider dateInformation= null;
	
	public static DayInformationProvider getInstance() {
		if (dateInformation != null) {
			return dateInformation;
		}
		
		dateInformation = new DayInformationProvider();
		return dateInformation;
	}
	
	private DayInformationProvider(){
		this.dateTime = getCurrentDateTime();
		this.weather = "����";
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
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(new Date());
	}
	
	
	
}
