package AuctionSystem;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;

public class Bid implements Serializable{
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double amount;
  private Buyer buyer;
  private LocalDateTime closeDate;
  private Bid currentMaxBid;
  private boolean noti = false;
  private Status status;
  
  /**
   * Bid Constructor
   * @param amount
   * @param buyer
   * @param bidDate
   */

  public Bid(double amount, Buyer buyer, LocalDateTime bidDate) {
    this.amount = amount;
    this.buyer = buyer;
    this.closeDate = bidDate;
  }
  
  @Override
  public String toString() {
	  return this.getClass().getSimpleName() + " >> " +
			  amount + " " +
			  buyer.toString() + " " +
			  closeDate.format(DateTimeFormatter.ofPattern("d MMM yy HH:mm"));
  
  }
  /**
   * 
   * @return amount
   */
  
  public double getAmount() {
	  return amount;
  }
  /**
   * 
   * @return buyer
   */
  
  public Buyer getBuyer() {
	  return buyer;
  }

public boolean isWinnerNotified() {
	return noti;
}

public void setIsWinnerNotified(Boolean noti) {
	this.noti = noti;
}

/**
 * Prints out Winning Notification
 * @param winner
 * @param item
 */
public void notifyWinner(Buyer winner, Item item) {
	System.out.println("Auction for: "+item.getName());
	System.out.println("Winner of auction: "+winner.getUsername());
}

/**
 * 
 * @return currentMaxBid
 */

public Bid getCurrentMaxBid() {
	//Bid highestBid = Collections.max(getBids(), Comparator.comparing(o -> o.getAmount()));
	return currentMaxBid;
}

public LocalDateTime getCloseDateTime() {
	return closeDate;
}

public Status getStatus() {
	return status;
}
public void setWon() {
	this.status = Status.CLOSED;
}
}
