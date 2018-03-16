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

  // still missing all the getters and setters

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

}
