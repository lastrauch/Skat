package logic;

import interfaces.GuiLogic;
import interfaces.LogicData;
import javafx.scene.image.Image;

public class ImplementsGuiInterface extends GameController implements GuiLogic {

  private LogicData logicData;
  private Game game;
  
  /* (non-Javadoc)
   * @see interfaces.GuiLogic#login(java.lang.String)
   */
  @Override
  public void login(String username) {
    // TODO Auto-generated method stub
    
  }

  /* (non-Javadoc)
   * @see interfaces.GuiLogic#updateAccount(java.lang.String, javafx.scene.image.Image)
   */
  @Override
  public void updateAccount(String username, Image profilbild) {
    // TODO Auto-generated method stub    
  }


  /* (non-Javadoc)
   * @see interfaces.GuiLogic#decideGameMode(logic.GameMode)
   */
  @Override
  public void decideGameMode(GameMode m) {
    // TODO Auto-generated method stub
    super.generateGame(m);
  }



}
