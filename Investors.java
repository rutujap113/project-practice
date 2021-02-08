// Demonstrate some basic functionality of the Portfolio class.
public class Investors{
  public static void main(String args[]){
    Portfolio barren = new Portfolio("Barren Wuffet", 5);
    barren.deposit(10000.0);
    System.out.println( barren.toString() );

    barren.addStock("GOOGL", 1000.62, "Alphabet Inc Class A");
    barren.buyShares("GOOGL", 5);

    barren.addStock("MSFT", 79.12, "Microsoft Corporation");
    barren.buyShares("MSFT", 10);

    System.out.println( barren.toString() );

    Portfolio gordon = new Portfolio("Gordon Gecko", 3);
    gordon.deposit(50000.0);
    System.out.println( gordon.toString() );

    gordon.addStock("AAPL", 166.89, "Apple Inc.");
    gordon.buyShares("AAPL", 50);
    
    gordon.addStock("GOOGL", 1000.62, "Alphabet Inc Class A");
    gordon.buyShares("GOOGL", 15);

    gordon.addStock("MSFT", 79.12, "Microsoft Corporation");
    gordon.buyShares("MSFT", 100);
    
    System.out.println( gordon.toString() );

    barren.sellShares("AAPL",100); // barren doesn't have AAPL

  }
}
