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
    //Product�N���X���C���X�^���X������
     UsersBean userbean = new UsersBean();

    //RequestContext�̃C���X�^���X�̎擾
     RequestContext reqc =getRequestContext();
  	HttpServletRequest req =(HttpServletRequest)reqc.getRequest();

     //RequestContext������̓p�����[�^�̎擾
  	 String[] loginids=(String[])reqc.getParameter("loginid");
     String loginid=loginids[0];
  	 userbean.setLoginID(loginid);


  //  userbean.setLoginID(loginid);

    String[] passArray = (String[])reqc.getParameter("pass");
    String pass=passArray[0];
    userbean.setPassword(pass);

    //�g�����U�N�V�������J�n����
    OracleConnectionManager.getInstance().beginTransaction();

    //�C���e�O���[�V�������C���̏������Ăяo��
    AbstractDaoFactory factory = AbstractDaoFactory.getFactory();

    KretaDao dao = factory.getKretaDao();

    userbean = dao.LoginProcessing(userbean);
    if(userbean.getPassword().equals(pass)){
      loginflag = true;
    }else{
      System.out.println("pass��������ID���Ⴂ�܂�");
       resc.setResult("pass��������ID���Ⴂ�܂�");
    }

    if(loginflag){
      HttpSession session = req.getSession();
      session.setAttribute("loginUser", userbean);
    }

    //�g�����U�N�V�������I������
    OracleConnectionManager.getInstance().commit();
    //�R�l�N�V�����̐ؒf
    OracleConnectionManager.getInstance().closeConnection();


    //view�̏����Z�b�g���Ă��̃C���X�^���X�����^�[������
    resc.setTarget("login");
    return resc;
  }
}
