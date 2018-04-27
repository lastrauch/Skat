package logic;


import java.util.ArrayList;
import java.util.List;

public class Trick {
  private PlayState ps;
  private int indexWinner;
  private List<Card> trickCards;

  public Trick(PlayState ps) {
    this.trickCards = new ArrayList<Card>();
    this.ps = ps;
    this.indexWinner = 0;
  }

  public Trick() {
    this.trickCards = new ArrayList<Card>();
  }

  // maybe add methods like calculate highest card in trick, depending on the PlayMode

  public List<Card> getTrickCards() {
    return this.trickCards;
  }

  public int getIndexWinner() {
    return this.indexWinner;
  }

  public Colour getFirstColour() {
    return this.trickCards.get(0).getColour();
  }

  public Card getFirstCard() {
    return this.trickCards.get(0);
  }
  // public void setTrickCards(Card[] trickCards) {
  // this.trickCards = trickCards;
  // }

  public void setCard1(Card card1) {
    this.trickCards.set(0, card1);
  }

  public void setCard2(Card card2) {
    this.trickCards.set(0, card2);
  }

  public void setCard3(Card card3) {
    this.trickCards.set(0, card3);
  }
  
  public void addCard(Card card) {
    this.trickCards.add(card);
  }

  public boolean isFull() {
    if (this.trickCards.size() == 3) {
      return true;
    }else {
      return false;
    }
  }

  //
  // public void playCard(Player player, Card card) throws LogicException {
  //
  // if (this.playedCards >= 3) {
  // throw new LogicException("the trick is already filled");
  // } else {
  // this.trickCards[this.playedCards] = card;
  // this.playersOfCards[this.playedCards] = player;
  // this.playedCards++;
  // }
  //
  // }


  /**
   * the winning card is calculated and the index of the winner is saved in indexWinner (submethods:
   * calculateWinnerColour(), calculateWinnerGrand(), calculateWinnerNull())
   * 
   * @author sandfisc
   * @throws LogicException
   */
  public Player calculateWinner(PlayState ps) throws LogicException {

    // calculate winner when PlayMode is Colour
    if (ps.getPlayMode() == PlayMode.SUIT) {
      return ps.getGroup()[this.calculateWinnerColour(ps)];
      // calculate winner when PlayMode is Grand
    } else if (ps.getPlayMode() == PlayMode.GRAND) {
      return ps.getGroup()[this.calculateWinnerGrand()];

      // calculate winner when PlayMode is Null or NullOuvert
    } else {
      return ps.getGroup()[this.calculateWinnerNull()];
      }
  }

  /**
   * calulates the winning card in the playmode Colour
   * 
   * @author sandfisc
   * @return index of the winning card
   */
  public int calculateWinnerColour(PlayState ps) {
    if (this.compareCardsColour(this.trickCards.get(0), this.trickCards.get(1), ps) == 0) {
      if (this.compareCardsColour(this.trickCards.get(0), this.trickCards.get(2), ps) == 0) {
        return 0;
      } else {
        return 2;
      }
    } else {
      if (this.compareCardsColour(this.trickCards.get(1), this.trickCards.get(2), ps) == 0) {
        return 1;
      } else {
        return 2;
      }
    }
  }

  /**
   * compares two cards with the trump which we have when playing in playmode colour
   * 
   * @author sandfisc
   * @param card1
   * @param card2
   * @return which card won (0 = card1, 1= card2)
   */
  public int compareCardsColour(Card card1, Card card2, PlayState ps) {

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
   * checks if the player watched out for the first played cards colour and which cards number is
   * higher
   * 
   * @author sandfisc
   * @param card1
   * @param card2
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
   * only used to compare jacks, the one with the higher Colour wins
   * 
   * @author sandfisc
   * @param card1
   * @param card2
   * @return which card won (0 = card1, 1= card2)
   */
  public int compareJacks(Card card1, Card card2) {
    if (card1.getColour().compareColourIntern(card2.getColour()) == 1) {
      return 0;
    } else {
      return 1;
    }
  }


  /**
   * calculates the winner when the PlayMode is Grand
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
   * compares two cards when the only trump cards are jacks
   * 
   * @author sandfisc
   * @param card1
   * @param card2
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
   * calculates the winner when the PlayMode is Null/NullOuvert
   * 
   * @author sandfisc
   * @return index of the winning card
   */
  public int calculateWinnerNull() {

    // test
    // System.out.println(this.trickCards[0].getNumber() + " " + this.trickCards[0].getColour());
    // System.out.println(this.trickCards[1].getNumber() + " " + this.trickCards[1].getColour());
    // System.out.println(this.trickCards[2].getNumber() + " " + this.trickCards[2].getColour());

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
   * checks if the player watched out for the first played cards colour and which cards number is
   * higher (with low ten)
   * 
   * @author sandfisc
   * @param card1
   * @param card2
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

}
