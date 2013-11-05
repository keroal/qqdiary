package com.locusdiary.module;

import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.locusdiary.module.GeneralAttachment.AttachmentStatus;
import com.locusdiary.ui.module.Thumbnail;

public class PictureAttachment extends GeneralAttachment{

	public PictureAttachment(GeneralDiary diary) {
		super(diary);
		// TODO Auto-generated constructor stub
		this.setType("图片");
	}

	@Override
	public void showAttachmentContent() {
		// TODO Auto-generated method stub
		String path = "";
		if (getStatus() == AttachmentStatus.EXIST  ) {
			path = System.getProperty("user.dir") + "\\" + getPath();
		}else {
			path = getPath();
		}
		
		File file = new File(path);
		if (!file.exists()) {
			JOptionPane.showMessageDialog( null , "文件不存在！");
			return;
		}
		try {
			Runtime.getRuntime().exec("rundll32 c:\\Windows\\System32\\shimgvw.dll,ImageView_Fullscreen "+path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public ImageIcon getAttachmentIcon() {
		// TODO Auto-generated method stub
		return new ImageIcon(Thumbnail.zoomImage(this.getPath(), 60, 60));
	}

	@Override
	public String generateDestPath() {
		// TODO Auto-generated method stub
		return resouceDir + getId() + ".png";
	}

}
