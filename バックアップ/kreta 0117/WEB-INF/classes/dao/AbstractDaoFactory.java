package dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public abstract class AbstractDaoFactory{
  public static AbstractDaoFactory getFactory(){
    AbstractDaoFactory f = null;
    Properties p = new Properties();

    try{
		
      p.load(AbstractDaoFactory.class.getClassLoader().getResourceAsStream("../properties/dao.properties"));
      String name = p.getProperty("dao");
      Class c = Class.forName(name);
      f = (AbstractDaoFactory) c.newInstance();
    }catch(Exception e){
      throw new RuntimeException(e.getMessage(), e);
    }
    return f;
  }

  public abstract ProductsDao getProductsDao();
}
