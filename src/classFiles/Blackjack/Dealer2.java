package src.classFiles.Blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dealer2 {
    private List<Integer> deck;
    private int nextCardIndex;

    public Dealer2() {
        deck = new ArrayList<>();
        resetDeck();
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

    public int dealCard() {
        if (nextCardIndex >= deck.size()) {
            resetDeck();
        }
        return deck.get(nextCardIndex++);
    }

    public boolean isDeckEmpty() {
        return nextCardIndex >= deck.size();
    }

    public static void main(String[] args) {
        Dealer2 dealer = new Dealer2();

        // Deal a few cards to demonstrate
        for (int i = 0; i < 10; i++) {
            int card = dealer.dealCard();
            System.out.println("Dealt card: " + card);
        }

        // Check if the deck is empty
        if (dealer.isDeckEmpty()) {
            System.out.println("The deck is empty.");
        } else {
            System.out.println("There are cards remaining in the deck.");
        }
    }
}