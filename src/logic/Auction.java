package logic;

public class Auction {

  private Player winner; // winner of the auction
  private Player[] auctionMembers = new Player[3]; // always 3 members of the auction, first one is
                                                   // forehand
  private int[] possibleBets; // list of the possible bets
  private int[] bets = new int[3]; // bets of the players, index: same as auctionMembers

  /**
   * constructor
   * 
   * @param auctionMembers
   */
  public Auction(Player[] auctionMembers, PlayState ps) {
    this.auctionMembers = auctionMembers;
    this.initializePossibleBets();
    this.initializeBets();
    this.organizeAuction();
    this.updatePlayState(ps);
  }

  /**
   * initializes the array of possible bets
   */
  public void initializePossibleBets() {
    possibleBets = new int[] {0, 18, 20, 22, 23, 24, 27, 30, 33, 35, 36, 40, 44, 45, 46, 48, 50, 54,
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

  /**
   * 
   * @param winner
   */
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
    for (int i = 0; i < this.possibleBets.length; i++) {
      if (bet == this.possibleBets[i]) {
        found = true;
      }
    }

    if (found) {
      this.bets[index] = bet;
    } else {
      throw new LogicException("the bet: " + bet + " is not possible");
    }

  }

  /**
   * organizes the actual talk between the players seperated in first and secound conversation
   * 
   * @author awesch
   */
  public void organizeAuction() {
    Player says;
    int indexSays;
    Player hears;
    int indexHears;
    int indexLastBet = 1;

    // Players sit : F(0), M(1), R(2)
    // .askForBet(18) gives back true if player bets 18 and false if he passes
    // (first part of the auction takes place between F and M. M speaks first)

    says = this.auctionMembers[1];
    indexSays = 1;
    hears = this.auctionMembers[0];
    indexHears = 0;

    // check if M wants to bet or pass (1. if --> pass)
    if (says.askForBet(this.possibleBets[indexLastBet]) == false) {
      this.bets[indexSays] = this.possibleBets[0];
      says = this.auctionMembers[2];
      indexSays = 2;
    }
    // if M doesn't want to pass:
    else {
      // check if F wants to pass or bet (if --> pass)
      if (hears.askForBet(this.possibleBets[indexLastBet]) == false) {
        this.bets[indexHears] = this.possibleBets[0];
        says = this.auctionMembers[2];
        hears = this.auctionMembers[indexSays];
        indexHears = indexSays;
        indexSays = 2;
      }
      // they both did not pass
      else {
        // do for all possible bets
        for (int i = 2; i < possibleBets.length; i++) {
          // check if says passes
          if (says.askForBet(this.possibleBets[i]) == false) {
            this.bets[indexSays] = this.possibleBets[0];
            says = this.auctionMembers[2];
            indexSays = 2;
            this.bets[indexHears] = this.possibleBets[indexLastBet];
            break;
          }
          // check if hears passes
          else if (hears.askForBet(this.possibleBets[i]) == false) {
            this.bets[indexHears] = this.possibleBets[0];
            says = this.auctionMembers[2];
            hears = this.auctionMembers[indexSays];
            indexHears = indexSays;
            indexSays = 2;
            this.bets[indexSays] = this.possibleBets[indexLastBet];
            indexLastBet++;
            break;
          } else {
            indexLastBet++;
          }
        }
      }
    }

    // first "conversation" is over, now: between winner and R
    // now R says and the surviver of the first conversation hears

    // check if R wants to bet or pass (1. if --> pass)
    for (int i = indexLastBet + 1; i < this.possibleBets.length; i++) {
      // checks if says want to pass
      if (says.askForBet(this.possibleBets[i]) == false) {
        this.bets[indexSays] = this.possibleBets[0];
        this.bets[indexHears] = this.possibleBets[indexLastBet];
        break;
      }
      // checks if hears wants to pass
      else if (hears.askForBet(this.possibleBets[i]) == false) {
        this.bets[indexHears] = this.possibleBets[0];
        this.bets[indexSays] = this.possibleBets[indexLastBet];
        break;
      } else {
        indexLastBet++;
      }

    }

    this.calculateWinner();

  }

  /**
   * winner sits at the position, where the bet is not null - means he has never passed
   * 
   * @author awesch
   */
  public void calculateWinner() {
    for (int i = 0; i < this.bets.length; i++) {
      if (this.bets[i] != 0) {
        this.winner = this.auctionMembers[i];
      }
    }
  }
  
  /**
   * updates the PlayState of the Play after the auction it self took place
   * @param ps
   * @author awesch
   */

  public void updatePlayState(PlayState ps) {
    // set declarer
    ps.setDeclarer(this.winner);
    // set opponents
    Player[] opponents = new Player[2];
    int indexOp = 0;
    for (int i = 0; i < this.auctionMembers.length; i++) {
      if (this.auctionMembers.equals(this.winner) == false) {
        opponents[indexOp] = this.auctionMembers[i];
        indexOp++;
      }
    }
    ps.setOpponents(opponents);
    // ask the declarer for other settings (PlayMode und maybe trump)
    this.winner.askForPlaySettings(ps);
    // set betValue
    int bV = 0;
    for (int i = 0; i < bets.length; i++) {
      if (bets[i] != 0) {
        bV = bets[i];
      }
    }
    ps.setBetValue(bV);
    
  }

  public static void main(String[] args) {
    Player sandra = new Player("Sandra");
    Player larissa = new Player("Larissa");
    Player felix = new Player("Felix");

    Player[] crew = new Player[3];
    crew[0] = sandra;
    crew[1] = larissa;
    crew[2] = felix;

    PlayState ps = new PlayState();
    Auction test = new Auction(crew, ps);
  }


}

