package tera;

import java.io.IOException;
import java.util.Properties;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.*;
import beans.*;

public class WebApplicationController implements ApplicationController {
	public RequestContext getRequest(Object request){
		RequestContext reqc = new WebRequestContext();
		
		reqc.setRequest(request);
		
		Object loginuser = null;
		
		try{
			HttpServletRequest hsr = (HttpServletRequest)request;
			HttpSession hs = hsr.getSession();
			
			
			if(hs.getAttribute("loginUser")!=null){
				loginuser = hs.getAttribute("loginUser");
			}else{
				UsersBean guest = new UsersBean();
				guest.setUserID("-1");
				guest.setLoginID("null");
				guest.setUserName("ÉQÉXÉg");
				
				loginuser = guest;
			}
			
			
		}catch(Throwable t){}
		
		reqc.setUserInfo(loginuser);
		
		return reqc;
	}
	
	public ResponseContext handleRequest(RequestContext reqc){
		AbstractCommand command = CommandFactory.getCommand(reqc);
		command.init(reqc);
		
		ResponseContext resc = command.execute(new WebResponseContext());
		
		return resc;
	}
	
	public void handleResponse(RequestContext reqc, ResponseContext resc){
		
		HttpServletRequest req = (HttpServletRequest) reqc.getRequest();
		HttpServletResponse res = (HttpServletResponse) resc.getResponse();
		
		req.setAttribute("result", resc.getResult());
		
		RequestDispatcher rd = req.getRequestDispatcher(resc.getTarget());
		
		try{
			rd.forward(req, res);
		}catch(ServletException e){
			
		}catch(IOException ee){
			
		}
	}
}
