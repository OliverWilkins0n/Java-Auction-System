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
			    	break;
			    	
			    } case "3": { //Browse All Auctions
		  	    	System.out.println("All current Listings: ");
		  	    	List<Auction> allAuctions = getAllAuctions(); //Creates List of all auctions
		  	    	for (Auction i : allAuctions) {
		  	    		System.out.println(i.toString());  //Prints out all Auctions
		  	    	}	  	    	
		  	        break;
			    	
				} case "Q": {
					menuLoop = false;
					System.exit(0);
					break;
				}
			  }
			    
			    
	  		} else if (currentMenu == "SELLER") { //Seller Section
	  			System.out.println("====="+activeUser.getUsername()+"'s Sellers menu=======");
	  			choice = menu.sellerMenu();
	  		    
	  		    /* 1 - Browse Listings
	  		     * 2 - create auction
	  		     * 3 - verify auction
	  		     * 4 - sign out
	  		     * Q - Quit
	  		     */
	  			switch (choice) {
	  	      case "1" :{ // Browse Listings
	  	    	  System.out.println("All your current Listings: ");
	  	    	  List<Auction> usersAuctions = getAllAuctions(); //Creates List of all auctions
	  	    	  for (Auction i : usersAuctions) {	//Loops through all the auctions
	  	    		  if(i.getSeller().getUsername().equals(activeUser.getUsername())){ //Checks for auctions made from the user logged in
	  	    			  System.out.println(i.toString());
	  	    		  }
	  	    	  }
	  	    	  break;
	  	    	   
	  	      } case "2" :{ // Create auction
	  	    	  
	  	    	  //Getting all info for the Auction 
	  	    	 
	  	    	  System.out.println("Enter Item Name: ");
	  	    	  String itemDesc = S.nextLine();
	  	    	  System.out.println("Enter Starting Price: ");
	  	    	  double startPrice = S.nextDouble();
	  	    	  System.out.println("Enter Reserve Price: ");
	  	    	  double reservePrice = S.nextDouble();
	  	    	  String lkasdjlk = S.nextLine(); // Skips Next Input without this
	  	    	  System.out.println("Enter End Date dd-MM-yyyy HH:mm : ");
	  	    	  String closeDateString = S.nextLine();
	  	    	  //Formatting The Date
	  	    	  // Need to add checking that the date is within 7 days of the current date.
	  	    	  LocalDateTime closeDate;
	  	    	  try {
	  	    		  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		  	    	  closeDate = LocalDateTime.parse(closeDateString, formatter);
	  	    	  } catch (Exception e) {
	  	    		  System.out.println("Date is in the wrong format!");
	  	    		  break;
	  	    	  }
	  	    	  
	  	    	  try {
	  	    		  createAuction((Seller) activeUser, new Item(itemDesc), startPrice, reservePrice, closeDate);
	  	    		  serializeAuctions();
	  	    	  } catch (Exception e){
	  	    		  System.out.println("There was an error creating auction!");
	  	    		  break;
	  	    	  }
	  	    	  //formatting date to output in the right format
	  	    	  String formatedCloseDate = closeDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
	  	    	  System.out.println(formatedCloseDate);
	  	    	  //verifying auction
	  	    	  System.out.println("Item Desc: "+itemDesc+"   Start Price: "+startPrice+"   Reserve Price: "+reservePrice+"   Close Date: "+formatedCloseDate);
	  	    	  System.out.println("If you are happy with the above, please enter verify to verify your auction:");
	  	    	  String auctionVerify = S.nextLine();
	  	    
	  	    	  if (auctionVerify.toUpperCase().equals("VERIFY")) {
	  	    		  getAllAuctions().get(getAllAuctions().size() -1).verify();
	  	    		  System.out.println("Auction Succesfully Verified");
	  	    		  break;
	  	    	  } else {
	  	    	  break;
	  	    	  }
	  	    	  
	  	      } case "3" : { //Verify Auction
	  	    	  System.out.println("======= PENDING AUCTIONS ========");
	  	    	  for (Auction auc : getAllAuctions()) {
	  	    		  if (auc.getSeller().getUsername().equals(activeUser.getUsername())) {
	  	    			  if (auc.getStatus().equals(Status.PENDING)) {
	  	    				System.out.println(auc.getItem().getName());
	  	    			  }
	  	    		  }
	  	    	  }
	  	    	  System.out.println("Enter the name of Item to verify: ");
	  	    	  String choosenName = S.nextLine().toLowerCase();
	  	    	  boolean itemFound = false;

	  	    	  for (Auction auc : getAllAuctions()) {  //finds the item with the name and verifies it.
	  	    		  if(auc.getItem().getName().toLowerCase().equals(choosenName)){
	  	    			  System.out.println(auc.getItem().getName()+" has been verified");
	  	    			  auc.verify();
	  	    			  itemFound = true;
	  	    			  serializeAuctions();
	  	    			  break;
	  	    		  }
	  	    		  
	  	    	 
	  	    	 }
	  	    	  
	  	    	  if (!itemFound) {
	  	    		 System.out.println("Item could not be found! Please Check name is entered correctly!");
	  	    		  
	  	    	  }
	  	    	 
	  	    	  break;
	  	    	  
	  	      } case "4" : {// Sign Out
	  	    	  activeUser = null;
	  	    	  currentMenu = "start";
	  	    	  break;
	  	      } case "Q" :{
	  	    	menuLoop = false; 
	  	        System.exit(0);
	  	        
	  	        
	  	      }
	  		}
	  			
	  		} else if (currentMenu == "BUYER") {
	  			
	  		    /* 1 - Browse Auctions
	  		     * 2 - Place Bid
	  		     * 3 - Search for Item by name
	  		     * 4 - Sign out
	  		     * Q - Quit
	  		     * 
	  		     */
	  			System.out.println("======="+activeUser.getUsername()+"'s Buyer Menu========");
	  			choice = menu.buyerMenu();
	  		    switch (choice) {
	  	      case "1" :{ //Browse Auctions
	  	    	System.out.println("All current Listings: ");
	  	    	List<Auction> allAuctions = getAllAuctions(); //Creates List of all auctions
	  	    	for (Auction i : allAuctions) {
	  	    		System.out.println(i.toString());  //Prints out all Auctions
	  	    	}	  	    	
	  	        break;
	  	        
	  	      }
	  	      case "2" : {
	  	    	  break;
	  	        
	  	      }
	  	      case "3" :{ //Search For Item By Name
	  	    	  System.out.println("Enter Item name: ");
	  	    	  String selectedItem = S.nextLine();
	  	    	  List<Auction> allAuctions = getAllAuctions(); //Gets List of all Auctions
	  	    	  for (Auction i : allAuctions) {   // Loops through every auction in list
	  	    		  if (i.getItem().getName().equals(selectedItem)){ //Checks to see if the item desc is the same as one selected.
	  	    			  System.out.println(i.toString()); 
	  	    		  }
	  	    	  }
	  	    	  break;
	  	      }
	  	      case "4" :{
	  	    	activeUser = null;
	  	    	currentMenu = "start";
	  	    	break;
	  	      }
	  	      case "Q" :{
	  	    	
	  	        System.exit(0);
	  	      }
	  		
	  		}
	  			
	  		} else if (currentMenu == "ADMIN") {   
	  			
	  			/* 1 - Delete an Auction
	  		    2 - Delete a user
	  		    3 - List all Sellers
	  		    4 - Sign out
	  		    Q - Quit 
	  		    */
	  			
	  			
	  			System.out.println("======"+activeUser.getUsername()+"'s Admin Menu========");
                choice = menu.adminMenu();
                switch(choice) {
              case "1":{ //Delete an Auction 
                    break;

                }
              case "2" : { // Delete a user
                    break;
                  }
              case "3" :{ //List all Sellers
                      break;

            }
                  case "4" :{ //Sign out
                	  activeUser = null;
    	  	    	  currentMenu = "start";
    	  	    	  break;
            }
                  case "Q" :{
      	  	    	
      	  	        System.exit(0);
      	  	      }
        }
                break;
            }
        } while (menuLoop = true);
}
  
  
  
//--------SYS PART OF CONSOLE CLASS-------------------------------------------------------------------------------------------------------------------
  
  public List<Auction> getAllAuctions() {
	  return this.auctions;
  }
  
  public void createAuction(Seller seller, Item item, double startPrice, double reservePrice, LocalDateTime closeDate) throws Exception {
	auctions.add(new Auction(seller, item, startPrice, reservePrice, closeDate));
	//System.out.println(auctions);
}

  public void createAccount(String username, String password, String accountType) throws Exception{
      if (accountType.equals("S")){
          users.add(new Seller(username, password));
          serialize(); //Saves the newly created account
      } else if (accountType.equals("B")) {
          users.add(new Buyer(username, password));
          serialize(); //Saves the new created account 
      } else if (accountType.equals("A")) {
          users.add(new Admin(username, password));
          serialize();
      }
  }
  
  public void placeAuction(Seller seller, Item item, double startPrice, double reservePrice, LocalDateTime closeDate) throws Exception{
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
