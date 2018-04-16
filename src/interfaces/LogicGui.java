package interfaces;

import java.util.ArrayList;
import logic.Card;
import logic.GameSettings;
import logic.Position;

// Logic to GUI, implemented by GUI
public interface LogicGui {

  /**
   * should start the gui and return the name of the Player
   * 
   */
  public String startGui();

  /**
   * should open first screen and return true if controller picks multiplayer game and false if he
   * picks singleplayer game
   * 
   * @return
   */
  public boolean askForMultiplayer();

  /**
   * should open singlePlayerLobby, probably adding some returns later
   * 
   */
  public void openSinglePlayerLobby();
  
  /**
   * should open multiPlayerLobby, probably adding some returns later
   */
  public void openMultiPlayerLobby();

  /**
   * should open the game settings screen and edit the gameSettings gs with setters
   * 
   * @param gs
   */
  public void setGameSettings(GameSettings gs);

  /**
   * should open the InGameScreen and show the Player his cards and his position
   * 
   * @param hand
   * @param position
   */
  public void startPlay(ArrayList<Card> hand, Position position);

}

