package gui;

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
    InGameController inGCon = new InGameController();
    return inGCon;
  }

  /* (non-Javadoc)
   * @see interfaces.LogicGui#updateLobby(logic.GameSettings, logic.Player[])
   */
  @Override
  public void updateLobby(GameSettings gs, Player[] group) {
    // TODO Auto-generated method stub
    
  }



}
