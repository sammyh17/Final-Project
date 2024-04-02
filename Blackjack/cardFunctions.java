package application;
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

import application.Card;

public class cardFunctioms extends Application {
	private int playerBalance;
	public ArrayList<Card> playerHand; //Card
	public ArrayList<Card> dealerHand; //Card
	public Deck deck; //Deck
	public int BET_AMOUNT;
	private ArrayList<Card> cards; //Card
	
	
    @Override
    public void start(Stage primaryStage) {
    	Button hitButton = new Button("Hit");               
    	Button standButton = new Button("Stand");                      
    	Button splitButton = new Button("Split");           
    	Button doubleDownButton = new Button("Double Down");
    	
    	BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));
    	HBox buttonBox = new HBox(10, hitButton, standButton, splitButton, doubleDownButton);
        root.setBottom(buttonBox);
        
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
    	            System.out.print("Splitting is only allowed with pairs of the same rank.");
    	        }
    	    } else {
    	        // Insufficient cards or balance for splitting
    	        System.out.print("Cannot split at this time.");
    	    }
    	});
    	
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
    }
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
            playerBalance -= 10;
            System.out.print("Balance: $" + playerBalance);
        }
    }
    
    private void determineWinner() {
        int playerScore = calculateScore(playerHand);
        int dealerScore = calculateScore(dealerHand);

        if (playerScore > 21) {
            System.out.print("You Bust! Dealer Wins!");
            playerBalance -= BET_AMOUNT;
        } else if (dealerScore > 21 || playerScore > dealerScore) {
            System.out.print("You Win!");
            playerBalance += BET_AMOUNT;
        } else if (playerScore < dealerScore) {
            System.out.print("Dealer Wins!");
            playerBalance -= BET_AMOUNT;
        } else {
            System.out.print("It's a Tie!");
        }

        System.out.print("Balance: $" + playerBalance);
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