package command;

import tera.RequestContext;
import tera.ResponseContext;

public class GetPostListCommand extends AbstractCommand{
  public ResponseContext execute(ResponseContext resc){
    RequestContext reqc=getRequestContext();
	
	

    resc.setTarget("postlist");
    return resc;
  }
}