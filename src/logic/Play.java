package logic;

public class Play implements PlayInterface {
  private Card[] cards = new Card[32];
  private Trick[] tricks = new Trick[10];
  private PlayState ps;

  public Play() {
    this.initializeCards();
  }

  public void initializeCards() {
    
    int counter = 0;
    for (int i = 1; i <= 4; i++) {
      Colour col = null;
      switch (i) {
        case 1:
          col = Colour.DIAMONDS;
          break;
        case 2:
          col = Colour.HEARTS;
          break;
        case 3:
          col = Colour.SPADES;
          break;
        case 4:
          col = Colour.CLUBS;
          break;
      }
      for (int j = 1; j <= 8; j++) {
        Number nr = null;
        switch (j) {
          case 1:
            nr = Number.SEVEN;
            break;
          case 2:
            nr = Number.EIGHT;
            break;
          case 3:
            nr = Number.NINE;
            break;
          case 4:
            nr = Number.JACK;
            break;
          case 5:
            nr = Number.QUEEN;
            break;
          case 6:
            nr = Number.KING;
            break;
          case 7:
            nr = Number.TEN;
            break;
          case 8:
            nr = Number.ASS;
            break;
        }
        //cards are generated in the order of their value
        
        Card c = new Card(col, nr);
        cards[counter] = c;
        counter++;

        System.out.println(counter + " " + col.toString() + " " + nr.toString());
      }
    }
  }

  public static void main(String[] args) {
    Play p = new Play();

  }
}
