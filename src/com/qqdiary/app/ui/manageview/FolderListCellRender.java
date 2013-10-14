package com.qqdiary.app.ui.manageview;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;

import com.qqdiary.app.module.DiaryFolder;
import com.qqdiary.app.ui.IconImageFactory;

/**
 * 分类JList显示样式类
 * @author Administrator
 *
 */
public class FolderListCellRender extends DefaultListCellRenderer {

	 @Override
	    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
	    	if(!(value instanceof DiaryFolder))
	        	return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
	       JLabel jl = (JLabel)super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
	       DiaryFolder folder = (DiaryFolder) value;
	       jl.setIcon(IconImageFactory.getInstance().createIconByName("folder.png")); 
	       jl.setText(folder.getName());
	       jl.setToolTipText(folder.getDescribe());
	       return jl;
	   }
}
