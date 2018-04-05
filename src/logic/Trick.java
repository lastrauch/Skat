package logic;

public class Trick {
  private Card[] trickCards;
  private Player[] playersOfCards;
  private int playedCards; // gives us the nr of played cards


  public Trick() {
    trickCards = new Card[3];
    playersOfCards = new Player[3];
    playedCards = 0;
  }


  // maybe add methods like calculate highest card in trick, depending on the PlayMode

  public Card[] getTrickCards() {
    return this.trickCards;
  }

  public void setTrickCards(Card[] trickCards) {
    this.trickCards = trickCards;
  }

  public void setCard1(Card card1) {
    this.trickCards[0] = card1;
  }

  public void setCard2(Card card2) {
    this.trickCards[1] = card2;
  }

  public void setCard3(Card card3) {
    this.trickCards[2] = card3;
  }



  
  public void playCard(Player player, Card card) throws LogicException {

    if (this.playedCards >= 3) {
      throw new LogicException("the trick is already filled");
    } else {
      this.trickCards[this.playedCards] = card;
      this.playersOfCards[this.playedCards] = player;
      this.playedCards++;
    }

  }


  public Player calculateWinner() {
    // TODO Auto-generated method stub
    return null;
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
