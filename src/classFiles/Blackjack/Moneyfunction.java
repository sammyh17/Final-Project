package src.classFiles.Blackjack;
import javafx.scene.control.Label;

public class Moneyfunction {
    private int playerMoney; // Player's money amount
    private Label moneyLabel; // Label to display player's money on the UI

    // Constructor to initialize ChipManager with initial money and money label
    public Moneyfunction(int initialMoney, Label moneyLabel) {
        this.playerMoney = initialMoney; // Set initial money
        this.moneyLabel = moneyLabel; // Set money label for UI
        updateMoneyLabel(); // Update money label on UI
    }

    // Method to add chips to the player's moneys
    public void addChips(int amount) {
        playerMoney += amount; // Add chips to money
        updateMoneyLabel(); // Update money label on UIw
    }

    // Method to subtract chips from the player's money
    public void subtractChips(int amount) {
        // Check if player has enough money to subtract
        if (playerMoney >= amount) {
            playerMoney -= amount; // Subtract chips from money
            updateMoneyLabel(); // Update money label on UI
        } else {
            // Handle insufficient funds error (you can customize this part based on your needs)
            System.out.println("Insufficient funds!");
        }
    }

    // Private method to update the money label on the UI
    private void updateMoneyLabel() {
        moneyLabel.setText("$" + playerMoney); // Update the money label on the UI with current money
    }

}// End class