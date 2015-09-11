package com.mirre.cardgame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {

	private static final int SHUFFLE_TIMES = 3;
	
	private final List<Card> ownedCards = new ArrayList<Card>();
	private final List<Card> trueDeck = new ArrayList<Card>();
	
	protected enum CardType {
		ACE,TWO,THREE,FOUR,FIVE,SIX,SEVEN,EIGHT,NINE,TEN,JACK,QUEEN,KING;
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
		trueDeck.sort((card1, card2) -> random.nextInt(2));
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
