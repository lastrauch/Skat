package logic;

import java.io.Serializable;

public class Auction implements Serializable{

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private Player winner; // winner of the auction
  private int[] possibleBets; // list of the possible bets
  private int betValue;
  private int indexOfBetValue;

  /**
   * constructor
   * 
   * @param auctionMembers
   * 
   */
  public Auction() {
    this.betValue = 0;
    this.initializePossibleBets();
  }

  /**
   * initializes the array of possible bets
   */
  public void initializePossibleBets() {
    possibleBets = new int[] {18, 20, 22, 23, 24, 27, 30, 33, 35, 36, 40, 44, 45, 46, 48, 50, 54,
        55, 59, 60, 63, 66, 70, 72, 77, 80, 81, 84, 88, 90, 96, 99, 100, 108, 110, 117, 120, 121,
        126, 130, 132, 135, 141, 143, 144, 150, 153, 154, 156, 160, 162, 165, 168, 170, 176, 180,
        187, 192, 198, 204, 216, 240, 264};
  }

  public int[] getPossibleBets() {
    return this.possibleBets;
  }

  public Player getWinner() {
    return winner;
  }

  /**
   * 
   * @param winner
   */
  public void setWinner(Player winner) {
    this.winner = winner;
  }
  //
  // public void mangageSkat(PlayState ps) {
  // // ask the declarer if he wants to take the skat or not and save the answer in the PlayState
  // ps.setHandGame(this.winner.askForHandGame());
  // // add the skat & lay it down if answer yes
  // if (ps.getHandGame()) {
  // this.takeUpStack(ps);
  // this.putDownTwoCards(ps);
  // }
  // }
  //
  // /**
  // * the two cards of the stack are added to the hand and the hand gets resorted, created for
  // * manageSkat
  // *
  // * @author awesch
  // */
  // public void takeUpStack(PlayState ps) {
  // this.winner.getHand().add(ps.getSkat()[0]);
  // this.winner.getHand().add(ps.getSkat()[1]);
  // this.winner.sortHand(ps);
  // System.out.println("Your new hand:");
  // this.printlistOfCards(this.winner.getHand());
  // }
  //
  //
  //
  // /**
  // * two cards are chosen by the declarer and added to his stack, created for manageSkat
  // *
  // * @author awesch
  // */
  // public void putDownTwoCards(PlayState ps) {
  // Card card1;
  // Card card2;
  // try {
  // card1 = this.winner.chooseCardFromHand();
  // ps.addToStackDeclarer(card1);
  // this.winner.removeCardFromHand(card1);
  //
  // card2 = this.winner.chooseCardFromHand();
  // ps.addToStackDeclarer(card2);
  // this.winner.removeCardFromHand(card2);
  //
  // System.out.println("Your new hand:");
  // this.printlistOfCards(this.winner.getHand());
  //
  // } catch (LogicException e) {
  // // TODO Auto-generated catch block
  // e.printStackTrace();
  // }
  // }
  //
  //

  public int getBetValue() {
    return betValue;
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

  public int getIndexOfBetValue() {
    return indexOfBetValue;
  }

}

