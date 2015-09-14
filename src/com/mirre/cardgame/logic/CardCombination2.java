package com.mirre.cardgame.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.mirre.cardgame.logic.Deck.CardColor;
import com.mirre.cardgame.logic.Deck.CardType;

public enum CardCombination2 {
	STRAIGHT_FLUSH(0,0) {
		@Override
		public Optional<Hand> check(Card... cards) {
			return Optional.empty();
		}
	},
	FOUR_OF_KIND(1,4),
	FULL_HOUSE(2,5),
	FLUSH(0,0) {
		@Override
		public Optional<Hand> check(Card... cards) {
			List<Card> list = new ArrayList<Card>();
			for(CardColor color : CardColor.values()){
				for(Card c : cards){
					if(c.getColor().equals(color)){
						list.add(c);
					}
				}
				if(list.size() < 5){
					list.clear();
					break;
				}
			}
			if(list.isEmpty()){
				return Optional.empty();
			}
			list.sort((card1, card2) -> card1.getType().getScore() - card2.getType().getScore());
			
			List<Card> otherList = new ArrayList<Card>();
			for(Card c : cards){
				otherList.add(c);
			}
			otherList.remove(list.subList(0, 4));
			
			return Optional.of(new Hand(this, list.subList(0, 4), otherList));
		}
	},
	STRAIGHT(0,0) {
		@Override
		public Optional<Hand> check(Card... cards) {
			return Optional.empty();
		}
	},
	THREE_OF_KIND(1, 3),
	TWO_PAIR(2, 4),
	ONE_PAIR(1, 2),
	HIGH_CARD(0, 0) {
		@Override
		public Optional<Hand> check(Card... cards) {
			Card highestCard = cards[0];
			for(int i = 0; i < cards.length ; i++){
				if(cards[i].getType().getScore() > highestCard.getType().getScore()){
					highestCard = cards[i];
				}
			}
			List<Card> otherList = new ArrayList<Card>();
			for(Card c : cards){
				otherList.add(c);
			}
			otherList.remove(highestCard);
			Hand hand = new Hand(this, new Card[]{ highestCard }, otherList.toArray(new Card[otherList.size()]));
			return Optional.of(hand);
		}
	};
	
	private int amountOfTypes, length;
	
	private CardCombination2(int amountOfTypes, int length){
		this.amountOfTypes = amountOfTypes;
		this.length = length;
	}
	
	public Optional<Hand> check(Card... cards){
		List<Card> list = this.getAllMulti(cards);
		List<Card> otherList = new ArrayList<Card>();
		for(Card c : cards){
			otherList.add(c);
		}
		otherList.removeAll(list);
		return list.isEmpty() ? Optional.empty() : Optional.of(new Hand(this, list, otherList));
	}
	
	protected List<Card> getAllMulti(Card... cards){
		List<Card> list = new ArrayList<Card>();
		List<CardType> blackList = new ArrayList<CardType>();
		for(Card c : cards){
			if(!blackList.contains(c.getType())){
				int i = 1;
				for(Card c2 : cards){
					if(c.getType().equals(c2.getType())){
						list.add(c2);
						i++;
					}
				}if(i != 1){
					blackList.add(c.getType());
				}
			}
		}
		if(list.size() == this.length && blackList.size() == this.amountOfTypes){
			return list;
		}
		return new ArrayList<Card>();
	}
	
	public static CardCombination2 findCombination(Card... cards){
		for(CardCombination2 c : CardCombination2.values()){
			if(c.check(cards).isPresent()){
				return c;
			}
		}
		throw new IllegalStateException("Failed");
		
	}
	
	private Card[] amountOf(CardType type, List<Card> cards){
		return cards.stream().filter((card) -> card.getType().equals(type)).toArray(Card[]::new);
	}
	
	public static void main(String[] args) {
		Deck deck = new Deck();
		Card first = new Card(deck, CardType.ACE, CardColor.DIAMOND);
		Card second = new Card(deck, CardType.ACE, CardColor.CLUB);
		Card third = new Card(deck, CardType.ACE, CardColor.HEART);
		Card four = new Card(deck, CardType.ACE, CardColor.HEART);
		
		CardCombination2 mine = CardCombination2.findCombination(first, second, third, four);
		
		System.out.println("My Result: " + mine);
		
		first = new Card(deck, CardType.ACE, CardColor.DIAMOND);
		second = new Card(deck, CardType.ACE, CardColor.CLUB);
		third = new Card(deck, CardType.KING, CardColor.HEART);
		four = new Card(deck, CardType.KING, CardColor.CLUB);
		
		mine = CardCombination2.findCombination(first, second, third, four);
		
		System.out.println("My Result: " + mine);
		
		first = new Card(deck, CardType.ACE, CardColor.SPADE);
		second = new Card(deck, CardType.ACE, CardColor.SPADE);
		third = new Card(deck, CardType.JACK, CardColor.SPADE);
		four = new Card(deck, CardType.KING, CardColor.SPADE);
		Card five = new Card(deck, CardType.KING, CardColor.SPADE);
		
		mine = CardCombination2.findCombination(first, second, third, four, five);
		
		System.out.println("My Result: " + mine);
	}
}
