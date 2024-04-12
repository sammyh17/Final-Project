package src.classFiles.Blackjack;
import java.util.ArrayList;

/**
 * Class that creates a deck objects and fills it with cards.
 */
public class Deck
{
	private static ArrayList<Card> deck;
	final static String[] suits = {"Clubs" , "Diamonds" , "Hearts" , "Spades"};
	final static String[] ranks = {"2","3","4","5","6","7","8","9","10","Jack","Queen","King","Ace"};

	// Constructor

	  public Deck()
	  	{
		this(52);
	  	}

	  public Deck(int numOfCards)
	    {
	    deck = new ArrayList<>(numOfCards);
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

	  // Drawcard

	  /*
	   * Method for drawing the top card in the deck
	   * @return card
	   */
	  public static Card drawCard()
	  	{
		return deck.get(0);
	  	}

	public static int size() {
		return size();
	}

}// END CLASS