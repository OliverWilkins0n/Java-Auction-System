import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class Auction {


	private Seller seller;
	private Buyer buyer;
	private Item item;
	private double startPrice, reservePrice;
	private LocalDate closeDate;
	private char status;
	private List<Bid> bidList = new ArrayList<Bid>();


	public Auction(Seller seller, double startPrice, double reservePrice, LocalDate closeDate){
		this.seller = seller;
		this.startPrice = startPrice;
		this.reservePrice = reservePrice;
		//Need to make sure that the close date is within 7 days and not in the past
		this.closeDate = closeDate;
	//	this.status = status;
	}

	//Get Functions
	public Seller getSeller(){
		return this.seller;
	}

	public Item getItem(){
		return this.item;
	}

//	public void placeBid(double amount, Buyer buyer) {
//		bidList.add(new Bid(amount, buyer, LocalDateTime.now()));
//	}

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
