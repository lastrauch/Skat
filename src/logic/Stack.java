
package logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * this class represents the stack of a player.
 * 
 * @author sandfisc
 */
public class Stack implements Serializable {

  private static final long serialVersionUID = 1L;
  private List<Card> stack; // List of cards on a stack

  /*-------------------------  CONSTRUCTOR  -------------------------------------*/
  public Stack() {
    stack = new ArrayList<Card>();
  }

  /*-------------------------  HANDLING OF A STACK -----------------------------*/
  /**
   * Adds a card to the list "stack".
   * 
   * @author sandfisc
   * @param card to add
   */
  public void addCard(Card card) {
    this.stack.add(card);
  }

  /**
   * Adds a list of cards to the list "stack".
   * 
   * @param cards to add
   */
  public void addCards(List<Card> cards) {
    this.stack.addAll(cards);
  }

  /**
   * returns the points of a variable stack of cards, created for calculateWinner.
   * 
   * @author awesch
   * @return poins of the stack
   */
  public int calculatePointsOfStack() {
    int sum = 0;
    for (int i = 0; i < this.stack.size(); i++) {
      sum += this.stack.get(i).getValue();
    }
    return sum;
  }

  /*----------------------------  GETTER  -------------------------------------------*/

  /**
   * Returns the stack.
   * 
   * @author sandfisc
   * @return this stack
   */
  public List<Card> getStack() {
    return this.stack;
  }
}
