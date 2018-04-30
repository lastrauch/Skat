package gui;

import java.util.List;
import interfaces.LogicGui;
import logic.GameSettings;
import logic.Player;

public class ImplementsLogicGui extends GameSettingsController implements LogicGui {

  /**
   * @author lstrauch
   */
  private GuiController guiCon = new GuiController();

  /** (non-Javadoc)
   * @see interfaces.LogicGui#startInGameScreen()
   * 
   * @author lstrauch
   */
  @Override
  public InGameController startInGameScreen() {
    // TODO Auto-generated method stub
    guiCon.displayInGame();
    System.out.println(GuiController.inGameCon);
    return GuiController.inGameCon;
  }


  /* (non-Javadoc)
   * @see interfaces.LogicGui#updateLobby(logic.GameSettings, java.util.List)
   */
  @Override
  public void updateLobby(GameSettings gs, List<Player> group) {
    // TODO Auto-generated method stub
    
  }



}
