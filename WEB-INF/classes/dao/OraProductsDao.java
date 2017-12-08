package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tera.Product;

public class OraProductsDao implements ProductsDao{
  public void addProduct(Product p){
    Connection cn = null;
    PreparedStatement st = null;

    try{
		
      cn = OracleConnectionManager.getInstance().getConnection();

      String sql = "insert into t_products(pid, name, price) values(?,?,?)";

      st = cn.prepareStatement(sql);

      st.setString(1, p.getPid());
      st.setString(2, p.getName());
      st.setString(3, p.getPrice());

      st.executeUpdate();

    }catch(Exception e){
		e.printStackTrace();
    }finally{
		try{
			if(st != null){
				st.close();
			}
		}catch(SQLException e2){
			
		}finally{
			try{
				if(cn != null){
					cn.close();
				}
			}catch(SQLException e3){
				
			}
		}
	
	}
  }

  public Product getProduct(String pid){
    return null;
  }

  public List getAllProducts(){
    Connection cn = null;
    PreparedStatement st = null;
    ResultSet rs = null;

	ArrayList<Product> products = new ArrayList<>();
	
    try{
      Class.forName("oracle.jdbc.driver.OracleDriver");

      cn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "info", "pro");

      cn.setAutoCommit(false);

      //String sql = "select p.pid, p.name, p.price, u.name uname from t_products p, t_products_record tp, t_user u where p.pid = tp.pid and tp.userid = u.userid";
	  String sql = "select pid, name, price from t_products order by pid";
	
      st = cn.prepareStatement(sql);

      rs = st.executeQuery();

      while(rs.next()){
        Product p = new Product();
		
		p.setPid(rs.getString(1));
		p.setName(rs.getString(2));
		p.setPrice(rs.getString(3));
		//p.setUname(rs.getString(4));
		
		products.add(p);
      }

    }catch(SQLException ee){
		try{
			cn.rollback();
		}catch(SQLException eee){
			eee.printStackTrace();
		}
    }catch(Exception e){
		e.printStackTrace();
    }finally{
		try{
			if(rs != null){
				rs.close();
			}
			if(st != null){
				st.close();
			}
		}catch(SQLException eeee){
			eeee.printStackTrace();
		}finally{
			try{
				if(cn != null){
					cn.close();
				}
			}catch(SQLException eeeee){
				eeeee.printStackTrace();
			}
		}
	}
	
	return products;
	
  }
}
