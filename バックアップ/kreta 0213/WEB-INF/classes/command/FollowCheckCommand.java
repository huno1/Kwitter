package command;

import tera.RequestContext;
import tera.ResponseContext;
import beans.UsersBean;
import beans.FollowBean;
import dao.OracleConnectionManager;
import dao.AbstractDaoFactory;
import dao.KretaDao;

public class FollowCheckCommand extends AbstractCommand{
  public ResponseContext execute(ResponseContext resc){
    RequestContext reqc=getRequestContext();


      UsersBean ub=(UsersBean)reqc.getUserInfo();
	  String targetname = reqc.getParameter("_tid")[0];

      FollowBean fb=new FollowBean();

      fb.setObserberID(ub.getLoginID());
      fb.setFollowID(targetname);

      OracleConnectionManager ocm = OracleConnectionManager.getInstance();
  		ocm.beginTransaction();


      AbstractDaoFactory factory = AbstractDaoFactory.getFactory();

  		KretaDao dao = factory.getKretaDao();

	  Boolean result = dao.checkFollow(fb);
	  
  		ocm.commit();
  		ocm.closeConnection();

	  
	resc.setResult(result);
	  
    resc.setTarget("followcheck");
	
    return resc;
  }
}