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
  private InGameInterface inGameController;
  private Game game;
  private ClientLogic clientLogic;

  public GameController(InGameController inGameController) {
    this.inGameController = inGameController;
  }

  public GameController() {}

  public void generateGame(GameMode gameMode) {
    this.game = new Game(gameMode);
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
  public void receiveConnectionRequestAsnwer(boolean accepted) {}

  void receiveLobby(List<Player> player, GameSettings gs) {
    this.group = player;
    this.game.setGameSettings(gs);
  }

  void receiveChatMessage(Player player, String msg) {}

  void receiveStartGame() {
    Player[] players = new Player[this.group.size()];
    int index = 0;
    Iterator<Player> it = this.group.iterator();
    
    while (it.hasNext()) {
      players[index] = it.next();
      index++;
    }
    this.game.runGame(players);
  }

  void receiveCards(Card[] cards) {
  //   this.clientLogic.player.setHand(cards);
  }

  void receiveBet(Player player, int bet) {}

  public void receiveGameSettings(GameSettings gs) {
    this.game.setGameSettings(gs);
  }

  void receiveCardPlayed(Player player, Card card) {
    // receive card in game logic
    this.game.getCurrentPlay().getCurrentTrick().addCard(card);
    // receive card in client logic
    this.clientLogic.inGameController.updateTrick(this.game.getCurrentPlay().getCurrentTrick().getTrickCards());
  }

  void receiveYourTurn() {
    this.clientLogic.inGameController.askToPlayCard();
  }

  void receivePlayerDisconnected(Player player) {}

  public void startClientLogic(Player player) {
    this.clientLogic = new ClientLogic(player, this.inGameController);
  }

  public static void main(String[] args) {
    // Application.launch(GuiController.class, args);

  }


}
