package beans;

public class ReplyBean implements Bean{
  private String replyID;
  private String postID;
  private String loginID;
  private String userName;
  private String replyDate;
  private String replyContent;
  private String iconPath;

  public void setReplyID(String replyID){
    this.replyID = replyID;
  }
  public String getReplyID(){
    return this.replyID;
  }

  public void setPostID(String postID){
    this.postID = postID;
  }
  public String getPostID(){
    return this.postID;
  }

  public void setLoginID(String loginID){
    this.loginID = loginID;
  }
  public String getLoginID(){
    return this.loginID;
  }

  public void setUserName(String userName){
    this.userName = userName;
  }
  public String getUserName(){
    return this.userName;
  }

  public void setReplyDate(String replyDate){
    this.replyDate = replyDate;
  }
  public String getReplyDate(){
    return this.replyDate;
  }

  public void setReplyContent(String replyContent){
    this.replyContent = replyContent;
  }
  public String getReplyContent(){
    return this.replyContent;
  }

  public void setIconPath(String iconPath){
    this.iconPath = iconPath;
  }
  public String getIconPath(){
    return this.iconPath;
  }
}
