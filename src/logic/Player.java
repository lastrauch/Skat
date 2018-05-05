package logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import interfaces.InGameInterface;
import interfaces.LogicData;
import interfaces.LogicGui;
import interfaces.LogicNetwork;
import javafx.scene.image.Image;

public class Player implements Serializable{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private int id;
  private String name;
  private Image img;
  private Position position;
  private List<Card> hand;
  private int bet; // -1 if you passed
  private int gamePoints; // saves the points of every Play until the whole game is over
  private boolean declarer; // true if the player is declarer and false when he/she is opponents
  private boolean bot;

  public Player(String name) {
    this.name = name;
    this.bet = 0;
    this.bot = false;
    this.hand  = new ArrayList<Card>();
  }
  
  public Player(String name, boolean bot) {
    this.name = name;
    this.bet = 0;
    this.bot = bot;
    this.hand  = new ArrayList<Card>();
  }

  public Player(String name, Image img) {
    this.name = name;
    this.img = img;
    this.bet = 0;
    this.bot = false;
    this.hand  = new ArrayList<Card>();
  }

  public Player(String name, int id, Image img, Position position, List<Card> hand, int bet,
      int gamePoints, boolean declarer) {
    this.name = name;
    this.id = id;
    this.img = img;
    this.position = position;
    this.hand = hand;
    this.bet = bet;
    this.gamePoints = gamePoints;
    this.declarer = declarer;
    this.bot = false;
    this.hand = new ArrayList<Card>();
  }

  public Player copyMe() {
    return new Player(this.name, this.id, this.img, this.position, this.hand, this.bet,
        this.gamePoints, this.declarer);
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
    // this.inGameController.updateHand(this.hand);
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

  public void setHand(List<Card> hand) {
    this.hand = hand;
  }


  public String getName() {
    return this.name;
  }

  public Position getPosition() {
    return this.position;
  }

  public List<Card> getHand() {
    return this.hand;
  }

  public List<Card> getDeepCopyHand() {
    List<Card> copyHand = new ArrayList<Card>();
    for (Card c : this.hand) {
      copyHand.add(c);
    }
    return copyHand;
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
    if (ps.getPlayMode() == PlayMode.SUIT || ps.getPlayMode() == PlayMode.GRAND
        || ps.getPlayMode() == null) {
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
    if (ps.getPlayMode() == PlayMode.SUIT || ps.getPlayMode() == PlayMode.GRAND
        || ps.getPlayMode() == null) {
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
   * @param hand2
   * @param start
   * @param length
   */
  public void addToHand(ArrayList<Card> cardsToAdd, List<Card> hand2, int start, int length) {
    int counter = 0;
    for (int i = start; i < start + length; i++) {
      hand2.set(i, cardsToAdd.get(counter));
      counter++;
    }
  }
  
  public void addToHand(Card card) {
    this.hand.add(card);
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

  public Image getImage() {
    return img;
  }

  public void setImage(Image img) {
    this.img = img;
  }

  public void setDeclarer(boolean isDeclarer) {
    this.declarer = isDeclarer;
  }

  public boolean isDeclarer() {
    return this.declarer;
  }

  /**
   * @param points
   */
  public void addToGamePoints(int points) {
    this.gamePoints += points;
  }
  public boolean isBot() {
    return this.bot;
  }
}
