package beans;

public class PostBean implements Bean{
  private String pPostID;
  private String uUserID;
  private String pPostContent;
  private String pPostDate;
  private String pState;

  public void setPPostID(String pPostID){
    this.pPostID = pPostID;
  }
  public String getPPostID(){
    return this.pPostID;
  }

  public void setUUserID(String uUserID){
    this.uUserID = uUserID;
  }
  public String getUUserID(){
    return this.uUserID;
  }

  public void setPPostContent(String pPostContent){
    this.pPostContent = pPostContent;
  }
  public String getPPostContent(){
    return this.pPostContent;
  }

  public void setPPostDate(String pPostDate){
    this.pPostDate = pPostDate;
  }
  public String getPPostDate(){
    return this.pPostDate;
  }

  public void setPState(String pState){
    this.pState = pState;
  }
  public String getPState(){
    return this.pState;
  }
}
