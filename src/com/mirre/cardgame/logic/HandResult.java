package com.mirre.cardgame.logic;


public enum HandResult {
	WON, EQUAL, LOST;
	
	/*
	 * @parameters
	 * i == j = equal
	 * i < j = LOST
	 * i > j = WON
	 */
	public static HandResult compareHand(int i, int j){
		if(i == j){
			return HandResult.EQUAL;
		}else if(i > j){
			return HandResult.WON;
		}
		return HandResult.LOST;
	}
	
	public static HandResult compareScore(Card[] mine, Card[] other){
		return HandResult.compareHand(totalScore(mine), totalScore(other));
	}
	
	private static int totalScore(Card... cards){
		int result = 0;
		for(Card c : cards){
			result += c.getType().ordinal() == 0 ? 13 : c.getType().ordinal();
		}
		return result;
	}
	
	/*
	 * @parameters
	 * i == j = equal
	 * i < j = WON
	 * i > j = false
	 */
	public static HandResult compareComb(int i, int j){
		if(i == j){
			return HandResult.EQUAL;
		}else if(i < j){
			return HandResult.WON;
		}
		return HandResult.LOST;
	}
}
