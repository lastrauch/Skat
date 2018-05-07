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



  /*
   * (non-Javadoc)
   * 
   * @see interfaces.LogicGui#updateLobby(logic.GameSettings, java.util.List)
   */
  @Override
  public void updateLobby(GameSettings gs, List<Player> group) {
    guiCon.getLobbyCon().displayPlayers(group.size(), group);

    guiCon.getLobbyCon().setGameSettingsLabel(gs);

  }



  /*
   * (non-Javadoc)
   * 
   * @see interfaces.LogicGui#showReceivedChatMessage(java.lang.String, logic.Player)
   */
  @Override
  public void showReceivedChatMessage(String mgs, Player player) {
    // TODO Auto-generated method stub

  }



  /*
   * (non-Javadoc)
   * 
   * @see interfaces.LogicGui#startInGameScreen()
   */
  @Override
  public void startInGameScreen() {
    // TODO Auto-generated method stub
    guiCon.displayInGame();
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }



  /*
   * (non-Javadoc)
   * 
   * @see interfaces.LogicGui#getInGameController()
   */
  @Override
  public InGameController getInGameController() {
    // TODO Auto-generated method stub

    return guiCon.getCon();
  }



}
