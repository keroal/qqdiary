package com.qqdiary.app.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;

import com.qqdiary.app.module.GeneralDiary;


public class DiaryTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

	private  Vector diariesList = new Vector<GeneralDiary>();
	private  Vector column = new Vector();
	
	public DiaryTableModel() { 
		initColumn();
	} 
	
	private void initColumn() {
		column.add("����");
		column.add("ʱ��");
		column.add("����");
		column.add("����");
		column.add("����");
	}
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return column.size();
	}
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return diariesList.size();
	}
	
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return (String) this.column.get(column);
	}
	
	@Override
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		GeneralDiary diary = (GeneralDiary)diariesList.get(row);
		return getPropertyValueByCol(diary, col);
	}
	
	public void clear(){
		diariesList.removeAllElements();
	}
	
	public boolean add(GeneralDiary diary){
		if (diariesList.contains(diary)) {
			return false;
		}
		
		diariesList.add(diary);
		return true;
	}
	
	public boolean remove(GeneralDiary diary) {
		if (!diariesList.contains(diary)) {
			return false;
		}
		
		diariesList.remove(diary);
		return true;
	}

	
	private Object getPropertyValueByCol(GeneralDiary diary, int col) { 
		switch (col) { 
		case 0: 
			return diary.getFolder().getName(); 
		case 1: 
			return diary.getDate();  
		case 2: 
			return diary.getWeather();  
		case 3: 
			return diary.getTitle(); 
		case 4:
			String hasAttach = "��";
			if (!diary.isHasAttachement()) {
				hasAttach = "��";
			}
			return hasAttach;
		} 
		return null; 
		} 
	
	

}
