package tera;

import java.io.Serializable;

public class ProductRecord implements Serializable{
	
	private String pid;
	private String uid;
	
	public String getPid(){
		return pid;
	}
	public void setPid(String pid){
		this.pid = pid;
	}
	
	public String getUid(){
		return uid;
	}
	public void setUid(String uid){
		this.uid = uid;
	}
}