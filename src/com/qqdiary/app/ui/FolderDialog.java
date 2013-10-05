package com.qqdiary.app.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.qqdiary.app.closure.ICallback;
import com.qqdiary.app.module.DiaryFolder;

public class FolderDialog extends JDialog{
	private DiaryFolder folder;
	private ICallback callback;
	
	private ViewPanel viewPanel;
	private JLabel nameLabel;
	private JTextField nameField;
	private JLabel descriptLabel;
	private JTextArea descripteField;
	
	private JButton addButton;
	private JButton cancelButton;
	
	public FolderDialog(DiaryFolder folder, ICallback callback){
		// TODO Auto-generated constructor stub
		this.folder = folder;
		this.callback = callback;
		initComponet();		
		initComponentValue();
	}
	
	public FolderDialog( ICallback callback){
		// TODO Auto-generated constructor stub
		this.folder = new DiaryFolder();
		this.callback = callback;
		initComponet();
	}
	
	
	private void initComponet(){
		this.setTitle("添加分类");
		this.setSize(300, 150);
		this.setLocationRelativeTo(null);
		
		JPanel namePanel = new JPanel(new BorderLayout());
		nameLabel = new JLabel("名称：");
		nameField = new JTextField();
		namePanel.add(nameLabel, "West");
		namePanel.add(nameField, "Center");
		
		JPanel descirptPanel = new JPanel(new BorderLayout());
		descriptLabel = new JLabel("描述：");
		descripteField = new JTextArea();
		descirptPanel.add(descriptLabel, "West");
		descirptPanel.add(descripteField, "Center");
		
		
		addButton = new JButton("确定");
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		cancelButton = new JButton("取消");
		cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				exitDialog();
			}
		});
		
		JPanel buttonPanel = new JPanel(new BorderLayout());
		JPanel opanel = new JPanel();
		opanel.add(addButton);
		opanel.add(cancelButton);
		buttonPanel.add(opanel, "Center");
		
		viewPanel = new ViewPanel(" ");
		viewPanel.setViewLayout(new BorderLayout());
		
		viewPanel.addComponent(namePanel, "North");
		viewPanel.addComponent(descirptPanel, "Center");
		viewPanel.addComponent(buttonPanel, "South");
		this.add(viewPanel);
	}
	
	private void initComponentValue(){
		nameField.setText(folder.getName());
		descripteField.setText(folder.getDescribe());
	}
	
	private void saveComponentValue(){
		folder.setName(nameField.getText());
		folder.setDescribe(descripteField.getText());
	}

	private void exitDialog(){
		int select = JOptionPane.showConfirmDialog( null , "确定要退出！" , "提示", JOptionPane.YES_NO_OPTION );
		if (select == JOptionPane.YES_OPTION) {
			this.dispose();
		}
	}

}
