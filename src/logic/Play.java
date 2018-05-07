package logic;

public class Play {

  /**
   * true if declarer over bid false if not
   * 
   * @author sandfisc
   * @return
   */
  public static boolean checkOverBid(PlayState ps) {
    for (int i = 0; i < ps.getGroup().length; i++) {
      if (ps.getPlayValue() < ps.getGroup()[i].getBet() && ps.getGroup()[i].isDeclarer()) {
        return true;
      } else {
        return false;
      }
    }
    return false;
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
        for (Player p : ps.getGroup()) {
          if (p.isDeclarer()) {
            winner[0] = p;
          }
        }
      }
      // 2.
      else if (pointsD > pointsO && (!ps.getHandGame())) {
        for (Player p : ps.getGroup()) {
          if (p.isDeclarer()) {
            winner[0] = p;
          }
        }
      }
      // in every other case the team wins
      else {
        for (Player p : ps.getGroup()) {
          if (!p.isDeclarer()) {
            if (winner[0] == null) {
              winner[0] = p;
            }else {
              winner[1] = p;
            }           
          }
        }
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
   * if declarer won, the value of the game is added to his/her gamePoints else twice the
   * value is subtracted from his/her score
   * 
   * @author sandfisc
   */
  public static void calculatePointsNormal(PlayState ps, boolean declarerWins) {
    if (declarerWins) {
      for (int i = 0; i < ps.getGroup().length; i++) {
        if (ps.getGroup()[i].isDeclarer() && ps.getGroup()[i].getPosition() != Position.DEALER) {
          ps.getGroup()[i].addToGamePoints((ps.getPlayValue()));
        }
      }   
    } else {
      for (int i = 0; i < ps.getGroup().length; i++) {
        if (ps.getGroup()[i].isDeclarer() && ps.getGroup()[i].getPosition() != Position.DEALER) {
          ps.getGroup()[i].addToGamePoints((-2) * (ps.getPlayValue()));
        }
      }   
    }
  }

  /**
   * only minuspoints here for the player/s who won
   * 
   * @author sandfisc
   */
  public static void calculatePointsBierlachs(PlayState ps, boolean declarerWins) {
    if (declarerWins) {      
      for (int i = 0; i < ps.getGroup().length; i++) {
        if (!ps.getGroup()[i].isDeclarer() && ps.getGroup()[i].getPosition() != Position.DEALER) {
          ps.getGroup()[i].addToGamePoints((-1) * (ps.getPlayValue()));
        }
      }     
    } else {
      for (int i = 0; i < ps.getGroup().length; i++) {
        if (ps.getGroup()[i].isDeclarer() && ps.getGroup()[i].getPosition() != Position.DEALER) {
          ps.getGroup()[i].addToGamePoints((-2) * (ps.getPlayValue()));
        }
      }
    }
  }

  /**
   * declarer gets or looses (the playValue + 50)
   * 
   * @author sandfisc
   */
  public static void calculatePointsSeegerfabian(PlayState ps, boolean declarerWins) {
    if (declarerWins) {
      for (int i = 0; i < ps.getGroup().length; i++) {
        if (ps.getGroup()[i].isDeclarer() && ps.getGroup()[i].getPosition() != Position.DEALER) {
          ps.getGroup()[i].addToGamePoints(ps.getPlayValue() + 50);
        }
      }     
    } else {
      for (int i = 0; i < ps.getGroup().length; i++) {
        if (ps.getGroup()[i].isDeclarer() && ps.getGroup()[i].getPosition() != Position.DEALER) {
          ps.getGroup()[i].addToGamePoints((-1) * (ps.getPlayValue() + 50));
        }
      }    
    }
  }
  
  

}

