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
//  public void setTrickCards(Card[] trickCards) {
//    this.trickCards = trickCards;
//  }

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
//  public void playCard(Player player, Card card) throws LogicException {
//
//    if (this.playedCards >= 3) {
//      throw new LogicException("the trick is already filled");
//    } else {
//      this.trickCards[this.playedCards] = card;
//      this.playersOfCards[this.playedCards] = player;
//      this.playedCards++;
//    }
//
//  }


  public void calculateWinner() throws LogicException {
    // TODO Auto-generated method stub
    if(this.ps.getPlayMode().equals("COLOUR")) {
      this.indexWinner = this.calculateWinnerColour();
    }else if(this.ps.getPlayMode().equals("GRAND")) {
      this.indexWinner = this.calculateWinnerGrand();
    }else if(this.ps.getPlayMode().equals("NULL") || this.ps.getPlayMode().equals("NULLOUVERT")) {
      this.indexWinner = this.calculateWinnerNull();
    }else {
      throw new LogicException("Calculating the winner is not possible! (No PlayMode found)");
    }
    
  }

  public int calculateWinnerColour() {
    if (this.compareCardsTrump(this.trickCards[0], this.trickCards[1]) == 0) {
      if (this.compareCardsTrump(this.trickCards[0], this.trickCards[2]) == 0) {
        return 0;
      }else {
        return 2;
      }
    }else {
      if (this.compareCardsTrump(this.trickCards[1], this.trickCards[2]) == 0) {
        return 1;
      }else {
        return 2;
      }
    }
  }
  
  public int compareCardsTrump(Card card1, Card card2) {
    
    if (card1.getColour().equals(ps.getTrump())) {
      if (card2.getColour().equals(ps.getTrump())) {
        return compareCardsNumber(card1, card2);
      }else {
        return 0;
      }
      
    }else {
      if (card2.getColour().equals(ps.getTrump())) {
        return 1;
      }else {
        return compareCardsNumber(card1, card2);
      }
    }
  }
  
  public int compareCardsNumber(Card card1, Card card2) {
    if(card1.getNumber().getRankingNorm() > card2.getNumber().getRankingNorm()) {
      return 0;
    }else {
      return 1;
    }
  }
  
  public int calculateWinnerGrand() {
    return 0;
  }
  
  public int calculateWinnerNull() {
    return 0;
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
