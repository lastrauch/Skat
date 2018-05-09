package logic;

import ai.AiController;
import ai.Bot;
import ai.BotDifficulty;
import interfaces.GuiLogic;
import interfaces.InGameInterface;
import interfaces.LogicGui;
import interfaces.LogicNetwork;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import network.NetworkController;
import network.server.Server;

public class GameController implements GuiLogic {

  private List<Player> group;
  private LogicGui logicGui; // interface from logic to gui
  private LogicNetwork networkController; // interface from logic to network
  private List<ClientLogic> clientLogic;
  private GameSettings gameSettings;
  private List<Server> server;
  private Server myServer;

  /**
   * constructor.
   * 
   * @param logicGui
   */
  public GameController(LogicGui logicGui) {
    this.logicGui = logicGui;
    this.gameSettings = new GameSettings();
    group = new ArrayList<Player>();
    clientLogic = new ArrayList<ClientLogic>();
    server = new ArrayList<Server>();
  }

  @Override
  /**
   * is called, when the controller logs in.
   * 
   * @author awesch
   * @see interfaces.GuiLogic#login(java.lang.String)
   */
  public void login(String username, Image profilepicture) {
    Player p = new Player(username, profilepicture);
    this.group.add(p);
    System.out.println("yoo I just created a Player " + p.getName() + " (login)");
    ClientLogic clientLogic = new ClientLogic(p);
    clientLogic.setLogicGui(this.logicGui);

    LogicNetwork networkController = new NetworkController(clientLogic);
    clientLogic.setNetworkController(networkController);

    this.clientLogic.add(clientLogic);
    this.networkController = networkController;
  }

  @Override
  /**
   * @author awesch
   */
  public void deleteBot(String botname) {
    for (int i = 0; i < this.clientLogic.size(); i++) {
      if (this.clientLogic.get(i).player.getName().equals(botname)) {
        this.clientLogic.get(i).netController.exitGame();
        this.clientLogic.remove(i);
      }
    }
  }

  @Override
  /**
   * @author awesch
   */
  public void setBot(String botname, BotDifficulty difficulty) {
    String name = "bot" + this.group.size();
    Player p = new Bot(name, difficulty);
    this.group.add(p);
    ClientLogic clientLogic = new ClientLogic(p);
    InGameInterface inGameController =
        new AiController(clientLogic, name, difficulty, this.gameSettings);
    LogicNetwork networkController = new NetworkController(clientLogic);
    clientLogic.setInGameController(inGameController);
    clientLogic.setNetworkController(networkController);
    this.clientLogic.add(clientLogic);
    networkController.joinLobby(myServer, p);
  }

  @Override
  /**
   * @author awesch
   */
  public void joinGame(String hostName) {
    // its serverName not hostName
    for (Server s : this.server) {
      if (s.getServerName().equals(hostName)) {
        this.networkController.joinLobby(s, this.clientLogic.get(0).player);
      }
    }
  }


  @Override
  public void sendChatText(String message) {
    this.clientLogic.get(0).sendChatMessage(message);

  }

  @Override
  public void hostGame(String comment, GameSettings gs) {
    this.gameSettings = gs;
    this.myServer =
        this.networkController.hostGame(this.clientLogic.get(0).player, this.gameSettings, comment);
    this.clientLogic.get(0).setGameSetting(gs);
  }



  @Override
  public void startGame(GameSettings gs) {
    System.out.println("start game method");
    this.gameSettings = gs;
    this.group = this.clientLogic.get(0).getLobby();
    for (ClientLogic cl : this.clientLogic) {
      cl.setInGame(true);
    }

    // if the lobby does not contain enough players
    if (this.group.size() < this.gameSettings.getNrOfPlayers()) {
      for (int i = this.group.size(); i < this.gameSettings.getNrOfPlayers(); i++) {
        String name = "bot" + i;
        this.setBot(name, BotDifficulty.EASY);
      }
    }
    this.networkController.startGame();
  }

  @Override
  public ArrayList<Server> lobbyInformation() {
    ArrayList<Server> lobbyInfo = new ArrayList<Server>();
    lobbyInfo = (ArrayList<Server>) this.networkController.getServer();
    return lobbyInfo;
  }


  @Override
  public Player getPlayer() {
    return this.clientLogic.get(0).getPlayer();
  }

  @Override
  public ArrayList<Card> sortHand(PlayState ps, List<Card> cardlist) {
    ArrayList<Card> hand = (ArrayList<Card>) cardlist;
    return Tools.sortHand(hand, ps);
  }

  @Override
  public void setUsername(String username) {
    this.group.get(0).setName(username);

  }

  @Override
  public void announceContra() {
    this.clientLogic.get(0).announceKontra();

  }

  @Override
  public void announceRekontra() {
    // TODO Auto-generated method stub

  }

}
