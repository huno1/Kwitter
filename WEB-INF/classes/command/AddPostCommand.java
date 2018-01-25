package command;

import tera.RequestContext;
import tera.ResponseContext;
import beans.PostBean;

import dao.OracleConnectionManager;
import dao.AbstractDaoFactory;
import dao.KretaDao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddPostCommand extends AbstractCommand{

    public ResponseContext execute(ResponseContext resc){
      RequestContext reqc = getRequestContext();

      String[] postContent = reqc.getParameter("postcontent");

      PostBean pb = new PostBean();

      pb.setPostContent(postContent[0]);

      OracleConnectionManager ocm = OracleConnectionManager.getInstance();
  		ocm.beginTransaction();


      AbstractDaoFactory factory = AbstractDaoFactory.getFactory();

  		KuretaDao dao = factory.getKretaDao();
    	
    	 String sql = "insert into Post(pPostId ,pPostContent) values(postid_seq.nextval,?)";
    	
    	
    	kreta.sqlUpdate(sql);

  		ocm.commit();
  		ocm.closeConnection();

        resc.setTarget("start");

        return resc;
    }

}
