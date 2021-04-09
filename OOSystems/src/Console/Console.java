package Console;

import AuctionSystem.*; // Import everything from AuctionSystem package
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public final class Console{
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
  private List<User> users = new LinkedList<User>();
  
  User activeUser = null; // The user that is logged in.
  public Console(){
    System.out.println("");
  }

  public void auctionSetup() throws Exception{
	  	deserialize();
	  	deserializeAuctions();
	  	do {
	  		if (currentMenu == "start") {
			    choice = menu.startMenu();
			    switch(choice) {
			    case "1": {//User has selected Create Account
			    	
			    	//System.out.println("start" +getAllUsers()); testing.
			    	System.out.println("Username: ");
			    	String username = S.nextLine();
			    	
			    	System.out.println("Password: ");
			    	String password = S.nextLine();
			    	
			    	System.out.println("Account Type? [B]uyer / [S]eller");
			    	String accountType = S.nextLine().toUpperCase();
			    	
			    	//Checks to make sure an correct account Type is entered
			    	//Not working atm for some reason
			    	/*
			    	while(accountType != "S" || accountType != "B" || accountType == "A") {
			    		System.out.println("Incorrect Account Type!");
			    		System.out.println("Please Enter Again: ");
			    		accountType = S.nextLine().toUpperCase();
			    	} */
			    	
			    	createAccount(username, password, accountType);
			    	System.out.println("Account has been Created.");
			    	
			    	activeUser = findUser(username);  //Logs in after account has been created.
			    	if (accountType == "S") {    //Goes to the correct menu after creating an account
			    		currentMenu = "SELLER";
			    	} else if (accountType == "B") {
			    		currentMenu = "BUYER";
			    	} else if (accountType == "A") {
			    		currentMenu = "ADMIN";
			    	}
			    	System.out.println(getAllUsers());
			    	break;
			    	
			    	
			    } case "2": { //log in menu
			    	
			    	System.out.println("Enter Username: ");
			    	String username = S.nextLine();
			    	
			    	if(findUser(username) == null) { //Searches to see if the username is valid.
			    		System.out.println("User does not exist.");
			    		break;
			    	}
			    	System.out.println("Enter password: ");
			    	String password = S.nextLine();
			    	
			    	if(findUser(username).validatePassword(password)) { //validates the password
			    		activeUser = findUser(username);
			    		
			    		if (activeUser instanceof Buyer) {
			    			currentMenu = "BUYER";
			    		} else if(activeUser instanceof Seller) {
			    			currentMenu = "SELLER";
			    		} else if (activeUser instanceof Admin) {// still need to sort out the admin part.
			    			currentMenu = "ADMIN";
			    		}
			    	} else {
			    		System.out.print("Password Entered is incorrect!");
			    	}
			    	
			    	
				} case "Q": {
					menuLoop = false;
				}
			  }
			    
			    
	  		} else if (currentMenu == "SELLER") { //Seller Section
	  			System.out.println("====="+activeUser.getUsername()+"'s Sellers menu=======");
	  			choice = menu.sellerMenu();
	  		    
	  		    /* 1 - Browse Listings
	  		     * 2 - Search for item by name
	  		     * 3 - create auction
	  		     * 4 - verify auction
	  		     * 5 - sign out
	  		     * Q - Quit
	  		     */
	  			switch (choice) {
	  	      case "1" :{ // Browse Listings
	  	        
	  	      } case "2" : { // Search For Item
	  	        
	  	      } case "3" :{ // Create auction
	  	    	  
	  	    	  //Getting all info for the Auction 
	  	    	  //System.out.println("Enter Item Name: ");
	  	    	  //String itemName = S.nextLine();
	  	    	  System.out.println("Enter Item Description: ");
	  	    	  String itemDesc = S.nextLine();
	  	    	  System.out.println("Enter Starting Price: ");
	  	    	  double startPrice = S.nextDouble();
	  	    	  System.out.println("Enter Reserve Price: ");
	  	    	  double reservePrice = S.nextDouble();
	  	    	  String lkasdjlk = S.nextLine(); // Skips Next Input without this
	  	    	  System.out.println("Enter End Date dd-MM-yyyy HH:mm : ");
	  	    	  String closeDate = S.nextLine();
	  	    	  //Formatting The Date
	  	    	  // Need to add checking that the date is within 7 days of the current date.
	  	    	  LocalDateTime dateTime;
	  	    	  try {
		  	    	  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		  	    	  dateTime = LocalDateTime.parse(closeDate, formatter);
	  	    	  } catch (Exception e) {
	  	    		  System.out.println("Date is in the wrong format!");
	  	    		  break;
	  	    	  }
	  	    	  
	  	    	  try {
	  	    		  createAuction((Seller) activeUser, new Item(itemDesc), startPrice, reservePrice, dateTime);
	  	    		  serializeAuctions();
	  	    	  } catch (Exception e){
	  	    		  System.out.println("There was an error creating auction!");
	  	    	  }
	  	      
	  	    	  //System.out.println(getAllAuctions());
	  	    	  
	  	    	  
	  	        
	  	      } case "4" : { //Verify Auction
	  	    	  
	  	      } case "5" : {// Sign Out
	  	    	  activeUser = null;
	  	    	  currentMenu = "start";
	  	      } case "Q" :{
	  	    	menuLoop = false; 
	  	        System.exit(0);
	  	        
	  	        
	  	      }
	  		}
	  			
	  		} else if (currentMenu == "BUYER") {
	  			
	  		} else if (currentMenu == "ADMIN") {   
	  			
	  		}
	  	} while (menuLoop = true);
  }
  
  
  
//--------SYS PART OF CONSOLE CLASS-------------------------------------------------------------------------------------------------------------------
  
  public List<Auction> getAllAuctions(){
	  return this.auctions;
  }
  
  public void createAuction(Seller seller, Item item, double startPrice, double reservePrice, LocalDateTime closeDate) {
	auctions.add(new Auction(seller, item, startPrice, reservePrice, closeDate));
	System.out.println(auctions);
}

  public void createAccount(String username, String password, String accountType) throws Exception{
	  if (accountType.equals("S")){
		  users.add(new Seller(username, password));
		  serialize(); //Saves the newly created account
	  } else if (accountType.equals("B")) {
		  users.add(new Buyer(username, password));
		  serialize(); //Saves the new created account 
	  }
  }
  
  public void placeAuction(Seller seller, Item item, double startPrice, double reservePrice, LocalDateTime closeDate){
	  auctions.add(new Auction(seller, item, startPrice, reservePrice, closeDate));
	  }

  public List<Auction> browseAuctions(){
	  return this.auctions;
	  }
	  
  public List<User> getAllUsers(){
	 /* for (User user : users) {
		  System.out.println(user.getUsername()); TESTING
	  } */ 
		  return this.users;
	  }
  
  public void deserialize() { //deserialize users
	  ObjectInputStream ois;
	  
	  try {
		  ois = new ObjectInputStream(new FileInputStream("usersSave.ser"));
		  users = (List<User>)ois.readObject(); //Loads all objects from SER file into linked list
		 // auctions = (List<Auction>)ois.readObject();
		  ois.close();
		 // System.out.println("ois test "+ getAllUsers());
	  } catch (Exception e) {
		  System.out.println(e.getMessage());
	  }
  }
  
  public void serialize() { //serialize Users
	  ObjectOutputStream oos;
	  
	  try {
		  oos = new ObjectOutputStream(new FileOutputStream("usersSave.ser"));
		  //System.out.println("oos test "+ getAllUsers());
		  oos.writeObject(getAllUsers()); //Writes all objects to SER file
		  //oos.writeObject(getAllAuctions());
		  oos.close();
	  } catch (Exception e) {
		  System.out.println("Exception");
		  System.out.println(e.getMessage());
	  }
  }
  
  public void serializeAuctions() { // Serialize Auctions
	  ObjectOutputStream oos;
	  try {
		  oos = new ObjectOutputStream(new FileOutputStream("Auctions.ser"));
		  oos.writeObject(getAllAuctions());
		  oos.close();
		  
	  } catch (Exception e) {
		  System.out.println(e.getMessage());
	  }
  }
  
  public void deserializeAuctions() { //deserialize Auctions
	  ObjectInputStream ois;
	  
	  try {
		  ois = new ObjectInputStream(new FileInputStream("Auctions.ser"));
		  auctions = (List<Auction>)ois.readObject(); //Loads all objects from SER file into linked list
		  ois.close();
	  } catch (Exception e) {
		  System.out.println(e.getMessage());
	  }
  }

  
  public User findUser(String username) {
	  for(User i : users) {
		  if(i.getUsername().equals(username)){
			  return i;
		  }
	  }
	  return null;
  }
  
}
