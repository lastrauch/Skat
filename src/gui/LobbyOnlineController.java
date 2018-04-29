package gui;

import com.jfoenix.controls.JFXButton;
import interfaces.GuiLogic;
import javafx.fxml.FXML;
import logic.GameController;
import logic.GameMode;

public class LobbyOnlineController {

  @FXML
  private JFXButton join;
  
  private GuiController main;

  
  public LobbyOnlineController() {
    this.main = new GuiController();
  }

  /**
   * @author lstrauch
   */
  @FXML
  public void join() {
    LoginController.interfGL.joinGame(LoginController.username);
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

  /**
   * @author lstrauch
   */
  @FXML
  public void startNewGame() {
    main.displayGameSettings(GameMode.MULTIPLAYER);
  }

}
