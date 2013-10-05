package com.qqdiary.app.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import com.qqdiary.app.module.DiaryFolder;
import com.qqdiary.app.module.GeneralDiary;

public class ManageViewPage extends ViewPanel {
	private JList foldersList;
	private DefaultListModel listModel;
	private JPopupMenu folderListPopuoMenu;
	private JMenuItem modifyFolderItem;
	private JMenuItem delFolderItem;
	private JMenuItem reloadListItem;

	private JTable manageTable;
	private DiaryTableModel manageTableModel;
	private JPopupMenu manageTablePopupMenu;
	private JMenuItem viewDiaryItem;
	private JMenuItem delDiaryItem;
	
	
	public ManageViewPage(String name){
		super(name);
		initFolderList();
		initDiaryTable();
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerSize(10);
		splitPane.setResizeWeight(0.1D);
		splitPane.setOneTouchExpandable(true);
	    
		JScrollPane folderScrollPane = new JScrollPane(foldersList);
		folderScrollPane.setBorder(new TitledBorder("分类"));
		splitPane.add(folderScrollPane, "left");

		JScrollPane diaryScrollPane = new JScrollPane(manageTable);
		diaryScrollPane.setBorder(new TitledBorder("日记列表"));
		
		splitPane.add( diaryScrollPane, "right");
		
		this.addComponent(splitPane, "Center");
	}
	
	private void initFolderList() {
		this.setViewLayout(new BorderLayout());
		
		ListCellRenderWithIcon lcr = new ListCellRenderWithIcon();
		listModel = new DefaultListModel();
		foldersList = new JList(listModel);
		foldersList.setCellRenderer(lcr);
		
		
		folderListPopuoMenu = new JPopupMenu();
		modifyFolderItem = new JMenuItem("修改分类");
		modifyFolderItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				addFolderItem(new DiaryFolder("aaaa", "bbbbb"));
			}
		});
		
		delFolderItem = new JMenuItem("删除分类");
		delFolderItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		reloadListItem = new JMenuItem("刷新");
		reloadListItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		folderListPopuoMenu.add(modifyFolderItem);
		folderListPopuoMenu.add(delFolderItem);
		folderListPopuoMenu.add(reloadListItem);
		
		foldersList.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent e) {
			     if (e.isPopupTrigger())
			      showMenu(e);
			}
			public void mouseReleased(MouseEvent e) {
			     if (e.isPopupTrigger())
			      showMenu(e);
			}
			private void showMenu(MouseEvent e) {
			     folderListPopuoMenu.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	
	private void initDiaryTable() {
		manageTableModel = new DiaryTableModel();
		manageTable = new JTable(manageTableModel);
		
		manageTablePopupMenu = new JPopupMenu();
		viewDiaryItem = new JMenuItem("打开日志");
		viewDiaryItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		delDiaryItem = new JMenuItem("删除日志");
		delDiaryItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		manageTablePopupMenu.add(viewDiaryItem);
		manageTablePopupMenu.add(delDiaryItem);
		
		manageTable.addMouseListener(new MouseAdapter() {
			 public void mousePressed(MouseEvent e) {
			     if (e.isPopupTrigger())
			      showMenu(e);
			 }
			 public void mouseReleased(MouseEvent e) {
			     if (e.isPopupTrigger())
			      showMenu(e);
			 }
			 private void showMenu(MouseEvent e) {
				 manageTablePopupMenu.show(e.getComponent(), e.getX(), e.getY());
			 }
		});
	}
	

	private void addFolderItem(DiaryFolder folder){
		listModel.addElement(folder);
		foldersList.updateUI();
	}
	
	private void addDiaryItem(GeneralDiary diary) {
		
	}
}
