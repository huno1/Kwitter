package beans;

public class LoginUserInfoBean implements Bean{
  private String userID;
  private String userName;
  private String introduction;
  private String iconPath;
  private String postCount;
  private String followCount;
  private String followerCount;

  public void setUserID(String userID){
    this.userID = userID;
  }
  public String getUserID(){
    return this.userID;
  }

  public void setUserName(String userName){
    this.userName = userName;
  }
  public String getUserName(){
    return this.userName;
  }

  public void setIntroduction(String introduction){
    this.introduction = introduction;
  }
  public String getIntroduction(){
    return this.introduction;
  }

  public void setIconPath(String iconPath){
    this.iconPath = iconPath;
  }
  public String getIconPath(){
    return this.iconPath;
  }
	
  public void setPostCount(String postCount){
    this.postCount = postCount;
  }
  public String getPostCount(){
	return this.postCount;
  }
	
  public void setFollowCount(String followCount){
    this.followCount = followCount;
  }
  public String getFollowCount(){
	return this.followCount;
  }
	
  public void setFollowerCount(String followerCount){
    this.followerCount = followerCount;
  }
  public String getFollowerCount(){
	return this.followerCount;
  }
}
