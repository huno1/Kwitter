package command;

import tera.ResponseContext;
import javax.servlet.http.HttpSession;

public class LogoutCommand extends AbstractCommand{
  public ResponseContext execute(ResponseContext resc){
    RequestContext reqc=getRequestContext();

  	//WEB‚Ì‚è‚ª‚·‚é‚Ì‚Í¡‚Í•Û—¯
  	HttpSession session = reqc.getSession();
  	session.invalidate();

    resc.setTarget("logout");
    return resc;
  }
}
