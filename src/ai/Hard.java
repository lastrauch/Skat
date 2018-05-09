package ai;

import java.util.List;
import logic.Card;
import logic.PlayState;

public class Hard {

  /*
   * This is a static class to implement methods to play with a difficult AI. Available methods are:
   */

  // askForBet(AIController, int) : boolean
  // Checks if the easy AI wants to place a bet of passed value.

  // askToTakeUpSkat(AIController) : boolean
  // Checks if the AI wants to pick up the skat.

  // switchSkat(AIController) : List<Card>
  // Gives back the cards, the AI wants to put on the skat after picking it up.

  // askToSetPlayState(AIController) : PlayState
  // If the AI won the auction, it needs to set a PlayState.

  // decideToPlayKontra(AIController) : void
  // If the AI wants to declare Kontra, he will call it to the Logic.

  // askToRekontra(AIController) : boolean
  // If someone called Kontra, check if the AI wants to call Rekontra.

  // askToPlayCard(AIController) : int
  // Gives back the index of a Card on the hand, that the AI wants to play.

  //////////////////////////////////////////////////////////////////////////////////////////////////
  // Methods called by AIController
  //////////////////////////////////////////////////////////////////////////////////////////////////
  /**
   * Checks if the easy AI wants to place a bet of passed value.
   * 
   * @author fkleinoe
   * @param controller with all information
   * @param bet to be placed
   * @return boolean whether the AI wants to place the bet
   */
  public static boolean askForBet(AiController controller, int bet) {
    // TODO
    return false;
  }

  /**
   * Checks if the AI wants to pick up the skat.
   * 
   * @author fkleinoe
   * @param controller with all information
   * @return boolean whether the AI wants to take up the skat
   */
  public static boolean askToTakeUpSkat(AiController controller) {
    // TODO
    return false;
  }

  /**
   * Gives back the cards, the AI wants to put on the skat after picking it up.
   * 
   * @author fkleinoe
   * @param controller with all information
   * @return List(Card) that should be returned in the skat
   */
  public static List<Card> switchSkat(AiController controller) {
    // TODO
    return null;
  }

  /**
   * If the AI won the auction, it needs to set a PlayState.
   * 
   * @author fkleinoe
   * @param controller with all information
   * @return PlayState that will be set
   */
  public static PlayState askToSetPlayState(AiController controller) {
    // TODO
    return null;
  }

  /**
   * Checks whether the AI wants to declare kontra.
   * 
   * @author fkleinoe
   * @param controller with all information
   * @return boolean whether the AI wants to declare kontra
   */
  public static boolean decideToPlayKontra(AiController controller) {
    // TODO
    return false;
  }

  /**
   * If someone called Kontra, check if the AI wants to call Rekontra.
   * 
   * @author fkleinoe
   * @param controller with all information
   * @return boolean whether the AI wants to declare rekontra
   */
  public static boolean askToRekontra(AiController controller) {
    if (controller.getSinglePlay().getCertainty() > 9) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Gives back the index of a Card on the hand, that the AI wants to play.
   * 
   * @author fkleinoe
   * @param controller with all information
   * @return int index of the played card
   */
  public static int askToPlayCard(AiController controller) {
    // TODO
    return 0;
  }

  //////////////////////////////////////////////////////////////////////////////////////////////////
  // Internal Methods
  //////////////////////////////////////////////////////////////////////////////////////////////////

}
