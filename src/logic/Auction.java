package logic;

public class Auction {

  private Player winner; // winner of the auction
  private Player[] auctionMembers = new Player[3]; // always 3 members of the auction, first one is
                                                   // forehand
  private Player says;
  private Player hears;
  private int[] possibleBets; // list of the possible bets
  private int[] bets = new int[3]; // bets of the players, index: same as auctionMembers
 
  /**
   * constructor  
   * @param auctionMembers
   */
  public Auction(Player[] auctionMembers, PlayState ps) {
    this.auctionMembers = auctionMembers;
    this.initializePossibleBets();
    this.initializeBets();
    this.updatePlaystate(ps);

    System.out.println("auction started");
    System.out.println();
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

  public Player getWinner() {
    return winner;
  }

  public void setWinner(Player winner) {
    this.winner = winner;
  }
  
  /**
   * 
   * @param index of the player in auctionMembers
   * @param bet 
   * @throws LogicException 
   */
  public void setBet(int index, int bet) throws LogicException {
    
    // check if the bet is possible
    boolean found = false;
    for(int i = 0; i < this.possibleBets.length; i++) {
      if (bet == this.possibleBets[i]) {
        found = true;
      }
    }
    
    if (found) {
      this.bets[index] = bet;
    }else {
      throw new LogicException("the bet: " + bet + " is not possible");
    }
   
  }
  
  public void updatePlaystate(PlayState ps) {
    
  }
  
  public void organizeAuction() {
//
//    The first part of the auction takes place between F and M. M speaks first, either passing or bidding a
//    number. There is no advantage in making a higher than necessary bid so M will normally either pass
//    or begin with the lowest bid: 18. If M bids a number, F can either give up the chance to be declarer
//    by saying "pass" or compete by saying "yes", which means that F bids the same number that M just
//    bid. If F says "yes", M can say "pass", or continue the auction with a higher bid, to which F will
//    again answer "yes" or "pass". This continues until either F or M drops out of the auction by passing
//    - once having passed you get no further opportunity to bid on that hand.
//    The second part of the auction is similar to the first part, but takes place between R and the survivor
//    of the first part (i.e. whichever of F and M did not pass). As the junior player, R either passes or bids
//    a succession of numbers, the first of which must be higher than any number mentioned in the first
//    part of the auction. To each number bid by R, the survivor must answer "yes" or "pass". The winner
//    of the second part of the auction becomes the declarer, and the bid is the last number the declarer
//    said or accepted.
    
    
  }
  

}
