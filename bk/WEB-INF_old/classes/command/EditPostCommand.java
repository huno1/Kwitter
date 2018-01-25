package command;

import tera.RequestContext;
import tera.ResponseContext;

public class EditPostCommand extends AbstractCommand{
  public ResponseContext execute(ResponseContext resc){
    RequestContext reqc=getRequestContext();
	
	

    resc.setTarget("editpost");
    return resc;
  }
}