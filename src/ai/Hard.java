package ai;

import logic.PlayState;

public class Hard {
  
  
  
  public static int playCard(AIController controller){
    //TODO
    
    return 0;
  }
  
  public static boolean setBet(AIController controller, int bet){
    //TODO
    return false;
  }
  
public static PlayState setPlayState(AIController controller){
	 //TODO 
	  return null;
  }

public static boolean askToRekontra(AIController controller){
  //TODO
  if(controller.getSinglePlay().getCertainty() > 9){
    return true;
  }else{
    return false;
  }
}

}
