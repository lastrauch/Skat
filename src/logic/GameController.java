package logic;

import gui.GuiController;
import interfaces.GuiLogic;
import interfaces.LogicData;
import interfaces.LogicGui;
import interfaces.LogicNetwork;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GameController {

  private Player[] group;
  // private GuiController guiController;
  private LogicGui logicGui;
  private LogicData logicData;
  private LogicNetwork logicNetwork;
  private Game game;

  public GameController(GuiController guiController) {
    this.group = new Player[4]; // if only 3 are playing group[3] is empty
    // this.guiController = guiController;
  }

  public GameController() {
    this.group = new Player[4];
  }

  public void generateGame(GameMode gameMode) {
    this.game = new Game(gameMode);
  }

  public void logIn(String username) {
    this.group[0] = this.logicData.getPlayer(username);
  }

  public void updateAccount(String oldUsername, String newUsername, Image profilbild) {

    Player updateThisPlayer;
    try {
      // update player in group
      updateThisPlayer = this.searchPlayer(oldUsername);
      updateThisPlayer.setName(newUsername);
      updateThisPlayer.setImage(profilbild);

      // update player in own database
      this.updatePlayerInOwnDatabase(updateThisPlayer);

      // update player in ALL databases
      this.logicNetwork.updatePlayer(updateThisPlayer);
    } catch (LogicException e) {
      e.printStackTrace();
    }
  }

  public Player searchPlayer(String userName) throws LogicException {
    for (int i = 0; i < this.group.length; i++) {
      if (this.group[i] != null) {
        if (this.group[i].getName().equals(userName)) {
          return this.group[i];
        }
      }
    }
    throw new LogicException("Player not found!");
  }

  public void updatePlayerInOwnDatabase(Player player) {
    this.logicData.updatePlayer(player);
  }

  public static void main(String[] args) {
  // Application.launch(GuiController.class, args);
  
  }


}
