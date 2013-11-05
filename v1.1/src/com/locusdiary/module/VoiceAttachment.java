package com.locusdiary.module;

import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.locusdiary.ui.ImageIconFactory;
import com.locusdiary.ui.module.Thumbnail;

public class VoiceAttachment extends GeneralAttachment{

	public VoiceAttachment(GeneralDiary diary) {
		super(diary);
		// TODO Auto-generated constructor stub
		this.setType("语音");
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
			JOptionPane.showMessageDialog( null , "附件文件不存在！");
			return;
		}
		
		try {
			Runtime.getRuntime().exec("\"C:\\Program Files\\Windows Media Player\\wmplayer.exe\" \""+ path + "\"");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	@Override
	public ImageIcon getAttachmentIcon() {
		// TODO Auto-generated method stub
		return ImageIconFactory.getInstance().getImageIcon("voice.png");
	}

	@Override
	public String generateDestPath() {
		// TODO Auto-generated method stub
		return resouceDir + getId() + ".wav";
	}

}
