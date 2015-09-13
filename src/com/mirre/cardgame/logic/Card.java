package com.mirre.cardgame.logic;

import java.util.Optional;

import com.mirre.cardgame.logic.Deck.CardColor;
import com.mirre.cardgame.logic.Deck.CardType;

public class Card {

	private final Deck owningDeck;
	private final CardType type;
	private final CardColor color;
	
	
	public Card(Deck deck, CardType type, CardColor color){
		this.owningDeck = deck;
		this.type = type;
		this.color = color;
	}
	
	public Deck getOwningDeck() {
		return owningDeck;
	}

	public CardType getType() {
		return type;
	}

	public Optional<Boolean> isHigher(Card card){
		int first = this.type.ordinal() == 0 ? 666 : this.type.ordinal();
		int second = card.type.ordinal() == 0 ? 666 : card.type.ordinal();
		boolean b = first > second ? true : false;
		return first == second ? Optional.empty() : Optional.of(b);
	}

	public CardColor getColor() {
		return color;
	}

	@Override
	public String toString() {
		return color + " " + type;
	}
	
	public static Card[] mergeHand(Card[] hand, Card... cards){
		Card[] merged = new Card[hand.length + cards.length];
		for(int i = 0 ; i < hand.length ; i++){
			merged[i] = hand[i];
		}
		for(int i = hand.length ; i < hand.length + cards.length ; i++){
			merged[i] = cards[i - hand.length];
		}
		return merged;
	}
}
