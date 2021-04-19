package AuctionSystem;

import java.io.Serializable;

public class Buyer extends User implements Serializable {

	/**
	 * Buyer Constructor
	 * @param username
	 * @param password
	 * @throws Exception
	 */

  public Buyer(String username, String password) throws Exception{
    super(username, password);
  }

  public void Victory(Buyer winner, Item item){
//	  System.out.println("Auction for: "+item.getName());
 //     System.out.println("Winner of auction: "+winner.getUsername()); Not needed it is done in bid
//
  }

}
