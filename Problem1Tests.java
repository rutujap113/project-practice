import org.junit.*;
import static org.junit.Assert.*;

import java.io.*;
import java.util.*;

public class Problem1Tests extends KTests {
  /*Main method runs tests in this file*/ 
  public static void main(String args[]) {
    org.junit.runner.JUnitCore.main("Problem1Tests");
  } 

  @Test public void stock_constructor() {
    Stock stock = new Stock("GOOGL",1000.62,500,"Alphabet Inc Class A");
  }

  // Basic tests for construction and getters
  public void check_getters(String symbol, double price,
                            int numShares, String name)
  {
    Stock stock = new Stock(symbol,price,numShares,name);
    assertEquals(symbol, stock.getSymbol());
    assertEquals(price, stock.getPrice(), 1e-6);
    assertEquals(numShares, stock.getShares());
    assertEquals(name, stock.getName());
  }

  @Test public void stock_getters1() {
    String symbol    = "GOOGL";
    double price     = 1000.62;
    int    numShares = 500;
    String name      = "Alphabet Inc Class A";
    check_getters(symbol,price,numShares,name);
  }
  @Test public void stock_getters2() {
    String symbol    = "AAPL";      
    double price     = 163.05;      
    int    numShares = 800;         
    String name      = "Apple Inc.";
    check_getters(symbol,price,numShares,name);
  }

  // toString() method for formatted output
  public void check_toString(String symbol, double price,
                             int numShares, String name,
                             String expect)
  {
    Stock stock = new Stock(symbol,price,numShares,name);
    String actual = stock.toString();
    String msg =
      String.format("\ntoString() wrong\n")+
      String.format("symbol: %s  price: %f  numShares: %d  name: %s\n",
                    symbol, price, numShares, name) +
      String.format("EXPECT:\n%s\n", expect) +
      String.format("ACTUAL:\n%s\n", actual) +
      "";
    assertEquals(msg,expect,actual);
  }
  
  @Test public void stock_toString1() {
    String symbol    = "GOOGL";
    double price     = 1000.62;
    int    numShares = 500;
    String name      = "Alphabet Inc Class A";
    String expect    = "Alphabet Inc Class A (GOOGL): price: $1000.62 shares: 500";
    check_toString(symbol,price,numShares,name,expect);
  }
  @Test public void stock_toString2() {
    String symbol    = "MSFT";
    double price     = 79.12;
    int    numShares = 126;
    String name      = "Microsoft Corporation";
    String expect    = "Microsoft Corporation (MSFT): price: $79.12 shares: 126";
    check_toString(symbol,price,numShares,name,expect);
  }
  @Test public void stock_multipleStocks(){
    String symbol1    = "MSFT";
    double price1     = 79.12;
    int    numShares1 = 126;
    String name1      = "Microsoft Corporation";
    String expect1    = "Microsoft Corporation (MSFT): price: $79.12 shares: 126";
    Stock stock1 = new Stock(symbol1,price1,numShares1,name1);

    String symbol2    = "MSFT";
    double price2     = 79.12;
    int    numShares2 = 126;
    String name2      = "Microsoft Corporation";
    String expect2    = "Microsoft Corporation (MSFT): price: $79.12 shares: 126";
    Stock stock2 = new Stock(symbol2,price2,numShares2,name2);
    
    assertEquals(expect1, stock1.toString());
    assertEquals(expect2, stock2.toString());
  }
}
