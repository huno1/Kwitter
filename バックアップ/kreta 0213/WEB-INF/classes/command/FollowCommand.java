package command;

import tera.RequestContext;
import tera.ResponseContext;
import beans.UsersBean;
import beans.FollowBean;
import dao.OracleConnectionManager;
import dao.AbstractDaoFactory;
import dao.KretaDao;

public class FollowCommand extends AbstractCommand{
  public ResponseContext execute(ResponseContext resc){
    RequestContext reqc=getRequestContext();


    String[] follows=(String[])reqc.getParameter("follow");
    String follow=follows[0];
      //System.out.println(follow);

    UsersBean ub=(UsersBean)reqc.getUserInfo();

    String obserberID = ub.getLoginID();
    //System.out.println(obserberID);

      OracleConnectionManager ocm = OracleConnectionManager.getInstance();
  		ocm.beginTransaction();


      AbstractDaoFactory factory = AbstractDaoFactory.getFactory();

  		KretaDao kretadao = factory.getKretaDao();
    if(follow.equals(obserberID)){
        System.out.println("自分自身はフォローできません");
    }else{
      String sql="insert into follow values('"+follow+"','"+obserberID+"')";
      	try{
		  kretadao.sqlUpdate(sql);
		  
		resc.setResult(true);
		}catch(Exception e){
		}
    }
  		ocm.commit();
  		ocm.closeConnection();



    resc.setTarget("followcheck");
    return resc;
  }
}
