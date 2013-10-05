package com.qqdiary.app.module;

import java.io.Serializable;

public class DiaryWeather implements Serializable {
	private static final long serialVersionUID = 5775637820097834974L;
	private String weather;
	public static final DiaryWeather fine = new DiaryWeather("��");
	public static final DiaryWeather rain = new DiaryWeather("��");
	public static final DiaryWeather cloud = new DiaryWeather("��");
	public static final DiaryWeather snow = new DiaryWeather("ѩ");

	  protected DiaryWeather()
	  {
	    this.weather = "��";
	  }

	  protected DiaryWeather(String weather)
	  {
	    this.weather = weather;
	  }

	  public final String toString()
	  {
	    return this.weather;
	  }

	  public static final DiaryWeather getWeather(String weather)
	  {
	    if (fine.weather.equals(weather))
	      return fine;
	    if (rain.weather.equals(weather))
	      return rain;
	    if (cloud.weather.equals(weather))
	      return cloud;
	    if (snow.weather.equals(weather))
	      return snow;
	    return null;
	  }
}
