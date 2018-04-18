package interfaces;

import java.util.ArrayList;
import gui.GuiController;
import logic.Card;
import logic.GameMode;
import logic.GameSettings;
import logic.Position;

// Logic to GUI, implemented by GUI
public interface LogicGui {

   /**
   * 
   */
  public GuiController getGuiController();

//  /**
//   * should open LoginScreen
//   */
//  public void openLoginScreen();
  
  /**
   * 
   * @return name of the player who wants to log in
   */
  public String logIn();
  
  /**
   * should open first screen where the player picks single or multiplayer game
   * @return gameMode (SINPLEPLAYER or MULTIPLAYER)
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

  
  // we still need aiDifficulty (enum??) 
  // array with two elements for two AIs
//  public AiDifficulty[] getDifficulties() {
//    
//  }
}

