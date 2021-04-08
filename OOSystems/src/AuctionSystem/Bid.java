package AuctionSystem;
import java.time.LocalDateTime;

public class Bid{
  private double amount;
  private Buyer who;
  private LocalDateTime when;

  public Bid(double amount, Buyer who, LocalDateTime when) {
    this.amount = amount;
    this.who = who;
    this.when = when;
  }
}
