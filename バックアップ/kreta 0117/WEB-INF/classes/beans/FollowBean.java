package beans;

public class FollowBean implements Bean{
  private String uFollowID;
  private String uObserberID;

  public void setUFollowID(String uFollowID){
    this.uFollowID = uFollowID;
  }
  public String getUFollowID(){
    return this.uFollowID;
  }

  public void setUObserberID(String uObserberID){
    this.uObserberID = uObserberID;
  }
  public String getUObserberID(){
    return this.uObserberID;
  }
}
