package classFiles.Blackjack;
/*
 * Main driver for the program.	
 */
public class BlackJackRunner {

	public static void main(String[] args) 
	{
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
		        
		        launch(args);

	}
		 

}
