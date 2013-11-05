package com.locusdiary.ui.module;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;
import javax.swing.border.TitledBorder;

import com.locusdiary.module.GeneralComment;
import com.locusdiary.module.GeneralDiary;
import com.locusdiary.module.User;

public class CommentListCellRender extends JPanel implements ListCellRenderer {
	private JLabel timeLabel = new JLabel();
	private JLabel nameLabel = new JLabel();
	private JLabel nullLabel = new JLabel();
	private JTextArea contentArea = new JTextArea();
	private JPanel titlePanel = new JPanel();

	 public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		  
		 initComponents();
		 GeneralComment comment = (GeneralComment)value;
		 setCommentDescriptionData(comment);
		 
		 Color background = Color.WHITE;
	     if (isSelected) {
	         background = new Color(0x87CEFA);
	     }
	     titlePanel.setBackground(background);
	     contentArea.setBackground(background);
	     return this;
	   }
	 
	 private void initComponents() {
		 setBorder(new TitledBorder(""));
		 setPreferredSize(new Dimension(600,60));
		 timeLabel.setFont(new Font("ËÎÌå", 2, 12));

 		 nameLabel.setFont(new Font("·ÂËÎ", 2, 12));
			
		 titlePanel.setLayout(new GridLayout(1, 2));
		 titlePanel.setBackground(Color.white);
		 titlePanel.add(timeLabel);
		 titlePanel.add(nameLabel);

		 contentArea.setLineWrap(true);
		 contentArea.setFont(new Font("·ÂËÎ", 2, 12));
			
		 setLayout(new GridLayout(2, 1));
		 add(titlePanel);
		 add(contentArea);
	}
		
	public void setCommentDescriptionData(GeneralComment comment){
			timeLabel.setText(comment.getPublishTime());
			nameLabel.setText(comment.getPublishUser().getUserid());
			contentArea.setText(comment.getContent());		
	}

}
