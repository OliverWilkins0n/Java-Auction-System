package AuctionSystem;

import java.time.LocalDateTime;
import java.util.List;

public class BidCheck implements Runnable {
	private List<Bid> bids;
	private Integer delay;
	
	public BidCheck(List<Bid> bids, Integer seconds) {
		this.bids = bids;
		setSeconds(seconds);
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(delay);
				
				synchronized (bids) {
					for (Bid bid : bids) {
						if (!bid.isWinnerNotified()) {
							if (bid.getCloseDateTime().minusSeconds(1).isBefore(LocalDateTime.now())) {
								bid.setIsWinnerNotified(true);
								bid.setWon();
								System.err.print("setIsWinnerNotified " + bid.getCurrentMaxBid() + " :)\n");
							}
						}
					}
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public synchronized void setSeconds(Integer seconds) {
		delay = seconds * 1000;
	}
	
	public Integer getSeconds() {
		return delay / 1000;
	}

}
