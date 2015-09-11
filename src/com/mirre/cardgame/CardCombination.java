package com.mirre.cardgame;

import com.mirre.cardgame.Deck.CardType;

/*
 * 0 = false
 * 1+ = true, 100 means that it will require additional checks to see which is higher.
 */
public enum CardCombination {
	STRAIGHT_FLUSH {
		@Override
		public int check(Card... cards) {
			int flush = CardCombination.FLUSH.check(cards);
			if(flush == 0){
				return 0;
			}
			return CardCombination.STRAIGHT.check(cards);
		}
	},
	FOUR_OF_KIND {
		@Override
		public int check(Card... cards) {
			for(Card card : cards){
				if(amountOf(card.getType(), cards) == 4){
					return 100;
				}
			}
			return 0;
		}
	},
	FULL_HOUSE {
		@Override
		public int check(Card... cards) {
			// TODO Auto-generated method stub
			return 0;
		}
	},
	FLUSH {
		@Override
		public int check(Card... cards) {
			return 0;
		}
	},
	STRAIGHT {
		@Override
		public int check(Card... cards) {
			// TODO Auto-generated method stub
			return 0;
		}
	},
	THREE_OF_KIND {
		@Override
		public int check(Card... cards) {
			// TODO Auto-generated method stub
			return 0;
		}
	},
	TWO_PAIR {
		@Override
		public int check(Card... cards) {
			// TODO Auto-generated method stub
			return 0;
		}
	},
	ONE_PAIR {
		@Override
		public int check(Card... cards) {
			// TODO Auto-generated method stub
			return 0;
		}
	},
	HIGH_CARD {
		@Override
		public int check(Card... cards) {
			int highestOrdinal = -1;
			for(Card card : cards){
				if(card.getType().ordinal() == 0)
					return 666;
				else if(card.getType().ordinal() > highestOrdinal){
					highestOrdinal = card.getType().ordinal();
				}
			}
			return highestOrdinal;
		}
	};
	

	 
	private CardCombination(){}
	
	public abstract int check(Card... cards);
	
	private static int amountOf(CardType type, Card... cards){
		int i = 0;
		for(Card card : cards){
			if(card.getType().equals(type)){
				i++;
			}
		}
		return i;
	}
	
}
