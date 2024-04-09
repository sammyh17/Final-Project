package src.classFiles.Blackjack;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dealer2 extends Application {
    private List<Integer> deck;
    private int nextCardIndex;
    private Label cardLabel;

    @Override
    public void start(Stage primaryStage) {
        deck = new ArrayList<>();
        resetDeck();

        VBox root = new VBox();
        cardLabel = new Label();

        root.getChildren().add(cardLabel);

        dealCardAndUpdateLabel();

        Scene scene = new Scene(root, 200, 200);
        primaryStage.setTitle("Dealer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void resetDeck() {
        deck.clear();
        for (int i = 1; i <= 10; i++) {
            deck.add(i);
        }
        shuffleDeck();
        nextCardIndex = 0;
    }

    private void shuffleDeck() {
        Collections.shuffle(deck);
    }

    private void dealCardAndUpdateLabel() {
        if (nextCardIndex >= deck.size()) {
            resetDeck();
        }
        int card = deck.get(nextCardIndex++);
        cardLabel.setText("Dealt card: " + card);
    }

    public static void main(String[] args) {
        launch(args);
    }
}