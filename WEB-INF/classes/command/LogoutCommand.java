package command;

import tera.RequestContext;
import tera.ResponseContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;


public class LogoutCommand extends AbstractCommand{
  public ResponseContext execute(ResponseContext resc){
    RequestContext reqc=getRequestContext();
  	HttpServletRequest req =(HttpServletRequest)reqc.getRequest();

  	//WEBÇÃçÅÇËÇ™Ç∑ÇÈÇÃÇÕç°ÇÕï€óØ
  	HttpSession session = req.getSession();
  	session.invalidate();

    resc.setTarget("logout");
    return resc;
  }
}
