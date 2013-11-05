package com.locusdiary.app;

import java.awt.Dialog;
import java.io.File;

import javax.swing.JOptionPane;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.locusdiary.closure.ICallback;
import com.locusdiary.listener.FileLogger;
import com.locusdiary.module.SessionManager;
import com.locusdiary.module.User;
import com.locusdiary.ui.LoginDialog;
import com.locusdiary.ui.MainFrame;

public class MainApp {
	//public static final Log logger = LogFactory.getLog(MainApp.class);
	
	/**
	 * 自检测函数
	 * @return
	 */
	public static boolean selfDetection(){
		/**检测数据库文件是否存在*/
		File dbFile = new File("diary.db");
		if (!dbFile.exists()) {
			//MainApp.logger.error("数据库文件不存在！");
			JOptionPane.showMessageDialog(null, "数据库文件不存在！");
			return false;
		}
		
		/**检测资源文件夹是否存在，不存在则创建*/
		File resFolder = new File("Media Resource");
		if (!resFolder.exists()) {
			//MainApp.logger.error("资源文件夹不存在，需要新建！");
			resFolder.mkdir();
		}
		
		return true;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		//进行信息监测
		if (!selfDetection()) {
			FileLogger.getInstance().logError("自检测失败，退出程序！");
			return;
		}
	
		new LoginDialog(new ICallback() {
			
			@Override
			public Object callback(Object param) {
				// TODO Auto-generated method stub
				new MainFrame().show();
				return null;
			}
		}).show();
	}

}
