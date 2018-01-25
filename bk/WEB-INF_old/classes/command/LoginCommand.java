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
    //Product�N���X���C���X�^���X������
     UsersBean userbean = new UsersBean();

    //RequestContext�̃C���X�^���X�̎擾
     RequestContext reqc =getRequestContext();

     //RequestContext������̓p�����[�^�̎擾
     String[] loginids=reqc.getParameter("loginid");
     String loginid=loginids[0];

    userbean.setULoginID(loginid);

    String[] passArray = (String[])reqc.getParameter("pass");
    String pass=passArray[0];
    userbean.setUPassword(pass);

    //�g�����U�N�V�������J�n����
    OracleConnectionManager.getInstance().beginTransaction();

    //�C���e�O���[�V�������C���̏������Ăяo��
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

    //�g�����U�N�V�������I������
    OracleConnectionManager.getInstance().commit();
    //�R�l�N�V�����̐ؒf
    OracleConnectionManager.getInstance().closeConnection();


    //view�̏����Z�b�g���Ă��̃C���X�^���X�����^�[������
    resc.setTarget("login");
    return resc;
  }
}
