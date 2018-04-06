package logic;

import java.util.ArrayList;

public class Play {

  private Player[] group; // gives us the Players and their position (first one is the
                          // forehand)
  
  private Card[] cards;
  private Trick[] tricks;
  private Auction auction;
  private int currentTrick;
  private int indexWinnerLastTrick;
  private PlayState ps;
  private final int nrTricks = 10;

  // needs a 3 Player Array
  public Play(Player[] group) {
    cards = new Card[32];
    tricks = new Trick[this.nrTricks];
    this.group = group;
    // this.runPlay();
    this.indexWinnerLastTrick = 0; // forehand starts the first trick
    this.currentTrick = 0;
    ps = new PlayState();

  }


  public void runPlay() {


    do {
      // pepearation
      this.initializeCards();
      this.shuffleCards();
      this.dealOutCards();
      // test:
      // this.printHands("after dealOutCards:");

      this.sortHands();
      // test:
      // this.printHands("after first sortCards:");
      auction = new Auction(this.group, this.ps);
    } while (!this.ps.getAuctionPossible());


    this.sortHands();
    // test:
    // this.printHands("after second sortCards:");

    // doing 10 tricks
    Card card1 = null;
    Card card2 = null;
    Card card3 = null;

    for (int i = 0; i < this.nrTricks; i++) {

      // start new trick
      this.tricks[i] = new Trick(this.ps);

      //test
      System.out.println(this.group[(this.indexWinnerLastTrick) % 3].getName());
      System.out.println(this.group[(this.indexWinnerLastTrick + 1) % 3].getName());
      System.out.println(this.group[(this.indexWinnerLastTrick + 2) % 3].getName());
      
      
      try {
        // first player plays card
        // card1 = this.group[(this.indexWinnerLastTrick) % 3].playCard();
        
        // test
        card1 = this.group[(this.indexWinnerLastTrick) % 3].playRandomCard();
        this.group[(this.indexWinnerLastTrick) % 3].removeCard(card1);

        this.tricks[i].setCard1(card1);

        // second player plays card
        do {
          // card2 = this.group[(this.indexWinnerLastTrick + 1) % 3].playCard();
          
          //test
          card2 = this.group[(this.indexWinnerLastTrick + 1) % 3].playRandomCard();
        } while (!this.checkIfCardPossible(card2, this.tricks[i].getFirstCard(),
            this.group[(this.indexWinnerLastTrick + 1) % 3]));

        // remove card from second players hand
        this.group[(this.indexWinnerLastTrick + 1) % 3].removeCard(card2);

        // add the second card to trick
        this.tricks[i].setCard2(card2);

        // third player plays card
        do {
         // card3 = this.group[(this.indexWinnerLastTrick + 2) % 3].playCard();
          
          //test
          card3 = this.group[(this.indexWinnerLastTrick + 2) % 3].playRandomCard();

        } while (!this.checkIfCardPossible(card3, this.tricks[i].getFirstCard(),
            this.group[(this.indexWinnerLastTrick + 2) % 3]));

        // remove card from third players hand
        this.group[(this.indexWinnerLastTrick + 2) % 3].removeCard(card3);
        // add the third card to trick
        this.tricks[i].setCard3(card3);

      } catch (LogicException e1) {
        e1.printStackTrace();
      }

      // the winner is calculated and his/her index is saved in indexWinnerLastTrick
      try {
        this.tricks[i].calculateWinner();
        this.indexWinnerLastTrick = this.tricks[i].getIndexWinner();
        System.out.println("winner: " + this.group[this.indexWinnerLastTrick].getName());
        
        // winner receives cards on his stack
        if (this.group[this.indexWinnerLastTrick] == this.ps.getDeclarer()) {
          ps.addToStackDeclarer(tricks[i]);
        }else {
          ps.addToStackOpponents(tricks[i]);
        }
      } catch (LogicException e) {
        e.printStackTrace();
      }

    }

  }

  /**
   * its is checked if the card can be played by the player depending on his hand, the first Colour
   * of the trick and the PlayMode
   * 
   * @param card (the player wants to play)
   * @param firstCard (the first played card in the current trick)
   * @param player (who wants to play the card)
   * @return if card can be played
   * @throws LogicException
   */
  public boolean checkIfCardPossible(Card card, Card firstCard, Player player)
      throws LogicException {
    if (this.ps.getPlayMode() == PlayMode.COLOUR) {
      return this.checkIfCardPossibleColour(card, firstCard, player);
    } else if (this.ps.getPlayMode() == PlayMode.GRAND) {
      return this.checkIfCardPossibleGrand(card, firstCard, player);
    } else if (this.ps.getPlayMode() == PlayMode.NULL
        || this.ps.getPlayMode() == PlayMode.NULLOUVERT) {
      return this.checkIfCardPossibleNull(card, firstCard, player);
    } else {
      throw new LogicException(
          "checking if the card is possible is not possible (no PlayMode found)");
    }
  }

  /**
   * submethod of checkIfCardPossible
   * 
   * @author sandfisc
   * @param card (the player wants to play)
   * @param firstCard (the first played card in the current trick)
   * @param player (who wants to play the card)
   * @return if card is possible in PlayMode Colour
   */
  public boolean checkIfCardPossibleColour(Card card, Card firstCard, Player player) {

    // check if card serves first played card
    if (this.checkIfServedColour(card, firstCard)) {
      return true;
    }

    // check if the player has a card which would serve the first card
    for (int i = 0; i < player.getHand().size(); i++) {
      if (this.checkIfServedColour(player.getHand().get(i), firstCard)) {
        return false;
      }
    }
    return true;
  }

  /**
   * checks if the serving card serves the served card --> checks is both are trump/jack or have the
   * same color
   * 
   * @author sandfisc
   * @param servingCard
   * @param servedCard
   * @return
   */
  public boolean checkIfServedColour(Card servingCard, Card servedCard) {

    if (servedCard.getColour() == this.ps.getTrump() || servedCard.getNumber() == Number.JACK) {
      // first card is trump
      if (servingCard.getColour() == servedCard.getColour()
          || servingCard.getNumber() == Number.JACK) {
        return true;
      }
    } else {
      // first card is not trump
      if (servingCard.getColour() == servedCard.getColour()
          && servingCard.getNumber() != Number.JACK) {
        return true;
      }
    }
    return false;
  }

  /**
   * submethod of checkIfCardPossible
   * 
   * @author sandfisc
   * @param card (the player wants to play)
   * @param firstCard (the first played card in the current trick)
   * @param player (who wants to play the card)
   * @return if card is possible in PlayMode Grand
   */
  public boolean checkIfCardPossibleGrand(Card card, Card firstCard, Player player) {

    // check if card serves first played card
    if (this.checkIfServedColour(card, firstCard)) {
      return true;
    }

    // check if the player has a card which would serve the first card
    for (int i = 0; i < player.getHand().size(); i++) {
      if (this.checkIfServedGrand(player.getHand().get(i), firstCard)) {
        return false;
      }
    }
    return true;
  }

  /**
   * checks if the serving card serves the served card --> checks is both are jack or have the same
   * color
   * 
   * @author sandfisc
   * @param servingCard
   * @param servedCard
   * @return
   */
  public boolean checkIfServedGrand(Card servingCard, Card servedCard) {

    // both cards are jack
    if (servedCard.getNumber() == Number.JACK && servingCard.getNumber() == Number.JACK) {
      return true;
    }

    // both cards are no jack
    if (servedCard.getNumber() != Number.JACK && servingCard.getNumber() != Number.JACK) {
      if (servedCard.getColour() == servingCard.getColour()) {
        return true;
      }
    }
    return false;
  }

  /**
   * submethod of checkIfCardPossible
   * 
   * @author sandfisc
   * @param card (the player wants to play)
   * @param firstCard (the first played card in the current trick)
   * @param player (who wants to play the card)
   * @return if card is possible in PlayMode Null or NullOuvert
   */
  public boolean checkIfCardPossibleNull(Card card, Card firstCard, Player player) {

    if (card.getColour() == firstCard.getColour()) {
      return true;

    } else {
      for (int i = 0; i < player.getHand().size(); i++) {
        if (player.getHand().get(i).getColour() == firstCard.getColour()) {
          return false;
        }
      }
      return true;
    }
  }

  // print methods to test the others
  public void printListCards(ArrayList<Card> list) {
    for (int i = 0; i < list.size(); i++) {
      System.out.println(list.get(i).getColour() + " " + list.get(i).getNumber());
    }
  }

  public void printHands(String text) {
    System.out.println(text);
    for (int i = 0; i < this.group.length; i++) {
      System.out.println("Hand" + (i + 1));
      this.printListCards(this.group[i].getHand());
      System.out.println();
    }
    System.out.println();
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

    this.group[0].setHand(handF);
    this.group[1].setHand(handM);
    this.group[2].setHand(handR);
    this.ps.setSkat(skat);

  }

  public void sortHands() {
    for (int i = 0; i < this.group.length; i++) {
      this.sortHand(this.group[i].getHand());
    }
  }

  public void sortHand(ArrayList<Card> hand) {
    // possible different orders : colour, grand, null(nullouvert)

    int counter = 0; // saves nr of jacks/ nr of already sorted cards
    ArrayList<Card> jacks = new ArrayList<Card>();

    // first step: jacks at the beginning
    if (this.ps.getPlayMode() == PlayMode.COLOUR | this.ps.getPlayMode() == PlayMode.GRAND
        | this.ps.getPlayMode() == null) {
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
    if (this.ps.getPlayMode() == PlayMode.COLOUR | this.ps.getPlayMode() == PlayMode.GRAND
        | this.ps.getPlayMode() == null) {
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
    // first: jacks - filled only if playmode colour grand or null(before mode is chosen)
    this.addToHand(jacks, hand, 0, jacks.size());

    // second: trump - only if PlayMode colour
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


    // add all other colours (if not trump)
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
        if (cards.get(j).isLowerAsLowTen(cards.get(j + 1))) {
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
//    test.getPlayState().setPlayMode(PlayMode.NULLOUVERT);
//    test.getPlayState().setTrump(Colour.CLUBS);
//    test.getPlayState().setNrOfPlays(1);
    test.runPlay();
  }
}
