package AuctionSystem;

import java.io.Serializable;

public class Buyer extends User implements Serializable {


  public Buyer(String username, String password) throws Exception{
    super(username, password);
  }

  public void Victory(){
      System.out.println("Winner of auction: ");

  }

}
