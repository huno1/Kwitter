package command;

import tera.RequestContext;
import tera.ResponseContext;

public class RemoveReplyCommand extends AbstractCommand{
  public ResponseContext execute(ResponseContext resc){
    RequestContext reqc=getRequestContext();



    resc.setTarget("removereply");
    return resc;
  }
}
