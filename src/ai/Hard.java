package ai;

import logic.PlayState;

import java.util.List;

import logic.Card;

public class Hard {

	// This is a static class to implement methods to play with a difficult AI.
	// Available methods are:

	// askForBet(AIController, int) : boolean
	// Checks if the easy AI wants to place a bet of passed value.

	// askToTakeUpSkat(AIController) : boolean
	// Checks if the AI wants to pick up the skat.

	// switchSkat(AIController) : List<Card>
	// Gives back the cards, the AI wants to put on the skat after picking it up.

	// askToSetPlayState(AIController) : PlayState
	// If the AI won the auction, it needs to set a PlayState.

	// askToRekontra(AIController) : boolean
	// If someone called Kontra, check if the AI wants to call Rekontra.

	// askToPlayCard(AIController) : int
	// Gives back the index of a Card on the hand, that the AI wants to play.

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Methods called by AIController
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Checks if the easy AI wants to place a bet of passed value.
	 * 
	 * @author fkleinoe
	 * @param controller
	 * @param bet
	 * @return boolean
	 */
	public static boolean askForBet(AIController controlle, int bet) {
		// TODO
		return false;
	}

	/**
	 * Checks if the AI wants to pick up the skat.
	 * 
	 * @author fkleinoe
	 * @param controller
	 * @return boolean
	 */
	public static boolean askToTakeUpSkat(AIController controller) {
		// TODO
		return false;
	}

	/**
	 * Gives back the cards, the AI wants to put on the skat after picking it up.
	 * 
	 * @author fkleinoe
	 * @param controller
	 * @return List(Card)
	 */
	public static List<Card> switchSkat(AIController controller) {
		// TODO
		return null;
	}

	/**
	 * If the AI won the auction, it needs to set a PlayState.
	 * 
	 * @author fkleinoe
	 * @param controller
	 * @return PlayState
	 */
	public static PlayState askToSetPlayState(AIController controller) {
		// TODO
		return null;
	}

	/**
	 * Check if the AI wants to play Kontra.
	 * 
	 * @author fkleinoe
	 * @param controller
	 */
	public static void decideToPlayKontra(AIController controller) {
		// TODO
	}

	/**
	 * If someone called Kontra, check if the AI wants to call Rekontra.
	 * 
	 * @author fkleinoe
	 * @param controller
	 * @return boolean
	 */
	public static boolean askToRekontra(AIController controller) {
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
	 * @param controller
	 * @return int
	 */
	public static int askToPlayCard(AIController controller) {
		// TODO
		return 0;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Internal Methods
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
