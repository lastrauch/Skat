package interfaces;

import java.util.ArrayList;
import java.util.List;
import gui.GuiController;
import gui.InGameController;
import logic.Card;
import logic.GameMode;
import logic.GameSettings;
import logic.Player;
import logic.Position;
import network.server.Server;

// Logic to GUI, implemented by GUI
public interface LogicGui {

  /**
   * method should open the inGameScreen
   */
  public void startInGameScreen();
  
  public void updateLobby(GameSettings gs, List<Player> group);
  
  
 
}

