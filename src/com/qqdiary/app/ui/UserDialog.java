package com.qqdiary.app.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.eltima.components.ui.DatePicker;
import com.qqdiary.app.closure.ICallback;
import com.qqdiary.app.module.User;
import com.qqdiary.app.service.DiaryService;
import com.qqdiary.app.service.DiaryShare;
import com.qqdiary.app.service.UserVerify;

/**
 * 登陆界面对话框类
 * @author Administrator
 *
 */
public class UserDialog extends JDialog{
	private UserVerify service;
	
	//登陆页面控件
	private JPanel loginPanel;
	private JLabel logNameLabel;
	private JTextField logNameField;
	private JLabel logPWLabel;
	private JTextField logPWField;
	private	JButton loginbButton;
	private JButton exitButton;
	private JLabel registLabel;
	
	//注册页面控件
	private JPanel registPanel;
	private JLabel regNameLabel;
	private JTextField regNameField;
	private JLabel regPWLabel;
	private JTextField regPWField;
	private JLabel regRPWLabel;
	private JTextField regRPWField;
	private JLabel regSexLabel;
	private JRadioButton maleRadioButton;
	private JRadioButton femaleRadioButton;
	private JLabel regBirthdayLabel;
	private DatePicker regBirthdayPicker;
	private JButton registButton;
	
	private CardLayout mainLayout;
	
	private User loginUser;
	private ICallback callback;
	
	public UserDialog(UserVerify service, ICallback callback){
		this.service = service;
		this.callback = callback;
		initComponent();
	}
	
	public UserDialog(){
		initComponent();
	}
	
	private void initComponent(){
		
		initLoginPage();
		initRegistPage();
		
		mainLayout = new CardLayout();
		this.setLayout(mainLayout);
		this.add("登陆", loginPanel);
		this.add("注册", registPanel);
		
		showLoginPage();
	}
	
	/**
	 * 登陆界面控件初始化
	 */
	private void initLoginPage() {
		
		logNameLabel = new JLabel("用户名：");
		logNameField = new JTextField();
		logPWLabel = new JLabel("密    码：");
		logPWField = new JTextField();
		
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
		
		registLabel = new JLabel("<html><u>注       册</u></html>");
		registLabel.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				showRegistPage();
			}
		});
		
		logNameLabel.setBounds(100, 120, 80, 25);
		logNameField.setBounds(170, 120, 220, 25);
		logPWLabel.setBounds(100, 160, 80, 25);
		logPWField.setBounds(170, 160, 220, 25);
		loginbButton.setBounds(100, 220, 90, 25);
		registButton.setBounds(200, 220, 90, 25);
		exitButton.setBounds(300, 220, 90, 25);
		registLabel.setBounds(330,220, 90, 25);
		
		
		loginPanel = new JPanel();
		loginPanel.setLayout(null);
		//setBackImage(loginPanel);
		
		loginPanel.add(logNameLabel);
		loginPanel.add(logNameField);
		loginPanel.add(logPWLabel);
		loginPanel.add(logPWField);
		loginPanel.add(loginbButton);
		loginPanel.add(registButton);
		loginPanel.add(exitButton);
		loginPanel.add(registLabel);

		Dimension scrSize=Toolkit.getDefaultToolkit().getScreenSize(); 
		this.setLocation((scrSize.width-this.getSize().width)/2,(scrSize.height-this.getSize().height)/2-10);
		this.setVisible(true);
	}
	
	private void initRegistPage(){
		registPanel = new JPanel();
		
		regNameLabel = new JLabel("昵        称：");
		regNameField = new JTextField();
		JPanel namePanel = new JPanel(new BorderLayout());
		namePanel.add(regNameLabel, "West");
		namePanel.add(regNameField, "Center");
		
		regPWLabel = new JLabel("密        码：");
		regPWField = new JTextField();
		JPanel pwPanel = new JPanel(new BorderLayout());
		pwPanel.add(regPWLabel, "West");
		pwPanel.add(regPWField, "Center");
		
		regRPWLabel = new JLabel("重复密码：");
		regRPWField = new JTextField();
		JPanel rpwPanel = new JPanel(new BorderLayout());
		rpwPanel.add(regRPWLabel, "West");
		rpwPanel.add(regRPWField, "Center");
		
		regSexLabel = new JLabel("性        别：");
		maleRadioButton = new JRadioButton("男");
		femaleRadioButton = new JRadioButton("女");
		ButtonGroup radioGroup = new ButtonGroup();
		radioGroup.add(maleRadioButton);
		radioGroup.add(femaleRadioButton);
		JPanel radioPanel = new JPanel(new GridLayout(1, 2));
		radioPanel.add(maleRadioButton);
		radioPanel.add(femaleRadioButton);
		JPanel sexPanel = new JPanel(new BorderLayout());
		sexPanel.add(regSexLabel, "West");
		sexPanel.add(radioPanel, "Center");
		
		regBirthdayLabel = new JLabel("生        日：");
		regBirthdayPicker = new DatePicker();
		JPanel birthdayPanel = new JPanel(new BorderLayout());
		birthdayPanel.add(regBirthdayLabel, "West");
		birthdayPanel.add(regBirthdayPicker, "Center");
		
		registButton = new JButton("注册");
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(registButton);
		buttonPanel.add(exitButton);
		
		JPanel sizePanel = new JPanel(new GridLayout(6,1));
		sizePanel.setBounds(0, 0, 200, 200);
		sizePanel.add(namePanel);
		sizePanel.add(pwPanel);
		sizePanel.add(rpwPanel);
		sizePanel.add(sexPanel);
		sizePanel.add(birthdayPanel);
		sizePanel.add(birthdayPanel);
		sizePanel.add(buttonPanel);
		
		registButton.setLayout(null);
		registPanel.add(sizePanel);
	}
	
	private void showLoginPage(){
		this.setTitle("登陆页面");
		this.setSize(500,300);
		mainLayout.show(this.getContentPane(), "登陆");
	}
	
	private void showRegistPage(){
		this.setTitle("注册页面");
		this.setSize(300, 300);
		mainLayout.show(this.getContentPane(), "注册");
	}
	
	private void  setBackImage(JPanel panel) {
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

	/**
	 * 用户验证函数
	 */
	private void verify() {
		// TODO Auto-generated method stub	
		if (!userInformationAssertion()) {
			return;
		}
		
		if (true/*service.userVerify(loginUser)*/) {
			new MainFrame((DiaryService)service, (DiaryShare)service).show();
			dispose();
		}else {
			JOptionPane.showConfirmDialog(null, "验证失败，请重新输入！", "提示", JOptionPane.YES_OPTION);
			return;
		}
	}
	/**
	 * 登陆信息有效性验证
	 * @return
	 */
	private boolean userInformationAssertion(){
		loginUser = new User();
		loginUser.setUsername(logNameField.getText());
		loginUser.setPassword(logPWField.getText());
		
		
		/**用户信息不能为空*/
		if (loginUser.getUserName().isEmpty() || loginUser.getPassword().isEmpty()) {
			JOptionPane.showConfirmDialog(null, "用户信息输入错误！", "警告", JOptionPane.OK_OPTION);
			return false;
		}
		
		if (loginUser.getUserName().length() > 20) {
			JOptionPane.showConfirmDialog(null, "用户名超长，无效！", "警告", JOptionPane.OK_OPTION);
			return false;
		}
		
		return true;
	}
	
	
	/**
	 * 用户注册
	 */
	private void register() {
		
	}
	
	public static void main(String[] args) {
		new UserDialog().show();
	}
}
