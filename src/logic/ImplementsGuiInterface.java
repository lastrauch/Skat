package logic;

import interfaces.GuiLogic;
import javafx.scene.image.Image;

public class ImplementsGuiInterface implements GuiLogic {

  // Which order?? we can return an array of "Card" instances where you can get the Image 
  // We only get "Blob" Images from the sqlite database
  @Override
  public Image[] getCards() {
    // TODO Auto-generated method stub
    return null;
  }


  /* (non-Javadoc)
   * @see interfaces.GuiLogic#retCardIndex(int)
   */
  @Override
  public void retCardIndex(int i) {
    // TODO Auto-generated method stub
    
  }

  /* (non-Javadoc)
   * @see interfaces.GuiLogic#setAskForBet(boolean)
   */
  @Override
  public void setAskForBet(boolean value) {
    // TODO Auto-generated method stub
    
  }


}
