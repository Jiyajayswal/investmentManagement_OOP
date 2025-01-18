package ePortfolio;


/**
 * The Stock class represents a stock investment, extending the Investment class.
 */
public class Stock extends Investment {

    private static final double COMMISSION = 9.99;

    /**
     * Constructs a Stock object with the specified symbol, name, quantity,
     * price, and book value.
     * 
     * @param symbol the stock symbol
     * @param name the name of the stock
     * @param quantity the quantity of shares
     * @param price the current price per share
     * @param bookValue the total book value of the investment
     */
    public Stock(String symbol, String name, int quantity, double price, double bookValue) {
        super(symbol, name, quantity, price, bookValue);
    }

    /**
     * Calculates and returns the gain (or loss) for the stock, 
     * which is the difference between the current value and the book value,
     * minus the commission fee.
     * 
     * @return the stock gain (or loss)
     */
    @Override
    public double getGain() {
        gain = (quantity * price - COMMISSION) - bookValue;
        return gain;
    }

    /**
     * Returns a string representation of the stock.
     * 
     * @return a string representation of the stock
     */
    @Override
    public String toString() {
        return "Stock [symbol=" + symbol + ", name=" + name + ", quantity=" + quantity + ", price=" + price + "]";
    }

    // Redeem shares method
    // @Override
    // public void redeemShares(int amount) {
    //     if (amount > getQuantity()) {
    //         throw new IllegalArgumentException("Cannot redeem more shares than owned.");
    //     }
    //     setQuantity(getQuantity() - amount);
    //     setBookValue(getBookValue() * ((double) getQuantity() / (getQuantity() + amount)));
    // }

    public double getCommission() {
       return COMMISSION;
    }
}
