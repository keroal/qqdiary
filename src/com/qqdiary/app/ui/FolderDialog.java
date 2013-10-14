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
 * ������ӡ��޸Ľ�����
 * @author Administrator
 *
 */
public class FolderDialog extends JDialog{
	private DiaryFolder folder;
	
	/**����Ļص�����ָ�룬���ڱ��������Ϣ*/
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
	 * ��ʼ������ؼ�
	 */
	private void initComponet(){
		this.setTitle("��ӷ���");
		this.setSize(300, 150);
		this.setLocationRelativeTo(null);
		
		JPanel namePanel = new JPanel(new BorderLayout());
		nameLabel = new JLabel("���ƣ�");
		nameField = new JTextField();
		namePanel.add(nameLabel, "West");
		namePanel.add(nameField, "Center");
		
		JPanel descirptPanel = new JPanel(new BorderLayout());
		descriptLabel = new JLabel("������");
		descripteField = new JTextArea();
		descirptPanel.add(descriptLabel, "West");
		descirptPanel.add(descripteField, "Center");
		
		
		addButton = new JButton("ȷ��");
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				saveFolder();
			}
		});
		
		cancelButton = new JButton("ȡ��");
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
	 * ��ʼ����������
	 */
	private void initComponentValue(){
		nameField.setText(folder.getName());
		descripteField.setText(folder.getDescribe());
	}
	
	/**
	 * �������ݶ��󻯲���
	 */
	private void saveComponentValue(){
		folder.setName(nameField.getText());
		folder.setDescribe(descripteField.getText());
	}
	
	/**
	 * ���������Ӧ����
	 */
	private void saveFolder(){
		saveComponentValue();
		if (folder.getName().isEmpty()) {
			JOptionPane.showMessageDialog(null, "�������Ʋ���Ϊ�գ�");
			return;
		}
		
		/**ֱ�ӵ��ûص��������������Ϣ*/
		callback.callback(folder);
		this.dispose();
	}

	/**
	 * �˳�������Ӧ����
	 */
	private void exitDialog(){
		int select = JOptionPane.showConfirmDialog( null , "ȷ��ֱ���˳���" , "��ʾ", JOptionPane.YES_NO_OPTION );
		if (select == JOptionPane.YES_OPTION) {
			this.dispose();
		}
	}

}
