package ai;

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
	 SinglePlay singlePlayer;
	 //wenn höchster Reizwert 
	 //bestimmt der Alleinspieker Art des Spiels : hier: Suit
	 singlePlayer = new SinglePlay(PlayMode.SUIT);
 
	 //entscheiden, was für ein Spiel gespielt werden soll
	 //also einer der Farben als Trumpf wählen
	  
	  return null;
  }
  public static void main (String args[]) {

  }
}
