package com.qqdiary.app.ui;

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
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.qqdiary.app.closure.Callback;
import com.qqdiary.app.closure.ICallback;
import com.qqdiary.app.module.DayInformationProvider;
import com.qqdiary.app.module.DiaryAttachment;
import com.qqdiary.app.module.DiaryFolder;
import com.qqdiary.app.module.GeneralDiary;
import com.qqdiary.app.module.DiaryAttachment.AttachmentStatus;
import com.qqdiary.app.ui.manageview.AttachmentFileFilter;
import com.qqdiary.app.ui.manageview.AttachmentListCellRender;
import com.qqdiary.app.ui.manageview.IconImageFactory;

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
	private JButton delAttachButton;
	private JButton modifyButton;
	
	
	public DiaryDialog(List<DiaryFolder> folderList, GeneralDiary diary, ICallback callback) {
		// TODO Auto-generated constructor stub
		this.folderList = folderList;
		this.callback = callback;
		this.diary = diary;
		
		initComponet();
		
		/**通过判断传入的日记对象是否为空，来决定界面数据初始化流程*/
		if (this.diary != null) {
			this.diary.setNew(false);
			initCompValue(this.diary);
			setModifyLock(false);
		}else {
			this.diary = new GeneralDiary();
			initCompValue();
			setModifyLock(true);
		}

	}
	
	/**窗口界面初始化*/
	private void initComponet() {
		this.setSize(900, 480);
		this.setTitle("日记编写");
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		
		JPanel panel = new JPanel(new GridLayout(2, 1));
		panel.add(createDatePanel());
		panel.add(createTitlePanel());
		
		this.add(panel, "North");
		this.add(createContentPanel(), "Center");
		this.add(createToolbarPanel(), "South");
		
	}
	
	/**
	 * 构造界面数据容器
	 * @return
	 */
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
	
	/**
	 * 构造标题数据容器
	 * @return
	 */
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
	
	/**
	 * 构造内容数据容器
	 * @return
	 */
	private JPanel createContentPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		
		JPanel leftPanel = new JPanel(new BorderLayout());
		docPanel = new JEditorPane();
		leftPanel.setBorder(new TitledBorder("内容"));
		leftPanel.add(new JScrollPane(docPanel), "Center");
		
		JPanel rightPanel = new JPanel(new BorderLayout());
		rightPanel.setPreferredSize(new Dimension(200, 0));
		rightPanel.setBorder(new TitledBorder("附件"));

		AttachmentListCellRender cellRender = new AttachmentListCellRender();
		listModel = new DefaultListModel();
		attachList = new JList(listModel);
		attachList.setCellRenderer(cellRender);
		attachList.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent e) {
			     
			}
			public void mouseReleased(MouseEvent e) {
			    
			}
			private void showMenu(MouseEvent e) {
			    
			}
			
			public void mouseClicked(MouseEvent e){  
		        if(e.getClickCount()==2){   
		        	openDiaryAttachment();
		        }  
		    }  
			
		});
		
		
		rightPanel.add(new JScrollPane(attachList), "Center");
	    
		panel.add(leftPanel, "Center");
		panel.add(rightPanel, "East");
		return panel;
	}
	
	/**
	 * 构造按钮控件容器
	 * @return
	 */
	private JPanel createToolbarPanel(){
		JPanel panel = new JPanel(new BorderLayout());
		
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
				AttachmentFileFilter filter = new AttachmentFileFilter(imageExts,
		                "Images Files(*.jpg;*.jpeg;*.png;*.gif;*.bmp)");
		        chooser.setFileFilter(filter);
		        
		        int result = chooser.showOpenDialog(null);
		        if(result == JFileChooser.APPROVE_OPTION)
		        {
		           String path = chooser.getSelectedFile().getAbsolutePath();
		           addPicAttachmentToList(path);
		        }
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
						addVoiAttachmentToList((String)param);
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
				int select = JOptionPane.showConfirmDialog( null , "确定要删除附件！" , "提示", JOptionPane.YES_NO_OPTION );
				if (select == JOptionPane.NO_OPTION) {
					return;
				}
				
				int[] selectIndies =  attachList.getSelectedIndices();
				if( selectIndies.length == 0){
					return;
				}
				
				Object[] delList = attachList.getSelectedValues();
				for (Object object : delList) {
					if (((DiaryAttachment)object).getStatus() == AttachmentStatus.EXIST) {
						((DiaryAttachment)object).setStatus(AttachmentStatus.DELETE);
						listModel.removeElement(object);
					}
					
				}
				
				attachList.updateUI();
			}
		});
		
		rightPanel.add(addPicButton);
		rightPanel.add(addVoiButton);
		rightPanel.add(delAttachButton);
		rightPanel.add(storeButton);
		rightPanel.add(cancelButton);
		
		JPanel leftPanel = new JPanel();
		modifyButton = new JButton("编辑");
		modifyButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				setModifyLock(true);
			}
		});
		leftPanel.add(modifyButton);
		
		panel.add(rightPanel, "East");
		panel.add(leftPanel, "West");
		return panel;
	}
	
	/**
	 * 界面数据初始化
	 * （日记对象为空的情况）
	 */
	private void initCompValue(){
		setUITime(DayInformationProvider.getInstance().getDateTime());
		setUIWeather(DayInformationProvider.getInstance().getWeather());
	}
	
	/**
	 * 界面数据初始化
	 * （日记对象不为空）
	 */
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
	
	/**
	 * 保存界面数据到日记对象
	 */
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
			
			/**修改日记附件列表，如果没有就插入*/
			for (DiaryAttachment attachment : getUIAttachments()) {
				if (!diary.getAttachmentList().contains(attachment)) {
					diary.getAttachmentList().add(attachment);
				}
			}
			
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
		for (DiaryAttachment diaryAttachment : attachments) {
			/** 判断主要是为了保证对修改后保持在本地的日记附件的过滤*/
			if (diaryAttachment.getStatus() != AttachmentStatus.DELETE) {
				diaryAttachment.setStatus(AttachmentStatus.EXIST);
				listModel.addElement(diaryAttachment);
			}
		}
		attachList.updateUI();
	}
	
	public List<DiaryAttachment> getUIAttachments(){
		List<DiaryAttachment> attachments = new ArrayList<DiaryAttachment>();
		for (int i = 0; i < listModel.getSize(); i++) {
			DiaryAttachment attachment = (DiaryAttachment)listModel.getElementAt(i);
			attachments.add(attachment);
		}
		return attachments;
	}
	
	
	/**
	 * 设置编辑锁
	 * @param lock
	 */
	public void setModifyLock(boolean lock) {
		this.titleField.setEditable(lock);
		this.weatherLabel.setEnabled(lock);
		this.folderComboBox.setEnabled(lock);
		this.docPanel.setEditable(lock);
		
		this.addPicButton.setEnabled(lock);
		this.addVoiButton.setEnabled(lock);
		this.delAttachButton.setEnabled(lock);
		this.storeButton.setEnabled(lock);
	}

	/**
	 * 添加附件响应函数
	 * @param path
	 */
	private void addPicAttachmentToList(String path) {
		DiaryAttachment attachment = new DiaryAttachment();
		attachment.setDiaryId(diary.getId());
		attachment.setType("图片");
		attachment.setPath(path);
		listModel.addElement(attachment);
		attachList.updateUI();
	}
	
	/**
	 * 添加语音响应函数
	 * @param path
	 */
	private void addVoiAttachmentToList(String path) {
		DiaryAttachment attachment = new DiaryAttachment();
		attachment.setDiaryId(diary.getId());
		attachment.setType("语音");
		attachment.setPath(path);listModel.addElement(attachment);
		attachList.updateUI();
	}
	
	/**
	 * 删除附件响应函数
	 * @param attachment
	 */
	private void removeAttachment(DiaryAttachment attachment) {
		File file = new File(attachment.getPath());
		if (file.exists()) {
			file.delete();
		}
		listModel.removeElement(attachment);
	}
	
	
	/**
	 * 双击打开附件响应函数
	 */
	private void openDiaryAttachment(){
		DiaryAttachment attachment = (DiaryAttachment)attachList.getSelectedValue();

		String path = "";
		if (attachment.getStatus() == AttachmentStatus.EXIST  ) {
			path = System.getProperty("user.dir") + "\\" + attachment.getPath();
		}else {
			path = attachment.getPath();
		}
		
		File file = new File(path);
		if (!file.exists()) {
			JOptionPane.showMessageDialog( null , "附件文件不存在！");
			return;
		}
		
		try{
			if (attachment.getType().contains("图片")) {
				Runtime.getRuntime().exec("rundll32 c:\\Windows\\System32\\shimgvw.dll,ImageView_Fullscreen "+path);
			} else if (attachment.getType().contains("语音")) {
				Runtime.getRuntime().exec("\"C:\\Program Files\\Windows Media Player\\wmplayer.exe\" \""+ path + "\"");	
			}
		 }catch (IOException e){
	            e.printStackTrace();
	     }
	}
	
	/**
	 * 保存日记操作响应函数
	 */
	private void saveDiary() {
		assembleDiary();
		this.callback.callback(diary);
		this.dispose();
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
	}
}
