package AuctionSystem;

import java.time.LocalDateTime;
import java.util.List;

import Console.Console;



public class AuctionCheck implements Runnable{
//	private List<Auction>;
	private Console console;
	private Integer delay;
	private List<Auction> auctions;
	private volatile boolean exit = false;
	
	/**
	 * Thread Constructor
	 * @param auctions
	 * @param seconds
	 */
	
	public AuctionCheck(List<Auction> auctions, Integer seconds) {
		this.auctions = auctions;
		this.delay = 1000;
	} 
	
	@Override
	public void run() {
		//System.out.println("got to here :)");
		//this.auctions = console.getAllAuctions();
			while(true) {
				synchronized(auctions) {
				//this.auctions = console.getAllAuctions();
				if(!auctions.isEmpty()) {
					for(Auction i : auctions) {
					//	if(i.getStatus().equals(Status.CLOSED)) {
					//		break;
					//	}
						if(i.getCloseDate().isBefore(LocalDateTime.now()) && i.getStatus().equals(Status.ACTIVE)) {
							i.close();
							//i.getHighestBid().notifyWinner(i.getHighestBid().getBuyer(), i.getItem());
							i.getHighestBid().notifyWinner(i.getBuyer(), i.getItem());
							try {
								Thread.sleep(4000);
							} catch (Exception e) {
								System.out.println("Thead Sleep error.");
							}
						}
					}
				}
			}
		}
	}
	public void stop() {
		exit = true;
		
	}
	public void start() {
		auctions = console.getAllAuctions();
		exit = false;
	}
}
	
//	public synchronized void setSeconds(Integer seconds) {
//		this.delay = seconds * 1000;
//	}
//	public void setDelay(seconds) {
//		this.delay = seconds
//	}



