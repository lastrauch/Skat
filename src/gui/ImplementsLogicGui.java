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
  public void startInGameScreen() {
    // TODO Auto-generated method stub
    guiCon.displayInGame();
  }


  /* (non-Javadoc)
   * @see interfaces.LogicGui#updateLobby(logic.GameSettings, java.util.List)
   */
  @Override
  public void updateLobby(GameSettings gs, List<Player> group) {
    // TODO Auto-generated method stub
    
  }



}
