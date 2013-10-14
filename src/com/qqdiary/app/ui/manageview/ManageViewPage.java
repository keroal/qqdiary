package com.qqdiary.app.ui.manageview;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import com.qqdiary.app.closure.Callback;
import com.qqdiary.app.closure.ICallback;
import com.qqdiary.app.module.DiaryAttachment;
import com.qqdiary.app.module.DiaryFolder;
import com.qqdiary.app.module.GeneralDiary;
import com.qqdiary.app.service.DiaryService;
import com.qqdiary.app.service.DiaryShare;
import com.qqdiary.app.ui.DiaryDialog;
import com.qqdiary.app.ui.DiaryTableModel;
import com.qqdiary.app.ui.FolderDialog;
import com.qqdiary.app.ui.ViewPanel;

/**
 * ������ͼҳ�涨����
 * @author Administrator
 *
 */
public class ManageViewPage extends ViewPanel {
	private static String viewName = "������ͼ";
	private DiaryService service;
	private DiaryShare share;
	
	/**�����б���*/
	private List<DiaryFolder> uiFolders;

	/**�����б�ؼ�����*/
	private JList foldersList;
	private DefaultListModel listModel;
	private JPopupMenu folderListPopuoMenu;
	private JMenuItem modifyFolderItem;
	private JMenuItem delFolderItem;
	private JMenuItem reloadListItem;

	/**�ռ��б�ؼ�����*/
	private JTable manageTable;
	private DiaryTableModel manageTableModel;
	private JPopupMenu manageTablePopupMenu;
	private JMenuItem viewDiaryItem;
	private JMenuItem delDiaryItem;
	
	public ManageViewPage(DiaryService service, DiaryShare share){
		super(viewName);
		this.service = service;
		this.share = share;
		
		initFolderListComp();
		initDiaryTableComp();
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerSize(10);
		splitPane.setResizeWeight(0.1D);
		splitPane.setOneTouchExpandable(true);
	    
		JScrollPane folderScrollPane = new JScrollPane(foldersList);
		folderScrollPane.setBorder(new TitledBorder("����"));
		splitPane.add(folderScrollPane, "left");

		JScrollPane diaryScrollPane = new JScrollPane(manageTable);
		diaryScrollPane.setBorder(new TitledBorder("�ռ��б�"));
		
		splitPane.add( diaryScrollPane, "right");	
		this.addComponent(splitPane, "Center");
		
		initFolderListData();
	}
	
	/**
	 * ��ʼ�������б�
	 */
	private void initFolderListComp() {
		this.setViewLayout(new BorderLayout());
		
		FolderListCellRender lcr = new FolderListCellRender();
		listModel = new DefaultListModel();
		foldersList = new JList(listModel);
		foldersList.setCellRenderer(lcr);
		
		
		folderListPopuoMenu = new JPopupMenu();
		modifyFolderItem = new JMenuItem("�޸ķ���");
		modifyFolderItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int selectIndex =  foldersList.getSelectedIndex();
				if( selectIndex == -1){
					JOptionPane.showMessageDialog( null , "����ѡ��Ҫ�޸ĵķ��࣡");
					return;
				}
				DiaryFolder folder = (DiaryFolder)listModel.getElementAt(selectIndex);
				new FolderDialog(folder, new Callback() {
					
					@Override
					protected Object callbackFunc(Object param) {
						// TODO Auto-generated method stub
						updateFolderItem((DiaryFolder)param);
						return null;
					}
				}).show();
			}
		});
		
		delFolderItem = new JMenuItem("ɾ������");
		delFolderItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub	
				int[] selectIndies =  foldersList.getSelectedIndices();
				if( selectIndies.length == 0){
					JOptionPane.showMessageDialog( null , "����ѡ��Ҫ�޸ĵķ��࣡");
					return;
				}
				
				int select = JOptionPane.showConfirmDialog( null , "ȷ��Ҫɾ�����࣡" , "��ʾ", JOptionPane.YES_NO_OPTION );
				if (select == JOptionPane.NO_OPTION) {
					return;
				}
				
				List<DiaryFolder> delList = new ArrayList<DiaryFolder>();
				for (int index : selectIndies) {
					delList.add((DiaryFolder)listModel.getElementAt(index));
				}
				
				remvoeFolderItems(delList);
				foldersList.updateUI();
			}
		});
		
		reloadListItem = new JMenuItem("ˢ��");
		reloadListItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				initFolderListData();
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
			
			public void mouseClicked(MouseEvent e){  
		        if(e.getClickCount()==2){   
		        	showDiaryItemsByFolder();
		        }  
		    }  
			
		});
	}
	
	/**
	 * ��ʼ���ռ��б�
	 */
	private void initDiaryTableComp() {
		manageTableModel = new DiaryTableModel();
		manageTable = new JTable(manageTableModel);
		
		manageTablePopupMenu = new JPopupMenu();
		viewDiaryItem = new JMenuItem("����־");
		viewDiaryItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int selectIndex =  manageTable.getSelectedRow();
				if( selectIndex == -1){
					JOptionPane.showMessageDialog( null , "����ѡ��Ҫ�򿪵��ռǣ�");
					return;
				}

				GeneralDiary diary = manageTableModel.getElementAt(selectIndex);
				if (diary.isHasAttachement()) {
					diary.setAttachmentList(service.getDiaryAttachments(diary));	
				}
				new DiaryDialog(uiFolders, diary, new ICallback() {
					
					@Override
					public Object callback(Object param) {
						// TODO Auto-generated method stub
						updateDiaryItem((GeneralDiary)param);
						return null;
					}
				}).show();
			}
		});
		
		delDiaryItem = new JMenuItem("ɾ����־");
		delDiaryItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int[] indies = manageTable.getSelectedRows();
				if (indies.length == 0) {
					JOptionPane.showMessageDialog( null , "����ѡ��Ҫɾ�����ռǣ�");
					
					return;
				}
				
				int select = JOptionPane.showConfirmDialog( null , "ȷ��Ҫɾ���ռǣ�" , "��ʾ", JOptionPane.YES_NO_OPTION );
				if (select == JOptionPane.NO_OPTION) {
					return;
				}
				
				List<GeneralDiary> diaries = new ArrayList<GeneralDiary>();
				for (int index : indies) {
					diaries.add(manageTableModel.getElementAt(index));
				}
				
				remvoeDiaryItems(diaries);
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
	
	/**
	 * ��ʼ����������
	 */
	@SuppressWarnings("unchecked")
	private void initFolderListData() {
		listModel.removeAllElements();	
		uiFolders = service.getDiaryFolderList();
		for (DiaryFolder diaryFolder : uiFolders) {
			listModel.addElement(diaryFolder);	
		}
		foldersList.updateUI();
	}
	
	/**
	 * ���·�����Ϣ
	 * @param folder
	 */
	private void updateFolderItem(DiaryFolder folder){
		service.saveDiaryFolder(folder);
		foldersList.updateUI();
		
	}
	
	/**
	 * ���������Ϣ
	 * @param folder
	 */
	@SuppressWarnings("unchecked")
	public void saveFolderItem(DiaryFolder folder){
		service.saveDiaryFolder(folder);
		listModel.addElement(folder);
		uiFolders.add(folder);
		foldersList.updateUI();
	}
	
	/**
	 * ����ɾ�������б�
	 * @param folders
	 */
	private void remvoeFolderItems(List<DiaryFolder> folders) {
		for (DiaryFolder folder : folders) {
			service.deleteFolder(folder);
			uiFolders.remove(folder);
			listModel.removeElement(folder);
		}
		
		manageTable.updateUI();
	}
	
	/**
	 * ˫��������Ӧ����
	 * ������ʾ��ǰ�����µ��ռ��б�
	 */
	private void showDiaryItemsByFolder(){
		manageTableModel.removeAllElements();
		DiaryFolder folder = (DiaryFolder)foldersList.getSelectedValue();
		if (folder.getDiariesList().isEmpty()) {
			folder.setDiariesList(service.getDiaries(folder));	
		}

		for (GeneralDiary diary : folder.getDiariesList()) {	
			manageTableModel.add(diary);
		}
		manageTable.updateUI();
	}
	
	/**
	 * �����ռ���Ϣ
	 * @param diary
	 */
	public void saveDiaryItem(GeneralDiary diary) {
		service.saveDiary(diary);
		diary.getFolder().getDiariesList().add(diary);
		manageTableModel.add(diary);
		manageTable.updateUI();
	}
	
	/**
	 * �����ռ���Ϣ
	 * @param diary
	 */
	public void updateDiaryItem(GeneralDiary diary) {
		service.saveDiary(diary);
		manageTable.updateUI();
	}	

	/**
	 * ����ɾ���ռ�
	 * @param diaries
	 */
	private void remvoeDiaryItems(List<GeneralDiary> diaries) {
		for (GeneralDiary generalDiary : diaries) {
			service.deleteDiary(generalDiary);
			generalDiary.getFolder().getDiariesList().remove(generalDiary);
			manageTableModel.remove(generalDiary);
		}
		
		manageTable.updateUI();
	}
	
	
	public List<DiaryFolder> getUiFolders() {
		return uiFolders;
	}

	public void setUiFolders(List<DiaryFolder> uiFolders) {
		this.uiFolders = uiFolders;
	}
	
	
}
