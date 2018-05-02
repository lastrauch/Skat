package ai;

import logic.GameSettings;
import logic.PlayMode;
import logic.PlayState;

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
    playState.getDeclarerStack();
    playState.setPlayMode(controller.getSinglePlay().getPlayMode().SUIT);    
    playState.setSkat(null);
    playState.setTrump(controller.getSinglePlay().getColour());
    playState.setHandGame(false);
    playState.setSchneiderAnnounced(false);
    playState.setSchwarzAnnounced(false);
    playState.setOpen(false);

    return playState;
  }
  public static void main (String [] args) {
    
//    AIController c = new AIController("hi", BotDifficulty.EASY , null);
//    setPlayState(c);
  }

}
