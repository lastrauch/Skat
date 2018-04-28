/**
 * 
 */
package logic;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sandr
 *
 */
public class Tools {

  public static Player getDeclarer(Player[] group) {
    for (int i = 0; i < group.length; i++) {
      if (group[i].IsDeclarer()) {
        return group[i];
      }
    }
    return null;
  }

  public static Player[] getOpponents(Player[] group) {
    Player[] opponents = new Player[2];
    Player opp1 = null;
    Player opp2 = null;

    for (int i = 0; i < group.length; i++) {
      if (!group[i].IsDeclarer() && group[i].getPosition() != Position.DEALER) {
        if (opp1 == null) {
          opp1 = group[i];
          opponents[0] = opp1;
        } else {
          opp2 = group[i];
          opponents[1] = opp2;
        }
      }
    }
    return opponents;
  }

  public static Player searchPlayer(Player player, List<Player> group) {
    for (Player p : group) {
      if (p.equals(player)) {
        return p;
      }
    }
    return null;
  }

  public static Player searchPlayer(Player player, Player[] group) {
    for (int i = 0; i < group.length; i++) {
      if (player.equals(group[i])) {
        return player;
      }
    }
    return null;
  }

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
   * Adds arrayList to ArrayList, created for sortHand(s)
   * 
   * @author awesch
   * @param cardsToAdd
   * @param hand2
   * @param start
   * @param length
   */
  public static void addToHand(ArrayList<Card> cardsToAdd, List<Card> hand2, int start,
      int length) {
    int counter = 0;
    for (int i = start; i < start + length; i++) {
      hand2.set(i, cardsToAdd.get(counter));
      counter++;
    }
  }

  /**
   * position (forehand, middlehand, rearhand) changes ater every play
   * 
   * @author sandfisc
   */
  public static void updatePosition(Player[] group) {
    int pointerForehand = searchForehand(group);

    group[pointerForehand].setPosition(Position.FOREHAND);
    group[((pointerForehand + 1) % group.length)].setPosition(Position.MIDDLEHAND);
    group[((pointerForehand + 2) % group.length)].setPosition(Position.REARHAND);


    if (group.length == 4) {
      group[((pointerForehand + 3) % group.length)].setPosition(Position.DEALER);
    }
  }  
  
  public static void updatePosition(List<Player> group) {
    Player[] groupArray = new Player[group.size()];
    
    for(int i = 0; i < group.size(); i++) {
      groupArray[i] = group.get(i);
    }
    
    updatePosition(groupArray);
    
    for(int i = 0; i < group.size(); i++) {
      group.set(i, groupArray[i]);
    }
  }

  public static int searchForehand(Player[] group) {
    for (int i = 0; i < group.length; i++) {
      if (group[i].getPosition() == Position.FOREHAND) {
        return i;
      }
    }
    return 0;
  }

  /**
   * shuffles the cards after they have been initialized
   * 
   * @author awesch
   */
  public static void shuffleCards(List<Card> cards) {
    int index;
    Card temp = null;
    for (int i = 0; i < 32; i++) {
      index = (int) (Math.random() * 32);
      temp = cards.get(i);
      cards.set(i, cards.get(index));
      cards.set(index, temp);
    }
  }
  
  /**
   * @author sandfisc
   * 
   * @param group
   * @return
   */
  public static Player[] getPlayingGroup(Player[] group) {
    // the playing group consists of forehand, middlehand, rarehand, NOT dealer
       Player [] playingGroup = new Player[4];
       
       if (group.length == 4) {
         int index = 0;
         for (int j = 0; j < group.length; j++) {
           if (group[j].getPosition() != Position.DEALER) {
             playingGroup[index] = group[j];
             index++;
           }
         }
       }    
       return playingGroup;
     }

}
