package beans;

public class ReplyBean implements Bean{
  private String replyID;
  private String postID;
  private String targetUserID;
  private String userID;
  private String replyDate;
  private String replyContent;

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

  public void setTargetUserID(String targetUserID){
    this.targetUserID = targetUserID;
  }
  public String getTargetUserID(){
    return this.targetUserID;
  }

  public void setUserID(String userID){
    this.userID = userID;
  }
  public String getUserID(){
    return this.userID;
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
}
