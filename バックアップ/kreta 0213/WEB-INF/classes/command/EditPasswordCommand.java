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

    //RequestContext�̃C���X�^���X�̎擾
    RequestContext reqc =getRequestContext();
    UsersBean userbean = (UsersBean)reqc.getUserInfo();
    //RequestContext������̓p�����[�^�̎擾
    String[] beforepass=(String[])reqc.getParameter("beforepass");
  	String[] afterpass=(String[])reqc.getParameter("afterpass");
  	String[] checkpass=(String[])reqc.getParameter("checkpass");

		//�p�X���[�h��ύX���邽�߂�SQL
		String sql="update Users set uPassword = '"+afterpass[0]+"' WHERE uUserName ='"+userbean.getLoginID()+"'";
		//Tomcat��Ŋm�F���邽�߂̃v�����g
    System.out.println(beforepass[0]);
    System.out.println(afterpass[0]);
    System.out.println(checkpass[0]);

    //�g�����U�N�V�������J�n����
    OracleConnectionManager.getInstance().beginTransaction();

    //�C���e�O���[�V�������C���̏������Ăяo��
    AbstractDaoFactory factory = AbstractDaoFactory.getFactory();

    KretaDao dao = factory.getKretaDao();
    if(beforepass[0]==""||afterpass[0]==""||checkpass[0]==""){
      System.out.println("�����͂̍��ڂ�����܂�");
      resc.setResult("<font size=\"5\" color=\"red\">error!</font><br>�����͂̍��ڂ�����܂�");
    }else	if(userbean.getPassword().equals(beforepass[0])){//���݂̃p�X���[�h�Ɠ��͂������̂���v���Ă��邩�`�F�b�N
			if(beforepass[0].equals(afterpass[0])){//�オ��v���Ă����ꍇ�A���݂̃p�X���[�h�ƐV�����p�X���[�h���ς���Ă��邩�`�F�b�N
				System.out.println("���݂̃p�X���[�h�ƐV�����p�X���[�h�������ł�");
				resc.setResult("<font size=\"5\" color=\"red\">error!</font><br>���݂̃p�X���[�h�ƐV�����p�X���[�h�������ł�");
			}else if(afterpass[0].equals(checkpass[0])){//�オ�ς���Ă����ꍇ�A�V�����p�X���[�h�Ɗm�F����v���Ă��邩�`�F�b�N
					dao.sqlUpdate(sql);
					userbean.setPassword(afterpass[0]);
					System.out.println("�p�X���[�h��ύX���܂����B");
					resc.setResult("�p�X���[�h��ύX���܂����B");
				}else{//�V�����p�X���[�h�Ɗm�F����v���Ă��Ȃ����update�����x��
					System.out.println("�V�����p�X���[�h�Ɗm�F����v���܂���");
		      resc.setResult("<font size=\"5\" color=\"red\">error!</font><br>�V�����p�X���[�h�Ɗm�F����v���܂���");
				}
		}else{//���݂̃p�X���[�h�ɓ��͂������̂���v���Ă��Ȃ����update�����x��
			System.out.println("���݂̃p�X���[�h���Ԉ���Ă��܂�");
      resc.setResult("<font size=\"5\" color=\"red\">error!</font><br>���݂̃p�X���[�h���Ԉ���Ă��܂�");
		}
    //�g�����U�N�V�������I������
    OracleConnectionManager.getInstance().commit();
    //�R�l�N�V�����̐ؒf
    OracleConnectionManager.getInstance().closeConnection();

    //view�̏����Z�b�g���Ă��̃C���X�^���X�����^�[������
    resc.setTarget("editpassword");
    return resc;
  }
}
