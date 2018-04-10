package logic;

import java.util.ArrayList;

public class Player {
  private int id;
  private String name;
  private Position position;
  private ArrayList<Card> hand = new ArrayList<Card>();
  private int bet;
  private int gamePoints; // saves the pionts of every Play until the whole game is over

  public Player(String name) {
    this.name = name;
    this.bet = 0;
  }

  // pos is the position of the card in the hand of the player
  public void playCard(int pos) {}

  public Card chooseCardFromHand() throws LogicException {
    System.out.println(this.name);
    int index = IOTools.readInteger("index of chosen card: ");

    if (index >= this.hand.size()) {
      throw new LogicException("the given index is too high!");
    }
    Card chosenCard = this.hand.get(index);
    return chosenCard;
  }

  public Card chooseRandomCardFromHand() {
    int index = (int) (Math.random() * this.hand.size());
    return this.hand.get(index);
  }

  /**
   * removes a given card from the hand
   * 
   * @author sandfisc
   * @param card
   * @throws LogicException
   */
  public void removeCardFromHand(Card card) throws LogicException {

    boolean found = false;

    for (int i = 0; i < this.hand.size(); i++) {
      if (this.hand.get(i).equals(card)) {
        // System.out.println("found index for remove : " + i);
        found = true;
        this.hand.remove(i);
        break;
      }
    }

    if (!found) {
      throw new LogicException("Removing the played card from the hand was not possible!");
    }
  }

  // We assume the hand to be sorted the first time (before the PlayMode was set)
  public void calculateHighestPossibleBet(ArrayList<Card> hand) {
    // ArrayList<Card> jacks = new ArrayList<Card>();
    // int index = 0;
    // while (hand.get(index).getNumber() == Number.JACK) {
    // jacks.add(hand.get(index));
    // index ++;
    // }
  }

  // methods needed for the auction.. say,

  /**
   * maybe something for the logic gui interface??? created for the auction
   * 
   * @author awesch
   * @param bet
   * @return
   */
  public boolean askForBet(int bet) {
    int whatTheySaid = IOTools.readInteger(this.name + " " + bet + " or PASS(0)?");
    if (whatTheySaid == bet) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * needs to be changed for the final version probably a method for the logicGui/logicNetwork
   * interface created for the auction
   * 
   * @param ps
   * @author awesch
   */
  public void askForPlaySettings(PlayState ps) {

    // set playmode
    String pm = IOTools.readLine("Set the PlayMode (colour, grand, null or nullouvert): ");
    PlayMode playMode;
    switch (pm) {
      case "colour":
        playMode = PlayMode.SUIT;
        break;
      case "grand":
        playMode = PlayMode.GRAND;
        break;
      case "null":
        playMode = PlayMode.NULL;
        ps.setTrump(null);
        break;
      default:
        System.out.println("your PlayMode could not be identificated, you will play Null now.");
        playMode = PlayMode.NULL;
    }
    ps.setPlayMode(playMode);

    // set Trump
    if (playMode == PlayMode.SUIT) {
      String t = IOTools.readLine("Set the Trump (clubs, spades, hearts, diamonds): ");
      Colour trump;
      switch (t) {
        case "clubs":
          trump = Colour.CLUBS;
          break;
        case "spades":
          trump = Colour.SPADES;
          break;
        case "hearts":
          trump = Colour.HEARTS;
          break;
        case "diamonds":
          trump = Colour.DIAMONDS;
          break;
        default:
          System.out.println("your Trump could not be identificated, you will play Clubs now.");
          trump = Colour.CLUBS;
      }
      ps.setTrump(trump);
    }
    // add other features and settings later
  }

  public boolean askForHandGame() {
    String handGame = IOTools.readLine("Do you want to take the skat?(yes/no)");
    if (handGame.equals("yes")) {
      return true;
    } else {
      return false;
    }
  }

  public int getGamePoints() {
    return this.gamePoints;
  }

  public void setGamePoints(int gamePoints) {
    this.gamePoints = gamePoints;
  }

  public int getBet() {
    return this.bet;
  }

  public void setBet(int bet) {
    this.bet = bet;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPosition(Position position) {
    this.position = position;
  }

  public void setHand(ArrayList<Card> hand) {
    this.hand = hand;
  }


  public String getName() {
    return this.name;
  }

  public Position getPosition() {
    return this.position;
  }

  public ArrayList<Card> getHand() {
    return this.hand;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getId() {
    return this.id;
  }

  public void sortHand(PlayState ps) {
    // possible different orders : colour, grand, null(nullouvert)

    int counter = 0; // saves nr of jacks/ nr of already sorted cards
    ArrayList<Card> jacks = new ArrayList<Card>();

    // first step: jacks at the beginning
    if (ps.getPlayMode() == PlayMode.SUIT | ps.getPlayMode() == PlayMode.GRAND
        | ps.getPlayMode() == null) {
      Card temp;
      for (int i = 0; i < this.hand.size(); i++) {
        if (this.hand.get(i).getNumber() == Number.JACK) {
          jacks.add(this.hand.get(i));
          temp = this.hand.get(counter);
          this.hand.set(counter, this.hand.get(i));
          this.hand.set(i, temp);
          counter++;
        }
      }

    }

    ps.sortCardsByColour(jacks);


    // seperate different colours
    ArrayList<Card> clubs = new ArrayList<Card>();
    ArrayList<Card> spades = new ArrayList<Card>();
    ArrayList<Card> hearts = new ArrayList<Card>();
    ArrayList<Card> diamonds = new ArrayList<Card>();

    for (int i = counter; i < this.hand.size(); i++) {
      Colour c = this.hand.get(i).getColour();
      switch (c) {
        case CLUBS:
          clubs.add(this.hand.get(i));
          break;
        case SPADES:
          spades.add(this.hand.get(i));
          break;
        case HEARTS:
          hearts.add(this.hand.get(i));
          break;
        case DIAMONDS:
          diamonds.add(this.hand.get(i));
          break;
      }
    }

    // sort different colours depending on the Playmode by their numbers
    if (ps.getPlayMode() == PlayMode.SUIT | ps.getPlayMode() == PlayMode.GRAND
        | ps.getPlayMode() == null) {
      ps.sortCardsValueNorm(clubs);
      ps.sortCardsValueNorm(spades);
      ps.sortCardsValueNorm(hearts);
      ps.sortCardsValueNorm(diamonds);
    } else if (ps.getPlayMode() == PlayMode.NULL) {
      ps.sortCardsValueLowTen(clubs);
      ps.sortCardsValueLowTen(spades);
      ps.sortCardsValueLowTen(hearts);
      ps.sortCardsValueLowTen(diamonds);
    }

    // put the different stacks back together in the right order
    // first: jacks - filled only if playmode colour grand or null(before mode is chosen)
    this.addToHand(jacks, this.hand, 0, jacks.size());

    // second: trump - only if PlayMode colour
    if (ps.getPlayMode() == PlayMode.SUIT) {
      Colour trump = ps.getTrump();
      switch (trump) {
        case CLUBS:
          this.addToHand(clubs, this.hand, counter, clubs.size());
          counter += clubs.size();
          break;
        case SPADES:
          this.addToHand(spades, this.hand, counter, spades.size());
          counter += spades.size();
          break;
        case HEARTS:
          this.addToHand(hearts, this.hand, counter, hearts.size());
          counter += hearts.size();
          break;
        case DIAMONDS:
          this.addToHand(diamonds, this.hand, counter, diamonds.size());
          counter += diamonds.size();
          break;
      }
    }


    // add all other colours (if not trump)
    if (ps.getTrump() != Colour.CLUBS) {
      this.addToHand(clubs, this.hand, counter, clubs.size());
      counter += clubs.size();
    }

    if (ps.getTrump() != Colour.SPADES) {
      this.addToHand(spades, this.hand, counter, spades.size());
      counter += spades.size();
    }

    if (ps.getTrump() != Colour.HEARTS) {
      this.addToHand(hearts, this.hand, counter, hearts.size());
      counter += hearts.size();
    }

    if (ps.getTrump() != Colour.DIAMONDS) {
      this.addToHand(diamonds, this.hand, counter, diamonds.size());
      counter += diamonds.size();
    }

  }

  /**
   * Adds arrayList to ArrayList, created for sortHand(s)
   * 
   * @author awesch
   * @param cardsToAdd
   * @param hand
   * @param start
   * @param length
   */
  public void addToHand(ArrayList<Card> cardsToAdd, ArrayList<Card> hand, int start, int length) {
    int counter = 0;
    for (int i = start; i < start + length; i++) {
      hand.set(i, cardsToAdd.get(counter));
      counter++;
    }
  }

  /**
   * calculates the the Matadors the hand has to be sorted before! with the (chosen/possible) trump
   * 
   * @return
   * @author awesch
   */
  public int calculateMatador() {
    // seperated to give a better overview and to have the possibility to give "with i" / "against
    // i"
    int with = 0;
    int against = 0;
    // play with(if first card is the clubs jack)
    if (this.hand.get(0).getMatadorValue() == 0) {
      // go through the hand until the row of trumps stops
      for (int i = 0; i < this.hand.size(); i++) {
        if (this.hand.get(i).getMatadorValue() == i) {
          with++;
        } else {
          return with;
        }
      }
    }
    // play against(if first card is not clubs jack)
    else {
      // matadorValue of first card is number
      against = this.hand.get(0).getMatadorValue();
      return against;
    }
    return 0;
  }

  /**
   * calculates the multiplier to calculate the value of a suit or grand game
   * 
   * @author awesch
   * @param ps
   * @return
   */

  public int calculateMultiplier(PlayState ps) {
    int result = 1; // 1 for the game
    result += this.calculateMatador(); // + matadors
    // 1 point for schneider
    if (ps.isSchneider()) {
      result++;
      // 1 point for schwarz
      if (ps.isSchwarz()) {
        result++;
      }
    }


    // possibilities if the Player plays hand
    if (ps.getHandGame()) {
      // 1 point for hand game
      result++;
      // 1 point for schneider announced
      if (ps.getSchneiderAnnounced()) {
        result++;
      }
      // 1 point for schwarz announced AND for schneider announced
      if (ps.getSchwarzAnnounced()) {
        result += 2;;
      }
      // 1 point for open
      if (ps.isOpen()) {
        result += 1;
      }
    }

    return result;
  }

  public void addToGamePoints(int points) {
    this.gamePoints += points;
  }

  // test method create random hand
  public ArrayList<Card> createRandomHand() {
    ArrayList<Card> randomHand = new ArrayList<Card>();
    // initialize Cards
    Card cards[] = new Card[32];
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
    // shuffle Cards
    int index;
    Card temp = null;
    for (int i = 0; i < 32; i++) {
      index = (int) (Math.random() * 32);
      temp = cards[i];
      cards[i] = cards[index];
      cards[index] = temp;
    }

    for (int i = 0; i < 10; i++) {
      randomHand.add(cards[i]);
    }
    return randomHand;
  }

  // test print method
  public void printList(ArrayList<Card> list) {
    for (int i = 0; i < list.size(); i++) {
      System.out
          .println(list.get(i).getColour().toString() + " " + list.get(i).getNumber().toString());
    }
  }

  public static void main(String[] args) {
    PlayState ps = new PlayState();
    ps.setPlayMode(PlayMode.SUIT);
    ps.setTrump(Colour.HEARTS);
    Player anne = new Player("Anne");
    anne.setHand(anne.createRandomHand());
    anne.sortHand(ps);
    anne.printList(anne.getHand());
    System.out.println("Matador: " + anne.calculateMatador());
  }
}
