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
      List<Card> cards = controller.getBot().getHand();
      
      boolean wantsGrand = false;
      boolean wantsSuit = false;
      boolean wantsNull = false;
      double certGrand = 0;   //Certainty to play Grand, integer in [0;10]
      double certSuit = 0;    //Certainty to play Suit, integer in [0;10]
      double certNull = 0;    //Certainty to play Null, integer in [0;10]
      
      boolean hasColour[] = new boolean[4];
      boolean hasJack[] = new boolean[4];
      
      //Check if AI wants to play Grand
      double minCertGrand = 20;
      //Single Cards value
      int jackSpades = 9;
      int jackClubs = 7;
      int jackHearts = 5;
      int jackDiamonds = 3;
      int ace = 2;
      int ten = 1;
      int twoJacksButNotSpadesAndClubs = -5;
      double rowFactorPerCard = 1.25;
      
      if(controller.getCardProbabilities()[4][0] == 1){
        certGrand += jackSpades;
        hasJack[0] = true;
      }
      if(controller.getCardProbabilities()[12][0] == 1){
        certGrand += jackClubs;
        hasJack[1] = true;
      }
      if(controller.getCardProbabilities()[20][0] == 1){
        certGrand += jackHearts;
        hasJack[2] = true;
      }
      if(controller.getCardProbabilities()[28][0] == 1){
        certGrand += jackDiamonds;
        hasJack[3] = true;
      }
      for(int i=0; i<cards.size(); i++){
        //Ace gets checked in the row
        //Ten should also be worth something, even no ace is in the hand
        if(cards.get(i).getNumber() == Number.TEN){
          certGrand += ten;
        }
        hasColour[4 - cards.get(i).getColour().ordinal()] = true;
      }
      
      //Deck value
      if(!hasJack[0] && !hasJack[1]) certGrand += twoJacksButNotSpadesAndClubs;
      for(int i=0; i<4; i++){
        int j = 0;
        double row = ace/rowFactorPerCard;
        while(controller.getCardProbabilities()[8*i+j][0] == 1 || j>7){
          row *=rowFactorPerCard;
          j++;
        }
        certGrand += row;
      }
      
      if(certGrand >= minCertGrand) wantsGrand = true;
      
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
