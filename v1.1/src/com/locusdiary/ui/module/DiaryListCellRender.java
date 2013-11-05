package com.locusdiary.ui.module;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;
import javax.swing.border.TitledBorder;

import com.locusdiary.module.GeneralDiary;

public class DiaryListCellRender extends JPanel implements ListCellRenderer {
	 private JLabel timeLabel = new JLabel();
	 private JLabel titleLabel = new JLabel();
	 private JLabel contentArea = new JLabel();
	    
	
	@Override
	 public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		  
		initComponents();
	    GeneralDiary diary = (GeneralDiary)value;
	    setDiaryDescriptionData(diary);
	     
	    Color background = Color.WHITE;
        if (isSelected) {
            background = new Color(0x87CEFA);
        }
        setBackground(background);
        contentArea.setBackground(background);
        
	    return this;
	 }
	
	 private void initComponents() {
	        setBorder(new TitledBorder(""));
	        setPreferredSize(new Dimension(425,80));

	    	timeLabel.setFont(new Font("ËÎÌו", 2, 12)); // NOI18N

	    	titleLabel.setFont(new Font("ËÎÌו", 2, 12)); // NOI18N
	    	
	    	setLayout(new GridLayout(3, 1));
	    	add(timeLabel);
	    	add(titleLabel);
	    	add(contentArea);
	    	
	 }
	 
	
	 public void setDiaryDescriptionData(GeneralDiary diary) {
			timeLabel.setText(diary.getBuildTime());
			titleLabel.setText(diary.getTitle());
			contentArea.setText(diary.getContent());
	 }

}
