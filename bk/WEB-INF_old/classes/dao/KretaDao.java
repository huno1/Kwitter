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
	
	
	public  List LoginProcessing(UsersBean u){
      PreparedStatement st = null;
      ResultSet rs = null;

      ArrayList products = new ArrayList(); //もらった値を入れておくリスト
      try{

        Connection cn = null;
        //Connnectionインターフェイスを実装するクラスの
        //インスタンスを返す
        cn = OracleConnectionManager.getInstance().getConnection();

      	String sql ="SELECT uUserID,uLoginID from Users where uUserId="+u.getUUserID()+"uPassword"+u.getUPassword();

        st = cn.prepareStatement(sql);


        rs = st.executeQuery();
        if( rs != null){
        while(rs.next()){
          UsersBean ub =new UsersBean();
          ub.setUUserID(rs.getString(1));
          ub.setUPassword(rs.getString(2));
          products.add(ub);
        }
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
      return products;
    }
}
