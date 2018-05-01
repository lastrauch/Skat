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
  //
  // public Card[] initializeCards() {
  // Card[]cards = new Card[32];
  //
  // int counter = 0;
  // for (int i = 1; i <= 4; i++) {
  // Colour col = null;
  // switch (i) {
  // case 1:
  // col = Colour.DIAMONDS;
  // break;
  // case 2:
  // col = Colour.HEARTS;
  // break;
  // case 3:
  // col = Colour.SPADES;
  // break;
  // case 4:
  // col = Colour.CLUBS;
  // break;
  // }
  // for (int j = 1; j <= 8; j++) {
  // Number nr = null;
  // switch (j) {
  // case 1:
  // nr = Number.SEVEN;
  // break;
  // case 2:
  // nr = Number.EIGHT;
  // break;
  // case 3:
  // nr = Number.NINE;
  // break;
  // case 4:
  // nr = Number.JACK;
  // break;
  // case 5:
  // nr = Number.QUEEN;
  // break;
  // case 6:
  // nr = Number.KING;
  // break;
  // case 7:
  // nr = Number.TEN;
  // break;
  // case 8:
  // nr = Number.ASS;
  // break;
  // }
  // // cards are generated in the order of their value
  //
  // Card c = new Card(col, nr);
  // cards[counter] = c;
  // counter++;
  //
  // // System.out.println(counter + " " + col.toString() + " " + nr.toString());
  // }
  // }
  // }

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
    clientLogic.setLogicGui(this.logicGui);

    LogicNetwork networkController = new NetworkController(clientLogic);
    clientLogic.setNetworkController(networkController);

    // InGameInterface inGameController = new InGameController();
    // clientLogic.setInGameController(inGameController);

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
    InGameInterface inGameController = new AIController(name, difficulty, this.gameSettings);
    ClientLogic clientLogic = new ClientLogic(p);
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
    // System.out.println("start hostGame method");
    this.myServer = this.networkController.hostGame(this.clientLogic.get(0).player, this.gameSettings, comment);
  }



  @Override
  public void startGame(GameSettings gs) {
    System.out.println("start game method");
    // only used in the singlePlayer mod?!
    this.gameSettings = gs;
    this.group = this.clientLogic.get(0).getLobby();

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
    return this.group.get(0);
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

}
