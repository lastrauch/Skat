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
