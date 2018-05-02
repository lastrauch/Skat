package ai;

import logic.PlayMode;
import logic.PlayState;
import java.util.ArrayList;
import java.util.List;
import logic.Card;
import logic.Colour;

public class Easy {
    
  public static int playCard(AIController controller){
    return General.playRandomCard(controller);
  }
  
  public static boolean setBet(AIController controller, int bet){
    int maxBet = General.getHighestPossibleBet(controller, PlayMode.SUIT);
    double random = Math.random();
    if(maxBet >= bet) {
      if(random < 0.4) {
        return true;
      }else{
       return false;
      }   
    }else {
    return false;
    
  }    
    }
  
  public static PlayState setPlayState(AIController controller){
    
    PlayState playState = controller.getPlayState();
    List<Card> skatList = new ArrayList<Card>();
    for(int i=0; i<controller.getPlayState().getSkat().length; i++) {
      skatList.add(controller.getPlayState().getSkat()[i]);
    }
    playState.getDeclarerStack().addCards(skatList);
    playState.setPlayMode(PlayMode.SUIT);
    if(controller.getSinglePlay().getColour() != null) {
      playState.setTrump(controller.getSinglePlay().getColour());
    }else {
      List<Card> cards = controller.getBot().getHand();
      int[] hasColour = new int[4];
      for(int i=0; i<cards.size(); i++) {
        hasColour[3 - cards.get(i).getColour().ordinal()]++;
      }
      int colour = 0;
      int max = hasColour[0];
      for(int i=1; i<hasColour.length; i++) {
        if(hasColour[i] > max) {
          colour = i;
          max = hasColour[i];
        }
      }
      Colour c;
      switch(colour) {
        case 0: c = Colour.SPADES;
        case 1: c = Colour.CLUBS;
        case 2: c = Colour.HEARTS;
        case 3: c = Colour.DIAMONDS;
        default: c = Colour.SPADES;
      }
      playState.setTrump(c);
    }
    playState.setSkat(null);
    playState.setHandGame(false);
    playState.setSchneiderAnnounced(false);
    playState.setSchwarzAnnounced(false);
    playState.setOpen(false);

    return playState;
  }
  
  public static boolean askToRekontra(AIController controller) {
    return false;
  }

}
