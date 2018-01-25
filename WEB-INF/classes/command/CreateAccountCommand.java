package command;

import tera.RequestContext;
import tera.ResponseContext;

import beans.UsersBean;

import dao.AbstractDaoFactory;
import dao.KretaDao;

public class CreateAccountCommand extends AbstractCommand{
  public ResponseContext execute(ResponseContext resc){
    RequestContext reqc=getRequestContext();

		String userName=reqc.getParameter("userName")[0];
		String loginID=reqc.getParameter("loginId")[0];
		String password=reqc.getParameter("pass")[0];
    String checkPass=reqc.getParameter("checkPass")[0];

    if(password.equals(checkPass)){
  		String sql = "insert into Users (uUserID,uUserName,uLoginID,uPassword) values (userID_seq.nextval,'"+userName+"','"+loginID+"','"+password+"')";

  	  AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
  		KretaDao dao = factory.getKretaDao();
  		dao.sqlUpdate(sql);
    }
  	
    resc.setTarget("createaccount");
    return resc;
  }
}
