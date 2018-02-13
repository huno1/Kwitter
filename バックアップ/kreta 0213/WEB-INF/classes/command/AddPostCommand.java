package command;

import tera.RequestContext;
import tera.ResponseContext;
import beans.*;
import util.ImageUpload;

import dao.OracleConnectionManager;
import dao.AbstractDaoFactory;
import dao.KretaDao;

public class AddPostCommand extends AbstractCommand{

    public ResponseContext execute(ResponseContext resc){
      
      RequestContext reqc = getRequestContext();
      ImageUpload iu = new ImageUpload();
      PostBean pb = iu.execute(reqc);

      String postcontent = pb.getPostContent();
    	String imagePath = pb.getPostMedia();
    	
      UsersBean ub = (UsersBean)reqc.getUserInfo();
      String userid = ub.getUserID();

	  String sql = "insert into Post(pPostId ,pPostContent, uUserID, pstate, attachment) values(postid_seq.nextval, '"+ postcontent+"', '"+ userid +"', 0, '"+ imagePath +"')";

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
