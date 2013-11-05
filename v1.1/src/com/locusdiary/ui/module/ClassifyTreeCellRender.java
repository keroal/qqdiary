package com.locusdiary.ui.module;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;

import com.locusdiary.module.DiaryFolder;
import com.locusdiary.module.User;
import com.locusdiary.ui.ImageIconFactory;

public class ClassifyTreeCellRender extends JPanel  implements TreeCellRenderer {
	private JLabel label = new JLabel();
	public Component  getTreeCellRendererComponent(JTree tree,Object value,boolean selected,boolean expanded,boolean leaf,int row,boolean hasFocus){
	
		DefaultMutableTreeNode node=(DefaultMutableTreeNode)value;
		Object obj=node.getUserObject();
		if(obj instanceof String){
			String name = (String)obj;
			label.setText(name);
			
			switch (name) {
			case "全部日记":
				label.setIcon(ImageIconFactory.getInstance().getImageIcon("notes.png"));				
				break;
			case "自定义分类":
				label.setIcon(ImageIconFactory.getInstance().getImageIcon("folder.png"));
				break;
			case "好友列表":
				label.setIcon(ImageIconFactory.getInstance().getImageIcon("friends.png"));
				break;
			default:
				break;
			}
	        label.setFont(new Font("微软雅黑", 0, 18));
		}else if(obj instanceof User) {
			label.setIcon(null);
			label.setText(((User)obj).getUsername());
	        label.setFont(new Font("宋体", 0, 14));
		}else if(obj instanceof DiaryFolder) {
			label.setIcon(null);
			label.setText(((DiaryFolder)obj).getName());
	        label.setFont(new Font("宋体", 0, 14));
		}
		
		Color background = Color.WHITE;
        Color foreground = Color.BLACK;
        if (selected) {
            foreground =  new Color(0x87CEFA);
        }
        setBackground(background);
        label.setForeground(foreground);

		add(label);
		return this;
	}
}
