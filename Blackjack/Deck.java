package Blackjack;
import java.util.ArrayList;
import java.util.Scanner;

public class Deck 
{
	private ArrayList<Card> deck;
	final static String[] suits = {"Clubs" , "Diamonds" , "Hearts" , "Spades"};
	final static String[] ranks = {"2","3","4","5","6","7","8","9","10","Jack","Queen","King","Ace"};

	// Constructor

	  public Deck(int numOfCards)
	    {
	    deck = new ArrayList<Card>(numOfCards);
	    for(int i = 0; i < deck.size(); i++)
	      {
	      int cardVal = 0;
	      String cardSuit = suits[0];
	      String cardRank = ranks[0];

	   
	      deck.add(new Card(cardVal,cardSuit,cardRank));
	      }
	    }

	// Shuffle

	  /**
	   * Method for randomizing the cards in the deck
	   * @param deck
	   */
	  public void shuffle(Card[] deck)
	    {
	    for (int i = 0; i < 1000; i++)
	      {
	      int j = (int)(Math.random()*deck.length);
	      int k = (int)(Math.random()*deck.length);
	      Card tmpCard = deck[j];
	      deck[j] = deck[k];
	      deck[k] = tmpCard;
	      }
	    }	
	
}// END CLASS