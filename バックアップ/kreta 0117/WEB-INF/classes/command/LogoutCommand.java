package command;

import tera.ResponseContext;
import javax.servlet.http.HttpSession;

public class LogoutCommand extends AbstractCommand{
  public ResponseContext execute(ResponseContext resc){
    RequestContext reqc=getRequestContext();
  	
  	//WEBの香りがするのは今は保留
  	HttpSession session = reqc.getSession();
  	session.invalidate();

    resc.setTarget("start");
    return resc;
  }
}