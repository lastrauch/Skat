package ai;

import logic.PlayMode;
import logic.PlayState;

public class Easy {
  
  
  public static int playCard(AIController controller){
    return General.playRandomCard(controller);
  }
  
  public static boolean setBet(AIController controller, int bet){
    int maxBet = General.getHighestPossibleBet(controller, PlayMode.SUIT);
    double random = 1 + (Math.random() *1);
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
	  
	  return null;
  }
  public static void main (String args[]) {

  }
}
