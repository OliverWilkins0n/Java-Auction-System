package AuctionSystem;

import java.util.LinkedList;
import java.util.List;
import java.time.LocalDateTime;

public final class Sys {

  //Setup Lists all all Users and Auctions
  private List<Auction> auctions = new LinkedList<Auction>();
  private List<User> users = new LinkedList<User>();

  public void createAccount(String username, String password, String accountType) throws Exception{
    if (accountType.equals("SELLER")){
      users.add(new Seller(username, password));
    } else if (accountType.equals("BUYER")){
      users.add(new Buyer(username, password));
    }
  }

  public void placeAuction(Seller seller, Item item, double startPrice, double reservePrice, LocalDateTime closeDate){
    auctions.add(new Auction(seller, item, startPrice, reservePrice, closeDate));
  }

  public List<Auction> browseAuctions(){
    return this.auctions;
  }
}
