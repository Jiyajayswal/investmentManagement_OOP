package ePortfolio;

/**
 * The MutualFund class represents a mutual fund investment, extending the Investment class.
 */
public class MutualFund extends Investment {

    private static final double REDEMPTION_FEE = 45.00;

    /**
     * Constructs a MutualFund object with the specified symbol, name, quantity,
     * price, and book value.
     * 
     * @param symbol the mutual fund symbol
     * @param name the name of the mutual fund
     * @param quantity the quantity of shares
     * @param price the current price per share
     * @param bookValue the total book value of the investment
     */
    public MutualFund(String symbol, String name, int quantity, double price, double bookValue) {
        super(symbol, name, quantity, price, bookValue);
    }

    /**
     * Calculates and returns the gain (or loss) for the mutual fund,
     * which is the difference between the current value and the book value,
     * minus the redemption fee.
     * 
     * @return the mutual fund gain (or loss)
     */
    @Override
    public double getGain() {
        gain = (quantity * price - REDEMPTION_FEE) - bookValue;
        return gain;
    }

    /**
     * Returns a string representation of the mutual fund.
     * 
     * @return a string representation of the mutual fund
     */

    //@Override
    public String toString() {
        return "MutualFund [symbol=" + getSymbol() + ", name=" + getName() + 
            ", quantity=" + getQuantity() + ", price=" + getPrice() +
            ", redemptionFee=" + REDEMPTION_FEE + ", gain=" + getGain() + "]";
    }

    // public void redeemShares(int amount) {
    //     if (amount > getQuantity()) {
    //         throw new IllegalArgumentException("Cannot redeem more shares than owned.");
    //     }
    //     setQuantity(getQuantity() - amount);
    //     setBookValue(getBookValue() * ((double) getQuantity() / (getQuantity() + amount)));
    // }


    public double getRedemptionFee() {
        return REDEMPTION_FEE;
    }
}