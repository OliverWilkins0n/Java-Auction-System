import javax.swing.JOptionPane;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;

public class sys {
	private final static Scanner S = new Scanner(System.in);

	public static void main(String[] args) {

		String choice = "";
		String filepath = "database.txt";
		String filepath_auction = "auction_database.txt";

		do {
			//Doesnt a user need to login to place an auction?
			System.out.println("==== Main Menu ====");
			System.out.println("1 - [P]lace an Auction");
			System.out.println("2 - [B]rowse Auctions");
			System.out.println("3 - [S]etup Account");
		//	System.out.println("4 - [L]og in");
			System.out.println("Q - Quit");
			System.out.println("Input: ");

			choice = S.nextLine().toUpperCase();

			switch (choice) {
				case "1" :
				case "P" :{
					placeAuction(filepath_auction);
					break;
				}
				case "2" :
				case "B" : {
					browseAuction();
					break;
				}
				case "3" :
				case "S" : {
					setupAccount(filepath);
					break;
				}
		/*		case "4" :
				case "L" : {
					logIn();
					break;
				}*/
			}

		} while(!choice.equals("Q"));
		//close Scanner
		S.close();
		System.out.println("Goodbye :)");
	}

	public static void placeAuction(String filepath_auction) {
		//just add to it, I can't think off anymore right now
		System.out.print("Please enter a item name: ");
		String itemName = S.next();
		System.out.print("Please enter a description: ");
		String description = S.next();
		System.out.print("Please enter a start price: ");
		Double startPrice = S.nextDouble();
		System.out.print("Please enter a reserve price: ");
		Double reservePrice = S.nextDouble();
		try {
			FileWriter fw2 = new FileWriter(filepath_auction,true);
			BufferedWriter bw2 = new BufferedWriter(fw2);
			PrintWriter pw2 = new PrintWriter(bw2);
			//writing username and password into new line of our database
			pw2.println(itemName+","+description+","+startPrice+","+reservePrice);
			pw2.flush();
			pw2.close();

			JOptionPane.showMessageDialog(null, "Auction successfully created!");
		}
		catch (Exception E)
		{
			JOptionPane.showMessageDialog(null, "Auction could not be created, please try again!");
		}

	}

	public static void browseAuction() {

	}

	public static void setupAccount(String filepath) {
		//need to add to check if username already exists
		System.out.print("Please enter a username: ");
		String username = S.next();
		//We need to add/link our password verification
		System.out.print("Please enter a password: ");
		String password = S.next();
		try {
			FileWriter fw = new FileWriter(filepath,true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			//writing username and password into new line of our database
			pw.println(username+","+password);
			pw.flush();
			pw.close();

			JOptionPane.showMessageDialog(null, "Account successfully created!");
		}
		catch (Exception E)
		{
			JOptionPane.showMessageDialog(null, "Account not created, please try again!");
		}

	}

	/* public static void logIn(){

	} */

//	public boolean authenticateUser(int)

}
