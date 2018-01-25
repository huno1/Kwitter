package command;

import tera.ResponseContext;
import javax.servlet.http.HttpSession;

public class LogoutCommand extends AbstractCommand{
  public ResponseContext execute(ResponseContext resc){
    RequestContext reqc=getRequestContext();

  	//WEB�̍��肪����͍̂��͕ۗ�
  	HttpSession session = reqc.getSession();
  	session.invalidate();

    resc.setTarget("logout");
    return resc;
  }
}
