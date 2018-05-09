package interfaces;

import gui.InGameController;
import logic.GameSettings;
import java.util.List;
import logic.Player;

// Logic to GUI, implemented by GUI
public interface LogicGui {

  /**
   * should start the inGameScreen.
   */
  public void startInGameScreen();
  
  /**
   * return an inGameController for every round.
   * 
   * @return inGameController
   */
  public InGameController getInGameController();

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

