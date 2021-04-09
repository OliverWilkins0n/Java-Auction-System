package AuctionSystem;

import java.io.Serializable;

public class Item implements Serializable {
  private String description;

  public Item(String description){
    this.description = description;
  }

  public String getDescription(String description){
    return this.description;
  }

  public void setDescription(String description){
    this.description = description;
  }

}
