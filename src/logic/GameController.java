package logic;

import java.util.Iterator;
import java.util.List;
import gui.GuiController;
import gui.InGameController;
import interfaces.GuiLogic;
import interfaces.InGameInterface;
import interfaces.LogicData;
import interfaces.LogicGui;
import interfaces.LogicNetwork;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GameController {

  private List<Player> group;
  // private GuiController guiController;
  private LogicGui logicGui;
  private LogicNetwork logicNetwork;
  private GuiLogic guiLogic;
  private InGameInterface inGameController;
  private Game game;
  private List<ClientLogic> clientLogic;

  public GameController(GuiLogic guiLogic) {
    this.guiLogic = guiLogic;
  }

  public GameController() {}

  public void generateGame(GameMode gameMode) {
    Iterator<ClientLogic> it = this.clientLogic.iterator();
    while(it.hasNext()) {
      this.game = new Game(gameMode, it.next());
    }    
  }

  // public void logIn(String username) {
  // this.group[0] = this.logicData.getPlayer(username);
  // }

  public void updateAccount(String oldUsername, String newUsername, Image profilbild) {

    Player updateThisPlayer;
    try {
      // update player in group
      updateThisPlayer = this.searchPlayer(oldUsername);
      updateThisPlayer.setName(newUsername);
      updateThisPlayer.setImage(profilbild);

      // update player in own database
      // this.updatePlayerInOwnDatabase(updateThisPlayer);

      // update player in ALL databases
      this.logicNetwork.updatePlayer(updateThisPlayer);
    } catch (LogicException e) {
      e.printStackTrace();
    }
  }

  public Player searchPlayer(String userName) throws LogicException {
    Iterator<Player> it = this.group.iterator();

    while (it.hasNext()) {
      if (it.next().getName().equals(userName)) {
        return it.next();
      }
    }
    throw new LogicException("Player not found!");
  }

  // public void updatePlayerInOwnDatabase(Player player) {
  // this.logicData.updatePlayer(player);
  // }




  public static void main(String[] args) {
    // Application.launch(GuiController.class, args);

  }


}
