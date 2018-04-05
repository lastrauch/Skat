package logic;

import java.util.ArrayList;

public class PlayState {
  private Player declarer; // single player
  private Player[] opponents = new Player[2]; // team Players
  private ArrayList<Card> declarerPlayer;
  private ArrayList<Card> opponentsPlayers;
  private Card[] skat = new Card[2];
  private Colour trump;
  private int playValue;
  private int betValue;
  private int nrOfPlays;
  private PlayMode pm;


  // still missing all the getters and setters
  // add method cardAlreadyPlayed

  public void addToStockDeclarer(Trick t) {
    for (int i = 0; i < 3; i++) {
      this.declarerPlayer.add(t.getTrickCards()[i]);
    }
  }

  public void addToStockOpponents(Trick t) {
    for (int i = 0; i < 3; i++) {
      this.opponentsPlayers.add(t.getTrickCards()[i]);
    }
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

