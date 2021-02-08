import org.junit.*;
import static org.junit.Assert.*;

import java.io.*;
import java.util.*;

public class Problem3Tests extends KTests {
  /*Main method runs tests in this file*/ 
  public static void main(String args[]) {
    org.junit.runner.JUnitCore.main("Problem3Tests");
  } 
  
  // Ensure constructor exists
  @Test public void portfolio_constructor() {
    String owner = "Gordon Gecko";
    int maxStocks = 5;
    Portfolio port = new Portfolio(owner, maxStocks);
  }

  // Check consturctor and basic getters()
  public void check_portfolio(Portfolio port,
                              String owner, double cash, int stockCount)
  {
    String fmt = "%-15s %-15s %-15s\n";
    String msg =
      String.format("Portfolio attributes incorrect\n") +
      String.format(fmt,"ATTRIBUTE","EXPECT","ACTUAL") +
      String.format(fmt,"getOwner()",     owner,port.getOwner()) +
      String.format(fmt,"getCash()",      cash,port.getCash()) +
      String.format(fmt,"getStockCount()",stockCount,port.getStockCount()) +
      "";

    assertEquals("\ngetOwner() wrong: "+msg,     owner,port.getOwner());
    assertEquals("\ngetCash() wrong: "+msg,      cash,port.getCash(), 1e-6);
    assertEquals("\ngetStockCount() wrong: "+msg,stockCount,port.getStockCount());
  }

  @Test public void portfolio_construct_get1() {
    String owner = "Gordon Gecko";
    int maxStocks = 5;
    Portfolio port = new Portfolio(owner, maxStocks);
    check_portfolio(port, owner, 0.0, 0);
  }

  @Test public void portfolio_construct_get2() {
    String owner = "Barren Wuffet";
    int maxStocks = 10;
    Portfolio port = new Portfolio(owner, maxStocks);
    check_portfolio(port, owner, 0.0, 0);
  }

  @Test public void portfolio_deposit1() {
    String owner = "Barren Wuffet";
    int maxStocks = 5;
    Portfolio port = new Portfolio(owner, maxStocks);
    double deposit = 100.25;
    assertTrue( port.deposit(deposit) );
    check_portfolio(port, owner, deposit, 0);
  }

  @Test public void portfolio_deposit2() {
    String owner = "Gordon Gecko";
    int maxStocks = 7;
    Portfolio port = new Portfolio(owner, maxStocks);
    double deposit = 512.16;
    assertTrue( port.deposit(deposit) );
    check_portfolio(port, owner, deposit, 0);
  }

  @Test public void portfolio_deposit_fail() {
    String owner = "Gordon Gecko";
    int maxStocks = 7;
    Portfolio port = new Portfolio(owner, maxStocks);
    double deposit = -16.91;
    assertFalse( port.deposit(deposit) );
    check_portfolio(port, owner, 0.0, 0);
  }

  @Test public void portfolio_multiple_deposit1() {
    String owner = "Gordon Gecko";
    int maxStocks = 7;
    Portfolio port = new Portfolio(owner, maxStocks);
    double tot = 0.0;
    double deposit;

    deposit = 100.00;
    tot += deposit;
    assertTrue( port.deposit(deposit) );
    check_portfolio(port, owner, tot, 0);

    deposit = 200.16;
    tot += deposit;
    assertTrue( port.deposit(deposit) );
    check_portfolio(port, owner, tot, 0);

    deposit = 45.50;
    tot += deposit;
    assertTrue( port.deposit(deposit) );
    check_portfolio(port, owner, tot, 0);

    deposit = -45.50;           // cannot deposit negative amount
    //    tot += deposit;
    assertFalse( port.deposit(deposit) );
    check_portfolio(port, owner, tot, 0);

  }

  @Test public void portfolio_deposit_withdraw() {
    String owner = "Barren Wuffet";
    int maxStocks = 3;
    Portfolio port = new Portfolio(owner, maxStocks);
    double tot = 0.0;
    double amount;

    amount = 100.00;
    tot += amount;
    assertTrue( port.deposit(amount) );
    check_portfolio(port, owner, tot, 0);

    amount = 50.00;
    tot -= amount;
    assertTrue( port.withdraw(amount) );
    check_portfolio(port, owner, tot, 0);

    amount = 35.19;
    tot -= amount;
    assertTrue( port.withdraw(amount) );
    check_portfolio(port, owner, tot, 0);

    amount = 35.19;             // withdrawing too much
    //    tot -= amount;
    assertFalse( port.withdraw(amount) );
    check_portfolio(port, owner, tot, 0);
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

  @Test public void portfolio_nostocks_totalValue1(){
    String owner = "Gordon Gecko";
    int maxStocks = 5;
    Portfolio port = new Portfolio(owner, maxStocks);
    assertTrue( port.deposit( 98765.1234) );
    assertTrue( port.withdraw( 100.00 ) );
    assertEquals( 98665.1234, port.totalValue(), 1e-6 );
  }
  @Test public void portfolio_nostocks_totalValue2(){
    String owner = "Barren Wuffet";
    int maxStocks = 3;
    Portfolio port = new Portfolio(owner, maxStocks);
    assertTrue( port.deposit( 1000.00) );
    assertEquals( 1000.00, port.totalValue(), 1e-6 );
  }

  @Test public void portfolio_nostocks_toString1(){
    String expect =
      "Portfolio of Barren Wuffet\n"+
      "Cash: $1000.00\n"+
      "0 stocks\n"+
      "Total value: $1000.00\n"+
      "";
    String owner = "Barren Wuffet";
    int maxStocks = 3;
    Portfolio port = new Portfolio(owner, maxStocks);
    assertTrue( port.deposit( 1000.00) );
    check_toString(port, expect);
  }
  @Test public void portfolio_nostocks_toString2(){
    String expect =
      "Portfolio of Gordon Gecko\n"+
      "Cash: $98665.12\n"+
      "0 stocks\n"+
      "Total value: $98665.12\n"+
      "";
    String owner = "Gordon Gecko";
    int maxStocks = 5;
    Portfolio port = new Portfolio(owner, maxStocks);
    assertTrue( port.deposit( 98765.1234) );
    assertTrue( port.withdraw( 100.00 ) );
    check_toString(port, expect);
  }

}
