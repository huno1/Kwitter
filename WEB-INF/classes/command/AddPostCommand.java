package command;

import tera.RequestContext;
import tera.ResponseContext;
import beans.*;

import dao.OracleConnectionManager;
import dao.AbstractDaoFactory;
import dao.KretaDao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddPostCommand extends AbstractCommand{

    public ResponseContext execute(ResponseContext resc){
      RequestContext reqc = getRequestContext();

      String[] postcontent = reqc.getParameter("postcontent");
    	
    	UsersBean ub = (UsersBean)reqc.getUserInfo();
    	String userid = ub.getUserID();

	 String sql = "insert into Post(pPostId ,pPostContent, uUserID) values(postid_seq.nextval, '"+postcontent[0]+"', '"+userid+"')";

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
