public abstract class User{
  protected String username, password;


  protected User(String username, String password) throws Exception{
    this.username = username;
    this.password = password;
  }

  public String getUsername(){
    return this.username;
  }

  protected boolean isValid(String password){
    if (password.length() >= 5){
      return true;
    } else {
      return false;
    }
}

public boolean checkPassword(String password){
    if (this.password == password) {
      return true;
    }
    else {
      return false;
    }
}
}
