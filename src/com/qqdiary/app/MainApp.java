package com.qqdiary.app;

import java.io.File;

import javax.swing.JOptionPane;

import com.qqdiary.app.service.DiaryService;
import com.qqdiary.app.service.DiaryServiceImp;
import com.qqdiary.app.service.DiaryShare;
import com.qqdiary.app.ui.MainFrame;

/**
 * 
 * @author Administrator
 *
 */
public class MainApp {

	/**
	 * 自检测函数
	 * @return
	 */
	public static boolean selfDetection(){
		/**检测数据库文件是否存在*/
		File dbFile = new File("diary.db");
		if (!dbFile.exists()) {
			JOptionPane.showMessageDialog(null, "数据库文件不存在！");
			return false;
		}
		
		/**检测资源文件夹是否存在，不存在则创建*/
		File resFolder = new File("Media Resource");
		if (!resFolder.exists()) {
			resFolder.mkdir();
		}
		
		return true;
	}
	
	
	/**程序入口函数
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//进行信息监测
		if (!selfDetection()) {
			return;
		}
		
		DiaryService service = new DiaryServiceImp();
		new MainFrame(service, (DiaryShare)service).show();
		
	}
	


}
