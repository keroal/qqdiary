package com.locusdiary.ui.module;

import java.util.Comparator;

import com.locusdiary.module.GeneralDiary;

public class DiaryComparatorByTime implements Comparator {
	@Override
	public int compare(Object arg0, Object arg1) {
		// TODO Auto-generated method stub
		GeneralDiary diary0 = (GeneralDiary)arg0;
		GeneralDiary diary1 = (GeneralDiary)arg1;
		
		int flag = diary0.getBuildTime().compareTo(diary1.getBuildTime());
		if(flag==0){
			return diary0.getTitle().compareTo(diary1.getTitle());
		}else{
			return flag;
		} 
	}
}
