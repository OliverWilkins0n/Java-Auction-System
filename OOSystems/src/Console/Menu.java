package Console;

import java.util.Scanner;


public class Menu {
  Scanner S = new Scanner(System.in);
  //Console console = new Console();
  //Sys system = new Sys();

  public String startMenu() {
	System.out.println("");
    System.out.println("==== Main Menu ====");
    System.out.println("1 - Create Account");
    System.out.println("2 - Log In");
    System.out.println("3 - Browse Auctions");
    System.out.println("Q - Quit");
    System.out.println("Input: ");
    String a = S.nextLine().toUpperCase();

    switch (a) {
      case "1" :{
        return "1";
      }
      case "2" : {
        return "2";
      }
      case "3" :{
        return "3";
      }
      case "Q" :{
    	new Console().serialize();  
        System.exit(0);
        
        
      }
      default :{
        System.out.println("Invalid responce");
        return startMenu();
      }
      }

  }

  public String buyerMenu(){
    System.out.println("1 - Browse Auctions");
    System.out.println("2 - Place Bid");
    System.out.println("3 - Search For Auction By Name");
    System.out.println("4 - Sign out");
    System.out.println("Q - Quit");

    String a = S.nextLine().toUpperCase();

    switch (a) {
      case "1" :{
        return "1";
      }
      case "2" : {
        return "2";
      }
      case "3" :{
        return "3";
      }
      case "4" :{
        return "4";
      }
      case "Q" :{
    	//new Console().serialize();
        System.exit(0);
      }
      default :{
        System.out.println("Invalid responce");
        return buyerMenu();
      }
      }

  }

  public String sellerMenu(){
    //System.out.println("====Seller Menu====");
    System.out.println("1 - Browse my listings");
    System.out.println("2 - Create Auction");
    System.out.println("3 - Verify Auction");
    System.out.println("4 - Sign out");
    System.out.println("Q - Quit");

    String a = S.nextLine().toUpperCase();

    switch (a) {
      case "1" :{
        return "1";
    
      }
      case "2" : {
        return "2";
      
      }
      case "3" :{
        return "3";
       
      }
      case "4" :{
        return "4";
       
      }
      case "Q" :{
    	//new Console().serialize();
        System.exit(0);
      }
      default :{
        System.out.println("Invalid responce");
        return sellerMenu();
      }
      }
  }

  public String adminMenu(){
    System.out.println("====Admin Menu====");
    System.out.println("1 - Delete an Auction");
    System.out.println("2 - Delete a user");
    System.out.println("3 - List all Sellers");
    System.out.println("4 - Sign out");
    System.out.println("Q - Quit");
    
    String a = S.nextLine().toUpperCase();

    switch (a) {
      case "1" :{
        return "1";
        
      }
      case "2" : {
        return "2";
        
      }
      case "3" :{
        return "3";
       
      }
      case "4" :{
    	  return "4";
      }
      case "Q" :{
    	//new Console().serialize();
        System.exit(1);
      }
      default :{
        System.out.println("Invalid responce");
        return adminMenu();
      }
      }
  }
  
//  public User getMenuUsername(){ Not needed for now.
//	  
// }
}
