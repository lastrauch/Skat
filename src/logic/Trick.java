package logic;

public class Trick {
  private Card[] trickCards;
  private PlayState ps;
  private int indexWinner;

  public Trick(PlayState ps) {
    trickCards = new Card[3];
    this.ps = ps;
    this.indexWinner = 0;
  }


  // maybe add methods like calculate highest card in trick, depending on the PlayMode

  public Card[] getTrickCards() {
    return this.trickCards;
  }

  public int getIndexWinner() {
    return this.indexWinner;
  }
  
  public Colour getFirstColour() {
    return this.trickCards[0].getColour();
  }
  
  public Card getFirstCard() {
    return this.trickCards[0];
  }
  // public void setTrickCards(Card[] trickCards) {
  // this.trickCards = trickCards;
  // }

  public void setCard1(Card card1) {
    this.trickCards[0] = card1;
  }

  public void setCard2(Card card2) {
    this.trickCards[1] = card2;
  }

  public void setCard3(Card card3) {
    this.trickCards[2] = card3;
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
   * @author Sandra Beate Angelika Fischer
   * @throws LogicException
   */
  public void calculateWinner() throws LogicException {

    System.out.println(this.ps.getPlayMode().toString());
    // calculate winner when PlayMode is Colour
    if (this.ps.getPlayMode() == PlayMode.COLOUR) {
      this.indexWinner = this.calculateWinnerColour();
      //test
      System.out.println("winner: " + this.trickCards[this.indexWinner].getNumber() + " " + " "
          + this.trickCards[this.indexWinner].getColour());

      // calculate winner when PlayMode is Grand
    } else if (this.ps.getPlayMode() == PlayMode.GRAND) {
      this.indexWinner = this.calculateWinnerGrand();
      //test
      System.out.println("winner: " + this.trickCards[this.indexWinner].getNumber() + " " + " "
          + this.trickCards[this.indexWinner].getColour());


      // calculate winner when PlayMode is Null or NullOuvert
    } else if (this.ps.getPlayMode() == PlayMode.NULL
        || this.ps.getPlayMode() == PlayMode.NULLOUVERT) {
      this.indexWinner = this.calculateWinnerNull();
      //test
      System.out.println("winner: " + this.trickCards[this.indexWinner].getNumber() + " " + " "
          + this.trickCards[this.indexWinner].getColour());

    } else {
      throw new LogicException("Calculating the winner is not possible! (No PlayMode found)");
    }

  }

  /**
   * calulates the winning card in the playmode Colour
   * 
   * @author Sandra Beate Angelika Fischer
   * @return index of the winning card
   */
  public int calculateWinnerColour() {

    // test
    System.out.println(this.trickCards[0].getNumber() + " " + this.trickCards[0].getColour());
    System.out.println(this.trickCards[1].getNumber() + " " + this.trickCards[1].getColour());
    System.out.println(this.trickCards[2].getNumber() + " " + this.trickCards[2].getColour());

    if (this.compareCardsColour(this.trickCards[0], this.trickCards[1]) == 0) {
      if (this.compareCardsColour(this.trickCards[0], this.trickCards[2]) == 0) {
        return 0;
      } else {
        return 2;
      }
    } else {
      if (this.compareCardsColour(this.trickCards[1], this.trickCards[2]) == 0) {
        return 1;
      } else {
        return 2;
      }
    }
  }

  /**
   * compares two cards with the trump which we have when playing in playmode colour
   * 
   * @author Sandra Beate Angelika Fischer
   * @param card1
   * @param card2
   * @return which card won (0 = card1, 1= card2)
   */
  public int compareCardsColour(Card card1, Card card2) {

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
   * @author Sandra Beate Angelika Fischer
   * @param card1
   * @param card2
   * @return which card won (0 = card1, 1= card2)
   */
  public int compareNumber(Card card1, Card card2) {

    if (card1.getColour() != this.trickCards[0].getColour()
        && card2.getColour() == this.trickCards[0].getColour()) {
      return 1;

    } else if (card1.getColour() == this.trickCards[0].getColour()
        && card2.getColour() != this.trickCards[0].getColour()) {
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
   * @author Sandra Beate Angelika Fischer
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
   * @author Sandra Beate Angelika Fischer
   * @return index of the winning card
   */
  public int calculateWinnerGrand() {

    // test
    System.out.println(this.trickCards[0].getNumber() + " " + this.trickCards[0].getColour());
    System.out.println(this.trickCards[1].getNumber() + " " + this.trickCards[1].getColour());
    System.out.println(this.trickCards[2].getNumber() + " " + this.trickCards[2].getColour());

    if (this.compareCardsGrand(this.trickCards[0], this.trickCards[1]) == 0) {
      if (this.compareCardsGrand(this.trickCards[0], this.trickCards[2]) == 0) {
        return 0;
      } else {
        return 2;
      }
    } else {
      if (this.compareCardsGrand(this.trickCards[1], this.trickCards[2]) == 0) {
        return 1;
      } else {
        return 2;
      }
    }
  }

  /**
   * compares two cards when the only trump cards are jacks
   * 
   * @author Sandra Beate Angelika Fischer
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
   * @return index of the winning card
   */
  public int calculateWinnerNull() {

    // test
    System.out.println(this.trickCards[0].getNumber() + " " + this.trickCards[0].getColour());
    System.out.println(this.trickCards[1].getNumber() + " " + this.trickCards[1].getColour());
    System.out.println(this.trickCards[2].getNumber() + " " + this.trickCards[2].getColour());

    if (this.compareNumberLowTen(this.trickCards[0], this.trickCards[1]) == 0) {
      if (this.compareNumberLowTen(this.trickCards[0], this.trickCards[2]) == 0) {
        return 0;
      } else {
        return 2;
      }
    } else {
      if (this.compareNumberLowTen(this.trickCards[1], this.trickCards[2]) == 0) {
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
   * @param card1
   * @param card2
   * @return which card won (0 = card1, 1= card2)
   */
  public int compareNumberLowTen(Card card1, Card card2) {

    if (card1.getColour() != this.trickCards[0].getColour()
        && card2.getColour() == this.trickCards[0].getColour()) {
      return 1;

    } else if (card1.getColour() == this.trickCards[0].getColour()
        && card2.getColour() != this.trickCards[0].getColour()) {
      return 0;

    } else if (card1.getNumber().getRankingLowTen() > card2.getNumber().getRankingLowTen()) {
      return 0;
    } else {
      return 1;
    }
  }



  // cant be calculated here --> in play because we need the hand
  public boolean checkIfCardIsPossible(Card card) {
    // TODO Auto-generated method stub
    return false;
  }


  public void endTrick() {
    // TODO Auto-generated method stub

  }

}