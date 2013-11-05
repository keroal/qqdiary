package com.locusdiary.ui.module;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPopupMenu;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;


public class PopupMenuLabel extends JLabel {
	private JPopupMenu menu;  
  
    public PopupMenuLabel(){  
        super();  
        this.setText("¡ø");  
        this.setHorizontalTextPosition(SwingConstants.RIGHT );  
        addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				 setText("¨‹");  
                 menu.show(PopupMenuLabel.this, 0, PopupMenuLabel.this.getHeight());
			}
		});
    }  
    public PopupMenuLabel(final String label){  
        super(label);  
        this.setText(label + " ¡ø");  
        this.setHorizontalTextPosition(SwingConstants.RIGHT );  
        addMouseListener(new MouseAdapter() {  
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				 setText(label + " ¨‹");  
                 menu.show(PopupMenuLabel.this, 0, PopupMenuLabel.this.getHeight());
			} 
        });  
    }  
    
    public void addMenu(JPopupMenu menu){  
        this.menu=menu;  
    }  

}  
