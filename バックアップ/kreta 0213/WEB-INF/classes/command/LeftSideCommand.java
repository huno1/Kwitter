package command;

import tera.RequestContext;
import tera.ResponseContext;
import beans.UsersBean;
import dao.KretaDao;
import dao.OracleConnectionManager;
import dao.AbstractDaoFactory;

public class LeftSideCommand extends AbstractCommand{
  public ResponseContext execute(ResponseContext resc){
    RequestContext reqc=getRequestContext();
	  
	  UsersBean result = null;
	  String targetid = "";
	  
	  System.out.println(reqc.getParameter("u")[0]);
	  
	  if(reqc.getParameter("u")[0]==null || reqc.getParameter("u")[0].equals("")==true ){
		  
		UsersBean temp = (UsersBean)reqc.getUserInfo();
		targetid = temp.getLoginID();
		
	  }else{
		  
	    targetid = reqc.getParameter("u")[0];
		
	  }
	  
		OracleConnectionManager.getInstance().beginTransaction();
		AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
		
		KretaDao dao = factory.getKretaDao();
	  
		result = dao.getUserInfo(targetid);
		
		OracleConnectionManager.getInstance().closeConnection();

	  
	resc.setResult(result);
	  
    resc.setTarget("leftside");
	
    return resc;
  }
}