package ai;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import logic.Card;
import logic.Colour;
import logic.PlayMode;
import logic.PlayState;

public class Easy {

	// This is a static class to implement methods to play with an easy AI.
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

	//////////////////////////////////////////////////////////////////////////////////////////////////
	// Methods called by AIController
	//////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Checks if the easy AI wants to place a bet of passed value.
	 * 
	 * @author dpervane
	 * @param controller
	 * @param bet
	 * @return boolean
	 */
	public static boolean askForBet(AiController controller, int bet) {
		int maxBet = General.getHighestPossibleBet(controller, PlayMode.SUIT);
		double random = Math.random();
		if (maxBet >= bet) {
			if (random < 0.4) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;

		}
	}

	/**
	 * Checks if the AI wants to pick up the skat.
	 * 
	 * @author dpervane
	 * @param controller
	 * @return boolean
	 */
	public static boolean askToTakeUpSkat(AiController controller) {
		return false;
	}

	/**
	 * Gives back the cards, the AI wants to put on the skat after picking it up.
	 * 
	 * @author dpervane
	 * @param controller
	 * @return List(Card)
	 */
	public static List<Card> switchSkat(AiController controller) {
		return Arrays.asList(controller.getPlayState().getSkat());
	}

	/**
	 * If the AI won the auction, it needs to set a PlayState.
	 * 
	 * @author dpervane
	 * @param controller
	 * @return PlayState
	 */
	public static PlayState askToSetPlayState(AiController controller) {

		PlayState playState = controller.getPlayState();
		List<Card> skatList = new ArrayList<Card>();
		for (int i = 0; i < controller.getPlayState().getSkat().length; i++) {
			skatList.add(controller.getPlayState().getSkat()[i]);
		}
		playState.getDeclarerStack().addCards(skatList);
		playState.setPlayMode(PlayMode.SUIT);
		if (controller.getSinglePlay() != null && controller.getSinglePlay().getColour() != null) {
			playState.setTrump(controller.getSinglePlay().getColour());
		} else {
			List<Card> cards = controller.getBot().getHand();
			int[] hasColour = new int[4];
			for (int i = 0; i < cards.size(); i++) {
				hasColour[3 - cards.get(i).getColour().ordinal()]++;
			}
			int colour = 0;
			int max = hasColour[0];
			for (int i = 1; i < hasColour.length; i++) {
				if (hasColour[i] > max) {
					colour = i;
					max = hasColour[i];
				}
			}
			Colour c;
			switch (colour) {
			case 0:
				c = Colour.SPADES;
			case 1:
				c = Colour.CLUBS;
			case 2:
				c = Colour.HEARTS;
			case 3:
				c = Colour.DIAMONDS;
			default:
				c = Colour.SPADES;
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

	/**
	 * If someone called Kontra, check if the AI wants to call Rekontra.
	 * 
	 * @author dpervane
	 * @param controller
	 * @return boolean
	 */
	public static boolean askToRekontra(AiController controller) {
		return false;
	}

	/**
	 * Gives back the index of a Card on the hand, that the AI wants to play.
	 * 
	 * @author dpervane
	 * @param controller
	 * @return int
	 */
	public static int askToPlayCard(AiController controller) {
		return General.playRandomCard(controller);
	}

}
