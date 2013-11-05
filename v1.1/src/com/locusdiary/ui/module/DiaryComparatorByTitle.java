package com.locusdiary.ui.module;

import java.util.Comparator;

import com.locusdiary.module.GeneralDiary;

public class DiaryComparatorByTitle implements Comparator {

	@Override
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		GeneralDiary diary1 = (GeneralDiary)o1;
		GeneralDiary diary2 = (GeneralDiary)o2;
		
		int flag = diary1.getTitle().compareTo(diary2.getTitle());
		if(flag==0){
			return diary1.getBuildTime().compareTo(diary2.getBuildTime());
		}else{
			return flag;
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
