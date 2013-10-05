package com.qqdiary.app.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class WeatherPopupMenu extends JPopupMenu implements ActionListener{

	private final DiaryDialog frame;
	private JMenuItem sun;
	private JMenuItem cloudy;
	private JMenuItem rain;
	private JMenuItem snow;
	
	public WeatherPopupMenu(final DiaryDialog frame) {
		this.frame = frame;
		
	    this.sun = new JMenuItem();
	    this.cloudy = new JMenuItem();
	    this.rain = new JMenuItem();
	    this.snow = new JMenuItem();
	    
	    this.sun.setText(IconImageFactory.WEATHER_TYPES[0]);
	    this.cloudy.setText(IconImageFactory.WEATHER_TYPES[1]);
	    this.rain.setText(IconImageFactory.WEATHER_TYPES[2]);
	    this.snow.setText(IconImageFactory.WEATHER_TYPES[3]);
	 
	    
	    this.sun.setIcon(IconImageFactory.getInstance().createIconByType(IconImageFactory.WEATHER_TYPES[0]));
	    this.cloudy.setIcon(IconImageFactory.getInstance().createIconByType(IconImageFactory.WEATHER_TYPES[1]));
	    this.rain.setIcon(IconImageFactory.getInstance().createIconByType(IconImageFactory.WEATHER_TYPES[2]));
	    this.snow.setIcon(IconImageFactory.getInstance().createIconByType(IconImageFactory.WEATHER_TYPES[3]));
	    
	    this.sun.addActionListener(this);
	    this.cloudy.addActionListener(this);
	    this.rain.addActionListener(this);
	    this.snow.addActionListener(this);
	    
	    add(this.sun);
	    add(this.cloudy);
	    add(this.rain);
	    add(this.snow);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == this.sun) {
			frame.setUIWeather(IconImageFactory.WEATHER_TYPES[0]);
		}
		
		if (e.getSource() == this.cloudy) {
			frame.setUIWeather(IconImageFactory.WEATHER_TYPES[1]);
		}
		
		if (e.getSource() == this.rain) {
			frame.setUIWeather(IconImageFactory.WEATHER_TYPES[2]);
		}
		
		if (e.getSource() == this.snow) {
			frame.setUIWeather(IconImageFactory.WEATHER_TYPES[3]);
		}
		
	}
	
	
}
