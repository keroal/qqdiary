package com.locusdiary.ui;


import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 * ���߰�����Ϣ�Ի�����
 * @author Administrator
 *
 */
public class AboutDialog extends JDialog {
	public AboutDialog(){
		setTitle("˵��");
		setSize(280, 200);
		setLocationRelativeTo(null);
		setBackground(Color.white);
		
		setLayout(null);
		JLabel team = new JLabel("С�飺 308 studio");
		JLabel tool = new JLabel("���ƣ� �����ռ�  v1.0");
		
		team.setBounds(70, 50, 150, 25);
		tool.setBounds(70, 80, 150, 25);
		
		add(team);
		add(tool);
	}
	
}
