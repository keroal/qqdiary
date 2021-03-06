package com.qqdiary.app.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

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

/**
 * 分类添加、修改界面类
 * @author Administrator
 *
 */
public class FolderDialog extends JDialog{
	private DiaryFolder folder;
	
	/**传入的回调函数指针，用于保存分类信息*/
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
	
	/**
	 * 初始化界面控件
	 */
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
				saveFolder();
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
	
	/**
	 * 初始化界面数据
	 */
	private void initComponentValue(){
		nameField.setText(folder.getName());
		descripteField.setText(folder.getDescribe());
	}
	
	/**
	 * 界面数据对象化操作
	 */
	private void saveComponentValue(){
		folder.setName(nameField.getText());
		folder.setDescribe(descripteField.getText());
	}
	
	/**
	 * 保存操作响应函数
	 */
	private void saveFolder(){
		saveComponentValue();
		if (folder.getName().isEmpty()) {
			JOptionPane.showMessageDialog(null, "分类名称不能为空！");
			return;
		}
		
		/**直接调用回调函数保存分类信息*/
		callback.callback(folder);
		this.dispose();
	}

	/**
	 * 退出操作响应函数
	 */
	private void exitDialog(){
		int select = JOptionPane.showConfirmDialog( null , "确定直接退出？" , "提示", JOptionPane.YES_NO_OPTION );
		if (select == JOptionPane.YES_OPTION) {
			this.dispose();
		}
	}

}
