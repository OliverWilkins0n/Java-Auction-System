import java.util.LinkedList;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.List;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


  public class CSVReader {


    public static void main(String[] args) {

    //  String choice = "";
    //  String filepath = "database.txt";
      String filepath_auction = "auction_database.txt";
      //String line = "";
    //  String splitBy = ",";

    	List<Auction> auctions = readAuctionsFromCSV(filepath_auction);
      for (Auction a : auctions) {
      			System.out.println(a);
          }
        }

    public static List<Auction> readAuctionsFromCSV(String filepath_auction){
      List<Auction> auctions = new ArrayList<>();
      Path pathToTheFile = Paths.get(filepath_auction);

      try (BufferedReader br = Files.newBufferedReader(pathToTheFile)){
        String line = br.readLine();
        while(line != null){
          //Splits the line at each , to separate the attributes
          String[] attributes = line.split(",");
          Auction auction = createAuction(attributes);

          auctions.add(auction);
          line = br.readLine();
          //System.out.println(attributes);
        //	Auction auction = createAuction(attributes);
        //	auctions.add(auction);
        //	line = br.readLine();
        }

      } catch (IOException ioe) {
        ioe.printStackTrace();
      }
      return auctions;
    }

    private static Auction createAuction(String[] metadata) {
      String name = metadata[0];
      String description = metadata[1];
      Double startPrice = Double.parseDouble(metadata[2]);
      Double reservePrice = Double.parseDouble(metadata[3]);

      return new Auction(name, description, startPrice, reservePrice);
    }
  }
    //	private static Auction createAuction(String[] metadata) {
    //		Seller seller = metadata[0];
    //
    //
    //	}

    class Auction {
      private String name;
      private String description;
      private double startPrice;
      private double reservePrice;

      public Auction(String name, String description, double startPrice, double reservePrice) {
        this.name = name;
        this.description = description;
        this.startPrice = startPrice;
        this.reservePrice = reservePrice;
      }
      public String getName() {
        return name;
      }
      public void setName(String name) {
        this.name = name;
      }
    public String getDescription() {
      return description;
    }
    public void setDescription(String description) {
      this.description = description;
    }
    public double getStartPrice() {
      return startPrice;
    }
    public void setStartPrice(double startPrice) {
      this.startPrice = startPrice;
    }
    public double getReservePrice() {
      return startPrice;
    }
    public void setReseverePrice(double reservePrice) {
      this.reservePrice = reservePrice;
    }
    @Override
    public String toString() {
      return "Auction [Name=" + name + ", Description=" + description + ", Start Price=" +startPrice+ ", Resevere Price=" +reservePrice;
    }
  }
