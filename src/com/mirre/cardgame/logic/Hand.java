package com.mirre.cardgame.logic;

import java.util.ArrayList;
import java.util.List;

public class Hand {

	private final CardCombination2 combination;
	private final List<Card> cards, otherCards;
	
	
	public Hand(CardCombination2 combination, List<Card> cards, List<Card> other){
		this.combination = combination;
		this.cards = cards;
		this.otherCards = other;
	}
	
	public Hand(CardCombination2 combination, Card[] cards, Card... other){
		this.combination = combination;
		this.cards = new ArrayList<Card>();
		for(Card c : cards){
			this.cards.add(c);
		}
		this.otherCards = new ArrayList<Card>();
		for(Card c : other){
			this.otherCards.add(c);
		}
		
	}
	
	public HandResult compare(Hand other){
		HandResult result = combinationCheck(other.combination);
		if(result.equals(HandResult.EQUAL)){
			
		}
		return result;
	}
	
	private HandResult combinationCheck(CardCombination2 combination2){
		return HandResult.compareComb(combination.ordinal(), combination2.ordinal());
	}

	public CardCombination2 getCombination() {
		return combination;
	}

	public List<Card> getCards() {
		return cards;
	}

	public List<Card> getOtherCards() {
		return otherCards;
	}
}
