package command;

import tera.RequestContext;
import tera.ResponseContext;

public class ShowPostCommand extends AbstractCommand{
  public ResponseContext execute(ResponseContext resc){
    RequestContext reqc=getRequestContext();



    resc.setTarget("showpost");
    return resc;
  }
}
