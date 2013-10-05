package com.qqdiary.app.ui;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class ListPopuoMenu extends JPopupMenu {
	private JMenuItem modifyFolderItem;
	private JMenuItem delFolderItem;
	private JMenuItem updateListItems;
	
	public ListPopuoMenu(){
		super();
		
		modifyFolderItem = new JMenuItem("ÐÞ¸Ä");
		delFolderItem = new JMenuItem("É¾³ý");
		updateListItems = new JMenuItem("Ë¢ÐÂ");
		
		this.add(modifyFolderItem);
		this.add(delFolderItem);
		this.addSeparator();
		this.add(updateListItems);
	}
}
