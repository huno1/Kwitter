package command;

import tera.RequestContext;
import tera.ResponseContext;
import dao.AbstractDaoFactory;
import dao.KretaDao;
import dao.OracleConnectionManager;
import javax.servlet.http.HttpServletRequest;

import beans.UsersBean;

public class EditProfileCommand extends AbstractCommand{
  public ResponseContext execute(ResponseContext resc){

    //RequestContextのインスタンスの取得
     RequestContext reqc =getRequestContext();
     UsersBean userbean = (UsersBean)reqc.getUserInfo();
    //RequestContextから入力パラメータの取得
    String[] username=(String[])reqc.getParameter("username");
  	String[] introduction=(String[])reqc.getParameter("introduction");
  	String[] iconpath=(String[])reqc.getParameter("iconpath");
  	String[] headerimagepath=(String[])reqc.getParameter("headerimagepath");

    //String sql = "UPDATE Users SET (uUserName,uLoginID) = ("+username+","+loginid+") WHERE uUserName = '"+userbean.getUserName+"'";
    // String sql = "update Users set uUserName = '"+username[0]+"',uLoginID = '"+loginids[0]+"' WHERE uLoginID = '"+userbean.getLoginID()+"'";

    System.out.println(username[0]);
    System.out.println(introduction[0]);
    System.out.println(iconpath[0]);
    System.out.println(headerimagepath[0]);
    //トランザクションを開始する
    OracleConnectionManager.getInstance().beginTransaction();

    //インテグレーションレイヤの処理を呼び出す
    AbstractDaoFactory factory = AbstractDaoFactory.getFactory();

    KretaDao dao = factory.getKretaDao();
  	
  	//項目が一つも入力されていなければUpdateしない
    if(username[0] == "" && introduction[0] == "" && iconpath[0] == "" && headerimagepath[0] == ""){
      System.out.println("項目が何も入力されていません");
      resc.setResult("<font size=\"5\" color=\"red\">error!</font><br>項目が何も入力されていません");
    }
  	
  	//入力された項目だけUpdateするif
    if(username[0]!=""){
      String sql = "update Users set uUserName = '"+username[0]+"' WHERE uLoginID = '"+userbean.getLoginID()+"'";
      dao.sqlUpdate(sql);
      userbean.setUserName(username[0]);
      System.out.println("usernameを更新しました。");
      resc.setResult("プロフィールを更新しました。");
    }
    if(introduction[0]!=""){
      String sql = "update Users set uIntroduction = '"+introduction[0]+"' WHERE uLoginID = '"+userbean.getLoginID()+"'";
      dao.sqlUpdate(sql);
      userbean.setIntroduction(introduction[0]);
      System.out.println("introductionを更新しました。");
      resc.setResult("プロフィールを更新しました。");
    }
    if(iconpath[0]!=""){
      String sql = "update Users set uIconPath = '"+iconpath[0]+"' WHERE uLoginID = '"+userbean.getLoginID()+"'";
      dao.sqlUpdate(sql);
      userbean.setIconPath(iconpath[0]);
      System.out.println("iconpathを更新しました。");
      resc.setResult("プロフィールを更新しました。");
    }
    if(headerimagepath[0]!=""){
      String sql = "update Users set uHeaderImagePath = '"+headerimagepath[0]+"' WHERE uLoginID = '"+userbean.getLoginID()+"'";
      dao.sqlUpdate(sql);
      userbean.setHeaderImagePath(headerimagepath[0]);
      System.out.println("headerimagepathを更新しました。");
      resc.setResult("プロフィールを更新しました。");
    }

    //トランザクションを終了する
    OracleConnectionManager.getInstance().commit();
    //コネクションの切断
    OracleConnectionManager.getInstance().closeConnection();
    //viewの情報をセットしてそのインスタンスをリターンする
    resc.setTarget("editprofile");
    return resc;
  }
}
