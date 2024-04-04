package classFiles.Blackjack;

import java.util.ArrayList;

import ajWorks.Deck;

/**
 * Class that creates card objects and sets them up.
 */
public class Card extends Deck 
{


	private int myValue;
	private String mySuit;
	private String myRank;

	public Card(int val, String suit, String rank)
		{
	    myValue = val;
	    mySuit = suit;
	    myRank = rank;
	    }
	
	
	/**
	   * Method that returns the cards Suit
	   *
	   * @return mySuit
	   */
	  public String getSuit()
	    {
	    return mySuit;
	    }

	  /**
	   * Method that returns the cards Rank
	   *
	   * @return myRank
	   */
	  public String getRank()
	    {
	    return myRank;
	    }

	  /**
	   * Method that returns the cards Value
	   *
	   * @return myValue
	   */
	  public int getVal()
	    {
	    return myValue;
	    }

	   /**
	    * Method that returns a string of the cards data
	    *
	    * @return cardData
	    */
	  public String toString()
	    {
	    String data = myValue + "  " + mySuit + "  " + myRank;
	    return data;
	    }
	  
	  
	  /**
	   * Method that compares the values of two cards. Card funtionality method
	   *
	   * @param cardIn, the Card which you want to compare to another card.
	   * @return +1 if this card is greater than otherCard , 0 if the cards are of equal value , and -1 if the current card is less than otherCard.
	   */
	  public int compareCard(Card otherCard)
	    {
	    if (this.getVal() > otherCard.getVal())
	      return 1;
	    else if(this.getVal() < otherCard.getVal())
	      return -1;
	    else return 0;
	    }

}
