package logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Trick implements Serializable {

  /*
   * This class includes all methods which belong to a trick. A trick has to be created (by the
   * logic) and filled (by Players). Then a winner can be calculated.
   */

  // The constructor initializes the List "trickCards"

  // addCard: void
  // Adds the given card to the trickCards

  // isFull: boolean
  // Checks if 3 cards are saved in the trickCards

  // calculateWinner: Player
  // The winner of the trick is calculated with the PlayState (parameter) and the trickCards

  // calculateWinnerSuit: int
  // Calculates the winning card of the trick and returns the index. Therefore the methode compares
  // two cards
  // and the higher one is compared to the third card of the trick.
  // submethod: compareCardsSuit

  // calculateWinnerGrand: int
  // Calculates the winning card of the trick and returns the index. Therefore the methode compares
  // two cards
  // and the higher one is compared to the third card of the trick.
  // submethod: compareCardsGrand

  // calculateWinnerNull: int
  // Calculates the winning card of the trick and returns the index. Therefore the methode compares
  // two cards
  // and the higher one is compared to the third card of the trick.
  // submethod: compareNumberLowTen

  // compareCardsSuit: int
  // Compares two cards and returns 0 or 1 depending on which card is higher
  // submethods: compareJacks, compareNumber

  // compareCardsGrand: int
  // Compares two cards and returns 0 or 1 depending on which card is higher
  // submethods: compareJacks, compareNumber

  // compareNumber: int
  // Checks which given card is higher depending on the number (with high ten)

  // compareNumberLowTen: int
  // Checks which given card is higher depending on the number (with high ten)

  // compareJacks: int
  // Checks which given card is higher depending on the Jack

  private static final long serialVersionUID = 1L;
  private List<Card> trickCards; // the list of trick cards includes 0,1,2 or 3 cards
  private List<Player> cardPlayers;

  /* ------------------- CONSTRUCTOR ------------------------------------------------- */


  public Trick() {
    this.trickCards = new ArrayList<Card>();
    cardPlayers = new ArrayList<Player>();
  }

  public Trick(List<Card> trickCards, List<Player> cardPlayers) {
    this.trickCards = trickCards;
    this.cardPlayers = cardPlayers;
  }

  /* ------------------- HANDLING OF A TRICK ----------------------------------------- */

  /**
   * adds the given card to the trickCards.
   * 
   * @author sandfisc
   * @param card to add
   * @param player who played it
   */
  public void addCard(Card card, Player player) {
    this.trickCards.add(card);
    this.cardPlayers.add(player);
  }

  /**
   * checks if 3 cards are saved in the trickCards.
   * 
   * @author sandfisc
   * @return if trick is full / over
   */
  public boolean isFull() {
    if (this.trickCards.size() == 3) {
      return true;
    } else {
      return false;
    }
  }


  /* -------------------- CALCULATE WINNER ------------------------------------------- */

  /**
   * the winning card is calculated and the winner is returned submethods (depending on PlayMode):
   * calculateWinnerColour(), calculateWinnerGrand(), calculateWinnerNull()).
   * 
   * @author sandfisc
   * @param ps PlayState
   * @return winner of the trick
   * @throws LogicException if not possible
   */
  public Player calculateWinner(PlayState ps) throws LogicException {

    // calculate winner when PlayMode is Suit
    if (ps.getPlayMode() == PlayMode.SUIT) {
      return this.cardPlayers.get(this.calculateWinnerSuit(ps));
      // calculate winner when PlayMode is Grand
    } else if (ps.getPlayMode() == PlayMode.GRAND) {
      return this.cardPlayers.get(this.calculateWinnerGrand());
      // calculate winner when PlayMode is Null or NullOuvert
    } else {
      return this.cardPlayers.get(this.calculateWinnerNull());
    }
  }

  /**
   * calulates the winning card in the playmode Suit.
   * 
   * @author sandfisc
   * @param ps PlayState
   * @return index of the winning card
   */
  public int calculateWinnerSuit(PlayState ps) {
    if (this.compareCardsSuit(this.trickCards.get(0), this.trickCards.get(1), ps) == 0) {
      if (this.compareCardsSuit(this.trickCards.get(0), this.trickCards.get(2), ps) == 0) {
        return 0;
      } else {
        return 2;
      }
    } else {
      if (this.compareCardsSuit(this.trickCards.get(1), this.trickCards.get(2), ps) == 0) {
        return 1;
      } else {
        return 2;
      }
    }
  }

  /**
   * calculates the winner when the PlayMode is Grand.
   * 
   * @author sandfisc
   * @return index of the winning card
   */
  public int calculateWinnerGrand() {

    if (this.compareCardsGrand(this.trickCards.get(0), this.trickCards.get(1)) == 0) {
      if (this.compareCardsGrand(this.trickCards.get(0), this.trickCards.get(2)) == 0) {
        return 0;
      } else {
        return 2;
      }
    } else {
      if (this.compareCardsGrand(this.trickCards.get(1), this.trickCards.get(2)) == 0) {
        return 1;
      } else {
        return 2;
      }
    }
  }

  /**
   * calculates the winner when the PlayMode is Null/NullOuvert.
   * 
   * @author sandfisc
   * @return index of the winning card
   */
  public int calculateWinnerNull() {

    if (this.compareNumberLowTen(this.trickCards.get(0), this.trickCards.get(1)) == 0) {
      if (this.compareNumberLowTen(this.trickCards.get(0), this.trickCards.get(2)) == 0) {
        return 0;
      } else {
        return 2;
      }
    } else {
      if (this.compareNumberLowTen(this.trickCards.get(1), this.trickCards.get(2)) == 0) {
        return 1;
      } else {
        return 2;
      }
    }
  }

  /**
   * compares two cards with the current trump.
   * 
   * @author sandfisc
   * @param card1 to compare
   * @param card2 to compare
   * @param ps PlayState
   * @return which card won (0 = card1, 1= card2)
   */
  public int compareCardsSuit(Card card1, Card card2, PlayState ps) {

    // search for jacks and compare them if necessary
    if (card1.getNumber() == Number.JACK) {
      if (card2.getNumber() == Number.JACK) {
        return this.compareJacks(card1, card2);
      } else {
        return 0;
      }
    } else {
      if (card2.getNumber() == Number.JACK) {
        return 1;
      }
    }

    // search for trumps and compare when necessary (when both are trumps or both are not)
    if (card1.getColour().equals(ps.getTrump())) {
      if (card2.getColour().equals(ps.getTrump())) {
        return compareNumber(card1, card2);
      } else {
        return 0;
      }

    } else {
      if (card2.getColour().equals(ps.getTrump())) {
        return 1;
      } else {
        return compareNumber(card1, card2);
      }
    }
  }

  /**
   * compares two cards when the only trump cards are jacks.
   * 
   * @author sandfisc
   * @param card1 to compare
   * @param card2 to compare
   * @return which card won (0 = card1, 1= card2)
   */
  public int compareCardsGrand(Card card1, Card card2) {

    // search for jacks (only trump) compare numbers if both cards are jacks or both are not
    if (card1.getNumber() == Number.JACK) {
      if (card2.getNumber() == Number.JACK) {
        return this.compareJacks(card1, card2);
      } else {
        return 0;
      }
    } else {
      if (card2.getNumber() == Number.JACK) {
        return 1;
      } else {
        return this.compareNumber(card1, card2);
      }
    }
  }

  /**
   * checks if the player watched out for the first played cards colour and which cards number is
   * higher.
   * 
   * @author sandfisc
   * @param card1 to compare
   * @param card2 to compare
   * @return which card won (0 = card1, 1= card2)
   */
  public int compareNumber(Card card1, Card card2) {

    if (card1.getColour() != this.trickCards.get(0).getColour()
        && card2.getColour() == this.trickCards.get(0).getColour()) {
      return 1;

    } else if (card1.getColour() == this.trickCards.get(0).getColour()
        && card2.getColour() != this.trickCards.get(0).getColour()) {
      return 0;

    } else if (card1.getNumber().getRankingNorm() > card2.getNumber().getRankingNorm()) {
      return 0;
    } else {
      return 1;
    }
  }

  /**
   * checks if the player watched out for the first played cards colour and which cards number is
   * higher (with low ten).
   * 
   * @author sandfisc
   * @param card1 to compare
   * @param card2 to compare
   * @return which card won (0 = card1, 1= card2)
   */
  public int compareNumberLowTen(Card card1, Card card2) {

    if (card1.getColour() != this.trickCards.get(0).getColour()
        && card2.getColour() == this.trickCards.get(0).getColour()) {
      return 1;

    } else if (card1.getColour() == this.trickCards.get(0).getColour()
        && card2.getColour() != this.trickCards.get(0).getColour()) {
      return 0;

    } else if (card1.getNumber().getRankingLowTen() > card2.getNumber().getRankingLowTen()) {
      return 0;
    } else {
      return 1;
    }
  }

  /**
   * only used to compare jacks, the one with the higher Colour wins.
   * 
   * @author sandfisc
   * @param card1 to compare
   * @param card2 to compare
   * @return which card won (0 = card1, 1= card2)
   */
  public int compareJacks(Card card1, Card card2) {
    if (card1.getColour().compareColourIntern(card2.getColour()) == 1) {
      return 0;
    } else {
      return 1;
    }
  }

  /* ----------------------------- COPY --------------------------------------------------- */

  /**
   * returns a deep copy of this trick.
   * 
   * @return copy of this trick
   */
  public Trick copyMe() {
    List<Card> newTrickCards = new ArrayList<Card>();
    for (Card c : this.trickCards) {
      newTrickCards.add(c);
    }
    List<Player> newCardPlayers = new ArrayList<Player>();
    for (Player p : this.cardPlayers) {
      newCardPlayers.add(p);
    }
    Trick newTrick = new Trick(newTrickCards, newCardPlayers);
    return newTrick;
  }

  /* ------------------------ SETTER AND GETTER ------------------------------------------- */

  public List<Card> getTrickCards() {
    return this.trickCards;
  }

  public Colour getFirstColour() {
    return this.trickCards.get(0).getColour();
  }

  public Card getFirstCard() {
    return this.trickCards.get(0);
  }

  public void setCard1(Card card1) {
    this.trickCards.set(0, card1);
  }

  public void setCard2(Card card2) {
    this.trickCards.set(0, card2);
  }

  public void setCard3(Card card3) {
    this.trickCards.set(0, card3);
  }
}
