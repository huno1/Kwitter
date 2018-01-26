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
        //ロールバックする
        OracleConnectionManager.getInstance().rollback();
      }finally{
        try{
          if(st !=null){
          st.close();
          }
        }catch (SQLException e) {
        	e.printStackTrace();
        }
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
				//ロールバックする
				OracleConnectionManager.getInstance().rollback();
			}finally{
				try{
					if(st !=null){
					st.close();
					}
				}catch (SQLException e) {
					e.printStackTrace();
				}
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
			String sql = "SELECT users.uusername, post.ppostid, post.uuserid, post.ppostcontent, post.ppostdate "+
						  "FROM   users JOIN post "+
						  "ON     users.uuserid = post.uuserid "+
						  "WHERE  post.ppostid ="+ pid;

			st = cn.prepareStatement(sql);
			rs = st.executeQuery();
			
			rs.next();
			
			pb.setPostID(rs.getString("pPostID"));
			pb.setUserID(rs.getString("uUserID"));
			pb.setUserName(rs.getString("uUserName"));
			//pb.setAvatar("");
			pb.setPostContent(rs.getString("pPostContent"));
			//pb.setPostMedia();
			pb.setPostDate(rs.getString("pPostDate"));
			//pb.setReplyCount();
			//pb.setLikeCount();

		}catch (SQLException e) {
			e.printStackTrace();
			//ロールバックする
			OracleConnectionManager.getInstance().rollback();
		}finally{
			try{
				if(st !=null){
				st.close();
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return pb;
	}

	
	public PostBean getNextPost(String pid){
		PreparedStatement st = null;
		ResultSet rs = null;
		Connection cn = null;
		PostBean pb = null;
		try{
			cn = OracleConnectionManager.getInstance().getConnection();
			String sql = "SELECT users.uusername, p.ppostid, p.uuserid, p.ppostcontent, p.ppostdate "+
						  "FROM   users JOIN (select * from post WHERE ppostid < "+pid+" ORDER BY ppostid DESC) p "+
						  "ON     users.uuserid = p.uuserid where rownum=1";

			st = cn.prepareStatement(sql);
			rs = st.executeQuery();
			
			rs.next();
			pb = new PostBean();
			
			pb.setPostID(rs.getString("pPostID"));
			pb.setUserID(rs.getString("uUserID"));
			pb.setUserName(rs.getString("uUserName"));
			//pb.setAvatar("");
			pb.setPostContent(rs.getString("pPostContent"));
			//pb.setPostMedia();
			pb.setPostDate(rs.getString("pPostDate"));
			//pb.setReplyCount();
			//pb.setLikeCount();

		}catch (SQLException e) {
			System.out.print("post end");
		}
		return pb;
	}
	
	public LoginUserInfoBean getLoginUserInfo(String userID){
		PreparedStatement st = null;
		ResultSet rs = null;
		Connection cn = null;
		LoginUserInfoBean loginInfoB = new LoginUserInfoBean();
		try{
			cn = OracleConnectionManager.getInstance().getConnection();
			String sql = "SELECT USERS.uuserid,USERS.uintroduction,USERS.uiconpath,USERS.uusername, "+
						 "(select count(ppostid)from post where uuserid="+ userID +") as \"ツイート数\", " +
						 "(select count(ufollowid)from follow where uobserberid=4) as \"フォロー数\", "+
						 "(select count(uobserberid) from follow where ufollowid=4) as \"フォロワー数\" "+
						 "FROM users users "+
						 "LEFT JOIN post post ON (USERS.uuserid=POST.uuserid) "+
						 "LEFT JOIN follow follow ON(USERS.uuserid=FOLLOW.ufollowid) "+
						 "WHERE USERS.uuserid="+ userID;

			//System.out.println(sql);
			st = cn.prepareStatement(sql);
			rs = st.executeQuery();
			
			rs.next();
			
			loginInfoB.setUserID(rs.getString("uuserid"));
			loginInfoB.setIntroduction(rs.getString("uintroduction"));
			loginInfoB.setUserName(rs.getString("uusername"));
			//loginInfoB.setIconPath(rs.getString("uiconpath"));
			loginInfoB.setPostCount(rs.getString("ツイート数"));
			//loginInfoB.setFollowCount(rs.getString("フォロー数"));
			//loginInfoB.setFollowerCount(rs.getString("フォロワー数"));


		}catch (SQLException e) {
			e.printStackTrace();
		}
		return loginInfoB;

	}
}
