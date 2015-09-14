package com.mirre.cardgame.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {

	private static final int SHUFFLE_TIMES = 3;
	
	private final List<Card> ownedCards = new ArrayList<Card>();
	private final List<Card> trueDeck = new ArrayList<Card>();
	
	protected enum CardType {
		ACE(14),TWO(2),THREE(3),FOUR(4),FIVE(5),SIX(6),SEVEN(7),EIGHT(8),NINE(9),TEN(10),JACK(11),QUEEN(12),KING(13);
		
		private final int score;
		
		private CardType(int score){
			this.score = score;
		}

		public int getScore() {
			return score;
		}
	}
	
	protected enum CardColor{
		SPADE,HEART,DIAMOND,CLUB;
	}
	
	public Deck(){
		for(CardColor color : CardColor.values()){
			for(CardType type : CardType.values()){
				ownedCards.add(new Card(this, type, color));
			}
		}
		this.trueDeck.addAll(ownedCards);
		
		for(int i = 0; i < SHUFFLE_TIMES; i++){
			shuffle();
		}
	}
	
	private final Random random = new Random();
	
	private void shuffle(){
		trueDeck.sort((card1, card2) -> random.nextBoolean() ? 1 : -1);
	}
	
	public Card[] getCards(int amount){
		Card[] cards = new Card[amount];
		for(int i = 0; i < amount ; i++){
			cards[i] = trueDeck.get(0);
			trueDeck.remove(0);
		}
		return cards;
	}
	
	
}
