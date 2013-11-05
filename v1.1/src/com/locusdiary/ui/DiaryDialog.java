package com.locusdiary.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;

import com.locusdiary.closure.ICallback;
import com.locusdiary.module.DiaryFolder;
import com.locusdiary.module.GeneralAttachment;
import com.locusdiary.module.GeneralAttachment.AttachmentStatus;
import com.locusdiary.module.GeneralDiary;
import com.locusdiary.module.PictureAttachment;
import com.locusdiary.module.SessionManager;
import com.locusdiary.module.TimeHelper;
import com.locusdiary.module.VoiceAttachment;
import com.locusdiary.ui.module.AttachmentListCellRender;
import com.locusdiary.ui.module.PictureFileFilter;


/**
 * 日记显示、编辑界面类
 * @author Administrator
 *
 */
public class DiaryDialog extends JDialog {
	
	private ICallback callback;
	private GeneralDiary diary;
	private List<DiaryFolder> folderList;
	
	//头部属性参数定义

	private JPanel headPanel;
	private JPanel contentPanel;
	private JPanel toolbarPanel;
	
	private JLabel titleLabel;
	private JTextField titleTextLabel;
	private JLabel classifyLabel;
	private JComboBox classifyComboBox;
	private JLabel buildTimeLabel;
	private JTextField buildTimeTextLabel;
	private JLabel modifyTimeLabel;
	private JTextField modifyTimeTextLabel;
	private JLabel weatherLabel;
	private JTextField weatherTextLabel;
	private JTextArea contentTextArea;
	
	//日记内容和附件空间定义
	private JScrollPane attachmentScrollPane;
	private DefaultListModel attachmentListModel;
	private JList attachmentList;
	
	//工具栏参数定义
	private JButton storeButton;
	private JButton cancelButton;
	private	JButton addPicButton;
	private JButton addVoiButton;
	private JButton delAttachButton;
	private JButton modifyButton;
	
	
	public DiaryDialog(GeneralDiary diary, List<DiaryFolder>list, ICallback callback) {
		// TODO Auto-generated constructor stub
		this.folderList = list;
		this.callback = callback;
		this.diary = diary;
		
		initComponets();	
		initComponetsValue(this.diary);
	}
	
	public DiaryDialog(List<DiaryFolder>list, ICallback callback) {
		// TODO Auto-generated constructor stub
		this.folderList = list;
		this.callback = callback;
		this.diary = new GeneralDiary();

		initComponets();
	}
	
	/**窗口界面初始化*/
	private void initComponets() {
		setSize(700, 600);
		setTitle("日记编写");
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		
		initHeadPanelComponents();
		initContentPanelComponents();
		initToolbarPanelComponents();
		
		add(headPanel, BorderLayout.NORTH);
		add(contentPanel, BorderLayout.CENTER);
		add(toolbarPanel, BorderLayout.SOUTH);
	}
	
	/** 
	* Name:			initHeadPanelComponents；
	* Description : 初始化日记信息头界面控件 ；
	* @param:		void ；
	* @return 		void；
	*/ 
	private void initHeadPanelComponents(){
		titleLabel = new JLabel("  标        题：");
		titleTextLabel = new JTextField("这是一个标题");
		classifyLabel = new JLabel("  分        类：");
		
		classifyComboBox = new JComboBox();
		for (DiaryFolder folder : folderList) {
			classifyComboBox.addItem(folder);
		}
		buildTimeLabel = new JLabel("  创建时间：");
		buildTimeTextLabel = new JTextField(TimeHelper.getInstance().getCurrentTime());
		modifyTimeLabel = new JLabel("修改时间：");
		modifyTimeTextLabel = new JTextField(TimeHelper.getInstance().getCurrentTime());
		weatherLabel = new JLabel("天        气：");
		weatherTextLabel = new JTextField("晴天");
		
		JPanel panel1 = new JPanel(new BorderLayout());
		panel1.add(titleLabel, BorderLayout.WEST);
		panel1.add(titleTextLabel, BorderLayout.CENTER);
		
		JPanel panel2 = new JPanel(new BorderLayout());
		panel2.add(classifyLabel, BorderLayout.WEST);
		panel2.add(classifyComboBox, BorderLayout.CENTER);
		
		JPanel panel3 = new JPanel(new BorderLayout());
		panel3.add(buildTimeLabel, BorderLayout.WEST);
		panel3.add(buildTimeTextLabel, BorderLayout.CENTER);
		
		JPanel panel4 = new JPanel(new BorderLayout());
		panel4.add(modifyTimeLabel, BorderLayout.WEST);
		panel4.add(modifyTimeTextLabel, BorderLayout.CENTER);
		
		JPanel panel5 = new JPanel(new BorderLayout());
		panel5.add(weatherLabel, BorderLayout.WEST);
		panel5.add(weatherTextLabel, BorderLayout.CENTER);
		
		JPanel panel6 = new JPanel(new GridLayout(1, 2, 5, 5 ));
		panel6.add(panel2);
		panel6.add(panel5);
		
		JPanel panel7 = new JPanel(new GridLayout(1,2, 5, 5));
		panel7.add(panel3);
		panel7.add(panel4);
		
		GridLayout layout = new GridLayout(3, 1, 10, 5);
		headPanel = new JPanel(layout);
		headPanel.setPreferredSize(new Dimension(400, 100));
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
		attachmentList.setPreferredSize(new Dimension(90,100));
		attachmentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		attachmentList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                attachmentListMouseClicked(evt);
            }
        });
		
		attachmentScrollPane = new JScrollPane(attachmentList);
		attachmentScrollPane.setPreferredSize(new Dimension(90,100));
		attachmentScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		attachmentScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		contentPanel.add(contentScrollPane, BorderLayout.CENTER);
		contentPanel.add(attachmentScrollPane, BorderLayout.EAST);
	}
	
	/**
	 * 构造按钮控件容器
	 * @return
	 */
	private void initToolbarPanelComponents(){
		toolbarPanel = new JPanel(new BorderLayout());
		
		JPanel rightPanel = new JPanel();
		storeButton = new JButton("保存");
		storeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				saveDiary();
			}
		});
		
		
		cancelButton = new JButton("退出");
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int select = JOptionPane.showConfirmDialog( null , "确定直接退出？" , "提示", JOptionPane.YES_NO_OPTION );
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
				
				JFileChooser chooser = new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				chooser.setDialogTitle("选择图片");
				
				//设置文件类型过滤
				chooser.setAcceptAllFileFilterUsed(false);  
				String imageExts[] = { "jpg", "jpeg", "png", "gif","bmp" };
				PictureFileFilter filter = new PictureFileFilter(imageExts,
		                "Images Files(*.jpg;*.jpeg;*.png;*.gif;*.bmp)");
		        chooser.setFileFilter(filter);
		        
		        int result = chooser.showOpenDialog(null);
		        if(result == JFileChooser.APPROVE_OPTION)
		        {
		            String path = chooser.getSelectedFile().getAbsolutePath();
		            addPicAttachmentToList(path);
		            attachmentList.updateUI();
		        }
			}
		});
		
		addVoiButton = new JButton("添加语音");
		addVoiButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new VoiceRecorder(new VoiceAttachment(diary), new ICallback() {
					
					@Override
					public Object callback(Object param) {
						// TODO Auto-generated method stub
						addVoiAttachmentToList((GeneralAttachment)param);
			            attachmentList.updateUI();
						return null;
					}
				}).show();
			}
		});
		
		delAttachButton = new JButton("删除附件");
		delAttachButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub				
				GeneralAttachment attachment =  (GeneralAttachment)attachmentList.getSelectedValue();
				if( attachment == null){
					JOptionPane.showMessageDialog(null, "选择需要删除的附件！");
					return;
				}
				
				if (attachment.getStatus() == AttachmentStatus.EXIST) {
					attachment.setStatus(AttachmentStatus.DELETE);
					attachmentListModel.removeElement(attachment);
				}else {
					attachmentListModel.removeElement(attachment);
				}
			
				attachmentList.updateUI();
			}
		});
		
		rightPanel.add(addPicButton);
		rightPanel.add(addVoiButton);
		rightPanel.add(delAttachButton);
		rightPanel.add(storeButton);
		rightPanel.add(cancelButton);
		
		toolbarPanel.add(rightPanel, "East");
	}
	
	
	/**
	 * 界面数据初始化
	 * （日记对象不为空）
	 */
	private void initComponetsValue(GeneralDiary diary){
		titleTextLabel.setText(diary.getTitle());
		weatherTextLabel.setText(diary.getWeather());
		buildTimeTextLabel.setText(diary.getBuildTime());
		modifyTimeTextLabel.setText(diary.getModifyTime());
		contentTextArea.setText(diary.getContent());
		
		for (DiaryFolder folder : folderList) {
			if (folder.getId().equals(diary.getFolderid())) {
				classifyComboBox.setSelectedItem(folder);
			}
		}
		if (!diary.getAttachmentList().isEmpty()) {
			attachmentScrollPane.setVisible(true);
			setUIAttachments(diary.getAttachmentList());
		}
	}
	
	/**
	 * 保存界面数据到日记对象
	 */
	private void assembleDiary(){
		diary.setTitle(titleTextLabel.getText());
		diary.setFolderid(((DiaryFolder)classifyComboBox.getSelectedItem()).getId());
		diary.setWeather(weatherTextLabel.getText());
		diary.setContent(contentTextArea.getText());
		diary.setBuildTime(buildTimeTextLabel.getText());
		diary.setModifyTime(TimeHelper.getInstance().getCurrentTime());
		if (attachmentListModel.isEmpty()) {
			diary.setHasAttachement(false);
		}else {
			diary.setHasAttachement(true);
			
			/**修改日记附件列表，如果没有就插入*/
			for (GeneralAttachment attachment : getUIAttachments()) {
				if (!diary.getAttachmentList().contains(attachment)) {
					diary.getAttachmentList().add(attachment);
				}
			}	
		}
		
	}
	
	
	public void setUIAttachments(List<GeneralAttachment> attachments) {
		for (GeneralAttachment diaryAttachment : attachments) {
			/** 判断主要是为了保证对修改后保持在本地的日记附件的过滤*/
			if (diaryAttachment.getStatus() != AttachmentStatus.DELETE) {
				diaryAttachment.setStatus(AttachmentStatus.EXIST);
				attachmentListModel.addElement(diaryAttachment);
			}
		}
		attachmentList.updateUI();
	}
	
	public List<GeneralAttachment> getUIAttachments(){
		List<GeneralAttachment> attachments = new ArrayList<GeneralAttachment>();
		for (int i = 0; i < attachmentListModel.getSize(); i++) {
			GeneralAttachment attachment = (GeneralAttachment)attachmentListModel.getElementAt(i);
			attachments.add(attachment);
		}
		return attachments;
	}
	
	private void attachmentListMouseClicked(MouseEvent evt) {                                    
        // TODO add your handling code here:
		if (evt.getClickCount() == 2) {
			((GeneralAttachment)attachmentList.getSelectedValue()).showAttachmentContent();
		}
	}  
	
	/**
	 * 添加附件响应函数
	 * @param path
	 */
	private void addPicAttachmentToList(String path) {
		GeneralAttachment attachment = new PictureAttachment(diary);
		attachment.setDiaryId(diary.getId());
		attachment.setType("图片");
		attachment.setPath(path);
		attachmentListModel.addElement(attachment);
		attachmentList.updateUI();
	}
	
	/**
	 * 添加语音响应函数
	 * @param path
	 */
	private void addVoiAttachmentToList(GeneralAttachment attachment) {
		attachmentListModel.addElement(attachment);
		attachmentList.updateUI();
	}
	
	/**
	 * 删除附件响应函数
	 * @param attachment
	 */
	private void removeAttachment(GeneralAttachment attachment) {
		File file = new File(attachment.getPath());
		if (file.exists()) {
			file.delete();
		}
		attachmentListModel.removeElement(attachment);
	}
	
	/**
	 * 保存日记操作响应函数
	 */
	private void saveDiary() {
		assembleDiary();
		
		try {
			assert diary.getTitle().length()<20:"日记标题长度不能超过20！";
			assert diary.getWeather().length()<5:"自定义天气长度不能大于5！";
			assert TimeHelper.getInstance().checkTimeFormate(diary.getBuildTime(), "yyyy-MM-dd HH:mm:ss"):"创建时间格式不正确";
			assert TimeHelper.getInstance().checkTimeFormate(diary.getBuildTime(), "yyyy-MM-dd HH:mm:ss"):"修改时间格式不正确";
		} catch (AssertionError e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e.getMessage());
			return;
		}
		
		this.callback.callback(diary);
		this.dispose();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
}
