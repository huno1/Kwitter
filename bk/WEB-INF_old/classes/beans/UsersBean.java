package beans;

public class UsersBean implements Bean{
  private String uUserID;
  private String uLoginID;
  private String uPassword;
  private String uUserName;
  private String uRegistrationDate;
  private String uIntroduction;
  private String uIconPath;
  private String uHeaderImagePath;

  public void setUUserID(String uUserID){
    this.uUserID = uUserID;
  }
  public String getUUserID(){
    return this.uUserID;
  }

  public void setULoginID(String uLoginID){
    this.uLoginID = uLoginID;
  }
  public String getULoginID(){
    return this.uLoginID;
  }

  public void setUPassword(String uPassword){
    this.uPassword = uPassword;
  }
  public String getUPassword(){
    return this.uPassword;
  }

  public void setUUserName(String uUserName){
    this.uUserName = uUserName;
  }
  public String getUUserName(){
    return this.uUserName;
  }

  public void setURegistrationDate(String uRegistrationDate){
    this.uRegistrationDate = uRegistrationDate;
  }
  public String getURegistrationDate(){
    return this.uRegistrationDate;
  }

  public void setUIntroduction(String uIntroduction){
    this.uIntroduction = uIntroduction;
  }
  public String getUIntroduction(){
    return this.uIntroduction;
  }

  public void setUIconPath(String uIconPath){
    this.uIconPath = uIconPath;
  }
  public String getUIconPath(){
    return this.uIconPath;
  }

  public void setUHeaderImagePath(String uHeaderImagePath){
    this.uHeaderImagePath = uHeaderImagePath;
  }
  public String getUHeaderImagePath(){
    return this.uHeaderImagePath;
  }
}
