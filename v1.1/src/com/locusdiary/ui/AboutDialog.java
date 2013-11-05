package com.locusdiary.ui;


import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 * 工具帮助信息对话框类
 * @author Administrator
 *
 */
public class AboutDialog extends JDialog {
	public AboutDialog(){
		setTitle("说明");
		setSize(280, 200);
		setLocationRelativeTo(null);
		setBackground(Color.white);
		
		setLayout(null);
		JLabel team = new JLabel("小组： 308 studio");
		JLabel tool = new JLabel("名称： 青青日记  v1.0");
		
		team.setBounds(70, 50, 150, 25);
		tool.setBounds(70, 80, 150, 25);
		
		add(team);
		add(tool);
	}
	
}
