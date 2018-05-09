package gui;

import interfaces.LogicGui;
import java.util.List;
import logic.GameSettings;
import logic.Player;

public class ImplementsLogicGui implements LogicGui {

  /**
   * initializes non-FXML attributes.
   * @author lstrauch
   */
  private GuiController guiCon = new GuiController();



  /*
   * (non-Javadoc)
   * 
   * @see interfaces.LogicGui#updateLobby(logic.GameSettings, java.util.List)
   * 
   */
  @Override
  public void updateLobby(GameSettings gs, List<Player> group) {
    System.out.println("group.size: " + group.size());
    for(int i = 0; i < group.size(); i++) {
      System.out.println("group(" + i + "): " + group.get(i));
    }
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
    guiCon.getLobbyCon().showChatMessage(mgs, player.getName());
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
