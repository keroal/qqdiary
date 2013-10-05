package com.qqdiary.app.ui;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;

import com.qqdiary.app.module.DiaryFolder;

public class ListCellRenderWithIcon extends DefaultListCellRenderer {

	 @Override
	    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
	    	if(!(value instanceof DiaryFolder))
	        	return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
	       JLabel jl = (JLabel)super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
	       DiaryFolder folder = (DiaryFolder) value;
	       jl.setIcon(new ImageIcon(getClass().getResource("res/folder.png"))); 
	       jl.setText(folder.getName());
	       return jl;
	   }
}
