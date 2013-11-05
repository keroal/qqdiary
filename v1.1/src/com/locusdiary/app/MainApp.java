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
	 * �Լ�⺯��
	 * @return
	 */
	public static boolean selfDetection(){
		/**������ݿ��ļ��Ƿ����*/
		File dbFile = new File("diary.db");
		if (!dbFile.exists()) {
			//MainApp.logger.error("���ݿ��ļ������ڣ�");
			JOptionPane.showMessageDialog(null, "���ݿ��ļ������ڣ�");
			return false;
		}
		
		/**�����Դ�ļ����Ƿ���ڣ��������򴴽�*/
		File resFolder = new File("Media Resource");
		if (!resFolder.exists()) {
			//MainApp.logger.error("��Դ�ļ��в����ڣ���Ҫ�½���");
			resFolder.mkdir();
		}
		
		return true;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		//������Ϣ���
		if (!selfDetection()) {
			FileLogger.getInstance().logError("�Լ��ʧ�ܣ��˳�����");
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
