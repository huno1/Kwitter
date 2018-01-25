package command;

import tera.RequestContext;
import tera.ResponseContext;

public class SearchCommand extends AbstractCommand{
  public ResponseContext execute(ResponseContext resc){
    RequestContext reqc=getRequestContext();
	
	

    resc.setTarget("search");
    return resc;
  }
}