package interfaces;

import gui.InGameController;
import logic.GameSettings;
import logic.Player;
import java.util.List;

// Logic to GUI, implemented by GUI
public interface LogicGui {

  /**
   * should start the in game screen and return an inGameController.
   * 
   * @return inGameController
   */
  public InGameController startInGameScreen();

  /**
   * should update the lobbyscreen, if recieved new one.
   * 
   * @param gs
   * @param group
   */
  public void updateLobby(GameSettings gs, List<Player> group);

  /**
   * should show chat message to user.
   * 
   * @param mgs
   * @param player
   */
  public void showReceivedChatMessage(String mgs, Player player);


}

