package logic;

import java.util.ArrayList;

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
   * 
   */
  public Auction(Player[] auctionMembers, PlayState ps) {
    this.auctionMembers = auctionMembers;
    this.initializePossibleBets();
    this.initializeBets();
    try {
      this.organizeAuction();
      this.updatePlayState(ps);
      this.mangageSkat(ps);
      this.winner.askForPlaySettings(ps);
      // test print
      System.out.println("We will now play: " + ps.getPlayMode().toString());
      ps.setAuctionPossible(true);
    } catch (LogicException e) {
      // TODO Auto-generated catch block
      // e.printStackTrace();
      System.out.println("The aution did not go well.. we'll restart the play!");
      ps.setAuctionPossible(false);
    }
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
  public void organizeAuction() throws LogicException {
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
    if (!says.askForBet(this.possibleBets[indexLastBet])) {
      this.bets[indexSays] = this.possibleBets[0];
      says = this.auctionMembers[2];
      indexSays = 2;
      indexLastBet = 0;
    }
    // if M doesn't want to pass:
    else {
      // check if F wants to pass or bet (if --> pass)
      this.bets[indexSays] = this.possibleBets[indexLastBet];
      if (!hears.askForBet(this.possibleBets[indexLastBet])) {
        this.bets[indexHears] = this.possibleBets[0];
        says = this.auctionMembers[2];
        hears = this.auctionMembers[indexSays];
        indexHears = indexSays;
        indexSays = 2;
        indexLastBet = 0;
      }
      // they both did not pass
      else {
        this.bets[indexHears] = this.possibleBets[indexLastBet];
        // do for all possible bets
        for (int i = 2; i < possibleBets.length; i++) {
          // check if says passes
          if (!says.askForBet(this.possibleBets[i])) {
            this.bets[indexSays] = this.possibleBets[0];
            says = this.auctionMembers[2];
            indexSays = 2;
            this.bets[indexHears] = this.possibleBets[indexLastBet];
            break;
          }
          // check if hears passes
          else if (!hears.askForBet(this.possibleBets[i])) {
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

    // first "conversation" is over, now: between survivor and R
    // now R says and the survivor of the first conversation hears

    // check if R wants to bet or pass (1. if --> pass)
    if (!says.askForBet(this.possibleBets[indexLastBet + 1])) {
      this.bets[indexSays] = this.possibleBets[0];
      // check if they both pass / if hears has bit
      if (this.bets[indexHears] == 0) {
        if (!hears.askForBet(this.possibleBets[indexLastBet + 1])) {
          // check if everyone passed now
          // throws exception if everyone passes
          throw new LogicException(
              "The game is not possible, because everyone passed! RESTART PLAY!");
        } else {
          // the suvivor of the first conversation becomes declarer
          this.bets[indexHears] = this.possibleBets[indexLastBet + 1];
          indexLastBet++;
        }
      } else {
        // the suvivor of the first conversation becomes declarer
        this.bets[indexHears] = this.possibleBets[indexLastBet + 1];
        indexLastBet++;
      }
    } // "says" did not want to pass
    else {
      indexLastBet++;
      for (int i = indexLastBet; i < this.possibleBets.length - 1; i++) {
        // checks if "hears" wants to pass
        if (!hears.askForBet(this.possibleBets[i])) {
          this.bets[indexHears] = this.possibleBets[0];
          this.bets[indexSays] = this.possibleBets[indexLastBet];
          break;
        }
        // checks if "says" wants to pass
        else if (!says.askForBet(this.possibleBets[i + 1])) {
          this.bets[indexSays] = this.possibleBets[0];
          this.bets[indexHears] = this.possibleBets[indexLastBet];
          break;
        } else {
          indexLastBet++;
        }

      }
    }

    // if they bet to the last bit Value the player who hears becomes the declarer (very unlikely to
    // happen
    if (indexLastBet == this.possibleBets.length - 1) {
      if (hears.askForBet(this.possibleBets[indexLastBet])) {
        this.bets[indexSays] = this.possibleBets[0];
        this.bets[indexHears] = this.possibleBets[indexLastBet];
      }
    }
    System.out.println("betValue is:" + this.possibleBets[indexLastBet]);

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
   * 
   * @param ps
   * @author awesch
   */

  public void updatePlayState(PlayState ps) {
    // set declarer
    ps.setDeclarer(this.winner);
    // set opponents (check bit where 0 --> add to opponents)
    Player[] opponents = new Player[2];
    int indexOp = 0;
    for (int i = 0; i < this.bets.length; i++) {
      if (this.bets[i] == 0) {
        opponents[indexOp] = this.auctionMembers[i];
        indexOp++;
      }

    }
    ps.setOpponents(opponents);
    // ask the declarer for other settings (PlayMode und maybe trump)
    // this.winner.askForPlaySettings(ps);
    // set betValue
    int bV = 0;
    for (int i = 0; i < bets.length; i++) {
      if (bets[i] != 0) {
        bV = bets[i];
      }
    }
    ps.setBetValue(bV);
    ps.initializeBaseValue();
    ps.setPlayValue(this.winner.calculatePlayValue(ps));
  }

  public void mangageSkat(PlayState ps) {
    // ask the declarer if he wants to take the skat or not and save the answer in the PlayState
    ps.setHandGame(this.winner.askForHandGame());
    // add the skat & lay it down if answer yes
    if (ps.getHandGame()) {
      this.takeUpStack(ps);
      this.putDownTwoCards(ps);
    }
  }

  /**
   * the two cards of the stack are added to the hand and the hand gets resorted, created for
   * manageSkat
   * 
   * @author awesch
   */
  public void takeUpStack(PlayState ps) {
    this.winner.getHand().add(ps.getSkat()[0]);
    this.winner.getHand().add(ps.getSkat()[1]);
    this.winner.sortHand(ps);
    System.out.println("Your new hand:");
    this.printlistOfCards(this.winner.getHand());
  }

  public void printlistOfCards(ArrayList<Card> cards) {
    for (Card c : cards) {
      System.out.println(c.getColour().toString() + " " + c.getNumber().toString());
    }
  }

  /**
   * two cards are chosen by the declarer and added to his stack, created for manageSkat
   * 
   * @author awesch
   */
  public void putDownTwoCards(PlayState ps) {
    Card card1;
    Card card2;
    try {

      card1 = this.winner.chooseCardFromHand();
      ps.addToStackDeclarer(card1);
      this.winner.removeCardFromHand(card1);

      card2 = this.winner.chooseCardFromHand();
      ps.addToStackDeclarer(card2);
      this.winner.removeCardFromHand(card2);

      System.out.println("Your new hand:");
      this.printlistOfCards(this.winner.getHand());

    } catch (LogicException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }



  // public static void main(String[] args) {
  // Player sandra = new Player("Sandra");
  // Player larissa = new Player("Larissa");
  // Player felix = new Player("Felix");
  //
  // Player[] crew = new Player[3];
  // crew[0] = sandra;
  // crew[1] = larissa;
  // crew[2] = felix;
  //
  // PlayState ps = new PlayState();
  // Auction test = new Auction(crew, ps);
  // }


}

