package command;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;



import tera.RequestContext;
import tera.ResponseContext;

import dao.AbstractDaoFactory;
import dao.KretaDao;
import dao.OracleConnectionManager;

import beans.UsersBean;

public class LoginCommand extends AbstractCommand{

  public ResponseContext execute(ResponseContext resc){

    boolean loginflag= false;
    //Productクラスをインスタンス化する
     UsersBean userbean = new UsersBean();

    //RequestContextのインスタンスの取得
     RequestContext reqc =getRequestContext();
  	HttpServletRequest req =(HttpServletRequest)reqc.getRequest();

     //RequestContextから入力パラメータの取得
  	 String[] loginids=(String[])reqc.getParameter("loginid");
     String loginid=loginids[0];
  	 userbean.setLoginID(loginid);


  //  userbean.setLoginID(loginid);

    String[] passArray = (String[])reqc.getParameter("pass");
    String pass=passArray[0];
    userbean.setPassword(pass);

    //トランザクションを開始する
    OracleConnectionManager.getInstance().beginTransaction();

    //インテグレーションレイヤの処理を呼び出す
    AbstractDaoFactory factory = AbstractDaoFactory.getFactory();

    KretaDao dao = factory.getKretaDao();

    userbean = dao.LoginProcessing(userbean);
    if(userbean.getPassword().equals(pass)){
      loginflag = true;
    }else{
      System.out.println("passもしくはIDが違います");
       resc.setResult("passもしくはIDが違います");
    }

    if(loginflag){
      HttpSession session = req.getSession();
      session.setAttribute("loginUser", userbean);
    }

    //トランザクションを終了する
    OracleConnectionManager.getInstance().commit();
    //コネクションの切断
    OracleConnectionManager.getInstance().closeConnection();


    //viewの情報をセットしてそのインスタンスをリターンする
    resc.setTarget("login");
    return resc;
  }
}
