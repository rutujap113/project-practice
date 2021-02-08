import org.junit.*;
import static org.junit.Assert.*;

import java.io.*;
import java.util.*;

public class Problem4Tests extends KTests {
  /*Main method runs tests in this file*/ 
  public static void main(String args[]) {
    org.junit.runner.JUnitCore.main("Problem4Tests");
  } 
  
  // Check consturctor and basic getters()
  public void check_portfolio(Portfolio port,
                              String owner, double cash, int stockCount, double totalValue)

  {
    String fmt = "%-15s %-15s %-15s\n";
    String msg =
      String.format("Portfolio attributes incorrect\n") +
      String.format(fmt,"ATTRIBUTE","EXPECT","ACTUAL") +
      String.format(fmt,"getOwner()",     owner,port.getOwner()) +
      String.format(fmt,"getCash()",      cash,port.getCash()) +
      String.format(fmt,"getStockCount()",stockCount,port.getStockCount()) +
      String.format(fmt,"totalValue()",   totalValue,port.totalValue()) +
      "";

    assertEquals("\ngetOwner() wrong: "+msg,     owner,port.getOwner());
    assertEquals("\ngetCash() wrong: "+msg,      cash,port.getCash(), 1e-6);
    assertEquals("\ngetStockCount() wrong: "+msg,stockCount,port.getStockCount());
    assertEquals("\ntotalValue() wrong: "+msg,   totalValue,port.totalValue(), 1e-6);
  }

  public void check_toString(Portfolio port, String expect){
    String actual = port.toString();
    String msg =
      "toString() incorrect\n"+
      String.format("EXPECT:\n%s------\n\n",expect) +
      String.format("ACTUAL:\n%s------\n\n",actual) +
      "";
    assertEquals(msg,expect,actual);
  }    


  @Test public void portfolio_add_stock1() {
    String owner = "Gordon Gecko";
    int maxStocks = 5;
    Portfolio port = new Portfolio(owner, maxStocks);
    assertTrue( port.addStock("GOOGL",1000.62,"Alphabet Inc Class A") ); 
    check_portfolio(port, owner, 0.0, 1, 0);
    String expect =
      "Portfolio of Gordon Gecko\n"+
      "Cash: $0.00\n"+
      "1 stocks\n"+
      " Alphabet Inc Class A (GOOGL): price: $1000.62 shares: 0\n"+
      "Total value: $0.00\n"+
      "";
    check_toString(port, expect);
  }
  @Test public void portfolio_add_stock2() {
    String owner = "Barren Wuffet";
    int maxStocks = 3;
    Portfolio port = new Portfolio(owner, maxStocks);
    assertTrue( port.addStock("AAPL",163.05,"Apple Inc.") ); 
    check_portfolio(port, owner, 0.0, 1, 0);
    String expect =
      "Portfolio of Barren Wuffet\n"+
      "Cash: $0.00\n"+
      "1 stocks\n"+
      " Apple Inc. (AAPL): price: $163.05 shares: 0\n"+
      "Total value: $0.00\n"+
      "";
    check_toString(port, expect);
  }
  @Test public void portfolio_add_stock3() {
    String owner = "Barren Wuffet";
    int maxStocks = 4;
    Portfolio port = new Portfolio(owner, maxStocks);
    assertTrue( port.addStock("AAPL",163.05,"Apple Inc.") ); 
    assertTrue( port.addStock("GOOGL",1000.62,"Alphabet Inc Class A") ); 
    assertTrue( port.addStock("MSFT",79.12,"Microsoft Corporation") );
    check_portfolio(port, owner, 0.0, 3, 0);
    String expect =
      "Portfolio of Barren Wuffet\n"+
      "Cash: $0.00\n"+
      "3 stocks\n"+
      " Apple Inc. (AAPL): price: $163.05 shares: 0\n"+
      " Alphabet Inc Class A (GOOGL): price: $1000.62 shares: 0\n"+
      " Microsoft Corporation (MSFT): price: $79.12 shares: 0\n"+
      "Total value: $0.00\n"+
      "";
    check_toString(port, expect);
  }
  @Test public void portfolio_add_stock_fail1() {
    String owner = "Barren Wuffet";
    int maxStocks = 2;          // only 2 spaces for stocks
    Portfolio port = new Portfolio(owner, maxStocks);
    assertTrue( port.addStock("AAPL",163.05,"Apple Inc.") ); 
    assertTrue( port.addStock("GOOGL",1000.62,"Alphabet Inc Class A") ); 
    assertFalse( port.addStock("MSFT",79.12,"Microsoft Corporation") ); // can't add
    check_portfolio(port, owner, 0.0, 2, 0);
    String expect =
      "Portfolio of Barren Wuffet\n"+
      "Cash: $0.00\n"+
      "2 stocks\n"+
      " Apple Inc. (AAPL): price: $163.05 shares: 0\n"+
      " Alphabet Inc Class A (GOOGL): price: $1000.62 shares: 0\n"+
      "Total value: $0.00\n"+
      "";
    check_toString(port, expect);
  }

  @Test public void portfolio_buyShares1() {
    String owner = "Gordon Gecko";
    int maxStocks = 5;
    Portfolio port = new Portfolio(owner, maxStocks);
    assertTrue( port.addStock("GOOGL",1000.62,"Alphabet Inc Class A") ); 
    assertTrue( port.deposit(40000.00) );
    assertTrue( port.buyShares("GOOGL", 25) );
    check_portfolio(port, owner, 14984.5, 1, 40000);
    String expect =
      "Portfolio of Gordon Gecko\n"+
      "Cash: $14984.50\n"+
      "1 stocks\n"+
      " Alphabet Inc Class A (GOOGL): price: $1000.62 shares: 25\n"+
      "Total value: $40000.00\n"+
      "";
    check_toString(port, expect);
  }
  @Test public void portfolio_buyShares2() {
    String owner = "Gordon Gecko";
    int maxStocks = 5;
    Portfolio port = new Portfolio(owner, maxStocks);
    assertTrue( port.addStock("GOOGL",1000.62,"Alphabet Inc Class A") ); 
    assertTrue( port.deposit(40000.00) );
    assertTrue( port.buyShares("GOOGL", 25) );
    assertTrue( port.buyShares("GOOGL", 10) );
    check_portfolio(port, owner, 4978.30, 1, 40000);
    String expect =
      "Portfolio of Gordon Gecko\n"+
      "Cash: $4978.30\n"+
      "1 stocks\n"+
      " Alphabet Inc Class A (GOOGL): price: $1000.62 shares: 35\n"+
      "Total value: $40000.00\n"+
      "";
    check_toString(port, expect);
  }
  @Test public void portfolio_buyShares3() {
    String owner = "Gordon Gecko";
    int maxStocks = 5;
    Portfolio port = new Portfolio(owner, maxStocks);
    assertTrue( port.deposit(40000.00) );
    assertTrue( port.addStock("GOOGL",1000.62,"Alphabet Inc Class A") ); 
    assertTrue( port.addStock("MSFT",79.12,"Microsoft Corporation") );
    assertTrue( port.addStock("AAPL",163.05,"Apple Inc.") ); 
    assertTrue( port.buyShares("MSFT", 10) );
    assertTrue( port.buyShares("AAPL", 12) );
    assertTrue( port.buyShares("GOOGL", 25) );
    check_portfolio(port, owner, 12236.70, 3, 40000);
    String expect =
      "Portfolio of Gordon Gecko\n"+
      "Cash: $12236.70\n"+
      "3 stocks\n"+
      " Alphabet Inc Class A (GOOGL): price: $1000.62 shares: 25\n"+
      " Microsoft Corporation (MSFT): price: $79.12 shares: 10\n"+
      " Apple Inc. (AAPL): price: $163.05 shares: 12\n"+
      "Total value: $40000.00\n"+
      "";
    check_toString(port, expect);
  }
  @Test public void portfolio_buyShares4() {
    String owner = "Barren Wuffet";
    int maxStocks = 4;
    Portfolio port = new Portfolio(owner, maxStocks);
    assertTrue( port.deposit(100000.00) );
    assertTrue( port.addStock("MSFT",79.12,"Microsoft Corporation") );
    assertTrue( port.addStock("AAPL",163.05,"Apple Inc.") ); 
    assertTrue( port.addStock("GOOGL",1000.62,"Alphabet Inc Class A") ); 
    assertTrue( port.addStock("GIS",52.03,"General Mills, Inc.") );
    assertTrue( port.buyShares("AAPL", 14) );
    assertTrue( port.buyShares("MSFT", 19) );
    assertTrue( port.buyShares("GIS", 220) );
    check_portfolio(port, owner, 84767.42, 4, 100000);
    String expect =
      "Portfolio of Barren Wuffet\n"+
      "Cash: $84767.42\n"+
      "4 stocks\n"+
      " Microsoft Corporation (MSFT): price: $79.12 shares: 19\n"+
      " Apple Inc. (AAPL): price: $163.05 shares: 14\n"+
      " Alphabet Inc Class A (GOOGL): price: $1000.62 shares: 0\n"+
      " General Mills, Inc. (GIS): price: $52.03 shares: 220\n"+
      "Total value: $100000.00\n"+
      "";
    check_toString(port, expect);
  }

  @Test public void portfolio_buyShares_fail1() {
    String owner = "Gordon Gecko";
    int maxStocks = 5;
    Portfolio port = new Portfolio(owner, maxStocks);
    assertTrue(  port.addStock("GOOGL",1000.62,"Alphabet Inc Class A") ); 
    assertTrue(  port.deposit(40000.00) );
    assertFalse( port.buyShares("GOOGL", 250) );
    check_portfolio(port, owner, 40000, 1, 40000);
    String expect =
      "Portfolio of Gordon Gecko\n"+
      "Cash: $40000.00\n"+
      "1 stocks\n"+
      " Alphabet Inc Class A (GOOGL): price: $1000.62 shares: 0\n"+
      "Total value: $40000.00\n"+
      "";
    check_toString(port, expect);
  }

  @Test public void portfolio_buy_sellShares1() {
    String owner = "Gordon Gecko";
    int maxStocks = 5;
    Portfolio port = new Portfolio(owner, maxStocks);
    assertTrue(  port.addStock("GOOGL",1000.62,"Alphabet Inc Class A") ); 
    assertTrue(  port.deposit(40000.00) );
    assertTrue(  port.buyShares("GOOGL", 25) );
    assertTrue(  port.sellShares("GOOGL", 10) );
    check_portfolio(port, owner, 24990.70, 1, 40000);
    String expect =
      "Portfolio of Gordon Gecko\n"+
      "Cash: $24990.70\n"+
      "1 stocks\n"+
      " Alphabet Inc Class A (GOOGL): price: $1000.62 shares: 15\n"+
      "Total value: $40000.00\n"+
      "";
    check_toString(port, expect);
  }
  @Test public void portfolio_buy_sellShares2() {
    String owner = "Barren Wuffet";
    int maxStocks = 4;
    Portfolio port = new Portfolio(owner, maxStocks);
    assertTrue( port.deposit(100000.00) );
    assertTrue( port.addStock("MSFT",79.12,"Microsoft Corporation") );
    assertTrue( port.addStock("AAPL",163.05,"Apple Inc.") ); 
    assertTrue( port.addStock("GOOGL",1000.62,"Alphabet Inc Class A") ); 
    assertTrue( port.addStock("GIS",52.03,"General Mills, Inc.") );
    // 
    assertTrue( port.buyShares("AAPL", 14) );
    assertTrue( port.buyShares("MSFT", 19) );
    assertTrue( port.buyShares("GIS", 220) );
    // 
    assertTrue( port.sellShares("AAPL", 7) );
    assertTrue( port.sellShares("MSFT", 10) );
    assertTrue( port.sellShares("GIS", 120) );
    // 
    assertTrue( port.buyShares("GOOGL", 10) );
    // 
    check_portfolio(port, owner, 82937.37, 4, 100000);
    String expect =
      "Portfolio of Barren Wuffet\n"+
      "Cash: $82937.37\n"+
      "4 stocks\n"+
      " Microsoft Corporation (MSFT): price: $79.12 shares: 9\n"+
      " Apple Inc. (AAPL): price: $163.05 shares: 7\n"+
      " Alphabet Inc Class A (GOOGL): price: $1000.62 shares: 10\n"+
      " General Mills, Inc. (GIS): price: $52.03 shares: 100\n"+
      "Total value: $100000.00\n"+
      "";
    check_toString(port, expect);
  }

  @Test public void portfolio_buy_sellShares_fail1() {
    String owner = "Barren Wuffet";
    int maxStocks = 4;
    Portfolio port = new Portfolio(owner, maxStocks);
    assertTrue( port.deposit(100000.00) );
    assertTrue( port.addStock("MSFT",79.12,"Microsoft Corporation") );
    assertTrue( port.addStock("AAPL",163.05,"Apple Inc.") ); 
    assertTrue( port.addStock("GOOGL",1000.62,"Alphabet Inc Class A") ); 
    assertTrue( port.addStock("GIS",52.03,"General Mills, Inc.") );
    // 
    assertTrue( port.buyShares("AAPL", 14) );
    assertTrue( port.buyShares("MSFT", 19) );
    assertTrue( port.buyShares("GIS", 220) );
    // 
    assertTrue( port.sellShares("AAPL", 7) );
    assertFalse( port.sellShares("MSFT", 100) ); // too few shares
    assertTrue( port.sellShares("GIS", 120) );
    // 
    assertFalse( port.buyShares("GOOGL", 1000) ); // too little cash
    // 
    check_portfolio(port, owner, 92152.37, 4, 100000);
    String expect =
      "Portfolio of Barren Wuffet\n"+
      "Cash: $92152.37\n"+
      "4 stocks\n"+
      " Microsoft Corporation (MSFT): price: $79.12 shares: 19\n"+
      " Apple Inc. (AAPL): price: $163.05 shares: 7\n"+
      " Alphabet Inc Class A (GOOGL): price: $1000.62 shares: 0\n"+
      " General Mills, Inc. (GIS): price: $52.03 shares: 100\n"+
      "Total value: $100000.00\n"+
      "";
    check_toString(port, expect);
  }


  @Test public void portfolio_multiple1() {
    Portfolio barren = new Portfolio("Barren Wuffet", 5);
    Portfolio gordon = new Portfolio("Gordon Gecko", 3);
    assertTrue( barren.deposit(100000.00) );
    assertTrue( barren.addStock("MSFT",79.12,"Microsoft Corporation") );
    assertTrue( barren.addStock("AAPL",163.05,"Apple Inc.") ); 
    assertTrue( barren.addStock("GOOGL",1000.62,"Alphabet Inc Class A") ); 
    assertTrue( barren.addStock("GIS",52.03,"General Mills, Inc.") );
    // 
    assertTrue( gordon.deposit(900000.00) );
    assertTrue( gordon.addStock("AAPL",163.05,"Apple Inc.") ); 
    assertTrue( gordon.addStock("GIS",52.03,"General Mills, Inc.") );
    assertTrue( gordon.addStock("GOOGL",1000.62,"Alphabet Inc Class A") ); 
    // 
    assertTrue( barren.buyShares("AAPL", 14) );
    assertTrue( barren.buyShares("MSFT", 19) );
    assertTrue( barren.buyShares("GIS", 220) );
    // 
    assertTrue( gordon.buyShares("GOOGL", 190) );
    assertTrue( gordon.buyShares("AAPL", 410) );
    assertTrue( gordon.buyShares("GIS",  150) );
    // 
    assertFalse( gordon.addStock("MSFT",79.12,"Microsoft Corporation") ); // 3 stocks only
    // 
    assertTrue( barren.sellShares("AAPL", 7) );
    assertFalse( barren.sellShares("MSFT", 100) ); // too few shares
    assertTrue( barren.sellShares("GIS", 120) );
    assertTrue( barren.withdraw(5000.00) );
    // 
    assertFalse( gordon.sellShares("MSFT", 7) ); // not in portfolio
    assertTrue( gordon.sellShares("GOOGL", 100) );
    assertTrue( gordon.sellShares("GIS", 120) );
    assertFalse( gordon.deposit(-100) );
    assertTrue( gordon.deposit(1000) );
    // 
    assertFalse( barren.buyShares("GOOGL", 1000) ); // too little cash
    // 
    check_portfolio(barren, "Barren Wuffet", 87152.37, 4, 95000);
    String expect;
    expect =
      "Portfolio of Barren Wuffet\n"+
      "Cash: $87152.37\n"+
      "4 stocks\n"+
      " Microsoft Corporation (MSFT): price: $79.12 shares: 19\n"+
      " Apple Inc. (AAPL): price: $163.05 shares: 7\n"+
      " Alphabet Inc Class A (GOOGL): price: $1000.62 shares: 0\n"+
      " General Mills, Inc. (GIS): price: $52.03 shares: 100\n"+
      "Total value: $95000.00\n"+
      "";
    check_toString(barren, expect);
    check_portfolio(gordon, "Gordon Gecko", 742532.80, 3, 901000);
    expect =
      "Portfolio of Gordon Gecko\n"+
      "Cash: $742532.80\n"+
      "3 stocks\n"+
      " Apple Inc. (AAPL): price: $163.05 shares: 410\n"+
      " General Mills, Inc. (GIS): price: $52.03 shares: 30\n"+
      " Alphabet Inc Class A (GOOGL): price: $1000.62 shares: 90\n"+
      "Total value: $901000.00\n"+
      "";
    check_toString(gordon, expect);
  }



}
