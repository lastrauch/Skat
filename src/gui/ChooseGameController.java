package gui;

import javafx.fxml.FXML;
import logic.GameMode;

public class ChooseGameController {

  /**
   * initializes FXML-attributes.
   * @author lstrauch
   */
  @FXML
  private GuiController main;

  /**
   * initializes non-FXML-attributes.
   * @author lstrauch
   */
  private GameMode gamemode;

  /**
   *@author lstrauch
   */
  public ChooseGameController() {
    this.main = new GuiController();
    GuiController.prevScreen = 1;
  }

  /**
   * sets gameMode = SINGLEPLAYER.
   * opens GameSettings - screen.
   * @author lstrauch
   */
  @FXML
  public void singlePlayer() {
    setGameMode(GameMode.SINGLEPLAYER);
    main.displayGameSettings();
  }

  /**
   * opens lobbyOnle - screen.
   * @author lstrauch
   */
  @FXML
  public void multiPlayer() {
    main.displayLobbyOnline();

  }

  /**
   * opens Settings-screen.
   * @author lstrauch
   */
  @FXML
  public void settings() {
    main.displaySettings();
  }

  /**
   * opens Help-screen.
   * @author lstrauch
   */
  @FXML
  public void help() {
    main.displayHelp();
  }

  /**
   * opens AccountSettings-screen.
   * @author lstrauch
   */
  @FXML
  public void accountSettings() {
    main.displayAccountSettings();
  }

  
  /**
   * sets GameMode.
   * @author lstrauch
   * @param gm
   */
  public void setGameMode(GameMode gm) {
    this.gamemode = gm;
  }
  
  
  /** returns GameMode.
   * @author lstrauch
   * @return this.gamemode
   */
  public GameMode getGameMode() {
    return this.gamemode;
  }

}
