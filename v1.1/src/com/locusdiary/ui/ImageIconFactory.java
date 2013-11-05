package com.locusdiary.ui;

import java.awt.Image;

import javax.swing.ImageIcon;

public class ImageIconFactory {
	
	private static ImageIconFactory  imageIconFactory = null;
	
	private ImageIconFactory() {
		
	}
	
	public static ImageIconFactory getInstance() {
		if (imageIconFactory == null) {
			imageIconFactory = new ImageIconFactory();
		}
		return imageIconFactory;
	}
	public ImageIcon getImageIcon(String name) {
		return new ImageIcon(this.getClass().getResource("res/" + name));
	}

}
