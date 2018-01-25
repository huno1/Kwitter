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
	public List getAllPost(){
		return new ArrayList();
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

      	String sql ="SELECT uUserID, uLogiID, uPassword, uUserName from Users where uLoginID='"+u.getLoginID()+"' && uPassword = '" + u.getPassword() + "'";

        st = cn.prepareStatement(sql);

        rs = st.executeQuery();
      	System.out.println(rs.getString(1));
      	      	System.out.println("test");
         ub.setUserID(rs.getString(1));
         ub.setLoginID(rs.getString(2));
         ub.setPassword(rs.getString(3));
         ub.setUserName(rs.getString(4));

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
}
