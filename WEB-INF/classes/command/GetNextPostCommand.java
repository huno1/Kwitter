package command;

import tera.RequestContext;
import tera.ResponseContext;
import beans.*;
import dao.KretaDao;
import dao.OracleConnectionManager;
import dao.AbstractDaoFactory;



public class GetNextPostCommand extends AbstractCommand{
  public ResponseContext execute(ResponseContext resc){
	  
    RequestContext reqc=getRequestContext();

	String pid = reqc.getParameter("_pid")[0];
  
    OracleConnectionManager.getInstance().beginTransaction();
    AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
  	KretaDao dao = factory.getKretaDao();

  	PostBean pb = dao.getNextPost(pid);
	
    OracleConnectionManager.getInstance().commit();
    OracleConnectionManager.getInstance().closeConnection();

		pb.setAvatar("https://pbs.twimg.com/profile_images/875517108884455426/q3HMm7hU_400x400.jpg");
		pb.setPostMedia("https://video.twimg.com/tweet_video/DT3mBJEVoAAT1gg.mp4");
		pb.setReplyCount("23");
		pb.setLikeCount("123");

	resc.setResult(pb);

    resc.setTarget("postlist");
    return resc;
  }
}