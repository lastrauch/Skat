package logic;

public class Play {
  
  /**
   * true if declarer over bid false if not
   * 
   * @author sandfisc
   * @return
   */
  public static boolean checkOverBid(PlayState ps) {
    if (ps.getPlayValue() < Tools.getDeclarer(ps.getGroup()).getBet()) {
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
   * declarer gets or looses (the playValue + 50)
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

}

