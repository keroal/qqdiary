package com.locusdiary.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.locusdiary.module.AttachmentFileFactory;
import com.locusdiary.module.DiaryFolder;
import com.locusdiary.module.GeneralAttachment;
import com.locusdiary.module.GeneralComment;
import com.locusdiary.module.User;
import com.locusdiary.module.GeneralAttachment.AttachmentStatus;
import com.locusdiary.module.GeneralDiary;
import com.locusdiary.module.SearchParameters;

public class DiaryDaoImp implements DiaryDao {
	
	private final String GET_DIARIES_BY_FOLDER = "select * from diarytable where FOLDERID = ?;";
	private final String GET_DIARIES_BY_USER = "select * from diarytable where USERID = ?;";
	private final String GET_DIARIES_BY_PARAMETERS = "select * from diarytable where TITLE like ? and CONTENT like ? and (BUILDTIME between ? and ? );";
	private final String GET_FOLDER_LIST = "select * from foldertable where USERID = ?;";
	private final String DELETE_ATTACHMENT_BY_DIARIES = "delete from attachmenttable where DIARYID in (?);";
	private final String DELETE_COMMENT_BY_DIARIES = "delete from commenttable where DIARYID in (?);";
	private final String DELETE_DIARY_BY_DIARIES = "delete from diarytable where ID in (?);";
	private final String DELETE_FOLDER_BY_FOLDERS = "delete from foldertable where ID= ?;";
	private final String INSERT_FOLDER="insert into foldertable values (?,?,?,?);";
	private final String UPDATE_FOLDER="update foldertable set NAME=?,DESCRIBE=? where ID=?;";
	private final String INSERT_DIARY="insert into diarytable values (?,?,?,?,?,?,?,?,?,?,?);";
	private final String UPDATE_DIARY="update diarytable set FOLDERID=?,TITLE=?,BUILDTIME=?,MODIFYTIME=?,WEATHER=?,CONTENT=?,HASATTACHMENT=?,HASCOMMENT=?, PUBLIC=? where ID=?;";
	private final String QUERY_ATTACHMENT_BY_DIARY = "select * from attachmenttable where DIARYID = ?;";
	private final String INSERT_ATTACHMENT="insert into attachmenttable values (?,?,?,?);";
	private final String DELETE_ATTACHMENT_BY_DIARY = "delete from attachmenttable where DIARYID= ?;";
	private final String GET_FRIEND_LIST = "select * from friendtable where ID = ?;";
	private final String DELETE_FRIEND = "delete from friendtable where ID = ? and FRIENDID = ?£»";
	private final String QUERY_USER_FROM_USERTABLE = "select * from usertable where ID = ?;";
	private final String ADD_FRIEND_CHECK = "select * from friendtable where ID = ? and FRIENDID = ?";
	private final String INSERT_FRIEND = "insert into friendtable values (?,?,?,?);";
	private final String USER_REGISTER = "insert into usertable(NAME,PASSWORD,EMAIL) values(?,?,?);";
	private final String USER_REGISTER_CHECK = "select * from usertable where EMAIL = ?;";
	private final String USER_VERIFY = "select * from usertable where ID = ?;";
	private final String QUERY_COMMENT_BY_DIARY = "select * from commenttable where DIARYID = ?;";
	private final String INSERT_COMMENT = "insert into commenttable(DIARYID,USERID,CONTENT,TIME) values (?,?,?,?);";
	private final String DELETE_COMMENT = "delete from commenttable where DIARYID= ? and USERID = ?;";
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
		} catch (Exception e) {
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
				diary.setStatus(false);
				diary.setId(rs.getString("ID"));
				diary.setTitle(rs.getString("TITLE"));
				diary.setWeather(rs.getString("WEATHER"));
				diary.setContent(rs.getString("CONTENT"));
				diary.setHasAttachement(rs.getInt("HASATTACHMENT")==1?true:false);
				diary.setHasComment(rs.getInt("HASCOMMENT")==1?true:false);
				diary.setHasPublic(rs.getInt("PUBLIC")==1?true:false);
				diary.setBuildTime(rs.getString("BUILDTIME"));
				diary.setModifyTime(rs.getString("MODIFYTIME"));
				diary.setFolderid(folder.getId());
				diary.setStatus(false);
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
				diary.setStatus(false);
				diary.setId(rs.getString("ID"));
				diary.setTitle(rs.getString("TITLE"));
				diary.setWeather(rs.getString("WEATHER"));
				diary.setContent(rs.getString("CONTENT"));
				diary.setHasAttachement(rs.getInt("HASATTACHMENT")==1?true:false);
				diary.setHasComment(rs.getInt("HASCOMMENT")==1?true:false);
				diary.setHasPublic(rs.getInt("PUBLIC")==1?true:false);
				diary.setBuildTime(rs.getString("BUILDTIME"));
				diary.setModifyTime(rs.getString("MODIFYTIME"));
				diary.setFolderid(rs.getString("FOLDERID"));
				diary.setStatus(false);
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
		if (diary.isStatus()) {
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
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    private void deleteComments(List<GeneralDiary> diaries) {

    	List<String> idList = new ArrayList<String>();
        for (GeneralDiary diary : diaries) {
			idList.add(diary.getId());
		}
        String idsStr = StringUtils.join(idList.toArray(),","); 

		try {
			PreparedStatement ps = conn.prepareStatement(DELETE_COMMENT_BY_DIARIES);
		    ps.setString(1, idsStr);
			ps.executeUpdate();
		
		} catch (Exception e) {
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
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		deleteAttachments(diaries);
		deleteComments(diaries);
		sqldisconnect();
	}
	
	private void InsertDiaryAttribute(GeneralDiary diary){
		
		try {
			PreparedStatement ps = conn.prepareStatement(INSERT_DIARY);
			ps.setString(1, diary.getId());
			ps.setString(2, diary.getUserid());
			ps.setString(3, diary.getFolderid());
			ps.setString(4, diary.getTitle());
			ps.setString(5, diary.getBuildTime());
			ps.setString(6, diary.getModifyTime());
			ps.setString(7, diary.getWeather());
			ps.setString(8, diary.getContent());	
			ps.setInt(9, diary.isHasAttachement()?1:0);
			ps.setInt(10, diary.isHasComment()?1:0);
			ps.setInt(11, diary.isHasPublic()?1:0);
			ps.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void updateDiaryAttribute(GeneralDiary diary){

		try {
			PreparedStatement ps = conn.prepareStatement(UPDATE_DIARY);
			ps.setString(1, diary.getFolderid());
			ps.setString(2, diary.getTitle());
			ps.setString(3, diary.getBuildTime());
			ps.setString(4, diary.getModifyTime());
			ps.setString(5, diary.getWeather());
			ps.setString(6, diary.getContent());	
			ps.setInt(7, diary.isHasAttachement()?1:0);
			ps.setInt(8, diary.isHasComment()?1:0);
			ps.setInt(9, diary.isHasPublic()?1:0);
			ps.setString(10, diary.getId());
		    ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void saveDiaryAttachment(GeneralDiary diary){
		for (GeneralAttachment attachment : diary.getAttachmentList()) {
			if (attachment.getStatus() == AttachmentStatus.NEW) {
				insertDiaryAttachment(attachment);
			}else if ( attachment.getStatus() == AttachmentStatus.DELETE) {
				deleteDiaryAttachment(attachment);
			}
		}
	}

	
	private void insertDiaryAttachment(GeneralAttachment attachment){

		try {
			PreparedStatement ps = conn.prepareStatement(INSERT_ATTACHMENT);
			ps.setString(1, attachment.getId());
			ps.setString(2, attachment.getDiaryId());
			ps.setString(3, attachment.getType());
			ps.setString(4, attachment.getPath());			
			ps.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void deleteAllDiaryAttachments(GeneralDiary diary){
		try {
			PreparedStatement ps = conn.prepareStatement(DELETE_ATTACHMENT_BY_DIARY);
		    ps.setString(1, diary.getId());
			ps.executeUpdate();
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void deleteDiaryAttachment(GeneralAttachment attachment){
		String sql = "delete from attachmenttable where ID=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
		    ps.setString(1, attachment.getId());
			ps.executeUpdate();
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public List<GeneralAttachment> getDiaryAttachments(GeneralDiary diary) {
		// TODO Auto-generated method stub
		sqlconnect();
		List<GeneralAttachment> attachments = new ArrayList<GeneralAttachment>();

		try {
			PreparedStatement ps = conn.prepareStatement(QUERY_ATTACHMENT_BY_DIARY);
		    ps.setString(1, diary.getId());
		    ResultSet  rs = ps.executeQuery();
			
			while (rs.next() ) {
				GeneralAttachment diaryattachment = AttachmentFileFactory.getInstance().createAttachment(diary, rs.getString("TYPE"));
				diaryattachment.setId(rs.getString("ID"));
				diaryattachment.setDiaryId(diary.getId());
				diaryattachment.setPath(rs.getString("PATH"));
				diaryattachment.setStatus(AttachmentStatus.EXIST);
				attachments.add(diaryattachment);
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sqldisconnect();
		return attachments;
	}


	
	@Override
	public List<DiaryFolder> getFolderList(User user) {
		// TODO Auto-generated method stub
		sqlconnect();
		List<DiaryFolder> folderList = new ArrayList<DiaryFolder>();

		try {
			PreparedStatement ps=conn.prepareStatement(GET_FOLDER_LIST);
			ps.setString(1, user.getUserid());
			ResultSet rs=ps.executeQuery();
			
			while (rs.next()) {
				DiaryFolder folder = new DiaryFolder();
				folder.setStatus(false);
				folder.setId(rs.getString("ID"));
				folder.setName(rs.getString("NAME"));
				folder.setDescribe(rs.getString("DESCRIBE"));
				folder.setUserId(rs.getString("USERID"));
				folder.setStatus(false);
				folderList.add(folder);
			}
		} catch (Exception e) {
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
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sqldisconnect();
	}

	
	@Override
	public boolean saveFolder(DiaryFolder folder) {
		// TODO Auto-generated method stub
		sqlconnect();
		if (folder.isStatus()) {
			return InsertDiaryFolder(folder);
		} else {
			updateDiaryFolder(folder);
		}
		sqldisconnect();
		return true;
	}
	
	@SuppressWarnings("unchecked")
	private boolean InsertDiaryFolder(DiaryFolder folder){
		if (checkFolderExist(folder)) {
			return false;
		}
		
		try {
			PreparedStatement ps = conn.prepareStatement(INSERT_FOLDER);
			ps.setString(1, folder.getId());
			ps.setString(2, folder.getUserId());
			ps.setString(3, folder.getName());
			ps.setString(4, folder.getDescribe());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	private boolean checkFolderExist(DiaryFolder folder){
		try {
			PreparedStatement ps = conn.prepareStatement("select * from foldertable where USERID = ? and NAME = ?");
			ps.setString(1, folder.getUserId());
			ps.setString(2, folder.getName());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	@SuppressWarnings("unchecked")
	private void updateDiaryFolder(DiaryFolder folder){

		try {
			PreparedStatement ps = conn.prepareStatement(UPDATE_FOLDER);
			ps.setString(1, folder.getName());
			ps.setString(2, folder.getDescribe());
			ps.setString(3, folder.getUserId());
			ps.setString(4, folder.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public List<User> getFriends(User user) {
		// TODO Auto-generated method stub
		sqlconnect();
		List<User> userList = new ArrayList<User>();
		try {
			PreparedStatement ps = conn.prepareStatement(GET_FRIEND_LIST);
			ps.setString(1, user.getUserid());
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				User newUser = new User();
				newUser.setUserid(rs.getString("FRIENDID"));
				newUser.setUsername(rs.getString("FRIENDNAME"));
				userList.add(newUser);
			}
			sqldisconnect();

			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return userList;
	}
	private boolean addFriendCheck(User user,User friendUser)
	{
		try {
			PreparedStatement ps = conn.prepareStatement(ADD_FRIEND_CHECK);
			ps.setString(1, user.getUserid());
			ps.setString(2, friendUser.getUserid());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	@Override
	public boolean addFriend(User user,User friendUser) {
		// TODO Auto-generated method stub
		sqlconnect();
		boolean ret = addFriendCheck(user, friendUser);
		if (ret) {
			sqldisconnect();
			return false;
		}
		try {
			PreparedStatement ps = conn.prepareStatement(INSERT_FRIEND);
			ps.setString(1, user.getUserid());
			ps.setString(2, friendUser.getUserid());
			ps.setString(3, friendUser.getUsername());
			ps.setString(4, "Ä¬ÈÏ");
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		sqldisconnect();
		return true;
	}
	
	
	@Override
	public void delFriend(User user,User friendUser) {
		// TODO Auto-generated method stub
		sqlconnect();
		boolean ret = addFriendCheck(user, friendUser);
				if (ret) {
					sqldisconnect();
					return ; 
				}
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(DELETE_FRIEND);
			ps.setString(1, user.getUserid());
			ps.setString(2, friendUser.getUserid());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			sqldisconnect();
			e.printStackTrace();
		}
		sqldisconnect();
		
	}
	private boolean userRegisterCheck(User user)
	{
		try {
			
			PreparedStatement ps = conn.prepareStatement(USER_REGISTER_CHECK);
			ps.setString(1, user.getEmail());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return false;
			}
			else {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
		
	}
	
	@Override
	public User userRegister(User user) {
		// TODO Auto-generated method stub
		sqlconnect();
		boolean ret = userRegisterCheck(user);
		if (!ret) {
			return null;
		}
		try {
			PreparedStatement ps = conn.prepareStatement(USER_REGISTER);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getEmail());
			ps.executeUpdate();
			PreparedStatement psgetid = conn.prepareStatement(USER_REGISTER_CHECK);
			psgetid.setString(1, user.getEmail());
			ResultSet rs = psgetid.executeQuery();
			user.setUserid(Integer.toString(rs.getInt("ID")));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		sqldisconnect();
		return user;
	}
	
	@Override
	public User userVerify(User user) {
		try {
			sqlconnect();
			PreparedStatement ps = conn.prepareStatement(USER_VERIFY);
			ps.setString(1, user.getUserid());
			ResultSet rs = ps.executeQuery();
			if (rs.next() && rs.getString("PASSWORD").equals(user.getPassword())) {
				user.setUsername(rs.getString("NAME"));
				sqldisconnect();
				return user;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		sqldisconnect();
		return null;
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<GeneralComment> getCommentList(GeneralDiary diary) {
		// TODO Auto-generated method stub
		sqlconnect();
		List<GeneralComment> comments = new ArrayList<GeneralComment>();

		try {
			PreparedStatement ps = conn.prepareStatement(QUERY_COMMENT_BY_DIARY);
		    ps.setString(1, diary.getId());
		    ResultSet  rs = ps.executeQuery();
			
			while (rs.next() ) {
				GeneralComment comment = new GeneralComment();
				comment.setDiaryId(diary.getId());
				comment.setPublishTime(rs.getString("TIME"));
				User newUser = new User();
				newUser.setUserid(rs.getString("USERID"));
				comment.setPublishUser(newUser);
				comment.setContent(rs.getString("CONTENT"));
				comments.add(comment);
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sqldisconnect();
		return comments;
	}
	@Override
	public void addComment(GeneralComment comment) {
		// TODO Auto-generated method stub
		try {
			sqlconnect();
			PreparedStatement psStatement = conn.prepareStatement(INSERT_COMMENT);
			psStatement.setString(1, comment.getDiaryId());
			psStatement.setString(2, comment.getPublishUser().getUserid());
			psStatement.setString(3, comment.getContent());
			psStatement.setString(4, comment.getPublishTime());
			psStatement.executeUpdate();
			sqldisconnect();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	@Override
	public void delComment(GeneralComment comment) {
		// TODO Auto-generated method stub
		try {
			sqlconnect();
			PreparedStatement ps = conn.prepareStatement(DELETE_COMMENT);
			ps.setString(1, comment.getDiaryId());
			ps.setString(2, comment.getPublishUser().getUserid());
			ps.executeUpdate();
			sqldisconnect();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	@Override
	public User getUser(User user) {
		// TODO Auto-generated method stub
		try {
			sqlconnect();
			PreparedStatement ps = conn.prepareStatement(QUERY_USER_FROM_USERTABLE);
			ps.setString(1, user.getUserid());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User newuserUser = new User();
				newuserUser.setUserid(user.getUserid());
				newuserUser.setUsername(rs.getString("NAME"));
				newuserUser.setEmail(rs.getString("EMAIL"));
				
				sqldisconnect();
				return newuserUser;
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	@Override
	public List<GeneralDiary> getDiaries(User user) {
		// TODO Auto-generated method stub
		sqlconnect();
		List<GeneralDiary> diariesList = new ArrayList<GeneralDiary>();

		try {
			PreparedStatement ps = conn.prepareStatement(GET_DIARIES_BY_USER);
		    ps.setString(1, user.getUserid());
		    ResultSet rs=ps.executeQuery();
			
			while (rs.next() ) {
				GeneralDiary diary = new GeneralDiary();
				diary.setId(rs.getString("ID"));
				diary.setTitle(rs.getString("TITLE"));
				diary.setWeather(rs.getString("WEATHER"));
				diary.setContent(rs.getString("CONTENT"));
				diary.setHasAttachement(rs.getInt("HASATTACHMENT")==1?true:false);
				diary.setBuildTime(rs.getString("BUILDTIME"));
				diary.setModifyTime(rs.getString("MODIFYTIME"));
				diary.setFolderid(rs.getString("FOLDERID"));
				diary.setUserid(rs.getString("USERID"));
				diary.setStatus(false);
				diariesList.add(diary);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sqldisconnect();
		return diariesList;
	}	
	
}
