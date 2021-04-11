package AuctionSystem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class User implements Serializable{
  protected String username, password;
  private Status st;
  
 // private List<Seller> sellers = new ArrayList<Seller>();
 // private List<Buyer> buyers = new ArrayList<Buyer>();

  protected User(String username, String password){
    this.username = username;
    this.password = password;
  }
  
  public String getUsername() {
	  return this.username;
  }
  
  public boolean validatePassword(String password) {
	  return this.password.equals(password);
  }
  
  public void setBlocked() {
      this.st = Status.BLOCKED;
      }

  public void setUnblocked() {
      this.st = Status.ACTIVE;
      }
}
