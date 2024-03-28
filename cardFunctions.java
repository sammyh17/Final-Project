package BlackJack;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class cardFunctioms extends Application {
	private int playerBalance;
	public ArrayList<Object> playerHand;
	public ArrayList<Object> dealerHand;
	public Object deck;
	public int BET_AMOUNT;
	private Label balanceLabel;
	private Label resultLabel;
	private ArrayList<Object> cards;
	
	
    @Override
    public void start(Stage primaryStage) {
    	Button hitButton = new Button("Hit");               
    	Button standButton = new Button("Stand");                      
    	Button splitButton = new Button("Split");           
    	Button doubleDownButton = new Button("Double Down");
    	
    	BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));
    	HBox buttonBox = new HBox(10, hitButton, standButton, splitButton, doubleDownButton);
    	VBox resultBox = new VBox(10, resultLabel, balanceLabel);
        root.setBottom(buttonBox);
        root.setTop(resultBox);
        
        
    	hitButton.setOnAction(e -> {
            playerHand.add(deck.drawCard());
            checkPlayerBust();
        });
    	
    	standButton.setOnAction(e -> {
            while (calculateScore(dealerHand) < 17) {
                dealerHand.add(deck.drawCard());
            }
            determineWinner();
        });
    	
    	splitButton.setOnAction(e -> {
    	    if (playerHand.size() == 2 && playerBalance >= BET_AMOUNT) {
    	        // Check if the first two cards are the same rank (e.g., two Queens)
    	        if (playerHand.get(0).getRank() == playerHand.get(1).getRank()) {
    	            // Deduct the bet amount for the second hand
    	            playerBalance -= BET_AMOUNT;
    	            balanceLabel.setText("Balance: $" + playerBalance);
    	            
    	            // Create two separate hands and deal one card to each
    	            ArrayList<Card> hand1 = new ArrayList<>();
    	            ArrayList<Card> hand2 = new ArrayList<>();
    	            hand1.add(playerHand.get(0));
    	            hand2.add(playerHand.get(1));
    	            hand1.add(deck.drawCard());
    	            hand2.add(deck.drawCard());
    	            
    	            // Update playerHand to hold the first hand, and add the second hand
    	            playerHand.clear();
    	            playerHand.addAll(hand1);
    	            playerHand.addAll(hand2);
    	            
    	        } else {
    	            // If the two cards are not the same rank, splitting is not allowed
    	            resultLabel.setText("Splitting is only allowed with pairs of the same rank.");
    	        }
    	    } else {
    	        // Insufficient cards or balance for splitting
    	        resultLabel.setText("Cannot split at this time.");
    	    }
    	});
    	
    	doubleDownButton.setOnAction(e -> {
    	    if (playerHand.size() == 2 && playerBalance >= BET_AMOUNT) {
    	        // Deduct the bet amount for the double down
    	        playerBalance -= BET_AMOUNT;
    	        balanceLabel.setText("Balance: $" + playerBalance);
    	        
    	        // Draw one additional card for the player
    	        playerHand.add(deck.drawCard());
    	        
    	        // Automatically trigger the stand action after doubling down
    	        standButton.fire();
    	    } else {
    	        // Insufficient cards or balance for doubling down
    	        resultLabel.setText("Cannot double down at this time.");
    	    }
    	});
    }
    private int calculateScore(ArrayList<Card> hand) {
    	int score = 0;
    	boolean hasAce = false;
    	for (Card card : hand) {
    		if (card.getValue() == 1) { // Ace
    			hasAce = true;
            }
    		score += card.getValue();
        }
    	if (hasAce && score + 10 <= 21) {
    		score += 10; // Count Ace as 11
        }
    	return score;
    }
    
    private void checkPlayerBust() {
        if (calculateScore(playerHand) > 21) {
            resultLabel.setText("You Bust! Dealer Wins!");
            playerBalance -= 10;
            balanceLabel.setText("Balance: $" + playerBalance);
        }
    }
    
    private void determineWinner() {
        int playerScore = calculateScore(playerHand);
        int dealerScore = calculateScore(dealerHand);

        if (playerScore > 21) {
            resultLabel.setText("You Bust! Dealer Wins!");
            playerBalance -= BET_AMOUNT;
        } else if (dealerScore > 21 || playerScore > dealerScore) {
            resultLabel.setText("You Win!");
            playerBalance += BET_AMOUNT;
        } else if (playerScore < dealerScore) {
            resultLabel.setText("Dealer Wins!");
            playerBalance -= BET_AMOUNT;
        } else {
            resultLabel.setText("It's a Tie!");
        }

        balanceLabel.setText("Balance: $" + playerBalance);
    }
   
    public Card drawCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("Deck is empty");
        }
        return cards.remove(cards.size() - 1);
    }
    	
    public static void main(String[] args) {
        launch(args);
    }
}