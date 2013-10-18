package com.qqdiary.app;

import java.io.File;

import javax.swing.JOptionPane;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qqdiary.app.closure.ICallback;
import com.qqdiary.app.module.User;
import com.qqdiary.app.service.DiaryService;
import com.qqdiary.app.service.DiaryServiceImp;
import com.qqdiary.app.service.DiaryShare;
import com.qqdiary.app.service.UserVerify;
import com.qqdiary.app.ui.MainFrame;
import com.qqdiary.app.ui.UserDialog;

/**
 * 
 * @author Administrator
 *
 */
public class MainApp {
	
	public static final Log logger = LogFactory.getLog(MainApp.class);
	
	public static DiaryService service = new DiaryServiceImp();

	/**
	 * 自检测函数
	 * @return
	 */
	public static boolean selfDetection(){
		/**检测数据库文件是否存在*/
		File dbFile = new File("diary.db");
		if (!dbFile.exists()) {
			MainApp.logger.error("数据库文件不存在！");
			JOptionPane.showMessageDialog(null, "数据库文件不存在！");
			return false;
		}
		
		/**检测资源文件夹是否存在，不存在则创建*/
		File resFolder = new File("Media Resource");
		if (!resFolder.exists()) {
			MainApp.logger.error("资源文件夹不存在，需要新建！");
			resFolder.mkdir();
		}
		
		return true;
	}
	
	public static void startMainFrame(DiaryService service){
		
	}
	
	
	/**程序入口函数
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//进行信息监测
		if (!selfDetection()) {
			logger.error("自检测失败，退出程序！");
			return;
		}
		
		UserDialog userDialog = new UserDialog((UserVerify)service, new ICallback() {
			
			@Override
			public Object callback(Object param) {
				// TODO Auto-generated method stub
				new MainFrame(service, (DiaryShare)service).show();
				return null;
			}
		});
		
	}
	


}
