package Console;

import java.util.Scanner;

public class Menu {
  Scanner S = new Scanner(System.in);

  public int startMenu() {
    System.out.println("==== Main Menu ====");
    System.out.println("1 - Create Account");
    System.out.println("2 - Log In");
    System.out.println("3 - Browse Auctions");
    System.out.println("Q - Quit");
    System.out.println("Input: ");

    String a = S.nextLine().toUpperCase();

    switch (a) {
      case "1" :{
        return 1;
      }
      case "2" : {
        return 2;
      }
      case "3" :{
        return 3;
      }
      case "Q" :{
        System.exit(0);
      }
      default :{
        System.out.println("Invalid responce");
        return startMenu();
      }
      }

  }

  public int buyerMenu(){
    System.out.println("====Buyer Menu====");
    System.out.println("1 - Search Item by name");
    System.out.println("2 - Place Bid");
    System.out.println("3 - Browse Auctions");
    System.out.println("4 - Sign out");
    System.out.println("Q - Quit");

    String a = S.nextLine().toUpperCase();

    switch (a) {
      case "1" :{
        return 1;
      }
      case "2" : {
        return 2;
      }
      case "3" :{
        return 3;
      }
      case "4" :{
        return 4;
      }
      case "Q" :{
        System.exit(0);
      }
      default :{
        System.out.println("Invalid responce");
        return buyerMenu();
      }
      }

  }

  public int sellerMenu(){
    System.out.println("====Seller Menu====");
    System.out.println("1 - Browse my listings");
    System.out.println("2 - Search for Item by name");
    System.out.println("3 - Create Auction");
    System.out.println("4 - Verify Auction");
    System.out.println("5 - Sign out");
    System.out.println("Q - Quit");

    String a = S.nextLine().toUpperCase();

    switch (a) {
      case "1" :{
        return 1;
    
      }
      case "2" : {
        return 2;
      
      }
      case "3" :{
        return 3;
       
      }
      case "4" :{
        return 4;
       
      }
      case "5" :{
        return 5;
       
      }
      case "Q" :{
        System.exit(0);
      }
      default :{
        System.out.println("Invalid responce");
        return sellerMenu();
      }
      }
  }

  public int adminMenu(){
    System.out.println("====Admin Menu====");
    System.out.println("1 - Delete an Auction");
    System.out.println("2 - Delete a user");
    System.out.println("3 - Sign out");
    System.out.println("Q - Quit");
    
    String a = S.nextLine().toUpperCase();

    switch (a) {
      case "1" :{
        return 1;
        
      }
      case "2" : {
        return 2;
        
      }
      case "3" :{
        return 3;
       
      }
      case "Q" :{
        System.exit(0);
      }
      default :{
        System.out.println("Invalid responce");
        return adminMenu();
      }
      }
  }
}
