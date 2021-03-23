import java.util.Scanner;

public class sys {
	private final static Scanner S = new Scanner(System.in);

	public static void main(String[] args) {

		String choice = "";

		do {
			System.out.println("==== Main Menu ====");
			System.out.println("1 - [P]lace an Auction");
			System.out.println("2 - [B]rowse Auctions");
			System.out.println("3 - [S]etup Account");
			System.out.println("Q - Quit");
			System.out.println("Input: ");

			choice = S.nextLine().toUpperCase();

			switch (choice) {
				case "1" :
				case "P" :{
					placeAuction();
					break;
				}
				case "2" :
				case "B" : {
					browseAuction();
					break;
				}
				case "3" :
				case "S" : {
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

//	public boolean authenticateUser(int)

}
