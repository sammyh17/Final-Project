package src.classFiles.Blackjack;

public abstract class Dealer2 {

   private Card[] deck;
   private static Deck playDeck;
   private int nextCardIndex;
   static int playerTotal = 0;
   static int dealerTotal = 0;



   public Dealer2() 
   	{
    resetDeck();
   	}


   
   private void resetDeck() 
   	{
    playDeck = new Deck(52);
    playDeck.shuffle(deck);
   	}

   public static Card dealCard() 
   	{
    return Deck.drawCard();
   	}


// Check if the deck is empty
   public boolean isDeckEmpty() 
   	{
    return nextCardIndex >= deck.length;
   	}
   
// Determine the winner
   public static Boolean didPlayerWin() 	
   		{
		   if (dealerTotal > 21 || playerTotal > dealerTotal) 
		   		{
			   return true;
		   		} 
		   else if (playerTotal < dealerTotal) 
		   		{   
			   	return false;
		   		} 
		   else 
	       		{
			   return null;
	       		}
   		}
   
   
   // Deal a card to the player
   public static Card dealCardPlayer()
	   	{
		Card playerCard1 = Deck.drawCard();
		return playerCard1;
	   	}
   
   
   public static Card dealerDraw()
	   	{
		Card Dealercard = Deck.drawCard();
	    System.out.println("Dealer dealt card: " + Dealercard);
	    return Dealercard;
	   	}
   
   
}//End Class