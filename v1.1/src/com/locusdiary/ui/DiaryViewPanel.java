package com.locusdiary.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;

import com.locusdiary.module.DiaryFolder;
import com.locusdiary.module.GeneralAttachment;
import com.locusdiary.module.GeneralComment;
import com.locusdiary.module.GeneralDiary;
import com.locusdiary.module.PictureAttachment;
import com.locusdiary.module.SessionManager;
import com.locusdiary.module.VoiceAttachment;
import com.locusdiary.ui.module.AttachmentListCellRender;
import com.locusdiary.ui.module.CommentListCellRender;

public class DiaryViewPanel extends JPanel {
	
	private JPanel infoPanel;
	private JPanel nonePanel;
	private JPanel headPanel;
	private JPanel contentPanel;
	private JPanel commentPanel;
	
	private CardLayout showLayout;
	
	private JLabel titleLabel;
	private JLabel titleTextLabel;
	private JLabel classifyLabel;
	private JLabel classifyTextLabel;
	private JLabel buildTimeLabel;
	private JLabel buildTimeTextLabel;
	private JLabel modifyTimeLabel;
	private JLabel modifyTimeTextLabel;
	private JLabel weatherLabel;
	private JLabel weatherTextLabel;
	private JTextArea contentTextArea;
	
	private JScrollPane attachmentScrollPane;
	private DefaultListModel attachmentListModel;
	private JList attachmentList;
	
	private DefaultListModel commentListModel;
	private JList commentList;
	
	/** 
	* Name:			initComponents；
	* Description : 初始化界面控件 ；
	* @param:		void ；
	* @return 		void；
	*/ 
	public DiaryViewPanel(){
		showLayout = new CardLayout();
		setLayout(showLayout);
		initInfoPanelComponents();
		initNonePanelComponents();
		add("1", infoPanel);
		add("2", nonePanel);
		
		showLayout.show(this, "2");
	}
	
	/** 
	* Name:			initInfoPanelComponents；
	* Description : 初始化选择日记情况下的界面控件 ；
	* @param:		void ；
	* @return 		void；
	*/ 
	private void initInfoPanelComponents() {
		infoPanel = new JPanel(new BorderLayout());
		infoPanel.setBorder(new TitledBorder(""));
		
		initHeadPanelComponents();
		initContentPanelComponents();
		initCommentPanelComponents();
		
		infoPanel.add(headPanel, BorderLayout.NORTH);
		infoPanel.add(contentPanel, BorderLayout.CENTER);
		infoPanel.add(commentPanel, BorderLayout.SOUTH);
	}
	
	/** 
	* Name:			initHeadPanelComponents；
	* Description : 初始化日记信息头界面控件 ；
	* @param:		void ；
	* @return 		void；
	*/ 
	private void initHeadPanelComponents(){
		titleLabel = new JLabel("  标        题：");
		titleTextLabel = new JLabel("这是一个标题");
		classifyLabel = new JLabel("  分        类：");
		classifyTextLabel = new JLabel("分类情况");
		buildTimeLabel = new JLabel("  创建时间：");
		buildTimeTextLabel = new JLabel("2013-10-11 11:12:11");
		modifyTimeLabel = new JLabel("修改时间：");
		modifyTimeTextLabel = new JLabel("2013-10-11 11:12:11");
		weatherLabel = new JLabel("天        气：");
		weatherTextLabel = new JLabel("晴天");
		
		JPanel panel1 = new JPanel(new BorderLayout());
		panel1.add(titleLabel, BorderLayout.WEST);
		panel1.add(titleTextLabel, BorderLayout.CENTER);
		
		JPanel panel2 = new JPanel(new BorderLayout());
		panel2.add(classifyLabel, BorderLayout.WEST);
		panel2.add(classifyTextLabel, BorderLayout.CENTER);
		
		JPanel panel3 = new JPanel(new BorderLayout());
		panel3.add(buildTimeLabel, BorderLayout.WEST);
		panel3.add(buildTimeTextLabel, BorderLayout.CENTER);
		
		JPanel panel4 = new JPanel(new BorderLayout());
		panel4.add(modifyTimeLabel, BorderLayout.WEST);
		panel4.add(modifyTimeTextLabel, BorderLayout.CENTER);
		
		JPanel panel5 = new JPanel(new BorderLayout());
		panel5.add(weatherLabel, BorderLayout.WEST);
		panel5.add(weatherTextLabel, BorderLayout.CENTER);
		
		JPanel panel6 = new JPanel(new GridLayout(1,2));
		panel6.add(panel2);
		panel6.add(panel5);
		
		JPanel panel7 = new JPanel(new GridLayout(1,2));
		panel7.add(panel3);
		panel7.add(panel4);
		
		headPanel = new JPanel(new GridLayout(3, 1));
		headPanel.setPreferredSize(new Dimension(400, 60));
		headPanel.add(panel1);
		headPanel.add(panel6);
		headPanel.add(panel7);
	}
	
	/** 
	* Name:			initContentPanelComponents；
	* Description : 初始化日记内容显示控件 ；
	* @param:		void ；
	* @return 		void；
	*/ 
	private void initContentPanelComponents(){
		contentPanel = new JPanel(new BorderLayout());
		contentPanel.setBorder(new TitledBorder("内容"));



		contentTextArea = new JTextArea();
		contentTextArea.setLineWrap(true);
		JScrollPane contentScrollPane = new JScrollPane(contentTextArea);
		contentScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		AttachmentListCellRender listCellRender = new AttachmentListCellRender();
		attachmentListModel = new DefaultListModel();
		attachmentList = new JList(attachmentListModel);
		attachmentList.setCellRenderer(listCellRender);
		attachmentList.setPreferredSize(new Dimension(90,200));
		attachmentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		attachmentList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                attachmentListMouseClicked(evt);
            }
        });
		
		attachmentScrollPane = new JScrollPane(attachmentList);
		attachmentScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		attachmentScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		contentPanel.add(contentScrollPane, BorderLayout.CENTER);
		contentPanel.add(attachmentScrollPane, BorderLayout.EAST);
	}
	
	/** 
	* Name:			initCommentPanelComponents；
	* Description : 初始化评论显示控件；
	* @param:		void ；
	* @return 		void；
	*/ 
	private void initCommentPanelComponents(){
		commentPanel = new JPanel(new BorderLayout());
		commentPanel.setBorder(new TitledBorder("评论"));
		commentPanel.setPreferredSize(new Dimension(400, 200));
		
		commentListModel = new DefaultListModel();
		CommentListCellRender commentListCellRender = new CommentListCellRender();
		commentList = new JList(commentListModel);
		commentList.setCellRenderer(commentListCellRender);
		commentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		commentPanel.add(new JScrollPane(commentList), BorderLayout.CENTER);
	}
	
	/** 
	* Name:			initNonePanelComponents；
	* Description : 初始化无选择日记情况下的显示界面控件 ；
	* @param:		void ；
	* @return 		void；
	*/ 
	private void initNonePanelComponents() {
		nonePanel = new JPanel();
		nonePanel.setBackground(Color.white);
		JLabel iconLabel = new JLabel();
		JLabel textlLabel = new JLabel();
		
		nonePanel.add(iconLabel);
		nonePanel.add(textlLabel);
	}
	
	/** 
	* Name:			attachmentListMouseClicked；
	* Description : 响应附件JList双击事件 ；
	* @param:		MouseEvent MouseEventev ；
	* @return 		void；
	*/ 
	private void attachmentListMouseClicked(MouseEvent evt) {                                    
        // TODO add your handling code here:
		if (evt.getClickCount() == 2) {
			((GeneralAttachment)attachmentList.getSelectedValue()).showAttachmentContent();
		}
	}  
	
	/** 
	* Name:			showDiaryDescription；
	* Description : 显示日记基本信息 ；
	* @param:		void ；
	* @return 		void；
	*/
	private void showDiaryDescription(GeneralDiary diary) {
		titleTextLabel.setText(diary.getTitle());
		for (DiaryFolder folder : SessionManager.getInstance().getService().getDiaryFolderList(SessionManager.getInstance().getUser())){
			if (folder.getId().equals(diary.getFolderid())) {
				classifyTextLabel.setText(folder.getName());
			}
		}
		buildTimeTextLabel.setText(diary.getBuildTime());
		modifyTimeTextLabel.setText(diary.getModifyTime());
		weatherTextLabel.setText(diary.getWeather());
		contentTextArea.setText(diary.getContent());
	}
	
	/** 
	* Name:			showDiaryAttachments；
	* Description : 显示附件列表信息 ；
	* @param:		void ；
	* @return 		void；
	*/
	private void showDiaryAttachments(List<GeneralAttachment> list) {
		for (GeneralAttachment generalAttachment : list) {
			attachmentListModel.addElement(generalAttachment);
		}
	}
	
	/** 
	* Name:			showDiaryComments；
	* Description : 显示评论列表信息 ；
	* @param:		void；
	* @return 		void；
	*/
	private void showDiaryComments(List<GeneralComment> list){
		for (GeneralComment generalComment : list) {
			commentListModel.addElement(generalComment);
		}
	}
	
	/** 
	* Name:			clearOriginalData；
	* Description : 清除界面数据信息 ；
	* @param:		void；
	* @return 		void；
	*/
	public void clearOriginalData() {
		titleTextLabel.setText("");
		classifyTextLabel.setText("");
		buildTimeTextLabel.setText("");
		modifyTimeTextLabel.setText("");
		weatherTextLabel.setText("");
		contentTextArea.setText("");
		
		attachmentListModel.removeAllElements();
		commentListModel.removeAllElements();
		attachmentScrollPane.setVisible(false);
		commentPanel.setVisible(false);
		showLayout.show(this, "2");
	}
	
	
	/** 
	* Name:			showDiaryInformation；
	* Description : 显示日记内容 ；
	* @param:		GeneralDiary；
	* @return 		void；
	*/ 
	public void showDiaryInformation(GeneralDiary diary) {
		clearOriginalData();
		if (diary == null) {
			return;
		}
		
		showLayout.show(this, "1");
		showDiaryDescription(diary);
		List<GeneralAttachment> attachments = diary.getAttachmentList();
		if (!attachments.isEmpty()) {
			attachmentScrollPane.setVisible(true);
			showDiaryAttachments(attachments);
		}
		
		List<GeneralComment> comments = diary.getCommentList();
		if (!comments.isEmpty()) {
			commentPanel.setVisible(true);
			showDiaryComments(comments);	
		}
	}
	
	public void addDiaryCommentItem(GeneralComment comment) {
		commentPanel.setVisible(true);
		commentListModel.addElement(comment);
	}
	
}
