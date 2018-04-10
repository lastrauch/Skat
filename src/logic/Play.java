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
  // private int indexWinner; // winner of play
  // well we could have two winners right? thougt boolean singlePlayer could be better, what do you
  // think honey? ***** i like that :*
  private PlayState ps;
  private final int nrTricks = 10;
  private GameSettings gameSettings;
  private boolean singlePlayerWins;

  // needs a 3 Player Array
  public Play(Player[] group, GameSettings gameSettings, Card[] cards) {
    this.cards = new Card[32];
    this.tricks = new Trick[this.nrTricks];
    this.group = group;
    // this.runPlay();
    this.indexWinnerLastTrick = 0; // forehand starts the first trick
    this.currentTrick = 0;
    this.ps = new PlayState();
    this.gameSettings = gameSettings;
    this.cards = cards;
  }

  public GameSettings getGameSettings() {
    return this.gameSettings;
  }

  public void setGameSettings(GameSettings gameSettings) {
    this.gameSettings = gameSettings;
  }

  /**
   * runs the play
   * 
   * @author awesch
   * @author sandfisc
   */
  public void runPlay() {
    do {
      // pepearation
      this.shuffleCards();
      this.dealOutCards();
      // test:
      // this.printHands("after dealOutCards:");

      this.sortHands();
      // test:
      // this.printHands("after first sortCards:");
      auction = new Auction(this.group, this.ps);

      // // test (without auction)
      // this.ps.setPlayMode(PlayMode.NULLOUVERT);
      // this.ps.setTrump(Colour.CLUBS);
      // this.ps.setNrOfPlays(6);
      // this.ps.setDeclarer(this.group[0]);
    } while (!this.ps.getAuctionPossible());


    this.sortHands();
    // test:
    // this.printHands("after second sortCards:");

    // test player kr���m
    this.group[0].setGamePoints(this.group[0].getGamePoints() + 1);

    // doing 10 tricks
    Card card1 = null;
    Card card2 = null;
    Card card3 = null;

    for (int i = 0; i < this.nrTricks; i++) {

      // start new trick
      this.tricks[i] = new Trick(this.ps);

      // test
      System.out.println(this.group[(this.indexWinnerLastTrick) % 3].getName());
      System.out.println(this.group[(this.indexWinnerLastTrick + 1) % 3].getName());
      System.out.println(this.group[(this.indexWinnerLastTrick + 2) % 3].getName());


      try {
        // first player plays card
        // card1 = this.group[(this.indexWinnerLastTrick) % 3].playCard();

        // test random card
        card1 = this.group[(this.indexWinnerLastTrick) % 3].chooseRandomCardFromHand();
        System.out.println("your hand " + this.group[(this.indexWinnerLastTrick) % 3].getName());
        this.printListCards(this.group[(this.indexWinnerLastTrick) % 3].getHand());
        // card1 = this.group[(this.indexWinnerLastTrick) % 3].chooseCardFromHand();
        this.group[(this.indexWinnerLastTrick) % 3].removeCardFromHand(card1);

        this.tricks[i].setCard1(card1);

        // second player plays card
        do {
          // card2 = this.group[(this.indexWinnerLastTrick + 1) % 3].playCard();

          // test random card
          card2 = this.group[(this.indexWinnerLastTrick + 1) % 3].chooseRandomCardFromHand();
          System.out
              .println("your hand " + this.group[(this.indexWinnerLastTrick + 1) % 3].getName());
          this.printListCards(this.group[(this.indexWinnerLastTrick + 1) % 3].getHand());
          // card2 = this.group[(this.indexWinnerLastTrick + 1) % 3].chooseCardFromHand();
        } while (!this.checkIfCardPossible(card2, this.tricks[i].getFirstCard(),
            this.group[(this.indexWinnerLastTrick + 1) % 3]));

        // remove card from second players hand
        this.group[(this.indexWinnerLastTrick + 1) % 3].removeCardFromHand(card2);

        // add the second card to trick
        this.tricks[i].setCard2(card2);

        // third player plays card
        do {
          // card3 = this.group[(this.indexWinnerLastTrick + 2) % 3].playCard();

          // test random card
          card3 = this.group[(this.indexWinnerLastTrick + 2) % 3].chooseRandomCardFromHand();

          System.out
              .println("your hand " + this.group[(this.indexWinnerLastTrick + 2) % 3].getName());
          this.printListCards(this.group[(this.indexWinnerLastTrick + 2) % 3].getHand());
          // card3 = this.group[(this.indexWinnerLastTrick + 2) % 3].chooseCardFromHand();
        } while (!this.checkIfCardPossible(card3, this.tricks[i].getFirstCard(),
            this.group[(this.indexWinnerLastTrick + 2) % 3]));

        // remove card from third players hand
        this.group[(this.indexWinnerLastTrick + 2) % 3].removeCardFromHand(card3);
        // add the third card to trick
        this.tricks[i].setCard3(card3);

      } catch (LogicException e1) {
        // e1.printStackTrace();

      }

      System.out.println("The trick:");
      this.printArrayOfCards(this.tricks[i].getTrickCards());

      // the winner is calculated and his/her index is saved in indexWinnerLastTrick
      try {
        this.tricks[i].calculateWinner();
        this.indexWinnerLastTrick = this.tricks[i].getIndexWinner();
        System.out.println(
            "Winner of the last Trick: " + this.group[this.indexWinnerLastTrick].getName());

        // winner receives cards on his stack
        if (this.group[this.indexWinnerLastTrick] == this.ps.getDeclarer()) {
          ps.addToStackDeclarer(tricks[i]);
        } else {
          ps.addToStackOpponents(tricks[i]);
        }

        // declarer is not allowed to win a trick when playMode is NULL/NULLOUVERT
        if (this.ps.getPlayMode() == PlayMode.NULL) {

          if (this.ps.getDeclarer().equals(this.group[this.indexWinnerLastTrick])) {
            break;
          }
        }
      } catch (LogicException e) {
        e.printStackTrace();

      }
      System.out.println("Declarer Stack:");
      this.printListCards(this.ps.getStackDeclarer());
      System.out.println();
      System.out.println("Opponents Stack:");
      this.printListCards(this.ps.getStackOpponents());
      System.out.println();
    }

  }

  // to test stuff
  public void printArrayOfCards(Card[] array) {
    for (Card c : array) {
      System.out.println(c.getColour().toString() + " " + c.getNumber().toString());
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
   * @author sandfisc
   */
  public boolean checkIfCardPossible(Card card, Card firstCard, Player player)
      throws LogicException {
    if (this.ps.getPlayMode() == PlayMode.COLOUR) {
      return this.checkIfCardPossibleColour(card, firstCard, player);
    } else if (this.ps.getPlayMode() == PlayMode.GRAND) {
      return this.checkIfCardPossibleGrand(card, firstCard, player);
    } else if (this.ps.getPlayMode() == PlayMode.NULL) {
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
   * same color.
   * 
   * @author sandfisc
   * @param servingCard
   * @param servedCard
   * @return
   */
  public boolean checkIfServedColour(Card servingCard, Card servedCard) {

    if (servedCard.getColour() == this.ps.getTrump() || servedCard.getNumber() == Number.JACK) {
      // first card is trump
      if (servingCard.getColour() == this.ps.getTrump() || servingCard.getNumber() == Number.JACK) {
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
   * submethod of checkIfCardPossible.
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
   * color.
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
   * submethod of checkIfCardPossible.
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

  /**
   * Calculates the winner of the play, and saves it in the boolean singlePlayerWins uses
   * calculatePoinsOfStack
   * 
   * @author awesch
   */
  public void calculateWinner() {
    // there are two possible states where the declarer wins (depends if he plays hand or not)
    // if he plays hand: poinsD >= pointsO (1.), if not: pointsD > pointsO(2.)

    int pointsD = this.calculatePointsOfStack(this.ps.getStackDeclarer());
    int pointsO = this.calculatePointsOfStack(this.ps.getStackOpponents());
    // 1.
    if (pointsD >= pointsO && this.ps.getHandGame()) {
      this.singlePlayerWins = true;
    }
    // 2.
    else if (pointsD > pointsO && (!this.ps.getHandGame())) {
      this.singlePlayerWins = true;
    }
    // in every other case the team wins
    else {
      this.singlePlayerWins = false;
    }
  }

  /**
   * Calculates the points of a variable stack of cards created for calculateWinner
   * 
   * @param stack
   * @return
   * @author awesch
   */
  public int calculatePointsOfStack(ArrayList<Card> stack) {
    int sum = 0;
    for (int i = 0; i < stack.size(); i++) {
      sum += stack.get(i).getValue();
    }
    return sum;
  }

  // print methods to test the others
  public void printListCards(ArrayList<Card> list) {
    for (int i = 0; i < list.size(); i++) {
      System.out.println(list.get(i).getColour() + " " + list.get(i).getNumber());
    }
  }

  // only to test stuff
  public void printHands(String text) {
    System.out.println(text);
    for (int i = 0; i < this.group.length; i++) {
      System.out.println("Hand" + (i + 1));
      this.printListCards(this.group[i].getHand());
      System.out.println();
    }
    System.out.println();
  }



  /**
   * shuffles the cards after they have been initialized
   * 
   * @author awesch
   */
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

  /**
   * deals out the cards on a basic level as it is done in the original game we did it like this
   * because we want it original intern as well
   * 
   * @author awesch
   */
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

  /**
   * sorts the Hands of every player in the Play can be called before and after the PlayMode and all
   * the other PlayState attributes are initialized uses sortHand
   * 
   * @author awesch
   */
  public void sortHands() {
    for (int i = 0; i < this.group.length; i++) {
      this.group[i].sortHand(this.ps);
    }
  }

  /**
   * Sorts the hand depending on the playMode by seperating the hand in jacks(if it's not
   * null(-ouvert)) and then in the different colours then orders different colours by number and
   * puts them back together in the right order (again depending on the Playmode)
   * 
   * uses addToHand, sortCardsValueNorm, sortCardsValueLowTen, sortCardsByColour
   * 
   * @author awesch
   * @param hand
   */

  public PlayState getPlayState() {
    return this.ps;
  }

  /**
   * calculates the value of the game
   * 
   * @author sandfisc
   * @throws LogicException
   */
  public void calculatePoints() throws LogicException {
    if (this.gameSettings.getCountRule() == CountRule.NORMAL) {
      this.calculatePointsNormal();
    }
    if (this.gameSettings.getCountRule() == CountRule.BIERLACHS) {
      this.calculatePointsBierlachs();
    } else if (this.gameSettings.getCountRule() == CountRule.SEEGERFABIAN) {
      this.calculatePointsSeegerfabian();
    } else {
      throw new LogicException(
          "Calculating the score update was not possible (no countRule found)");
    }
  }

  public void calculatePointsNormal() {
    if (this.ps.getPlayValue() < this.ps.getDeclarer().getBet()){
    }
  }

  public void calculatePointsBierlachs() {

  }

  public void calculatePointsSeegerfabian() {

  }

  public Trick getLastTrick() {
    Trick lastTrick = new Trick(ps);
    if (this.currentTrick > 0) {
      lastTrick = this.tricks[this.currentTrick - 1];
    }
    return lastTrick;
  }

  public Trick getCurrentTrick() {
    return this.tricks[this.currentTrick];
  }
}
