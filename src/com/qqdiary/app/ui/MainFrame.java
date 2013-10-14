package com.qqdiary.app.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import com.qqdiary.app.closure.ICallback;
import com.qqdiary.app.module.DayInformationProvider;
import com.qqdiary.app.module.DiaryFolder;
import com.qqdiary.app.module.GeneralDiary;
import com.qqdiary.app.service.DiaryService;
import com.qqdiary.app.service.DiaryShare;
import com.qqdiary.app.ui.manageview.IconImageFactory;
import com.qqdiary.app.ui.manageview.ManageViewPage;
import com.qqdiary.app.ui.searchview.SearchViewPage;
import com.qqdiary.app.ui.welcomeview.WelcomeViewPage;

/**
 * 主窗口类
 * @author Administrator
 *
 */
public class MainFrame extends JFrame {
	/**单例模式的基本信息类，用于保存当前日期已经天气*/
	private DayInformationProvider dateInformation = DayInformationProvider.getInstance();
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
	private ConfigDialog configDialog;
	
	public MainFrame(DiaryService service, DiaryShare share) {
		this.service = service;
		this.share = share;
		
		initComponet();
		
		
	}
	
	/**
	 * 初始化主窗口控件
	 */
	private void initComponet(){
		this.setTitle("清青日记 v1.0");
		this.setSize(1000, 600);
		this.setLocationRelativeTo(null);
		
		initMenuBar();
		initToolBar();
		initViewComponet();
		
		this.setJMenuBar(menuBar);
		this.add(toolBar, BorderLayout.NORTH);
		this.add(viewPanel, BorderLayout.CENTER);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * 初始化菜单
	 */
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
						managepPage.saveFolderItem((DiaryFolder)param);
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
				if(managepPage.getUiFolders().isEmpty()){
					JOptionPane.showMessageDialog( null , "当前无分类，请先添加分类！");
					return;
				}
				
				new DiaryDialog(managepPage.getUiFolders(), null, new ICallback() {
					
					@Override
					public Object callback(Object param) {
						// TODO Auto-generated method stub
						managepPage.saveDiaryItem((GeneralDiary)param);
						return null;
					}
				}).show();
			}
		});
		
		exitMenuItem = new JMenuItem("退出");
		exitMenuItem.setIcon(IconImageFactory.getInstance().createIconByName("exit.png"));
		exitMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				exitSystem();
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
				configDialog.show();
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
	
	/**
	 * 初始化工具栏
	 */
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
				configDialog.show();
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
	
	/**
	 * 初始化显示视图
	 */
	private void initViewComponet() {
		
		welcomePage = new WelcomeViewPage();
		managepPage = new ManageViewPage(service, share);
		searchPage = new SearchViewPage(service);
		aboutDialog = new AboutDialog();
		configDialog = new ConfigDialog();
		

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
	
	/**
	 * 显示关于对话框
	 */
	private void showAboutDialog() {
		aboutDialog.show();
	}
	
	/**
	 * 退出函数
	 */
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
