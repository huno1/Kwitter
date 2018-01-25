package command;
import java.util.Map;
import java.util.List;


import tera.RequestContext;
import tera.ResponseContext;

import dao.AbstractDaoFactory;
import dao.ProductsDao;
import dao.OracleConnectionManager;

import beans.UsersBean;

public class LoginCommand extends AbstractCommand{

  public ResponseContext execute(ResponseContext resc){
    boolean loginflag= false;
    List loginResult=null;
    //Productクラスをインスタンス化する
     UsersBean userbean = new UsersBean();

    //RequestContextのインスタンスの取得
     RequestContext reqc =getRequestContext();

     //RequestContextから入力パラメータの取得
     String[] loginids=reqc.getParameter("loginid");
     String loginid=loginids[0];

    userbean.setULoginID(loginid);

    String[] passArray = (String[])reqc.getParameter("pass");
    String pass=passArray[0];
    userbean.setUPassword(pass);

    //トランザクションを開始する
    OracleConnectionManager.getInstance().beginTransaction();

    //インテグレーションレイヤの処理を呼び出す
    AbstractDaoFactory factory = AbstractDaoFactory.getFactory();

    KretaDao dao = factory.getKretaDao();

    loginResult = dao.LoginProcessing(userbean);
    if(loginResult != null){
      loginflag =   true    ;
    }
    if(loginflag){
      HttpSession session = req.getSession();
      session.setAttribute("loginUser", loginResult);
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
