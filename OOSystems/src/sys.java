import java.util.LinkedList;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.JOptionPane;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class sys {
	private final static Scanner S = new Scanner(System.in);

	//This of for adding the composition with the class Auction.

	private static List<Auction> auctions = new LinkedList<Auction>();
	//The next bit of this code to follow is link 49


/*
	public final List<Auction> auctions;
	sys(List<Auction> auctions){
		this.auctions = auctions;
	}
	*/



	public static void main(String[] args) {

		String choice = "";
		String filepath = "database.txt";
		String filepath_auction = "auction_database.txt";
		String line = "";
		String splitBy = ",";

		//READ ME!!!!!!!
		// Basically get an error for everywhere it has <Auction> saying
		// That the symbol cannot be found, Been stuck on it for ages ngl.

		//Loading the CSV files into arrays. 125 in auction
	//	List<Auction> auctions = new ArrayList<Auction>();
		List<Auction> auctions = readAuctionsFromCSV(filepath_auction);

		System.out.println("test: "+auctions);
		//

		do {
			//Doesn't a user need to login to place an auction?
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
					browseAuction(filepath_auction,line,splitBy);
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

	public static List<Auction> readAuctionsFromCSV(String filepath_auction){
		List<Auction> auctions = new ArrayList<>();
		Path filePath = Paths.get(filepath_auction);

		try (BufferedReader br = Files.newBufferedReader(filePath)){
			String line = br.readLine();
			while(line != null){
				//Splits the line at each , to separate the attributes
				String[] attributes = line.split(",");
				System.out.println(attributes);
			//	Auction auction = createAuction(attributes);
			//	auctions.add(auction);
			//	line = br.readLine();
			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return auctions;
	}

//	private static Auction createAuction(String[] metadata) {
//		Seller seller = metadata[0];
//
//
//	}

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
		//add local time and cancel after 7 days or whatever it was
		try {
			FileWriter fw2 = new FileWriter(filepath_auction,true);
			BufferedWriter bw2 = new BufferedWriter(fw2);
			PrintWriter pw2 = new PrintWriter(bw2);
			//writing  specified objetcs on a new line into our database
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

	public static void browseAuction(String filepath_auction, String line,  String splitBy) {
		//reader to read already existing auctions from datatbase
//		private int counter = 0;
			try {
				//	System.out.println("==== Active Auctions ====");
					BufferedReader br = new BufferedReader(new FileReader(filepath_auction));
					while ((line = br.readLine()) != null) // return boolean value

					{

						//converting lines from database into an array to output nicely
					//	Auction auctions[counter] = new Auction(auctionOutput[0], auctionOutput[1], auctionOutput[2], auctionOutput[3])

						String[] auctionOutput = line.split(splitBy);
					//	System.out.println("Item name=" + auctionOutput[0] + ", Description=" + auctionOutput[1] + ", Start Price = £" + auctionOutput[2] + ", Reserve Price= £" + auctionOutput[3]);

					}
					br.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}


	}



	public static void setupAccount(String filepath) {
		//need to add to check if username already exists
		System.out.print("Please enter a username: ");
		String username = S.next();
		//We need to add/link our password verification
		System.out.print("Please enter a password: ");
		String password = S.next();

		System.out.print("Account type? [S]eller or [B]uyer: ");
		String accountType = S.next().toUpperCase();
		while((accountType != "S") & (accountType != "B")) {
			if (accountType.toUpperCase().charAt(0) == 'S') {
					accountType = "Seller";
					//The code to create a seller.
			} else if (accountType.toUpperCase().charAt(0) == 'B') {
						accountType = "Buyer";
						//The code to create a buyer
					//	User buyer = Buyer(username, password);
			} else {
					System.out.print("Invalid account type! Please try again [S]eller or [B]uyer: ");
					accountType = S.next().toUpperCase();
			}
		}

		//We need to add/link our password verification
		try {
			FileWriter fw = new FileWriter(filepath,true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			//writing username and password into new line of our database

			// List<User> buyerSellers = new List<users>();
		//	pw.println(username+","+password+","+accountType);
		//	pw.println(buyer.username, buyer.password. accountType);
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
