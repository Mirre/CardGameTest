package com.mirre.cardgame.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mirre.cardgame.logic.Deck.CardColor;
import com.mirre.cardgame.logic.Deck.CardType;

/*
 * 0 = false
 * 1+ = true
 */
public enum CardCombination {
	STRAIGHT_FLUSH {
		@Override
		public int check(Card... cards) {
			int flush = CardCombination.FLUSH.check(cards);
			return flush == 0 ? 0 : CardCombination.STRAIGHT.check(cards);
		}
	},
	FOUR_OF_KIND {
		@Override
		public int check(Card... cards) {
			for(Card card : cards){
				if(amountOf(card.getType(), cards) == 4){
					return card.getType().ordinal() == 0 ? 13 : card.getType().ordinal();
				}
			}
			return 0;
		}
	},
	FULL_HOUSE {
		@Override
		public int check(Card... cards) {
			List<CardType> checkedTypes = new ArrayList<CardType>();
			int i = 0;
			int highestThree = 0;
			for(Card c : cards){
				if(!checkedTypes.contains(c.getType())){
					int amount = amountOf(c.getType());
					if(amount == 2){
						i++;
						
					}else if(amount == 3){
						i++;
						int j = c.getType().ordinal() == 0 ? 13 : c.getType().ordinal();
						if(j > highestThree){
							highestThree = j;
						}
					}if(i == 2) 
						return highestThree;
				}
				checkedTypes.add(c.getType());
			}
			return i == 2 ? highestThree : 0;
		}

	},
	FLUSH {
		@Override
		public int check(Card... cards) {
			return flushCheck(cards);
		}

	},
	/*
	 * Returns the total ordinal of the STRAIGHT. ACE ordinal when used in a Royal Straight means 13.
	 * False if 0
	 */
	STRAIGHT {
		@Override
		public int check(Card... cards) {
			int[] integers = new int[cards.length];
			for(int i = 0 ; i < cards.length ; i++){
				integers[i] = cards[i].getType().ordinal();
			}
			Arrays.sort(integers);
			int j = 0;
			for(int i = 0; i < integers.length; i++){
				if(i + 1 == integers.length){
					if(integers[0] == 0){
						j++;
					}
				}else if(integers[i] == integers[i+1] - 1){
					j++;
				}
			}
			if(j >= 5){		
				int result = 0;
				for(int i = integers.length - 1 ; i > -1 ; i--){
					if(integers[i] == 12 && integers[0] == 0){
						result += 13;
					}
					result += integers[i];
					if(i == integers.length - 5){
						return result;
					}
				}
			}
			return 0;
		}

	},
	THREE_OF_KIND {
		@Override
		public int check(Card... cards) {
			int highestKind = 0;
			List<CardType> usedTypes = new ArrayList<CardType>();
			for(Card card : cards){
				if(!usedTypes.contains(card.getType()) && amountOf(card.getType(), cards) == 3){
					int i = card.getType().ordinal() == 0 ? 13 : card.getType().ordinal();
					if(i > highestKind){
						highestKind = i;
					}
				}
				usedTypes.add(card.getType());
			}
			return highestKind;
		}

	},
	/*
	 * Three pair is 2 pair. You cannot have three pairs as you only take the 5 best cards you have into consideration.
	 */
	TWO_PAIR {
		@Override
		public int check(Card... cards) {
			return highestPair(2, cards);
		}

	},
	ONE_PAIR {
		@Override
		public int check(Card... cards) {
			return highestPair(1, cards);
		}

	},
	/*
	 * Returns the highest ordinal. 13 incase of ACE.
	 */
	HIGH_CARD {
		@Override
		public int check(Card... cards) {
			int highestOrdinal = -1;
			for(Card card : cards){
				if(card.getType().ordinal() == 0)
					return 13;
				else if(card.getType().ordinal() > highestOrdinal){
					highestOrdinal = card.getType().ordinal();
				}
			}
			return highestOrdinal;
		}

	};
	

	 
	private CardCombination(){}
	
	public abstract int check(Card... cards);
	
	public HandResult equal(CardCombination c){
		return HandResult.compareComb(this.ordinal(), c.ordinal());
	}
	
	private static int amountOf(CardType type, Card... cards){
		int i = 0;
		for(Card card : cards){
			if(card.getType().equals(type)){
				i++;
			}
		}
		return i;
	}
	
	private static int highestPair(int level, Card... cards){
		int highestPair = -1;
		int pairs = 0;
		List<CardType> usedTypes = new ArrayList<CardType>();
		for(Card card : cards){
			if(!usedTypes.contains(card.getType()) && amountOf(card.getType(), cards) == 2){
				pairs++;
				int score = card.getType().ordinal() == 0 ? 13 : card.getType().ordinal();
				if(score > highestPair)
					highestPair = score;
			}
			usedTypes.add(card.getType());
		}
		if(level == 2)
			return pairs >= level ? highestPair : 0; 
		return pairs == level ? highestPair : 0; 
	}
	
	private static int flushCheck(Card... cards){
		for(CardColor color : CardColor.values()){
			int i = 0;
			int result = 0;
			for(Card c : cards){
				if(c.getColor().equals(color)){
					i++;
					result += c.getType().ordinal() == 0 ? 13 : c.getType().ordinal();
				}
			}
			if(i >= 5){
				return result;
			}
		}
		
		return 0;
	}
	
	public static CardCombination findCombination(Card... cards){
		for(CardCombination c : CardCombination.values()){
			if(c.check(cards) > 0){
				return c;
			}
		}
		throw new IllegalStateException("Failed");
		
	}
}
