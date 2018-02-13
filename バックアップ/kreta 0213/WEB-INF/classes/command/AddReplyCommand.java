package command;

import tera.RequestContext;
import tera.ResponseContext;
import beans.*;

import dao.OracleConnectionManager;
import dao.AbstractDaoFactory;
import dao.KretaDao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddReplyCommand extends AbstractCommand{
  public ResponseContext execute(ResponseContext resc){
      RequestContext reqc = getRequestContext();

      String[] postid = reqc.getParameter("pid");
      String[] postcontent = reqc.getParameter("postcontent");
    	
    	UsersBean ub = (UsersBean)reqc.getUserInfo();
    	String userid = ub.getUserID();

	 String sql = "insert into Reply(rReplyID ,pPostID, uUserID, rReplyContent) values(postid_seq.nextval, '"+postid[0]+"', '"+userid+"', '"+postcontent[0]+"')";

      OracleConnectionManager ocm = OracleConnectionManager.getInstance();
  		ocm.beginTransaction();


      AbstractDaoFactory factory = AbstractDaoFactory.getFactory();

  		KretaDao dao = factory.getKretaDao();
    	
    	
    	
    	dao.sqlUpdate(sql);

  		ocm.commit();
  		ocm.closeConnection();

        resc.setTarget("start");

        return resc;
  }
}
