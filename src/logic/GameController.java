package logic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import ai.AIController;
import ai.Bot;
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
import network.server.Server;

public class GameController implements GuiLogic {

  private List<Player> group;
  private LogicGui logicGui; // interface from logic to gui
  private LogicNetwork networkController; // interface from logic to network
  private GuiLogic guiLogic; // interface from gui to logic
  private List<ClientLogic> clientLogic;
  private Game game;
  private GameSettings gameSettings;
  private List<Server> server;
  private Server myServer;


  public GameController(LogicGui logicGui) {
    this.logicGui = logicGui;
    this.gameSettings = new GameSettings();
    group = new ArrayList<Player>();
    clientLogic = new ArrayList<ClientLogic>();
    server = new ArrayList<Server>();
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
    System.out.println("yoo I just created a Player " + p.getName() + " (login)");
    // InGameInterface inGameController = new InGameController();
    ClientLogic clientLogic = new ClientLogic(p);
    LogicNetwork networkController = new NetworkController(clientLogic);
    clientLogic.setNetworkController(networkController);
    this.clientLogic.add(clientLogic);
    this.networkController = networkController;
  }

  // FRAGE!! WAS PASSIERT WENN DIE CLIENTLOGIK NUR AUS DER LISTE GELOESCHT WIRD??
  // eventuell muss die ClientLogik erst bei start
  @Override
  /**
   * @author awesch
   */
  public void deleteBot(String botname) {
    for (int i = 0; i < this.group.size(); i++) {
      if (botname.equals(this.group.get(i).getName())) {
        this.group.remove(i);
      }
    }
  }

  @Override
  /**
   * @author awesch
   */
  public void setBot(String botname, BotDifficulty difficulty) {
    Player p = new Bot(botname, difficulty);
    this.group.add(p);
    // InGameInterface inGameController = new AIController(botname, difficulty, this.gameSettings);
    // ClientLogic clientLogic = new ClientLogic(p);
    // LogicNetwork networkController = new NetworkController(clientLogic);
    // clientLogic.setNetworkController(networkController);
    // this.clientLogic.add(clientLogic);
  }

  @Override
  /**
   * @author awesch
   */
  public void joinGame(String hostName) {
    // its serverName not hostName
    for (Server s : this.server) {
      if (s.getServerName().equals(hostName)) {
        this.networkController.joinLobby(s, this.group.get(0));
      }
    }
  }


  @Override
  public String getChatText() {
    // TODO Auto-generated method stub
    return null;
  }



  @Override
  public void sendChatText(String message) {
    // TODO Auto-generated method stub

  }



  @Override
  public void hostGame(String comment, GameSettings gs) {
    System.out.println("start hostGame method");
    this.myServer = this.networkController.hostGame(this.group.get(0), this.gameSettings, comment);
  }


  @Override
  public void startGame(GameSettings gs) {
    System.out.println("start game method");
    // only used in the singlePlayer mod?!
    this.gameSettings = gs;
    this.myServer = this.networkController.hostGame(this.group.get(0), this.gameSettings, " ");
    System.out.println("finished host game");
    // if the player did not set enough bots to play with the chosen number of players we fill the
    // gaps automatically
    for (int i = 1; i < this.gameSettings.getNrOfPlayers(); i++) {
      Bot temp;
      if (i < this.group.size()) {
        temp = (Bot) this.group.get(i);
      } else {
        String name = "bot" + i;
        temp = new Bot(name, BotDifficulty.EASY);
      }
      InGameInterface inGameController =
          new AIController(temp.getName(), temp.getDifficulty(), this.gameSettings);
      ClientLogic clientLogic = new ClientLogic(temp);
      LogicNetwork networkController = new NetworkController(clientLogic);
      clientLogic.setNetworkController(networkController);
      clientLogic.setInGameController(inGameController);
      this.clientLogic.add(clientLogic);
      networkController.joinLobby(this.myServer, temp);
    }
    this.logicGui.startInGameScreen();
  }

  @Override
  public ArrayList<Server> lobbyInformation() {
    ArrayList<Server> lobbyInfo = new ArrayList<Server>();
    lobbyInfo = (ArrayList<Server>) this.networkController.getServer();
    return lobbyInfo;
  }

  @Override
  public ArrayList<Card> sortHand(PlayState ps, ArrayList<Card> hand) {
    return Tools.sortHand(hand, ps);
  }
}
