package com.qqdiary.app.ui;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;

public class TablePopupMenu extends JPopupMenu {
	private JMenuItem viewDiaryItem;
	private JMenuItem delDiaryItem;
	private JMenuItem updateTableItem;
	
	public TablePopupMenu(){
		super();
		viewDiaryItem = new JMenuItem("´ò¿ª");
		delDiaryItem = new JMenuItem("É¾³ý");
		updateTableItem = new JMenuItem("Ë¢ÐÂ");
		
		this.add(viewDiaryItem);
		this.addSeparator();
		this.add(delDiaryItem);
		this.addSeparator();
		this.add(updateTableItem);
	}
	
	public static void addPopup(Component component, final JPopupMenu popup) {
		   component.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent e) {
		     if (e.isPopupTrigger())
		      showMenu(e);
		    }
		    public void mouseReleased(MouseEvent e) {
		     if (e.isPopupTrigger())
		      showMenu(e);
		    }
		    private void showMenu(MouseEvent e) {
		     popup.show(e.getComponent(), e.getX(), e.getY());
		    }
		   });
	}
}
