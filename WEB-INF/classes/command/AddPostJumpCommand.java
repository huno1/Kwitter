package command;

import tera.RequestContext;
import tera.ResponseContext;


public class AddPostJumpCommand extends AbstractCommand{
  public ResponseContext execute(ResponseContext resc){
    RequestContext reqc=getRequestContext();

    resc.setTarget("addpost");
    return resc;
  }
}

