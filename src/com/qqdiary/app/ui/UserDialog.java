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
 * ��½����Ի�����
 * @author Administrator
 *
 */
public class UserDialog extends JDialog{
	private UserVerify service;
	
	//��½ҳ��ؼ�
	private JPanel loginPanel;
	private JLabel logNameLabel;
	private JTextField logNameField;
	private JLabel logPWLabel;
	private JTextField logPWField;
	private	JButton loginbButton;
	private JButton exitButton;
	private JLabel registLabel;
	
	//ע��ҳ��ؼ�
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
		this.add("��½", loginPanel);
		this.add("ע��", registPanel);
		
		showLoginPage();
	}
	
	/**
	 * ��½����ؼ���ʼ��
	 */
	private void initLoginPage() {
		
		logNameLabel = new JLabel("�û�����");
		logNameField = new JTextField();
		logPWLabel = new JLabel("��    �룺");
		logPWField = new JTextField();
		
		loginbButton = new JButton("��½");
		loginbButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				verify();
			}
		});
		
		registButton = new JButton("ע��");
		registButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				register();
			}
		});
		
		exitButton = new JButton("�˳�");
		exitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int code = JOptionPane.showConfirmDialog(null, "���Ҫ�˳���", "��ʾ", JOptionPane.YES_NO_OPTION);
				if (code == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		
		registLabel = new JLabel("<html><u>ע       ��</u></html>");
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
		
		regNameLabel = new JLabel("��        �ƣ�");
		regNameField = new JTextField();
		JPanel namePanel = new JPanel(new BorderLayout());
		namePanel.add(regNameLabel, "West");
		namePanel.add(regNameField, "Center");
		
		regPWLabel = new JLabel("��        �룺");
		regPWField = new JTextField();
		JPanel pwPanel = new JPanel(new BorderLayout());
		pwPanel.add(regPWLabel, "West");
		pwPanel.add(regPWField, "Center");
		
		regRPWLabel = new JLabel("�ظ����룺");
		regRPWField = new JTextField();
		JPanel rpwPanel = new JPanel(new BorderLayout());
		rpwPanel.add(regRPWLabel, "West");
		rpwPanel.add(regRPWField, "Center");
		
		regSexLabel = new JLabel("��        ��");
		maleRadioButton = new JRadioButton("��");
		femaleRadioButton = new JRadioButton("Ů");
		ButtonGroup radioGroup = new ButtonGroup();
		radioGroup.add(maleRadioButton);
		radioGroup.add(femaleRadioButton);
		JPanel radioPanel = new JPanel(new GridLayout(1, 2));
		radioPanel.add(maleRadioButton);
		radioPanel.add(femaleRadioButton);
		JPanel sexPanel = new JPanel(new BorderLayout());
		sexPanel.add(regSexLabel, "West");
		sexPanel.add(radioPanel, "Center");
		
		regBirthdayLabel = new JLabel("��        �գ�");
		regBirthdayPicker = new DatePicker();
		JPanel birthdayPanel = new JPanel(new BorderLayout());
		birthdayPanel.add(regBirthdayLabel, "West");
		birthdayPanel.add(regBirthdayPicker, "Center");
		
		registButton = new JButton("ע��");
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
		this.setTitle("��½ҳ��");
		this.setSize(500,300);
		mainLayout.show(this.getContentPane(), "��½");
	}
	
	private void showRegistPage(){
		this.setTitle("ע��ҳ��");
		this.setSize(300, 300);
		mainLayout.show(this.getContentPane(), "ע��");
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
	 * �û���֤����
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
			JOptionPane.showConfirmDialog(null, "��֤ʧ�ܣ����������룡", "��ʾ", JOptionPane.YES_OPTION);
			return;
		}
	}
	/**
	 * ��½��Ϣ��Ч����֤
	 * @return
	 */
	private boolean userInformationAssertion(){
		loginUser = new User();
		loginUser.setUsername(logNameField.getText());
		loginUser.setPassword(logPWField.getText());
		
		
		/**�û���Ϣ����Ϊ��*/
		if (loginUser.getUserName().isEmpty() || loginUser.getPassword().isEmpty()) {
			JOptionPane.showConfirmDialog(null, "�û���Ϣ�������", "����", JOptionPane.OK_OPTION);
			return false;
		}
		
		if (loginUser.getUserName().length() > 20) {
			JOptionPane.showConfirmDialog(null, "�û�����������Ч��", "����", JOptionPane.OK_OPTION);
			return false;
		}
		
		return true;
	}
	
	
	/**
	 * �û�ע��
	 */
	private void register() {
		
	}
	
	public static void main(String[] args) {
		new UserDialog().show();
	}
}
