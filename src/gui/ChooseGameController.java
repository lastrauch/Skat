package gui;

import interfaces.GuiLogic;
import javafx.fxml.FXML;
import logic.GameController;
import logic.GameMode;

public class ChooseGameController {

  /**
   * @author lstrauch
   */
  @FXML
  private GuiController main;

  /**
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
   * @author lstrauch
   */
  @FXML
  public void SinglePlayer() {
    setGameMode(GameMode.SINGLEPLAYER);
    main.displayGameSettings();
  }

  /**
   * @author lstrauch
   */
  @FXML
  public void MultiPlayer() {
    main.displayLobbyOnline();

  }

  /**
   * @author lstrauch
   */
  @FXML
  public void settings() {
    main.displaySettings();
  }

  /**
   * @author lstrauch
   */
  @FXML
  public void help() {
    main.displayHelp();
  }

  /**
   * @author lstrauch
   */
  @FXML
  public void accountSettings() {
    main.displayAccountSettings();
  }

  
  public void setGameMode(GameMode gm) {
    this.gamemode = gm;
  }
  
  public GameMode getGameMode() {
    return this.gamemode;
  }

}
