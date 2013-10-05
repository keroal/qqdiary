package com.qqdiary.app.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.TitledBorder;

import com.qqdiary.app.closure.Callback;
import com.qqdiary.app.module.CurrentDateInformation;
import com.qqdiary.app.module.DiaryAttachment;
import com.qqdiary.app.module.DiaryFolder;
import com.qqdiary.app.module.GeneralDiary;
import com.qqdiary.app.service.DiaryService;
import com.qqdiary.app.service.DiaryShare;

public class DiaryDialog extends JDialog {
	private DiaryService service;
	private DiaryShare share;
	private GeneralDiary diary;
	private List<DiaryFolder> folderList;
	
	//头部属性参数定义
	private JLabel weatherLabel;
	private JLabel dateLabel;
	private JLabel titleLabel;
	private JLabel folderLabel;
	
	private JComboBox folderComboBox;
	private JTextField titleField;
	
	private JPopupMenu weatherPopupMenu;
	private JMenuItem sunItem;
	private JMenuItem cloudyItem;
	private JMenuItem rainItem;
	private JMenuItem snowItem;
	
	//日记内容和附件空间定义
	private JEditorPane docPanel;
	
	private JList attachList;
	private DefaultListModel listModel;
	
	//工具栏参数定义
	private JButton storeButton;
	private JButton cancelButton;
	private	JButton addPicButton;
	private JButton addVoiButton;
	private JButton clearButton;
	private JButton modifyButton;
	
	
	public DiaryDialog(DiaryService serivce, DiaryShare share, GeneralDiary dariy) {
		// TODO Auto-generated constructor stub
		this.service = serivce;
		this.share = share;
		this.diary = diary;
		
		initFolderList();
		initComponet();
		if (this.diary != null) {
			initCompValue(this.diary);
		}else {
			initCompValue();
		}
	}
	
	private void initFolderList(){
		//folderList = service.getDiaryFolderList();
		folderList = new ArrayList<DiaryFolder>();
		folderList.add(new DiaryFolder("nihao", "bbbbb"));
	}
	
	private void initComponet() {
		setSize(900, 480);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		
		JPanel panel = new JPanel(new GridLayout(2, 1));
		panel.add(createDatePanel());
		panel.add(createTitlePanel());
		
		this.add(panel, "North");
		this.add(createContentPanel(), "Center");
		this.add(createToolbarPanel(), "South");
		
	}
	
	private JPanel createDatePanel(){
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(0, 24));
		panel.setMinimumSize(new Dimension(0, 24));
		panel.setLayout(new BorderLayout());
		panel.setBackground(Color.blue);
		
		dateLabel = new JLabel("日期");
		dateLabel.setForeground(Color.white);
		dateLabel.setHorizontalAlignment(2);
		
		weatherLabel = new JLabel("天气");
		weatherLabel.setForeground(Color.white);
		weatherLabel.setHorizontalAlignment(0);
		weatherLabel.setMaximumSize(new Dimension(80, 0));
		weatherLabel.setMinimumSize(new Dimension(80, 0));
		weatherLabel.setPreferredSize(new Dimension(60, 0));
		weatherLabel.setToolTipText("设置天气");
		
		weatherPopupMenu = new JPopupMenu();
		sunItem = new JMenuItem(IconImageFactory.WEATHER_TYPES[0]);
	    cloudyItem = new JMenuItem(IconImageFactory.WEATHER_TYPES[1]);
	    rainItem = new JMenuItem(IconImageFactory.WEATHER_TYPES[2]);
	    snowItem = new JMenuItem(IconImageFactory.WEATHER_TYPES[3]);
	    sunItem.setIcon(IconImageFactory.getInstance().createIconByType(IconImageFactory.WEATHER_TYPES[0]));
	    cloudyItem.setIcon(IconImageFactory.getInstance().createIconByType(IconImageFactory.WEATHER_TYPES[1]));
	    rainItem.setIcon(IconImageFactory.getInstance().createIconByType(IconImageFactory.WEATHER_TYPES[2]));
	    snowItem.setIcon(IconImageFactory.getInstance().createIconByType(IconImageFactory.WEATHER_TYPES[3]));
	    
	    sunItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				setUIWeather(IconImageFactory.WEATHER_TYPES[0]);
			}
		});
	    
	    cloudyItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				setUIWeather(IconImageFactory.WEATHER_TYPES[1]);
			}
		});
 
	    rainItem.addActionListener(new ActionListener() {
		
	    	@Override
	    	public void actionPerformed(ActionEvent arg0) {
	    		// TODO Auto-generated method stub
	    		setUIWeather(IconImageFactory.WEATHER_TYPES[2]);
	    	}
	    });
 
	    snowItem.addActionListener(new ActionListener() {
		
	    	@Override
	    	public void actionPerformed(ActionEvent arg0) {
	    		// TODO Auto-generated method stub
	    		setUIWeather(IconImageFactory.WEATHER_TYPES[3]);
	    	}
	    });
	    
	    weatherPopupMenu.add(sunItem);
	    weatherPopupMenu.add(cloudyItem);
	    weatherPopupMenu.add(rainItem);
	    weatherPopupMenu.add(snowItem);
	   
		weatherLabel.addMouseListener(new MouseListener() {
			
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
				weatherPopupMenu.show(e.getComponent(), e.getX(), e.getY());
			}
		});
		
		
		panel.add(dateLabel, "Center");
		panel.add(weatherLabel, "East");
		return panel;
	}
	
	private JPanel createTitlePanel() {
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(0, 30));
		panel.setLayout(new BorderLayout());
		
		JPanel tPanel = new JPanel(new BorderLayout());
		titleLabel = new JLabel("日记标题：");
		titleLabel.setHorizontalAlignment(2);
		titleField = new JTextField();
		tPanel.add(titleField, "Center");
		tPanel.add(titleLabel, "West");
		
		JPanel jPanel = new JPanel(new BorderLayout());
		jPanel.setPreferredSize(new Dimension(200, 0));
		folderLabel = new JLabel("分类：");
		folderComboBox = new JComboBox();
		for (DiaryFolder folder : folderList) {
			folderComboBox.addItem(folder);
		}
		jPanel.add(folderLabel, "West");
		jPanel.add(folderComboBox, "Center");
		
		panel.add(tPanel, "Center");
		panel.add(jPanel, "East");
		return panel;
	}
	
	private JPanel createContentPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		
		JPanel leftPanel = new JPanel(new BorderLayout());
		docPanel = new JEditorPane();
		leftPanel.setBorder(new TitledBorder("内容"));
		leftPanel.add(new JScrollPane(docPanel), "Center");
		
		JPanel rightPanel = new JPanel(new BorderLayout());
		rightPanel.setPreferredSize(new Dimension(200, 0));
		rightPanel.setBorder(new TitledBorder("附件"));
		listModel = new DefaultListModel();
		attachList = new JList(listModel);
		ListCellRenderWithIcon lcr = new ListCellRenderWithIcon();
		attachList.setCellRenderer(lcr);
		rightPanel.add(new JScrollPane(attachList), "Center");
	    
		panel.add(leftPanel, "Center");
		panel.add(rightPanel, "East");
		return panel;
	}
	
	private JPanel createToolbarPanel(){
		JPanel panel = new JPanel(new BorderLayout());
		//panel.setPreferredSize(new Dimension(200, 0));
		
		JPanel rightPanel = new JPanel();
		storeButton = new JButton("保存");
		storeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		clearButton = new JButton("清空");
		clearButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		cancelButton = new JButton("退出");
		cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int select = JOptionPane.showConfirmDialog( null , "确定要退出！" , "提示", JOptionPane.YES_NO_OPTION );
				if (select == JOptionPane.YES_OPTION) {
					dispose();
				}
			}
		});
		
		addPicButton = new JButton("添加图片");
		addPicButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		addVoiButton = new JButton("添加语音");
		addVoiButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new VoiceRecorder(new Callback() {
					
					@Override
					protected Object callbackFunc(Object param) {
						// TODO Auto-generated method stub
						return null;
					}
				}).show();
			}
		});
		
		
		
		
		rightPanel.add(addPicButton);
		rightPanel.add(addVoiButton);
		rightPanel.add(clearButton);
		rightPanel.add(storeButton);
		rightPanel.add(cancelButton);
		
		JPanel leftPanel = new JPanel();
		modifyButton = new JButton("编辑");
		leftPanel.add(modifyButton);
		
		panel.add(rightPanel, "East");
		panel.add(leftPanel, "West");
		return panel;
	}
	

	private void initCompValue(){
		setUITime(CurrentDateInformation.getInstance().getDateTime());
		setUIWeather(CurrentDateInformation.getInstance().getWeather());
	}
	
	private void initCompValue(GeneralDiary diary){
		setUITime(diary.getDate());
		setUIWeather(diary.getWeather());
		setUITitle(diary.getTitle());
		setUIFolder(diary.getFolder());
		setUIContent(diary.getContent());
		if (diary.isHasAttachement()) {
			setUIAttachments(diary.getAttachmentList());
		}
		
	}
	
	private void assembleDiary(){
		diary.setDate(getUITime());
		diary.setWeather(getUIWeather());
		diary.setTitle(getUITitle());
		diary.setFolder(getUIFolder());
		diary.setContent(getUIContent());
		if (listModel.isEmpty()) {
			diary.setHasAttachement(false);
		}else {
			diary.setHasAttachement(true);
		}
		
	}
	
	public void setUITime(String date){
		this.dateLabel.setText(date);
	}
	
	public String getUITime(){
		return this.dateLabel.getText();
	}
	
	public void setUIWeather(String weather){
		this.weatherLabel.setText(weather);
		this.weatherLabel.setIcon(IconImageFactory.getInstance().createIconByType(weather));
		
	}
	
	public String getUIWeather(){
		return this.weatherLabel.getText();
	}
	
	public void setUITitle(String title){
		this.titleField.setText(title);
	}
	
	public String getUITitle(){
		return this.titleField.getText();
	}
	
	public void setUIFolder(DiaryFolder folder){
		folderComboBox.setSelectedItem(folder);
	}
	
	public DiaryFolder getUIFolder(){
		return (DiaryFolder)folderComboBox.getSelectedItem();
	}
	
	public void setUIContent(String content){
		this.docPanel.setText(content);
	}
	
	public String getUIContent(){
		return this.docPanel.getText();
	}
	
	public void setUIAttachments(List<DiaryAttachment> attachments) {
		
	}
	
	public List<DiaryAttachment> getUIAttachments(){
		return null;
	}
	
	public void setUILock(boolean lock) {
		this.titleField.setEditable(lock);
		this.folderComboBox.setEnabled(lock);
		this.docPanel.setEditable(lock);
		
		this.addPicButton.setEnabled(lock);
		this.addVoiButton.setEnabled(lock);
		this.clearButton.setEnabled(lock);
		this.storeButton.setEnabled(lock);
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
	}
}
