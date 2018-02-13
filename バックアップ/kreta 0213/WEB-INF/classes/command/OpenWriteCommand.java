package command;

import tera.RequestContext;
import tera.ResponseContext;

public class OpenWriteCommand extends AbstractCommand{
  public ResponseContext execute(ResponseContext resc){
    RequestContext reqc=getRequestContext();
	
	

    resc.setTarget("postwrite");
    return resc;
  }
}