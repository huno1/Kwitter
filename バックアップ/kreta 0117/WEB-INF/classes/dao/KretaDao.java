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
	public Bean getPost(){
		return new PostBean();
	}
	public List getAllPost(){
		return new ArrayList();
	}
}
