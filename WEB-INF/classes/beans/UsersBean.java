package beans;

public class UsersBean implements Bean{
  private String userID;
  private String loginID;
  private String password;
  private String userName;
  private String registrationDate;
  private String introduction;
  private String iconPath;
  private String headerImagePath;

  public void setUserID(String userID){
    this.userID = userID;
  	System.out.println("Beanの中でデバッグ");
  }
  public String getUserID(){
    return this.userID;
  }

  public void setLoginID(String loginID){
    this.loginID = loginID;
  }
  public String getLoginID(){
    return this.loginID;
  }

  public void setPassword(String password){
    this.password = password;
  }
  public String getPassword(){
    return this.password;
  }

  public void setUserName(String userName){
    this.userName = userName;
  }
  public String getUserName(){
    return this.userName;
  }

  public void setRegistrationDate(String registrationDate){
    this.registrationDate = registrationDate;
  }
  public String getRegistrationDate(){
    return this.registrationDate;
  }

  public void setIntroduction(String introduction){
    this.introduction = introduction;
  }
  public String getIntroduction(){
    return this.introduction;
  }

  public void setIconPath(String iconPath){
    this.iconPath = iconPath;
  }
  public String getIconPath(){
    return this.iconPath;
  }

  public void setHeaderImagePath(String headerImagePath){
    this.headerImagePath = headerImagePath;
  }
  public String getHeaderImagePath(){
    return this.headerImagePath;
  }
}
