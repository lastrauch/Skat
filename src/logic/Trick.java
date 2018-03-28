package logic;

public class Trick {
  private Card[] trickCards = new Card[3];
  private Player[] playersOfCards = new Player[3];
  private int playedCards = 0; // gives us the nr of played cards

  public Trick() {}

  public Trick(Card[] trickCards) {
    this.trickCards = trickCards;
  }

  public Trick(Card card1, Card card2, Card card3) {
    this.trickCards[0] = card1;
    this.trickCards[1] = card2;
    this.trickCards[2] = card3;
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



  // Maybe add exception to throw instead of sysout
  public void playCard(Player player, Card card) {

    if (this.playedCards >= 3) {
      System.out.println("the trick is already filled");
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
