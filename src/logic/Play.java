package logic;

import java.util.ArrayList;
import java.util.List;

public class Play {

  /**
   * returns true if declarer over bid false if not.
   * 
   * @param ps PlayState
   * @return boolean
   * @author sandfisc
   */
  public static boolean checkOverBid(PlayState ps) {
    for (Player p : ps.getGroup()) {
      if (ps.getPlayValue() < p.getBet() && p.isDeclarer()) {
        return true;
      } else {
        return false;
      }
    }
    return false;
  }

  /**
   * Calculates the winner of the play, and saves it in the boolean singlePlayerWins uses
   * calculatePoinsOfStack.
   * 
   * @param ps PlayState
   * @return List of players
   * @author awesch
   * @author sandfisc
   */
  public static List<Player> calculateWinner(PlayState ps) {

    List<Player> winner = new ArrayList<Player>();

    // initialize winner with opponents
    for (Player p : ps.getGroup()) {
      if (!p.isDeclarer()) {
        winner.add(p);
      }
    }

    // "singleplayer bidded himself over"
    if (!checkOverBid(ps)) {

      int pointsD = ps.getDeclarerStack().calculatePointsOfStack();
      int pointsO = ps.getOpponentsStack().calculatePointsOfStack();

      // check "schneider"
      if (pointsD >= 90) {
        ps.setSchneider(true);
      } else if (ps.getSchneiderAnnounced()) {
        return winner;
      }

      // check "schwarz"
      if (pointsO == 0) {
        ps.setSchwarz(true);
      } else if (ps.getSchneiderAnnounced()) {
        return winner;
      }

      // there are two possible states where the declarer wins (depends if he plays hand or not)
      // if he plays hand: poinsD >= pointsO (1.), if not: pointsD > pointsO(2.)
      // 1.
      if (pointsD >= pointsO && ps.getHandGame()) {
        for (Player p : ps.getGroup()) {
          if (p.isDeclarer()) {
            winner.set(0, p);
          }
        }
      } else if (pointsD > pointsO && (!ps.getHandGame())) {
        for (Player p : ps.getGroup()) {
          if (p.isDeclarer()) {
            winner.set(0, p);
          }
        }
      }

    }
    return winner;
  }


  /**
   * calculates the value of the game.
   * 
   * @param ps Playstate
   * @param gameSettings (settings of the game)
   * @param declarerWins (tells the methos id the declarer wins
   * @return Playstate (updated)
   * @author sandfisc
   * @throws LogicException if not possible
   */
  public static PlayState calculatePoints(PlayState ps, GameSettings gameSettings,
      boolean declarerWins) throws LogicException {

    // check if the declarer over bid
    if (checkOverBid(ps)) {
      return calculatePointsOverBit(ps);

      // calculate the players points with the countRule
    } else {
      if (gameSettings.getCountRule() == CountRule.NORMAL) {
        return calculatePointsNormal(ps, declarerWins);
      } else if (gameSettings.getCountRule() == CountRule.BIERLACHS) {
        return calculatePointsBierlachs(ps, declarerWins);
      } else {
        return calculatePointsSeegerfabian(ps, declarerWins);
      }
    }
  }

  /**
   * The amount subtracted from the declarer's score is twice the least multiple of the base value
   * of the game actually played which would have fulfilled the bid.
   * 
   * @param ps PlayState
   * @return PlayState (updated)
   */
  public static PlayState calculatePointsOverBit(PlayState ps) {
    int points = ps.getBaseValue();
    while (points < ps.getBetValue()) {
      points += points;
    }
    for (Player p : ps.getGroup()) {
      if (p.isDeclarer()) {
        p.getPlayScore().add((points * (-2)));
      } else {
        p.getPlayScore().add(0);
      }
    }
    return ps;
  }

  /**
   * if declarer won, the value of the game is added to his/her gamePoints else twice the value is
   * subtracted from his/her score.
   * 
   * @param ps PlayState
   * @param declarerWins (boolean)
   * @return PlayState (updated)
   * @author sandfisc
   */
  public static PlayState calculatePointsNormal(PlayState ps, boolean declarerWins) {
    if (declarerWins) {
      for (int i = 0; i < ps.getGroup().length; i++) {
        if (ps.getGroup()[i].isDeclarer() && ps.getGroup()[i].getPosition() != Position.DEALER) {
          ps.getGroup()[i].getPlayScore().add(ps.getPlayValue());
        } else {
          ps.getGroup()[i].getPlayScore().add(0);
        }
      }
    } else {
      for (int i = 0; i < ps.getGroup().length; i++) {
        if (ps.getGroup()[i].isDeclarer() && ps.getGroup()[i].getPosition() != Position.DEALER) {
          ps.getGroup()[i].getPlayScore().add((-2) * (ps.getPlayValue()));
        } else {
          ps.getGroup()[i].getPlayScore().add(0);
        }
      }
    }
    return ps;
  }

  /**
   * only minuspoints here for the player/s who won.
   * 
   * @param declarerWins (boolean)
   * @param ps PlayState
   * @return PlayState
   * @author sandfisc
   */
  public static PlayState calculatePointsBierlachs(PlayState ps, boolean declarerWins) {

    for (Player p : ps.getGroup()) {
      if (p.isDeclarer()) {
        if (declarerWins) {
          p.getPlayScore().add(0);
        } else {
          p.getPlayScore().add((-2) * (ps.getPlayValue()));
        }
      } else {
        if (declarerWins) {
          p.getPlayScore().add((-1) * (ps.getPlayValue()));
        } else {
          p.getPlayScore().add(0);
        }
      }
    }
    return ps;
  }

  /**
   * declarer gets or looses (the playValue + 50).
   * 
   * @param declarerWins (boolean)
   * @param ps PlayState
   * @return PlayState
   * @author sandfisc
   */
  public static PlayState calculatePointsSeegerfabian(PlayState ps, boolean declarerWins) {
    if (declarerWins) {
      for (int i = 0; i < ps.getGroup().length; i++) {
        if (ps.getGroup()[i].isDeclarer() && ps.getGroup()[i].getPosition() != Position.DEALER) {
          ps.getGroup()[i].getPlayScore().add(ps.getPlayValue() + 50);
        } else {
          ps.getGroup()[i].getPlayScore().add(0);
        }
      }
    } else {
      for (int i = 0; i < ps.getGroup().length; i++) {
        if (ps.getGroup()[i].isDeclarer() && ps.getGroup()[i].getPosition() != Position.DEALER) {
          ps.getGroup()[i].getPlayScore().add((-1) * (ps.getPlayValue() + 50));
        } else {
          ps.getGroup()[i].getPlayScore().add(0);
        }
      }
    }
    return ps;
  }

}

