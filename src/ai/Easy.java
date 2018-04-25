package ai;

import java.util.ArrayList;
import java.util.List;

import logic.Card;
import logic.LogicException;

public class Easy {
  
  
  public static int playCard(AIController controller){
    List<Card> cards = controller.getBot().getHand();
    List<Card> possibleCards = new ArrayList<Card>();
    if(controller.getCurrentTrick().size() > 0){
        for(int i=0; i<cards.size(); i++){
            try {
                if(controller.getBot().checkIfCardPossible(cards.get(i), controller.getCurrentTrick().get(0))){
                    possibleCards.add(cards.get(i));
                }
            } catch (LogicException e) {
                e.printStackTrace();
            }
        }
    }else{
        possibleCards = cards;
    }
    
    
    return 0;
  }
  
  public static boolean setBet(AIController controller, int bet){
    
    return false;
  }

}
