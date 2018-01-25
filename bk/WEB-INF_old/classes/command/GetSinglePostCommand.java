package command;

import tera.RequestContext;
import tera.ResponseContext;

public class GetSinglePostCommand extends AbstractCommand{
  public ResponseContext execute(ResponseContext resc){
    RequestContext reqc=getRequestContext();
	
	PostBean pb = new PostBean();
	
	pb.setPostID("testID");
	
	resc.setResult(pb);


	

    resc.setTarget("postsingle");
    return resc;
  }
}