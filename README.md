# CIS*2430 (Fall 2024) Assignment 


# Name: Jiya Jayswal
# Compilation: javac ePortfolio/*.java
# Run Command: java ePortfolio.Main investments.txt

### Overview
This assignment implements an investment management system where users can buy, sell, and search for stocks and mutual funds. The core components of this system are based on an object-oriented design using inheritance to handle investments as a common superclass and `Stock` and `MutualFund` as subclasses. The system supports efficient searching of investments using a `HashMap` index.

### Features
1. **Investment Classes**: 
   - `Investment`
   - `Stock`
   - `MutualFund`
   
2. **Buying and Selling**: 
   - Allows the buying and selling of stocks and mutual funds. 
   - Updates the list of investments and modifies the search index accordingly.

3. **Search Functionality**:
   - Supports searching for investments based on keywords in their name.
   - Implements single keyword, multiple keyword, and combined searches.

4. **HashMap Indexing**:
   - Stores keywords from investment names in a `HashMap` to optimize search performance.

5. **File I/O**:
   - Investments are loaded from and saved to a file.
   - Supports reading and writing investment details and updating the file when buying or selling investments.

6. **Command-Line Parameters**: 
   - Supports specifying the file for reading and writing investment data via command-line arguments.

## Directory structure
    /doc
    /ePortfolio   
      Investment.java                
      Stock.java                     
      MutualFund.java
      Main.java
      Portfolio.java               
    README.md  
    investments.txt

## Compilation and Execution 

Steps to Compile

1. Navigate to the `src/` directory:
   cd jjayswal_A2/java

2. Compile all `.java` files:
   javac ePortfolio/*.java

### Steps to Run

After compiling, you can run the program with the following command:
  java ePortfolio.Main investments.txt

java pack.Main
### Test Plan

#### 1. File I/O Testing

- **Input File Parsing**
  - Verify that investments are correctly loaded from the input file.
  - Input: Input a file with a list of stocks and mutual funds.
  - Output: The investments should be properly parsed, stored in the system, and the index updated.

- **Output File Writing**
  - Ensure the system correctly updates the output file after modifying investments.
  - Input: After adding a stock and selling a mutual fund.
  - Output: The output file should reflect these changes accurately.

#### 2. Buying and Selling Investments

- **Buying an Investment**
  - Confirm that an investment can be bought and added to the list.
  - Input: Buy a `stock` investment.
  - Output: The stock should be added to the list, and the index updated.

- **Selling an Investment**
  - Confirm: Verify that an investment can be sold and removed from the list.
  - Input: Sell a `mutualfund` investment.
  - Output: The mutual fund should be removed from the list, and the index updated.

#### 3. Searching for Investments

- **Single Keyword Search**
  - Test that searching with a single keyword returns the correct investments.
  - Input: Search for "tata" 
  - Output: A list of investments whose names contain "tata" should be returned.

- **Multiple Keyword Search**
  - Test that searching with multiple keywords correctly filters investments.
  - Input: Search for "tata" and "stock".
  - Output: A list of investments containing both "tata" and "stock" in their names should be returned.

- **Combined Search (Keyword and Price Range)**
  - Ensure that combined searches with both keywords and price ranges work as expected.
  - Input: Search for investments with the keyword "APP" and a price range between $50 and $100.
  - Output: A list of investments matching both criteria should be returned.

#### 4. HashMap Indexing

- **Index Creation**
  - Verify that the initial index is created correctly when the file is loaded.
  - Input: Load a file with a variety of investments.
  - Output: The `HashMap` should correctly map keywords to their respective investments.

- **Index Update on Buying**
  - Test that the index updates when a new investment is bought.
  - Input: Buy a `Stock` with the keyword "Tech".
  - Output: The index should be updated to include "Tech" pointing to the new stock.

- **Index Update on Selling**
  - Ensure that the index updates when an investment is sold.
  - Input: Sell a `MutualFund` with the keyword "Global".
  - Output: The index should be updated, removing the "Global" keyword.




### Conclusion
The investment management system is designed to handle a range of essential functions, such as managing investments, searching through them, and maintaining an up-to-date index. This test plan provides a comprehensive set of test cases to validate the system’s functionality under different scenarios. The program is expected to correctly handle buying and selling investments, searching with multiple parameters, and updating its internal index. Error handling is also a key component to ensure smooth operation, even when input data is incorrect or incomplete.