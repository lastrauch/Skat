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
/**
 * should show the name plus comment of the games which are open and where you can join
 * @param hostName
 * @param comment
 */
  public void showOpenLobby(String hostName, String comment);
  
  
  
}

