/*public class Stock {
    
}

//eliminate all the static aspects of Stock class
//symbol,price,numShares,name
Stock s = new Stock("APPL",1.43,0,"Apple.Inc.");
double cost = s.buyMore(10);

public String getSymbol(){
    retunr s.symbol;
}
//an implicit reference to the stock via the this pointer.
public double buyMore(int moreShares){

}

System.out.println(s.toString());*/


public class Stock{
// Represent ownership of a single stock
String symbol,name;
double price;
int numShares;

  public Stock(String Symbol, double Price, int NumShares, String Name){
  // Constructor which takes initial values for fields. Assign each
  // parameter to its corresponding field.
  symbol=Symbol;
  price=Price;
  numShares=NumShares;
  name=Name;

  };

  public String getSymbol()
  // Return the symbol for the stock
{
    return symbol;
};
  public double getPrice()
  // Return the price of the stock
{
    return price;
};

  public int getShares()
  // Return the number of shares owned
{
    return numShares;
};

  public String getName()
  // Return the name of the stock
{
    return name;
};
  public String toString()
  // Return a String with information on the given stock to the
  // screen. The format should be
  //   Microsoft Corporation (MSFT): price: $79.12 shares: 350
  //   ^^^                    ^^^            ^^            ^^
  //   Name                   Symbol         Price         Number of shares
  // 
  // Note that the price is preceded by a $ and has 2 decimal places
  // of accuracy. Since this method returns a formatted string, the
  // String.format() method use useful.
{   double roundedDouble = Math.round(price * 100.0) / 100.0; 
    String output = name + " (" + symbol + "): price: $" + String.valueOf(roundedDouble) + " shares: " + Integer.toString(numShares) ;
    return output;
};




  public double buyMore(int moreShares)
  // Buy more of the specified stock. The number of additional shares
  // to buy is the parameter moreShares.  Modify stock.numShares to be
  // this amount more. Calculate the cost of the purchase which is the
  // 
  //   cost -= (moreShares * stock.price)
  // 
  // Note that costs are negative while profits are positive. Return
  // the cost.
  {
      double cost -= (moreShares * price);
      return cost;
  };

  public double sellOff(int lessShares)
  // Sell off shares of the specified stock. The number of shares to
  // sell is the parameter lessShares. If the numShares field of stock
  // is smaller than lessShares, the transaction is error and return
  // 0.0 without changing anything about stock.  Otherwise, reduce
  // stock.numShares by lessShares. Calculate the profit which is
  // 
  //   profit = stock.price * lessShares
  // 
  // Return the profit.
{
    if(lessShare<numShare){
        profit = stock.price * lessShares;
        numShares -= lessShares;
        return profit;

    }
    else
     return 0.0;

};

}
