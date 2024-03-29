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
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public final class Console{
  //Start up
  Scanner S = new Scanner(System.in);
 // Sys system = new Sys();
  Menu menu = new Menu();
  //Console console = new Console();
  String choice;
  boolean menuLoop = true; //Loops menu until Quit is chosen on the home page
  String currentMenu = "start";
  
  //Lists for Users and Auctions.
  public List<Auction> auctions = Collections.synchronizedList(new LinkedList<Auction>());
  //private Auction auction;
  private List<User> users = new LinkedList<User>();
  //private User user;
  //Thread thread = new Thread(new AuctionCheck(auctions, 1));/*Creates the thread, to check for when
  															//	an auction ends*/
  boolean firstTimeStart = true;
  
  User activeUser = null; // The user that is logged in.
  
  public Console() throws Exception{

  }
  
  

  public void auctionSetup() throws Exception{
	  	
		  if (firstTimeStart) {
	  
	  		deserialize();
	  		deserializeAuctions();	

	          //Creating Sample Admin
	          createAccount("admin", "_Admin", "A");

	          //Creating Sample Sellers
	          createAccount("sorren", "_Sorren", "S");
	          createAccount("glyn", "_Glyn", "S");

	          //Creating Sample Buyers
	          createAccount("mark", "_Mark", "B");
	          createAccount("kirsty", "_Kirsty", "B");
	          createAccount("andy", "_Andy", "B");

	          //Creating Sample Auctions
	          createAuction((Seller) findUser("sorren"), new Item("iPad"), 200, 300, LocalDateTime.now().plusMinutes(1));
	          createAuction((Seller) findUser("glyn"), new Item("Bike"), 80, 120, LocalDateTime.now().plusMinutes(1));
	          createAuction((Seller) findUser("glyn"), new Item("Rocket Ship"), 80, 120, LocalDateTime.now().plusMinutes(1));
	          //placeAuction((Seller) findUser("andy"), new Item("VR Headset"), 180, 270, LocalDateTime.now().plusMinutes(2));

	          //Verify auctions
	         // String sampleAuction1 = "iPad";
	          //String sampleAuction2 = "Bike";
	          //String sampleAuction3 = "VR Headset";
	          int aucLength = getAllAuctions().size();
	          
	         // System.out.println(aucLength = getAllAuctions().size());
	          
	          getAllAuctions().get(aucLength-1).verify(); //Verifies the auctions
	          getAllAuctions().get(aucLength-2).verify();
	          getAllAuctions().get(aucLength-3).verify();
	          
	          getAllAuctions().get(aucLength-1).placeBid(1000, (Buyer) findUser("mark")); //Places the bids for setup
	          getAllAuctions().get(aucLength-1).placeBid(2400, (Buyer) findUser("kirsty"));
	          getAllAuctions().get(aucLength-2).placeBid(200, (Buyer) findUser("andy"));

	         /* for (Auction auc : getAllAuctions()) {  //finds the item with the name and verifies it.
	               if(auc.getItem().getName().toLowerCase().equals(sampleAuction1)){
	                   auc.verify();
	                  if(auc.getItem().getName().toLowerCase().equals(sampleAuction2)){
	                        auc.verify();
	                       if(auc.getItem().getName().toLowerCase().equals(sampleAuction3)){
	                             auc.verify();
	               }
	                  }
	               }
	          } */
	               



	          firstTimeStart = false;
	   //       serialize();        
	    //      serializeAuctions();     
	          
		  }
	        
	  	
	    Thread thread = new Thread(new AuctionCheck(auctions, 1)); // Creates the multithreading thread
	  	thread.start(); // Starts the thread
  
	  	
	  	do {
	  		if (currentMenu == "start") {
			    choice = menu.startMenu();
			    switch(choice) {
			    case "1": {//User has selected Create Account
			    	
			    	//System.out.println("start" +getAllUsers()); testing.
			    	System.out.println("Username: ");
			    	String username = S.nextLine().toLowerCase(); //needs to be lowercase otherwise blocking function has error

			    	if (users.contains(findUser(username))) {
			    		System.out.println("Username is already taken, please choose another username."); //check if username is already taken, if so returns error
			    		break;
			    	} else {
			    	
			    	
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
			    	//serialize();
			    	
			    	
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
			   
			    	}
			    } case "2": { //log in menu
			    	   	
			    	System.out.println("Enter Username: ");
			    	String username = S.nextLine().toLowerCase();
			    	
			    	//stop user from logging in if he's blocked
			    	//if (findUser(username).isBlocked()) { //check if user is blocked
	  	    			//System.out.println("You cannot log in as your account is blocked.");
	  	    			//break;
	  	    			//} else {
	  	    				  
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
		  	    		if(i.getStatus().equals(Status.ACTIVE))
		  	    			System.out.println(i.toString());  //Prints out all Auctions
		  	    	}	  	    	
		  	        break;
			    	
				} case "Q": {
					menuLoop = false;
					thread.stop();
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
	  	    	  String itemDesc = S.nextLine().toLowerCase(); //needs to be lowercase otherwise blocking function has error
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
	  	    		 // thread.stop();
	  	    		 // thread.stop();
	  	    		  createAuction((Seller) activeUser, new Item(itemDesc), startPrice, reservePrice, closeDate);
	  	    		  serializeAuctions();
	  	    		 // thread.start();
	  	    	  } catch (Exception e){
	  	    		  System.out.println("There was an error creating auction!");
	  	    		  break;
	  	    	  }
	  	    	 // thread.start();
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
	  	    		  serializeAuctions();
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

	  	    	  
	  	    	  //add date formatation and make sure status is changed to ACTIVE
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
	  	    	thread.stop();
	  	        System.exit(0);        
	  	        
	  	      }
	  		}
	  			
	  		} else if (currentMenu == "BUYER") {
	  			
	  		    /* 1 - Browse Auctions
	  		     * 2 - Place Bid
	  		     * 3 - Search for Item by name
	  		     * 4 - See Current Bids on Item
	  		     * 5 - Sign out
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
	  	      case "2" : {// Place Bid
	  	    	  System.out.println("Enter Item Name: ");
	  	    	  String bidName = S.nextLine();
	  	    	  System.out.println("Enter Bid: ");
	  	    	  double bidPrice = S.nextDouble();
	  	    	  String weirdFix = S.nextLine();
	  	    	  
	  	    	  for (Auction i : auctions) {
	  	    		  if(i.getItem().getName().toLowerCase().equals(bidName.toLowerCase())) {
	  	    			  i.placeBid(bidPrice, (Buyer) activeUser);
	  	    			  serializeAuctions();
	  	    		  }
	  	    	  }
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
	  	      } case "4" : {// Seen Current Bids On Item
	  	    	  //List<Auction> allAuctions = getAllAuctions();
	  	    	  //Bid highestBid = null;
	  	    	  
	  	    	  System.out.println("Enter Item name: ");
	  	    	  String itemName = S.nextLine().toLowerCase();
	  	    	  System.out.println("====Bidding History====");
	  	    	  for(Auction i : auctions) {
	  	    		  if(i.getItem().getName().toLowerCase().equals(itemName)) {
	  	    			  System.out.println(i.getBids().toString());
	  	    			 // highestBid = i.getHighestBid();	  	    				  
	  	    			  }
	  	    		  }
	  	    	  }
	  	    	 // System.out.println("====Current Highest Bid=====");
	  	    	  //System.out.println(highestBid.)
	  	    	  break;
	  	    	  
	  	      
	  	      case "5" :{
	  	    	activeUser = null;
	  	    	currentMenu = "start";
	  	    	break;
	  	      }
	  	      case "Q" :{
	  	    	thread.stop();
	  	        System.exit(0);
	  	        break;
	  	      }
	  		
	  		}
	  			
	  		} else if (currentMenu == "ADMIN") {   
	  		
	  			/* 1 - Delete an Auction
	  		    2 - Delete a user
	  		    3 - List all Sellers
	  		    4 - List all Buyers
	  		    5 - Block/Unblock User
	  		    6 - Block Auction
	  		    7 - Sign out
	  		    Q - Quit 
	  		    */
	  			
	  			
	  			System.out.println("======"+activeUser.getUsername()+"'s Admin Menu========"); 
                choice = menu.adminMenu();
                switch(choice) {
              case "1":{ //Delete an Auction
    		//	  thread.stop();
  	    	//	  thread.interrupt();
            	  List<Auction> allAuctions = getAllAuctions();
            	  for (Auction i : allAuctions) {   // Loops through every auction in list
	  	    			  System.out.println(i.toString()); 	  	    			 
	  	    	  }
            	  System.out.println("Enter Item Name to Be Deleted: ");
            	  String itemChoice = S.nextLine().toLowerCase();
            	  
            	  for (Auction i : auctions) {
            		  if(i.getItem().getName().toLowerCase().equals(itemChoice)) { //Finds the auction choosen from the list
            			  auctions.remove(i); //removes the auction
            			  System.out.println("Auction Succesfully Removed");
            			  serializeAuctions(); //Updates the save
            			  break;
            	  }

                }
            	//  thread.run();
            	  break;
              }
              case "2" : { // Delete a user
            	  System.out.println("All Users: ");
            	  List<User> allUsers = getAllUsers();
                  for (User u : allUsers) {  // Loops through every user in list
	  	    			  System.out.println(u.getUsername()); 
                  }
            	  System.out.println("Enter the username of the user that you want to delete: ");
                  String userDelete = S.nextLine();
                      for(User i : users) {
                          if(i.getUsername().toLowerCase().equals(userDelete)) {
                          users.remove(i); //removes user
                          System.out.println("User deleted succesfully!");
                          serialize(); //Updates the save
                          break;
                  }
                      }
                 
            	  break;
                  }
              case "3" :{ //List all Sellers
            	  
            	  List<User> allUsers = getAllUsers();
                  for (User u : allUsers) {
                      if(u instanceof Seller) {
                          System.out.println(u.getUsername());
                      }
                  }
                      break;

            } case "4" :{ //List all Buyers
            	List<User> allUsers = getAllUsers();
            	for(User u : allUsers) {
            		if(u instanceof Buyer) {
            			System.out.println(u.getUsername());
            		}
            	}
            	break;
            } case "5" :{ //Block and unblock User
            	System.out.println("Current Users and their Status");
            	List<User> allUsers = getAllUsers();
            	for(User u : allUsers) {
            			System.out.println(u.getUsername() +" | "+ u.getStatus());  //outputs current users + their current status
            	}
                System.out.println("Do you want to [B]lock or [U]nblock a user: ");
                String blockOrUnblock = S.nextLine().toUpperCase();
                if (blockOrUnblock.equals("B")) {
                System.out.println("Enter the username of the user that you want to block: ");
                String userBlock = S.nextLine().toLowerCase();
                            findUser(userBlock).setBlocked(); //set status
                            System.out.println("User blocked successfully.");
                            serialize(); //save status
                            break;
                } else if (blockOrUnblock.equals("U")) {

                    System.out.println("Enter the username of the user that you want to unblock: ");
                    String userUnblock = S.nextLine().toLowerCase();
                                findUser(userUnblock).setUnblocked(); //set status
                                System.out.println("User unblocked successfully.");
                                serialize(); //save status
                               break;
               } else {
                 break;
                }
           }
            case "6" : { //Block Auction
            	System.out.println("Current Auctions and their Status");
            	List<Auction> allAuctions = getAllAuctions();
            	for(Auction a : allAuctions) {
            			System.out.println(a.getItem().getName() +" | "+ a.getStatus());	//outputs current auctions + their current status
            	}
            	
            	
                System.out.println("Do you want to [B]lock or [U]nblock an auction: ");
                String blockOrUnblock2 = S.nextLine().toUpperCase();
                if (blockOrUnblock2.equals("B")) {
                	
                System.out.println("Enter the name of the auction that you want to block: ");
                String auctionBlock = S.nextLine().toLowerCase();
                	findAuction(auctionBlock).setBlocked(); //set status
                            System.out.println("Auction blocked successfully.");
                            serializeAuctions(); //save status
                            break;
                } else if (blockOrUnblock2.equals("U")) {

                    System.out.println("Enter the name of the auction that you want to unblock: ");
                    String auctionUnblock = S.nextLine().toLowerCase();
                    	findAuction(auctionUnblock).setUnblocked(); //set status
                                System.out.println("Auction unblocked successfully.");
                                serializeAuctions(); //save status
                               break;
                } else {
                	break;
                }
                
            } case "7" :{ //Sign out
            	   activeUser = null;
            	   currentMenu = "start";
            	   break;
            }
           		case "Q" :{
      	  	    	
      	  	        System.exit(0);
      	  	      }
        }
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
  
//  public Seller findSeller(String username) {
//	  for (User i : users) {
//		  if(i.getUsername().equals(username) && i instanceof Seller) {
//			  return i;
//		  }
//	  }
//  }

  
  public User findUser(String username) {
	  for(User i : users) {
		  if(i.getUsername().equals(username)){
			  return i;
		  }
	  }
	  return null;
  }
  
  public Auction findAuction(String item) {
	  for(Auction a : auctions) {
		  if(a.getItem().getName().equals(item)) {
			  return a;
		  }
	  }
	  return null;
  }
  
}
