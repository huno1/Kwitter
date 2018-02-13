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

    //RequestContext�̃C���X�^���X�̎擾
     RequestContext reqc =getRequestContext();
     UsersBean userbean = (UsersBean)reqc.getUserInfo();
    //RequestContext������̓p�����[�^�̎擾
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
    //�g�����U�N�V�������J�n����
    OracleConnectionManager.getInstance().beginTransaction();

    //�C���e�O���[�V�������C���̏������Ăяo��
    AbstractDaoFactory factory = AbstractDaoFactory.getFactory();

    KretaDao dao = factory.getKretaDao();
  	
  	//���ڂ�������͂���Ă��Ȃ����Update���Ȃ�
    if(username[0] == "" && introduction[0] == "" && iconpath[0] == "" && headerimagepath[0] == ""){
      System.out.println("���ڂ��������͂���Ă��܂���");
      resc.setResult("<font size=\"5\" color=\"red\">error!</font><br>���ڂ��������͂���Ă��܂���");
    }
  	
  	//���͂��ꂽ���ڂ���Update����if
    if(username[0]!=""){
      String sql = "update Users set uUserName = '"+username[0]+"' WHERE uLoginID = '"+userbean.getLoginID()+"'";
      dao.sqlUpdate(sql);
      userbean.setUserName(username[0]);
      System.out.println("username���X�V���܂����B");
      resc.setResult("�v���t�B�[�����X�V���܂����B");
    }
    if(introduction[0]!=""){
      String sql = "update Users set uIntroduction = '"+introduction[0]+"' WHERE uLoginID = '"+userbean.getLoginID()+"'";
      dao.sqlUpdate(sql);
      userbean.setIntroduction(introduction[0]);
      System.out.println("introduction���X�V���܂����B");
      resc.setResult("�v���t�B�[�����X�V���܂����B");
    }
    if(iconpath[0]!=""){
      String sql = "update Users set uIconPath = '"+iconpath[0]+"' WHERE uLoginID = '"+userbean.getLoginID()+"'";
      dao.sqlUpdate(sql);
      userbean.setIconPath(iconpath[0]);
      System.out.println("iconpath���X�V���܂����B");
      resc.setResult("�v���t�B�[�����X�V���܂����B");
    }
    if(headerimagepath[0]!=""){
      String sql = "update Users set uHeaderImagePath = '"+headerimagepath[0]+"' WHERE uLoginID = '"+userbean.getLoginID()+"'";
      dao.sqlUpdate(sql);
      userbean.setHeaderImagePath(headerimagepath[0]);
      System.out.println("headerimagepath���X�V���܂����B");
      resc.setResult("�v���t�B�[�����X�V���܂����B");
    }

    //�g�����U�N�V�������I������
    OracleConnectionManager.getInstance().commit();
    //�R�l�N�V�����̐ؒf
    OracleConnectionManager.getInstance().closeConnection();
    //view�̏����Z�b�g���Ă��̃C���X�^���X�����^�[������
    resc.setTarget("editprofile");
    return resc;
  }
}
