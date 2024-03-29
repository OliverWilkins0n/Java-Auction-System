package AuctionSystem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
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
	
	/**
	 * Auction Constructor
	 * @param seller
	 * @param item
	 * @param startPrice
	 * @param reservePrice
	 * @param closeDate
	 * @throws Exception
	 */

	public Auction(Seller seller, Item item, double startPrice, double reservePrice, LocalDateTime closeDate) throws Exception{
		this.seller = seller;
		this.item = item;
		this.startPrice = startPrice;
		this.reservePrice = reservePrice;
		//Need to make sure that the close date is within 7 days and not in the past
		if (this.closeDateValidation(closeDate)) {
			this.closeDate = closeDate;
		} else {
			throw new Exception("Close date should be within 7 days and not in the past!");
		}
		this.status = Status.PENDING;
	}
	
	public String toString() {
		//String theString = ("Item Name: "+this.item.getName()+" Status: "+this.getStatus()+" Start Price: "+this.startPrice+" Reserve Price: "+this.reservePrice+" Closing Date: "+this.closeDate);
		String theString = ("Item Name: "+this.item.getName()+" Status: "+this.getStatus()+" Start Price: "+this.startPrice+" Reserve Price: "+this.reservePrice+" Closing Date: "+this.closeDate.getDayOfMonth() + "." +this.closeDate.getMonth() + "."+ this.closeDate.getYear()+ " " +this.closeDate.getHour()+":" +this.closeDate.getMinute());
		return theString;
	}

	//Get Functions
	public Seller getSeller(){
		return this.seller;
	}
	
	public Buyer getBuyer(){
		return this.buyer;
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
	
	public Status getStatus() {
        return this.status;
    }


	//verifying the date, that it is within 7 days and not in the past
	public boolean closeDateValidation(LocalDateTime inputDate) {
		if(inputDate.isAfter(LocalDateTime.now()) && inputDate.isBefore(LocalDateTime.now().plusDays(7))) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Place Bid
	 * @param amount
	 * @param buyer
	 * @throws Exception
	 */
	public void placeBid(double amount, Buyer buyer) throws Exception{
		Bid currentMaxBid = getHighestBid();
		System.out.println("current Max "+currentMaxBid);
		if (Objects.isNull(currentMaxBid)){
			bids.add(new Bid(amount, buyer, LocalDateTime.now()));
		} else if (amount > (currentMaxBid.getAmount()*1.1)) {
			bids.add(new Bid(amount, buyer, LocalDateTime.now()));
			System.out.println("Bid Placed");
		} else {
			System.out.println("The bid needs to be 10% greater then the current highest bid");
		}

	}
	
	/**
	 * 
	 * @return The Greatest Bid on an item
	 */
	public Bid getHighestBid() {
		/*
		List<Bid> allBids = getBids();
		//System.out.print("GetHighestBid "+allBids);
		Bid currentHighest = null;
		if(!allBids.isEmpty()){
			for(Bid i : allBids) {
				if(i.getAmount() > currentHighest.getAmount()) {
					currentHighest = i;
				} else if(i.getAmount() <= currentHighest.getAmount()) {
					System.out.println("m");
				}
			}
		} else {
			return currentHighest;
		}
		return currentHighest;*/
		List<Bid> allBids = getBids();
		Bid highestBid;
		if(!allBids.isEmpty()) {
			highestBid = Collections.max(getBids(), Comparator.comparing(o -> o.getAmount()));
		} else {
			return null;
		}
		return highestBid;
	}

	/**
	 * 
	 * @return bids
	 */
	public List<Bid> getBids(){
		return this.bids;
	}

	public void verify() {
		this.status = Status.ACTIVE;
	}

	public void close() {
		this.status = Status.CLOSED;
		Bid bidWinner = getHighestBid();
		if(bidWinner != null) {
			this.buyer = bidWinner.getBuyer();
		}
	}
	
//	public void deleteAuction() {
//		
//	}

	/**
	 * 
	 * @return isBlocked
	 */
	public boolean isBlocked() {
		if (this.status.equals(Status.BLOCKED)) {
			return true;
		} else {
			return false;
		}
	}
	

	public void setBlocked() {
		this.status = Status.BLOCKED;
	}
	
	public void setUnblocked() {
		this.status = Status.ACTIVE;
	}
	
}
