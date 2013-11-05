package com.locusdiary.ui.module;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.ListCellRenderer;
import javax.swing.tree.TreeCellRenderer;

import com.locusdiary.module.GeneralAttachment;
import com.locusdiary.module.GeneralDiary;
import com.locusdiary.module.PictureAttachment;
import com.locusdiary.module.VoiceAttachment;

public class AttachmentListCellRender  extends JPanel  implements ListCellRenderer {
	private JLabel label = new JLabel();

	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
			// TODO Auto-generated method stub
		
		GeneralAttachment attachment = (GeneralAttachment)value;
		label.setIcon(attachment.getAttachmentIcon());
		
		Color background = Color.WHITE;
	    if (isSelected) {
	        background = new Color(0x87CEFA);
	    }
	    setBackground(background);
		
	    setPreferredSize(new Dimension(70,70));
		add(label);
		return this;
	}

}
