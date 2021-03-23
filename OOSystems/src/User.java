public abstract class User{
  protected String username, password;


  protected User(String username, String password) throws Exception{
    this.username = username;
    if (isValid(password)) {
      this.password = password;
    } else {
      throw new Exception("Password to short!");
    }
  }

  public String getUsername(){
    return this.username;
  }

  //idk if this checkPassword should be to do this, or to like check if the
  //password they entered is the correct one for there account.
  protected boolean isValid(String password){
    if (password.length() >= 5){
      return true;
    } else {
      return false;
    }
}

public boolean checkPassword(int userPassword){
    if (password == userPassword) {
      return true;
    }
    else {
      return false;
    }

}
