/**
 * 
 */
package logic;

/**
 * @author sandr
 *
 */
public class Tools {

  public static Player getDeclarer(Player[] group) {
    for (int i = 0; i < group.length; i++) {
      if (group[i].IsDeclarer()) {
        return group[i];
      }
    }
    return null;
  }
  
  public static Player[] getOpponents(Player[] group) {
    Player[] opponents = new Player[2];
    Player opp1 = null;
    Player opp2 = null;
    
    for (int i = 0; i < group.length; i++) {
      if (!group[i].IsDeclarer() && group[i].getPosition() != Position.DEALER) {
        if (opp1 == null) {
          opp1 = group[i];
          opponents[0] = opp1;
        }else {
          opp2 = group[i];
          opponents[1] = opp2;
        }
      }
    }
    return opponents;
  }
}
