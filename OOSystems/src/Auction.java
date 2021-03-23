import java.time.LocalDate;

public class Auction {

	private Seller seller;
	private Buyer buyer;
	private Item item;
	private double startPrice, reservePrice;
	private LocalDate closeDate;
	private char status;

	public Auction(Seller seller, Buyer buyer, double startPrice, double reservePrice, LocalDate closeDate,	char status){
		this.seller = seller;
		this.buyer = buyer;
		this.startPrice = startPrice;
		this.reservePrice = reservePrice;
		//Need to make sure that the close date is within 7 days and not in the past
		this.closeDate = closeDate;
		this.status = status;
	}

	//Get Functions
	public Seller getSeller(){
		return this.seller;
	}

	public Item getItem(){
		return this.item;
	}

	public void placeBid() {

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
