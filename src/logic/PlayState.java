package logic;

import java.util.ArrayList;

public class PlayState {
  private Player declarer; // single player
  private Player[] opponents = new Player[2]; // team Players
  private ArrayList<Card> declarerStack;
  private ArrayList<Card> opponentsStack;
  private Card[] skat = new Card[2];
  private Colour trump;
  private int playValue;
  private int betValue;
  private int nrOfPlays;
  private PlayMode pm;
  private boolean auctionPossible = true;


  // still missing all the getters and setters
  // add method cardAlreadyPlayed

  public void addToStackDeclarer(Trick t) {
    for (int i = 0; i < 3; i++) {
      this.declarerStack.add(t.getTrickCards()[i]);
    }
  }

  public void setAuctionPossible(boolean auctionNotPossible) {
    this.auctionPossible = auctionNotPossible;
  }

  public boolean getAuctionPossible() {
    return this.auctionPossible;
  }

  public void addToStackOpponents(Trick t) {
    for (int i = 0; i < 3; i++) {
      this.opponentsStack.add(t.getTrickCards()[i]);
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

