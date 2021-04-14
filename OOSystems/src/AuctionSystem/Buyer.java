package AuctionSystem;

import java.io.Serializable;

public class Buyer extends User implements Serializable {


  public Buyer(String username, String password) throws Exception{
    super(username, password);
  }

  public void Victory(Buyer winner, Item item){
//	  System.out.println("Auction for: "+item.getName());
 //     System.out.println("Winner of auction: "+winner.getUsername()); Not needed it is done in bid
//
  }

}
