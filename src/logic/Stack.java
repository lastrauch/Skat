/**
 * 
 */
package logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sandfisc
 *
 */
public class Stack implements Serializable{

  private static final long serialVersionUID = 1L;
  private List<Card> stack; // List of cards on a stack
  
  /*-------------------------  CONSTRUCTOR  -------------------------------------*/
  public Stack() {
    stack = new ArrayList();
  }
  
  /*-------------------------  HANDLING OF A STACK -----------------------------*/
  /**
   * Adds a card to the list "stack"
   * @author sandfisc
   * @param card
   */
  public void addCard(Card card) {
    this.stack.add(card);
  }
  
  /**
   * Adds a list of cards to the list "stack"
   * @param cards
   */
  public void addCards(List<Card> cards) {
    this.stack.addAll(cards);
  }
  
  /**
   * Calculates the points of a variable stack of cards created for calculateWinner
   * 
   * @param stack
   * @return
   * @author awesch
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
   * Returns the stack
   * @author sandfisc
   * @return
   */
  public List<Card> getStack(){
    return this.stack;
  }
}
