package AuctionSystem;
import java.time.LocalDate;

public class Bid{
  private double amount;
  private Buyer who;
  private LocalDate when;

  public Bid(double amount, Buyer who, LocalDate when) {
    this.amount = amount;
    this.who = who;
    this.when = when;
  }
}
