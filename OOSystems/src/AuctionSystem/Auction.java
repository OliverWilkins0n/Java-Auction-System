package AuctionSystem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.time.LocalDate;

public final class Auction implements Serializable {



	private Seller seller;
	private Buyer buyer;
	private Item item;
	private double startPrice, reservePrice;
	private LocalDateTime closeDate;
	private Status status;
	private List<Bid> bids = new ArrayList<Bid>();

	public Auction(Seller seller, Item item, double startPrice, double reservePrice, LocalDateTime closeDate){
		this.seller = seller;
		this.item = item;
		this.startPrice = startPrice;
		this.reservePrice = reservePrice;
		//Need to make sure that the close date is within 7 days and not in the past
		this.closeDate = closeDate;
		this.status = Status.PENDING;
	}
	
	public String toString() {
		String theString = ("Item Name: "+this.item.getName()+" Start Price: "+this.startPrice+" Reserve Price: "+this.reservePrice+" Closing Date: "+this.closeDate);
		return theString;
	}

	//Get Functions
	public Seller getSeller(){
		return this.seller;
	}

	public Item getItem(){
		return this.item;
	}
	
	public double getStartPrice() {
		return this.startPrice;
	}
	public double getReservePrice() {
		return this.reservePrice;
	}
	
	public LocalDateTime getCloseDate() {
		return this.closeDate;
	}

//	public void placeBid(double amount, Buyer buyer) {
//		bidList.add(new Bid(amount, buyer, LocalDateTime.now()));
//	}

	public void placeBid(double amount, Buyer buyer) throws Exception{
		bids.add(new Bid(amount, buyer, LocalDateTime.now()));
	}

	public List<Bid> getBids(){
		return this.bids;
	}

	public void verify() {

	}

	public void close() {

	}

//	public boolean isBlocked() {
//
//	}
	//

	public void setBlocked() {

	}

}
