package com.qqdiary.app.ui;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class IconImageFactory {
	public static final String RESOURCE_FOLDER_PATH = "res/";
	public static final String DEFAULT_ICON_PATH = RESOURCE_FOLDER_PATH + "default.png";
	public static final String[] WEATHER_TYPES = {"«ÁÃÏ", "∂‡‘∆", "∂‡”Í", "—©"};
	public static final String[] WEATHER_ICON_NAMES = {"sunshine.png", "cloudy.png", "rain.png", "snow.png"};
	
	private static IconImageFactory iconFactoryFactory = null;
	
	private IconImageFactory() {
		
	}
	
	public static IconImageFactory getInstance(){
		if(iconFactoryFactory == null){
			return new IconImageFactory();
		}
		
		return iconFactoryFactory;
	}
	
	public Icon createIconByName(String name) {
		String path = RESOURCE_FOLDER_PATH + name;
		return new ImageIcon(getClass().getResource(path));
	}
	
	public Icon createIconByType(String type) {
		String path = RESOURCE_FOLDER_PATH + WEATHER_ICON_NAMES[0];
		
		for (int i = 0; i < WEATHER_TYPES.length; i++) {
			if (WEATHER_TYPES[i].contains(type)) {
				path = RESOURCE_FOLDER_PATH + WEATHER_ICON_NAMES[i];
				break;
			}
		}

		return new ImageIcon(getClass().getResource(path));
	}
	
	
	
}
