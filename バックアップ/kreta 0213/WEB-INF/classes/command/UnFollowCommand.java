package command;

import tera.RequestContext;
import tera.ResponseContext;
import beans.UsersBean;
import beans.FollowBean;
import dao.KretaDao;
import dao.OracleConnectionManager;
import dao.AbstractDaoFactory;

public class UnFollowCommand extends AbstractCommand{
  public ResponseContext execute(ResponseContext resc){
    RequestContext reqc=getRequestContext();


    String[] unfollows=(String[])reqc.getParameter("unfollow");
    String unfollowid=unfollows[0];


    UsersBean ub=(UsersBean)reqc.getUserInfo();

    String obserberID = ub.getLoginID();

      OracleConnectionManager ocm = OracleConnectionManager.getInstance();
  		ocm.beginTransaction();


      AbstractDaoFactory factory = AbstractDaoFactory.getFactory();

  		KretaDao dao = factory.getKretaDao();

		String sql="DELETE follow where uobserberID ='"+obserberID+"'and ufollowid ='"+unfollowid+"'";
	
      dao.sqlUpdate(sql);
	  
  		ocm.commit();
  		ocm.closeConnection();

    resc.setTarget("followcheck");
    return resc;
  }
}
