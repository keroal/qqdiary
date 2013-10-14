package com.qqdiary.app.ui.welcomeview;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.qqdiary.app.ui.ViewPanel;

/**
 * ��ӭ��ͼ������
 * @author Administrator
 *
 */
public class WelcomeViewPage extends ViewPanel {
	private static String viewName = "��ӭ��ͼ";
	
	public WelcomeViewPage() {
		super(viewName);
		// TODO Auto-generated constructor stub
		
		setViewLayout(new BorderLayout());
		ImageIcon image = new ImageIcon(this.getClass().getResource("res/welcome.png"));
		JLabel lable = new JLabel(image);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		lable.setBounds(0,0,dimension.width, dimension.height);
		addComponent(lable, "Center");
	}
}
