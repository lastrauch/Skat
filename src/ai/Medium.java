package ai;

import java.util.List;

import logic.Card;
import logic.Colour;
import logic.PlayMode;
import logic.Number;

public class Medium {
  
	private class SinglePlay{
		private PlayMode playMode;
		private Colour colour;
		
		public SinglePlay(PlayMode playMode, Colour colour){
			this.playMode = playMode;
			this.colour = colour;
		}
	}
  
	
	
	public static int playCard(AIController controller){
    
    
		return 0;
	}
  
	public static boolean setBet(AIController controller, int bet){
		if(Medium.calculateBet(controller) >= bet){
			return true;
		}else{
			return false;
		}
	}
  
	
	private static SinglePlay playSingle(AIController controller){
		boolean wantsGrand = false;
		boolean wantsSuit = false;
		boolean wantsNull = false;
		int certGrand = 0;	//Certainty to play Grand, integer in [0;10]
		int certSuit = 0;	//Certainty to play Suit, integer in [0;10]
		int certNull = 0;	//Certainty to play Null, integer in [0;10]
		
		//Check if AI wants to play Grand
		
		
		//Check if AI wants to play Suit
		
		//CHeck if AI wants to play Null
		
		//If AI wants to play Grand and Suit, and certainty is the same, check what has the higher game value and play this, set other to not wanted
		//IF AI wants to play Suit and Null, and certainty is the same, check what has the higher game value and play this
		//IF AI wants to play Grand and Null, and certainty is the same, always play Grand
		
		//If the AI doesn't want to play single, return null
		return null;
	}
	
	private static int calculateBet(AIController controller){
		SinglePlay singlePlay;
		if((singlePlay = Medium.playSingle(controller)) != null){
			if(singlePlay.playMode == PlayMode.NULL){
				return 23;
			}
			if(singlePlay.playMode == PlayMode.GRAND){
				return General.getHighestPossibleBet(controller, PlayMode.GRAND);
			}
			if(singlePlay.playMode == PlayMode.SUIT){
				int gameLevel = General.getGameLevel(controller);
				int colourValue = 0;
				switch(singlePlay.colour){
					case CLUBS: colourValue = 12; break;
					case SPADES: colourValue = 11; break;
					case HEARTS: colourValue = 10; break;
					case DIAMONDS: colourValue = 9; break;
				}
				return gameLevel*colourValue;
			}
		}else{
			return 0;
		}
		return 0;
	}

}
