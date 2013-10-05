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
		
		modifyFolderItem = new JMenuItem("�޸�");
		delFolderItem = new JMenuItem("ɾ��");
		updateListItems = new JMenuItem("ˢ��");
		
		this.add(modifyFolderItem);
		this.add(delFolderItem);
		this.addSeparator();
		this.add(updateListItems);
	}
}
