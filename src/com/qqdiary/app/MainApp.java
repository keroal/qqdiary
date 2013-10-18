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
	 * �Լ�⺯��
	 * @return
	 */
	public static boolean selfDetection(){
		/**������ݿ��ļ��Ƿ����*/
		File dbFile = new File("diary.db");
		if (!dbFile.exists()) {
			MainApp.logger.error("���ݿ��ļ������ڣ�");
			JOptionPane.showMessageDialog(null, "���ݿ��ļ������ڣ�");
			return false;
		}
		
		/**�����Դ�ļ����Ƿ���ڣ��������򴴽�*/
		File resFolder = new File("Media Resource");
		if (!resFolder.exists()) {
			MainApp.logger.error("��Դ�ļ��в����ڣ���Ҫ�½���");
			resFolder.mkdir();
		}
		
		return true;
	}
	
	public static void startMainFrame(DiaryService service){
		
	}
	
	
	/**������ں���
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//������Ϣ���
		if (!selfDetection()) {
			logger.error("�Լ��ʧ�ܣ��˳�����");
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
