package com.mirre.cardgame.view;

import com.mirre.cardgame.logic.Card;
import com.mirre.cardgame.logic.CardCombination;
import com.mirre.cardgame.logic.Deck;
import com.mirre.cardgame.logic.HandResult;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class GameViewController {

	@FXML
	private Label mineLabel, otherLabel, winLabel;
	
	@FXML
	private Button startButton;
	
	public GameViewController(){}
	
	@FXML
	public void initialize(){
		newMatch();
	}
	
	@FXML
	public void newMatch(){
		Deck deck = new Deck();
		Card[] myCards = deck.getCards(5);
		Card[] otherCards = deck.getCards(5);
		
		String s = "You: ";
		for(Card c : myCards){
			s += c.toString() + ", ";
		}
		mineLabel.setText(s.substring(0, s.length() - 2));
		
		s = "Other: ";
		for(Card c : otherCards){
			s += c.toString() + ", ";
		}
		otherLabel.setText(s.substring(0, s.length() - 2));
		
		CardCombination mine = CardCombination.findCombination(myCards);
		CardCombination other = CardCombination.findCombination(otherCards);
		
		HandResult combResult = mine.equal(other);
		HandResult result = null;
		if(combResult.equals(HandResult.EQUAL)){
			int m = mine.check(myCards);
			int n = other.check(otherCards);
			result = HandResult.compareHand(m, n);
		}else{
			result = combResult;
		}
		if(result.equals(HandResult.EQUAL)){
			result = HandResult.compareScore(myCards, otherCards);
		}
		
		winLabel.setText("You " + result.toString().toLowerCase() + " with " +  mine.toString().toLowerCase() + " against " + other.toString().toLowerCase() + ".");
	}
}
