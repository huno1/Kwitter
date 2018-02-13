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

    //�g�����U�N�V�������J�n����
    OracleConnectionManager.getInstance().beginTransaction();

    //�C���e�O���[�V�������C���̏������Ăяo��
    AbstractDaoFactory factory = AbstractDaoFactory.getFactory();

    KretaDao dao = factory.getKretaDao();

    //System.out.println("���O�C������userid "+ dao.getUserID(sql1));
    //System.out.println("�폜���������e�̓��e��id "+ dao.getUserID(sql2));

     if(dao.getUserID(sql1).equals(dao.getUserID(sql2))){
       //System.out.println("���O�C�����̃��[�U�[ID�ƃ|�X�g�̓��e��ID����v");
       String sql3 = "UPDATE Post SET pState = 1 WHERE pPostid = "+ postid +" ";
       dao.sqlUpdate(sql3);
       resc.setResult("�폜�ɐ������܂����B");
     }else{
       //System.out.println("���O�C�����̃��[�U�[ID�ƃ|�X�g�̓��e��ID���s��v");
       resc.setResult("�폜�Ɏ��s���܂����B");
     }


    //�g�����U�N�V�������I������
    OracleConnectionManager.getInstance().commit();
    //�R�l�N�V�����̐ؒf
    OracleConnectionManager.getInstance().closeConnection();


    //view�̏����Z�b�g���Ă��̃C���X�^���X�����^�[������
    resc.setTarget("removepost");
    return resc;
  }
}
