package gui;

import java.util.List;
import interfaces.LogicGui;
import javafx.application.Platform;
import logic.GameSettings;
import logic.Player;

public class ImplementsLogicGui implements LogicGui {

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
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        guiCon.displayInGame();
      }
    });
    return guiCon.getCon();
  }
 


  /* (non-Javadoc)
   * @see interfaces.LogicGui#updateLobby(logic.GameSettings, java.util.List)
   */
  @Override
  public void updateLobby(GameSettings gs, List<Player> group) {
    guiCon.getLobbyCon().displayPlayers(group.size(), group);
    
    guiCon.getLobbyCon().setGameSettingsLabel(gs);    
    
  }



  /* (non-Javadoc)
   * @see interfaces.LogicGui#showReceivedChatMessage(java.lang.String, logic.Player)
   */
  @Override
  public void showReceivedChatMessage(String mgs, Player player) {
    // TODO Auto-generated method stub
    
  }



}
