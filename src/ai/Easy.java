package ai;

import java.util.ArrayList;
import java.util.List;
import logic.PlayMode;
import logic.PlayState;
import logic.Player;

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
    return null;
  }
//    PlayState playState = controller.getPlayState();
//    playState.getDeclarerStack();
//    playState.setPlayMode(controller.getSinglePlay().getPlayMode().SUIT);
//    playState.setSkat(null);
//    playState.setTrump(controller.getSinglePlay().getColour());
//    playState.setHandGame(false);
//    playState.setSchneiderAnnounced(false);
//    playState.setSchwarzAnnounced(false);
//    playState.setOpen(false);
//    
//    controller.setPartner(null);
//    List<Player> opponents = new ArrayList<Player>();
//    for (int i = 0; i < controller.getAllPlayer().size(); i++) {
//      opponents.add(controller.getAllPlayer().get(i));
//    }
//    while()
//   
//      controller.setExistingTrumps(11);
//    
//      
//    }
    
   
//    
////    playState.setTrump(controller.getSinglePlay().getColour());
//	 SinglePlay singlePlayer;
//	 //wenn Reiz gewonnen 
//	 //bestimmt der Alleinspieker Art des Spiels : hier: Suit
////	 singlePlayer = new SinglePlay(PlayMode.SUIT);
//	 
// 
//	 //entscheiden, was für ein Spiel gespielt werden soll
//	 //also einer der Farben als Trumpf wählen
	  

}
