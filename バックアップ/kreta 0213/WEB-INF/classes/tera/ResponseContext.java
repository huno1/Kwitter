package tera;

public interface ResponseContext{
	public Object getResult();
	String getTarget();
	Object getResponse();
	void setResult(Object bean);
	void setTarget(String transferInfo);
	void setResponse(Object obj);
}