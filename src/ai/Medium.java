package ai;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import logic.Card;
import logic.Colour;
import logic.Number;
import logic.PlayMode;
import logic.PlayState;

/**
 * This is a static class to implement methods to play with a medium AI. Available methods are:
 * 
 * @author fkleinoe
 */
public class Medium {

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

  // returnSkat(AIController, PlayMode) : List<Card>
  // Determines which cards to put back into the Skat.

  // calculateBet(AIController) : int
  // Calculates the maximum bet the AI wants to place.

  // playSingle(AIController) : SinglePlay
  // Determines the PlayMode the AI wants to play as a declarer

  // playCardGrand(AIController) : int
  // Decides which card to play, if play mode is Grand

  // playCardSuit(AIController) : int
  // Decides which card to play, if play mode is Suit.

  // playCardNull(AIController) : int
  // Decides which card to play, if play mode is Null.


  private static final int minTrickValue = 7; // min value of trick, so AI wants to win

  private static final double minCertGrand = 25;
  private static final int jackSpadesGrand = 9;
  private static final int jackClubsGrand = 7;
  private static final int jackHeartsGrand = 5;
  private static final int jackDiamondsGrand = 3;
  private static final int aceGrand = 2;
  private static final int tenGrand = 1;
  private static final int twoJacksButNotSpadesAndClubs = -5;
  private static final double rowFactorPerCardGrand = 1.25;

  private static final double minCertSuit = 25;
  private static final int jackSpadesSuit = 9;
  private static final int jackClubsSuit = 7;
  private static final int jackHeartsSuit = 5;
  private static final int jackDiamondsSuit = 3;
  private static final int aceSuit = 2;
  private static final int tenSuit = 1;
  private static final int missingColourSuit = 3;
  private static final double colourFactorPerCardSuit = 1.25;

  private static final double minCertNull = 25;
  private static final int sevenNull = 4;
  private static final int eightNull = sevenNull;
  private static final int nineNull = 2;
  private static final int tenNull = 1;
  private static final int missingColourNull = 3;
  private static final double rowFactorPerCardNull = 1.25;
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
    if (controller.getMaxBet() == 0) {
      controller.setMaxBet(Medium.calculateBet(controller));
    }
    if (controller.getMaxBet() >= bet) {
      int[] bets = controller.getBets();
      bets[0] = bet;
      controller.setBets(bets);
      return true;
    } else {
      return false;
    }
  }

  /**
   * Checks if the AI wants to pick up the skat.
   * 
   * @author fkleinoe
   * @param controller with all information
   * @return boolean whether the AI wants to take up the skat
   */
  public static boolean askToTakeUpSkat(AiController controller) {
    return true;
  }

  /**
   * Gives back the cards, the AI wants to put on the skat after picking it up.
   * 
   * @author fkleinoe
   * @param controller with all information
   * @return List(Card) that should be returned in the skat
   */
  public static List<Card> switchSkat(AiController controller) {
    if (controller.getSinglePlay() != null && controller.getSinglePlay().getPlayMode() != null) {
      return Medium.returnSkat(controller, controller.getSinglePlay().getPlayMode());
    } else {
      return Arrays.asList(controller.getPlayState().getSkat());
    }
  }

  /**
   * If the AI won the auction, it needs to set a PlayState.
   * 
   * @author fkleinoe
   * @param controller with all information
   * @return PlayState that will be set
   */
  public static PlayState askToSetPlayState(AiController controller) {
    if (controller.getSinglePlay() == null || controller.getSinglePlay().getPlayMode() == null) {
      Medium.calculateBet(controller);
    }
    if (controller.getSinglePlay() == null) {
      controller.setSinglePlay(new SinglePlay(PlayMode.NULL));
    }

    PlayState ps = controller.getPlayState();
    ps.setPlayMode(controller.getSinglePlay().getPlayMode());
    if (controller.getSinglePlay().getPlayMode() == PlayMode.SUIT) {
      ps.setTrump(controller.getSinglePlay().getColour());
    }
    ps.setHandGame(false);
    ps.setSchneiderAnnounced(false);
    ps.setSchwarzAnnounced(false);
    ps.setOpen(false);

    return ps;
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
    switch (controller.getPlayState().getPlayMode()) {
      case GRAND:
        return Medium.playCardGrand(controller);
      case SUIT:
        return Medium.playCardSuit(controller);
      case NULL:
        return Medium.playCardNull(controller);
      default:
    }
    return General.playRandomCard(controller);
  }

  //////////////////////////////////////////////////////////////////////////////////////////////////
  // Internal Methods
  //////////////////////////////////////////////////////////////////////////////////////////////////
  /**
   * Determines which cards to put back into the Skat.
   * 
   * @author fkleinoe
   * @param controller with all information
   * @param playMode play mode that is played
   * @return List(Card) the skat the should be put away
   */
  public static List<Card> returnSkat(AiController controller, PlayMode playMode) {
    List<Card> skat = Arrays.asList(controller.getPlayState().getSkat());

    List<Card> cards = new ArrayList<Card>(); // Intermediate hand with twelve cards
    for (int i = 0; i < controller.getBot().getHand().size(); i++) {
      cards.add(controller.getBot().getHand().get(i));
    }
    for (int i = 0; i < skat.size(); i++) {
      cards.add(skat.get(i));
    }

    controller.setCardProbabilities(General.initializeProbabilities(cards));

    int[] hasColour = new int[4];
    for (int i = 0; i < cards.size(); i++) {
      hasColour[3 - cards.get(i).getColour().ordinal()]++;
    }

    List<Card> skatReturn = new ArrayList<Card>();
    int minIndex = 0;
    int minValue = hasColour[0];
    for (int i = 1; i < hasColour.length; i++) {
      if (hasColour[i] < minValue && hasColour[i] > 0) {
        minIndex = i;
        minValue = hasColour[i];
      }
    }
    if (playMode == PlayMode.GRAND || playMode == PlayMode.SUIT) {
      int j = 7;
      while (j >= 0 && skatReturn.size() < 2) {
        if (controller.getCardProbabilities()[minIndex * 8 + j][0] == 1) {
          controller.setCardProbability(0, minIndex, j, 0);
          hasColour[minIndex]--;
          for (int i = 0; i < cards.size(); i++) {
            if ((3 - cards.get(i).getColour().ordinal()) == minIndex
                && (7 - cards.get(i).getNumber().ordinal()) == j) {
              skatReturn.add(cards.get(i));
              cards.remove(i);
            }
          }
        }
        j--;
      }
    } else {
      int j = 0;
      while (j < 8 && skatReturn.size() < 2) {
        if (controller.getCardProbabilities()[minIndex * 8 + j][0] == 1) {
          controller.setCardProbability(0, minIndex, j, 0);
          hasColour[minIndex]--;
          for (int i = 0; i < cards.size(); i++) {
            if ((3 - cards.get(i).getColour().ordinal()) == minIndex
                && (7 - cards.get(i).getNumber().ordinal()) == j) {
              skatReturn.add(cards.get(i));
              cards.remove(i);
            }
          }
        }
        j++;
      }
    }
    for (int i = skatReturn.size(); i < 2; i++) {
      skatReturn.add(cards.get((int) Math.random() * cards.size()));
      cards.remove(i);
    }

    for (int i = 0; i < skatReturn.size(); i++) {
      controller.setCardProbability(0, 3 - skatReturn.get(i).getColour().ordinal(),
          7 - skatReturn.get(i).getNumber().ordinal(), 0);
    }

    return skatReturn;
  }

  private static int calculateBet(AiController controller) {
    SinglePlay singlePlay = Medium.playSingle(controller);
    controller.setSinglePlay(singlePlay);
    if (singlePlay != null) {
      if (singlePlay.getPlayMode() == PlayMode.NULL) {
        return 23;
      }
      if (singlePlay.getPlayMode() == PlayMode.GRAND) {
        return General.getHighestPossibleBet(controller, PlayMode.GRAND);
      }
      if (singlePlay.getPlayMode() == PlayMode.SUIT) {
        int gameLevel = General.getGameLevel(controller);
        int colourValue = 0;
        switch (singlePlay.getColour()) {
          case CLUBS:
            colourValue = 12;
            break;
          case SPADES:
            colourValue = 11;
            break;
          case HEARTS:
            colourValue = 10;
            break;
          case DIAMONDS:
            colourValue = 9;
            break;
          default:
        }
        return gameLevel * colourValue;
      }
    }
    return -1;
  }

  private static SinglePlay playSingle(AiController controller) {

    boolean wantsGrand = false;
    boolean wantsSuit = false;
    boolean wantsNull = false;
    double certGrand = 0; // Certainty to play Grand, integer in [0;10]
    double certSuit = 0; // Certainty to play Suit, integer in [0;10]
    double certNull = 0; // Certainty to play Null, integer in [0;10]

    boolean[] hasColour = new boolean[4];
    boolean[] hasJack = new boolean[4];

    // Check if AI wants to play Grand
    // Highest certainty value one can get for grand
    double scaleGrand = 0;
    // Bot has four jacks
    scaleGrand += jackSpadesGrand + jackClubsGrand + jackHeartsGrand + jackDiamondsGrand;
    scaleGrand += 4 * aceGrand + 2 * tenGrand; // Bot has four aceGrands and two tenGrands
    scaleGrand += 2 * rowFactorPerCardGrand; // RowFactor because of the two tenGrands

    // Single Cards value
    if (controller.getCardProbabilities()[4][0] == 1) {
      certGrand += jackSpadesGrand;
      hasJack[0] = true;
    }
    if (controller.getCardProbabilities()[12][0] == 1) {
      certGrand += jackClubsGrand;
      hasJack[1] = true;
    }
    if (controller.getCardProbabilities()[20][0] == 1) {
      certGrand += jackHeartsGrand;
      hasJack[2] = true;
    }
    if (controller.getCardProbabilities()[28][0] == 1) {
      certGrand += jackDiamondsGrand;
      hasJack[3] = true;
    }

    List<Card> cards = controller.getBot().getHand();
    for (int i = 0; i < cards.size(); i++) {
      // aceGrands getting checked in the deck values in row-rule
      // tenGrand should also be worth something, even no aceGrand is in the hand
      if (cards.get(i).getNumber() == Number.TEN) {
        certGrand += tenGrand;
      }
      if (cards.get(i).getNumber() != Number.JACK) {
        hasColour[3 - cards.get(i).getColour().ordinal()] = true;
      }
    }

    // Deck value
    if (!hasJack[0] && !hasJack[1]) {
      certGrand += twoJacksButNotSpadesAndClubs;
    }
    for (int i = 0; i < 4; i++) {
      int j = 0;
      double row = aceGrand / rowFactorPerCardGrand;
      while (j < 8 && controller.getCardProbabilities()[8 * i + j][0] == 1) {
        row *= rowFactorPerCardGrand;
        j++;
      }
      certGrand += row;
    }

    if (certGrand >= minCertGrand) {
      wantsGrand = true;
    }
    certGrand = (certGrand / scaleGrand) * 10;

    // Check if AI wants to play Suit
    Colour suitColour = Colour.CLUBS;
    int[] numberOfColour = new int[4];

    // Highest certainty value one can get for suit
    double scaleSuit = 0;
    // Bot has four jacks
    scaleSuit += jackSpadesSuit + jackClubsSuit + jackHeartsSuit + jackDiamondsSuit;
    scaleSuit += 1 * aceSuit + 1 * tenSuit; // Bot has one aceSuit and one tenSuit
    scaleSuit += 3 * missingColourSuit; // Bot has three colours missing (ignoring jacks)
    scaleSuit += Math.pow(colourFactorPerCardSuit, 6); // Bot has six cards from the same colour

    // Single Cards value
    if (hasJack[0]) {
      certSuit += jackSpadesSuit;
    }
    if (hasJack[1]) {
      certSuit += jackClubsSuit;
    }
    if (hasJack[2]) {
      certSuit += jackHeartsSuit;
    }
    if (hasJack[3]) {
      certSuit += jackDiamondsSuit;
    }
    for (int i = 0; i < cards.size(); i++) {
      if (cards.get(i).getNumber() == Number.ASS) {
        certSuit += aceSuit;
      }
      if (cards.get(i).getNumber() == Number.TEN) {
        certSuit += tenSuit;
      }
      if (cards.get(i).getNumber() != Number.JACK) {
        numberOfColour[3 - cards.get(i).getColour().ordinal()]++;
      }
    }

    // Deck value
    for (int i = 0; i < hasColour.length; i++) {
      if (!hasColour[i]) {
        certSuit += missingColourSuit;
      }
    }
    int maxNumber = 0;
    for (int i = 0; i < numberOfColour.length; i++) {
      if (maxNumber < numberOfColour[i]) {
        maxNumber = numberOfColour[i];
        switch (i) {
          case 0:
            suitColour = Colour.SPADES;
            break;
          case 1:
            suitColour = Colour.CLUBS;
            break;
          case 2:
            suitColour = Colour.HEARTS;
            break;
          case 3:
            suitColour = Colour.DIAMONDS;
            break;
          default:
        }
      }
    }
    certSuit += Math.pow(colourFactorPerCardSuit, maxNumber);

    if (certSuit >= minCertSuit) {
      wantsSuit = true;
    }
    certSuit = (certSuit / scaleSuit) * 10;

    // CHeck if AI wants to play Null
    int[] gap = new int[4]; // If the row has just one missing card, it is as good as a full row

    double scaleNull = 0;
    // Bot has four sevens, four eights and two nines
    scaleNull += 4 * sevenNull + 4 * eightNull + 2 * nineNull;
    // Therefore the bot has two rows of length three and two rows of length two
    scaleNull += 2 * Math.pow(1.25, 3) + 2 * Math.pow(1.25, 2);

    for (int i = 0; i < cards.size(); i++) {
      switch (cards.get(i).getNumber()) {
        case SEVEN:
          certNull += sevenNull;
          break;
        case EIGHT:
          certNull += eightNull;
          break;
        case NINE:
          certNull += nineNull;
          break;
        case TEN:
          certNull += tenNull;
          break;
        default:
      }
      hasColour[3 - cards.get(i).getColour().ordinal()] = true;
    }
    for (int i = 0; i < hasColour.length; i++) {
      if (hasColour[i]) {
        int j = 0;
        double row = 1;
        while (j < 8 && gap[i] < 2) {
          if (controller.getCardProbabilities()[8 * i + j][0] == 1) {
            row *= rowFactorPerCardNull;
          } else {
            gap[i]++;
          }
          j++;
        }
        if (row != 1) {
          certNull += row;
        }
      } else {
        certNull += missingColourNull;
      }
    }

    if (certNull >= minCertNull) {
      wantsNull = true;
    }
    certNull = (certNull / scaleNull) * 10;

    SinglePlay singlePlay;
    // If AI wants to play Grand and Suit, and certainty is the same, check
    // what has the higher game value and play this, set other to not
    // wanted.
    // If the game values are the same, play Grand
    if (wantsGrand && wantsSuit) {
      if (certGrand > certSuit) {
        wantsSuit = false;
      } else if (certGrand < certSuit) {
        wantsGrand = false;
      } else {
        int betGrand = General.getHighestPossibleBet(controller, PlayMode.GRAND);
        int gameLevel = General.getGameLevel(controller);
        int colourValue = 0;
        switch (suitColour) {
          case CLUBS:
            colourValue = 12;
            break;
          case SPADES:
            colourValue = 11;
            break;
          case HEARTS:
            colourValue = 10;
            break;
          case DIAMONDS:
            colourValue = 9;
            break;
          default:
        }
        int betSuit = gameLevel * colourValue;
        if (betGrand >= betSuit) {
          wantsSuit = false;
        } else {
          wantsGrand = false;
        }
      }
    }

    // IF AI wants to play Suit and Null, and certainty is the same, check
    // what has the higher game value and play this, set other to not
    // wanted.
    // If the game values are the same, play Suit
    if (wantsNull && wantsSuit) {
      if (certNull > certSuit) {
        wantsSuit = false;
      } else if (certNull < certSuit) {
        wantsNull = false;
      } else {
        int betNull = 23;
        int gameLevel = General.getGameLevel(controller);
        int colourValue = 0;
        switch (suitColour) {
          case CLUBS:
            colourValue = 12;
            break;
          case SPADES:
            colourValue = 11;
            break;
          case HEARTS:
            colourValue = 10;
            break;
          case DIAMONDS:
            colourValue = 9;
            break;
          default:
        }
        int betSuit = gameLevel * colourValue;
        if (betNull > betSuit) {
          wantsSuit = false;
        } else {
          wantsNull = false;
        }
      }
    }

    // IF AI wants to play Grand and Null, and certainty is the same, always
    // play Grand
    if (wantsGrand && wantsNull) {
      if (certGrand >= certNull) {
        wantsNull = false;
      } else {
        wantsGrand = false;
      }
    }

    // Check if AI wants to solely play one PlayMode after the above
    // discrimination and return this one
    // If the AI does not want to play single, return null
    if ((wantsGrand ^ wantsSuit ^ wantsNull)
        && ((wantsGrand != wantsSuit) || (wantsGrand != wantsNull))) {
      if (wantsGrand) {
        singlePlay = new SinglePlay(PlayMode.GRAND, null, certGrand);
        return singlePlay;
      } else if (wantsNull) {
        singlePlay = new SinglePlay(PlayMode.NULL, null, certNull);
        return singlePlay;
      } else {
        singlePlay = new SinglePlay(PlayMode.SUIT, suitColour, certSuit);
        return singlePlay;
      }
    } else {
      return null;
    }
  }

  /**
   * PlayMode is Grand. AI decides which card to play.
   * 
   * @author fkleinoe
   * @param controller with all information
   * @return int card index
   */
  public static int playCardGrand(AiController controller) {
    List<Card> cards = controller.getBot().getHand();
    List<Card> trick = controller.getCurrentTrick();

    int ownTrumps = 0;
    for (int i = 0; i < cards.size(); i++) {
      if (cards.get(i).getNumber() == Number.JACK) {
        ownTrumps++;
      }
    }

    // Bot is declarer
    if (controller.getBot().isDeclarer()) {
      // Bot should play the first card
      if (trick.size() == 0) {
        // Opponents still have trumps
        if (controller.getExistingTrumps() - ownTrumps > 0) {
          // Find highest trump in hand
          for (int colour = 0; colour < 4; colour++) {
            if (controller.getCardProbabilities()[colour * 8
                + (7 - Number.JACK.ordinal())][0] == 1) {
              // Find index in hand
              for (int i = 0; i < cards.size(); i++) {
                if ((3 - cards.get(i).getColour().ordinal()) == colour
                    && cards.get(i).getNumber() == Number.JACK) {
                  return i;
                }
              }
            }
          }
          // Player has no trump, but opponents still have some
          return General.playRandomCard(controller);
        } else {
          // Play Ace
          for (int colour = 0; colour < 4; colour++) {
            if (controller.getCardProbabilities()[colour * 8
                + (7 - Number.ASS.ordinal())][0] == 1) {
              // Find index in hand
              for (int i = 0; i < cards.size(); i++) {
                if ((3 - cards.get(i).getColour().ordinal()) == colour
                    && cards.get(i).getNumber() == Number.ASS) {
                  return i;
                }
              }
            }
          }

          // Play Ten
          for (int colour = 0; colour < 4; colour++) {
            if (controller.getCardProbabilities()[colour * 8
                + (7 - Number.TEN.ordinal())][0] == 1) {
              // Find index in hand
              for (int i = 0; i < cards.size(); i++) {
                if ((3 - cards.get(i).getColour().ordinal()) == colour
                    && cards.get(i).getNumber() == Number.TEN) {
                  return i;
                }
              }
            }
          }

          // Play Jack anyway
          for (int colour = 0; colour < 4; colour++) {
            if (controller.getCardProbabilities()[colour * 8
                + (7 - Number.JACK.ordinal())][0] == 1) {
              // Find index in hand
              for (int i = 0; i < cards.size(); i++) {
                if ((3 - cards.get(i).getColour().ordinal()) == colour
                    && cards.get(i).getNumber() == Number.JACK) {
                  return i;
                }
              }
            }
          }

          // Play random
          return General.playRandomCard(controller);
        }

        // Bot should play 2nd or 3rd card
      } else {
        // First card is trump
        if (trick.get(0).getNumber() == Number.JACK) {
          // Bot has trump
          if (controller.getHasTrump()[0]) {
            int maxColour = 3 - trick.get(0).getNumber().ordinal();
            if (trick.size() > 1 && trick.get(1).getNumber() == Number.JACK
                && (3 - trick.get(1).getColour().ordinal() < maxColour)) {
              maxColour = 3 - trick.get(1).getColour().ordinal();
            }
            int number = 7 - Number.JACK.ordinal();
            // Bot can play a higher Jack
            for (int colour = 0; colour < maxColour; colour++) {
              if (controller.getCardProbabilities()[colour * 8 + number][0] == 1) {
                for (int j = 0; j < cards.size(); j++) {
                  if (cards.get(j).getNumber() == Number.JACK
                      && (3 - cards.get(j).getColour().ordinal()) == colour) {
                    return j;
                  }
                }
              }
            }
            // Bot needs to play trump, but can not win --> play low Jack
            for (int colour = 3; colour > maxColour; colour--) {
              if (controller.getCardProbabilities()[colour * 8 + number][0] == 1) {
                for (int j = 0; j < cards.size(); j++) {
                  if (cards.get(j).getNumber() == Number.JACK
                      && (3 - cards.get(j).getColour().ordinal()) == colour) {
                    return j;
                  }
                }
              }
            }
          }
          // Bot does not have trump, so play low value
          int number = 7;
          while (number >= 0) {
            for (int colour = 0; colour < 4; colour++) {
              if (controller.getCardProbabilities()[colour * 8 + number][0] == 1) {
                for (int j = 0; j < cards.size(); j++) {
                  if ((7 - cards.get(j).getNumber().ordinal()) == number
                      && (3 - cards.get(j).getColour().ordinal()) == colour) {
                    return j;
                  }
                }
              }
            }
          }

          // First card is not trump
        } else {
          // If bot has colour
          if (controller.getHasColour()[3 - trick.get(0).getColour().ordinal()][0]) {
            // Try to win with colour
            if (trick.size() < 2 || trick.get(1).getNumber() != Number.JACK) {
              int value = 7;
              for (int i = 0; i < trick.size(); i++) {
                if (trick.get(i).getColour() == trick.get(0).getColour()
                    && (7 - trick.get(i).getNumber().ordinal()) < value) {
                  value = 7 - trick.get(i).getNumber().ordinal();
                }
              }
              int number = 0;
              while (number < value && controller.getCardProbabilities()[3
                  - trick.get(0).getColour().ordinal()][0] == 0) {
                number++;
              }
              if (number != value) {
                for (int i = 0; i < cards.size(); i++) {
                  if (cards.get(i).getColour() == trick.get(0).getColour()
                      && (7 - cards.get(i).getNumber().ordinal()) == number) {
                    return i;
                  }
                }
              }
            }
            // Play lowest card of colour
            int number = 7;
            while (number >= 0 && controller.getCardProbabilities()[3
                - trick.get(0).getColour().ordinal()][0] == 0) {
              number--;
            }
            if (number != -1) {
              for (int i = 0; i < cards.size(); i++) {
                if (cards.get(i).getColour() == trick.get(0).getColour()
                    && (7 - cards.get(i).getNumber().ordinal()) == number) {
                  return i;
                }
              }
            }
            return General.playRandomCard(controller);
          }
          // Bot does not have colour
          // Check if want to and can play trump
          if (controller.getHasTrump()[0]) {
            int trickValue = 0;
            for (int i = 0; i < trick.size(); i++) {
              trickValue += trick.get(i).getValue();
            }
            // If AI wants to win, play lowest trump
            if (trickValue >= minTrickValue) {
              int number = 7 - Number.JACK.ordinal();
              int colour = 3;
              while (colour >= 0
                  && controller.getCardProbabilities()[colour * 8 + number][0] != 1) {
                colour--;
              }
              if (colour != 0) {
                for (int i = 0; i < cards.size(); i++) {
                  if ((3 - cards.get(i).getColour().ordinal()) == colour
                      && (7 - cards.get(i).getNumber().ordinal()) == number) {
                    return i;
                  }
                }
              }
            }
          }
          // If AI does not want to win or does not have trump, play any low card
          for (int number = 7; number >= 0; number--) {
            for (int colour = 3; colour >= 0; colour--) {
              if (controller.getCardProbabilities()[colour * 8 + number][0] == 1) {
                for (int i = 0; i < cards.size(); i++) {
                  if ((3 - cards.get(i).getColour().ordinal()) == colour
                      && (7 - cards.get(i).getNumber().ordinal()) == number) {
                    return i;
                  }
                }
              }
            }
          }
        }
      }

      // Bot is not declarer
    } else {
      // Get declarer index
      int declarer = 0;
      for (int i = 0; i < controller.getPlayState().getGroup().length; i++) {
        if (controller.getPlayState().getGroup()[i].isDeclarer()) {
          for (int j = 0; j < controller.getPlayer().size(); j++) {
            if (controller.getPlayer().get(j).getName()
                .equals(Integer.toString(controller.getPlayState().getGroup()[i].getId()))) {
              declarer = j;
            }
          }
        }
      }

      // First Card in trick
      if (trick.size() == 0) {
        // Existing trumps, # not own trumps > 0 --> Play low value
        if ((controller.getExistingTrumps() - ownTrumps) > 0
            && controller.getHasTrump()[declarer]) {
          for (int colour = 3; colour >= 0; colour--) {
            for (int number = 7; number >= 0; number--) {
              if (controller.getCardProbabilities()[colour * 8 + number][0] == 1) {
                for (int i = 0; i < cards.size(); i++) {
                  if ((3 - cards.get(i).getColour().ordinal()) == colour
                      && (7 - cards.get(i).getNumber().ordinal()) == number) {
                    return i;
                  }
                }
              }
            }
          }
        }
        // No exisiting trumps --> play high value
        if ((controller.getExistingTrumps() - ownTrumps) == 0) {
          if (ownTrumps > 0) {
            for (int i = 0; i < cards.size(); i++) {
              if (cards.get(i).getNumber() == Number.JACK) {
                return i;
              }
            }
          }
          for (int number = 0; number < 8; number++) {
            for (int colour = 0; colour < 4; colour++) {
              if (controller.getCardProbabilities()[colour * 8 + number][0] == 1) {
                for (int i = 0; i < cards.size(); i++) {
                  if ((7 - cards.get(i).getNumber().ordinal()) == number
                      && (3 - cards.get(i).getColour().ordinal()) == colour) {
                    return i;
                  }
                }
              }
            }
          }
        }
        // Else play random
        return General.playRandomCard(controller);
      }

      // 2nd card in trick
      if (trick.size() == 1) {
        // If predecessor is declarer, try to win, else low value
        if (controller.getPlayedCards()[controller.getPlayedCards().length - 1][declarer] != null) {
          // If first card is trump
          if (trick.get(0).getNumber() == Number.JACK && controller.getHasTrump()[0]) {
            int number = 7 - Number.JACK.ordinal();
            int colourValue = 3 - trick.get(0).getColour().ordinal();
            for (int colour = 0; colour < colourValue; colour++) {
              if (controller.getCardProbabilities()[colour * 8 + number][0] == 1) {
                for (int i = 0; i < cards.size(); i++) {
                  if ((3 - cards.get(i).getColour().ordinal()) == colour
                      && (7 - cards.get(i).getNumber().ordinal()) == number) {
                    return i;
                  }
                }
              }
            }
            for (int colour = 3; colour > colourValue; colour--) {
              if (controller.getCardProbabilities()[colour * 8 + number][0] == 1) {
                for (int i = 0; i < cards.size(); i++) {
                  if ((3 - cards.get(i).getColour().ordinal()) == colour
                      && (7 - cards.get(i).getNumber().ordinal()) == number) {
                    return i;
                  }
                }
              }
            }
            // First Card is not Trump
          } else {
            if (controller.getHasColour()[3 - trick.get(0).getColour().ordinal()][0]) {
              int colour = 3 - trick.get(0).getColour().ordinal();
              int numberValue = 7 - trick.get(0).getNumber().ordinal();
              for (int number = numberValue + 1; number >= 0; number++) {
                if (controller.getCardProbabilities()[colour * 8 + number][0] == 1) {
                  for (int i = 0; i < cards.size(); i++) {
                    if ((3 - cards.get(i).getColour().ordinal()) == colour
                        && (7 - cards.get(i).getNumber().ordinal()) == number) {
                      return i;
                    }
                  }
                }
              }
              for (int number = 7; number > numberValue; number--) {
                if (controller.getCardProbabilities()[colour * 8 + number][0] == 1) {
                  for (int i = 0; i < cards.size(); i++) {
                    if ((3 - cards.get(i).getColour().ordinal()) == colour
                        && (7 - cards.get(i).getNumber().ordinal()) == number) {
                      return i;
                    }
                  }
                }
              }
            }
          }

          // If predecessor is not declarer and threw high value, try to win, else throw
        } else {
          // Needs to play trump
          if (trick.get(0).getNumber() == Number.JACK) {
            // Can play trump
            if (ownTrumps > 0) {
              int number = 7 - Number.JACK.ordinal();
              for (int colour = 3; colour >= 0; colour--) {
                if (controller.getCardProbabilities()[colour * 8 + number][0] == 1) {
                  for (int i = 0; i < cards.size(); i++) {
                    if ((3 - cards.get(i).getColour().ordinal()) == colour
                        && (7 - cards.get(i).getNumber().ordinal()) == number) {
                      return i;
                    }
                  }
                }
              }
            } else {
              for (int number = 0; number < 8; number++) {
                for (int colour = 0; colour < 4; colour++) {
                  if (controller.getCardProbabilities()[colour * 8 + number][0] == 1) {
                    for (int i = 0; i < cards.size(); i++) {
                      if ((3 - cards.get(i).getColour().ordinal()) == colour
                          && (7 - cards.get(i).getNumber().ordinal()) == number) {
                        return i;
                      }
                    }
                  }
                }
              }
            }
          }
          // Needs to play colour
          // Check if AI wants to take the trick
          if (trick.get(0).getValue() > 3) {
            if (controller.getHasColour()[3 - trick.get(0).getColour().ordinal()][0]) {
              int colour = 3 - trick.get(0).getColour().ordinal();
              int numberValue = 7 - trick.get(0).getNumber().ordinal();
              for (int number = 0; number < numberValue; number++) {
                if (controller.getCardProbabilities()[colour * 8 + number][0] == 1) {
                  for (int i = 0; i < cards.size(); i++) {
                    if ((3 - cards.get(i).getColour().ordinal()) == colour
                        && (7 - cards.get(i).getNumber().ordinal()) == number) {
                      return i;
                    }
                  }
                }
              }
            } else if (ownTrumps > 0) {
              int number = 7 - Number.JACK.ordinal();
              for (int colour = 3; colour >= 0; colour--) {
                if (controller.getCardProbabilities()[colour * 8 + number][0] == 1) {
                  for (int i = 0; i < cards.size(); i++) {
                    if ((3 - cards.get(i).getColour().ordinal()) == colour
                        && (7 - cards.get(i).getNumber().ordinal()) == number) {
                      return i;
                    }
                  }
                }
              }
            }
          }
        }
        // low value
        if (trick.get(0).getNumber() == Number.JACK && ownTrumps > 0) {
          for (int colour = 3; colour >= 0; colour--) {
            int number = 7 - Number.JACK.ordinal();
            if (controller.getCardProbabilities()[colour * 8 + number][0] == 1) {
              for (int i = 0; i < cards.size(); i++) {
                if ((3 - cards.get(i).getColour().ordinal()) == colour
                    && (7 - cards.get(i).getNumber().ordinal()) == number) {
                  return i;
                }
              }
            }
          }
        } else if (controller.getHasColour()[3 - trick.get(0).getColour().ordinal()][0]) {
          int colour = 3 - trick.get(0).getColour().ordinal();
          for (int number = 7; number >= 0; number--) {
            if (controller.getCardProbabilities()[colour * 8 + number][0] == 1) {
              for (int i = 0; i < cards.size(); i++) {
                if ((3 - cards.get(i).getColour().ordinal()) == colour
                    && (7 - cards.get(i).getNumber().ordinal()) == number) {
                  return i;
                }
              }

            }
          }
        }
        for (int colour = 3; colour >= 0; colour--) {
          for (int number = 7; number >= 0; number--) {
            if (controller.getCardProbabilities()[colour * 8 + number][0] == 1) {
              for (int i = 0; i < cards.size(); i++) {
                if ((3 - cards.get(i).getColour().ordinal()) == colour
                    && (7 - cards.get(i).getNumber().ordinal()) == number) {
                  return i;
                }
              }
            }
          }
        }
        // Else play random card
        return General.playRandomCard(controller);
      }

      // 3rd card in trick
      if (trick.size() == 2) {
        Card declarerCard =
            controller.getPlayedCards()[controller.getPlayedCards().length - 1][declarer];
        int partner = (declarer == 1) ? 2 : 1;
        Card partnerCard =
            controller.getPlayedCards()[controller.getPlayedCards().length - 1][partner];
        boolean serveTrump = (trick.get(0).getNumber() == Number.JACK);
        // If already won, throw points
        if (serveTrump) {
          if (partnerCard.getNumber() == Number.JACK && (declarerCard.getNumber() != Number.JACK
              || declarerCard.getColour().ordinal() < partnerCard.getColour().ordinal())) {
            // Already won
            if (ownTrumps > 0) {
              int index;
              int number = 7 - Number.JACK.ordinal();
              for (int colour = 3; colour >= 0; colour--) {
                if ((index = General.checkIfPossibleAndGetIndex(controller.getCardProbabilities(),
                    cards, colour, number, 0)) != -1) {
                  return index;
                }
              }
            } else {
              int index;
              for (int number = 0; number < 8; number++) {
                for (int colour = 3; colour >= 0; colour--) {
                  if ((index = General.checkIfPossibleAndGetIndex(controller.getCardProbabilities(),
                      cards, colour, number, 0)) != -1) {
                    return index;
                  }
                }
              }
            }
          } else {
            // Until now, trick is lost with trump
            // Try to win
            if (trick.get(0).getValue() + trick.get(1).getValue() > minTrickValue) {
              if (ownTrumps > 0) {
                int colourValue = 3 - declarerCard.getColour().ordinal();
                int number = 7 - Number.JACK.ordinal();
                int index;
                for (int colour = 0; colour < colourValue; colour++) {
                  if ((index = General.checkIfPossibleAndGetIndex(controller.getCardProbabilities(),
                      cards, colour, number, 0)) != -1) {
                    return index;
                  }
                }
                for (int colour = 3; colour > colourValue; colour--) {
                  if ((index = General.checkIfPossibleAndGetIndex(controller.getCardProbabilities(),
                      cards, colour, number, 0)) != -1) {
                    return index;
                  }
                }
              }
            }
            // Throw low value
            if (ownTrumps > 0) {
              int colourValue = 3 - declarerCard.getColour().ordinal();
              int number = 7 - Number.JACK.ordinal();
              int index;
              for (int colour = 3; colour > colourValue; colour--) {
                if ((index = General.checkIfPossibleAndGetIndex(controller.getCardProbabilities(),
                    cards, colour, number, 0)) != -1) {
                  return index;
                }
              }
            } else {
              int index;
              for (int number = 7; number >= 0; number--) {
                for (int colour = 3; colour >= 0; colour--) {
                  if ((index = General.checkIfPossibleAndGetIndex(controller.getCardProbabilities(),
                      cards, colour, number, 0)) != -1) {
                    return index;
                  }
                }
              }
            }

          }
        } else {
          int colourValue = 3 - trick.get(0).getColour().ordinal();
          if (partnerCard.getNumber() == Number.JACK
              || (partnerCard.getColour() == trick.get(0).getColour()
                  && ((declarerCard.getColour() != trick.get(0).getColour()
                      && declarerCard.getNumber() == Number.JACK)
                      || (declarerCard.getColour() == trick.get(0).getColour() && declarerCard
                          .getNumber().ordinal() < partnerCard.getNumber().ordinal())))) {
            // Already won with colour
            if (controller.getHasColour()[colourValue][0]) {
              int index;
              for (int number = 0; number < 8; number++) {
                if ((index = General.checkIfPossibleAndGetIndex(controller.getCardProbabilities(),
                    cards, colourValue, number, 0)) != -1) {
                  return index;
                }
              }
            } else {
              int index;
              for (int number = 0; number < 8; number++) {
                for (int colour = 3; colour >= 0; colour--) {
                  if ((index = General.checkIfPossibleAndGetIndex(controller.getCardProbabilities(),
                      cards, colour, number, 0)) != -1) {
                    return index;
                  }
                }
              }
            }
          } else {
            // Unitl now, trick is lost with colour
            // Try to win
            if (trick.get(0).getValue() + trick.get(1).getValue() > minTrickValue) {
              if (trick.get(1).getNumber() != Number.JACK) {
                int value = -1;
                if (trick.get(0).getColour() == trick.get(1).getColour()) {
                  if (trick.get(0).getNumber().ordinal() > trick.get(1).getNumber().ordinal()) {
                    value = 7 - trick.get(0).getNumber().ordinal();
                  } else {
                    value = 7 - trick.get(1).getNumber().ordinal();
                  }
                } else {
                  value = 7 - trick.get(0).getNumber().ordinal();
                }
                int index = General.playColour(controller.getCardProbabilities(), cards,
                    3 - trick.get(0).getColour().ordinal(), value, 0, false);
                if (index != -1) {
                  return index;
                }
              }
            }
            // Throw low value
            int index;
            if (controller.getHasColour()[3 - trick.get(0).getColour().ordinal()][0]) {
              if ((index = General.playColour(controller.getCardProbabilities(), cards,
                  3 - trick.get(0).getColour().ordinal(), -1, 0, true)) != -1) {
                return index;
              }
            } else {
              if ((index = General.playValue(controller.getCardProbabilities(), cards, -1,
                  7 - Number.JACK.ordinal(), 0, true)) != -1) {
                return index;
              }
            }
          }
        }
        // Else play random
        return General.playRandomCard(controller);
      }
    }

    return General.playRandomCard(controller);
  }

  /**
   * PlayMode is Suit. AI decides which card to play.
   * 
   * @author fkleinoe
   * @param controller with all information
   * @return int card index
   */
  public static int playCardSuit(AiController controller) {
    int trumpColour = controller.getPlayState().getTrump().ordinal();
    List<Card> cards = controller.getBot().getHand();
    List<Card> trick = controller.getCurrentTrick();
    Card[][] playedCards = controller.getPlayedCards();
    double[][] cardProbabilities = controller.getCardProbabilities();
    boolean[][] hasColour = controller.getHasColour();
    int existingTrumps = controller.getExistingTrumps();
    int ownTrumps = 0;
    int number = 0;
    int colour = 0;
    int value = 0;
    for (int i = 0; i < cards.size(); i++) {
      if (cards.get(i).getNumber() == Number.JACK
          || (3 - cards.get(i).getColour().ordinal() == trumpColour)) {
        ownTrumps++;
      }
    }
    int declarer = 0;
    for (int i = 0; i < controller.getPlayState().getGroup().length; i++) {
      if (controller.getPlayState().getGroup()[i].isDeclarer()) {
        for (int j = 0; j < controller.getPlayer().size(); j++) {
          if (controller.getPlayer().get(j).getName()
              .equals(Integer.toString(controller.getPlayState().getGroup()[i].getId()))) {
            declarer = j;
          }
        }
      }
    }
    int index = -1;
    // Bot is declarer
    if (controller.getBot().isDeclarer()) {
      if (trick.size() == 0) {
        // -> Play highest Trump
        if (ownTrumps > 0) {
          if ((index = General.playTrump(PlayMode.SUIT, cardProbabilities, cards, -1, trumpColour,
              -1, 0, false)) != -1) {
            return index;
          }
        }
        // -> Play highest value of any colour
        if ((index = General.playValue(cardProbabilities, cards, trumpColour,
            7 - Number.JACK.ordinal(), 0, false)) != -1) {
          return index;
        }
      } else {
        // Play 2nd or 3rd card
        // Bot has colour or number is jack
        if (hasColour[3 - trick.get(0).getColour().ordinal()][0]
            || trick.get(0).getNumber() == Number.JACK) {
          // Colour is trump or number is jack
          if (3 - trick.get(0).getColour().ordinal() == trumpColour
              || trick.get(0).getNumber() == Number.JACK) {

            Card enemyCard;
            if (trick.size() > 1 && (trick.get(1).getNumber() == Number.JACK
                || 3 - trick.get(1).getColour().ordinal() == trumpColour)) {
              if ((trick.get(0).getNumber() == Number.JACK && (trick.get(1)
                  .getNumber() != Number.JACK
                  || (trick.get(1).getNumber() == Number.JACK
                      && trick.get(1).getColour().ordinal() < trick.get(0).getColour().ordinal())))
                  || (trick.get(0).getNumber() != Number.JACK
                      && trick.get(1).getNumber() != Number.JACK
                      && trick.get(0).getNumber().ordinal() > trick.get(1).getNumber().ordinal())) {
                enemyCard = trick.get(0);
              } else {
                enemyCard = trick.get(1);
              }
            } else {
              enemyCard = trick.get(0);
            }
            int jackColour;
            if (enemyCard.getNumber() == Number.JACK) {
              jackColour = 3 - enemyCard.getColour().ordinal();
            } else {
              jackColour = -1;
            }
            // Try to win with trump
            if ((index = General.playTrump(PlayMode.SUIT, cardProbabilities, cards, jackColour,
                trumpColour, 7 - enemyCard.getNumber().ordinal(), 0, false)) != -1) {
              return index;
            }
            // Else play low trump
            if ((index = General.playTrump(PlayMode.SUIT, cardProbabilities, cards, jackColour,
                trumpColour, 7 - enemyCard.getNumber().ordinal(), 0, true)) != -1) {
              return index;
            }

          } else {
            Card enemyCard;
            if (trick.size() > 1) {
              if (trick.get(0).getColour() == trick.get(1).getColour()
                  && trick.get(1).getNumber() != Number.JACK
                  && trick.get(0).getNumber().ordinal() > trick.get(1).getNumber().ordinal()) {
                enemyCard = trick.get(0);
              } else {
                enemyCard = trick.get(1);
              }
            } else {
              enemyCard = trick.get(0);
            }
            // Try to win with colour
            if ((index =
                General.playColour(cardProbabilities, cards, 3 - trick.get(0).getColour().ordinal(),
                    7 - enemyCard.getNumber().ordinal(), 0, false)) != -1) {
              return index;
            }
            // Else play lowest card of colour
            if ((index =
                General.playColour(cardProbabilities, cards, 3 - trick.get(0).getColour().ordinal(),
                    7 - enemyCard.getNumber().ordinal(), 0, true)) != -1) {
              return index;
            }
          }
        } else {
          // Bot does not have colour
          // Check if he wants to and can play trump
          int trickValue = 0;
          for (int i = 0; i < trick.size(); i++) {
            trickValue += trick.get(i).getValue();
          }
          if (trickValue > minTrickValue && ownTrumps > 0) {
            if ((index = General.playTrump(PlayMode.SUIT, cardProbabilities, cards, -1, trumpColour,
                -1, 0, true)) != -1) {
              return index;
            }
          }
          // Else play low card
          if ((index = General.playValue(cardProbabilities, cards, trumpColour,
              7 - Number.JACK.ordinal(), 0, true)) != -1) {
            return index;
          }
        }
      }


      // Bot is not declarer
    } else {
      if (trick.size() == 0) {
        if (existingTrumps - ownTrumps == 0) {
          if (ownTrumps > 0) {
            if ((index = General.playTrump(PlayMode.SUIT, cardProbabilities, cards, -1, trumpColour,
                -1, 0, true)) != -1) {
              return index;
            }
          } else {
            if ((index = General.playValue(cardProbabilities, cards, -1, -1, 0, false)) != -1) {
              return index;
            }
          }
        }
        if ((index = General.playValue(cardProbabilities, cards, trumpColour,
            7 - Number.JACK.ordinal(), 0, true)) != -1) {
          return index;
        }

      }
      if (trick.size() == 1) {
        // Declarer has not played yet
        if (playedCards[playedCards.length - 1][declarer] == null) {
          // If card played value > 3 try to win, else throw low value
          if (trick.get(0).getValue() > 3) {
            if (3 - trick.get(0).getColour().ordinal() == trumpColour
                || trick.get(0).getNumber() == Number.JACK) {
              if (ownTrumps > 0) {
                int jackColour;
                if (trick.get(0).getNumber() == Number.JACK) {
                  jackColour = 3 - trick.get(0).getColour().ordinal();
                  value = -1;
                } else {
                  jackColour = -1;
                  value = 7 - trick.get(0).getNumber().ordinal();
                }
                if ((index = General.playTrump(PlayMode.SUIT, cardProbabilities, cards, jackColour,
                    trumpColour, value, 0, false)) != -1) {
                  return index;
                }
                if ((index = General.playTrump(PlayMode.SUIT, cardProbabilities, cards, jackColour,
                    trumpColour, value, 0, true)) != -1) {
                  return index;
                }
              } else {
                if ((index = General.playValue(cardProbabilities, cards, -1, -1, 0, true)) != -1) {
                  return index;
                }
              }
            } else {
              if (hasColour[3 - trick.get(0).getColour().ordinal()][0]) {
                if ((index = General.playColour(cardProbabilities, cards,
                    3 - trick.get(0).getColour().ordinal(), -1, 0, true)) != -1) {
                  return index;
                }
              } else {
                if ((index = General.playValue(cardProbabilities, cards, trumpColour,
                    7 - Number.JACK.ordinal(), 0, true)) != -1) {
                  return index;
                }
              }
            }
          } else {
            // Throw low value
            if (3 - trick.get(0).getColour().ordinal() == trumpColour
                || trick.get(0).getNumber() == Number.JACK) {
              if (ownTrumps > 0) {
                if ((index = General.playTrump(PlayMode.SUIT, cardProbabilities, cards, -1,
                    trumpColour, -1, 0, true)) != -1) {
                  return index;
                } else {
                  if ((index = General.playValue(cardProbabilities, cards, trumpColour,
                      7 - Number.JACK.ordinal(), 0, true)) != -1) {
                    return index;
                  }
                }
              }
            } else {
              if (hasColour[3 - trick.get(0).getColour().ordinal()][0]) {
                if ((index = General.playColour(cardProbabilities, cards,
                    3 - trick.get(0).getColour().ordinal(), -1, 0, true)) != -1) {
                  return index;
                } else {
                  if ((index = General.playValue(cardProbabilities, cards, trumpColour,
                      7 - Number.JACK.ordinal(), 0, true)) != -1) {
                    return index;
                  }
                }
              }
            }
          }
          // Declarer has already played
        } else {
          colour = 3 - trick.get(0).getColour().ordinal();
          number = 7 - trick.get(0).getNumber().ordinal();
          // Try to win, else throw low value
          if (colour == trumpColour || number == 7 - Number.JACK.ordinal()) {
            if (ownTrumps > 0) {
              int jackColour;
              if (number == 7 - Number.JACK.ordinal()) {
                jackColour = colour;
                value = -1;
              } else {
                jackColour = -1;
                value = number;
              }
              if ((index = General.playTrump(PlayMode.SUIT, cardProbabilities, cards, jackColour,
                  trumpColour, value, 0, false)) != -1) {
                return index;
              }
              if ((index = General.playTrump(PlayMode.SUIT, cardProbabilities, cards, jackColour,
                  trumpColour, value, 0, true)) != -1) {
                return index;
              }
            } else {
              if ((index = General.playValue(cardProbabilities, cards, -1, -1, 0, true)) != -1) {
                return index;
              }
            }
          } else {
            if (hasColour[colour][0]) {
              value = 7 - trick.get(0).getNumber().ordinal();
              if ((index =
                  General.playColour(cardProbabilities, cards, colour, value, 0, false)) != -1) {
                return index;
              }
              if ((index =
                  General.playColour(cardProbabilities, cards, colour, value, 0, true)) != -1) {
                return index;
              }
            } else {
              if ((index = General.playValue(cardProbabilities, cards, -1, -1, 0, true)) != -1) {
                return index;
              }
            }
          }

        }
      }
      if (trick.size() == 2) {
        // If already won, throw high value
        Card partnerCard = playedCards[playedCards.length - 1][(declarer == 1) ? 2 : 1];
        Card opponentCard = playedCards[playedCards.length - 1][declarer];
        boolean alreadyWon;
        // Partner played trump
        if (3 - partnerCard.getColour().ordinal() == trumpColour
            || partnerCard.getNumber() == Number.JACK) {
          // opponent did not
          if (3 - opponentCard.getColour().ordinal() != trumpColour
              && opponentCard.getNumber() != Number.JACK) {
            alreadyWon = true;
            // opponent did aswell
          } else {
            if (partnerCard.getNumber() == Number.JACK) {
              if (opponentCard.getNumber() != Number.JACK) {
                alreadyWon = true;
              } else {
                if (partnerCard.getColour().ordinal() > opponentCard.getColour().ordinal()) {
                  alreadyWon = true;
                } else {
                  alreadyWon = false;
                }
              }
            } else {
              if (opponentCard.getNumber() != Number.JACK) {
                if (partnerCard.getNumber().ordinal() > opponentCard.getNumber().ordinal()) {
                  alreadyWon = true;
                } else {
                  alreadyWon = false;
                }
              } else {
                alreadyWon = false;
              }
            }
          }
          // Partner did not play trump
        } else {
          // Did opponent serve colour or played trump?
          if (trick.get(0).getColour() != opponentCard.getColour()
              && opponentCard.getNumber() != Number.JACK
              && 3 - opponentCard.getColour().ordinal() != trumpColour) {
            alreadyWon = true;
          } else {
            // Opponent plyed trump
            if (opponentCard.getNumber() == Number.JACK
                || 3 - opponentCard.getColour().ordinal() == trumpColour) {
              alreadyWon = false;
              // Both played colour
            } else {
              if (partnerCard.getNumber().ordinal() > opponentCard.getNumber().ordinal()) {
                alreadyWon = true;
              } else {
                alreadyWon = false;
              }
            }
          }
        }

        if (alreadyWon) {
          // Throw high value
          if (3 - trick.get(0).getColour().ordinal() == trumpColour
              || trick.get(0).getNumber() == Number.JACK) {
            if (ownTrumps > 0) {
              if ((index =
                  General.playColour(cardProbabilities, cards, trumpColour, -1, 0, false)) != -1) {
                return index;
              }
              if ((index = General.playTrump(PlayMode.SUIT, cardProbabilities, cards, -1,
                  trumpColour, -1, 0, false)) != -1) {
                return index;
              }
            } else {
              if ((index = General.playValue(cardProbabilities, cards, -1, -1, 0, false)) != -1) {
                return index;
              }
            }
          }
          if (hasColour[3 - trick.get(0).getColour().ordinal()][0]) {
            if ((index = General.playColour(cardProbabilities, cards,
                3 - trick.get(0).getColour().ordinal(), -1, 0, false)) != -1) {
              return index;
            }
          } else {
            if ((index = General.playValue(cardProbabilities, cards, -1, -1, 0, false)) != -1) {
              return index;
            }
          }
        } else {
          // Else try to win, if value > minTrickValue
          if (trick.get(0).getValue() + trick.get(1).getValue() > minTrickValue) {
            int enemyColour = 3 - opponentCard.getColour().ordinal();
            int enemyNumber = 7 - opponentCard.getNumber().ordinal();
            int jackColour;
            if (opponentCard.getNumber() == Number.JACK) {
              jackColour = enemyColour;
              value = -1;
            } else {
              jackColour = -1;
              value = enemyNumber;
            }
            if (3 - trick.get(0).getColour().ordinal() == trumpColour
                || trick.get(0).getNumber() == Number.JACK) {
              if (ownTrumps > 0) {
                if ((index = General.playTrump(PlayMode.SUIT, cardProbabilities, cards, jackColour,
                    trumpColour, value, 0, true)) != -1) {
                  return index;
                }
                if ((index = General.playTrump(PlayMode.SUIT, cardProbabilities, cards, jackColour,
                    trumpColour, value, 0, false)) != 1) {
                  return index;
                }
              } else {
                if ((index = General.playValue(cardProbabilities, cards, -1, -1, 0, true)) != -1) {
                  return index;
                }
              }
            }
            if (hasColour[3 - trick.get(0).getColour().ordinal()][0]) {
              if (enemyColour != 3 - trick.get(0).getColour().ordinal()) {
                if ((index = General.playColour(cardProbabilities, cards,
                    3 - trick.get(0).getColour().ordinal(), -1, 0, true)) != -1) {
                  return index;
                }
              } else {
                if ((index = General.playColour(cardProbabilities, cards,
                    3 - trick.get(0).getColour().ordinal(), value, 0, false)) != -1) {
                  return index;
                }
                if ((index = General.playColour(cardProbabilities, cards,
                    3 - trick.get(0).getColour().ordinal(), -1, 0, true)) != -1) {
                  return index;
                }
              }
              // AI does not have the colour
            } else {
              // Declarer played trump
              if (enemyColour != 3 - trick.get(0).getColour().ordinal()) {
                if ((index = General.playTrump(PlayMode.SUIT, cardProbabilities, cards, jackColour,
                    trumpColour, value, 0, false)) != -1) {
                  return index;
                }
                if ((index = General.playValue(cardProbabilities, cards, trumpColour,
                    7 - Number.JACK.ordinal(), 0, true)) != -1) {
                  return index;
                }
              } else {
                if (ownTrumps > 0) {
                  if ((index = General.playTrump(PlayMode.SUIT, cardProbabilities, cards,
                      jackColour, trumpColour, value, 0, true)) != -1) {
                    return index;
                  }
                } else {
                  if ((index =
                      General.playValue(cardProbabilities, cards, -1, -1, 0, true)) != -1) {
                    return index;
                  }
                }
              }
            }

            // Else throw low value
          } else {
            if (3 - trick.get(0).getColour().ordinal() == trumpColour
                || trick.get(0).getNumber() == Number.JACK) {
              if (ownTrumps > 0) {
                if ((index = General.playTrump(PlayMode.SUIT, cardProbabilities, cards, -1,
                    trumpColour, -1, 0, true)) != -1) {
                  return index;
                }
              } else {
                if ((index = General.playValue(cardProbabilities, cards, -1, -1, 0, true)) != -1) {
                  return index;
                }
              }
            }
            if (hasColour[3 - trick.get(0).getColour().ordinal()][0]) {
              if ((index = General.playColour(cardProbabilities, cards,
                  3 - trick.get(0).getColour().ordinal(), -1, 0, true)) != -1) {
                return index;
              }
            } else {
              if ((index = General.playValue(cardProbabilities, cards, -1, -1, 0, true)) != -1) {
                return index;
              }
            }
          }

        }
      }
    }

    return General.playRandomCard(controller);
  }

  /**
   * PlayMode is Null. AI decides which card to play.
   * 
   * @author fkleinoe
   * @param controller with all information
   * @return int Card index
   */
  public static int playCardNull(AiController controller) {
    List<Card> cards = controller.getBot().getHand();
    List<Card> trick = controller.getCurrentTrick();

    // Bot needs to play the first card of the trick
    // He plays the card with the lowest Number-Value
    if (trick.size() == 0) {
      int cardIndex = 0;
      int minValue = 8;
      for (int i = 0; i < cards.size(); i++) {
        if (cards.get(i).getNumber().ordinal() < minValue) {
          cardIndex = i;
          minValue = cards.get(i).getNumber().ordinal();
        }
      }
      return cardIndex;
      // Bot does not play the first card, so he needs to react to the first card
    } else {
      // Bot does not have colour
      if (controller.getHasColour()[3 - trick.get(0).getColour().ordinal()][0]) {

        // Play highest Card of colour, where amount is < 3
        int[] hasColour = new int[4];
        for (int i = 0; i < cards.size(); i++) {
          hasColour[3 - trick.get(0).getColour().ordinal()]++;
        }
        for (int colour = 0; colour < hasColour.length; colour++) {
          if (hasColour[colour] < 3 && hasColour[colour] > 0) {
            int number = 0;
            while (number < 8 && controller.getCardProbabilities()[colour * 8 + number][0] == 0) {
              number++;
            }
            for (int h = 0; h < cards.size(); h++) {
              if ((7 - cards.get(h).getNumber().ordinal()) == number
                  && (3 - cards.get(h).getColour().ordinal()) == colour) {
                return h;
              }
            }
          }
        }
        // No color has amount < 3, play one of the highest cards in general
        int cardIndex = 0;
        int maxValue = 0;
        for (int i = 0; i < cards.size(); i++) {
          if (cards.get(i).getNumber().ordinal() > maxValue) {
            cardIndex = i;
            maxValue = cards.get(i).getNumber().ordinal();
          }
        }
        return cardIndex;

        // Bot has color
      } else {
        // If bot hasColour, play the predecessor, if it does not exist, play the
        // successor
        int colour = 3 - trick.get(0).getColour().ordinal();
        int value = 7 - trick.get(0).getNumber().ordinal();
        int j = value;
        while (j < 8 && controller.getCardProbabilities()[colour * 8 + j][0] == 0) {
          j++;
        }
        if (j != 7) {
          for (int i = 0; i < cards.size(); i++) {
            if ((7 - cards.get(i).getNumber().ordinal()) == j
                && cards.get(i).getColour() == trick.get(0).getColour()) {
              return i;
            }
          }
        }
        j = value;
        while (j >= 0 && controller.getCardProbabilities()[colour * 8 + j][0] == 0) {
          j--;
        }
        if (j != 0) {
          for (int i = 0; i < cards.size(); i++) {
            if ((7 - cards.get(i).getNumber().ordinal()) == j
                && cards.get(i).getColour() == trick.get(0).getColour()) {
              return i;
            }
          }
        }
      }
    }
    return General.playRandomCard(controller);
  }
}
