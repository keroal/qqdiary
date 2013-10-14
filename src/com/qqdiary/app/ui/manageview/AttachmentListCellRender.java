package com.qqdiary.app.ui.manageview;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;

import com.qqdiary.app.module.DiaryAttachment;
import com.qqdiary.app.module.DiaryFolder;
import com.qqdiary.app.ui.IconImageFactory;

/**
 * 附件JList显示样式类
 * @author Administrator
 *
 */
public class AttachmentListCellRender  extends DefaultListCellRenderer {

	 @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		 if(!(value instanceof DiaryAttachment))
	       	return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
	      JLabel jl = (JLabel)super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
	      DiaryAttachment attachment = (DiaryAttachment) value;
	      jl.setIcon(selectAttachmentIcon(attachment)); 
	      jl.setText(attachment.getType());
	      jl.setToolTipText(attachment.getPath());
	      return jl;
	   }
	 
	 private ImageIcon selectAttachmentIcon(DiaryAttachment attachment){
		 String name = IconImageFactory.DEFAULT_ICON_PATH;
		 
		 if (attachment.getType().contains("图片")) {
			 name = "picture.png";
		 }else if (attachment.getType().contains("语音")) {
			 name = "music.png";
		}
		 
		return (ImageIcon)IconImageFactory.getInstance().createIconByName(name);
	 }
	
}
