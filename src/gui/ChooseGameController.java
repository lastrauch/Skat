package gui;

import interfaces.GuiLogic;
import javafx.fxml.FXML;
import logic.GameController;
import logic.GameMode;

public class ChooseGameController {

  @FXML
  private GuiController main;

  private GameMode gamemode;
  private GuiLogic interf;

  public ChooseGameController() {
    this.main = new GuiController();
    interf = new GameController();
  }

  @FXML
  public void SinglePlayer() {
    main.displayLobbyLocal();
    gamemode = GameMode.SINGLEPLAYER;
//    interf.decideGameMode(gamemode);
  }

  @FXML
  public void MultiPlayer() {
    main.displayLobbyOnline();
    gamemode = GameMode.MULTIPLAYER;
//    interf.decideGameMode(gamemode);

  }

  @FXML
  public void settings() {
    main.displaySettings();
  }

  @FXML
  public void help() {
    main.displayHelp();
  }

  @FXML
  public void accountSettings() {
    main.displayAccountSettings();
  }

  public GameMode getGameMode() {
    return gamemode;
  }

}
