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
			System.out.println("Input: ");

			choice = S.nextLine().toUpperCase();

			switch (choice) {
				case "1" : {
					placeAuction();
					break;
				}
				case "2" : {
					browseAuction();
					break;
				}
				case "3" : {
					setupAccount();
					break;
				}
			}

		} while(!choice.equals("Q"));

	}


	public static void placeAuction() {

	}

	public static void browseAuction() {

	}

	public static void setupAccount() {

	}

}
