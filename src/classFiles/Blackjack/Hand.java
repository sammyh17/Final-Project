package src.classFiles.Blackjack;

import java.util.ArrayList;
import java.util.List;



	public class Hand {
	private Hand hand;



	public Hand() {
	this.hand = new Hand();

   }



   public void takeCard(Deck deck) {
	   Card card1 = deck.dealCard();
       hand.addCard(card1);

   }

   //BELOW METHOD SHOULD BE IN PLAYER CLASS

   public void printHandContents() {

       // Return all cards except the first one (face-down card)

       System.out.print(this.hand);

       

       //add all cards in the parameter hand to the arraylist (visibleHand)

       //print all the cards (using for loop to traverse arraylist)


   }

   

   public Hand getHand() {

    return this.hand;

   }

}




