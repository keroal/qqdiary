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
 * ��������
 * @author Administrator
 *
 */
public class MainFrame extends JFrame {
	/**����ģʽ�Ļ�����Ϣ�࣬���ڱ��浱ǰ�����Ѿ�����*/
	private DayInformationProvider dateInformation = DayInformationProvider.getInstance();
	private DiaryService service;
	private DiaryShare	share;
	
	//�˵�����������
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

	//��������������
	private JToolBar toolBar;
	private JButton manageViewBtn;
	private JButton searchViewBtn;
	private JButton setConfigBtn;
	private JButton aboutDiaryBtn;
	private JButton exitDiaryBtn;
	
	//view��ʾ�ռ䶨��
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
	 * ��ʼ�������ڿؼ�
	 */
	private void initComponet(){
		this.setTitle("�����ռ� v1.0");
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
	 * ��ʼ���˵�
	 */
	private void initMenuBar(){
		menuBar = new JMenuBar();
		fileMenu = new JMenu("�ļ�");
		confMenu = new JMenu("��������");
		managMenu = new JMenu("��ͼ����");
		helpMenu = new JMenu("����");
		
		createFolderItem = new JMenuItem("������");
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
		
		createDiaryItem = new JMenuItem("д��־");
		createDiaryItem.setIcon(IconImageFactory.getInstance().createIconByName("write.png"));
		createDiaryItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(managepPage.getUiFolders().isEmpty()){
					JOptionPane.showMessageDialog( null , "��ǰ�޷��࣬������ӷ��࣡");
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
		
		exitMenuItem = new JMenuItem("�˳�");
		exitMenuItem.setIcon(IconImageFactory.getInstance().createIconByName("exit.png"));
		exitMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				exitSystem();
			}
		});
		
		
		welcomeMenuItem = new JMenuItem("��ӭ��ͼ");
		welcomeMenuItem.setIcon(IconImageFactory.getInstance().createIconByName("welcomview.png"));
		welcomeMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				showWelcomeView();
			}
		});
		
		viewMenuItem = new JMenuItem("������ͼ");
		viewMenuItem.setIcon(IconImageFactory.getInstance().createIconByName("manageview.png"));
		viewMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				showManageView();
			}
		});
		
		searchMenuItem = new JMenuItem("��ѯ��ͼ");
		searchMenuItem.setIcon(IconImageFactory.getInstance().createIconByName("searchview.png"));
		searchMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				showSearchView();
			}
		});
		
		
		
		setConfigMenuItem = new JMenuItem("����");
		setConfigMenuItem.setIcon(IconImageFactory.getInstance().createIconByName("date.png"));
		setConfigMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				configDialog.show();
			}
		});
		
		aboutMenuItem = new JMenuItem("����");
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
	 * ��ʼ��������
	 */
	private void initToolBar(){
		toolBar = new JToolBar();
		//toolBar.setBorder(BorderFactory.createLineBorder(Color.w));
		//toolBar.setFloatable(true);
		
		manageViewBtn = new JButton("������ͼ");
		manageViewBtn.setIcon(IconImageFactory.getInstance().createIconByName("manageview.png"));
		manageViewBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				showManageView();
			}
		});
		
		searchViewBtn = new JButton("��ѯ��ͼ");
		searchViewBtn.setIcon(IconImageFactory.getInstance().createIconByName("searchview.png"));
		searchViewBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				showSearchView();
			}
		});
		
		setConfigBtn = new JButton("����");
		setConfigBtn.setIcon(IconImageFactory.getInstance().createIconByName("date.png"));
		setConfigBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				configDialog.show();
			}
		});
		
		aboutDiaryBtn = new JButton("����");
		aboutDiaryBtn.setIcon(IconImageFactory.getInstance().createIconByName("help.png"));
		aboutDiaryBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				showAboutDialog();
			}
		});
		
		exitDiaryBtn = new JButton("�˳�");
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
	 * ��ʼ����ʾ��ͼ
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
		viewPanel.add("��ӭ��ͼ", welcomePage);
		viewPanel.add("������ͼ", managepPage);
		viewPanel.add("��ѯ��ͼ", searchPage);
		
		showWelcomeView();
	}
	
	private void showWelcomeView() {
		viewLayout.show(viewPanel, "��ӭ��ͼ");
	}
	
	private void showManageView() {
		viewLayout.show(viewPanel, "������ͼ");
	}
	
	private void showSearchView() {
		viewLayout.show(viewPanel, "��ѯ��ͼ");
	}
	
	/**
	 * ��ʾ���ڶԻ���
	 */
	private void showAboutDialog() {
		aboutDialog.show();
	}
	
	/**
	 * �˳�����
	 */
	private void exitSystem() {
		int select = JOptionPane.showConfirmDialog( null , "ȷ��Ҫ�˳���" , "��ʾ", JOptionPane.YES_NO_OPTION );
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
