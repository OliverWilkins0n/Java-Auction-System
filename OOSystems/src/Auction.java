import java.time.LocalDate;

public class Auction {

	private Seller seller;
	private Buyer buyer;
	private double startPrice, reservePrice;
	private LocalDate closeDate;
	private char status;

	public Auction(Seller seller, Buyer buyer, double startPrice, double reservePrice, LocalDate closeDate,	char status){
		this.seller = seller;
		this.buyer = buyer;
		this.startPrice = startPrice;
		this.reservePrice = reservePrice;
		this.closeDate = closeDate;
		this.status = status;
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
