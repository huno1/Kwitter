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
		pb.setUserID("ローソン");
		pb.setAvatar("https://pbs.twimg.com/profile_images/875517108884455426/q3HMm7hU_400x400.jpg");
		pb.setPostContent("testID ＼ #でか焼鳥無料プレゼント ／ 1周年記念！フォロー＆リツイートで抽選で1日1万名様に さらにうまくなった #でか焼鳥 がその場で当たります(^^) 3日目は1/19 10:59まで！ #ローソン http://lawson.eng.mg/9d182");
		pb.setPostMedia("https://video.twimg.com/tweet_video/DT3mBJEVoAAT1gg.mp4");
		pb.setPostDate("2時間前");
		pb.setReplyCount("23");
		pb.setLikeCount("123");
	}else if(reqc.getParameter("_pid")[0].equals("2")){
		pb.setPostID("2");
		pb.setUserID("ローソン");
		pb.setAvatar("https://pbs.twimg.com/profile_images/875517108884455426/q3HMm7hU_400x400.jpg");
		pb.setPostContent("「マッシュルームとズッキーニの五穀入りチャウダー」が新発売♪ランチにもぴったりです(^^) #ローソン #コンビニスープ #ナチュラルローソン http://lawson.eng.mg/8d00b");
		pb.setPostMedia("/kreta/web-resources/images/DT3mByYUQAEhUUF.jpg");
		pb.setPostDate("3時間前");
		pb.setReplyCount("34");
		pb.setLikeCount("234");
	}
	resc.setResult(pb);


	

    resc.setTarget("postsingle");
    return resc;
  }
}