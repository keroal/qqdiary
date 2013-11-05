package com.locusdiary.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.border.TitledBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import com.locusdiary.closure.ICallback;
import com.locusdiary.module.DiaryFolder;
import com.locusdiary.module.GeneralDiary;
import com.locusdiary.module.SessionManager;
import com.locusdiary.module.User;
import com.locusdiary.ui.module.ClassifyTreeCellRender;

public class ClassifyTreePanel extends JPanel {
	private ICallback callback;
	private JTree classifyTree;
	private DefaultTreeModel treeModel;
	private DefaultMutableTreeNode notesMutableTreeNode;
	private DefaultMutableTreeNode folderMutableTreeNode;
	private DefaultMutableTreeNode friendsMutableTreeNode;
	
	/** 
	* Name:			ClassifyTreePanel��
	* Description : Ĭ�Ϲ��캯�� ��
	* @param:		void ��
	* @return 		void��
	*/ 
	public ClassifyTreePanel(ICallback callback){
		this.callback = callback;
		setPreferredSize(new Dimension(250,1000));
		setBorder(new TitledBorder(""));
		setLayout(new BorderLayout());
		initComponents();
		ListDiariesByUser(SessionManager.getInstance().getUser());		
	}
	
	/** 
	* Name:			initComponents��
	* Description : ��ʼ������ؼ� ��
	* @param:		void ��
	* @return 		void��
	*/ 
	private void initComponents(){
		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("����");
		notesMutableTreeNode = new DefaultMutableTreeNode("ȫ���ռ�");
		folderMutableTreeNode = new DefaultMutableTreeNode("�Զ������");
		friendsMutableTreeNode = new DefaultMutableTreeNode("�����б�");
		rootNode.add(notesMutableTreeNode);
		rootNode.add(folderMutableTreeNode);
		rootNode.add(friendsMutableTreeNode);


		treeModel = new DefaultTreeModel(rootNode);
		classifyTree = new JTree(treeModel);		
		classifyTree.setCellRenderer(new ClassifyTreeCellRender());
		classifyTree.setRootVisible(false);

		classifyTree.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                classifyTreeMouseClicked(evt);
            }
        });
		
		TreeNode root = (TreeNode) classifyTree.getModel().getRoot();
	    expandTree(classifyTree, new TreePath(root));
		add(new JScrollPane(classifyTree), BorderLayout.CENTER);
	}
	
	/** 
	* Name:			expandTree��
	* Description : չ������ֻ�ڵ� ��
	* @param:		JTree tree, TreePath parent ��
	* @return 		void��
	*/ 
	private void expandTree(JTree tree, TreePath parent) {
	    TreeNode node = (TreeNode) parent.getLastPathComponent();
	    if (node.getChildCount() >= 0) {
	       for (Enumeration<?> e = node.children(); e.hasMoreElements();) {
	           TreeNode n = (TreeNode) e.nextElement();
	           TreePath path = parent.pathByAddingChild(n);
	           expandTree(tree, path);
	       }
	    }
	    tree.expandPath(parent);
	}
	
	/** 
	* Name:			classifyTreeMouseClicked��
	* Description : �������������Ӧ������
	* @param:		MouseEvent evt ��
	* @return 		void��
	*/ 
	private void classifyTreeMouseClicked(MouseEvent evt){
		if (evt.getClickCount() == 2) {
			DefaultMutableTreeNode node=(DefaultMutableTreeNode) classifyTree.getLastSelectedPathComponent();
			Object object =node.getUserObject();
			if (object instanceof String && ((String) object).contains("ȫ���ռ�")) {
				User user = SessionManager.getInstance().getUser();
				ListDiariesByUser(user);
			}else if (object instanceof String && ((String) object).contains("�Զ������")) {
				reloadFolderListItems();
			}else if (object instanceof String && ((String) object).contains("�����б�")) {
				reloadFriendListItems();
			}else if (object instanceof DiaryFolder) {
				ListDiraiesByFolder((DiaryFolder)object);
			}else if(object instanceof User) {
				ListDiariesByUser((User)object);
			}
		}
	}
	
	public void reloadFriendListItems(){
		friendsMutableTreeNode.removeAllChildren();
		List<User> friends = SessionManager.getInstance().getService().getFriends(SessionManager.getInstance().getUser());
		for (User user : friends) {
			addFriendNodeItem(user);
		}
	}
	
	public void reloadFolderListItems() {
		folderMutableTreeNode.removeAllChildren();
		List<DiaryFolder> folderList = SessionManager.getInstance().getService().getDiaryFolderList(SessionManager.getInstance().getUser());
		for (DiaryFolder diaryFolder : folderList) {
			addFolderNodeItem(diaryFolder);
		}
	}
	
	private void ListDiariesByUser(User user) {
		List<GeneralDiary> list = SessionManager.getInstance().getService().getDiaries(user);
		callback.callback(list);
	}
	
	private void ListDiraiesByFolder(DiaryFolder folder) {
		List<GeneralDiary> list = SessionManager.getInstance().getService().getDiaries(folder);
		callback.callback(list);
	}
	
	
	
	/** 
	* Name:			addFolderNodeItem��
	* Description : ����ռǷ���ڵ� ��
	* @param:		DiaryFolder��
	* @return 		void��
	*/ 
	public void addFolderNodeItem(DiaryFolder folder){
		treeModel.insertNodeInto(new DefaultMutableTreeNode(folder), folderMutableTreeNode, 0);
		updateTree();
	}
	
	/** 
	* Name:			addFriendNodeItem��
	* Description : ��Ӻ��ѽڵ� ��
	* @param:		User��
	* @return 		void��
	*/ 
	public void addFriendNodeItem(User user) {
		treeModel.insertNodeInto(new DefaultMutableTreeNode(user), friendsMutableTreeNode, 0);	
		updateTree();
	}
	
	/** 
	* Name:			getSelectedFolderNodeItem��
	* Description : ��ȡ�ռǷ���ڵ����ݶ��� ��
	* @param:		void��
	* @return 		DiaryFolder��
	*/ 
	public DiaryFolder getSelectedFolderNodeItem(){
		DefaultMutableTreeNode node=(DefaultMutableTreeNode)classifyTree.getLastSelectedPathComponent();
		if (node != null) {
			Object obj = node.getUserObject();
			if (obj instanceof DiaryFolder) {
				return (DiaryFolder)obj;
			}
		}
		return null;
	}
	
	/** 
	* Name:			getSelectedUserNodeItem��
	* Description : ��ȡ���ѽڵ����ݶ��� ��
	* @param:		void��
	* @return 		void��
	*/ 
	public User getSelectedUserNodeItem(){
		DefaultMutableTreeNode node=(DefaultMutableTreeNode)classifyTree.getLastSelectedPathComponent();
		if (node != null) {
			Object obj = node.getUserObject();
			if (obj instanceof User) {
				return (User)obj;
			}
		}
		return null;
	}
	
	/** 
	* Name:			deleteSelectedNodeItem��
	* Description : ɾ��ѡ��JTreee���ݽڵ� ��
	* @param:		void��
	* @return 		void��
	*/ 
	public void deleteSelectedNodeItem() {
		DefaultMutableTreeNode node=(DefaultMutableTreeNode)classifyTree.getLastSelectedPathComponent();
		treeModel.removeNodeFromParent(node);
		updateTree();
	}
	
	
	
	/** 
	* Name:			updateTree��
	* Description : ����JTreeUI ��
	* @param:		void��
	* @return 		void��
	*/ 
	public void updateTree() {
		classifyTree.updateUI();
	}
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
