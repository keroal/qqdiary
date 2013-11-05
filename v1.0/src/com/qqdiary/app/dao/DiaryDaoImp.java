package com.qqdiary.app.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.qqdiary.app.module.DiaryAttachment;
import com.qqdiary.app.module.DiaryFolder;
import com.qqdiary.app.module.GeneralDiary;
import com.qqdiary.app.module.DiaryAttachment.AttachmentStatus;
import com.qqdiary.app.module.SearchParameters;

/**
 * 数据操作层接口实现类
 * @author Administrator
 *
 */
public class DiaryDaoImp implements DiaryDao {
	
	private final String GET_DIARIES_BY_FOLDER = "select * from diarytable where FOLDERID = ?;";
	private final String GET_DIARIES_BY_PARAMETERS = "select * from diarytable where TITLE like ? and CONTENT like ? and (DATE between ? and ? );";
	private final String GET_FOLDER_LIST = "select * from foldertable;";
	private final String DELETE_ATTACHMENT_BY_DIARIES = "delete from attachmenttable where DIARYID in (?)";
	private final String DELETE_DIARY_BY_DIARIES = "delete from diarytable where ID in (?)";
	private final String DELETE_FOLDER_BY_FOLDERS = "delete from foldertable where ID= ?";
	private final String INSERT_FOLDER="insert into foldertable values (?,?,?)";
	private final String UPDATE_FOLDER="update foldertable set NAME=?,DESCRIBE=? where ID=?";
	private final String INSERT_DIARY="insert into diarytable values (?,?,?,?,?,?,?)";
	private final String UPDATE_DIARY="update diarytable set FOLDERID=?,TITLE=?,DATE=?,WEATHER=?,CONTENT=?,HASATTACHMENT=? where ID=?";
	private final String QUERY_ATTACHMENT_BY_DIARY = "select * from attachmenttable where DIARYID = ?;";
	private final String INSERT_ATTACHMENT="insert into attachmenttable values (?,?,?,?)";
	private final String DELETE_ATTACHMENT_BY_DIARY = "delete from attachmenttable where DIARYID= ?";

	
	private String connString = "org.sqlite.JDBC";
	private String sqldatabase = "jdbc:sqlite:diary.db";
	private Connection conn;
	
	public String getConnString() {
		return connString;
	}
	public void setConnString(String connString) {
		this.connString = connString;
	}
	
	private boolean sqlconnect() {
		try {
			Class.forName(connString);
			conn = DriverManager.getConnection(sqldatabase);

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
		
	}
	
	private boolean sqldisconnect() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
		
	}
    
	@Override
	public List<GeneralDiary> getDiaries(DiaryFolder folder) {
		// TODO Auto-generated method stub
		sqlconnect();
		List<GeneralDiary> diariesList = new ArrayList<GeneralDiary>();

		try {
			PreparedStatement ps = conn.prepareStatement(GET_DIARIES_BY_FOLDER);
		    ps.setString(1, folder.getId());
		    ResultSet rs=ps.executeQuery();
			
			while (rs.next() ) {
				GeneralDiary diary = new GeneralDiary();
				diary.setId(rs.getString("ID"));
				diary.setTitle(rs.getString("TITLE"));
				diary.setWeather(rs.getString("WEATHER"));
				diary.setContent(rs.getString("CONTENT"));
				diary.setHasAttachement(rs.getInt("HASATTACHMENT")==1?true:false);
				diary.setDate(rs.getString("DATE"));
				diary.setFolder(folder);
				diariesList.add(diary);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sqldisconnect();
		return diariesList;
	}
	
	@Override
	public List<GeneralDiary> getDiaries(SearchParameters parameters){
		sqlconnect();
		List<GeneralDiary> diariesList = new ArrayList<GeneralDiary>();
		try {
			PreparedStatement ps = conn.prepareStatement(GET_DIARIES_BY_PARAMETERS);
		    ps.setString(1, "%"+parameters.getTitle()+"%");
		    ps.setString(2, "%"+parameters.getKeyword()+"%");
		    ps.setString(3, parameters.getsTime());
		    ps.setString(4, parameters.geteTime());
		    ResultSet rs=ps.executeQuery();
			
			while (rs.next() ) {
				GeneralDiary diary = new GeneralDiary();
				diary.setId(rs.getString("ID"));
				diary.setTitle(rs.getString("TITLE"));
				diary.setWeather(rs.getString("WEATHER"));
				diary.setContent(rs.getString("CONTENT"));
				diary.setHasAttachement(rs.getInt("HASATTACHMENT")==1?true:false);
				diary.setDate(rs.getString("DATE"));
				diariesList.add(diary);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sqldisconnect();
		return diariesList;
	}

	@Override
	public void saveDiary(GeneralDiary diary) {
		// TODO Auto-generated method stub
		sqlconnect();
		if (diary.isNew()) {
			InsertDiaryAttribute(diary);
		} else {
			updateDiaryAttribute(diary);
		}

		saveDiaryAttachment(diary);
		sqldisconnect();
	}
	
    private void deleteAttachments(List<GeneralDiary> diaries) {

    	List<String> idList = new ArrayList<String>();
        for (GeneralDiary diary : diaries) {
			idList.add(diary.getId());
		}
        String idsStr = StringUtils.join(idList.toArray(),","); 

		try {
			PreparedStatement ps = conn.prepareStatement(DELETE_ATTACHMENT_BY_DIARIES);
		    ps.setString(1, idsStr);
			ps.executeUpdate();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void deleteDiaries(List<GeneralDiary> diaries) {
		// TODO Auto-generated method stub
		sqlconnect();

		List<String> idList = new ArrayList<String>();
        for (GeneralDiary diary : diaries) {
			idList.add(diary.getId());
		}
        String idsStr = StringUtils.join(idList.toArray(),","); 

		try {
			PreparedStatement ps = conn.prepareStatement(DELETE_DIARY_BY_DIARIES);
		    ps.setString(1, idsStr);
			ps.executeUpdate();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		deleteAttachments(diaries);
		sqldisconnect();
	}
	
	@SuppressWarnings("unchecked")
	private void InsertDiaryAttribute(GeneralDiary diary){
		
		try {
			PreparedStatement ps = conn.prepareStatement(INSERT_DIARY);
			ps.setString(1, diary.getId());
			ps.setString(2, diary.getFolder().getId());
			ps.setString(3, diary.getTitle());
			ps.setString(4, diary.getDate());
			ps.setString(5, diary.getWeather());
			ps.setString(6, diary.getContent());	
			ps.setInt(7, diary.isHasAttachement()?1:0);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	private void updateDiaryAttribute(GeneralDiary diary){

		try {
			PreparedStatement ps = conn.prepareStatement(UPDATE_DIARY);
			ps.setString(1, diary.getFolder().getId());
			ps.setString(2, diary.getTitle());
			ps.setString(3, diary.getDate());
			ps.setString(4, diary.getWeather());
			ps.setString(5, diary.getContent());
			ps.setInt(6, diary.isHasAttachement()?1:0);

			ps.setString(7, diary.getId());
		    ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@SuppressWarnings("unchecked")
	private void saveDiaryAttachment(GeneralDiary diary){
		for (DiaryAttachment attachment : diary.getAttachmentList()) {
			if (attachment.getStatus() == AttachmentStatus.NEW) {
				insertDiaryAttachment(attachment);
			}else if ( attachment.getStatus() == AttachmentStatus.DELETE) {
				deleteDiaryAttachment(attachment);
			}
		}
	}

	
	@SuppressWarnings("unchecked")
	private void insertDiaryAttachment(DiaryAttachment attachment){

		try {
			PreparedStatement ps = conn.prepareStatement(INSERT_ATTACHMENT);
			ps.setString(1, attachment.getId());
			ps.setString(2, attachment.getDiaryId());
			ps.setString(3, attachment.getType());
			ps.setString(4, attachment.getPath());			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@SuppressWarnings("unchecked")
	private void deleteAllDiaryAttachments(GeneralDiary diary){
		try {
			PreparedStatement ps = conn.prepareStatement(DELETE_ATTACHMENT_BY_DIARY);
		    ps.setString(1, diary.getId());
			ps.executeUpdate();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void deleteDiaryAttachment(DiaryAttachment attachment){
		String sql = "delete from attachmenttable where ID=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
		    ps.setString(1, attachment.getId());
			ps.executeUpdate();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public List<DiaryAttachment> getDiaryAttachments(GeneralDiary diary) {
		// TODO Auto-generated method stub
		sqlconnect();
		List<DiaryAttachment> attachments = new ArrayList<DiaryAttachment>();

		try {
			PreparedStatement ps = conn.prepareStatement(QUERY_ATTACHMENT_BY_DIARY);
		    ps.setString(1, diary.getId());
		    ResultSet  rs = ps.executeQuery();
			
			while (rs.next() ) {
				DiaryAttachment diaryattachment = new DiaryAttachment();
				diaryattachment.setId(rs.getString("ID"));
				diaryattachment.setDiaryId(diary.getId());
				diaryattachment.setType(rs.getString("TYPE"));
				diaryattachment.setPath(rs.getString("PATH"));
				diaryattachment.setStatus(AttachmentStatus.EXIST);
				attachments.add(diaryattachment);
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sqldisconnect();
		return attachments;
	}


	
	@Override
	public List<DiaryFolder> getFolderList() {
		// TODO Auto-generated method stub
		sqlconnect();
		List<DiaryFolder> folderList = new ArrayList<DiaryFolder>();

		try {
			PreparedStatement ps=conn.prepareStatement(GET_FOLDER_LIST);
			ResultSet rs=ps.executeQuery();
			
			while (rs.next()) {
				DiaryFolder folder = new DiaryFolder();
				folder.setId(rs.getString("ID"));
				folder.setName(rs.getString("NAME"));
				folder.setDescribe(rs.getString("DESCRIBE"));
				folder.setNew(false);
				folderList.add(folder);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sqldisconnect();
		return folderList;
	}

	@Override
	public void deleteFolder(DiaryFolder folder) {
		// TODO Auto-generated method stub
		sqlconnect();

		try {
			PreparedStatement ps = conn.prepareStatement(DELETE_FOLDER_BY_FOLDERS);
		    ps.setString(1, folder.getId());
			ps.executeUpdate();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sqldisconnect();
	}

	
	@Override
	public void saveFolder(DiaryFolder folder) {
		// TODO Auto-generated method stub
		sqlconnect();
		if (folder.isNew()) {
			InsertDiaryFolder(folder);
		} else {
			updateDiaryFolder(folder);
		}
		sqldisconnect();
	}
	
	@SuppressWarnings("unchecked")
	private void InsertDiaryFolder(DiaryFolder folder){
		
		try {
			PreparedStatement ps = conn.prepareStatement(INSERT_FOLDER);
			ps.setString(1, folder.getId());
			ps.setString(2, folder.getName());
			ps.setString(3, folder.getDescribe());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	private void updateDiaryFolder(DiaryFolder folder){

		try {
			PreparedStatement ps = conn.prepareStatement(UPDATE_FOLDER);
			ps.setString(3, folder.getId());
			ps.setString(1, folder.getName());
			ps.setString(2, folder.getDescribe());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
}
