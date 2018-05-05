package network;

import java.util.List;
import interfaces.LogicNetwork;
import logic.Card;
import logic.GameSettings;
import logic.ClientLogic;
import logic.PlayState;
import logic.Player;
import network.client.Client;
import network.messages.*;
import network.server.Server;
import network.server.ServerFinder;

public class NetworkController implements LogicNetwork {

  // This class is the connection between the logic interface and the network.
  // It implements the interface methods as well as some other methods to reach out to the client,
  // which communicates with it's ClientConnection on the server.
  // Available methods are:

  // hostGame(Player, GameSettings, String) : Server
  // With this method the player can host a game.

  // joinLobby(Server, Player) : boolean
  // This method lets the player join a game.

  // getServer() : List<Server>
  // Returns a list of skat servers.

  // sendChatMessage(String) : void
  // Sends a chat message.

  // sendGameSettings(GameSettings) : void
  // Sends the GameSettings.

  // startGame() : void
  // Sends a message, that the game will start.

  // dealCards(Player, List<Card>, PlayState) : void
  // Sends a message with dealt cards for the specified player.

  // bet(int, Player) : void
  // Sends a message, that the player has placed the specified bet.

  // sendPlayState(PlayState) : void
  // Sends the PlayState.
  
  // sendKontra()
  // Sends a message, that the player wants to declare kontra.

  // sendRekontra()
  // Sends a message, that the player wants to declare rekontra.
  
  // yourTurn(Player) : void
  // Sends a message, that it is players turn.

  // sendCardPlayed(Card, Player) : void
  // Sends a message that the player played the specified card.

  // exitGame()
  // Sends a message, that the player on this logic has left the game.

  private ClientLogic logic; // Logic class all interface methods towards the logic are called on
  private Player player; // Player of this client
  private boolean isHost = false; // Boolean whether client is also host of the game
  private Server server; // Server that the client is connected to
  private int port = Settings.PORT; // Port of the connection
  private Client client; // CLient of this NetworkController instance
  private boolean isInLobby = false; // Boolean whether the client is in a lobby or not
  private ServerFinder finder; // Instance of the class ServerFinder

  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // Constructor
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  /**
   * Constructor of the NetworkController. A ClientLogic instance is passed, that the Controller
   * should speak to
   * 
   * @author fkleinoe
   * @param logic
   */
  public NetworkController(ClientLogic logic) {
    this.logic = logic;
  }

  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // Interface Methods
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  @Override
  /**
   * This methods sets up a server for a game, builds a client for the player and connects to the
   * server.
   * 
   * @author fkleinoe
   * @param player
   * @param gameSettings
   * @param comment
   * @return Server
   */
  public Server hostGame(Player player, GameSettings gameSettings, String comment) {
    this.player = player;
    this.isHost = true;
    this.server = new Server("Server von " + player.getName(), this.port, gameSettings, comment);
    this.server.start();
    while (!this.isInLobby) {
      this.isInLobby = joinLobby(this.server, player);
    }
    return this.server;
  }

  @Override
  /**
   * This method creates a client and connects to a server, if the server is not full already.
   * Returns if the connections was accepted.
   * 
   * @author fkleinoe
   * @param server
   * @param player
   * @return boolean
   */
  public boolean joinLobby(Server server, Player player) {
    this.client = new Client(server, player, server.getPort(), this.logic);
    if (this.client.requestConnection()) {
      return true;
    } else {
      this.client.disconnect();
      this.client = null;
    }
    return false;
  }

  @Override
  /**
   * The methods returns a list of skat servers available in the network.
   * 
   * @author fkleinoe
   * @return List(Server)
   */
  public List<Server> getServer() {
    if (this.finder != null) {
      return this.finder.refresh();
    } else {
      this.finder = new ServerFinder(this.port);
      return this.finder.getServers();
    }
  }

  @Override
  /**
   * This method sends a chat message to the server.
   * 
   * @author fkleinoe
   * @param message
   */
  public void sendChatMessage(String message) {
    ChatMessage_Msg msg = new ChatMessage_Msg(this.player, message);
    this.client.sendMessage(msg);
  }

  @Override
  /**
   * This method sends the GameSettings to the server.
   * 
   * @author fkleinoe
   * @param gameSettings
   */
  public void sendGameSettings(GameSettings gameSettings) {
    if (isHost) {
      GameSettings_Msg msg = new GameSettings_Msg(gameSettings);
      this.client.sendMessage(msg);
    }
  }

  @Override
  /**
   * This method sends a message to the server, that the game will start now.
   * 
   * @author fkleinoe
   */
  public void startGame() {
    StartGame_Msg msg = new StartGame_Msg();
    this.client.sendMessage(msg);
  }

  @Override
  /**
   * This method sends the dealt cards to the server. The server then sends these to the specified
   * player.
   * 
   * @author fkleinoe
   * @param player
   * @param cards
   * @param playState
   */
  public void dealCards(Player player, List<Card> cards, PlayState playState) {
    DealtCards_Msg msg = new DealtCards_Msg(player, cards, playState);
    this.client.sendMessage(msg);
  }

  @Override
  /**
   * This method sends the message, that the passed player placed the passed bet.
   * 
   * @author fkleinoe
   * @param bet
   * @param player
   */
  public void bet(int bet, Player player) {
    Bet_Msg msg = new Bet_Msg(player, bet);
    this.client.sendMessage(msg);
  }

  @Override
  /**
   * This method sends the PlayState to the server.
   * 
   * @author fkleinoe
   * @param playState
   */
  public void sendPlayState(PlayState playState) {
    PlayState_Msg msg = new PlayState_Msg(playState);
    this.client.sendMessage(msg);
  }
  
  @Override
  /**
   * This method sends a message, that the player announced kontra.
   * 
   * @author fkleinoe
   */
  public void sendKontra() {
    Kontra_Msg msg = new Kontra_Msg();
    this.client.sendMessage(msg);
  }

  @Override
  /**
   * This method sends a message, that the player announced rekontra.
   * 
   * @author fkleinoe
   */
  public void sendRekontra() {
    Rekontra_Msg msg = new Rekontra_Msg();
    this.client.sendMessage(msg);
  }
  
  @Override
  /**
   * This method sends a message to the server, that it is the passed players turn.
   * 
   * @author fkleinoe
   * @param player
   */
  public void yourTurn(Player player) {
    YourTurn_Msg msg = new YourTurn_Msg(player);
    this.client.sendMessage(msg);
  }

  @Override
  /**
   * This method sends a card that was played by the passed player.
   * 
   * @author fkleinoe
   * @param card
   * @param player
   */
  public void sendCardPlayed(Card card, Player player) {
    CardPlayed_Msg msg = new CardPlayed_Msg(player, card);
    this.client.sendMessage(msg);
  }

  @Override
  /**
   * This method sends a message, that the player left the game.
   * 
   * @author fkleinoe
   */
  public void exitGame() {
    this.client.disconnect();
  }

}
