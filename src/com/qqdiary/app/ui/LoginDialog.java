package com.qqdiary.app.ui;

import java.awt.Button;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.qqdiary.app.service.DiaryService;
import com.qqdiary.app.service.DiaryShare;
import com.qqdiary.app.service.UserVerify;
public class LoginDialog extends JDialog{
	private UserVerify service;
	private JLabel nameLabel;
	private JTextField nameField;
	private JLabel pwLabel;
	private JLabel titleLabel;
	private JTextField pwField;
	private	JButton loginbButton;
	private JButton exitButton;
	private JButton registButton;
	
	public LoginDialog(UserVerify service){
		this.service = service;
		initComponent();
	}
	
	public LoginDialog(){
		initComponent();
	}
	
	private void initComponent() {
		this.setSize(500, 300);
		this.setLayout(null);
		this.setBackImage();
		this.setTitle("登录界面");
		
		nameLabel = new JLabel("用户名：");
		nameField = new JTextField();
		pwLabel = new JLabel("密    码：");
		pwField = new JTextField();
		
		loginbButton = new JButton("登陆");
		loginbButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				verify();
			}
		});
		
		registButton = new JButton("注册");
		registButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				register();
			}
		});
		
		exitButton = new JButton("退出");
		exitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int code = JOptionPane.showConfirmDialog(null, "真的要退出吗？", "提示", JOptionPane.YES_NO_OPTION);
				if (code == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		
		nameLabel.setBounds(100, 120, 80, 25);
		nameField.setBounds(170, 120, 220, 25);
		pwLabel.setBounds(100, 160, 80, 25);
		pwField.setBounds(170, 160, 220, 25);
		loginbButton.setBounds(100, 220, 90, 25);
		registButton.setBounds(200, 220, 90, 25);
		exitButton.setBounds(300, 220, 90, 25);
		
		//this.add(titleLabel);
		this.add(nameLabel);
		this.add(nameField);
		this.add(pwLabel);
		this.add(pwField);
		this.add(loginbButton);
		this.add(registButton);
		this.add(exitButton);

		Dimension scrSize=Toolkit.getDefaultToolkit().getScreenSize(); 
		this.setLocation((scrSize.width-this.getSize().width)/2,(scrSize.height-this.getSize().height)/2-10);
		this.setVisible(true);
	}
	
	private void  setBackImage() {
		Container panel = this.getContentPane();
		((JPanel)panel).setOpaque(false);
		JLayeredPane layeredPane = this.getLayeredPane();
		
		Component[]  coms = layeredPane.getComponentsInLayer(new Integer(Integer.MIN_VALUE));
		if (coms.length >0) {
			for (Component component : coms) {
				layeredPane.remove(component);
			}
		}
		
		ImageIcon icon = new ImageIcon(getClass().getResource("res/loginback.png"));
		JLabel label = new JLabel(icon);
		icon.setImage(icon.getImage().getScaledInstance(this.getSize().width, this.getSize().height, Image.SCALE_SMOOTH));
		
		label.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
		layeredPane.add(label, new Integer(Integer.MIN_VALUE));
	}

	public void verify() {
		// TODO Auto-generated method stub			
		String userName =nameField.getText();
		String password = pwField.getText();
		if (userName.isEmpty() || password.isEmpty()) {
			JOptionPane.showConfirmDialog(null, "用户信息输入错误！", "警告", JOptionPane.OK_OPTION);
			return;
		}
			
		if (true/*service.userVerify(userName, password)*/) {
			new MainFrame((DiaryService)service, (DiaryShare)service).show();
			dispose();
		}else {
			JOptionPane.showConfirmDialog(null, "验证失败，请重新输入！", "提示", JOptionPane.YES_OPTION);
			return;
		}
	}
	
	private void register() {
		
	}
	
	public static void main(String[] args) {
		new LoginDialog().show();
	}
}
