package beans;

import java.util.ArrayList;

public class PostBean implements Bean{
  private String postID;
  private String userID;
  private String avatar;
  private String postContent;
  private String postMedia;
  private String postDate;
  private String state;
  private String replyCount;
  private String likeCount;
  private ArrayList<Bean> replies = new ArrayList<>();
  

  public void setPostID(String postID){
    this.postID = postID;
  }
  public String getPostID(){
    return this.postID;
  }

  public void setUserID(String userID){
    this.userID = userID;
  }
  public String getUserID(){
    return this.userID;
  }

  public void setAvatar(String avatar){
    this.avatar = avatar;
  }
  public String getAvatar(){
    return this.avatar;
  }

  public void setPostContent(String postContent){
    this.postContent = postContent;
  }
  public String getPostContent(){
    return this.postContent;
  }

  public void setPostDate(String postDate){
    this.postDate = postDate;
  }
  public String getPostDate(){
    return this.postDate;
  }

  public void setState(String state){
    this.state = state;
  }
  public String getState(){
    return this.state;
  }

  public void setReplyCount(String r){
    this.replyCount = r;
  }
  public String getReplyCount(){
    return this.replyCount;
  }

  public void setLikeCount(String l){
    this.likeCount = l;
  }
  public String getLikeCount(){
    return this.likeCount;
  }

  public void setPostMedia(String postMedia){
    this.postMedia = postMedia;
  }
  public String getPostMedia(){
    return this.postMedia;
  }

  public void setReplies(ArrayList<Bean> replies){
    this.replies = replies;
  }
  public ArrayList<Bean> getReplies(){
    return this.replies;
  }
}
