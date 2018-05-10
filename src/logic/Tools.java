
package logic;

import java.util.ArrayList;
import java.util.List;

public class Tools {

  /**
   * returns a resorted hand.
   * 
   * @author awesch
   * @param hand to sort
   * @param ps playState
   */
  public static ArrayList<Card> sortHand(ArrayList<Card> hand, PlayState ps) {
    // possible different orders : colour, grand, null(nullouvert)

    int counter = 0; // saves nr of jacks/ nr of already sorted cards
    ArrayList<Card> jacks = new ArrayList<Card>();

    // first step: jacks at the beginning
    if (ps.getPlayMode() == PlayMode.SUIT | ps.getPlayMode() == PlayMode.GRAND
        | ps.getPlayMode() == null) {
      Card temp;
      for (int i = 0; i < hand.size(); i++) {
        if (hand.get(i).getNumber() == Number.JACK) {
          jacks.add(hand.get(i));
          temp = hand.get(counter);
          hand.set(counter, hand.get(i));
          hand.set(i, temp);
          counter++;
        }
      }

    }

    ps.sortCardsByColour(jacks);


    // seperate different colours
    ArrayList<Card> clubs = new ArrayList<Card>();
    ArrayList<Card> spades = new ArrayList<Card>();
    ArrayList<Card> hearts = new ArrayList<Card>();
    ArrayList<Card> diamonds = new ArrayList<Card>();

    for (int i = counter; i < hand.size(); i++) {
      Colour c = hand.get(i).getColour();
      switch (c) {
        case CLUBS:
          clubs.add(hand.get(i));
          break;
        case SPADES:
          spades.add(hand.get(i));
          break;
        case HEARTS:
          hearts.add(hand.get(i));
          break;
        case DIAMONDS:
          diamonds.add(hand.get(i));
          break;
        default:
          break;
      }
    }

    // sort different colours depending on the Playmode by their numbers
    if (ps.getPlayMode() == PlayMode.SUIT | ps.getPlayMode() == PlayMode.GRAND
        | ps.getPlayMode() == null) {
      ps.sortCardsValueNorm(clubs);
      ps.sortCardsValueNorm(spades);
      ps.sortCardsValueNorm(hearts);
      ps.sortCardsValueNorm(diamonds);
    } else if (ps.getPlayMode() == PlayMode.NULL) {
      ps.sortCardsValueLowTen(clubs);
      ps.sortCardsValueLowTen(spades);
      ps.sortCardsValueLowTen(hearts);
      ps.sortCardsValueLowTen(diamonds);
    }

    // put the different stacks back together in the right order
    // first: jacks - filled only if playmode colour grand or null(before mode is chosen)
    Tools.addToHand(jacks, hand, 0, jacks.size());

    // second: trump - only if PlayMode colour
    if (ps.getPlayMode() == PlayMode.SUIT) {
      Colour trump = ps.getTrump();
      switch (trump) {
        case CLUBS:
          Tools.addToHand(clubs, hand, counter, clubs.size());
          counter += clubs.size();
          break;
        case SPADES:
          Tools.addToHand(spades, hand, counter, spades.size());
          counter += spades.size();
          break;
        case HEARTS:
          Tools.addToHand(hearts, hand, counter, hearts.size());
          counter += hearts.size();
          break;
        case DIAMONDS:
          Tools.addToHand(diamonds, hand, counter, diamonds.size());
          counter += diamonds.size();
          break;
        default:
          break;
      }
    }


    // add all other colours (if not trump)
    if (ps.getTrump() != Colour.CLUBS) {
      Tools.addToHand(clubs, hand, counter, clubs.size());
      counter += clubs.size();
    }

    if (ps.getTrump() != Colour.SPADES) {
      Tools.addToHand(spades, hand, counter, spades.size());
      counter += spades.size();
    }

    if (ps.getTrump() != Colour.HEARTS) {
      Tools.addToHand(hearts, hand, counter, hearts.size());
      counter += hearts.size();
    }

    if (ps.getTrump() != Colour.DIAMONDS) {
      Tools.addToHand(diamonds, hand, counter, diamonds.size());
      counter += diamonds.size();
    }

    return hand;
  }

  /**
   * Adds arrayList to ArrayList, created for sortHand(s).
   * 
   * @author awesch
   * @param cardsToAdd to the hand
   * @param hand2 hand to add them on
   * @param start where to start the play
   * @param length how long the period is
   */
  public static void addToHand(ArrayList<Card> cardsToAdd, List<Card> hand2, int start,
      int length) {
    int counter = 0;
    for (int i = start; i < start + length; i++) {
      hand2.set(i, cardsToAdd.get(counter));
      counter++;
    }
  }

}
