package command;

import tera.RequestContext;
import tera.ResponseContext;

public class UnFollowCommand extends AbstractCommand{
  public ResponseContext execute(ResponseContext resc){
    RequestContext reqc=getRequestContext();
	
	

    resc.setTarget("unfollow");
    return resc;
  }
}