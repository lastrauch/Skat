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
  private int nrOfPlays;
  private PlayMode pm;
  private boolean auctionPossible;


  // still missing all the getters and setters
  // add method cardAlreadyPlayed
  public PlayState () {
    this.declarer = null;
    this.opponents = new Player[2];
    this.declarerStack = new ArrayList<Card>();
    this.opponentsStack = new ArrayList<Card>();
    this.skat = new Card[2];
    this.trump = Colour.CLUBS;
    this.playValue = 0;
    this.betValue = 0;
    this.nrOfPlays = 3;
    this.pm = PlayMode.COLOUR;
    this.auctionPossible = true;
  }
  
  public void addToStackDeclarer(Trick t) {
    Card [] trick = t.getTrickCards();
    for (int i = 0; i < trick.length; i++) {
      this.declarerStack.add(trick[i]);
    }
  }

  public void setAuctionPossible(boolean auctionNotPossible) {
    this.auctionPossible = auctionNotPossible;
  }

  public boolean getAuctionPossible() {
    return this.auctionPossible;
  }

  public void addToStackOpponents(Trick t) {
    Card [] trick = t.getTrickCards();
    for (int i = 0; i < trick.length; i++) {
      this.opponentsStack.add(trick[i]);
    }
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

  public void setNrOfPlays(int nrOfPlays) {
    this.nrOfPlays = nrOfPlays;
  }

  public int getNrOfPlays() {
    return this.nrOfPlays;
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
}

