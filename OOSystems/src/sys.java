import java.util.Scanner;

public class sys {
	private final static Scanner S = new Scanner(System.in);

	public static void main(String[] args) {
		
		String choice = "";
		
		do {
			System.out.println("==== Main Menu ====");
			System.out.println("1 - Place an Auction");
			System.out.println("2 - Browse Auctions");
			System.out.println("3 - Setup Account");
			System.out.println("Q - Quit");
			System.out.println(":");
			
			choice = S.nextLine().toUpperCase();
			
			switch (choice) {
				case "1" : {
					placeAuction();
				}
				case "2" : {
					browseAuction();
				}
				case "3" : {
					setupAccount();
				}
			}
			
		} while(!choice.equals("Q"));

	}

	
	public void placeAuction() {
		
	}
	
	public void browseAuction() {
		
	}
	
	public void setupAccount() {
		
	}
	
}
