package beans;

public class AttachmentBean implements Bean{
  private String attachmentID;
  private String postID;
  private String filePath;

  public void setAttachmentID(String attachmentID){
    this.attachmentID = attachmentID;
  }
  public String getAttachmentID(){
    return this.attachmentID;
  }

  public void setPostID(String postID){
    this.postID = postID;
  }
  public String getPostID(){
    return this.postID;
  }

  public void setFilePath(String filePath){
    this.filePath = filePath;
  }
  public String getFilePath(){
    return this.filePath;
  }
}
