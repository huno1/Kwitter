package command;

import tera.RequestContext;
import tera.ResponseContext;
import beans.*;
import dao.KretaDao;
import dao.OracleConnectionManager;
import dao.AbstractDaoFactory;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;



public class LoginUserInfoCommand extends AbstractCommand{
  public ResponseContext execute(ResponseContext resc){
    RequestContext reqc=getRequestContext();
  	HttpServletRequest req =(HttpServletRequest)reqc.getRequest();

  	HttpSession session = req.getSession();
  	UsersBean ub = (UsersBean)session.getAttribute("loginUser");
  	String userID = ub.getUserID();
  
    OracleConnectionManager.getInstance().beginTransaction();
    AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
  	KretaDao dao = factory.getKretaDao();

  	LoginUserInfoBean loginInfoB = dao.getLoginUserInfo(userID);
  	
    OracleConnectionManager.getInstance().commit();
    OracleConnectionManager.getInstance().closeConnection();

  	
  	loginInfoB.setIconPath("https://pbs.twimg.com/profile_images/875517108884455426/q3HMm7hU_400x400.jpg");
  	loginInfoB.setFollowCount("50");
  	loginInfoB.setFollowerCount("88");
  	
  	System.out.println(loginInfoB.getUserID());
  	System.out.println(loginInfoB.getUserName());
  	System.out.println(loginInfoB.getIntroduction());
  	System.out.println(loginInfoB.getIconPath());
  	System.out.println(loginInfoB.getPostCount());
  	System.out.println(loginInfoB.getFollowCount());
  	System.out.println(loginInfoB.getFollowerCount());
  	
	resc.setResult(loginInfoB);

    resc.setTarget("start");
    return resc;
  }
}