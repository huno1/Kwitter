package tera;

import javax.servlet.http.HttpServletResponse;

public class WebResponseContext implements ResponseContext{
	private Object result;
	private String target;
	
	private HttpServletResponse _response;
	
	public void setTarget(String transferInfo){
		target = "/WEB-INF/jsp/"+transferInfo+".jsp";
	}
	public String getTarget(){
		return target;
	}
	
	public void setResult(Object bean){
		result = bean;
	}
	public Object getResult(){
		return result;
	}
	
	public void setResponse(Object obj){
		_response = (HttpServletResponse)obj;
	}
	public Object getResponse(){
		return _response;
	}
}