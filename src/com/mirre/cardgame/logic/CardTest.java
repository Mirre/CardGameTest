package com.mirre.cardgame.logic;


public class CardTest {

	public static void main(String[] args) {
		Deck deck = new Deck();
		Card[] myCards = deck.getCards(2);
		Card[] otherCards = deck.getCards(2);
		Card[] river = deck.getCards(5);
		
		
		System.out.println("My Cards: ");
		for(Card c : myCards){
			System.out.println(c.toString());
		}
		
		System.out.println("Other Cards: ");
		for(Card c : otherCards){
			System.out.println(c.toString());
		}
		
		System.out.println("River: ");
		for(Card c : river){
			System.out.println(c.toString());
		}
		
		Card[] merged = Card.mergeHand(myCards, river);
		Card[] mergedOther = Card.mergeHand(otherCards, river);
		
		System.out.println("Merged: ");
		for(Card c : merged){
			System.out.println(c.toString());
		}
		
		System.out.println("Merged Other: ");
		for(Card c : mergedOther){
			System.out.println(c.toString());
		}
		CardCombination mine = CardCombination.findCombination(merged);
		CardCombination other = CardCombination.findCombination(mergedOther);
		
		System.out.println("My Result: " + mine);
		System.out.println("Other Result: " + other);
		HandResult combResult = mine.equal(other);
		HandResult result = null;
		if(combResult.equals(HandResult.EQUAL)){
			int m = mine.check(merged);
			int n = other.check(mergedOther);
			result = HandResult.compareHand(m, n);
		}else{
			result = combResult;
		}
		
		
		System.out.println("Hand Result: You " + result);
		if(result.equals(HandResult.EQUAL)){
			System.out.println("Equal Result: " + HandResult.compareScore(merged, mergedOther));
		}
	}

}
