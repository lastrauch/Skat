package ai;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import logic.Card;
import logic.Colour;
import logic.PlayMode;
import logic.PlayState;
import logic.Number;

public class Medium {

  // This is a static class to implement methods to play with a medium AI.
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



  // TODO Description for internal methods

  private static final int minTrickValue = 7; // The value the trick needs to have, so that the AI
                                              // wants to win it
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
  public static boolean askForBet(AIController controller, int bet) {
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
   * @param controller
   * @return boolean
   */
  public static boolean askToTakeUpSkat(AIController controller) {
    return true;
  }

  /**
   * Gives back the cards, the AI wants to put on the skat after picking it up.
   * 
   * @author fkleinoe
   * @param controller
   * @return List(Card)
   */
  public static List<Card> switchSkat(AIController controller) {
    if (controller.getSinglePlay() != null && controller.getSinglePlay().getPlayMode() != null) {
      return Medium.returnSkat(controller, controller.getSinglePlay().getPlayMode());
    } else {
      return Arrays.asList(controller.getPlayState().getSkat());
    }
  }

  /**
   * If the AI won the auction, it needs to return a PlayState.
   * 
   * @author fkleinoe
   * @param controller
   * @return PlayState
   */
  public static PlayState askToSetPlayState(AIController controller) {
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
    switch (controller.getPlayState().getPlayMode()) {
      case GRAND:
        return Medium.playCardGrand(controller);
      case SUIT:
        return Medium.playCardSuit(controller);
      case NULL:
        return Medium.playCardNull(controller);
    }
    return General.playRandomCard(controller);
  }

  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // Internal Methods
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  public static List<Card> returnSkat(AIController controller, PlayMode playMode) {
    List<Card> skat = Arrays.asList(controller.getPlayState().getSkat());
    List<Card> skatReturn = new ArrayList<Card>();

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

    while (skatReturn.size() < 2) {
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
              }
            }
          }
          j++;
        }
      }
    }

    for (int i = 0; i < skatReturn.size(); i++) {
      controller.setCardProbability(0, 3 - skatReturn.get(i).getColour().ordinal(),
          7 - skatReturn.get(i).getNumber().ordinal(), 0);
    }

    return skatReturn;
  }

  private static int calculateBet(AIController controller) {
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
        }
        return gameLevel * colourValue;
      }
    }
    return -1;
  }

  private static SinglePlay playSingle(AIController controller) {
    List<Card> cards = controller.getBot().getHand();

    boolean wantsGrand = false;
    boolean wantsSuit = false;
    boolean wantsNull = false;
    double certGrand = 0; // Certainty to play Grand, integer in [0;10]
    double certSuit = 0; // Certainty to play Suit, integer in [0;10]
    double certNull = 0; // Certainty to play Null, integer in [0;10]

    boolean hasColour[] = new boolean[4];
    boolean hasJack[] = new boolean[4];

    // TODO put the values as global variables
    // TODO change names to jackSpadesGrand etc.
    // Check if AI wants to play Grand
    double minCertGrand = 25;
    int jackSpades = 9;
    int jackClubs = 7;
    int jackHearts = 5;
    int jackDiamonds = 3;
    int ace = 2;
    int ten = 1;
    int twoJacksButNotSpadesAndClubs = -5;
    double rowFactorPerCard = 1.25;

    // Highest certainty value one can get for grand
    double scaleGrand = 0;
    scaleGrand += jackSpades + jackClubs + jackHearts + jackDiamonds; // Bot has four jacks
    scaleGrand += 4 * ace + 2 * ten; // Bot has four aces and two tens
    scaleGrand += 2 * rowFactorPerCard; // RowFactor because of the two tens

    // Single Cards value
    if (controller.getCardProbabilities()[4][0] == 1) {
      certGrand += jackSpades;
      hasJack[0] = true;
    }
    if (controller.getCardProbabilities()[12][0] == 1) {
      certGrand += jackClubs;
      hasJack[1] = true;
    }
    if (controller.getCardProbabilities()[20][0] == 1) {
      certGrand += jackHearts;
      hasJack[2] = true;
    }
    if (controller.getCardProbabilities()[28][0] == 1) {
      certGrand += jackDiamonds;
      hasJack[3] = true;
    }
    for (int i = 0; i < cards.size(); i++) {
      // Aces getting checked in the deck values in row-rule
      // Ten should also be worth something, even no ace is in the hand
      if (cards.get(i).getNumber() == Number.TEN) {
        certGrand += ten;
      }
      if (cards.get(i).getNumber() != Number.JACK) {
        hasColour[3 - cards.get(i).getColour().ordinal()] = true;
      }
    }

    // Deck value
    if (!hasJack[0] && !hasJack[1])
      certGrand += twoJacksButNotSpadesAndClubs;
    for (int i = 0; i < 4; i++) {
      int j = 0;
      double row = ace / rowFactorPerCard;
      while (controller.getCardProbabilities()[8 * i + j][0] == 1 || j < 8) {
        row *= rowFactorPerCard;
        j++;
      }
      certGrand += row;
    }

    if (certGrand >= minCertGrand)
      wantsGrand = true;
    certGrand = (certGrand / scaleGrand) * 10;

    // Check if AI wants to play Suit
    double minCertSuit = 25;
    Colour suitColour = Colour.CLUBS;
    int[] numberOfColour = new int[4];
    jackSpades = 9;
    jackClubs = 7;
    jackHearts = 5;
    jackDiamonds = 3;
    ace = 2;
    ten = 1;
    int missingColour = 3;
    double colourFactorPerCard = 1.25;

    // Highest certainty value one can get for suit
    double scaleSuit = 0;
    scaleSuit += jackSpades + jackClubs + jackHearts + jackDiamonds; // Bot has four Jacks
    scaleSuit += 1 * ace + 1 * ten; // Bot has one ace and one ten
    scaleSuit += 3 * missingColour; // Bot has three colours missing (ignoring jacks)
    scaleSuit += Math.pow(colourFactorPerCard, 6); // Bot has six cards from the same colour

    // Single Cards value
    if (hasJack[0]) {
      certSuit += jackSpades;
    }
    if (hasJack[1]) {
      certSuit += jackClubs;
    }
    if (hasJack[2]) {
      certSuit += jackHearts;
    }
    if (hasJack[3]) {
      certSuit += jackDiamonds;
    }
    for (int i = 0; i < cards.size(); i++) {
      if (cards.get(i).getNumber() == Number.ASS) {
        certSuit += ace;
      }
      if (cards.get(i).getNumber() == Number.TEN) {
        certSuit += ten;
      }
      if (cards.get(i).getNumber() != Number.JACK) {
        numberOfColour[3 - cards.get(i).getColour().ordinal()]++;
      }
    }

    // Deck value
    for (int i = 0; i < hasColour.length; i++) {
      if (!hasColour[i])
        certSuit += missingColour;
    }
    int maxNumber = 0;
    for (int i = 0; i < numberOfColour.length; i++) {
      if (maxNumber < numberOfColour[i]) {
        maxNumber = numberOfColour[i];
        switch (i) {
          case 0:
            suitColour = Colour.SPADES;
          case 1:
            suitColour = Colour.CLUBS;
          case 2:
            suitColour = Colour.HEARTS;
          case 3:
            suitColour = Colour.DIAMONDS;
        }
      }
    }
    certSuit += Math.pow(colourFactorPerCard, maxNumber);

    if (certSuit >= minCertSuit)
      wantsSuit = true;
    certSuit = (certSuit / scaleSuit) * 10;

    // CHeck if AI wants to play Null
    double minCertNull = 25;
    int seven = 4;
    int eight = seven; // You can not win a trick if you have an eight
    int nine = 2;
    ten = 1;
    missingColour = 3;
    rowFactorPerCard = 1.25;
    int gap[] = new int[4]; // If the row has just one missing card, it is as good as a full row

    double scaleNull = 0;
    scaleNull += 4 * seven + 4 * eight + 2 * nine; // Bot has four sevens, four eights and two nines
    scaleNull += 2 * Math.pow(1.25, 3) + 2 * Math.pow(1.25, 2); // Therefore the bot has two rows of
                                                                // length three and two rows of
                                                                // length two

    for (int i = 0; i < cards.size(); i++) {
      switch (cards.get(i).getNumber()) {
        case SEVEN:
          certNull += seven;
          break;
        case EIGHT:
          certNull += eight;
          break;
        case NINE:
          certNull += nine;
          break;
        case TEN:
          certNull += ten;
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
            row *= rowFactorPerCard;
          } else {
            gap[i]++;
          }
          j++;
        }
        if (row != 1) {
          certNull += row;
        }
      } else {
        certNull += missingColour;
      }
    }

    if (certNull >= minCertNull)
      wantsNull = true;
    certNull = (certNull / scaleNull) * 10;

    SinglePlay sP;
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
        sP = new SinglePlay(PlayMode.GRAND, null, certGrand);
        return sP;
      } else if (wantsNull) {
        sP = new SinglePlay(PlayMode.NULL, null, certNull);
        return sP;
      } else {
        sP = new SinglePlay(PlayMode.SUIT, suitColour, certSuit);
        return sP;
      }
    } else {
      return null;
    }
  }

  public static int playCardGrand(AIController controller) {
    // TODO
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
      // If predecessor is declarer, try to win, else low value
      // If predecessor is not declarer and threw high value, try to win, else throw
      // low value
      // Else play random card

      // 3rd card in trick
      // If already on, throw points
      // If not, try to win, if value > 10 with own card
      // If do not want to win, throw low value card
      // Else play random
    }

    return General.playRandomCard(controller);
  }

  public static int playCardSuit(AIController controller) {
    // TODO

    List<Card> cards = controller.getBot().getHand();
    List<Card> trick = controller.getCurrentTrick();

    // Bot is declarer
    if (controller.getBot().isDeclarer()) {

      // Play 2nd or 3rd card
      // Bot has colour
      if (controller.getHasColour()[3 - trick.get(0).getColour().ordinal()][0]
          || (trick.get(0).getColour() == controller.getPlayState().getTrump()
              && controller.getHasTrump()[0])) {
        // Colour is trump
        if (trick.get(0).getColour() == controller.getPlayState().getTrump()) {
          // Play highest Jack
          int number = 7 - Number.JACK.ordinal();
          for (int colour = 0; colour < 4; colour++) {
            if (controller.getCardProbabilities()[colour * 8 + number][0] == 1) {
              for (int j = 0; j < cards.size(); j++) {
                if ((3 - cards.get(j).getColour().ordinal()) == colour
                    && cards.get(j).getNumber() == Number.JACK) {
                  return j;
                }
              }
            }
          }
        }
        // Try to win with colour
        int colour = 3 - trick.get(0).getColour().ordinal();
        if (trick.size() == 1) {
          int number = 0;
          while (number < 8 && controller.getCardProbabilities()[colour * 8 + number][0] == 0) {
            number++;
          }
          for (int j = 0; j < cards.size(); j++) {
            if ((3 - cards.get(j).getColour().ordinal()) == colour
                && (7 - cards.get(j).getNumber().ordinal()) == number) {
              return j;
            }
          }
        }
        if (trick.size() == 2) {
          if (trick.get(0).getColour() == trick.get(1).getColour()
              || trick.get(1).getNumber() != Number.JACK) {
            int value;
            if (trick.get(0).getNumber().ordinal() > trick.get(1).getNumber().ordinal()) {
              value = 7 - trick.get(0).getNumber().ordinal();
            } else {
              value = 7 - trick.get(1).getNumber().ordinal();
            }
            // Try to win
            int number = value;
            while (number >= 0 && controller.getCardProbabilities()[colour * 8 + number][0] == 0) {
              number--;
            }
            if (number != -1) {
              for (int j = 0; j < cards.size(); j++) {
                if ((3 - cards.get(j).getColour().ordinal()) == colour
                    && (7 - cards.get(j).getNumber().ordinal()) == number) {
                  return j;
                }
              }
            }
            // Else play lowest card of colour
            number = 7;
            while (number > value
                && controller.getCardProbabilities()[colour * 8 + number][0] == 0) {
              number--;
            }
            if (number != value) {
              for (int j = 0; j < cards.size(); j++) {
                if ((3 - cards.get(j).getColour().ordinal()) == colour
                    && (7 - cards.get(j).getNumber().ordinal()) == number) {
                  return j;
                }
              }
            }
          }
          return General.playRandomCard(controller);
        }

        // Bot does not have colour
      } else {
        // Check if he wants to and can play trump
      }
      // Else play low card
      return General.playRandomCard(controller);

      // Bot is not declarer
    } else {

    }
    return General.playRandomCard(controller);
  }

  public static int playCardNull(AIController controller) {
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
      if (controller.getHasColour()[4 - trick.get(0).getColour().ordinal()][0]) {

        // Play highest Card of colour, where amount is < 3
        int[] hasColour = new int[4];
        for (int i = 0; i < cards.size(); i++) {
          hasColour[4 - trick.get(0).getColour().ordinal()]++;
        }
        for (int colour = 0; colour < hasColour.length; colour++) {
          if (hasColour[colour] < 3 && hasColour[colour] > 0) {
            int number = 0;
            while (controller.getCardProbabilities()[colour * 8 + number][0] == 0) {
              number++;
            }
            for (int h = 0; h < cards.size(); h++) {
              if ((8 - cards.get(h).getNumber().ordinal()) == number
                  && (4 - cards.get(h).getColour().ordinal()) == colour) {
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
        while (j < 7 && controller.getCardProbabilities()[colour * 8 + j][0] == 0) {
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
