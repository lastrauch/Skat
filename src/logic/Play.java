package logic;

import java.util.ArrayList;

public class Play {

  private Player[] groupPos; // gives us the Players and their position (first one is the
                             // forehand)
  private Card[] cards = new Card[32];
  private Trick[] tricks = new Trick[10];
  private PlayState ps = new PlayState();


  // needs a 3 Player Array
  public Play(Player[] group) {
    this.groupPos = group;

    this.initializeCards();
    this.shuffleCards();
    // Auction needs to tke place here

    // as a test :
    ps.setPlayMode(PlayMode.COLOUR);
    ps.setTrump(Colour.HEARTS);

    // this.printCardsTest();
    this.dealOutCards();
    this.printListCards(this.groupPos[0].getHand());
    System.out.println("after testOrder n' stuff");
    this.testOrderNStuff();

    this.sortHands();
  }

  public void printCardsTest() {
    for (int i = 0; i < 32; i++) {
      System.out.println(this.cards[i].getColour() + " " + this.cards[i].getNumber());
    }
  }

  public void printListCards(ArrayList<Card> list) {
    for (int i = 0; i < list.size(); i++) {
      System.out.println(list.get(i).getColour() + " " + list.get(i).getNumber());
    }
  }

  // test only!
  public void testOrderNStuff() {
    for (int i = 0; i < 3; i++) {
      this.sortHand(this.groupPos[i].getHand());
      printListCards(this.groupPos[i].getHand());
      System.out.println();
    }
    System.out.println();
    System.out.println("Stack:");
    System.out.println(this.ps.getSkat()[0].getColour() + " " + this.ps.getSkat()[0].getNumber());
    System.out.println(this.ps.getSkat()[1].getColour() + " " + this.ps.getSkat()[1].getNumber());
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
        // cards are generated in the order of their value

        Card c = new Card(col, nr);
        cards[counter] = c;
        counter++;

        // System.out.println(counter + " " + col.toString() + " " + nr.toString());
      }
    }
  }

  public void shuffleCards() {
    int index;
    Card temp = null;
    for (int i = 0; i < 32; i++) {
      index = (int) (Math.random() * 32);
      temp = this.cards[i];
      this.cards[i] = this.cards[index];
      this.cards[index] = temp;
    }
  }

  public void dealOutCards() {
    // idea: deal out as in the original game, just because we want it intern
    // needed : position forehand, players of the game, how many players?,

    // forehand is the position 0 of group array
    ArrayList<Card> handF = new ArrayList<Card>();
    ArrayList<Card> handM = new ArrayList<Card>();
    ArrayList<Card> handR = new ArrayList<Card>();
    ArrayList<ArrayList<Card>> crew = new ArrayList<ArrayList<Card>>();
    crew.add(handF);
    crew.add(handM);
    crew.add(handR);
    Card[] skat = new Card[2];
    int counter = 0; // points on first card (next to deal out)

    // deal out first 9 cards (3 each)
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        crew.get(i).add(cards[counter]);
        counter++;
      }
    }

    // deal out skat
    for (int i = 0; i < 2; i++) {
      skat[i] = cards[counter];
      counter++;
    }

    // deal out 4 cards each
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 4; j++) {
        crew.get(i).add(cards[counter]);
        counter++;
      }
    }

    // deal out 3 cards each
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        crew.get(i).add(cards[counter]);
        counter++;
      }
    }

    this.groupPos[0].setHand(handF);
    this.groupPos[1].setHand(handM);
    this.groupPos[2].setHand(handR);
    this.ps.setSkat(skat);

  }

  public void sortHands() {

  }

  public void sortHand(ArrayList<Card> hand) {
    // possible different orders : colour, grand, null(nullouvert)

    int counter = 0; // saves nr of jacks/ nr of already sorted cards
    ArrayList<Card> jacks = new ArrayList<Card>();

    // first step: jacks at the beginning
    if (this.ps.getPlayMode() == PlayMode.COLOUR | this.ps.getPlayMode() == PlayMode.GRAND) {
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

    this.sortCardsByColour(jacks);


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
    if (this.ps.getPlayMode() == PlayMode.COLOUR | this.ps.getPlayMode() == PlayMode.GRAND) {
      this.sortCardsValueNorm(clubs);
      this.sortCardsValueNorm(spades);
      this.sortCardsValueNorm(hearts);
      this.sortCardsValueNorm(diamonds);
    } else if (this.ps.getPlayMode() == PlayMode.NULL
        | this.ps.getPlayMode() == PlayMode.NULLOUVERT) {
      this.sortCardsValueHighTen(clubs);
      this.sortCardsValueHighTen(spades);
      this.sortCardsValueHighTen(hearts);
      this.sortCardsValueHighTen(diamonds);
    }

    // put the different stacks back together in the right order
    this.addToHand(jacks, hand, 0, jacks.size());

    if (this.ps.getPlayMode() == PlayMode.COLOUR) {
      Colour trump = this.ps.getTrump();
      switch (trump) {
        case CLUBS:
          this.addToHand(clubs, hand, counter, clubs.size());
          counter += clubs.size();
          break;
        case SPADES:
          this.addToHand(spades, hand, counter, spades.size());
          counter += spades.size();
          break;
        case HEARTS:
          this.addToHand(hearts, hand, counter, hearts.size());
          counter += hearts.size();
          break;
        case DIAMONDS:
          this.addToHand(diamonds, hand, counter, diamonds.size());
          counter += diamonds.size();
          break;
      }
    }

    System.out.println(clubs.toString());
    if (this.ps.getTrump() != Colour.CLUBS) {
      this.addToHand(clubs, hand, counter, clubs.size());
      counter += clubs.size();
    }

    if (this.ps.getTrump() != Colour.SPADES) {
      this.addToHand(spades, hand, counter, spades.size());
      counter += spades.size();
    }

    if (this.ps.getTrump() != Colour.HEARTS) {
      this.addToHand(hearts, hand, counter, hearts.size());
      counter += hearts.size();
    }

    if (this.ps.getTrump() != Colour.DIAMONDS) {
      this.addToHand(diamonds, hand, counter, diamonds.size());
      counter += diamonds.size();
    }

  }

  // Adds arrayList to ArrayList
  public void addToHand(ArrayList<Card> cardsToAdd, ArrayList<Card> hand, int start, int length) {
    int counter = 0;
    for (int i = start; i < start + length; i++) {
      hand.set(i, cardsToAdd.get(counter));
      counter++;
    }
  }

  // sorts cards by its value -- not colour!
  public void sortCardsValueNorm(ArrayList<Card> cards) {
    Card temp;
    for (int i = 1; i < cards.size(); i++) {
      for (int j = 0; j < cards.size() - 1; j++) {
        if (cards.get(j).isLowerAsNorm(cards.get(j + 1))) {
          temp = cards.get(j);
          cards.set(j, cards.get(j + 1));
          cards.set(j + 1, temp);
        }
      }
    }
  }

  public void sortCardsValueHighTen(ArrayList<Card> cards) {
    Card temp;
    for (int i = 1; i < cards.size(); i++) {
      for (int j = 0; j < cards.size() - 1; j++) {
        if (cards.get(j).isHigherAsNorm(cards.get(j + 1))) {
          temp = cards.get(j);
          cards.set(j, cards.get(j + 1));
          cards.set(j + 1, temp);
        }
      }
    }
  }

  public void sortCardsByColour(ArrayList<Card> cards) {
    Card temp;
    for (int i = 0; i < cards.size(); i++) {
      for (int j = 0; j < cards.size() - 1; j++) {
        if (cards.get(j).getColour().compareColourIntern(cards.get(j + 1).getColour()) < 0) {
          temp = cards.get(i);
          cards.set(j, cards.get(j + 1));
          cards.set(j + 1, temp);
        }
      }
    }
  }

  public PlayState getPlayState() {
    return this.ps;
  }

  public static void main(String[] args) {
    Player sandra = new Player("Sandra");
    Player larissa = new Player("Larissa");
    Player felix = new Player("Felix");

    Player[] crew = new Player[3];
    crew[0] = sandra;
    crew[1] = larissa;
    crew[2] = felix;

    Play test = new Play(crew);

  }
}
