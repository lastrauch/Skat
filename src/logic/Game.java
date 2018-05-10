package logic;

import java.util.ArrayList;
import java.util.List;

public class Game {

  /**
   * calculates the winner of the whole game.
   * 
   * @return List of Players
   * @param list of players
   * @author sandfisc
   * @author awesch
   */
  public static List<Player> calculateWinner(List<Player> group) {
    List<Player> winner = new ArrayList<Player>();
    int highestScore = 0;

    // go through the group
    for (Player p : group) {
      int score = 0;

      // go through the playScore
      for (int i : p.getPlayScore()) {

        // if score = -1 the player was dealer in this play
        if (i != -1) {
          score += i;
        }
      }

      // save score in GameScore
      p.setGameScore(score);

      // set highest score
      if (p.getGameScore() > highestScore) {
        highestScore = p.getGameScore();
      }
    }

    // go through the group again and check if highest score
    for (Player p : group) {
      if (p.getGameScore() == highestScore) {
        winner.add(p);
      }
    }

    return winner;
  }
}
