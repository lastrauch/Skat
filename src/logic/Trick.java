package logic;

public class Trick {
  private Card[] trickCards = new Card[3];

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

}
