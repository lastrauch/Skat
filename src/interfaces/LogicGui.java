package interfaces;

import java.util.ArrayList;
import java.util.List;
import gui.GuiController;
import logic.Card;
import logic.GameMode;
import logic.GameSettings;
import logic.Position;
import network.server.Server;

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

  /**
   * method should open the inGameScreen
   */
  public void startInGameScreen();
  
}

