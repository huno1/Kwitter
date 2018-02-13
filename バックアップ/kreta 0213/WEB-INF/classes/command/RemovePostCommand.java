package command;

import tera.RequestContext;
import tera.ResponseContext;

import beans.UsersBean;

import dao.AbstractDaoFactory;
import dao.KretaDao;
import dao.OracleConnectionManager;


public class RemovePostCommand extends AbstractCommand{

  public ResponseContext execute(ResponseContext resc){
    RequestContext reqc=getRequestContext();

    UsersBean ub = (UsersBean)reqc.getUserInfo();

    String loginID = ub.getLoginID();

    String postid=reqc.getParameter("postid")[0];

    String sql1 = "SELECT uUserID FROM Users WHERE uLoginID = '"+ loginID +"' ";
    //System.out.println(sql1);
    String sql2 = "SELECT uUserID FROM Post WHERE pPostID = "+ postid +" ";
    //System.out.println(sql2);

    //トランザクションを開始する
    OracleConnectionManager.getInstance().beginTransaction();

    //インテグレーションレイヤの処理を呼び出す
    AbstractDaoFactory factory = AbstractDaoFactory.getFactory();

    KretaDao dao = factory.getKretaDao();

    //System.out.println("ログイン中のuserid "+ dao.getUserID(sql1));
    //System.out.println("削除したい投稿の投稿者id "+ dao.getUserID(sql2));

     if(dao.getUserID(sql1).equals(dao.getUserID(sql2))){
       //System.out.println("ログイン中のユーザーIDとポストの投稿者IDが一致");
       String sql3 = "UPDATE Post SET pState = 1 WHERE pPostid = "+ postid +" ";
       dao.sqlUpdate(sql3);
       resc.setResult("削除に成功しました。");
     }else{
       //System.out.println("ログイン中のユーザーIDとポストの投稿者IDが不一致");
       resc.setResult("削除に失敗しました。");
     }


    //トランザクションを終了する
    OracleConnectionManager.getInstance().commit();
    //コネクションの切断
    OracleConnectionManager.getInstance().closeConnection();


    //viewの情報をセットしてそのインスタンスをリターンする
    resc.setTarget("removepost");
    return resc;
  }
}
