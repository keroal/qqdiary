package com.locusdiary.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.border.TitledBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import com.locusdiary.closure.ICallback;
import com.locusdiary.module.DiaryFolder;
import com.locusdiary.module.GeneralComment;
import com.locusdiary.module.GeneralDiary;
import com.locusdiary.module.SearchParameters;
import com.locusdiary.module.SessionManager;
import com.locusdiary.module.User;
import com.locusdiary.ui.module.DiaryListCellRender;
import com.locusdiary.ui.module.ClassifyTreeCellRender;
import com.locusdiary.ui.module.PopupMenuLabel;

public class MainFrame extends JFrame {
	//�˵��ؼ�
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenu compileMenu;
	private JMenu toolMenu;
	private JMenu helpMenu;
	private JMenuItem createDiaryMenuItem;
	private JMenuItem createFolderMenuItem;
	private JMenuItem addFriendMenuItem;
	private JMenuItem exitMenuItem;
	private JMenuItem compileDiaryMenuItem;
	private JMenuItem compileFolderMenuItem;
	private JMenuItem deleteDiaryMenuItem;
	private JMenuItem deleteFolderMenuItem;
	private JMenuItem deleteFriendMenuItem;
	private JMenuItem shareDiaryMenuItem;
	private JMenuItem configMenuItem;
	private JMenuItem accountMenuItem;
	private JMenuItem aboutMenuItem;
	
	//�������ؼ�
	private JToolBar toolBar;
	private JLabel userPopupLabel;
	private JButton writeDiaryButton;
	private JButton shareDiaryButton;
	private JButton searchDiarybButton;
	private JButton commentDiaryButton;
	
	private JPanel mainPanel;
	private ClassifyTreePanel classifyPanel;
	private DiaryListPanel diaryListPanel;
	private DiaryViewPanel diaryViewPanel;
	
	private AboutDialog aboutDialog;
	private SearchDialog searchDialog;

	/** 
	* Name:			initComponents��
	* Description : Ĭ�Ϲ��캯��
	*/ 
	public MainFrame(){
		setTitle("�����ռ�");
		setSize(1300, 650);
		setLayout(new GridLayout());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		addWindowListener(new WindowAdapter(){ 
			public void windowClosing(WindowEvent evt){ 
				exitSystem();
		     } 
		 }); 
	
		initComponents();
		
	}
	
	/** 
	* Name:			initComponents��
	* Description : ���������ʼ�� ��
	* @param:		void ��
	* @return 		void��
	*/ 
	private void initComponents() {
		initMenuComponents();
		initMPanelComponents();
		
		setJMenuBar(menuBar);
		add(mainPanel);
		aboutDialog = new AboutDialog();
		searchDialog = new SearchDialog(new ICallback() {
			@Override
			public Object callback(Object param) {
				// TODO Auto-generated method stub
				List<GeneralDiary> list = SessionManager.getInstance().getService().getDiaries((SearchParameters)param);
				diaryListPanel.showDiariesDescription(list);
				return null;
			}
		});
	}
	
	/** 
	* Name:			initMenuComponents��
	* Description : ��ʼ���˵��ؼ� ��
	* @param:		void ��
	* @return 		void��
	*/ 
	private void initMenuComponents() {

		menuBar = new JMenuBar();
		fileMenu = new JMenu("�ļ�");
		createDiaryMenuItem = new JMenuItem("�½��ռ�");
		createDiaryMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	writeDiaryActionPerformed(evt);
            }
        });
		
		createFolderMenuItem = new JMenuItem("�½�����");
		createFolderMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	createFolderActionPerformed(evt);
            }
        });
		
		addFriendMenuItem = new JMenuItem("��Ӻ���");
		addFriendMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	addFriendActionPerformed(evt);
            }
        });
		
		exitMenuItem = new JMenuItem("�˳�");
		exitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	exitActionPerformed(evt);
            }
        });
		
		compileMenu = new JMenu("����");
		compileDiaryMenuItem = new JMenuItem("�༭�ռ�");
		compileDiaryMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	compileDiaryActionPerformed(evt);
            }
        });
		
		compileFolderMenuItem = new JMenuItem("�༭����");
		compileFolderMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	compileFolderActionPerformed(evt);
            }
        });
		
		deleteDiaryMenuItem = new JMenuItem("ɾ���ռ�");
		deleteDiaryMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	deleteDiaryActionPerformed(evt);
            }
        });
		
		deleteFolderMenuItem = new JMenuItem("ɾ������");
		deleteFolderMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	deleteFolderActionPerformed(evt);
            }
        });
		
		deleteFriendMenuItem = new JMenuItem("ɾ������");
		deleteFriendMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	deleteFriendActionPerformed(evt);
            }
        });
		
		shareDiaryMenuItem = new JMenuItem("����");
		shareDiaryMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	shareDiaryActionPerformed(evt);
            }
        });
		
		toolMenu = new JMenu("����");
		configMenuItem = new JMenuItem("��������");
		configMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	configActionPerformed(evt);
            }
        });
		
		accountMenuItem = new JMenuItem("�˺�");
		accountMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	accountActionPerformed(evt);
            }
        });
		
		helpMenu = new JMenu("����");
		aboutMenuItem = new JMenuItem("����");
		aboutMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	aboutActionPerformed(evt);
            }
        });
		
		fileMenu.add(createDiaryMenuItem);
		fileMenu.add(createFolderMenuItem);
		fileMenu.add(addFriendMenuItem);
		fileMenu.add(exitMenuItem);
		compileMenu.add(compileDiaryMenuItem);
		compileMenu.add(compileFolderMenuItem);
		compileMenu.add(deleteDiaryMenuItem);
		compileMenu.add(deleteFolderMenuItem);
		
		toolMenu.add(configMenuItem);
		toolMenu.add(accountMenuItem);
		toolMenu.add(shareDiaryMenuItem);
		helpMenu.add(aboutMenuItem);
		
		menuBar.add(fileMenu);
		menuBar.add(compileMenu);
		//menuBar.add(toolMenu);
		menuBar.add(helpMenu);
	}
	
	/** 
	* Name:			initToolbarComponents��
	* Description : ��ʼ���������ؼ� ��
	* @param:		void ��
	* @return 		void��
	*/ 
	private void initToolbarComponents() {
		toolBar = new JToolBar();
		userPopupLabel = new JLabel(SessionManager.getInstance().getUser().getUsername());
		userPopupLabel.setIcon(new ImageIcon(this.getClass().getResource("res/user.png")));
	
		writeDiaryButton = new JButton("д�ռ�");
		writeDiaryButton.setIcon(new ImageIcon(this.getClass().getResource("res/write.png")));
		writeDiaryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	writeDiaryActionPerformed(evt);
            }
        });
		
		shareDiaryButton = new JButton("����");
		shareDiaryButton.setIcon(new ImageIcon(this.getClass().getResource("res/share.png")));
		shareDiaryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	shareDiaryActionPerformed(evt);
            }
        });
		
		searchDiarybButton = new JButton("��ѯ");
		searchDiarybButton.setIcon(new ImageIcon(this.getClass().getResource("res/search.png")));
		searchDiarybButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	searchDiaryActionPerformed(evt);
            }
        });
		
		commentDiaryButton = new JButton("�������");
		commentDiaryButton.setIcon(new ImageIcon(this.getClass().getResource("res/comment.png")));
		commentDiaryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	commentDiaryActionPerformed(evt);
            }
        });
		
		toolBar.add(userPopupLabel);
		toolBar.addSeparator();
		toolBar.add(writeDiaryButton);
		toolBar.add(commentDiaryButton);
		toolBar.add(shareDiaryButton);
		toolBar.add(searchDiarybButton);
		
	}
	
	/** 
	* Name:			initMPanelComponents��
	* Description : ��ʼ���������ؼ� ��
	* @param:		void ��
	* @return 		void��
	*/ 
	private void initMPanelComponents() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		initToolbarComponents();

		diaryListPanel = new DiaryListPanel(new ICallback() {
			@Override
			public Object callback(Object param) {
				// TODO Auto-generated method stub
				modifyDiaryInformation();
				return null;
			}
		}, new ICallback() {
			
			@Override
			public Object callback(Object param) {
				// TODO Auto-generated method stub
				showDiaryInDetail();
				return null;
			}
		});
		
		diaryViewPanel = new DiaryViewPanel();
		
		classifyPanel = new ClassifyTreePanel(new ICallback() {
			@Override
			public Object callback(Object param) {
				// TODO Auto-generated method stub
				showDiaryList((List<GeneralDiary>)param);
				return null;
			}
		});

		JSplitPane firstSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, false, classifyPanel,diaryListPanel);
		firstSplitPane.setDividerLocation(0.45);
		firstSplitPane.setDividerSize(2);
		
		JSplitPane secondSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, false, firstSplitPane, diaryViewPanel);
		secondSplitPane.setDividerLocation(0.5);
		secondSplitPane.setDividerSize(2);
		secondSplitPane.setBorder(new TitledBorder(""));
		
		mainPanel.add(toolBar, BorderLayout.NORTH);
		mainPanel.add(secondSplitPane, BorderLayout.CENTER);
	}
	
	
	/**�¼���Ӧ����*/
	private void writeDiaryActionPerformed(ActionEvent evt) {                                             
		// TODO add your handling code here:
		List<DiaryFolder> folderList = SessionManager.getInstance().getService().getDiaryFolderList(SessionManager.getInstance().getUser());
		if (folderList.isEmpty()) {
			JOptionPane.showMessageDialog(null, "�����ȡʧ�ܣ���ȷ���ѽ������࣡");
			return;
		}
		new DiaryDialog(folderList, new ICallback() {
			@Override
			public Object callback(Object param) {
				// TODO Auto-generated method stub
				GeneralDiary diary = (GeneralDiary)param;
				SessionManager.getInstance().getService().saveDiary(diary);
				DiaryFolder folder = new DiaryFolder();
				folder.setId(diary.getFolderid());
				diaryListPanel.showDiariesDescription(SessionManager.getInstance().getService().getDiaries(folder));
				diaryViewPanel.showDiaryInformation(diaryListPanel.getFirstDiary());
				return null;
			}
		}).show();
	} 
	
	private void createFolderActionPerformed(ActionEvent evt) {                                             
		// TODO add your handling code here:
		new FolderDialog(new ICallback() {
			
			@Override
			public Object callback(Object param) {
				// TODO Auto-generated method stub
				DiaryFolder folder = (DiaryFolder)param;
				if (!SessionManager.getInstance().getService().saveDiaryFolder(folder)) {
					JOptionPane.showMessageDialog(null, "�����Ѿ����ڣ�");
					return null;
				}
				classifyPanel.reloadFolderListItems();
				return null;
			}
		}).show();
	} 
	
	private void addFriendActionPerformed(ActionEvent evt) {                                             
		// TODO add your handling code here:
		new AddFriendDialog(new ICallback() {
			@Override
			public Object callback(Object param) {
				// TODO Auto-generated method stub
				if (SessionManager.getInstance().getService().addFriend(SessionManager.getInstance().getUser(), (User)param)) {
					JOptionPane.showMessageDialog(null, "��Ӻ���ʧ�ܣ������Ѿ����ڣ�");
					return null;
				}
				classifyPanel.addFriendNodeItem((User)param);
				return null;
			}
		}).show();
	} 
	
	private void exitActionPerformed(ActionEvent evt) {                                             
		// TODO add your handling code here:
		exitSystem();
	} 
	
	private void compileDiaryActionPerformed(ActionEvent evt) {                                             
		// TODO add your handling code here:
		modifyDiaryInformation();
	} 
	
	private void compileFolderActionPerformed(ActionEvent evt) {                                             
		// TODO add your handling code here:
		DiaryFolder folder = classifyPanel.getSelectedFolderNodeItem();
		if (folder == null) {
			JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�༭�ķ��࣡");
			return;
		}
		
		new FolderDialog(new ICallback() {
			
			@Override
			public Object callback(Object param) {
				// TODO Auto-generated method stub
				SessionManager.getInstance().getService().saveDiaryFolder((DiaryFolder)param);
				return null;
			}
		}).show();
	} 
	
	private void deleteDiaryActionPerformed(ActionEvent evt) {                                             
		// TODO add your handling code here:
		GeneralDiary diary = diaryListPanel.getSelectedDiary();
		if (diary == null) {
			JOptionPane.showMessageDialog(null, "��ѡ����Ҫɾ�����ռǣ�");
			return;
		}
		
		String duid = diary.getUserid();
		String uid = SessionManager.getInstance().getUser().getUserid();
		if (!duid.equals(uid)) {
			JOptionPane.showMessageDialog(null, "�޷�ɾ�������ռ�");
			return;
		}
		
		SessionManager.getInstance().getService().deleteDiary(diaryListPanel.getSelectedDiary());
		diaryListPanel.deleteSelectedDiary();
		diaryViewPanel.clearOriginalData();
		JOptionPane.showMessageDialog(null, "ɾ���ɹ���");
	}
	
	private void deleteFolderActionPerformed(ActionEvent evt) {                                             
		// TODO add your handling code here:
		if (classifyPanel.getSelectedFolderNodeItem() == null) {
			JOptionPane.showMessageDialog(null, "��ѡ��Ҫɾ���ķ��࣡");
			return;
		}
		
		SessionManager.getInstance().getService().deleteFolder(classifyPanel.getSelectedFolderNodeItem());
		classifyPanel.deleteSelectedNodeItem();
		JOptionPane.showMessageDialog(null, "ɾ���ɹ���");
	} 
	
	private void deleteFriendActionPerformed(ActionEvent evt) {                                             
		// TODO add your handling code here:
		if (classifyPanel.getSelectedUserNodeItem() == null) {
			JOptionPane.showMessageDialog(null, "��ѡ��Ҫɾ���ĺ��ѣ�");
			return;
		}
		
		SessionManager.getInstance().getService().deleteFolder(classifyPanel.getSelectedFolderNodeItem());
		classifyPanel.deleteSelectedNodeItem();
		JOptionPane.showMessageDialog(null, "ɾ���ɹ���");
	} 
	
	private void configActionPerformed(ActionEvent evt) {                                             
		// TODO add your handling code here:
		
	} 
	
	private void accountActionPerformed(ActionEvent evt) {                                             
		// TODO add your handling code here:
	} 
	
	private void aboutActionPerformed(ActionEvent evt) {                                             
		// TODO add your handling code here:
		aboutDialog.show();
	} 

    private void shareDiaryActionPerformed(ActionEvent evt) {                                             
        // TODO add your handling code here:
    	JOptionPane.showMessageDialog(null, "�˹��ܻ�δʵ��!");
    }    
	   
    private void searchDiaryActionPerformed(ActionEvent evt) {                                             
        // TODO add your handling code here:
    	searchDialog.show();
    }                                            

    private void commentDiaryActionPerformed(ActionEvent evt) {                                             
        // TODO add your handling code here:
    	if (diaryListPanel.getSelectedDiary() == null) {
			JOptionPane.showMessageDialog(null, "��ѡ����Ҫ���۵��ռǣ�");
			return;
		}
    	
    	new CommentDialog(diaryListPanel.getSelectedDiary(), new ICallback() {
			
			@Override
			public Object callback(Object param) {
				// TODO Auto-generated method stub
				SessionManager.getInstance().getService().addComment((GeneralComment)param);
				diaryViewPanel.addDiaryCommentItem((GeneralComment)param);
				return null;
			}
		}).show();
    	
    }                                            

	private void modifyDiaryInformation() {
		GeneralDiary diary = diaryListPanel.getSelectedDiary();
		if (diary == null) {
			JOptionPane.showMessageDialog(null, "��ѡ����Ҫ�༭���ռǣ�");
			return;
		}
		
		String duid = diary.getUserid();
		String uid = SessionManager.getInstance().getUser().getUserid();
		if (!duid.equals(uid)) {
			JOptionPane.showMessageDialog(null, "�ⲻ�������ռǣ����ܽ����޸ģ�");
			return;
		}
		
		List<DiaryFolder> folderList = SessionManager.getInstance().getService().getDiaryFolderList(SessionManager.getInstance().getUser());
		if (folderList.isEmpty()) {
			JOptionPane.showMessageDialog(null, "�����ȡʧ�ܣ���ȷ���ѽ������࣡");
			return;
		}
		new DiaryDialog(diary, folderList, new ICallback() {
			@Override
			public Object callback(Object param) {
				// TODO Auto-generated method stub
				SessionManager.getInstance().getService().saveDiary((GeneralDiary)param);
				return null;
			}
		}).show();
	}
	

	private void showDiaryInDetail(){
		GeneralDiary diary = diaryListPanel.getSelectedDiary();
		if (diary != null) {
			diaryViewPanel.showDiaryInformation(diary);
		}
	}
	
	private void showDiaryList(List<GeneralDiary> list){
		diaryListPanel.showDiariesDescription(list);
		diaryViewPanel.showDiaryInformation(diaryListPanel.getFirstDiary());
	}
	
	
	private void exitSystem() {
		int n = JOptionPane.showConfirmDialog(null, "���Ҫ�˳���", "��ʾ", JOptionPane.YES_NO_OPTION);
		if (n == 0) {
			System.exit(0);
		}
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
		
		new MainFrame().show();
	}

}
