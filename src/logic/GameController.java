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
import network.server.Server;

public class GameController implements GuiLogic {

  private List<Player> group = new ArrayList<Player>();
  private LogicGui logicGui; // interface from logic to gui
  private LogicNetwork networkController; // interface from logic to network
  private GuiLogic guiLogic; // interface from gui to logic
  private List<ClientLogic> clientLogic;
  private Game game;
  private GameSettings gameSettings;
  private List<Server> server;

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
    clientLogic.setNetworkController(networkController);
    this.clientLogic.add(clientLogic);
    this.networkController = networkController;

    // asks for servers and shows them on the ui
    this.server = new ArrayList<Server>();
    this.server = this.networkController.getServer();

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
        this.clientLogic.remove(i);
      }
    }
  }

  @Override
  /**
   * @author awesch
   */
  public void setBot(String botname, BotDifficulty difficulty) {
    Player p = new Player(botname);
    this.group.add(p);
    InGameInterface inGameController = new AIController(botname, difficulty, this.gameSettings);
    ClientLogic clientLogic = new ClientLogic(p, inGameController);
    LogicNetwork networkController = new NetworkController(clientLogic);
    clientLogic.setNetworkController(networkController);
    this.clientLogic.add(clientLogic);
  }

  // WO BEKOMMEN WIR DENN UBERHAUPT EINEN HOST UEBERGEBEN??
  // DIE VERBINDUNG DES NETZWERKS BRAUCHEN WIR SCHON HIER ODER JOIN GAME UND CO GEHOEREN SCHON IN
  // DIE CLIENT LOGIK!!!!
  @Override
  /**
   * @author awesch
   */
  public void joinGame(String hostName) {
    // joinLobby erfordert einen server!! wir haben aber nur den namen
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
    this.networkController.hostGame(this.group.get(0), this.gameSettings, comment);
  }



  @Override
  public ArrayList<Card> sortHand() {
    // TODO Auto-generated method stub
    return null;
  }



  @Override
  public void startGame(GameSettings gs) {
    // TODO Auto-generated method stub
    
  }



  @Override
  public ArrayList<Server> lobbyInformation() {
    // TODO Auto-generated method stub
    return null;
  }
}
