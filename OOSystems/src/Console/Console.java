package Console;

import AuctionSystem.*; // Import everything from AuctionSystem package
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class Console{
  //Start up
  Scanner S = new Scanner(System.in);
 // Sys system = new Sys();
  Menu menu = new Menu();
  String choice;
  boolean menuLoop = true; //Loops menu until Quit is chosen on the home page
  String currentMenu = "start";
  
  //Lists for Users and Auctions.
  private List<Auction> auctions = new LinkedList<Auction>();
  private Auction auction;
  public List<User> users = new LinkedList<User>();
  private User user;
  public Console(){
    System.out.println("");
  }

  public void auctionSetup() throws Exception{
	  	deserialize();
	  	do {
	    choice = menu.startMenu();
	    switch(choice) {
	    case "1": { //User has selected Create Account
	    	System.out.println("Username: ");
	    	String username = S.nextLine();
	    	
	    	System.out.println("Password: ");
	    	String password = S.nextLine();
	    	
	    	System.out.println("Account Type? [B]uyer / [S]eller");
	    	String accountType = S.nextLine().toUpperCase();
	    	
	    	createAccount(username, password, accountType);
	    	System.out.println("Account has been Created.");
	    	System.out.println(getAllUsers());
		} case "Q": {
			menuLoop = false;
		}
	    }
  } while (menuLoop = true);
  }
  
  
  
//--------SYS PART OF CONSOLE CLASS------------------------------------
  
  public void createAccount(String username, String password, String accountType) throws Exception{
	  if (accountType.equals("S")){
		  users.add(new Seller(username, password));
	  } else if (accountType.equals("B")) {
		  users.add(new Buyer(username, password));
	  }
  }
  
  public void placeAuction(Seller seller, Item item, double startPrice, double reservePrice, LocalDateTime closeDate){
	  auctions.add(new Auction(seller, item, startPrice, reservePrice, closeDate));
	  }

  public List<Auction> browseAuctions(){
	  return this.auctions;
	  }
	  
  public List<User> getAllUsers(){
	  for (User user : users) {
		  System.out.println(user.getUsername());
	  }
		  return this.users;
	  }
  
  public void deserialize() {
	  ObjectInputStream ois;
	  
	  try {
		  ois = new ObjectInputStream(new FileInputStream("usersSave.ser"));
		  users = (List<User>)ois.readObject();
		  ois.close();
		  System.out.println("ois test "+ getAllUsers());
	  } catch (Exception e) {
		  System.out.println(e.getMessage());
	  }
  }
  
  public void serialize() {
	  ObjectOutputStream oos;
	  
	  try {
		  oos = new ObjectOutputStream(new FileOutputStream("usersSave.ser"));
		  System.out.println("oos test "+ getAllUsers());
		  oos.writeObject(getAllUsers()); //Writes all objects to SER file
		  oos.close();
		  System.out.println("Succesfully Saved Data, I am a true lier.");
	  } catch (Exception e) {
		  System.out.println(e.getMessage());
	  }
  }

}
