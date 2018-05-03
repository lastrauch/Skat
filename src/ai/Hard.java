package ai;

import logic.PlayState;

import java.util.List;

import logic.Card;

public class Hard {
  
public static boolean askForBet(AIController controlle, int bet) {
	//TODO
	return false;
}
  
public static boolean askToTakeUpSkat(AIController controller) {
	//TODO
	return false;
}

public static List<Card> switchSkat(AIController controller){
	//TODO
	return null;
}

public static PlayState askToSetPlayState(AIController controller) {
	//TODO
	return null;
}

public static void decideToPlayKontra(AIController controller) {
	//TODO
}

public static boolean askToRekontra(AIController controller){
  if(controller.getSinglePlay().getCertainty() > 9){
    return true;
  }else{
    return false;
  }
}

public static int askToPlayCard(AIController controller) {
	//TODO
	return 0;
}

}
