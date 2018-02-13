package command;

import tera.RequestContext;
import tera.ResponseContext;
import beans.*;
import dao.KretaDao;
import dao.OracleConnectionManager;
import dao.AbstractDaoFactory;
import util.HTMLFilter;

public class GetNextPostCommand extends AbstractCommand{
  public ResponseContext execute(ResponseContext resc){
	  
    RequestContext reqc=getRequestContext();

	String pid = reqc.getParameter("_pid")[0];
	String s = reqc.getParameter("_s")[0];
	String u = reqc.getParameter("_u")[0];
	String f = "null";
	
	if(reqc.getUserInfo()!=null){
		UsersBean ub = (UsersBean)reqc.getUserInfo();
		f = ub.getLoginID();
	}
  
    OracleConnectionManager.getInstance().beginTransaction();
    AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
  	KretaDao dao = factory.getKretaDao();

  	PostBean pb = dao.getNextPost(pid, s, u, f);
	
    OracleConnectionManager.getInstance().commit();
    OracleConnectionManager.getInstance().closeConnection();
	
	pb.setPostContent(HTMLFilter.filter(pb.getPostContent()));

	resc.setResult(pb);

    resc.setTarget("postlist");
    return resc;
  }
}