package logic;

public class Game {

  /**
   * calculates the winner of the whole game
   * 
   * @author sandfisc
   */
  public static Player calculateWinner(PlayState ps) {
    Player winner = ps.getGroup()[0];
    for (int i = 1; i < ps.getGroup().length; i++) {
      if (ps.getGroup()[i].getGameScore() > winner.getGameScore()) {
        winner = ps.getGroup()[i];
      }
    }
    return winner;
  }
}
