package classFiles.Blackjack;
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

    //
    //
    //
    //
    //
    @Override
    public void start(Stage primaryStage) {
        // Create a label to display player's money and initialize it with $2500
        Label moneyLabel = new Label("$2500");

        // Create an instance of ChipManager with initial money and money label
        Moneyfunction chipManager = new Moneyfunction(2500, moneyLabel);

        // Create a button to add $100 to player's money
        Button addChipsButton = new Button("Add $100");
        // Set action for addChipsButton - calls addChips method of chipManager with amount 100
        addChipsButton.setOnAction(e -> chipManager.addChips(100));

        // Create a button to subtract $100 from player's money
        Button subtractChipsButton = new Button("Subtract $100");
        // Set action for subtractChipsButton - calls subtractChips method of chipManager with amount 100
        subtractChipsButton.setOnAction(e -> chipManager.subtractChips(100));

        // Create a vertical box layout to hold UI components
        VBox root = new VBox(10);
        // Add moneyLabel, addChipsButton, and subtractChipsButton to the root layout
        root.getChildren().addAll(moneyLabel, addChipsButton, subtractChipsButton);

        // Create a scene with the root layout, setting width and height
        Scene scene = new Scene(root, 200, 150);

        // Set the title of the stage (window)
        primaryStage.setTitle("Blackjack");
        // Set the scene for the stage
        primaryStage.setScene(scene);
        // Show the stage
        primaryStage.show();
    }

    // Main method to launch the JavaFX application
    public static void main(String[] args) {
        launch(args);
    }
}