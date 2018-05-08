package logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Auction implements Serializable {

  /**
   * This class includes everything an auction can do.
   * 
   */
  private static final long serialVersionUID = 1L;
  private Player winner; // winner of the auction
  private int[] possibleBets; // list of the possible bets
  private int betValue;
  private int indexOfBetValue;
  private List<Integer> bets;

  /* ------------------------- CONSTRUCTOR ------------------------------------------- */

  public Auction() {
    this.bets = new ArrayList<Integer>();
    this.betValue = 18;
    this.indexOfBetValue = 0;
    this.initializePossibleBets();
  }

  public Auction(Player winner, int[] possibleBets, int betValue, int indexOfBetValue,
      List<Integer> bets) {
    this.winner = winner;
    this.possibleBets = possibleBets;
    this.betValue = betValue;
    this.indexOfBetValue = indexOfBetValue;
    this.bets = bets;
  }

  /* ------------------------- BET VALUE ---------------------------------------------- */
  
  /**
   * initializes the array of possible bets.
   */
  public void initializePossibleBets() {
    possibleBets = new int[] {18, 20, 22, 23, 24, 27, 30, 33, 35, 36, 40, 44, 45, 46, 48, 50, 54,
        55, 59, 60, 63, 66, 70, 72, 77, 80, 81, 84, 88, 90, 96, 99, 100, 108, 110, 117, 120, 121,
        126, 130, 132, 135, 141, 143, 144, 150, 153, 154, 156, 160, 162, 165, 168, 170, 176, 180,
        187, 192, 198, 204, 216, 240, 264};
  }

  /**
   * @param currentBet
   * @return
   */
  public int calculateNewBet() {
    if (this.bets.size() == 1) {
      return this.betValue;
    }
    if (this.bets.get(this.bets.size() - 1) == this.bets.get(this.bets.size() - 2)) {
      return this.possibleBets[this.indexOfBetValue + 1];
    }
    if (this.bets.get(this.bets.size() - 1) == -1 && this.bets.size() != 1) {
      return this.possibleBets[this.indexOfBetValue + 1];
    }
    return this.betValue;
  }

  /* ------------------------- COPY ---------------------------------------------------- */

  public Auction copyMe() {
    
    Player newWinner = this.winner.copyMe();
    int[] newPossibleBets = new int[this.possibleBets.length];
    for (int i = 0; i < this.possibleBets.length; i++) {
      newPossibleBets[i] = this.possibleBets[i];
    }
    int newBetValue = this.betValue;
    int newIndexOfBetValue = this.indexOfBetValue;
    List<Integer> newBets = new ArrayList<Integer>();
    for (int i : this.bets) {
      newBets.add(i);
    }
    Auction newAuction =
        new Auction(newWinner, newPossibleBets, newBetValue, newIndexOfBetValue, newBets);
    
    return newAuction;
  }

  /* ---------------------- GETTER AND SETTER ------------------------------------------- */

  public void setWinner(Player winner) {
    this.winner = winner;
  }
  
  public void addToBets(int bet) {
    this.bets.add(bet);
  }

  public List<Integer> getBets() {
    return this.bets;
  }

  public void setBetValue(int betValue) {
    this.betValue = betValue;
    this.setIndexOfBetValue();
  }

  public void setIndexOfBetValue() {
    for (int i = 0; i < this.possibleBets.length; i++) {
      if (this.possibleBets[i] == this.betValue) {
        this.indexOfBetValue = i;
        break;
      }
    }
  }

  public int[] getPossibleBets() {
    return this.possibleBets;
  }

  public Player getWinner() {
    return winner;
  }

  public int getBetValue() {
    return betValue;
  }

  public int getIndexOfBetValue() {
    return indexOfBetValue;
  }


}

