package logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * in the PlayState everything is saved, that happend in the play already, it is send inbetween the
 * different clientLogic instances.
 */

public class PlayState implements Serializable {

  private static final long serialVersionUID = 1L;
  private Player[] playingGroup;
  private Stack declarerStack;
  private Stack opponentsStack;
  private Card[] skat;
  private Colour trump;
  private int playValue;
  private PlayMode pm;
  private int playNr;
  private int trickNr;
  private boolean auctionPossible;
  private boolean handGame;
  private boolean schneider;
  private boolean schneiderAnnounced;
  private boolean schwarz;
  private boolean schwarzAnnounced;
  private boolean open;
  private int baseValue;
  private Trick currentTrick;
  private Auction auction;
  private boolean announcedKontra;
  private boolean announcedRekontra;

  /* ------------------------- CONSTRUCTOR ------------------------------------------- */

  /**
   * constructor (default) the attributes are initialized but we want the player(s) to change them
   * during the game.
   */
  public PlayState(Player[] group) {
    this.playingGroup = group;
    this.declarerStack = new Stack();
    this.opponentsStack = new Stack();
    this.skat = new Card[2];
    this.trump = Colour.CLUBS;
    this.playValue = 0;
    this.auction = new Auction();
    this.pm = PlayMode.SUIT;
    this.playNr = 1;
    this.trickNr = 1;
    this.currentTrick = new Trick();
    this.auctionPossible = true;
    this.auction = new Auction();
    this.schneider = false;
    this.schneiderAnnounced = false;
    this.schwarz = false;
    this.schwarzAnnounced = false;
    this.initializeBaseValue();
  }

  /**
   * constructor.
   * 
   * @param playingGroup
   * @param declarerStack
   * @param opponentsStack
   * @param skat
   * @param trump
   * @param playValue
   * @param pm
   * @param playNr
   * @param trickNr
   * @param auctionPossible
   * @param handGame
   * @param schneider
   * @param schneiderAnnounced
   * @param schwarz
   * @param schwarzAnnounced
   * @param open
   * @param baseValue
   * @param currentTrick
   * @param auction
   * @param announcedKontra
   * @param announcedRekontra
   */
  public PlayState(Player[] playingGroup, Stack declarerStack, Stack opponentsStack, Card[] skat,
      Colour trump, int playValue, PlayMode pm, int playNr, int trickNr, boolean auctionPossible,
      boolean handGame, boolean schneider, boolean schneiderAnnounced, boolean schwarz,
      boolean schwarzAnnounced, boolean open, int baseValue, Trick currentTrick, Auction auction,
      boolean announcedKontra, boolean announcedRekontra) {

    this.playingGroup = playingGroup;
    this.declarerStack = declarerStack;
    this.opponentsStack = opponentsStack;
    this.skat = skat;
    this.trump = trump;
    this.playValue = playValue;
    this.pm = pm;
    this.playNr = playNr;
    this.trickNr = trickNr;
    this.auctionPossible = auctionPossible;
    this.handGame = handGame;
    this.schneider = schneider;
    this.schneiderAnnounced = schwarzAnnounced;
    this.open = open;
    this.baseValue = baseValue;
    this.currentTrick = currentTrick;
    this.auction = auction;
    this.announcedKontra = announcedKontra;
    this.announcedRekontra = announcedRekontra;
  }

  /* ------------------------- HANDLE THIS PLAYSTATE ------------------------------------------- */

  public PlayState copyMe() {
    Player[] playerCopy = new Player[this.playingGroup.length];
    for (int i = 0; i < this.playingGroup.length; i++) {
      playerCopy[i] = this.playingGroup[i];
    }

    Stack declarerStackCopy = this.declarerStack;
    Stack opponentsStackCopy = this.opponentsStack;

    Card[] skatCopy = new Card[this.skat.length];
    for (int i = 0; i < this.skat.length; i++) {
      skatCopy[i] = this.skat[i];
    }

    Colour trumpCopy = trump;
    int playValueCopy = this.playValue;
    PlayMode playModeCopy = this.getPlayMode();

    int playNrCopy = this.playNr;
    int trickNrCopy = this.trickNr;
    boolean auctionPossibleCopy = this.auctionPossible;

    boolean handGameCopy = this.handGame;
    boolean schneiderCopy = this.schneider;
    boolean schneiderAnnouncedCopy = this.schneiderAnnounced;

    boolean schwarzCopy = this.schwarz;
    boolean schwarzAnnouncedCopy = this.schwarzAnnounced;
    boolean openCopy = this.open;

    int baseValueCopy = this.baseValue;
    Trick currentTrickCopy = this.currentTrick.copyMe();
    Auction auctionCopy = this.auction.copyMe();

    boolean announcedKontraCopy = this.announcedKontra;
    boolean announcedRekontraCopy = this.announcedRekontra;

    return new PlayState(playerCopy, declarerStackCopy, opponentsStackCopy, skatCopy, trumpCopy,
        playValueCopy, playModeCopy, playNrCopy, trickNrCopy, auctionPossibleCopy, handGameCopy,
        schneiderCopy, schneiderAnnouncedCopy, schwarzCopy, schwarzAnnouncedCopy, openCopy,
        baseValueCopy, currentTrickCopy, auctionCopy, announcedKontraCopy, announcedRekontraCopy);
  }


  /**
   * resets PlayState, after a play.
   */
  public void resetPlayState() {
    this.declarerStack = new Stack();
    this.opponentsStack = new Stack();

    this.skat = new Card[2];
    this.trump = Colour.CLUBS;
    this.playValue = 0;
    this.pm = PlayMode.SUIT;

    this.trickNr = 1;
    this.currentTrick = new Trick();
    this.auctionPossible = true;
    this.auction = new Auction();

    this.schneider = false;
    this.schneiderAnnounced = false;
    this.schwarz = false;
    this.schwarzAnnounced = false;
    this.setAnnouncedKontra(false);
    this.setAnnouncedRekontra(false);
  }

  /* ------------------------- SORT CARDS ------------------------------------------- */

  /**
   * sorts cards by its value for normal values (high ten), created for sortHand(s).
   * 
   * @author awesch
   * @param cards
   */
  public void sortCardsValueNorm(ArrayList<Card> cards) {
    Card temp;
    for (int i = 0; i < cards.size(); i++) {
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
   * sorts cards by its value for a low ten playMode, created for sortHand(s).
   * 
   * @author awesch
   * @param cards
   */
  public void sortCardsValueLowTen(ArrayList<Card> cards) {
    Card temp;
    for (int i = 0; i < cards.size(); i++) {
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
   * sorts cards bei their colour, order: clubs, spades, hearts, diamonds. created for sortHand(s).
   * 
   * @author awesch
   * @param cards
   */
  public void sortCardsByColour(ArrayList<Card> cards) {
    Card temp;
    for (int i = 0; i < cards.size(); i++) {
      for (int j = 0; j < cards.size() - 1; j++) {
        if (cards.get(j).getColour().compareColourIntern(cards.get(j + 1).getColour()) < 0) {
          temp = cards.get(j);
          cards.set(j, cards.get(j + 1));
          cards.set(j + 1, temp);
        }
      }
    }
  }

  /* ------------------------- BASE VALUE ------------------------------------------- */

  /**
   * initializes the base value depending on the playMode.
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
        default:
          break;
      }
    }
    if (this.pm == PlayMode.GRAND) {
      this.baseValue = 24;
    }
  }

  /* ------------------------- GETTER AND SETTER ------------------------------------------- */

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

  public int getBetValue() {
    return this.auction.getBetValue();
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

  /**
   * sets the PlayMode and initializes the baseValue, if not suit.
   * 
   * @param pm
   */
  public void setPlayMode(PlayMode pm) {
    this.pm = pm;

    if (pm == PlayMode.GRAND || pm == PlayMode.NULL) {
      this.trump = null;
    }

    if (pm == PlayMode.GRAND) {
      this.baseValue = 24;
    }
  }

  /**
   * sets trump and initializes the base value.
   */
  public void setTrump(Colour trump) {
    this.trump = trump;
    this.initializeBaseValue();
  }

  public Colour getTrump() {
    return this.trump;
  }

  public void setBetValue(int betValue) {
    this.auction.setBetValue(betValue);
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

  public int getBaseValue() {
    return this.baseValue;
  }

  public Trick getCurrentTrick() {
    return currentTrick;
  }

  public void setCurrentTrick(Trick currentTrick) {
    this.currentTrick = currentTrick;
  }

  public Player[] getGroup() {
    return playingGroup;
  }

  public void setGroup(Player[] group) {
    this.playingGroup = group;
  }

  public void setPlayNr(int nr) {
    this.playNr = nr;
  }

  public int getPlayNr() {
    return this.playNr;
  }

  public void setTrickNr(int nr) {
    this.trickNr = nr;
  }

  public int getTrickNr() {
    return this.trickNr;
  }

  public Auction getAuction() {
    return this.auction;
  }

  public Stack getDeclarerStack() {
    return this.declarerStack;
  }

  public Stack getOpponentsStack() {
    return this.opponentsStack;
  }

  public boolean isAnnouncedKontra() {
    return announcedKontra;
  }

  public void setAnnouncedKontra(boolean announcedKontra) {
    this.announcedKontra = announcedKontra;
  }

  public boolean isAnnouncedRekontra() {
    return announcedRekontra;
  }

  public void setAnnouncedRekontra(boolean announcedRekontra) {
    this.announcedRekontra = announcedRekontra;
  }
}
