package tera;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import beans.UsersBean;

public class WebRequestContext implements RequestContext{
	private Map _parameters;
	private HttpServletRequest _request;
	private UsersBean _userinfo;
	
	public String getCommandPath(){
		String servletPath=_request.getServletPath();
		
		String commandPath = servletPath.substring(1);
		
		return commandPath;
	}
	
	public String[] getParameter(String key){
		return (String[])_parameters.get(key);
	}
	
	public Object getRequest(){
		return _request;
	}
	
	public void setRequest(Object req){
		_request = (HttpServletRequest)req;
		
		_parameters = _request.getParameterMap();
	}
	
	public Object getUserInfo(){
		return _userinfo;
	}
	public void setUserInfo(Object userinfo){
		this._userinfo = (UsersBean)userinfo;
	}
}