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
	 * �Լ�⺯��
	 * @return
	 */
	public static boolean selfDetection(){
		/**������ݿ��ļ��Ƿ����*/
		File dbFile = new File("diary.db");
		if (!dbFile.exists()) {
			JOptionPane.showMessageDialog(null, "���ݿ��ļ������ڣ�");
			return false;
		}
		
		/**�����Դ�ļ����Ƿ���ڣ��������򴴽�*/
		File resFolder = new File("Media Resource");
		if (!resFolder.exists()) {
			resFolder.mkdir();
		}
		
		return true;
	}
	
	
	/**������ں���
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//������Ϣ���
		if (!selfDetection()) {
			return;
		}
		
		DiaryService service = new DiaryServiceImp();
		new MainFrame(service, (DiaryShare)service).show();
		
	}
	


}
