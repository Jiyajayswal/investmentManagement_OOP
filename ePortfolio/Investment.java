package ePortfolio;

public abstract class Investment {

    protected String symbol;  // The symbol of the investment
    protected String name;    // The name of the investment
    protected int quantity;   // The quantity of shares
    protected double price;   // The current price per share
    protected double bookValue; // The total book value of the investment
    protected double gain;    // The gain or loss of the investment

    /**
     * Constructor for creating an Investment object.
     * 
     * @param symbol the investment symbol
     * @param name the name of the investment
     * @param quantity the quantity of shares
     * @param price the current price per share
     * @param bookValue the total book value of the investment
     */
    public Investment(String symbol, String name, int quantity, double price, double bookValue) {

        if (quantity < 0 || price < 0 || bookValue < 0) {
            throw new IllegalArgumentException("Quantity, price, and book value must be non-negative.");
        }
        this.symbol = symbol;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.bookValue = bookValue;
    }

    /**
     * Returns the symbol of the investment.
     * 
     * @return the investment symbol
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Returns the name of the investment.
     * 
     * @return the investment name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the quantity of shares of the investment.
     * 
     * @return the quantity of shares
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of shares of the investment.
     * 
     * @param quantity the new quantity of shares
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Returns the current price per share of the investment.
     * 
     * @return the current price per share
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the current price per share of the investment.
     * 
     * @param price the new price per share
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Calculates and returns the gain (or loss) for the investment,
     * which is the difference between the current value and the book value.
     * 
     * @return the investment gain (or loss)
     */
    public double getGain() {
        gain = (quantity * price) - bookValue;
        return gain;
    }

    public void setGain(double gain) {
        // BigDecimal currentValue = BigDecimal.valueOf(quantity).multiply(BigDecimal.valueOf(price));
        // BigDecimal bookVal = BigDecimal.valueOf(bookValue);
        // return currentValue.subtract(bookVal).doubleValue();
        // this.gain = gain;
        this.gain = gain;
    }

    public double getBookValue(){
        return bookValue;
    }


    /**
     * Returns a string representation of the investment.
     * 
     * @return a string representation of the investment
     */
    @Override
    public String toString() {
        return "Investment [symbol=" + symbol + ", name=" + name + ", quantity=" + quantity + ", price=" + price + "]";
    }
}

