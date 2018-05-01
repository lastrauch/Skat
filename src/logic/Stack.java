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

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private List<Card> stack;
  
  public Stack() {
    stack = new ArrayList();
  }
  
  public void addCard(Card card) {
    this.stack.add(card);
  }
  
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
  
  public List<Card> getStack(){
    return this.stack;
  }
}
