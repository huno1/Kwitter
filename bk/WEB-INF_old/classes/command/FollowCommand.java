package command;

import tera.RequestContext;
import tera.ResponseContext;

public class FollowCommand extends AbstractCommand{
  public ResponseContext execute(ResponseContext resc){
    RequestContext reqc=getRequestContext();
	
	

    resc.setTarget("follow");
    return resc;
  }
}