import org.junit.*;
import static org.junit.Assert.*;

import java.io.*;
import java.util.*;

public class Problem2Tests extends KTests {
  /*Main method runs tests in this file*/ 
  public static void main(String args[]) {
    org.junit.runner.JUnitCore.main("Problem2Tests");
  } 
  
  // Tests for the buyMore() method
  public void check_buyMore(String symbol, double price,
                            int numShares, String name,
                            int buyQuant)
  {
    Stock stock = new Stock(symbol,price,numShares,name);
    int expectNumShares = buyQuant + numShares;
    double actualCost = stock.buyMore(buyQuant);
    assertEquals("stock.getShares() wrong", expectNumShares, stock.getShares());
    double expectCost = -buyQuant * price;
    assertEquals("returned cost wrong",expectCost,actualCost, 1e-4);
    assertEquals("price changed",price,stock.getPrice(), 1e-4);
    assertEquals("symbol changed",symbol,stock.getSymbol());
    assertEquals("name changed",name,stock.getName());
  }

  @Test public void check_buyMore1() {
    String symbol = "GOOGL";
    double price = 1000.62;
    int numShares = 500;
    String name = "Alphabet Inc Class A";
    int buyQuant = 100;
    check_buyMore(symbol,price,numShares,name,buyQuant);
  }
  @Test public void check_buyMore2() {
    String symbol = "GOOGL";
    double price = 1000.62;
    int numShares = 500;
    String name = "Alphabet Inc Class A";
    int buyQuant = 256;
    check_buyMore(symbol,price,numShares,name,buyQuant);
  }
  @Test public void check_buyMore3() {
    String symbol = "GOOGL";
    double price = 1000.62;
    int numShares = 500;
    String name = "Alphabet Inc Class A";
    int buyQuant = 256;
    check_buyMore(symbol,price,numShares,name,buyQuant);
  }
  @Test public void check_buyMore4() {
    String symbol = "AAPL";
    double price = 163.05;
    int numShares = 800;
    String name = "Apple Inc.";
    int buyQuant = 5;
    check_buyMore(symbol,price,numShares,name,buyQuant);
  }

  // Tests for the sellOff() method
  public void check_sellOff(String symbol, double price,
                            int numShares, String name,
                            int sellQuant)
  {
    Stock stock = new Stock(symbol,price,numShares,name);
    int expectNumShares = numShares < sellQuant ? numShares : numShares - sellQuant;
    double actualProfit = stock.sellOff(sellQuant);
    assertEquals("stock.getShares() wrong", expectNumShares, stock.getShares());
    double expectProfit = numShares < sellQuant ? 0.0 : sellQuant * price;
    assertEquals("returned cost wrong",expectProfit, actualProfit, 1e-4);
    assertEquals("price changed",price,stock.getPrice(), 1e-4);
    assertEquals("symbol changed",symbol,stock.getSymbol());
    assertEquals("name changed",name,stock.getName());
  }

  @Test public void stock_sellOff1() {
    String symbol = "GOOGL";
    double price = 1000.62;
    int numShares = 500;
    String name = "Alphabet Inc Class A";
    int sellQuant = 100;
    check_sellOff(symbol,price,numShares,name,sellQuant);
  }
  @Test public void stock_sellOff2() {
    String symbol = "GOOGL";
    double price = 1000.62;
    int numShares = 500;
    String name = "Alphabet Inc Class A";
    int sellQuant = 250;
    check_sellOff(symbol,price,numShares,name,sellQuant);
  }
  @Test public void stock_sellOff3() {
    String symbol = "AAPL";
    double price = 163.05;
    int numShares = 800;
    String name = "Apple Inc.";
    int sellQuant = 800;
    check_sellOff(symbol,price,numShares,name,sellQuant);
  }
  // Check for attempts to sell too much
  @Test public void stock_sellOff4() {
    String symbol = "AAPL";
    double price = 163.05;
    int numShares = 800;
    String name = "Apple Inc.";
    int sellQuant = 801;
    check_sellOff(symbol,price,numShares,name,sellQuant);
  }
  @Test public void stock_sellOff5() {
    String symbol = "GOOGL";
    double price = 1000.62;
    int numShares = 250;
    String name = "Alphabet Inc Class A";
    int sellQuant = 500;
    check_sellOff(symbol,price,numShares,name,sellQuant);
  }


}
