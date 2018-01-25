package command;

import tera.RequestContext;
import tera.ResponseContext;

public class CreateAccountCommand extends AbstractCommand{
  public ResponseContext execute(ResponseContext resc){
    RequestContext reqc=getRequestContext();
	
	

    resc.setTarget("createaccount");
    return resc;
  }
}