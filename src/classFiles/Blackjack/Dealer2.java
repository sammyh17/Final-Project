package src.classFiles.Blackjack;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Dealer2 {
    private List<Integer> deck;
    private int nextCardIndex;

    public Dealer2() {
        resetDeck();
    }

    private void resetDeck() {
        deck = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            for (int j = 0; j < 4; j++) {
                deck.add(i);
            }
        }
        Collections.shuffle(deck);
        nextCardIndex = 0;
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
        int playerTotal = 0;
        int dealerTotal = 0;
        Scanner scanner = new Scanner(System.in);

        // Deal one card to the player
        int playerCard1 = dealer.dealCard();
        playerTotal += playerCard1;
        System.out.println("Player dealt card: " + playerCard1);

        // Deal one card to the dealer (mysterious)
        int dealerCard1 = dealer.dealCard();
        System.out.println("Dealer dealt card: ??");

        // Deal another card to the player
        int playerCard2 = dealer.dealCard();
        playerTotal += playerCard2;
        System.out.println("Player dealt card: " + playerCard2);

        // Deal a card to the dealer with number showing
        int dealerCard2 = dealer.dealCard();
        dealerTotal += dealerCard2;
        System.out.println("Dealer dealt card: " + dealerCard2);

        // Player's turn
        while (true) {
            System.out.println("Player total: " + playerTotal);
            if (playerTotal == 21) {
                System.out.println("Player has 21! Player wins!");
                return; // End the game
            } else if (playerTotal > 21) {
                System.out.println("Player busted! Total: " + playerTotal);
                return; // End the game
            }
            System.out.print("Do you want to (h)it or (s)tand? ");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("h")) {
                int card = dealer.dealCard();
                System.out.println("Player dealt card: " + card);
                playerTotal += card;
            } else if (choice.equalsIgnoreCase("s")) {
                break; // Player stands, end loop
            } else {
                System.out.println("Invalid choice. Please enter 'h' or 's'.");
            }
        }

        // Dealer's turn (reveal the mysterious card)
        System.out.println("Dealer's mysterious card was: " + dealerCard1);
        dealerTotal += dealerCard1;

        while (dealerTotal < 17) {
            int card = dealer.dealCard();
            System.out.println("Dealer dealt card: " + card);
            dealerTotal += card;
        }

        // Determine the winner
        if (dealerTotal > 21 || playerTotal > dealerTotal) {
            System.out.println("Player wins! Player total: " + playerTotal + ", Dealer total: " + dealerTotal);
        } else if (playerTotal < dealerTotal) {
            System.out.println("Dealer wins! Player total: " + playerTotal + ", Dealer total: " + dealerTotal);
        } else {
            System.out.println("It's a tie! Player total: " + playerTotal + ", Dealer total: " + dealerTotal);
        }
    }
}