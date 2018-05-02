package ai;

import logic.PlayMode;
import logic.PlayState;
import java.util.ArrayList;
import java.util.List;
import logic.Card;

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
    playState.setPlayMode(controller.getSinglePlay().getPlayMode());    
    playState.setSkat(null);
    playState.setTrump(controller.getSinglePlay().getColour());
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
