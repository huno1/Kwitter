package command;

import tera.RequestContext;
import tera.ResponseContext;
import beans.*;

public class GetSinglePostCommand extends AbstractCommand{
  public ResponseContext execute(ResponseContext resc){
    RequestContext reqc=getRequestContext();
	
	PostBean pb = new PostBean();
	
	if(reqc.getParameter("_pid")[0].equals("1")){
		pb.setPostID("1");
		pb.setUserID("���[�\��");
		pb.setAvatar("https://pbs.twimg.com/profile_images/875517108884455426/q3HMm7hU_400x400.jpg");
		pb.setPostContent("testID �_ #�ł��Ē������v���[���g �^ 1���N�L�O�I�t�H���[�����c�C�[�g�Œ��I��1��1�����l�� ����ɂ��܂��Ȃ��� #�ł��Ē� �����̏�œ�����܂�(^^) 3���ڂ�1/19 10:59�܂ŁI #���[�\�� http://lawson.eng.mg/9d182");
		pb.setPostMedia("https://video.twimg.com/tweet_video/DT3mBJEVoAAT1gg.mp4");
		pb.setPostDate("2���ԑO");
		pb.setReplyCount("23");
		pb.setLikeCount("123");
	}else if(reqc.getParameter("_pid")[0].equals("2")){
		pb.setPostID("2");
		pb.setUserID("���[�\��");
		pb.setAvatar("https://pbs.twimg.com/profile_images/875517108884455426/q3HMm7hU_400x400.jpg");
		pb.setPostContent("�u�}�b�V�����[���ƃY�b�L�[�j�̌܍�����`���E�_�[�v���V�����􃉃��`�ɂ��҂�����ł�(^^) #���[�\�� #�R���r�j�X�[�v #�i�`���������[�\�� http://lawson.eng.mg/8d00b");
		pb.setPostMedia("/kreta/web-resources/images/DT3mByYUQAEhUUF.jpg");
		pb.setPostDate("3���ԑO");
		pb.setReplyCount("34");
		pb.setLikeCount("234");
	}
	resc.setResult(pb);


	

    resc.setTarget("postsingle");
    return resc;
  }
}