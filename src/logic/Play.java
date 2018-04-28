package logic;

import java.util.ArrayList;
import java.util.List;
import gui.InGameController;
import interfaces.InGameInterface;
import interfaces.LogicNetwork;

public class Play {

  private Player[] group; // gives us the Players and their position (first one is the
                          // forehand)
  private Card[] cards;
  private Trick[] tricks;
  private int currentTrick;
  private int indexWinnerLastTrick;
  private PlayState ps;
  private final int nrTricks = 10;
  private GameSettings gameSettings;
  private boolean singlePlayerWins;
  private LogicNetwork logicNetwork;
  private ClientLogic clientLogic;

  // neu und sinnvoll
  public static Player declarer;
  public static Player opponents1;
  public static Player opponents2;

  /**
   * constructor
   * 
   * @param group
   * @param gameSettings
   * @param cards
   */
  public Play(Player[] group, GameSettings gameSettings, Card[] cards) {
    this.tricks = new Trick[this.nrTricks];
    // this.runPlay();
    this.indexWinnerLastTrick = 0; // forehand starts the first trick
    this.currentTrick = 0;
    this.ps = new PlayState(group);
    this.gameSettings = gameSettings;
    this.cards = cards;
    this.logicNetwork.startGame();
  }



  // /**
  // * updates the hands of the group
  // *
  // * @author sandfisc
  // */
  // public void updateHands() {
  // for (int i = 0; i < this.group.length; i++) {
  // this.group[i].updateHand();
  // }
  // }
  //
  // /**
  // * starts the gui on all clients
  // */
  // public void startPlayOnGui() {
  // for (int i = 0; i < this.group.length; i++) {
  // this.group[i].startPlay();
  // }
  // }


  /**
   * updates the trick of all clients --> the new card is send to all players
   * 
   * @sandfisc
   * @param card
   */
  public void updateTrick(Card card) {
    // this.logicNetwork.updateTrick(this.tricks[this.currentTrick]);
    for (int i = 0; i < this.group.length; i++) {
      // this.logicNetwork.sendCard(card, this.group[i]);
    }
  }

  /**
   * true if declarer over bid false if not
   * 
   * @author sandfisc
   * @return
   */
  public static boolean checkOverBid(PlayState ps) {
    if (ps.getPlayValue() < declarer.getBet()) {
      return false;
    } else {
      return true;
    }
  }

  /**
   * Calculates the winner of the play, and saves it in the boolean singlePlayerWins uses
   * calculatePoinsOfStack
   * 
   * @author awesch
   * @author sandfisc
   */
  public static Player[] calculateWinner(PlayState ps) {

    Player winner[] = new Player[2];
    // "singleplayer bidded himself over"
    if (checkOverBid(ps)) {
      winner = Tools.getOpponents(ps.getGroup());
    } else {

      int pointsD = ps.getDeclarerStack().calculatePointsOfStack();
      int pointsO = ps.getOpponentsStack().calculatePointsOfStack();

      // check "schneider"
      if (pointsD >= 90) {
        ps.setSchneider(true);
      } else if (ps.getSchneiderAnnounced()) {
        winner = Tools.getOpponents(ps.getGroup());
      }

      // check "schwarz"
      if (pointsO == 0) {
        ps.setSchwarz(true);
      } else if (ps.getSchneiderAnnounced()) {
        winner = Tools.getOpponents(ps.getGroup());
      }

      // there are two possible states where the declarer wins (depends if he plays hand or not)
      // if he plays hand: poinsD >= pointsO (1.), if not: pointsD > pointsO(2.)
      // 1.
      if (pointsD >= pointsO && ps.getHandGame()) {
        winner[0] = Tools.getDeclarer(ps.getGroup());
      }
      // 2.
      else if (pointsD > pointsO && (!ps.getHandGame())) {
        winner[0] = Tools.getDeclarer(ps.getGroup());
      }
      // in every other case the team wins
      else {
        winner = Tools.getOpponents(ps.getGroup());
      }
    }
    return winner;

  }

  
  /**
   * deals out the cards on a basic level as it is done in the original game we did it like this
   * because we want it original intern as well
   * 
   * @author awesch
   */
  public static void dealOutCards(Player[] group, List<Card> cards2, PlayState ps) {
    // idea: deal out as in the original game, just because we want it intern
    // needed : position forehand, players of the game, how many players?,

    // forehand is the position 0 of group array
    ArrayList<Card> handF = new ArrayList<Card>();
    ArrayList<Card> handM = new ArrayList<Card>();
    ArrayList<Card> handR = new ArrayList<Card>();
    ArrayList<ArrayList<Card>> crew = new ArrayList<ArrayList<Card>>();
    crew.add(handF);
    crew.add(handM);
    crew.add(handR);
    Card[] skat = new Card[2];
    int counter = 0; // points on first card (next to deal out)

    // deal out first 9 cards (3 each)
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        crew.get(i).add(cards2.get(counter));
        counter++;
      }
    }

    // deal out skat
    for (int i = 0; i < 2; i++) {
      skat[i] = cards2.get(counter);
      counter++;
    }

    // deal out 4 cards each
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 4; j++) {
        crew.get(i).add(cards2.get(counter));
        counter++;
      }
    }

    // deal out 3 cards each
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        crew.get(i).add(cards2.get(counter));
        counter++;
      }
    }

    group[0].setHand(handF);
    group[1].setHand(handM);
    group[2].setHand(handR);
    ps.setSkat(skat);

  }

  /**
   * sorts the Hands of every player in the Play can be called before and after the PlayMode and all
   * the other PlayState attributes are initialized uses sortHand
   * 
   * @author awesch
   */
  public void sortHands() {
    for (int i = 0; i < this.group.length; i++) {
      this.group[i].sortHand(this.ps);
    }
  }

  /**
   * calculates the value of the game
   * 
   * @author sandfisc
   * @throws LogicException
   */
  public static void calculatePoints(PlayState ps, GameSettings gameSettings, boolean declarerWins)
      throws LogicException {

    // check if the declarer over bid
    if (checkOverBid(ps)) {
      calculatePointsOverBit(ps);

      // calculate the players points with the countRule
    } else {
      if (gameSettings.getCountRule() == CountRule.NORMAL) {
        calculatePointsNormal(ps, declarerWins);
      } else if (gameSettings.getCountRule() == CountRule.BIERLACHS) {
        calculatePointsBierlachs(ps, declarerWins);
      } else {
        calculatePointsSeegerfabian(ps, declarerWins);
      }
    }
  }

  /**
   * The amount subtracted from the declarer's score is twice the least multiple of the base value
   * of the game actually played which would have fulfilled the bid
   */
  public static void calculatePointsOverBit(PlayState ps) {
    int points = ps.getBaseValue();
    while (points < ps.getBetValue()) {
      points += points;
    }
    Tools.getDeclarer(ps.getGroup()).addToGamePoints(points * (-2));
  }

  /**
   * if declarer won he/she the value of the game is added to his/her gamePoints else twice the
   * value is subtracted from his/her score
   * 
   * @author sandfisc
   */
  public static void calculatePointsNormal(PlayState ps, boolean declarerWins) {
    if (declarerWins) {
      Tools.getDeclarer(ps.getGroup()).addToGamePoints(ps.getPlayValue());
    } else {
      Tools.getDeclarer(ps.getGroup()).addToGamePoints((-2) * (ps.getPlayValue()));
    }
  }

  /**
   * only minuspoints here for the player/s who won
   * 
   * @author sandfisc
   */
  public static void calculatePointsBierlachs(PlayState ps, boolean declarerWins) {
    if (declarerWins) {
      Tools.getOpponents(ps.getGroup())[0].addToGamePoints((-1) * (ps.getPlayValue()));
      Tools.getOpponents(ps.getGroup())[1].addToGamePoints((-1) * (ps.getPlayValue()));
    } else {
      Tools.getDeclarer(ps.getGroup()).addToGamePoints((-2) * (ps.getPlayValue()));
    }
  }

  /**
   * declarer gets or losses (the playValue + 50)
   * 
   * @author sandfisc
   */
  public static void calculatePointsSeegerfabian(PlayState ps, boolean declarerWins) {
    if (declarerWins) {
      Tools.getDeclarer(ps.getGroup()).addToGamePoints(ps.getPlayValue() + 50);
    } else {
      Tools.getDeclarer(ps.getGroup()).addToGamePoints((-1) * (ps.getPlayValue() + 50));
    }
  }

  /**
   * 
   * @return the gameSettings of the play/game
   */
  public GameSettings getGameSettings() {
    return this.gameSettings;
  }

  /**
   * method to update the gameSettings
   * 
   * @param gameSettings
   */
  public void setGameSettings(GameSettings gameSettings) {
    this.gameSettings = gameSettings;
  }

  /**
   * 
   * @return playstate
   */
  public PlayState getPlayState() {
    return this.ps;
  }

  public void setPlayState(PlayState ps) {
    this.ps = ps;
  }

  /**
   * gets the last trick (not only important for AI)
   * 
   * @return
   */
  public Trick getLastTrick() {
    Trick lastTrick = new Trick(ps);
    if (this.currentTrick > 0) {
      lastTrick = this.tricks[this.currentTrick - 1];
    }
    return lastTrick;
  }

  /**
   * gets the current trick (even if it is not filled)
   * 
   * @return
   */
  public Trick getCurrentTrick() {
    return this.tricks[this.currentTrick];
  }
}

