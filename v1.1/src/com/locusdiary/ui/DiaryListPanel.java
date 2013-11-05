package com.locusdiary.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;

import com.locusdiary.closure.ICallback;
import com.locusdiary.module.GeneralDiary;
import com.locusdiary.ui.module.DiaryComparatorByTime;
import com.locusdiary.ui.module.DiaryComparatorByTitle;
import com.locusdiary.ui.module.DiaryListCellRender;
import com.locusdiary.ui.module.PopupMenuLabel;
import com.locusdiary.ui.module.SortedListModel;
import com.locusdiary.ui.module.SortedListModel.SortOrder;

public class DiaryListPanel extends JPanel {
	private PopupMenuLabel sortLabel;
	private JMenuItem titleSortItem;
	private JMenuItem timeSortItem;
	private JList diaryItemsList;
	private DefaultListModel diaryItemsModel;
	private SortedListModel sortedListModel;
	private boolean sortStatus;
	
	private ICallback modifyCallback;
	private ICallback showDetialCallback;
		
	/** 
	* Name:			initComponents；
	* Description : 默认构造函数 ；
	*/ 
	public DiaryListPanel(ICallback modifyCallback, ICallback showCallback ){
		this.modifyCallback = modifyCallback;
		this.showDetialCallback = showCallback;
		sortStatus = false;
		
		setPreferredSize(new Dimension(450,1000));
		setBorder(new TitledBorder(""));
		setLayout(new BorderLayout());
		
		initComponents();
	}
	
	/** 
	* Name:			initComponents；
	* Description : 初始化界面控件 ；
	* @param:		void ；
	* @return 		void；
	*/ 
	private void initComponents(){
		sortLabel = new PopupMenuLabel("按照时间排序");
		JPopupMenu sortMenu = new JPopupMenu();
		titleSortItem = new JMenuItem("按标题排序");
		titleSortItem.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
                titleSortListMouseClicked(evt);
            }
		});
		
		timeSortItem = new JMenuItem("按时间排序");
		timeSortItem.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
                timeSortListMouseClicked(evt);
            }
		});
		
		sortMenu.add(titleSortItem);
		sortMenu.add(timeSortItem);
		sortLabel.addMenu(sortMenu);
		
		DiaryListCellRender listCellRender = new DiaryListCellRender();
		diaryItemsModel = new DefaultListModel();
		sortedListModel = new SortedListModel(diaryItemsModel, SortOrder.UNORDERED);
		diaryItemsList = new JList(sortedListModel);
		diaryItemsList.setCellRenderer(listCellRender);
		diaryItemsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//添加双击事件
		diaryItemsList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                jListMouseClicked(evt);
            }
        });
		
	
		JScrollPane listScrollPane = new JScrollPane(diaryItemsList);
		listScrollPane.setBorder(new TitledBorder(""));
		add(sortLabel, BorderLayout.NORTH);
		add(listScrollPane, BorderLayout.CENTER);
	}
	
	private void timeSortListMouseClicked(MouseEvent evt) {
		DiaryComparatorByTime comparator = new DiaryComparatorByTime();
		sortedListModel.setComparator(comparator);
		if (sortStatus) {
			sortedListModel.setSortOrder(SortOrder.ASCENDING);
			sortStatus = false;
		}else {
			sortedListModel.setSortOrder(SortOrder.DESCENDING);
			sortStatus = true;
		}
	}
	
	private void titleSortListMouseClicked(MouseEvent evt) {
		DiaryComparatorByTitle comparator = new DiaryComparatorByTitle();
		sortedListModel.setComparator(comparator);
		if (sortStatus) {
			sortedListModel.setSortOrder(SortOrder.ASCENDING);
			sortStatus = false;
		}else {
			sortedListModel.setSortOrder(SortOrder.DESCENDING);
			sortStatus = true;
		}
	}
	
	/** 
	* Name:			clearDiaryListItems；
	* Description : 清空JList控件内容 ；
	* @param:		void ；
	* @return 		void；
	*/ 
	private void clearDiaryListItems() {
		diaryItemsModel.removeAllElements();
	}
	
	/** 
	* Name:			jListMouseClicked；
	* Description : 响应JList双击事件 ；
	* @param:		MouseEvent MouseEventev ；
	* @return 		void；
	*/ 
	private void jListMouseClicked(MouseEvent evt) {                                    
        // TODO add your handling code here:
		if (evt.getClickCount() == 1) {
			showDetialCallback.callback(null);
		}if (evt.getClickCount() == 2) {
			modifyCallback.callback(null);
		}
	}  

	/** 
	* Name:			showDiariesDescription；
	* Description : 显示日记描述信息 ；
	* @param:		List<GeneralDiary> diaryList；
	* @return 		void；
	*/ 
	public void showDiariesDescription(List<GeneralDiary> diaryList) {
		clearDiaryListItems();
		for (GeneralDiary diary : diaryList) {
			diaryItemsModel.addElement(diary);
		}
		diaryItemsList.updateUI();
	}
	
	/** 
	* Name:			getSelectedDiary；
	* Description : 获取选中的JList数据对象 ；
	* @param:		void；
	* @return 		GeneralDiary；
	*/ 
	public GeneralDiary getSelectedDiary(){
		return (GeneralDiary)diaryItemsList.getSelectedValue();
	}
	
	public GeneralDiary getFirstDiary() {
		if (diaryItemsModel.getSize()>0) {
			diaryItemsList.setSelectedIndex(0);
			return (GeneralDiary)diaryItemsModel.get(0);	
		}
		
		return null;
	}
	
	
	/** 
	* Name:			deleteSelectedDiary；
	* Description : 删除选择JList数据对象 ；
	* @param:		void；
	* @return 		GeneralDiary；
	*/ 
	public void deleteSelectedDiary() {
		diaryItemsModel.removeElement(getSelectedDiary());
	}
	
	public void addDiaryItem(GeneralDiary diary) {
		diaryItemsModel.addElement(diary);
		diaryItemsList.updateUI();
		
	}
}
