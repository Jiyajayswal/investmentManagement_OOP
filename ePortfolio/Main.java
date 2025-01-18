//Main.java

package ePortfolio;

import java.util.Scanner;

/**
 * The Main class is the entry point for the ePortfolio application.
 * It provides a command-line interface for managing a portfolio of stocks and mutual funds.
 */
public class Main {

    /**
     * The main method serves as the command loop for interacting with the Portfolio class.
     * Users can buy, sell, update, calculate total gains, search for investments, and quit the program.
     * 
     * @param args command-line arguments where args[0] is the filename containing investments.
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Please provide a filename as the argument.");
            return;
        }

        String filename = args[0];  // Get the filename from the command-line argument
        Portfolio portfolio = new Portfolio();  // Initialize portfolio object
        
        // Load existing investments from the file
        portfolio.loadInvestmentsFromFile(filename);

        try (Scanner scanner = new Scanner(System.in)) {
            // Continuous loop to prompt the user for commands
            while (true) {
                System.out.println("Enter command (buy, sell, update, getGain, search, quit): ");
                String command = scanner.nextLine().trim().toLowerCase();  // Capture and clean user input

                // Command handling using a switch statement
                switch (command) {
                    case "buy":
                        // Handle the buy command
                        System.out.println("Enter type (stock or mutualfund): ");
                        String type = scanner.nextLine();  // Input for investment type
                        System.out.println("Enter symbol: ");
                        String symbol = scanner.nextLine();  // Input for investment symbol

                        if (portfolio.investmentExists(symbol)) {
                            // If the investment exists, prompt the user to add more to it
                            System.out.println("Enter quantity to add: ");
                            int quantity = Integer.parseInt(scanner.nextLine());  // Quantity input
                            System.out.println("Enter price: ");
                            double price = Double.parseDouble(scanner.nextLine());  // Price input
                            portfolio.updateExistingInvestment(symbol, quantity, price);  // Update existing investment
                        } else {
                            // If the investment does not exist, prompt the user to add a new one
                            System.out.println("Enter name: ");
                            String name = scanner.nextLine();  // Input for investment name
                            System.out.println("Enter quantity: ");
                            int quantity = Integer.parseInt(scanner.nextLine());  // Input for quantity
                            System.out.println("Enter price: ");
                            double price = Double.parseDouble(scanner.nextLine());  // Input for price
                            portfolio.buyInvestment(type, symbol, name, quantity, price);  // Buy new investment
                        }
                        break;

                    case "sell":
                        // Handle the sell command
                        System.out.println("Enter symbol: ");
                        String sellSymbol = scanner.nextLine();  // Input for symbol to sell

                        if (portfolio.investmentExists(sellSymbol)) {
                            // If the investment exists, proceed with selling it
                            System.out.println("Enter quantity to sell: ");
                            int sellQuantity = Integer.parseInt(scanner.nextLine());  // Input for quantity to sell
                            System.out.println("Enter selling price: ");
                            double sellPrice = Double.parseDouble(scanner.nextLine());  // Input for selling price
                            portfolio.sellInvestment(sellSymbol, sellQuantity, sellPrice);  // Process the sale
                        } else {
                            System.out.println("Investment with this symbol does not exist.");  // Error if investment not found
                        }
                        break;

                    case "update":
                        // Handle the update command to update prices of all investments
                        portfolio.updatePrices();
                        break;

                    case "getgain":
                        // Calculate and display the total gain of the portfolio
                        System.out.println("Total Gain: " + portfolio.getTotalGain());
                        break;

                    case "search":
                        // Handle the search command to search for investments by criteria
                        System.out.println("Enter symbol to search (or leave blank): ");
                        String searchSymbol = scanner.nextLine();  // Input for symbol search
                        System.out.println("Enter keywords in name (or leave blank): ");
                        String keywords = scanner.nextLine();  // Input for name search

                        // Input for price range low
                        System.out.println("Enter price range low (or leave blank): ");
                        String lowPriceStr = scanner.nextLine();
                        double lowPrice = lowPriceStr.isEmpty() ? 0.0 : Double.parseDouble(lowPriceStr);  // Default to 0.0 if blank

                        // Input for price range high
                        System.out.println("Enter price range high (or leave blank): ");
                        String highPriceStr = scanner.nextLine();
                        double highPrice = highPriceStr.isEmpty() ? Double.MAX_VALUE : Double.parseDouble(highPriceStr);  // Default to maximum if blank

                        // Perform search and display results
                        portfolio.searchInvestments(searchSymbol, keywords, lowPrice, highPrice);
                        break;

                    case "quit", "Quit", "q", "Q", "QUIT":
                    portfolio.saveInvestments(filename);
                    System.out.println("Investments successfully saved!");
                    System.out.println("Exiting program...");
                    return;
                      

                    default:
                        // Handle invalid commands
                        System.out.println("Invalid command. Please try again.");
                }
            }
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

