package com.locusdiary.module;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * ID工厂类（单例模式）
 * @author Administrator
 *
 */
public class TimeHelper {
	private static TimeHelper timeHelper = null;
	
	private TimeHelper() {
		
	}
	
	public static TimeHelper getInstance(){
		if(timeHelper == null){
			timeHelper =  new TimeHelper();
		}
		return timeHelper;
	}
	
	/**
	 * 创建任意唯一ID
	 * @return
	 */
	public String createRandomID() {
		return getTime();
	}
	
	public String getCurrentTime() {
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 return formatter.format(new Date());
	}
	
	public boolean checkTimeFormate(String time, String formate) {
		 SimpleDateFormat dFormat = new SimpleDateFormat(formate);
	        try {
	        	dFormat.setLenient(false);
	        	dFormat.parse(time);
	            return true;
	        } catch (ParseException e) {
	            
	        }
		return false;
	}
	
	private String getTime() {
		Calendar c = Calendar.getInstance(); 
	    long millis = c.getTimeInMillis(); 
		return Long.toString(millis);
	}
	
	
	
	
}
