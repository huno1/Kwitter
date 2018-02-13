package beans;

public class FollowBean implements Bean{
  private String followID;
  private String obserberID;

  public void setFollowID(String followID){
    this.followID = followID;
  }
  public String getFollowID(){
    return this.followID;
  }

  public void setObserberID(String obserberID){
    this.obserberID = obserberID;
  }
  public String getObserberID(){
    return this.obserberID;
  }
}
