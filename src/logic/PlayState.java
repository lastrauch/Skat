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


  // still missing all the getters and setters
  // add method cardAlreadyPlayed
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

  public void addToStackDeclarer(Trick t) {
    Card[] trick = t.getTrickCards();
    for (int i = 0; i < trick.length; i++) {
      this.declarerStack.add(trick[i]);
    }
  }

  public void addToStackDeclarer(Card card) {
    this.declarerStack.add(card);
  }

  public ArrayList<Card> getStackDeclarer() {
    return this.declarerStack;
  }

  public int getPlayValue() {
    return this.playValue;
  }
  
  public void setPlayValue(int playValue) {
    this.playValue = playValue;
  }
  
  public boolean getHandGame() {
    return this.handGame;
  }

  public void setHandGame(boolean handGame) {
    this.handGame = handGame;
  }

  public void setAuctionPossible(boolean auctionNotPossible) {
    this.auctionPossible = auctionNotPossible;
  }

  public boolean getAuctionPossible() {
    return this.auctionPossible;
  }

  public void addToStackOpponents(Trick t) {
    Card[] trick = t.getTrickCards();
    for (int i = 0; i < trick.length; i++) {
      this.opponentsStack.add(trick[i]);
    }
  }

  public ArrayList<Card> getStackOpponents() {
    return this.opponentsStack;
  }


  public Player getDeclarer() {
    return this.declarer;
  }

  public void setSkat(Card[] skat) {
    this.skat = skat;
  }

  public Card[] getSkat() {
    return this.skat;
  }

  public PlayMode getPlayMode() {
    return this.pm;
  }

  public void setPlayMode(PlayMode pm) {
    this.pm = pm;
  }

  public void setTrump(Colour trump) {
    this.trump = trump;
  }

  public Colour getTrump() {
    return this.trump;
  }

  public void setDeclarer(Player declarer) {
    this.declarer = declarer;
  }

  public void setOpponents(Player[] opponents) {
    this.opponents = opponents;
  }

  public void setBetValue(int betValue) {
    this.betValue = betValue;
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

  public boolean isSchneider() {
    return schneider;
  }

  public void setSchneider(boolean schneider) {
    this.schneider = schneider;
  }

  public boolean getSchneiderAnnounced() {
    return schneiderAnnounced;
  }

  public void setSchneiderAnnounced(boolean schneiderAnnounced) {
    this.schneiderAnnounced = schneiderAnnounced;
  }

  public boolean isSchwarz() {
    return schwarz;
  }

  public void setSchwarz(boolean schwarz) {
    this.schwarz = schwarz;
  }

  public boolean getSchwarzAnnounced() {
    return schwarzAnnounced;
  }

  public void setSchwarzAnnounced(boolean schwarzAnnounced) {
    this.schwarzAnnounced = schwarzAnnounced;
  }

  public boolean isOpen() {
    return open;
  }

  public void setOpen(boolean open) {
    this.open = open;
  }
  
  public Player[] getOpponents() {
    return this.opponents;
  }
  
  public int getBaseValue() {
    return this.baseValue;
  }

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

  public int getBetValue() {
    return this.betValue;
  }


}

