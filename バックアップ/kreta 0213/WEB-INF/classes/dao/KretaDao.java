package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.*;


public class KretaDao{
	public void sqlUpdate(String sql){
		Connection cn = null;
		Statement st = null;

		try{

		  cn = OracleConnectionManager.getInstance().getConnection();
			st = cn.createStatement();

		  st.executeUpdate(sql);

		}catch(Exception e){
			e.printStackTrace();
		}

	}

	//
	public Bean getPost(){
		return new PostBean();
	}
	public List getSearchResult(){
		return new ArrayList();
	}
	public  UsersBean LoginProcessing(UsersBean u){
      PreparedStatement st = null;
      ResultSet rs = null;
	  UsersBean ub =new UsersBean();

      try{

        Connection cn = null;
        //Connnectionインターフェイスを実装するクラスの
        //インスタンスを返す
        cn = OracleConnectionManager.getInstance().getConnection();

      	String sql ="SELECT uUserID, uLoginID, uPassword, uUserName from Users where uLoginID='"+u.getLoginID()+"' AND uPassword = '" + u.getPassword() + "'";


        st = cn.prepareStatement(sql);

        rs = st.executeQuery();
				if(rs.next()){
				 ub.setUserID(rs.getString("uUserID"));
				 ub.setLoginID(rs.getString("uLoginID"));
				 ub.setPassword(rs.getString("uPassword"));
				 ub.setUserName(rs.getString("uUserName"));
			 }else{
				 System.out.println("null");
				 ub.setPassword("間違い");
			 }

      }catch (SQLException e) {
			e.printStackTrace();
      }
      return ub;
    }
		public int loginIdCheck(String loginId){
			PreparedStatement st = null;
			ResultSet rs = null;
			int checkResult = 0;

			try{

				Connection cn = null;
				//Connnectionインターフェイスを実装するクラスの
				//インスタンスを返す
				cn = OracleConnectionManager.getInstance().getConnection();

				String sql ="SELECT count(uLoginID) from Users where uLoginID='"+ loginId +"'";

				st = cn.prepareStatement(sql);

				rs = st.executeQuery();

				rs.next();
				 checkResult = rs.getInt(1);

			}catch (SQLException e) {
				e.printStackTrace();
			}
			return checkResult;
		}

	public PostBean getSinglePost(String pid){
		PreparedStatement st = null;
		ResultSet rs = null;
		Connection cn = null;
		PostBean pb = new PostBean();
		try{
			cn = OracleConnectionManager.getInstance().getConnection();
			String sql = "SELECT users.uusername, users.uloginid, post.ppostid, post.uuserid, post.ppostcontent, post.ppostdate "+
						  "FROM   users JOIN post "+
						  "ON     users.uuserid = post.uuserid "+
						  "WHERE  post.ppostid ="+ pid;

			st = cn.prepareStatement(sql);
			rs = st.executeQuery();

			rs.next();

			pb.setPostID(rs.getString("pPostID"));
			pb.setUserID(rs.getString("uUserID"));
			pb.setLoginID(rs.getString("uLoginID"));
			pb.setUserName(rs.getString("uUserName"));
			//pb.setAvatar("");
			pb.setPostContent(rs.getString("pPostContent"));
			//pb.setPostMedia();
			pb.setPostDate(rs.getString("pPostDate"));
			//pb.setReplyCount();
			//pb.setLikeCount();

		}catch (SQLException e) {
			e.printStackTrace();
		}
		return pb;
	}


	public PostBean getNextPost(String pid, String s, String u, String f){
		PreparedStatement st = null;
		ResultSet rs = null;
		Connection cn = null;
		PostBean pb = null;
		String sql = "";
		String prepareS = "";
		String prepareU = "";
		String prepareF = "";

		System.out.println("pid "+ pid);
		try{
			if(pid.equals("undefined")){
				pid = ">0";
			}
			if(!(s.equals("null"))){
				if(s.equals("")){
					//全件表示
				}else{
					prepareS = "AND REGEXP_LIKE(pPostContent,'"+s+"')";
				}
			}else if(!(u.equals("null")||u.equals(""))){
				prepareU = "AND REGEXP_LIKE(uuserid,(SELECT uUserId FROM Users WHERE uLoginId = '"+u+"'))";
			}else if(!(f.equals("null")||f.equals(""))){
				prepareF = 	"AND uuserid in (SELECT uuserid FROM Users WHERE uLoginId in "+
  									"(SELECT ufollowid FROM Follow WHERE uobserberid = '"+f+"') or uuserid in (SELECT uuserid FROM Users WHERE uLoginId = '"+f+"') )";
			}else{
				//全件表示
			}

			sql =	"SELECT * FROM ( "+
						"SELECT u.uUsername, u.uLoginID, p.pPostid, p.uUserid, p.pPostcontent, p.pPostdate, a.aFilePath, u.uIconPath, p.attachment "+
						"FROM  (SELECT * FROM Post WHERE ppostid < "+pid+" AND pstate = 0 "+prepareS+" "+prepareU+" "+prepareF+" ) p "+
						"LEFT JOIN  Users u ON(u.uuserid = p.uuserid) "+
						"LEFT JOIN  Attachment a ON(p.ppostid = a.ppostid) ORDER BY ppostid DESC) "+
						"WHERE rownum = 1";

			cn = OracleConnectionManager.getInstance().getConnection();

			st = cn.prepareStatement(sql);
			rs = st.executeQuery();

			if(rs.next()){
				pb = new PostBean();

				pb.setPostID(rs.getString("pPostID"));
				pb.setUserID(rs.getString("uUserID"));
				pb.setLoginID(rs.getString("uLoginID"));
				pb.setUserName(rs.getString("uUserName"));
				pb.setAvatar(rs.getString("uIconPath"));
				pb.setPostContent(rs.getString("pPostContent"));
				pb.setPostMedia(rs.getString("attachment"));
				pb.setPostDate(rs.getString("pPostDate"));
				//pb.setReplyCount();
				//pb.setLikeCount();
			}

		}catch (SQLException e) {
			System.out.println(sql);
			e.printStackTrace();
		}
		return pb;
	}

	public PostBean getNewPost(String pid, String s, String u, String f){
		PreparedStatement st = null;
		ResultSet rs = null;
		Connection cn = null;
		PostBean pb = null;
		String sql = "";
		String prepareS = "";
		String prepareU = "";
		String prepareF = "";

		System.out.println("getNewPostのpid "+ pid);
		try{
			if(pid.equals("undefined")){
				pid = ">0";
			}
			if(!(s.equals("null"))){
				if(s.equals("")){
					//全件表示
				}else{
					prepareS = "AND REGEXP_LIKE(pPostContent,'"+s+"')";
				}
			}else if(!(u.equals("null")||u.equals(""))){
				prepareU = "AND REGEXP_LIKE(uuserid,(SELECT uUserId FROM Users WHERE uLoginId = '"+u+"'))";
			}else if(!(f.equals("null")||f.equals(""))){
				prepareF = 	"AND uuserid in (SELECT uuserid FROM Users WHERE uLoginId in "+
  									"(SELECT ufollowid FROM Follow WHERE uobserberid = '"+f+"') or uuserid in (SELECT uuserid FROM Users WHERE uLoginId = '"+f+"') )";
			}else{
				//全件表示
			}
			sql ="SELECT * FROM ( "+
						"SELECT u.uUsername, u.uLoginID, p.pPostid, p.uUserid,p. pPostcontent, p.pPostdate, a.aFilePath, u.uIconPath "+
						"FROM  (SELECT * FROM Post WHERE ppostid > "+pid+" AND pstate = 0 "+prepareS+" "+prepareU+" "+prepareF+" ) p "+
						"LEFT JOIN  Users u ON(u.uuserid = p.uuserid) "+
						"LEFT JOIN  Attachment a ON(p.ppostid = a.ppostid) ORDER BY ppostid ASC) "+
						"WHERE rownum = 1";

			/*
			sql = "SELECT users.uusername, p.ppostid, p.uuserid, p.ppostcontent, p.ppostdate "+
						  "FROM   users JOIN (select * from post WHERE ppostid > "+pid+" ORDER BY ppostid ASC) p "+
						  "ON     users.uuserid = p.uuserid where rownum=1";
						  */
			cn = OracleConnectionManager.getInstance().getConnection();

			st = cn.prepareStatement(sql);
			rs = st.executeQuery();

			if(rs.next()){
				pb = new PostBean();

				pb.setPostID(rs.getString("pPostID"));
				pb.setUserID(rs.getString("uUserID"));
				pb.setLoginID(rs.getString("uLoginID"));
				pb.setUserName(rs.getString("uUserName"));
				pb.setAvatar(rs.getString("uIconPath"));
				pb.setPostContent(rs.getString("pPostContent"));
				pb.setPostMedia(rs.getString("attachment"));
				pb.setPostDate(rs.getString("pPostDate"));
				//pb.setReplyCount();
				//pb.setLikeCount();
			}

		}catch (SQLException e) {
			System.out.println(sql);
			e.printStackTrace();
		}
		return pb;
	}

	public UsersBean getUserInfo(String loginid){
		PreparedStatement st = null;
		ResultSet rs = null;
		Connection cn = null;
		UsersBean ub = new UsersBean();
		try{
			cn = OracleConnectionManager.getInstance().getConnection();
			String sql = "SELECT USERS.uuserid,USERS.uloginid,USERS.uintroduction,USERS.uiconpath,USERS.uusername, "+
						 "(select count(ppostid) from post where uuserid=USERS.uuserid and pstate=0) as \"ツイート数\", " +
						 "(select count(ufollowid) from follow where uobserberid=USERS.uloginid) as \"フォロー数\", "+
						 "(select count(uobserberid) from follow where ufollowid=USERS.uloginid) as \"フォロワー数\" "+
						 "FROM users users "+
						 "LEFT JOIN post post ON (USERS.uuserid=POST.uuserid) "+
						 "LEFT JOIN follow follow ON (USERS.uloginid=FOLLOW.ufollowid) "+
						 "WHERE USERS.uloginid='"+ loginid + "'";

			System.out.println(sql);
			st = cn.prepareStatement(sql);
			rs = st.executeQuery();

			if(rs.next()){

				ub.setUserID(rs.getString("uuserid"));
				ub.setLoginID(rs.getString("uloginid"));
				ub.setIntroduction(rs.getString("uintroduction"));
				ub.setUserName(rs.getString("uusername"));
				ub.setIconPath(rs.getString("uiconpath"));
				ub.setPostCount(rs.getString("ツイート数"));
				ub.setFollowCount(rs.getString("フォロー数"));
				ub.setFollowerCount(rs.getString("フォロワー数"));
			}

		}catch (SQLException e) {
			e.printStackTrace();
		}
		return ub;

	}
	public String getUserID(String sql){
		PreparedStatement st = null;
		ResultSet rs = null;
		Connection cn = null;
		String userID ="";
		try{
			cn = OracleConnectionManager.getInstance().getConnection();

			//System.out.println(sql);
			st = cn.prepareStatement(sql);
			rs = st.executeQuery();

			rs.next();

			userID = rs.getString("uuserid");

		}catch (SQLException e) {
			e.printStackTrace();
		}
		return userID;
	}
	public ArrayList getReplyList(String pid){
		PreparedStatement st = null;
		ResultSet rs = null;
		Connection cn = null;
		ArrayList replyList = new ArrayList();
		try{
			cn = OracleConnectionManager.getInstance().getConnection();
			String sql = "SELECT reply.rReplyId,reply.rReplyDate,reply.rReplyContent,users.uLoginID,users.uUserName,users.uIconPath "+
						 "FROM reply join users "+
						 "on reply.uUserID = users.uUserID "+
						 "where reply.pPostId = " + pid +
						 "ORDER BY rReplyDate";


			//System.out.println(sql);
			st = cn.prepareStatement(sql);
			rs = st.executeQuery();

			while(rs.next()){
				ReplyBean rb = new ReplyBean();
				rb.setReplyID(rs.getString(1));
				rb.setReplyDate(rs.getString(2));
				rb.setReplyContent(util.HTMLFilter.filter(rs.getString(3)));
				rb.setLoginID(rs.getString(4));
				rb.setUserName(rs.getString(5));
				rb.setIconPath(rs.getString(6));
				replyList.add(rb);
			}


		}catch (SQLException e) {
			e.printStackTrace();
		}
		return replyList;
	}
	public boolean checkFollow(FollowBean followbean){
		PreparedStatement st = null;
		ResultSet rs = null;
		Connection cn = null;
		boolean check = false ;
		try{
			cn = OracleConnectionManager.getInstance().getConnection();
			String sql ="SELECT * FROM follow where ufollowid ='"+followbean.getFollowID()+"' and uobserberID ='"+followbean.getObserberID()+"'";
			System.out.println(sql);
			st = cn.prepareStatement(sql);
			rs = st.executeQuery();

			if(rs.next()){

				followbean.setFollowID(rs.getString("uFollowID"));
				followbean.setObserberID(rs.getString("uObserberID"));
				check=true;
			}
			else{
				System.out.println("フォローされていません");
				check=false;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return check;
	}
}
