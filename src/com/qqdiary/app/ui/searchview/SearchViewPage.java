package com.qqdiary.app.ui.searchview;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.eltima.components.ui.DatePicker;
import com.qqdiary.app.closure.ICallback;
import com.qqdiary.app.module.DayInformationProvider;
import com.qqdiary.app.module.DiaryFolder;
import com.qqdiary.app.module.GeneralDiary;
import com.qqdiary.app.module.SearchParameters;
import com.qqdiary.app.service.DiaryService;
import com.qqdiary.app.service.DiaryShare;
import com.qqdiary.app.ui.DiaryDialog;
import com.qqdiary.app.ui.DiaryTableModel;
import com.qqdiary.app.ui.ViewPanel;

/**
 * ��ѯ��ͼ������
 * @author Administrator
 *
 */
public class SearchViewPage extends ViewPanel {
	private static String viewName = "��ӭ��ͼ";
	
	private DiaryService service;
	
	/**��ѯ�����ؼ�����*/
	private JPanel paramPanel;
	private JLabel titleLabel;
	private JLabel timeLabel;
	private JLabel tagLabel;
	private JLabel keywordLabel;
	private JTextField titleField;
	private JTextField keywordFiled;
	private DatePicker beginDatePicker;
	private DatePicker endDatePicker;
	
	/**��ѯ�����ʾ�ؼ�����*/
	private JButton searchButton;
	private JButton resetButton;
	private DiaryTableModel searchTableModel;
	private JTable searchTable;
	private JPopupMenu searchPopupMenu;
	private JMenuItem viewDiaryItem;
	private JMenuItem delDiaryItem;
	
	private List<DiaryFolder> uiFolderes;

	public SearchViewPage(DiaryService serivce){
		super(viewName);
		this.service = serivce;
		uiFolderes = serivce.getDiaryFolderList();
		
		this.setViewLayout(new BorderLayout());
		initParamPanel();
		initContentTable();
		
		JScrollPane tScrollPane = new JScrollPane(searchTable);
		tScrollPane.setBorder(new TitledBorder("��ѯ���"));
		
		JSplitPane splitPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, paramPanel, tScrollPane);
		splitPanel.setOneTouchExpandable(true);
		this.addComponent(splitPanel, "Center");			
	}
	
	/**
	 * ��ʼ����ѯ�����ؼ�
	 */
	private void initParamPanel() {
		
		JPanel buttonPanel = new JPanel(new GridLayout(1,2));
		buttonPanel.setPreferredSize(new Dimension(200,20));
		searchButton = new JButton("��ѯ");
		searchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				searchDiaries();
			}
		});
		
		resetButton = new JButton("���");
		resetButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				clearSearchParameters();
			}
		});
		
		buttonPanel.add(searchButton);
		buttonPanel.add(resetButton);
		
		JPanel titlePanel = new JPanel(new BorderLayout());
		titlePanel.setPreferredSize(new Dimension(0,20));
		titleLabel = new JLabel("��    �⣺");
		titleField = new JTextField();
		titlePanel.add(titleLabel, "West");
		titlePanel.add(titleField, "Center");
		titlePanel.add(buttonPanel, "East");
		
		JPanel timePanel = new JPanel(new BorderLayout());
		timePanel.setPreferredSize(new Dimension(0,20));
		timeLabel = new JLabel("ʱ    �䣺");
		
		String DefaultFormat = "yyyy-MM-dd";
		Date sdate=new Date();
		Date edate=new Date();
		Font font=new Font("Times New Roman", Font.BOLD, 14);
		Dimension dimension=new Dimension(177,24);
		
		beginDatePicker = new DatePicker(sdate,DefaultFormat,font,dimension);
		//beginDatePicker.setTimePanleVisible(true);

		endDatePicker = new DatePicker(edate,DefaultFormat,font,dimension);
		//endDatePicker.setTimePanleVisible(true);

		
		JPanel panel = new JPanel(new GridLayout(1,0));
		tagLabel = new JLabel("---------");
		//panel.add(beginTimeField);
		panel.add(beginDatePicker);
		JPanel opanel = new JPanel();
		opanel.add(tagLabel);
		panel.add(opanel);
		//panel.add(endTimeField);
		panel.add(endDatePicker);
		timePanel.add(timeLabel, "West");
		timePanel.add(panel, "Center");

		JPanel keywordPanel = new JPanel(new BorderLayout());
		keywordPanel.setPreferredSize(new Dimension(0,20));
		keywordLabel = new JLabel("�ؼ��֣�");
		keywordFiled = new JTextField();
		keywordPanel.add(keywordLabel, "West");
		keywordPanel.add(keywordFiled, "Center");
		
		
		
		paramPanel = new JPanel(new GridLayout(4,1));
		paramPanel.setBorder(new TitledBorder("��ѯ����"));
		paramPanel.add(titlePanel);
		paramPanel.add(keywordPanel);
		paramPanel.add(timePanel);
	}
	
	/**
	 * ��ʼ�������ʾ�ؼ�
	 */
	private void initContentTable(){
		searchTableModel = new DiaryTableModel();
		searchTable = new JTable(searchTableModel);
		
		searchPopupMenu = new JPopupMenu();
		viewDiaryItem = new JMenuItem("���ռ�");
		viewDiaryItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int selectIndex =  searchTable.getSelectedRow();
				if( selectIndex == -1){
					JOptionPane.showMessageDialog( null , "����ѡ��Ҫ�򿪵��ռǣ�");
					return;
				}

				GeneralDiary diary = searchTableModel.getElementAt(selectIndex);
				if (diary.isHasAttachement()) {
					diary.setAttachmentList(service.getDiaryAttachments(diary));	
				}
				new DiaryDialog(uiFolderes, diary, new ICallback() {
					
					@Override
					public Object callback(Object param) {
						// TODO Auto-generated method stub
						updateDiaryItem((GeneralDiary)param);
						return null;
					}
				}).show();
				
			}
		});
		
		delDiaryItem = new JMenuItem("ɾ���ռ�");
		delDiaryItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int[] indies = searchTable.getSelectedRows();
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
					diaries.add(searchTableModel.getElementAt(index));
				}
				
				remvoeDiaryItems(diaries);
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
	
	/**
	 * �����޸��ռ���Ϣ
	 * @param diary
	 */
	public void updateDiaryItem(GeneralDiary diary) {
		service.saveDiary(diary);
		searchTable.updateUI();
	}
	
	/**
	 * ����ɾ���ռ�
	 * @param diaries
	 */
	private void remvoeDiaryItems(List<GeneralDiary> diaries) {
		for (GeneralDiary generalDiary : diaries) {
			service.deleteDiary(generalDiary);
			searchTableModel.remove(generalDiary);
		}
		
		searchTable.updateUI();
	}
	
	/**
	 * ��ѯ�������󻯲���
	 * @return
	 */
	private SearchParameters getParameters() {
		SearchParameters parameters = new SearchParameters();
		parameters.setTitle(this.titleField.getText());
		parameters.setsTime(this.beginDatePicker.getText());
		parameters.seteTime(this.endDatePicker.getText());
		parameters.setKeyword(this.keywordFiled.getText());
	
		return parameters;
	}
	
	/**
	 * �����������
	 */
	private void clearSearchParameters(){
		this.titleField.setText("");
		this.keywordFiled.setText("");
		searchTableModel.removeAllElements();
		searchTable.updateUI();
	}
	
	/**
	 * ��ѯ��Ӧ����
	 */
	private void searchDiaries() {
		SearchParameters parameters = getParameters();
		
		/**��ӿ�ʼ���������������ж�*/
		if (java.sql.Date.valueOf(parameters.getsTime()).after(java.sql.Date.valueOf(parameters.geteTime()))) {
			JOptionPane.showMessageDialog(null, "��ʼ������������Ч�����������ã�");
			return;
		}
		List<GeneralDiary> searchResult = service.getDiaries(parameters);
		showSearchResult(searchResult);
	}
	
	/**
	 * ���ؽ����ʾ����
	 * @param result
	 */
	private void showSearchResult(List<GeneralDiary> result){
		searchTableModel.removeAllElements();
		for (GeneralDiary generalDiary : result) {
			searchTableModel.add(generalDiary);	
		}
		searchTable.updateUI();
	}

}












