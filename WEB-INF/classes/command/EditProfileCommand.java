package command;

import tera.RequestContext;
import tera.ResponseContext;

public class EditProfileCommand extends AbstractCommand{
  public ResponseContext execute(ResponseContext resc){
    RequestContext reqc=getRequestContext();
	
	

    resc.setTarget("editprofile");
    return resc;
  }
}