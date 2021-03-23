import java.time.LocalDate;

public class Auction {

	private Seller seller;
	private Buyer buyer;
	private double startPrice, reservePrice;
	private LocalDate closeDate;
	private char status;

	public Auction(double startPrice, double reservePrice, LocalDate closeDate,	char status){
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
