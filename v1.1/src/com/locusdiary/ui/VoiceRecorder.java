package com.locusdiary.ui;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import java.io.ByteArrayOutputStream;

import java.io.File;

import java.io.InputStream;

import java.awt.BorderLayout;
import java.awt.Button;

import java.awt.Frame;

import java.awt.FlowLayout;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.awt.event.WindowAdapter;

import java.awt.event.WindowEvent;

import javax.sound.sampled.AudioFileFormat;

import javax.sound.sampled.AudioFormat;

import javax.sound.sampled.AudioInputStream;

import javax.sound.sampled.AudioSystem;

import javax.sound.sampled.DataLine;

import javax.sound.sampled.SourceDataLine;

import javax.sound.sampled.TargetDataLine;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.locusdiary.closure.*;
import com.locusdiary.module.GeneralAttachment;
import com.locusdiary.module.GeneralDiary;
import com.locusdiary.module.TimeHelper;

/**
 * ¼����������
 * @author Administrator
 *
 */
public class VoiceRecorder extends JDialog {

	private ICallback callback;
	private GeneralAttachment attachment;
    private boolean stopCapture = false;       //����¼����־
    private AudioFormat audioFormat;           //¼����ʽ

    //��ȡ���ݣ���TargetDataLineд��ByteArrayOutputStream¼��
   private ByteArrayOutputStream byteArrayOutputStream;
   private int totaldatasize = 0;
   private TargetDataLine targetDataLine;

    //�������ݣ���AudioInputStreamд��SourceDataLine����
   private AudioInputStream audioInputStream;
   private SourceDataLine sourceDataLine;
   
   private JButton captureBtn = new JButton("¼��");
   private JButton stopBtn = new JButton("ֹͣ");
   private JButton playBtn = new JButton("����");
   private JButton saveBtn = new JButton("����");

    public VoiceRecorder(GeneralAttachment attachment, ICallback callback) {
    	this.attachment = attachment;
        this.callback = callback;
        initComponent();
    }
    
    private void initComponent(){
    	setTitle("¼����");
    	setSize(300, 80);
		this.setLocationRelativeTo(null);
    	this.setLayout(new BorderLayout());
    	

    	captureBtn = new JButton("¼��");
    	stopBtn = new JButton("ֹͣ");
    	playBtn = new JButton("����");
    	saveBtn = new JButton("����");
        captureBtn.setEnabled(true);
        stopBtn.setEnabled(false);
        playBtn.setEnabled(false);
        saveBtn.setEnabled(false);

        //���ô�������
    	JPanel panel = new JPanel();
    	panel.setLayout(new FlowLayout());
        panel.add(captureBtn);
        panel.add(stopBtn);
        panel.add(playBtn);
        panel.add(saveBtn);
        this.add(panel, "Center");

        //ע��¼���¼�
        captureBtn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                captureBtn.setEnabled(false);
                stopBtn.setEnabled(true);
                playBtn.setEnabled(false);
                saveBtn.setEnabled(false);

                //��ʼ¼��
                capture();
            }
        });


        //ע��ֹͣ�¼�
        stopBtn.addActionListener(new ActionListener() {
        	

            public void actionPerformed(ActionEvent e) {

                captureBtn.setEnabled(true);
                stopBtn.setEnabled(false);
                playBtn.setEnabled(true);
                saveBtn.setEnabled(true);

                //ֹͣ¼��
                stop();
            }
        });


        //ע�Ქ���¼�
        playBtn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                //����¼��
                play();
            }
        });


        //ע�ᱣ���¼�
        saveBtn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                //����¼��
                save();
            }
        });
    }

    //¼���¼������浽ByteArrayOutputStream��
    private void capture() {

        try {

            //��¼��

            audioFormat = getAudioFormat();

            DataLine.Info dataLineInfo = new DataLine.Info(

                    TargetDataLine.class, audioFormat);

            targetDataLine = (TargetDataLine) AudioSystem.getLine(dataLineInfo);

            targetDataLine.open(audioFormat);

            targetDataLine.start();

            //���������߳̽���¼��

            Thread captureThread = new Thread(new CaptureThread());

            captureThread.start();

        } catch (Exception e) {

            e.printStackTrace();

            System.exit(0);

        }

    }

    //��2������ByteArrayOutputStream�е�����
    private void play() {

        try {

            //ȡ��¼������

            byte audioData[] = byteArrayOutputStream.toByteArray();

            //ת����������

            InputStream byteArrayInputStream = new ByteArrayInputStream(

                    audioData);

            AudioFormat audioFormat = getAudioFormat();

            audioInputStream = new AudioInputStream(byteArrayInputStream,

                    audioFormat, audioData.length / audioFormat.getFrameSize());

            DataLine.Info dataLineInfo = new DataLine.Info(

                    SourceDataLine.class, audioFormat);

            sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);

            sourceDataLine.open(audioFormat);

            sourceDataLine.start();

            //���������߳̽��в���

            Thread playThread = new Thread(new PlayThread());

            playThread.start();

        } catch (Exception e) {

            e.printStackTrace();

            System.exit(0);

        }

    }

    //��3��ֹͣ¼��

    public void stop() {
        stopCapture = true;
    }

    //��4�������ļ�
    public void save() {

        //ȡ��¼��������

        AudioFormat audioFormat = getAudioFormat();
        byte audioData[] = byteArrayOutputStream.toByteArray();
        InputStream byteArrayInputStream = new ByteArrayInputStream(audioData);
        audioInputStream = new AudioInputStream(byteArrayInputStream,audioFormat, audioData.length / audioFormat.getFrameSize());

        //д���ļ�
        JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("ѡ������");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("WAV FILES(*.wav)","wav");
		chooser.setFileFilter(filter);
		chooser.setSelectedFile(new File(attachment.getId()+".wav"));
		
        int result = chooser.showSaveDialog(null);
        if(result == JFileChooser.APPROVE_OPTION) {    
        	 File file = chooser.getSelectedFile();
        	 if (file.exists()) {
        		 int copy = JOptionPane.showConfirmDialog(null,"�Ƿ�Ҫ���ǵ�ǰ�ļ���", "����", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        		 if (copy == JOptionPane.NO_OPTION){
        			 this.dispose();
        			return;
        		 }
        	 }
        	 
        	try {
				AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, file);
				attachment.setPath(file.getPath());
	         	this.callback.callback(attachment);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showConfirmDialog(null, "�����ļ�����ʧ�ܣ�", "��ʾ", JOptionPane.YES_OPTION);
		        e.printStackTrace();
			}
         	this.dispose();
            return;
        }
     
    }

    //ȡ��AudioFormat

    private AudioFormat getAudioFormat() {

        float sampleRate = 16000.0F;
        int sampleSizeInBits = 16;
        int channels = 1;
        boolean signed = true;
        boolean bigEndian = false;

        return new AudioFormat(sampleRate, sampleSizeInBits, channels, signed,

                bigEndian);

    }

    
    class CaptureThread extends Thread {

    //��ʱ����

    byte tempBuffer[] = new byte[10000];

    public void run() {

        byteArrayOutputStream = new ByteArrayOutputStream();

        totaldatasize = 0;

        stopCapture = false;

        try {//ѭ��ִ�У�ֱ������ֹͣ¼����ť

            while (!stopCapture) {

                //��ȡ10000������

                int cnt = targetDataLine.read(tempBuffer, 0,

                        tempBuffer.length);

                if (cnt > 0) {

                    //���������

                    byteArrayOutputStream.write(tempBuffer, 0, cnt);

                    totaldatasize += cnt;

                }

            }

            byteArrayOutputStream.close();

        } catch (Exception e) {

            e.printStackTrace();

            System.exit(0);

        }

    }

}

class PlayThread extends Thread {

    byte tempBuffer[] = new byte[10000];

    public void run() {

        try {

            int cnt;

            //��ȡ���ݵ���������

            while ((cnt = audioInputStream.read(tempBuffer, 0,

                    tempBuffer.length)) != -1) {

                if (cnt > 0) {

                    //д�뻺������

                    sourceDataLine.write(tempBuffer, 0, cnt);

                }

            }

            //Block�ȴ���ʱ���ݱ����Ϊ��

            sourceDataLine.drain();

            sourceDataLine.close();

        } catch (Exception e) {

            e.printStackTrace();

            System.exit(0);

        }

    }

	}

}
