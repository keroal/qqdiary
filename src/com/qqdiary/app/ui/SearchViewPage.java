package com.qqdiary.app.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class SearchViewPage extends ViewPanel {
	private JPanel paramPanel;
	private JLabel titleLabel;
	private JLabel timeLabel;
	private JLabel tagLabel;
	private JLabel keywordLabel;
	private JTextField titleField;
	private JTextField beginTimeField;
	private JTextField endTimeField;
	private JTextField keywordFiled;
	
	private JButton searchButton;
	private JButton resetButton;
	private DiaryTableModel searchTableModel;
	private JTable searchTable;
	private JPopupMenu searchPopupMenu;
	private JMenuItem viewDiaryItem;
	private JMenuItem delDiaryItem;

	public SearchViewPage(String name){
		super(name);
		this.setViewLayout(new BorderLayout());
		initParamPanel();
		initContentTable();
		
		JScrollPane tScrollPane = new JScrollPane(searchTable);
		tScrollPane.setBorder(new TitledBorder("查询结果"));
		
		JSplitPane splitPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, paramPanel, tScrollPane);
		splitPanel.setOneTouchExpandable(true);
		this.addComponent(splitPanel, "Center");			
	}
	
	private void initParamPanel() {
		
		JPanel buttonPanel = new JPanel(new GridLayout(1,2));
		buttonPanel.setPreferredSize(new Dimension(200,20));
		searchButton = new JButton("查询");
		searchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		resetButton = new JButton("清空");
		resetButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		buttonPanel.add(searchButton);
		buttonPanel.add(resetButton);
		
		JPanel titlePanel = new JPanel(new BorderLayout());
		titlePanel.setPreferredSize(new Dimension(0,20));
		titleLabel = new JLabel("标    题：");
		titleField = new JTextField();
		titlePanel.add(titleLabel, "West");
		titlePanel.add(titleField, "Center");
		titlePanel.add(buttonPanel, "East");
		
		JPanel timePanel = new JPanel(new BorderLayout());
		timePanel.setPreferredSize(new Dimension(0,20));
		timeLabel = new JLabel("时    间：");
		
		JPanel panel = new JPanel(new GridLayout(1,0));
		tagLabel = new JLabel("---------");
		beginTimeField = new JTextField();
		endTimeField = new JTextField();
		panel.add(beginTimeField);
		JPanel opanel = new JPanel();
		opanel.add(tagLabel);
		panel.add(opanel);
		panel.add(endTimeField);
		timePanel.add(timeLabel, "West");
		timePanel.add(panel, "Center");

		JPanel keywordPanel = new JPanel(new BorderLayout());
		keywordPanel.setPreferredSize(new Dimension(0,20));
		keywordLabel = new JLabel("关键字：");
		keywordFiled = new JTextField();
		keywordPanel.add(keywordLabel, "West");
		keywordPanel.add(keywordFiled, "Center");
		
		
		
		paramPanel = new JPanel(new GridLayout(4,1));
		paramPanel.setBorder(new TitledBorder("查询参数"));
		paramPanel.add(titlePanel);
		paramPanel.add(timePanel);
		paramPanel.add(keywordPanel);
	}
	
	private void initContentTable(){
		searchTableModel = new DiaryTableModel();
		searchTable = new JTable(searchTableModel);
		
		searchPopupMenu = new JPopupMenu();
		viewDiaryItem = new JMenuItem("打开日记");
		viewDiaryItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		delDiaryItem = new JMenuItem("删除日记");
		delDiaryItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		searchPopupMenu.add(viewDiaryItem);
		searchPopupMenu.add(delDiaryItem);
		searchTable.addMouseListener(new MouseAdapter() {

		    public void mousePressed(MouseEvent e) {
		     if (e.isPopupTrigger())
		      showMenu(e);
		    }
		    public void mouseReleased(MouseEvent e) {
		     if (e.isPopupTrigger())
		      showMenu(e);
		    }
		    private void showMenu(MouseEvent e) {
		    	searchPopupMenu.show(e.getComponent(), e.getX(), e.getY());
		    }
		   
		});
		
	}
}












