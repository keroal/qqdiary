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
	* Name:			ClassifyTreePanel；
	* Description : 默认构造函数 ；
	* @param:		void ；
	* @return 		void；
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
	* Name:			initComponents；
	* Description : 初始化界面控件 ；
	* @param:		void ；
	* @return 		void；
	*/ 
	private void initComponents(){
		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("分类");
		notesMutableTreeNode = new DefaultMutableTreeNode("全部日记");
		folderMutableTreeNode = new DefaultMutableTreeNode("自定义分类");
		friendsMutableTreeNode = new DefaultMutableTreeNode("好友列表");
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
	* Name:			expandTree；
	* Description : 展开树的只节点 ；
	* @param:		JTree tree, TreePath parent ；
	* @return 		void；
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
	* Name:			classifyTreeMouseClicked；
	* Description : 分类树鼠标点击相应函数；
	* @param:		MouseEvent evt ；
	* @return 		void；
	*/ 
	private void classifyTreeMouseClicked(MouseEvent evt){
		if (evt.getClickCount() == 2) {
			DefaultMutableTreeNode node=(DefaultMutableTreeNode) classifyTree.getLastSelectedPathComponent();
			Object object =node.getUserObject();
			if (object instanceof String && ((String) object).contains("全部日记")) {
				User user = SessionManager.getInstance().getUser();
				ListDiariesByUser(user);
			}else if (object instanceof String && ((String) object).contains("自定义分类")) {
				reloadFolderListItems();
			}else if (object instanceof String && ((String) object).contains("好友列表")) {
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
	* Name:			addFolderNodeItem；
	* Description : 添加日记分类节点 ；
	* @param:		DiaryFolder；
	* @return 		void；
	*/ 
	public void addFolderNodeItem(DiaryFolder folder){
		treeModel.insertNodeInto(new DefaultMutableTreeNode(folder), folderMutableTreeNode, 0);
		updateTree();
	}
	
	/** 
	* Name:			addFriendNodeItem；
	* Description : 添加好友节点 ；
	* @param:		User；
	* @return 		void；
	*/ 
	public void addFriendNodeItem(User user) {
		treeModel.insertNodeInto(new DefaultMutableTreeNode(user), friendsMutableTreeNode, 0);	
		updateTree();
	}
	
	/** 
	* Name:			getSelectedFolderNodeItem；
	* Description : 获取日记分类节点数据对象 ；
	* @param:		void；
	* @return 		DiaryFolder；
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
	* Name:			getSelectedUserNodeItem；
	* Description : 获取好友节点数据对象 ；
	* @param:		void；
	* @return 		void；
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
	* Name:			deleteSelectedNodeItem；
	* Description : 删除选中JTreee数据节点 ；
	* @param:		void；
	* @return 		void；
	*/ 
	public void deleteSelectedNodeItem() {
		DefaultMutableTreeNode node=(DefaultMutableTreeNode)classifyTree.getLastSelectedPathComponent();
		treeModel.removeNodeFromParent(node);
		updateTree();
	}
	
	
	
	/** 
	* Name:			updateTree；
	* Description : 根据JTreeUI ；
	* @param:		void；
	* @return 		void；
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
