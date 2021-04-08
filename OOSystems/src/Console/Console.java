package Console;

import AuctionSystem.*; // Import everything from AuctionSystem package
import java.util.Scanner;
import java.util.LocalDateTime;
import java.util.List;

public class Console{
  //Start up
  Scanner S = new Scanner(System.in);
  Sys system = new Sys();
  Menu menu = new Menu();
  String Choice;

  public Console(){
    System.out.println("Start");
  }

  public void auctionSetup() throws Exception{
    choice = menu.startMenu();
  }
}
