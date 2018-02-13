package command;

import tera.RequestContext;
import tera.ResponseContext;
import beans.*;
import dao.KretaDao;
import dao.OracleConnectionManager;
import dao.AbstractDaoFactory;
import java.util.ArrayList;



public class GetReplyListCommand extends AbstractCommand{
  public ResponseContext execute(ResponseContext resc){
	  
    RequestContext reqc=getRequestContext();
	
	String pid = reqc.getParameter("_pid")[0];

    OracleConnectionManager.getInstance().beginTransaction();
    AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
  	KretaDao dao = factory.getKretaDao();

  	ArrayList<ReplyBean> rb = dao.getReplyList(pid);
	
    OracleConnectionManager.getInstance().commit();
    OracleConnectionManager.getInstance().closeConnection();

	resc.setResult(rb);

    resc.setTarget("replylist");
    return resc;
  }
}