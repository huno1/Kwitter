package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class OracleConnectionManager{
	private static OracleConnectionManager oraconn = null;
	private Connection cn = null;
	
	private OracleConnectionManager(){}
	
	public static OracleConnectionManager getInstance(){
		if(oraconn == null){
			oraconn = new OracleConnectionManager();
		}
		
		return oraconn;
	}
	
	public Connection getConnection(){
		try{
			if(cn == null || cn.isClosed() ){
					Class.forName("oracle.jdbc.driver.OracleDriver");
					
					Properties p = new Properties();
					p.load(AbstractDaoFactory.class.getClassLoader().getResourceAsStream("../properties/dao.properties"));
					String _host = p.getProperty("host");
					String _id = p.getProperty("id");
					String _pass = p.getProperty("pass");
					
					cn = DriverManager.getConnection(_host, _id, _pass);
			}
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return cn;
	}
	
	public void closeConnection(){
		try{
			if(cn != null || cn.isClosed()==false ){
				cn.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void beginTransaction(){
		try{
			if(cn == null || cn.isClosed()){
				getConnection();
			}
			cn.setAutoCommit(false);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void commit(){
		try{
			cn.commit();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void rollback(){
		try{
			cn.rollback();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}