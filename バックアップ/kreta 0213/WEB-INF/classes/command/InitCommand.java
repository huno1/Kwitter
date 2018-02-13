package command;

import tera.RequestContext;
import tera.ResponseContext;


public class InitCommand extends AbstractCommand{
  public ResponseContext execute(ResponseContext resc){
    //RequestContext reqc=getRequestContext();
	
    resc.setTarget("index");
    return resc;
  }
}

