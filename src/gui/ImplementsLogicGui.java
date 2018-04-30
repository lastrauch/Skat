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
    InGameController inGCon = new InGameController();
    System.out.println(inGCon);
    return inGCon;
  }


  /* (non-Javadoc)
   * @see interfaces.LogicGui#updateLobby(logic.GameSettings, java.util.List)
   */
  @Override
  public void updateLobby(GameSettings gs, List<Player> group) {
    // TODO Auto-generated method stub
    LobbyController.displayPlayers(group.size(), group);
    LobbyController.setGamesettings(gs);    
  }



}
