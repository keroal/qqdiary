package com.qqdiary.app.ui;


import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 * 工具帮助信息对话框类
 * @author Administrator
 *
 */
public class AboutDialog extends JDialog {
	public AboutDialog(){
		this.setTitle("说明");
		this.setSize(280, 200);
		this.setLocationRelativeTo(null);
		
		this.setLayout(null);
		JLabel team = new JLabel("小组： 308 studio");
		JLabel tool = new JLabel("名称： 青青日记  v1.0");
		
		team.setBounds(70, 50, 150, 25);
		tool.setBounds(70, 80, 150, 25);
		
		this.add(team);
		this.add(tool);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new AboutDialog().show();
		
	}
}
