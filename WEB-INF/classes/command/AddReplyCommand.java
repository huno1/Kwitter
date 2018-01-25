package command;

import tera.RequestContext;
import tera.ResponseContext;

public class AddReplyCommand extends AbstractCommand{
  public ResponseContext execute(ResponseContext resc){
    RequestContext reqc=getRequestContext();



    resc.setTarget("addreply");
    return resc;
  }
}
