public class User{
  protected String username, password;

  public User(String username, String password) {
    this.userName = username;
    this.password = password;

  }
  
  public boolean checkPassword(int userPassword){
      if (password == userPassword) {
        return true;
      }
      else {
        return false;
      }
}

}
