package command;

import tera.RequestContext;
import tera.ResponseContext;

import beans.UsersBean;

import dao.AbstractDaoFactory;
import dao.KretaDao;
import dao.OracleConnectionManager;

public class CreateAccountCommand extends AbstractCommand{
  public ResponseContext execute(ResponseContext resc){
    RequestContext reqc=getRequestContext();

		String userName=reqc.getParameter("userName")[0];
		String loginID=reqc.getParameter("loginId")[0];
		String password=reqc.getParameter("pass")[0];
    String checkPass=reqc.getParameter("checkPass")[0];

    if(password.equals(checkPass)){

      OracleConnectionManager.getInstance().beginTransaction();
      AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
  		KretaDao dao = factory.getKretaDao();

      if(dao.loginIdCheck(loginID)==0){

    		String sql = "insert into Users (uUserID,uUserName,uLoginID,uPassword) values (userID_seq.nextval,'"+userName+"','"+loginID+"','"+password+"')";
        System.out.println(sql);

    		dao.sqlUpdate(sql);
        System.out.println("ìoò^äÆóπ");
      }else{
        System.out.println("LoginIDÇ™èdï°ÇµÇƒÇ¢Ç‹Ç∑");
      	resc.setResult("LoginIDÇ™èdï°ÇµÇƒÇ¢Ç‹Ç∑");
      }
      OracleConnectionManager.getInstance().commit();
      OracleConnectionManager.getInstance().closeConnection();
    }

    resc.setTarget("createaccount");
    return resc;
  }
}
