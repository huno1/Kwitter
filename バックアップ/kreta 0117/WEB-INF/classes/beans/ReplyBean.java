package beans;

public class ReplyBean implements Bean{
  private String rReplyID;
  private String pPostID;
  private String uTargetUserID;
  private String uUserID;
  private String rReplyDate;
  private String rReplyContent;

  public void setRReplyID(String rReplyID){
    this.rReplyID = rReplyID;
  }
  public String getRReplyID(){
    return this.rReplyID;
  }

  public void setPPostID(String pPostID){
    this.pPostID = pPostID;
  }
  public String getPPostID(){
    return this.pPostID;
  }

  public void setUTargetUserID(String uTargetUserID){
    this.uTargetUserID = uTargetUserID;
  }
  public String getUTargetUserID(){
    return this.uTargetUserID;
  }

  public void setUUserID(String uUserID){
    this.uUserID = uUserID;
  }
  public String getUUserID(){
    return this.uUserID;
  }

  public void setRReplyDate(String rReplyDate){
    this.rReplyDate = rReplyDate;
  }
  public String getRReplyDate(){
    return this.rReplyDate;
  }

  public void setRReplyContent(String rReplyContent){
    this.rReplyContent = rReplyContent;
  }
  public String getRReplyContent(){
    return this.rReplyContent;
  }
}
