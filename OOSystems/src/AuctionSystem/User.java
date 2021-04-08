package AuctionSystem;
public abstract class User{
  protected String username, password;

  protected User(String username, String password){
    this.username = username;
    this.password = password;
  }
}
