package com.qqdiary.app.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.TitledBorder;

import com.qqdiary.app.closure.ICallback;
import com.qqdiary.app.module.CurrentDateInformation;
import com.qqdiary.app.module.DiaryFolder;
import com.qqdiary.app.module.GeneralDiary;
import com.qqdiary.app.service.DiaryService;
import com.qqdiary.app.service.DiaryShare;
import com.qqdiary.app.ui.ListCellRenderWithIcon;

public class MainFrame extends JFrame {
	private CurrentDateInformation dateInformation = CurrentDateInformation.getInstance();
	private DiaryService service;
	private DiaryShare	share;
	
	//菜单栏参数定义
	private JMenuBar menuBar;
	private JMenu 	fileMenu;
	private JMenu	managMenu;
	private JMenu	confMenu;
	private JMenu	helpMenu;
	private JMenuItem createFolderItem;
	private JMenuItem createDiaryItem;
	private JMenuItem exitMenuItem;
	private JMenuItem welcomeMenuItem;
	private JMenuItem viewMenuItem;
	private JMenuItem searchMenuItem;
	private JMenuItem setConfigMenuItem;
	private JMenuItem aboutMenuItem;

	//工具栏参数定义
	private JToolBar toolBar;
	private JButton manageViewBtn;
	private JButton searchViewBtn;
	private JButton setConfigBtn;
	private JButton aboutDiaryBtn;
	private JButton exitDiaryBtn;
	
	//view显示空间定义
	private CardLayout viewLayout;
	private JPanel viewPanel;
	private WelcomeViewPage welcomePage;
	private ManageViewPage managepPage;
	private SearchViewPage searchPage;
	private AboutDialog aboutDialog;
	
	public MainFrame(DiaryService service, DiaryShare share) {
		this.service = service;
		this.share = share;
		
		initComponet();
		
		
	}
	
	private void initComponet(){
		this.setTitle("QQ 日记");
		this.setSize(800, 500);
		this.setLocationRelativeTo(null);
		
		initMenuBar();
		initToolBar();
		initViewComponet();
		
		this.setJMenuBar(menuBar);
		this.add(toolBar, BorderLayout.NORTH);
		this.add(viewPanel, BorderLayout.CENTER);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void initMenuBar(){
		menuBar = new JMenuBar();
		fileMenu = new JMenu("文件");
		confMenu = new JMenu("参数设置");
		managMenu = new JMenu("视图管理");
		helpMenu = new JMenu("帮助");
		
		createFolderItem = new JMenuItem("建分类");
		createFolderItem.setIcon(IconImageFactory.getInstance().createIconByName("folder.png"));
		createFolderItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new FolderDialog(new ICallback() {
					
					@Override
					public Object callback(Object param) {
						// TODO Auto-generated method stub
						return null;
					}
				}).show();
			}
		});
		
		createDiaryItem = new JMenuItem("写日志");
		createDiaryItem.setIcon(IconImageFactory.getInstance().createIconByName("write.png"));
		createDiaryItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new DiaryDialog((DiaryService)service, (DiaryShare)service, null).show();
			}
		});
		
		exitMenuItem = new JMenuItem("退出");
		exitMenuItem.setIcon(IconImageFactory.getInstance().createIconByName("exit.png"));
		exitMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				showWelcomeView();
			}
		});
		
		
		welcomeMenuItem = new JMenuItem("欢迎视图");
		welcomeMenuItem.setIcon(IconImageFactory.getInstance().createIconByName("welcomview.png"));
		welcomeMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				showWelcomeView();
			}
		});
		
		viewMenuItem = new JMenuItem("管理视图");
		viewMenuItem.setIcon(IconImageFactory.getInstance().createIconByName("manageview.png"));
		viewMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				showManageView();
			}
		});
		
		searchMenuItem = new JMenuItem("查询视图");
		searchMenuItem.setIcon(IconImageFactory.getInstance().createIconByName("searchview.png"));
		searchMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				showSearchView();
			}
		});
		
		
		
		setConfigMenuItem = new JMenuItem("设置");
		setConfigMenuItem.setIcon(IconImageFactory.getInstance().createIconByName("date.png"));
		setConfigMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		aboutMenuItem = new JMenuItem("关于");
		aboutMenuItem.setIcon(IconImageFactory.getInstance().createIconByName("help.png"));
		aboutMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new AboutDialog().show();
			}
		});
		

		fileMenu.add(createDiaryItem);
		fileMenu.add(createFolderItem);
		fileMenu.add(exitMenuItem);
		
		managMenu.add(welcomeMenuItem);
		managMenu.add(viewMenuItem);
		managMenu.add(searchMenuItem);
		
		confMenu.add(setConfigMenuItem);
		
		helpMenu.add(aboutMenuItem);
		
		menuBar.add(fileMenu);
		menuBar.add(fileMenu);
		menuBar.add(confMenu);
		menuBar.add(managMenu);
		menuBar.add(helpMenu);
	}
	
	private void initToolBar(){
		toolBar = new JToolBar();
		//toolBar.setBorder(BorderFactory.createLineBorder(Color.w));
		//toolBar.setFloatable(true);
		
		manageViewBtn = new JButton("管理视图");
		manageViewBtn.setIcon(IconImageFactory.getInstance().createIconByName("manageview.png"));
		manageViewBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				showManageView();
			}
		});
		
		searchViewBtn = new JButton("查询视图");
		searchViewBtn.setIcon(IconImageFactory.getInstance().createIconByName("searchview.png"));
		searchViewBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				showSearchView();
			}
		});
		
		setConfigBtn = new JButton("设置");
		setConfigBtn.setIcon(IconImageFactory.getInstance().createIconByName("date.png"));
		setConfigBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		aboutDiaryBtn = new JButton("关于");
		aboutDiaryBtn.setIcon(IconImageFactory.getInstance().createIconByName("help.png"));
		aboutDiaryBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				showAboutDialog();
			}
		});
		
		exitDiaryBtn = new JButton("退出");
		exitDiaryBtn.setIcon(IconImageFactory.getInstance().createIconByName("exit.png"));
		exitDiaryBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				exitSystem();
			}
		});
		
		toolBar.add(manageViewBtn, null);
		toolBar.add(searchViewBtn, null);
		toolBar.addSeparator();
		toolBar.add(setConfigBtn, null);
		toolBar.add(aboutDiaryBtn, null);
		toolBar.addSeparator();
		toolBar.add(exitDiaryBtn, null);
	}
	
	private void initViewComponet() {
		
		welcomePage = new WelcomeViewPage("");
		managepPage = new ManageViewPage("");
		searchPage = new SearchViewPage("");
		aboutDialog = new AboutDialog();
		

		viewLayout = new CardLayout();
		viewPanel = new JPanel();
		viewPanel.setLayout(viewLayout);
		viewPanel.add("欢迎视图", welcomePage);
		viewPanel.add("管理视图", managepPage);
		viewPanel.add("查询视图", searchPage);
		
		showWelcomeView();
	}
	
	private void showWelcomeView() {
		viewLayout.show(viewPanel, "欢迎视图");
	}
	
	private void showManageView() {
		viewLayout.show(viewPanel, "管理视图");
	}
	
	private void showSearchView() {
		viewLayout.show(viewPanel, "查询视图");
	}
	
	private void showAboutDialog() {
		aboutDialog.show();
	}
	
	private void exitSystem() {
		int select = JOptionPane.showConfirmDialog( null , "确定要退出！" , "提示", JOptionPane.YES_NO_OPTION );
		if (select == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
	
	public DiaryService getService() {
		return service;
	}

	public void setService(DiaryService service) {
		this.service = service;
	}

	public DiaryShare getShare() {
		return share;
	}

	public void setShare(DiaryShare share) {
		this.share = share;
	}
}
