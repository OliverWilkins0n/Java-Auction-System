import javax.swing.JOptionPane;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;

public class sys {
	//private List<User> users = new LinkedList<User>();
	private final static Scanner S = new Scanner(System.in);

	public static void main(String[] args) {

		String choice = "";
		String filepath = "database.txt";

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

	}


	public static void placeAuction() {

	}

	public static void browseAuction() {

	}

	public static void setupAccount(String filepath) {
		System.out.print("Please enter a username: ");
		String username = S.next();
		System.out.print("Please enter a password: ");
		String password = S.next();
		try {
			FileWriter fw = new FileWriter(filepath,true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);

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
