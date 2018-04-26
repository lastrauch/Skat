package logic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import ai.AIController;
import ai.BotDifficulty;
import gui.ImplementsLogicGui;
import gui.InGameController;
import interfaces.GuiLogic;
import interfaces.InGameInterface;
import interfaces.LogicGui;
import interfaces.LogicNetwork;
import interfaces.NetworkLogic;
import javafx.scene.image.Image;
import network.NetworkController;

public class GameController implements GuiLogic {

  private List<Player> group = new ArrayList<Player>();
  private LogicGui logicGui; // interface from logic to gui
  private LogicNetwork logicNetwork; // interface from logic to network
  private GuiLogic guiLogic; // interface from gui to logic
  private List<ClientLogic> clientLogic;
  private Game game;
  private GameSettings gameSettings;

  public GameController(LogicGui logicGui) {
    this.logicGui = logicGui;
    this.gameSettings = new GameSettings();
  }



  // /**
  // * defines in which order players "sitting on a table" (random)
  // *
  // * @author sandfisc
  // */
  // public void defineSeatingList(Player[] group) {
  // int randomIndex;
  // int index = 0;
  // Player temp;
  //
  // for (int i = 0; i < group.length; i++) {
  // randomIndex = (int) (Math.random() * (this.group.length));
  // temp = this.group[i];
  // this.group[i] = this.group[randomIndex];
  // this.group[randomIndex] = this.group[i];
  // }
  // }
  //

  /*
   * (non-Javadoc)
   * 
   * @see interfaces.GuiLogic#updateAccount(java.lang.String, java.lang.String,
   * javafx.scene.image.Image)
   */
  @Override
  public void updateAccount(String oldUsername, String newUsername, Image profilbild) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see interfaces.GuiLogic#decideGameMode(logic.GameMode)
   */
  @Override
  public void decideGameMode(GameMode m) {
    // TODO Auto-generated method stub
    // if (m == GameMode.MULTIPLAYER) {
    // this.logicGui.openMultiPlayerLobby();
    // }else {
    // this.logicGui.openSinglePlayerLobby();
    // }
  }


  @Override
  /**
   * (non-Javadoc)
   * 
   * @author awesch
   * @see interfaces.GuiLogic#login(java.lang.String)
   */
  public void login(String username, Image profilepicture) {
    Player p = new Player(username, profilepicture);
    this.group.add(p);
    InGameInterface inGameController = new InGameController();
    ClientLogic clientLogic = new ClientLogic(p, inGameController);
    LogicNetwork networkController = new NetworkController(clientLogic);
    // auskommentiert, weil in clientLogic noch nicht
    // clientLogic.setNetworkController(networkController);
    this.clientLogic.add(clientLogic);
  }

  @Override
  public void deleteBot(String botname) {
    // TODO Auto-generated method stub

  }

  @Override
  /**
   * @author awesch
   */
  public void setBot(String botname, BotDifficulty difficulty) {
    Player p = new Player(botname);
    this.group.add(p);
    // InGameInterface inGameController = new AIController(botname, difficulty,this.gameSettings);
    // ClientLogic clientLogic = new ClientLogic(p, inGameController);
    // LogicNetwork networkController = new NetworkController(clientLogic);
    // clientLogic.setNetworkController(networkController);
    // this.clientLogic.add(clientLogic);
  }

  /**
   * supposed to be in the gui logic interface as well
   */
  public void joinGame(String hostName) {

  }

  /**
   * supposed to be in the gui logic interface as well
   */
  public void hostGame(String comment) {

  }
}
