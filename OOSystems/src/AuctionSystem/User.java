package AuctionSystem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class User implements Serializable{
  protected String username, password;
  private Status st;
  
 // private List<Seller> sellers = new ArrayList<Seller>();
 // private List<Buyer> buyers = new ArrayList<Buyer>();
  
  /**
   * 
   * @param username
   * @param password
   * @param type Can be Buyer or Seller
   * 
   */

  protected User(String username, String password){
    this.username = username;
    if (isValid(password)) {
    	this.password = password;
    } else {
    	System.out.println("Password needs to be longer!");
    }
    this.st = Status.ACTIVE;
  }
  
  /**
   * 
   * @return username
   */
  public String getUsername() {
	  return this.username;
  }
  
  /**
   * 
   * @return Status
   */
  public Status getStatus() {
	  return this.st;
  }
  
  public boolean isValid(String password) {
	  if(password.length()>=2) {
		  return true;
	  } else {
		  return false;
	  }
  }
  
  public boolean validatePassword(String password) {
	  return this.password.equals(password);
  }

  public void setBlocked() {
      this.st = Status.BLOCKED;
      }
  
 public boolean isBlocked() {
	  if (this.st.equals(Status.BLOCKED)) {
		  return true;
	  } else {
		  return false;
	  }
		  
  } 

  public void setUnblocked() {
      this.st = Status.ACTIVE;
      }
}
