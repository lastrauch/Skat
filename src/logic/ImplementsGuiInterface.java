package logic;

import interfaces.GuiLogic;
import interfaces.LogicData;
import javafx.scene.image.Image;

public class ImplementsGuiInterface extends GameController implements GuiLogic {
  
  /* (non-Javadoc)
   * @see interfaces.GuiLogic#login(java.lang.String)
   */
  @Override
  public void login(String username) {
    // TODO Auto-generated method stub
 //   super.logIn(username);
  }

  /* (non-Javadoc)
   * @see interfaces.GuiLogic#decideGameMode(logic.GameMode)
   */
  @Override
  public void decideGameMode(GameMode m) {
    // TODO Auto-generated method stub
    super.generateGame(m);
  }


  /* (non-Javadoc)
   * @see interfaces.GuiLogic#updateAccount(java.lang.String, java.lang.String, javafx.scene.image.Image)
   */
  @Override
  public void updateAccount(String oldUsername, String newUsername, Image profilbild) {
    // TODO Auto-generated method stub
    super.updateAccount(oldUsername, newUsername, profilbild);
  }



}
