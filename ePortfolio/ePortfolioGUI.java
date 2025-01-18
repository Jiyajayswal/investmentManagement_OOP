import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ePortfolioGUI {
    private JFrame frame;
    private JTextArea messageArea;
    
    public ePortfolioGUI() {
        frame = new JFrame("ePortfolio");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        
        // Welcome message panel
        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new BorderLayout());
        JTextArea welcomeMessage = new JTextArea("Welcome to ePortfolio!\nUse the menu to manage your investments.");
        welcomeMessage.setEditable(false);
        welcomePanel.add(welcomeMessage, BorderLayout.CENTER);
        
        // Menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu commandsMenu = new JMenu("Commands");
        JMenuItem buyItem = new JMenuItem("Buy");
        JMenuItem sellItem = new JMenuItem("Sell");
        JMenuItem updateItem = new JMenuItem("Update");
        JMenuItem getGainItem = new JMenuItem("Get Gain");
        JMenuItem searchItem = new JMenuItem("Search");
        JMenuItem quitItem = new JMenuItem("Quit");
        
        commandsMenu.add(buyItem);
        commandsMenu.add(sellItem);
        commandsMenu.add(updateItem);
        commandsMenu.add(getGainItem);
        commandsMenu.add(searchItem);
        commandsMenu.add(quitItem);
        menuBar.add(commandsMenu);
        frame.setJMenuBar(menuBar);
        
        // Message area
        messageArea = new JTextArea();
        messageArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(messageArea);
        
        frame.add(welcomePanel, BorderLayout.CENTER);
        frame.add(scrollPane, BorderLayout.SOUTH);
        
        // Action Listeners for menu items
        buyItem.addActionListener(e -> showBuyInterface());
        sellItem.addActionListener(e -> showSellInterface());
        updateItem.addActionListener(e -> showUpdateInterface());
        getGainItem.addActionListener(e -> showGetGainInterface());
        searchItem.addActionListener(e -> showSearchInterface());
        quitItem.addActionListener(e -> System.exit(0));
        
        frame.setVisible(true);
    }

    private void showBuyInterface() {
        // Handle the buy interface (create a new panel with buy options)
        JOptionPane.showMessageDialog(frame, "Buy Interface");
    }
    
    private void showSellInterface() {
        // Handle the sell interface (create a new panel with sell options)
        JOptionPane.showMessageDialog(frame, "Sell Interface");
    }
    
    private void showUpdateInterface() {
        // Handle the update interface (create a new panel with update options)
        JOptionPane.showMessageDialog(frame, "Update Interface");
    }

    private void showGetGainInterface() {
        // Handle the get gain interface
        JOptionPane.showMessageDialog(frame, "Get Gain Interface");
    }

    private void showSearchInterface() {
        // Handle the search interface
        JOptionPane.showMessageDialog(frame, "Search Interface");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ePortfolioGUI::new);
    }
}
