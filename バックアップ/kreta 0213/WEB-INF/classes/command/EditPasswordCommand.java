package command;

import tera.RequestContext;
import tera.ResponseContext;
import dao.AbstractDaoFactory;
import dao.KretaDao;
import dao.OracleConnectionManager;
import javax.servlet.http.HttpServletRequest;

import beans.UsersBean;

public class EditPasswordCommand extends AbstractCommand{
  public ResponseContext execute(ResponseContext resc){

    //RequestContextのインスタンスの取得
    RequestContext reqc =getRequestContext();
    UsersBean userbean = (UsersBean)reqc.getUserInfo();
    //RequestContextから入力パラメータの取得
    String[] beforepass=(String[])reqc.getParameter("beforepass");
  	String[] afterpass=(String[])reqc.getParameter("afterpass");
  	String[] checkpass=(String[])reqc.getParameter("checkpass");

		//パスワードを変更するためのSQL
		String sql="update Users set uPassword = '"+afterpass[0]+"' WHERE uUserName ='"+userbean.getLoginID()+"'";
		//Tomcat上で確認するためのプリント
    System.out.println(beforepass[0]);
    System.out.println(afterpass[0]);
    System.out.println(checkpass[0]);

    //トランザクションを開始する
    OracleConnectionManager.getInstance().beginTransaction();

    //インテグレーションレイヤの処理を呼び出す
    AbstractDaoFactory factory = AbstractDaoFactory.getFactory();

    KretaDao dao = factory.getKretaDao();
    if(beforepass[0]==""||afterpass[0]==""||checkpass[0]==""){
      System.out.println("未入力の項目があります");
      resc.setResult("<font size=\"5\" color=\"red\">error!</font><br>未入力の項目があります");
    }else	if(userbean.getPassword().equals(beforepass[0])){//現在のパスワードと入力したものが一致しているかチェック
			if(beforepass[0].equals(afterpass[0])){//上が一致していた場合、現在のパスワードと新しいパスワードが変わっているかチェック
				System.out.println("現在のパスワードと新しいパスワードが同じです");
				resc.setResult("<font size=\"5\" color=\"red\">error!</font><br>現在のパスワードと新しいパスワードが同じです");
			}else if(afterpass[0].equals(checkpass[0])){//上が変わっていた場合、新しいパスワードと確認が一致しているかチェック
					dao.sqlUpdate(sql);
					userbean.setPassword(afterpass[0]);
					System.out.println("パスワードを変更しました。");
					resc.setResult("パスワードを変更しました。");
				}else{//新しいパスワードと確認が一致していなければupdateせず警告
					System.out.println("新しいパスワードと確認が一致しません");
		      resc.setResult("<font size=\"5\" color=\"red\">error!</font><br>新しいパスワードと確認が一致しません");
				}
		}else{//現在のパスワードに入力したものが一致していなければupdateせず警告
			System.out.println("現在のパスワードが間違っています");
      resc.setResult("<font size=\"5\" color=\"red\">error!</font><br>現在のパスワードが間違っています");
		}
    //トランザクションを終了する
    OracleConnectionManager.getInstance().commit();
    //コネクションの切断
    OracleConnectionManager.getInstance().closeConnection();

    //viewの情報をセットしてそのインスタンスをリターンする
    resc.setTarget("editpassword");
    return resc;
  }
}
