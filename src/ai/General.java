package ai;

import java.util.ArrayList;
import java.util.List;
import logic.Card;
import logic.ClientLogic;
import logic.LogicException;
import logic.Number;
import logic.PlayMode;

/**
 * This is a static class to implement methods which are used through different Bot-difficulties.
 * 
 * @author fkleinoe
 */
public class General {

  // playRandomCard(AIController) : int
  // Returns the index of a playable card on the hand of the bot.

  // getHighesPossibleBet(AIController) : int
  // Returns the highest possible bet, with the current hand of the bot

  // getHighestPossibleBet(AIController, PlayMode) : int
  // Returns the highest possible bet, playing PlayMode

  // getGameLevel(AIController) : int
  // Returns the game level, the bot is playing

  // initializeProbabilities(List<Card>) : double[][]
  // Returns the initialization of card probabilities, first column is bot

  // checkIfPossibleAndGetIndex(double[][], List<Card>, int, int, int) : int
  // Checks whether the AI has said card in its hand and return the index, if so.

  // playColour(doulbe[][], List<Card>, int, int, int, boolean) : int
  // Tries to play card of given colour.

  // playTrump(PlayMode, double[][], List<Card>, int, int, int, int, boolean) : int
  // Tries to play a trump card.

  // playValue(double[][], List<Card>, int, int, int, boolean) : int
  // Tries to play a card with no specific colour.


  //////////////////////////////////////////////////////////////////////////////////////////////////
  // Internal methods
  //////////////////////////////////////////////////////////////////////////////////////////////////
  /**
   * Returns the index of a playable card on the hand of the bot.
   * 
   * @author fkleinoe
   * @param controller with all information
   * @return int index of a playable card
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

    int rnd = (int) (Math.random() * possibleCards.size());
    Card playCard = possibleCards.get(rnd);
    int index = cards.indexOf(playCard);

    return index;
  }

  /**
   * Returns the highest possible bet, with the current hand of the bot.
   * 
   * @author fkleinoe
   * @param controller with all information
   * @return int highest possible bet with current hand
   */
  public static int getHighestPossibleBet(AiController controller) {
    return getHighestPossibleBet(controller, PlayMode.GRAND);
  }

  /**
   * Returns the highest possible bet, playing PlayMode.
   * 
   * @author fkleinoe
   * @param controller with all information
   * @param playMode highest possible bet with current hand
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

  /**
   * Returns the game level, the bot is playing.
   * 
   * @author fkleinoe
   * @param controller with all information
   * @return int game level with current hand
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
          default:
        }
      }
    }

    // Determine the gameLevel
    int gameLevel = 2;
    boolean with = jacks[0];
    for (int i = 1; i < 4; i++) {
      if (jacks[i] == with) {
        gameLevel++;
      }
    }
    return gameLevel;
  }

  /**
   * Returns the initialization of card probabilities, first column is bot.
   * 
   * @author fkleinoe
   * @param hand current hand of the AI
   * @return double[][] cardProbabilities
   */
  public static double[][] initializeProbabilities(List<Card> hand) {
    double[][] prob = new double[32][3];
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
    return prob;
  }

  /**
   * Checks wether the AI has said card in its hand and return the index, if so.
   * 
   * @author fkleinoe
   * @param cardProbabilities of all players
   * @param cards current hand of AI
   * @param colour of card that should be played
   * @param number of card that should be played
   * @param playerIndex of the player that should play the card
   * @return int the index of the card within the hand
   */
  public static int checkIfPossibleAndGetIndex(double[][] cardProbabilities, List<Card> cards,
      int colour, int number, int playerIndex) {
    if (cardProbabilities[colour * 8 + number][playerIndex] > 0) {
      for (int i = 0; i < cards.size(); i++) {
        if ((3 - cards.get(i).getColour().ordinal()) == colour
            && (7 - cards.get(i).getNumber().ordinal()) == number) {
          return i;
        }
      }
    }
    return -1;
  }

  /**
   * Tries to play card of said colour.
   * 
   * @author fkleinoe
   * @param cardProbabilities of all players
   * @param cards of the AI
   * @param colour that should be played
   * @param value number the played card should exceed
   * @param playerIndex of the player that should play a card
   * @param low whether the card should be low or high in value
   * @return the index of a choosen card within the hand
   */
  public static int playColour(double[][] cardProbabilities, List<Card> cards, int colour,
      int value, int playerIndex, boolean low) {
    if (low) {
      for (int number = 7; number > value; number--) {
        if (7 - Number.JACK.ordinal() != number) {
          int index =
              checkIfPossibleAndGetIndex(cardProbabilities, cards, colour, number, playerIndex);
          if (index != -1) {
            return index;
          }
        }
      }
    } else {
      if (value == -1) {
        value = 8;
      }
      for (int number = 0; number < value; number++) {
        if (7 - Number.JACK.ordinal() != number) {
          int index =
              checkIfPossibleAndGetIndex(cardProbabilities, cards, colour, number, playerIndex);
          if (index != -1) {
            return index;
          }
        }
      }
    }
    return -1;
  }

  /**
   * Tries to play a trump card.
   * 
   * @author fkleinoe
   * @param playMode current PlayMode
   * @param cardProbabilities of all players
   * @param cards of the AI
   * @param jackColour of a possibly played jack
   * @param trumpColour trump colour if PlayMode is SUIT
   * @param value number the played card should exceed
   * @param playerIndex of the player that should play a card
   * @param low whether the card should be low or high in value
   * @return the index of a choosen card within the hand
   */
  public static int playTrump(PlayMode playMode, double[][] cardProbabilities, List<Card> cards,
      int jackColour, int trumpColour, int value, int playerIndex, boolean low) {
    if (playMode == PlayMode.GRAND) {
      if (low) {
        int number = 7 - Number.JACK.ordinal();
        for (int colour = 3; colour > jackColour; colour--) {
          int index =
              checkIfPossibleAndGetIndex(cardProbabilities, cards, colour, number, playerIndex);
          if (index != -1) {
            return index;
          }
        }
      } else {
        if (jackColour == -1) {
          jackColour = 4;
        }
        int number = 7 - Number.JACK.ordinal();
        for (int colour = 0; colour < jackColour; colour++) {
          int index =
              checkIfPossibleAndGetIndex(cardProbabilities, cards, colour, number, playerIndex);
          if (index != -1) {
            return index;
          }
        }
      }
    }
    if (playMode == PlayMode.SUIT) {
      if (low) {
        int colour = trumpColour;
        for (int number = 7; number < value; number--) {
          if (7 - Number.JACK.ordinal() != number) {
            int index =
                checkIfPossibleAndGetIndex(cardProbabilities, cards, colour, number, playerIndex);
            if (index != -1) {
              return index;
            }
          }
        }
      } else {
        if (value == -1) {
          value = 8;
        }
        int number = 7 - Number.JACK.ordinal();
        for (int colour = 0; colour < 4; colour++) {
          int index =
              checkIfPossibleAndGetIndex(cardProbabilities, cards, colour, number, playerIndex);
          if (index != -1) {
            return index;
          }
        }
        if (jackColour == -1) {
          int colour = trumpColour;
          for (number = 0; number < 8; number++) {
            int index =
                checkIfPossibleAndGetIndex(cardProbabilities, cards, colour, number, playerIndex);
            if (index != -1) {
              return index;
            }
          }
        }
      }
    }
    return -1;
  }

  /**
   * Tries to play a card with no specific colour.
   * 
   * @author fkleinoe
   * @param cardProbabilities of all players
   * @param cards of the AI
   * @param excludeColour colour that should not be played
   * @param excludeValue number thath should not be played
   * @param playerIndex of the player that should play a card
   * @param low whether the card should be low or high in value
   * @return the index of a choosen card within the hand
   */
  public static int playValue(double[][] cardProbabilities, List<Card> cards, int excludeColour,
      int excludeValue, int playerIndex, boolean low) {
    if (low) {
      for (int number = 7; number > -1; number--) {
        for (int colour = 3; colour > -1; colour--) {
          if (colour != excludeColour && number != excludeValue) {
            int index =
                checkIfPossibleAndGetIndex(cardProbabilities, cards, colour, number, playerIndex);
            if (index != -1) {
              return index;
            }
          }
        }
      }
    } else {
      for (int number = 0; number < 8; number++) {
        for (int colour = 0; colour < 8; colour++) {
          if (colour != excludeColour && number != excludeValue) {
            int index =
                checkIfPossibleAndGetIndex(cardProbabilities, cards, colour, number, playerIndex);
            if (index != -1) {
              return index;
            }
          }
        }
      }
    }
    return -1;
  }

}
