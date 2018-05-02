package ai;

import java.util.ArrayList;
import java.util.List;
import logic.Card;
import logic.Colour;
import logic.PlayMode;
import logic.PlayState;
import logic.Player;
import logic.Number;

public class Medium {

  public static int playCard(AIController controller) {
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

  public static boolean setBet(AIController controller, int bet) {
    if (controller.getMaxBet() == 0) {
      controller.setMaxBet(Medium.calculateBet(controller));
    }
    if (controller.getMaxBet() >= bet) {
      return true;
    } else {
      return false;
    }
  }

  public static PlayState setPlayState(AIController controller) {
    // TODO If SinglePlay is null????

    PlayState ps = controller.getPlayState();
    ps.getDeclarerStack()
        .addCards(Medium.returnSkat(controller, controller.getSinglePlay().getPlayMode()));
    ps.setSkat(null);
    ps.setTrump(controller.getSinglePlay().getColour());
    // ps.setPlayValue(controller.getMaxBet()); //Will be set by Logic
    ps.setPlayMode(controller.getSinglePlay().getPlayMode());
    ps.setHandGame(false);
    ps.setSchneiderAnnounced(false);
    ps.setSchwarzAnnounced(false);
    ps.setOpen(false);
    // int baseValue; //Will be set by Logic

    controller.setPartner(null);
    List<Player> opponents = new ArrayList<Player>();
    for (int i = 0; i < controller.getAllPlayer().size(); i++) {
      opponents.add(controller.getAllPlayer().get(i));
    }
    controller.setOpponents(opponents);
    switch (ps.getPlayMode()) {
      case GRAND:
        controller.setExistingTrumps(4);
      case SUIT:
        controller.setExistingTrumps(11);
      case NULL:
        controller.setExistingTrumps(0);
    }

    return ps;
  }

  public static boolean askToRekontra(AIController controller) {
    if (controller.getSinglePlay().getCertainty() > 9) {
      return true;
    } else {
      return false;
    }
  }

  public static List<Card> returnSkat(AIController controller, PlayMode playMode) {
    // TODO Check if player has the correct hand after this method. Check in AIController and in
    // Logic
    // TODO Check if Skat/declarerStack is correct in PlayState in AIController and in Logic
    List<Card> cards = controller.getBot().getHand();
    Card[] skatArray = controller.getPlayState().getSkat();
    List<Card> skatList = new ArrayList<Card>();
    for (int i = 0; i < skatArray.length; i++) {
      skatList.add(skatArray[i]);
    }
    List<Card> skatReturn = new ArrayList<Card>();

    cards.addAll(skatList);
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
                skatReturn.add(cards.get(i)); // TODO evtl. Hardcopy is needed
                cards.remove(i);
                continue;
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
                skatReturn.add(cards.get(i)); // TODO evtl. Hardcopy is needed
                cards.remove(i);
                continue;
              }
            }
          }
          j++;
        }
      }
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
    int eight = seven; // You can't win a trick if you have an eight
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
    // If the AI doesn't want to play single, return null
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
      if (trick.size() == 0) {

        for (int colour = 0; colour < 4; colour++) {
          if (controller.getCardProbabilities()[colour * 8 + (7 - Number.JACK.ordinal())][0] == 1) {



          }
        }
      }



      // Bot isn't declarer
    } else {

    }

    return General.playRandomCard(controller);
  }

  public static int playCardSuit(AIController controller) {
    List<Card> cards = controller.getBot().getHand();
    List<Card> trick = controller.getCurrentTrick();

    // Bot is declarer
    if (controller.getBot().isDeclarer()) {



      // Bot isn't declarer
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
      // Bot doesn't play the first card, so he needs to react to the first card
    } else {
      // Bot doesn't have colour
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
        // If bot hasColour, play the predecessor, if it doen't exist, play the successor
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
