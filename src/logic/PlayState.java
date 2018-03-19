package logic;

import java.util.ArrayList;

public class PlayState {
  private Player singlePlayer;
  private Player[] teamPlayers = new Player[2];
  private ArrayList<Card> stockSinglePlayer;
  private ArrayList<Card> stockTeamPlayers;
  private Card[] skat = new Card[2];
  private Colour trump;
  private int playValue;
  private int betValue;
  private int nrOfPlays;
  private PlayMode pm;
  

  // still missing all the getters and setters
  //add method cardAlreadyPlayed

  public void addToStockSP(Trick t) {
    for (int i = 0; i < 3; i++) {
      this.stockSinglePlayer.add(t.getTrickCards()[i]);
    }
  }

  public void addToStockTP(Trick t) {
    for (int i = 0; i < 3; i++) {
      this.stockTeamPlayers.add(t.getTrickCards()[i]);
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
}

