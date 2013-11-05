package com.locusdiary.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.locusdiary.closure.ICallback;
import com.locusdiary.listener.FileLogger;
import com.locusdiary.module.DiaryFolder;
import com.locusdiary.module.SessionManager;


/**
 * ������ӡ��޸Ľ�����
 * @author Administrator
 *
 */
public class FolderDialog extends JDialog{
	private DiaryFolder folder;
	
	/**����Ļص�����ָ�룬���ڱ��������Ϣ*/
	private ICallback callback;
	
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
				exit();
			}
		});
		
		JPanel buttonPanel = new JPanel(new BorderLayout());
		JPanel opanel = new JPanel();
		opanel.add(addButton);
		opanel.add(cancelButton);
		buttonPanel.add(opanel, "Center");
		
		add(namePanel, "North");
		add(descirptPanel, "Center");
		add(buttonPanel, "South");
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
		folder.setUserId(SessionManager.getInstance().getUser().getUserid());
		folder.setName(nameField.getText());
		folder.setDescribe(descripteField.getText());
	}
	
	/**
	 * ���������Ӧ����
	 */
	private void saveFolder(){
		saveComponentValue();
		
		try {
			assert folder.getName().length()!=0:"����������Ϊ�գ�";
			assert folder.getName().length()<10:"���������Ȳ��ܴ���10��";
			assert folder.getDescribe().length()<30:"�����������Ȳ��ܴ���30��";
		} catch (AssertionError e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e.getMessage());
			return;
		}
		
		/**ֱ�ӵ��ûص��������������Ϣ*/
		callback.callback(folder);
		this.dispose();
	}

	/**
	 * �˳�������Ӧ����
	 */
	private void exit(){
		int select = JOptionPane.showConfirmDialog( null , "ȷ��ֱ���˳���" , "��ʾ", JOptionPane.YES_NO_OPTION );
		if (select == JOptionPane.YES_OPTION) {
			this.dispose();
		}
	}

}
