package interfaces;

import java.util.ArrayList;
import logic.Card;
import logic.GameMode;
import logic.GameSettings;
import logic.Position;

// Logic to GUI, implemented by GUI
public interface LogicGui {

  // start should be in main
//  /**
//   * should start the gui
//   * 
//   */
//  public void startGui();

  /**
   * should open first screen where the player picks single or multiplayer game
   * 
   */
  public GameMode decideGameMode();

  /**
   * should open singlePlayerLobby
   * 
   */
  public void openSinglePlayerLobby();
  
  /**
   * should open multiPlayerLobby
   */
  public void openMultiPlayerLobby();

  /**
   * should open the game settings screen and edit the gameSettings gs with setters
   * 
   * @param gs
   */
  public void setGameSettings(GameSettings gs);


}

