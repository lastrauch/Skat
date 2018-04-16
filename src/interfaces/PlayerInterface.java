package interfaces;

import java.util.ArrayList;
import javafx.scene.image.Image;
import logic.Card;
import logic.Trick;

public interface PlayerInterface {
  
  /**
   * returns the current hand of the Player
   * implemented by logic
   * 
   * @return 
   */
  public ArrayList<Card> getHand();
  
  /**
   * checks if a Card is allowed to play
   * implemented by logic
   * 
   * @return
   */
  public boolean checkIfCardPossible(Trick currentTrick, Card card);
  
  
}
