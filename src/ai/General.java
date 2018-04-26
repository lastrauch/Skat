package ai;

import java.util.ArrayList;
import java.util.List;

import logic.Card;
import logic.Number;
import logic.PlayMode;
import logic.LogicException;

public class General {
	
	/* This is a static class to implement methods which are used through different Bot-difficulties.
	 * Available Methods are:
	 * playRandomCard(AIController) : int					//Returns the index of a playable card on the hand of the bot.
	 * getHighestPossibleBet(AIController, PlayMode) : int	//Returns the highest possible bet, with the current hand of the bot
	 */
	
	public static int playRandomCard(AIController controller){
		List<Card> cards = controller.getBot().getHand();
	    List<Card> possibleCards = new ArrayList<Card>();
	    if(controller.getCurrentTrick().size() > 0){
	        for(int i=0; i<cards.size(); i++){
	            try {
	                if(controller.getBot().checkIfCardPossible(cards.get(i), controller.getCurrentTrick().get(0))){
	                    possibleCards.add(cards.get(i));
	                }
	            } catch (LogicException e) {
	                e.printStackTrace();
	            }
	        }
	    }else{
	        possibleCards = cards;
	    }
	    
	    int rnd = (int) (Math.random() * possibleCards.size());
	    Card playCard = possibleCards.get(rnd);
	    int index = cards.indexOf(playCard);
	    
	    return index;
	}
	
	public static int getHighestPossibleBet(AIController controller, PlayMode playMode){
		if(playMode == PlayMode.NULL){
			return 23;
		}else{
			int gameLevel = General.getGameLevel(controller);
			if(playMode == PlayMode.GRAND){
				return 24*gameLevel;
			}else{
				return 12*gameLevel;
			}
		}
	}
	
	public static int getGameLevel(AIController controller){
		List<Card> cards = controller.getBot().getHand();
		//Determine the Jacks
		boolean[] jacks = new boolean[4];
		for(int i=0; i<cards.size(); i++){
			if(cards.get(i).getNumber() == Number.JACK){
				switch(cards.get(i).getColour()){
					case CLUBS: jacks[0] = true; break;
					case SPADES: jacks[1] = true; break;
					case HEARTS: jacks[2] = true; break;
					case DIAMONDS: jacks[3] = true; break;
				}
			}
		}
		
		//Determine the gameLevel 
		int gameLevel = 1;
		boolean with = jacks[0];
		for(int i=1; i<4; i++){
			if(jacks[0] == with){
				gameLevel++;
			}
		}
		return gameLevel;
	}

}
