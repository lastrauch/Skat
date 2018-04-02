package logic;

public class Auction {

  private Player winner; // winner of the auction
  private Player[] auctionMembers = new Player[3]; // always 3
  private int[] possibleBets; // list of the possible bets
  private int[] bets = new int[3]; // bets of the players, index: same as(s) auctionMembers
  
  /**
   * constructor
   * @param auctionMembers
   */
  public Auction(Player[] auctionMembers) {
    this.auctionMembers = auctionMembers;
    this.initializePossibleBets();
    this.initializeBets();
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
  
  /**
   * the bet of each player is set 0 in the start of the auction
   */
  public void initializeBets() {
    for (int i = 0; i < this.bets.length; i++) {
      this.bets[i] = 0;
    }
  }

  /**
   * 
   * @return (array of) possible bets
   */
  public int[] getPossibleBets() {
    return this.possibleBets;
  }

  

}
