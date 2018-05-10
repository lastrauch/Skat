package logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * this class represents a player.
 *
 */
public class Player implements Serializable {

  private static final long serialVersionUID = 1L;
  private int id;
  private String name;
  private Position position;
  private List<Card> hand;
  private int bet; // -1 if you passed
  private List<Integer> playScore; // points of one round
  private int gameScore; // saves the points of every Play until the whole game is over
  private boolean declarer; // true if the player is declarer and false when he/she is opponents
  private boolean bot;

  /* ------------------------- CONSTRUCTOR ------------------------------------------- */

  /**
   * constructor.
   * 
   * @param name of the player
   */
  public Player(String name) {
    this.name = name;
    this.bet = 0;
    this.bot = false;
    this.hand = new ArrayList<Card>();
    this.setPlayScore(new ArrayList<Integer>());
    this.declarer = false;
  }

  /**
   * constructor.
   * 
   * @param name of the player
   * @param bot submethod or not
   */
  public Player(String name, boolean bot) {
    this.name = name;
    this.bet = 0;
    this.bot = bot;
    this.hand = new ArrayList<Card>();
    this.setPlayScore(new ArrayList<Integer>());
    this.declarer = false;
  }

  /**
   * constructor.
   * 
   * @param name of the player
   * @param id of the player
   * @param position of the player
   * @param hand of the player
   * @param bet last bet
   * @param gamePoints of the player
   * @param declarer of the play or not
   * @param bot or not
   * @param playPoints of the player
   */
  public Player(String name, int id, Position position, List<Card> hand, int bet, int gamePoints,
      boolean declarer, boolean bot, List<Integer> playPoints) {
    this.name = name;
    this.id = id;
    this.position = position;
    this.hand = hand;
    this.bet = bet;
    this.gameScore = gamePoints;
    this.declarer = declarer;
    this.bot = bot;
    this.playScore = playPoints;
  }

  /**
   * returns a deep copy of the player.
   * @return player (deep copy)
   */
  public Player copyMe() {
    String newName = this.name;
    int newId = this.id;
    Position newPosition = this.position;
    List<Card> newHand = new ArrayList<Card>();
    for (Card c : this.hand) {
      newHand.add(c);
    }
    int newBet = this.bet;
    int newGameScore = this.gameScore;
    boolean newDeclarer = this.declarer;
    boolean newBot = this.bot;
    List<Integer> newPlayScore = new ArrayList<Integer>();
    for (int i : this.playScore) {
      newPlayScore.add(i);
    }
    Player newPlayer = new Player(newName, newId, newPosition, newHand, newBet, newGameScore,
        newDeclarer, newBot, newPlayScore);

    return newPlayer;
  }


  /* ------------------------- DO SOMETHING WITH THE HAND ----------------------------- */


  /**
   * returns a random index of the hand.
   * @return Card (random)
   */
  public Card chooseRandomCardFromHand() {
    int index = (int) (Math.random() * this.hand.size());
    return this.hand.get(index);
  }

  /**
   * removes a given card from the hand.
   * 
   * @author sandfisc
   * @param card to remove from hand
   * @throws LogicException if not possible
   */
  public void removeCardFromHand(Card card) throws LogicException {

    boolean found = false;

    for (int i = 0; i < this.hand.size(); i++) {
      if (this.hand.get(i).equals(card)) {
        found = true;
        this.hand.remove(i);
        break;
      }
    }

    if (!found) {
      throw new LogicException("Removing the played card from the hand was not possible!");
    }
  }

  /**
   * sorts the players hand.
   * 
   * @param ps current playState
   */
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
        default:
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
        default:
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
   * Adds arrayList to ArrayList, created for sortHand(s).
   * 
   * @author awesch
   * @param cardsToAdd to the hand
   * @param hand2 to add the cards on
   * @param start to add the cards
   * @param length of cards
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

  /* ------------------------- GETTER AND SETTER ------------------------------------- */

  public void addToPlayScore(int score) {
    this.playScore.add(score);
  }

  public int getGameScore() {
    return this.gameScore;
  }

  public void setGameScore(int gamePoints) {
    this.gameScore = gamePoints;
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

  /**
   * returns a deep copy of the players hand.
   * @return deep copied hand
   */
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

  public void setDeclarer(boolean isDeclarer) {
    this.declarer = isDeclarer;
  }

  public boolean isDeclarer() {
    return this.declarer;
  }

  public void addToGamePoints(int points) {
    this.gameScore += points;
  }

  public boolean isBot() {
    return this.bot;
  }

  public List<Integer> getPlayScore() {
    return playScore;
  }

  public void setPlayScore(List<Integer> playPoints) {
    this.playScore = playPoints;
  }
}
