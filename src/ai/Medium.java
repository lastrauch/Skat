package ai;

import java.util.List;

import logic.Card;
import logic.Colour;
import logic.PlayMode;
import logic.PlayState;
import logic.Number;

public class Medium {
	
	public static int playCard(AIController controller){
		//TODO
    
		return 0;
	}
  
	public static boolean setBet(AIController controller, int bet){
		if(Medium.calculateBet(controller) >= bet){
			return true;
		}else{
			return false;
		}
	}
	
	public static PlayState setPlayState(AIController controller){
		  
		  return null;
	  }
	
	public static List<Card> returnSkat(AIController controller){
		//TODO interne Hilfsmethode um zu entscheiden welcher Skat wieder eingefügt werden soll
		
		return null;
	}
	
	public static SinglePlay decidePlayMode(AIController controller){
		return playSingle(controller);
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
      int jackSpades = 9;
      int jackClubs = 7;
      int jackHearts = 5;
      int jackDiamonds = 3;
      int ace = 2;
      int ten = 1;
      int twoJacksButNotSpadesAndClubs = -5;
      double rowFactorPerCard = 1.25;
      
      //Single Cards value
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
        //Aces getting checked in the deck values in row-rule
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
      
      //Scale certGrand
      
      //Check if AI wants to play Suit
      //TODO
      //Single Cards value
      double minCertSuit = 20;
      Colour suitColour = Colour.CLUBS;
      jackSpades = 9;
      jackClubs = 7;
      jackHearts = 5;
      jackDiamonds = 3;
      ace = 2;
      ten = 1;
      int missingColour = 3;
      double colourFactorPerCard = 1.25;
      if(hasJack[0]){
          certSuit += jackSpades;
        }
        if(hasJack[1]){
          certSuit += jackClubs;
        }
        if(hasJack[2]){
          certSuit += jackHearts;
        }
        if(hasJack[3]){
          certSuit += jackDiamonds;
        }
        for(int i=0; i<cards.size(); i++){
          if(cards.get(i).getNumber() == Number.ASS){
        	  certSuit += ace;
          }
          if(cards.get(i).getNumber() == Number.TEN){
            certSuit += ten;
          }
        }
        
        //Deck value
        for(int i=0; i<hasColour.length; i++){
        	if(!hasColour[i]) certSuit += missingColour;
        }
        
        
        if(certSuit >= minCertSuit) wantsSuit = true;
      
      
      
      
      //CHeck if AI wants to play Null
      //TODO
      
      
      
      
      
      
      
      
      SinglePlay sP;
      //If AI wants to play Grand and Suit, and certainty is the same, check what has the higher game value and play this, set other to not wanted.
      //If the game values are the same, play Grand
      if(wantsGrand && wantsSuit){
    	  if(certGrand > certSuit){
    		  wantsSuit = false;
    	  }else if(certGrand < certSuit){
    		  wantsGrand = false;
    	  }else{
    		  int betGrand = General.getHighestPossibleBet(controller, PlayMode.GRAND);
    		  int gameLevel = General.getGameLevel(controller);
				int colourValue = 0;
				switch(suitColour){
					case CLUBS: colourValue = 12; break;
					case SPADES: colourValue = 11; break;
					case HEARTS: colourValue = 10; break;
					case DIAMONDS: colourValue = 9; break;
				}
				int betSuit = gameLevel*colourValue;
				if(betGrand >= betSuit){
					wantsSuit = false;
				}else{
					wantsGrand = false;
				}
    	  }
      }
      
      //IF AI wants to play Suit and Null, and certainty is the same, check what has the higher game value and play this, set other to not wanted.
      //If the game values are the same, play Suit
      if(wantsNull && wantsSuit){
    	  if(certNull > certSuit){
    		  wantsSuit = false;
    	  }else if(certNull < certSuit){
    		  wantsNull = false;
    	  }else{
    		  int betNull = 23;
    		  int gameLevel = General.getGameLevel(controller);
				int colourValue = 0;
				switch(suitColour){
					case CLUBS: colourValue = 12; break;
					case SPADES: colourValue = 11; break;
					case HEARTS: colourValue = 10; break;
					case DIAMONDS: colourValue = 9; break;
				}
				int betSuit = gameLevel*colourValue;
				if(betNull > betSuit){
					wantsSuit = false;
				}else{
					wantsNull = false;
				}
    	  }
      }
      
      //IF AI wants to play Grand and Null, and certainty is the same, always play Grand
      if(wantsGrand && wantsNull){
    	  if(certGrand >= certNull){
    		  wantsNull = false;
    	  }else{
    		  wantsGrand = false;
    	  }
      }
      
      //Check if AI wants to solely play one PlayMode after the above discrimination and return this one
      //If the AI doesn't want to play single, return null
      if((wantsGrand ^ wantsSuit ^ wantsNull) && ((wantsGrand != wantsSuit) || (wantsGrand != wantsNull))){
    	  if(wantsGrand){
    		  sP = new SinglePlay(PlayMode.GRAND);
    		  return sP;
    	  }else if(wantsNull){
    		  sP = new SinglePlay(PlayMode.SUIT);
    		  return sP;
    	  }else{
    		  sP = new SinglePlay(PlayMode.SUIT, suitColour);
    		  return sP;
    	  }
      }else{
    	  return null;
      }
  }
	
	private static int calculateBet(AIController controller){
		SinglePlay singlePlay;
		if((singlePlay = Medium.playSingle(controller)) != null){
			if(singlePlay.getPlayMode() == PlayMode.NULL){
				return 23;
			}
			if(singlePlay.getPlayMode() == PlayMode.GRAND){
				return General.getHighestPossibleBet(controller, PlayMode.GRAND);
			}
			if(singlePlay.getPlayMode() == PlayMode.SUIT){
				int gameLevel = General.getGameLevel(controller);
				int colourValue = 0;
				switch(singlePlay.getColour()){
					case CLUBS: colourValue = 12; break;
					case SPADES: colourValue = 11; break;
					case HEARTS: colourValue = 10; break;
					case DIAMONDS: colourValue = 9; break;
				}
				return gameLevel*colourValue;
			}
		}
		return 0;
	}

}
