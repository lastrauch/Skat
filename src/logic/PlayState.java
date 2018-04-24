 package logic;

import java.util.ArrayList;

public class PlayState {
  private Player declarer; // single player
  private Player[] opponents; // team Players
  private ArrayList<Card> declarerStack;
  private ArrayList<Card> opponentsStack;
  private Card[] skat;
  private Colour trump;
  private int playValue;
  private int betValue;
  private PlayMode pm;
  private boolean auctionPossible;
  private boolean handGame;
  private boolean schneider;
  private boolean schneiderAnnounced;
  private boolean schwarz;
  private boolean schwarzAnnounced;
  private boolean open;
  private int baseValue;

  /**
   * constructor (default)
   * the attributes are initialized but we want the player(s) to change them during the game
   * 
   * @author sandfisc
   * @author awesch
   */
  public PlayState() {
    this.declarer = null;
    this.opponents = new Player[2];
    this.declarerStack = new ArrayList<Card>();
    this.opponentsStack = new ArrayList<Card>();
    this.skat = new Card[2];
    this.trump = Colour.CLUBS;
    this.playValue = 0;
    this.betValue = 0;
    this.pm = PlayMode.SUIT;
    this.auctionPossible = true;
  }

  /**
   * adds a trick to the stack of the declarer
   * 
   * @author awesch
   * @param t
   */
  public void addToStackDeclarer(Trick t) {
    this.declarerStack.addAll(t.getTrickCards());
//    Card[] trick = t.getTrickCards();
//    for (int i = 0; i < trick.length; i++) {
//      this.declarerStack.add(trick[i]);
//    }
  }

//  /**
//   * adds a card to the stack of the declarer (skat)
//   * 
//   * @author awesch
//   * @param card
//   */
//  public void addToStackDeclarer(Card card) {
//    this.declarerStack.add(card);
//  }

  /**
   * @param t
   */
  public void addToStackOpponents(Trick t) {
    this.opponentsStack.addAll(t.getTrickCards());
//    Card[] trick = t.getTrickCards();
//    for (int i = 0; i < trick.length; i++) {
//      this.opponentsStack.add(trick[i]);
//    }
  }

  /**
   * sorts cards by its value for normal values (high ten), created for sortHand(s)
   * 
   * @author awesch
   * @param cards
   */
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

  /**
   * sorts cards by its value for a low ten playMode, created for sortHand(s)
   * 
   * @author awesch
   * @param cards
   */
  public void sortCardsValueLowTen(ArrayList<Card> cards) {
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

  /**
   * sorts cards bei their colour, order: clubs, spades, hearts, diamonds. created for sortHand(s)
   * 
   * @author awesch
   * @param cards
   */
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


  /**
   * initializes the base value depending on the playMode
   * 
   * @author awesch
   */
  public void initializeBaseValue() {
    if (this.pm == PlayMode.SUIT) {
      switch (this.trump) {
        case DIAMONDS:
          this.baseValue = 9;
          break;
        case HEARTS:
          this.baseValue = 10;
          break;
        case SPADES:
          this.baseValue = 11;
          break;
        case CLUBS:
          this.baseValue = 12;
          break;
      }
    }
    if (this.pm == PlayMode.GRAND) {
      this.baseValue = 24;
    }
  }

  /**
   * @author awesch
   * @return
   */
  public ArrayList<Card> getStackDeclarer() {
    return this.declarerStack;
  }

  /**
   * @return
   */
  public ArrayList<Card> getStackOpponents() {
    return this.opponentsStack;
  }

  /**
   * @author awesch
   * @return
   */
  public int getPlayValue() {
    return this.playValue;
  }

  /**
   * @author awesch
   * @param playValue
   */
  public void setPlayValue(int playValue) {
    this.playValue = playValue;
  }

  /**
   * @author awesch
   * @return
   */
  public boolean getHandGame() {
    return this.handGame;
  }

  /**
   * @param handGame
   */
  public void setHandGame(boolean handGame) {
    this.handGame = handGame;
  }

  /**
   * @param auctionNotPossible
   */
  public void setAuctionPossible(boolean auctionNotPossible) {
    this.auctionPossible = auctionNotPossible;
  }

  /**
   * @return
   */
  public boolean getAuctionPossible() {
    return this.auctionPossible;
  }
  /**
   * @return
   */
  public int getBetValue() {
    return this.betValue;
  }

  /**
   * @return
   */
  public Player getDeclarer() {
    return this.declarer;
  }

  /**
   * @param skat
   */
  public void setSkat(Card[] skat) {
    this.skat = skat;
  }

  /**
   * @return
   */
  public Card[] getSkat() {
    return this.skat;
  }

  /**
   * @return
   */
  public PlayMode getPlayMode() {
    return this.pm;
  }

  /**
   * @param pm
   */
  public void setPlayMode(PlayMode pm) {
    this.pm = pm;
  }

  /**
   * @param trump
   */
  public void setTrump(Colour trump) {
    this.trump = trump;
  }

  /**
   * @return
   */
  public Colour getTrump() {
    return this.trump;
  }

  /**
   * @param declarer
   */
  public void setDeclarer(Player declarer) {
    this.declarer = declarer;
  }

  /**
   * @param opponents
   */
  public void setOpponents(Player[] opponents) {
    this.opponents = opponents;
  }

  /**
   * @param betValue
   */
  public void setBetValue(int betValue) {
    this.betValue = betValue;
  }

  /**
   * @return
   */
  public boolean isSchneider() {
    return schneider;
  }

  /**
   * @param schneider
   */
  public void setSchneider(boolean schneider) {
    this.schneider = schneider;
  }

  /**
   * @return
   */
  public boolean getSchneiderAnnounced() {
    return schneiderAnnounced;
  }

  /**
   * @param schneiderAnnounced
   */
  public void setSchneiderAnnounced(boolean schneiderAnnounced) {
    this.schneiderAnnounced = schneiderAnnounced;
  }

  /**
   * @return
   */
  public boolean isSchwarz() {
    return schwarz;
  }

  /**
   * @param schwarz
   */
  public void setSchwarz(boolean schwarz) {
    this.schwarz = schwarz;
  }

  /**
   * @return
   */
  public boolean getSchwarzAnnounced() {
    return schwarzAnnounced;
  }

  /**
   * @param schwarzAnnounced
   */
  public void setSchwarzAnnounced(boolean schwarzAnnounced) {
    this.schwarzAnnounced = schwarzAnnounced;
  }

  /**
   * @return
   */
  public boolean isOpen() {
    return open;
  }

  /**
   * @param open
   */
  public void setOpen(boolean open) {
    this.open = open;
  }

  /**
   * @return
   */
  public Player[] getOpponents() {
    return this.opponents;
  }

  /**
   * @return
   */
  public int getBaseValue() {
    return this.baseValue;
  }

}
