package src.classFiles.Blackjack;
	
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class cardFunctions extends Application {

    private int credits = 2500;
    private int betAmount = 0;
    private List<String> playerCards = new ArrayList<>();
    private List<String> dealerCards = new ArrayList<>();
    private boolean gameStarted = false;
    private boolean playerStanding = false;
    private boolean dealerDone = false;

    @Override
    public void start(Stage primaryStage) {
        // Create the main layout
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.setBackground(new Background(new BackgroundFill(Color.DARKGREEN, CornerRadii.EMPTY, Insets.EMPTY)));

        // Text to display game status
        Text gameStatus = new Text("Welcome to Blackjack!");
        gameStatus.setFill(Color.WHITE);

        // Label to display credits
        Label creditsLabel = new Label("Credits: " + credits);
        creditsLabel.setTextFill(Color.WHITE);

        // Label to display current bet
        Label betLabel = new Label("Current Bet: " + betAmount);
        betLabel.setTextFill(Color.WHITE);

        // Start game button
        Button startGameButton = new Button("Start Game");
        startGameButton.setOnAction(event -> {
            gameStarted = true;
            startNewHand();
            updateGameStatus(gameStatus);
            disableBettingButtons(startGameButton);
        });

        // Betting area
        Text betText = new Text("Place your bet:");
        Button bet100Button = new Button("Bet 100");

        // Clear bet button
        Button clearBetButton = new Button("Clear Bet");
        clearBetButton.setOnAction(event -> {
            credits += betAmount;
            betAmount = 0;
            updateBetAndCreditsLabels(betLabel, creditsLabel);
        });

        // Add action event handlers for betting buttons
        bet100Button.setOnAction(event -> {
            if (!gameStarted && credits >= 100) {
                betAmount += 100;
                credits -= 100;
                updateBetAndCreditsLabels(betLabel, creditsLabel);
            }
        });

        // Buttons for player actions
        Button hitButton = new Button("Hit");
        Button standButton = new Button("Stand");

        // Add action event handlers for player actions
        hitButton.setOnAction(event -> {
            if (gameStarted && !playerStanding) {
                // Deal another card to the player
                playerCards.add(drawCard());
                updateGameStatus(gameStatus);

                // Check if player busts
                if (calculateHandValue(playerCards) > 21) {
                    gameStatus.setText("Player busts! You lose.");
                    betAmount = 0;
                    updateBetAndCreditsLabels(betLabel, creditsLabel);
                    playerStanding = true; // Player automatically stands after busting
                    enableBettingButtons(startGameButton);
                    gameStarted = false;
                    playerStanding = false;
                    dealerDone = false;
                    if (credits <= 0) {
                        gameStatus.setText("Game over! You have no funds left.");
                        disableBettingButtons(bet100Button, clearBetButton);
                    }
                }
            }
        });

        standButton.setOnAction(event -> {
            if (gameStarted && !playerStanding) {
                playerStanding = true;
                // Play dealer's hand
                while (calculateHandValue(dealerCards) < 17) {
                    dealerCards.add(drawCard());
                }
                dealerDone = true;
                updateGameStatus(gameStatus);
                // Check if player wins
                if (calculateHandValue(playerCards) > calculateHandValue(dealerCards)
                        || calculateHandValue(dealerCards) > 21) {
                    credits += betAmount * 2; // Double the bet amount
                } else if (calculateHandValue(playerCards) == calculateHandValue(dealerCards)) {
                    credits += betAmount; // Return the bet amount to the player
                }
                betAmount = 0;
                updateBetAndCreditsLabels(betLabel, creditsLabel);
                enableBettingButtons(startGameButton);
                gameStarted = false;
                playerStanding = false;
                dealerDone = false;
                if (credits <= 0) {
                    gameStatus.setText("Game over! You have no funds left.");
                    disableBettingButtons(bet100Button, clearBetButton);
                }
            }
        });

        // Add buttons to an HBox for layout
        HBox buttonBox = new HBox(10, bet100Button, clearBetButton, startGameButton, hitButton, standButton);
        buttonBox.setPadding(new Insets(10));
        buttonBox.setBackground(new Background(new BackgroundFill(Color.DARKGREEN, CornerRadii.EMPTY, Insets.EMPTY)));

        // Add all components to the root layout
        root.getChildren().addAll(gameStatus, creditsLabel, betText, betLabel, buttonBox);

        // Create the scene and set it on the stage
        Scene scene = new Scene(root);
        primaryStage.setTitle("Blackjack Game");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    private void updateBetAndCreditsLabels(Label betLabel, Label creditsLabel) {
        betLabel.setText("Current Bet: " + betAmount);
        creditsLabel.setText("Credits: " + credits);
    }

    private void startNewHand() {
        playerCards.clear();
        dealerCards.clear();
        playerCards.add(drawCard());
        playerCards.add(drawCard());
        dealerCards.add(drawCard());
        dealerCards.add(drawCard());
    }

    private String drawCard() {
        Random random = new Random();
        int value = random.nextInt(13) + 1;
        String[] suits = {"hearts", "diamonds", "clubs", "spades"};
        String[] ranks = {"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};
        return ranks[value - 1] + " of " + suits[random.nextInt(4)];
    }

    private void updateGameStatus(Text gameStatus) {
        if (gameStarted) {
            StringBuilder playerCardsText = new StringBuilder("Player Cards: ");
            for (int i = 0; i < playerCards.size(); i++) {
                playerCardsText.append(playerCards.get(i));
                if (i < playerCards.size() - 1) {
                    playerCardsText.append(", ");
                }
            }
            StringBuilder dealerCardsText = new StringBuilder("Dealer Cards: ");
            dealerCardsText.append(dealerCards.get(0)); // Only show the first card
            dealerCardsText.append(", [Hidden]"); // Hide the second card

            if (playerStanding || dealerDone) {
                dealerCardsText = new StringBuilder("Dealer Cards: ");
                for (int i = 0; i < dealerCards.size(); i++) {
                    dealerCardsText.append(dealerCards.get(i));
                    if (i < dealerCards.size() - 1) {
                        dealerCardsText.append(", ");
                    }
                }
            }

            gameStatus.setText(playerCardsText.toString() + "\n" + dealerCardsText.toString());
        } else {
            gameStatus.setText("Welcome to Blackjack!");
        }
    }

    private void disableBettingButtons(Button... buttons) {
        for (Button button : buttons) {
            button.setDisable(true);
        }
    }

    private void enableBettingButtons(Button... buttons) {
        for (Button button : buttons) {
            button.setDisable(false);
        }
    }

    private int calculateHandValue(List<String> cards) {
        int value = 0;
        int aceCount = 0;
        for (String card : cards) {
            String[] cardInfo = card.split(" ");
            String rank = cardInfo[0];
            if (rank.equals("ace")) {
                value += 11;
                aceCount++;
            } else if (rank.equals("jack") || rank.equals("queen") || rank.equals("king")) {
                value += 10;
            } else {
                value += Integer.parseInt(rank);
            }
        }
        // Adjust ace value if necessary
        while (value > 21 && aceCount > 0) {
            value -= 10;
            aceCount--;
        }
        return value;
    }

    public static void main(String[] args) {
        launch(args);
    }
}