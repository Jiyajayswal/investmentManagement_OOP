//Portfolio.java
package ePortfolio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
/**
 * The Portfolio class manages a collection of stocks and mutual funds, 
 * allowing for buying, selling, updating prices, calculating gains, and searching investments.
 */
public class Portfolio {
    private ArrayList<Investment> investments;
    private HashMap<String, List<Integer>> nameIndex;

    /** List of stocks in the portfolio */
    //private ArrayList<Stock> stocks = new ArrayList<>();

    /** List of mutual funds in the portfolio */
    //private ArrayList<MutualFund> mutualFunds = new ArrayList<>();

    /**
     * Constructs a new Portfolio object, initializing the stock and mutual fund lists.
     */
    public Portfolio() {
        
        //stocks = new ArrayList<>();  // Initialize stocks list
        //mutualFunds = new ArrayList<>();  // Initialize mutual funds list
        investments = new ArrayList<>();
    }

    /**
     * Loads investments from a file and adds them to the portfolio.
     * Each investment is formatted with type, symbol, name, quantity, price, and bookValue.
     *
     * @param filename the name of the file to load investments from
     */
    public void loadInvestmentsFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            String type = null;
            String symbol = null;
            String name = null;
            int quantity = 0;
            double price = 0.0;
            double bookValue = 0.0;

            while ((line = reader.readLine()) != null) {
                // Parse each attribute from the file line by line
                if (line.startsWith("type")) {
                    type = line.split("=")[1].trim().replace("\"", "");
                } else if (line.startsWith("symbol")) {
                    symbol = line.split("=")[1].trim().replace("\"", "");
                } else if (line.startsWith("name")) {
                    name = line.split("=")[1].trim().replace("\"", "");
                } else if (line.startsWith("quantity")) {
                    quantity = Integer.parseInt(line.split("=")[1].trim().replace("\"", ""));
                } else if (line.startsWith("price")) {
                    price = Double.parseDouble(line.split("=")[1].trim().replace("\"", ""));
                } else if (line.startsWith("bookValue")) {
                    bookValue = Double.parseDouble(line.split("=")[1].trim().replace("\"", ""));
                }
                
                // When all attributes for an investment are loaded, create the object
                if (type != null && symbol != null && name != null && quantity != 0 && price != 0 && bookValue != 0) {
                    if (type.equalsIgnoreCase("stock")) {
                        investments.add(new Stock(symbol, name, quantity, price, bookValue));
                    } else if (type.equalsIgnoreCase("mutualfund")) {
                        investments.add(new MutualFund(symbol, name, quantity, price, bookValue));
                    }else{
                        System.out.println("Invalid investment type in file: " + type);
                    }
                
                    // Reset variables for next investment
                    type = symbol = name = null;
                    quantity = 0;
                    price = bookValue = 0.0;
                }
                
            }

            System.out.println("Investments loaded from file successfully.");
            createNameIndex(); // Create the index after loading the investments
        } catch (IOException e) {
            System.out.println("Error reading from file: " + filename + ". The file may not exist or could not be read.");
        } catch (NumberFormatException e) {
            System.out.println("Error parsing numerical data from file.");
        }
    }

    /**
     * Saves all investments in the portfolio to a file in a readable format.
     * Each investment will be written with its type, symbol, name, quantity, price, and bookValue.
     *
     * @param filename the name of the file to save investments to
     */
    public void saveInvestments(String filename) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename))) {
            for (Investment investment : investments) {
                bufferedWriter.write("type = \"" + investment.getClass().getSimpleName().toLowerCase() + "\"\n");
                bufferedWriter.write("symbol = \"" + investment.symbol + "\"\n");
                bufferedWriter.write("name = \"" + investment.name + "\"\n");
                bufferedWriter.write("quantity = \"" + investment.quantity + "\"\n");
                bufferedWriter.write("price = \"" + investment.price + "\"\n");
                bufferedWriter.write("bookValue = \"" + investment.bookValue + "\"\n\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
    
    
    /**
     * Buys an investment (either stock or mutual fund) and adds it to the portfolio.
     * 
     * @param type the type of investment ("stock" or "mutualfund")
     * @param symbol the symbol of the investment
     * @param name the name of the investment
     * @param quantity the quantity to buy
     * @param price the price per share
     */
    public void buyInvestment(String type, String symbol, String name, int quantity, double price) {
        Investment investment = null; // Check if investment already exists
        
        // for (Investment inv : investments) {
        //     if (inv.getSymbol().equalsIgnoreCase(symbol)) {
        //         existingInvestment = inv;
        //         break;
        //     }
        // }

        // if (existingInvestment != null) {
        //     // If investment exists, update quantity and price
        //     existingInvestment.setQuantity(existingInvestment.getQuantity() + quantity);
        //     existingInvestment.setPrice(price); // Optionally update price if needed
        //     System.out.println("Investment updated: " + existingInvestment);
        // } 
        // else {

        if (type.equalsIgnoreCase("stock")) {
                investment = new Stock(symbol, name, quantity, price, price * quantity);
                } else if (type.equalsIgnoreCase("mutualfund")) {
                    investment = new MutualFund(symbol, name, quantity, price, price * quantity);
                } else {
                    System.out.println("Invalid investment type.");
                    return;
                }
                investments.add(investment);
                updateNameIndexForInvestment(investment);
        //}
    } 
    
    private void updateNameIndexForInvestment(Investment investment) {

        if (nameIndex == null) {
            nameIndex = new HashMap<>();
        }
        
        String[] keywords = investment.getName().split("\\s+");
        int index = investments.indexOf(investment);

        for (String keyword : keywords) {
            keyword = keyword.toLowerCase();
            nameIndex.putIfAbsent(keyword, new ArrayList<>());
            nameIndex.get(keyword).add(index);
        }
    }


    /**
     * Sells an investment (either stock or mutual fund) from the portfolio.
     * 
     * @param symbol the symbol of the investment
     * @param quantity the quantity to sell
     * @param price the selling price per share
     */
    public void sellInvestment(String symbol, int quantity, double price) {
        // Lookup the investment from the investments map (could be Stock or MutualFund)
        //Investment investment = investments.get(symbol);
        Investment investment = null;
        for (Investment inv : investments) {
            if (inv.getSymbol().equalsIgnoreCase(symbol)) {
                investment = inv;
                break;
            }
        }

        if (investment == null) {
            System.out.println("Investment not found.");
            return;
        }

        // Process the investment (either Stock or MutualFund)
        if (investment instanceof Stock) {
            Stock stock = (Stock) investment;

            // Ensure enough quantity is available to sell
            if (stock.getQuantity() >= quantity) {
                double commission = stock.getCommission();
                double payment = price * quantity - commission;
                double bookValue = stock.getPrice() * quantity + commission;
                double gain = payment - bookValue;
                stock.setGain(gain);
                System.out.println("Stock sold. Gain: " + gain);

                // Update the quantity of the stock
                stock.setQuantity(stock.getQuantity() - quantity);

                // If all the stock is sold, remove it from the investments list
                if (stock.getQuantity() == 0) {
                    investments.remove(stock);
                }
                removeIndexForInvestment(stock);
            } else {
                System.out.println("Not enough quantity to sell.");
            }

            removeIndexForInvestment(stock);
        } else if (investment instanceof MutualFund) {
            MutualFund fund = (MutualFund) investment;

            // Ensure enough quantity is available to sell
            if (fund.getQuantity() >= quantity) {
                double redemptionFee = fund.getRedemptionFee();
                double payment = price * quantity - redemptionFee;
                double bookValue = fund.getPrice() * quantity + redemptionFee;
                double gain = payment - bookValue;
                fund.setGain(gain);
                System.out.println("Mutual fund sold. Gain: " + gain);

                // Update the quantity of the mutual fund
                fund.setQuantity(fund.getQuantity() - quantity);

                // If all the mutual fund units are sold, remove it from the investments list
                if (fund.getQuantity() == 0) {
                    investments.remove(fund);
                }
                removeIndexForInvestment(fund);
            } else {
                System.out.println("Not enough quantity to sell.");
            }

            //removeIndexForInvestment(fund);
        } else {
            System.out.println("Investment is neither Stock nor MutualFund.");
        }
    }

    
    private void removeIndexForInvestment(Investment investment) {
        String[] keywords = investment.getName().split("\\s+");
        int index = investments.indexOf(investment);

        for (String keyword : keywords) {
            keyword = keyword.toLowerCase();
            List<Integer> indices = nameIndex.get(keyword);
            if (indices != null) {
                indices.remove(Integer.valueOf(index)); // Remove index from the list
            }
        }
    }


    /**
     * Updates the prices of all stocks and mutual funds in the portfolio.
     * The user is prompted to enter new prices for each investment.
     */
    public void updatePrices() {
        try (Scanner scanner = new Scanner(System.in)) {
            for (Investment investment : investments) {
                System.out.println("Enter new price for " + investment.getSymbol() + ":");
                double newPrice = scanner.nextDouble();
                investment.setPrice(newPrice);
            }
        }
    }
    
    

    /**
     * Calculates the total gain from all investments in the portfolio.
     * 
     * @return the total gain from stocks and mutual funds
     */
    public double getTotalGain() {
        double totalGain = 0.0;
    
        for (Investment investment : investments) {
            totalGain += investment.getGain();
        }
    
        return totalGain;
    }
    
   

    /**
     * Searches for investments in the portfolio based on criteria such as symbol, name, 
     * and price range, and prints the matching investments.
     * 
     * @param symbol the symbol to search for (can be empty)
     * @param name the name to search for (can be empty)
     * @param lowPrice the lower bound of the price range
     * @param highPrice the upper bound of the price range
     */
    public void searchInvestments(String symbol, String name, double lowPrice, double highPrice) {
        // Check if the nameIndex contains the name key
        if (nameIndex.containsKey(name)) {
            // Get the list of indices for the investments with this name
            List<Integer> investmentIndices = nameIndex.get(name);
    
            // Iterate over the list of indices (which point to investments)
            for (Integer index : investmentIndices) {
                Investment investment = investments.get(index); // Retrieve the actual investment object
    
                // Check if this investment matches the search criteria
                boolean matchesSymbol = symbol.isEmpty() || investment.getSymbol().equalsIgnoreCase(symbol);
                boolean matchesPrice = investment.getPrice() >= lowPrice && investment.getPrice() <= highPrice;
    
                if (matchesSymbol && matchesPrice) {
                    System.out.println(investment); // Print the matched investment
                }
            }
        } else {
            // If name is not found in the index, fall back to looping through all investments
            for (Investment investment : investments) {
                boolean matchesSymbol = symbol.isEmpty() || investment.getSymbol().equalsIgnoreCase(symbol);
                boolean matchesName = name.isEmpty() || investment.getName().toLowerCase().contains(name.toLowerCase());
                boolean matchesPrice = investment.getPrice() >= lowPrice && investment.getPrice() <= highPrice;
    
                if (matchesSymbol && matchesName && matchesPrice) {
                    System.out.println(investment);
                }
            }
        }
    }
 

    /**
     * Checks if an investment with the given symbol exists in the portfolio.
     * 
     * @param symbol the symbol of the investment to check
     * @return true if the investment exists, false otherwise
     */
    public boolean investmentExists(String symbol) {
        for (Investment investment : investments) {
            if (investment.getSymbol().equalsIgnoreCase(symbol)) {
                return true;
            }
        }
        return false;
    }
    

    /**
     * Updates the quantity and price of an existing investment in the portfolio.
     * 
     * @param symbol the symbol of the investment to update
     * @param quantity the quantity to add
     * @param price the new price of the investment
     */
    public void updateExistingInvestment(String symbol, int quantity, double price) {
        // Lookup the investment in the investments list
        Investment investment = null;
        for (Investment inv : investments) {
            if (inv.getSymbol().equalsIgnoreCase(symbol)) {
                investment = inv;
                break;
            }
        }

        if (investment != null) {
            investment.setQuantity(investment.getQuantity() + quantity);
            investment.setPrice(price);
        } else {
            System.out.println("Investment not found.");
        }
    }

        /**
     * Creates an index for all investments based on the keywords in their names.
     */
    public void createNameIndex() {
        nameIndex = new HashMap<>();

        for (int i = 0; i < investments.size(); i++) {
            Investment investment = investments.get(i);
            String[] keywords = investment.getName().split("\\s+"); // Split name by spaces
            
            for (String keyword : keywords) {
                keyword = keyword.toLowerCase(); // Normalize keyword to lowercase
                nameIndex.putIfAbsent(keyword, new ArrayList<>());
                nameIndex.get(keyword).add(i); // Add index to the list of that keyword
            }
        }

        System.out.println("Index created for all investment names.");
    }

    

}
