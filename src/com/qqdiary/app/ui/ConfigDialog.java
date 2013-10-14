package com.qqdiary.app.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.eltima.components.ui.DatePicker;
import com.qqdiary.app.module.DayInformationProvider;

/**
 * ������Ϣ���öԻ�����
 * @author Administrator
 *
 */
public class ConfigDialog extends JDialog {
	private JLabel weatherLabel;
	private JLabel datelLabel;
	
	private JComboBox weatherComboBox;
	private DatePicker datePicker;
	
	private JButton storeButton;
	private JButton canncelButton;
	
	public ConfigDialog(){
		initComponent();
	}
	
	private void initComponent() {
		this.setTitle("��������");
		this.setSize(300, 150 );
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		
		weatherLabel = new JLabel("������    ");
		weatherComboBox = new JComboBox(IconImageFactory.WEATHER_TYPES);
		JPanel weatherPanel = new JPanel(new BorderLayout());
		weatherPanel.add(weatherLabel, "West");
		weatherPanel.add(weatherComboBox, "Center");
		
		datelLabel = new JLabel("���ڣ�    ");
		String DefaultFormat = "yyyy-MM-dd";
		Date sdate=new Date();
		Font font=new Font("Times New Roman", Font.BOLD, 14);
		Dimension dimension=new Dimension(177,24);
		datePicker = new DatePicker(sdate,DefaultFormat,font,dimension);
		JPanel datePanel = new JPanel(new BorderLayout());
		datePanel.add(datelLabel, "West");
		datePanel.add(datePicker, "Center");
		
		storeButton = new JButton("ȷ��");
		storeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				DayInformationProvider.getInstance().setDateTime(datePicker.getText());
				DayInformationProvider.getInstance().setWeather((String)weatherComboBox.getSelectedItem());
				dispose();
			}
		});
		
		canncelButton = new JButton("ȡ��");
		canncelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int select = JOptionPane.showConfirmDialog( null , "ȷ��û�б�����˳���" , "��ʾ", JOptionPane.YES_NO_OPTION );
				if (select == JOptionPane.YES_OPTION) {
					dispose();
				}
			}
		});
		
		weatherPanel.setBounds(30, 15, 230, 25);
		datePanel.setBounds(30, 50, 230, 25);	
		storeButton.setBounds(40, 90, 90, 25);
		canncelButton.setBounds(160, 90, 90, 25);
		
		
		this.add(datePanel);
		this.add(weatherPanel);
		this.add(storeButton);
		this.add(canncelButton);
	}
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ConfigDialog().show();
	}

}
