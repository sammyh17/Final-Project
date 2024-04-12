package src.classFiles.Blackjack;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.transform.Translate;

//import javafx.scene.layout.VBox;

import javafx.stage.Stage;

//import application.Card;

// Known errors
//
// Player doesnt auto win on blackjack and can still play after
// Dealer still plays even if player busts

public class cardFunctions extends Application {
	private int playerBalance;
	public ArrayList<Card> playerHand; //Card
	public ArrayList<Card> dealerHand; //Card
	public Deck deck; //Deck
	public int BET_AMOUNT;
	private ArrayList<Card> cards; //Card


    @Override
    public void start(Stage primaryStage) {
    	primaryStage.setTitle("Blackjack");

    	Rectangle rect = new Rectangle(2000,2000);
        rect.setFill(Color.DARKGREEN);
        Button startButton = new Button("Start Game");

        StackPane layout = new StackPane();
        layout.getChildren().add(rect);
        layout.getChildren().add(startButton);

        Scene scene = new Scene(layout, 700, 500);
        
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
//     // Set the title of the stage (window)
//        primaryStage.setTitle("Blackjack");
//        // Set the scene for the stage
//        primaryStage.setScene(scene);
//        // Show the stage
//        primaryStage.show();
    	
    	
    	
    	// EXPERIMENT
    	//Create a label to display player's money and initialize it with $2500
    	Label balance = new Label("Balance:");
    	Label moneyLabel = new Label("$2500");
    	Label betsize = new Label("Bet amount:");
    	Label betLabel = new Label("$0");
    	balance.setTextFill(Color.WHITE);
    	moneyLabel.setTextFill(Color.WHITE);
    	betsize.setTextFill(Color.WHITE);
    	betLabel.setTextFill(Color.WHITE);
    	
    	// Create a button to subtract $100 from player's money and add to bet pool
    	Button subtractChipsButton = new Button("Bet $100");
    	// Create a butoon to add $100 to player's money from bet pool
    	Button refundChipsButton = new Button("Return $100");
    	
    	// Create an instance of ChipManager with initial money and money label
    	Moneyfunction chipManager = new Moneyfunction(2500, moneyLabel);
    	
    	Moneyfunction betManager = new Moneyfunction(0, betLabel);
    	
    	// Set action for subtractChipsButton - calls subtractChips method of chipManager with amount 100
    	subtractChipsButton.setOnAction(e -> 
    		{
    		if(chipManager.getBal() >= 100)
    			{
    			chipManager.subtractChips(100);  
    	    	betManager.addChips(100);
    			}
    		}
    	);
    	
    	refundChipsButton.setOnAction(e -> 
    		{
    		if(betManager.getBal() >= 100)
    			{
    			betManager.subtractChips(100); 
        		chipManager.addChips(100);
    			}
    		}
    	);
    	//
    	
   
    	
    	
    	Button hitButton = new Button("Hit");
    	hitButton.setTextFill(Color.RED);
    	Button standButton = new Button("Stand");
    	standButton.setTextFill(Color.RED);
    	//Button splitButton = new Button("Split");
    	//Button doubleDownButton = new Button("Double Down");
    	Button startHand = new Button("Play hand");
    	startHand.setTextFill(Color.RED);
    	if((betManager.getBal())==0) {
    		startHand.setOnAction(null);
    		hitButton.setOnAction(null);
    		standButton.setOnAction(null);
    		} else {
    			startHand.setOnAction(e -> {
    				subtractChipsButton.setOnAction(null);
    				refundChipsButton.setOnAction(null);
    				hitButton.setOnAction(l -> {
    					playerHand.add(Dealer2.dealCard());
    					checkPlayerBust();
    					});
    				standButton.setOnAction(f -> {
    					while (calculateScore(dealerHand) < 17) {
    						dealerHand.add(Dealer2.dealCard());
    						}
    					determineWinner();
    					});
    			});
    	}
    	

    	startHand.setOnAction(e -> {
    		subtractChipsButton.setOnAction(null);
    		refundChipsButton.setOnAction(null);
    		hitButton.setOnAction(l -> {
                playerHand.add(Dealer2.dealCard());
                checkPlayerBust(); 
            });

        	standButton.setOnAction(f -> {
                while (calculateScore(dealerHand) < 17) {
                    dealerHand.add(Dealer2.dealCard());
                }
                determineWinner(); 
            });
    		
    	});
  
    	Rectangle rect2 = new Rectangle(2000,2000);
        rect2.setFill(Color.DARKGREEN);
              

    	BorderPane root = new BorderPane();
    	root.getChildren().add(rect2);
        root.setPadding(new Insets(10));
    	HBox buttonBox = new HBox(10, hitButton, standButton,subtractChipsButton,refundChipsButton,balance,moneyLabel,betsize,betLabel,startHand);
        root.setBottom(buttonBox);
        Scene scene2=new Scene(root,700,500);
        primaryStage.setScene(scene2);
        primaryStage.show();
      
        
        primaryStage.setTitle("Blackjack");
        // Set the scene for the stage
        primaryStage.setScene(scene);
        // Show the stage
        primaryStage.show();

        startButton.setOnAction(e -> {
        	primaryStage.setScene(scene2);
            System.out.println("Starting Blackjack game...");
        });
        
    	hitButton.setOnAction(null);

    	standButton.setOnAction(null);
    	
    	
    	Card testCard = new Card(1, "Spades", "Ace");
        Text text = new Text(testCard.getRank() + " of " + testCard.getSuit());
        Translate rosettaStone = new Translate();
        text.setStroke(Color.WHITE);
        rosettaStone.setY(100);
        rosettaStone.setX(100);
        text.getTransforms().add(rosettaStone);
        root.getChildren().add(text);


//    	splitButton.setOnAction(e -> {
//    	    if (playerHand.size() == 2 && playerBalance >= BET_AMOUNT) {
//    	        // Check if the first two cards are the same rank (e.g., two Queens)
//    	        if (playerHand.get(0).getRank() == playerHand.get(1).getRank()) {
//    	            // Deduct the bet amount for the second hand
//    	            playerBalance -= BET_AMOUNT;
//
//
//    	            // Create two separate hands and deal one card to each
//    	            ArrayList<Card> hand1 = new ArrayList<>();
//    	            ArrayList<Card> hand2 = new ArrayList<>();
//    	            hand1.add(playerHand.get(0));
//    	            hand2.add(playerHand.get(1));
//    	            hand1.add(deck.drawCard());
//    	            hand2.add(deck.drawCard());
//
//    	            // Update playerHand to hold the first hand, and add the second hand
//    	            playerHand.clear();
//    	            playerHand.addAll(hand1);
//    	            playerHand.addAll(hand2);
//
//    	        } else {
//    	            // If the two cards are not the same rank, splitting is not allowed
//    	            System.out.print("Splitting is only allowed with pairs of the same rank.");
//    	        }
//    	    } else {
//    	        // Insufficient cards or balance for splitting
//    	        System.out.print("Cannot split at this time.");
//    	    }
//    	});
/*
    	doubleDownButton.setOnAction(e -> {
    	    if (playerHand.size() == 2 && playerBalance >= BET_AMOUNT) {

    	        playerBalance -= BET_AMOUNT;

    	        //Draws one additional card for the player
    	        playerHand.add(deck.drawCard());

    	        //make stand after
    	        standButton.fire();
    	    } else {
    	        // Insufficient cards or balance for doubling down
    	        System.out.print("Cannot double down at this time.");
    	    }
    	});
*/    	
    	
//    	 // Create a label to display player's money and initialize it with $2500
//        Label moneyLabel = new Label("$2500");
//
//        // Create an instance of ChipManager with initial money and money label
//        Moneyfunction chipManager = new Moneyfunction(2500, moneyLabel);
//
//        // Create a button to add $100 to player's money
//        Button addChipsButton = new Button("Add $100");
//        // Set action for addChipsButton - calls addChips method of chipManager with amount 100
//        addChipsButton.setOnAction(e -> chipManager.addChips(100));
//
//        // Create a button to subtract $100 from player's money
//        Button subtractChipsButton = new Button("Subtract $100");
//        // Set action for subtractChipsButton - calls subtractChips method of chipManager with amount 100
//        subtractChipsButton.setOnAction(e -> chipManager.subtractChips(100));
//
//        // Create a vertical box layout to hold UI components
//        VBox root2 = new VBox(10);
//        // Add moneyLabel, addChipsButton, and subtractChipsButton to the root layout
//        root.getChildren().addAll(moneyLabel, addChipsButton, subtractChipsButton);
//
//        // Create a scene with the root layout, setting width and height
//        Scene scene2 = new Scene(root2, 200, 150);
//
//        // Set the title of the stage (window)
//        primaryStage.setTitle("Blackjack");
//        // Set the scene for the stage
//        primaryStage.setScene(scene2);
//        // Show the stage
//        primaryStage.show();
    	
    	
    	
    }// END START
    private int calculateScore(ArrayList<Card> hand) {
    	int score = 0;
    	boolean hasAce = false;
    	for (Card card : hand) {
    		if (card.getVal() == 1) { // Ace
    			hasAce = true;
            }
    		score += card.getVal();
        }
    	if (hasAce && score + 10 <= 21) {
    		score += 10; // Count Ace as 11
        }
    	return score;
    }

    private void checkPlayerBust() {
        if (calculateScore(playerHand) > 21) {
            System.out.print("You Bust! Dealer Wins!");
            playerBalance -= BET_AMOUNT;
            System.out.print("Balance: $" + playerBalance);
        }
    }

    private void determineWinner() {
        int playerScore = calculateScore(playerHand);
        int dealerScore = calculateScore(dealerHand);

        if (playerScore > 21) {
            System.out.print("You Bust! Dealer Wins!");
           BET_AMOUNT = 0;
        } else if (dealerScore > 21 || playerScore > dealerScore) {
            System.out.print("You Win!");
            playerBalance += BET_AMOUNT;
        } else if (playerScore < dealerScore) {
            System.out.print("Dealer Wins!");
            BET_AMOUNT = 0;
        } else {
            System.out.print("It's a Tie!");
        }

        System.out.print("Balance: $" + playerBalance);
    }


    public static void main(String[] args) 
    	{launch(args);}
}