package ai;

import java.util.ArrayList;
import java.util.List;

import logic.Card;
import logic.ClientLogic;
import logic.Number;
import logic.PlayMode;
import logic.LogicException;

public class General {

    // This is a static class to implement methods which are used through different
    // Bot-difficulties.
    // Available Methods are:

    // playRandomCard(AIController) : int
    // Returns the index of a playable card on the hand of the bot.

    // getHighesPossibleBet(AIController) : int
    // Returns the highest possible bet, with the current hand of the bot

    // getHighestPossibleBet(AIController, PlayMode) : int
    // Returns the highest possible bet, playing PlayMode

    // getGameLevel(AIController) : int
    // Returns the game level, the bot is playing

    // with initializeProbabilities(List<Card>) : double[][]
    // Returns the initialization of card probabilities, first column is bot

<<<<<<< HEAD
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Internal methods
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Returns the index of a playable card on the hand of the bot.
     * 
     * @author fkleinoe
     * @param controller
     * @return int
     */
    public static int playRandomCard(AiController controller) {
        List<Card> cards = controller.getBot().getHand();
        List<Card> possibleCards = new ArrayList<Card>();
        if (controller.getCurrentTrick().size() > 0) {
            for (int i = 0; i < cards.size(); i++) {
                try {
                    if (ClientLogic.checkIfCardPossible(cards.get(i), controller.getCurrentTrick().get(0),
                            controller.getPlayState(), controller.getBot())) {
                        possibleCards.add(cards.get(i));
                    }
                } catch (LogicException e) {
                    e.printStackTrace();
                }
            }
        } else {
            possibleCards = cards;
        }
=======
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Internal methods
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Returns the index of a playable card on the hand of the bot.
	 * 
	 * @author fkleinoe
	 * @param controller
	 * @return int
	 */
	public static int playRandomCard(AiController controller) {
		List<Card> cards = controller.getBot().getHand();
		List<Card> possibleCards = new ArrayList<Card>();
		if (controller.getCurrentTrick().size() > 0) {
			for (int i = 0; i < cards.size(); i++) {
				try {
					if (ClientLogic.checkIfCardPossible(cards.get(i), controller.getCurrentTrick().get(0),
							controller.getPlayState(), controller.getBot())) {
						possibleCards.add(cards.get(i));
					}
				} catch (LogicException e) {
					e.printStackTrace();
				}
			}
		} else {
			possibleCards = cards;
		}
>>>>>>> refs/heads/master

<<<<<<< HEAD
        int rnd = (int) (Math.random() * possibleCards.size());
        Card playCard = possibleCards.get(rnd);
        int index = cards.indexOf(playCard);
=======
		int rnd = (int) (Math.random() * possibleCards.size());
		Card playCard = possibleCards.get(rnd);
		int index = cards.indexOf(playCard);
>>>>>>> refs/heads/master

        return index;
    }

<<<<<<< HEAD
    /**
     * Returns the highest possible bet, with the current hand of the bot
     * 
     * @author fkleinoe
     * @param controller
     * @return int
     */
    public static int getHighestPossibleBet(AiController controller) {
        return getHighestPossibleBet(controller, PlayMode.GRAND);
    }
=======
	/**
	 * Returns the highest possible bet, with the current hand of the bot
	 * 
	 * @author fkleinoe
	 * @param controller
	 * @return int
	 */
	public static int getHighestPossibleBet(AiController controller) {
		return getHighestPossibleBet(controller, PlayMode.GRAND);
	}
>>>>>>> refs/heads/master

<<<<<<< HEAD
    /**
     * Returns the highest possible bet, playing PlayMode
     * 
     * @author fkleinoe
     * @param controller
     * @param playMode
     * @return
     */
    public static int getHighestPossibleBet(AiController controller, PlayMode playMode) {
        if (playMode == PlayMode.NULL) {
            return 23;
        } else {
            int gameLevel = General.getGameLevel(controller);
            if (playMode == PlayMode.GRAND) {
                return 24 * gameLevel;
            } else {
                return 12 * gameLevel;
            }
        }
    }
=======
	/**
	 * Returns the highest possible bet, playing PlayMode
	 * 
	 * @author fkleinoe
	 * @param controller
	 * @param playMode
	 * @return
	 */
	public static int getHighestPossibleBet(AiController controller, PlayMode playMode) {
		if (playMode == PlayMode.NULL) {
			return 23;
		} else {
			int gameLevel = General.getGameLevel(controller);
			if (playMode == PlayMode.GRAND) {
				return 24 * gameLevel;
			} else {
				return 12 * gameLevel;
			}
		}
	}
>>>>>>> refs/heads/master

<<<<<<< HEAD
    /**
     * Returns the game level, the bot is playing
     * 
     * @author fkleinoe
     * @param controller
     * @return int
     */
    public static int getGameLevel(AiController controller) {
        List<Card> cards = controller.getBot().getHand();
        // Determine the Jacks
        boolean[] jacks = new boolean[4];
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getNumber() == Number.JACK) {
                switch (cards.get(i).getColour()) {
                case CLUBS:
                    jacks[0] = true;
                    break;
                case SPADES:
                    jacks[1] = true;
                    break;
                case HEARTS:
                    jacks[2] = true;
                    break;
                case DIAMONDS:
                    jacks[3] = true;
                    break;
                }
            }
        }
=======
	/**
	 * Returns the game level, the bot is playing
	 * 
	 * @author fkleinoe
	 * @param controller
	 * @return int
	 */
	public static int getGameLevel(AiController controller) {
		List<Card> cards = controller.getBot().getHand();
		// Determine the Jacks
		boolean[] jacks = new boolean[4];
		for (int i = 0; i < cards.size(); i++) {
			if (cards.get(i).getNumber() == Number.JACK) {
				switch (cards.get(i).getColour()) {
				case CLUBS:
					jacks[0] = true;
					break;
				case SPADES:
					jacks[1] = true;
					break;
				case HEARTS:
					jacks[2] = true;
					break;
				case DIAMONDS:
					jacks[3] = true;
					break;
				}
			}
		}
>>>>>>> refs/heads/master

        // Determine the gameLevel
        int gameLevel = 1;
        boolean with = jacks[0];
        for (int i = 1; i < 4; i++) {
            if (jacks[0] == with) {
                gameLevel++;
            }
        }
        return gameLevel;
    }

    /**
     * Returns the initialization of card probabilities, first column is bot
     * 
     * @author fkleinoe
     * @param hand
     * @return double[][]
     */
    public static double[][] initializeProbabilities(List<Card> hand) {
        double prob[][] = new double[32][3];
        for (int i = 0; i < prob.length; i++) {
            for (int j = 1; j < prob[0].length; j++) {
                prob[i][j] = 0.5;
            }
        }
        int colour;
        int number;
        for (int i = 0; i < hand.size(); i++) {
            colour = 3 - hand.get(i).getColour().ordinal();
            number = 7 - hand.get(i).getNumber().ordinal();
            prob[colour * 8 + number][0] = 1;
            prob[colour * 8 + number][1] = 0;
            prob[colour * 8 + number][2] = 0;
        }

<<<<<<< HEAD
        return prob;
    }
    
    //TODO
    public static int checkIfPossibleAndGetIndex(double[][] cardProbabilities, List<Card> cards, int colour, int number, int playerIndex) {
      if(cardProbabilities[colour*8 + number][playerIndex] > 0) {
        for(int i=0; i<cards.size(); i++) {
          if((3 - cards.get(i).getColour().ordinal()) == colour && (7 - cards.get(i).getNumber().ordinal()) == number) {
            return i;
          }
        }
      }
      return -1;
    }
=======
		return prob;
	}
	
	//TODO
	public static int checkIfPossibleAndGetIndex(double[][] cardProbabilities, List<Card> cards, int colour, int number, int playerIndex) {
	  if(cardProbabilities[colour*8 + number][playerIndex] > 0) {
	    for(int i=0; i<cards.size(); i++) {
	      if((3 - cards.get(i).getColour().ordinal()) == colour && (7 - cards.get(i).getNumber().ordinal()) == number) {
	        return i;
	      }
	    }
	  }
	  return -1;
	}
>>>>>>> refs/heads/master

}
