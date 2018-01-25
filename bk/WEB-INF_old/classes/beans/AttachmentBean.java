package beans;

public class AttachmentBean implements Bean{
  private String aAttachmentID;
  private String pPostID;
  private String aFilePath;

  public void setAAttachmentID(String aAttachmentID){
    this.aAttachmentID = aAttachmentID;
  }
  public String getAAttachmentID(){
    return this.aAttachmentID;
  }

  public void setPPostID(String pPostID){
    this.pPostID = pPostID;
  }
  public String getPPostID(){
    return this.pPostID;
  }

  public void setAFilePath(String aFilePath){
    this.aFilePath = aFilePath;
  }
  public String getAFilePath(){
    return this.aFilePath;
  }
}
