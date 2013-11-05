package com.qqdiary.app.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 显示视图基础类
 * @author Administrator
 *
 */
public class ViewPanel extends JPanel {
	private JPanel tagPanel;
	private JPanel mainPanel;
	private JLabel tagLabel;
	private String viewName;
	
	public ViewPanel(String name) {
		this.viewName = name;
		initComponet();
	}
	
	public ViewPanel() {
		this.viewName = "默认视图";
		initComponet();
	}
	
	public Component addComponent(Component comp){
		return mainPanel.add(comp);
	}
	
	public void addComponent(Component comp, Object local){
		mainPanel.add(comp, local);
	}
	
	public void setViewLayout(LayoutManager layout) {
		mainPanel.setLayout(layout);
	}
	
	private void initComponet() {
		tagPanel = new JPanel(){
			 @Override
			 protected void paintComponent(Graphics g) {
				 super.paintComponent(g);
				 ImageIcon icon = new ImageIcon(getClass().getResource("res/tag.png"));
				 g.drawImage(icon.getImage(), 0, 0, this);
			    }
		};
		mainPanel = new JPanel();
		
		tagLabel = new JLabel(viewName);
		tagLabel.setForeground(Color.white);
		tagPanel.setLayout(new BorderLayout());
		tagPanel.add(tagLabel, "West");
		
		this.setLayout(new BorderLayout());
		this.add(tagPanel, "North");
		this.add(mainPanel, "Center");
	}

}
