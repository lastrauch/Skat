package network.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import logic.GameSettings;
import logic.PlayState;
import logic.Player;

/**
 * Server that hosts a skat game.
 * 
 * @author fkleinoe
 */
public class Server extends Thread {

  // run() : void
  // Run server.

  // stopServer() : void
  // Stop the server.

  private String serverName;
  private String ip;
  private ServerSocket serverSocket;
  private int port;
  private List<ClientConnection> clientConnections;
  private boolean serverRunning = false;
  private static int playerId = 1; // Iterator the set unique player ID
  private GameSettings gameSettings;
  private String comment;
  private PlayState playState;
  private List<Player> player;
  private int numPlayer; // Dummy value of the number of players on the server
  private int maxPlayer; // Dummy value of the maximum number of player allowed on the server

  //////////////////////////////////////////////////////////////////////////////////////////////////
  // Constructor
  //////////////////////////////////////////////////////////////////////////////////////////////////
  /**
   * Constructor.
   * 
   * @author fkleinoe
   * @param serverName of the server
   * @param port of the server
   * @param gameSettings of the game
   * @param comment of the server
   */
  public Server(String serverName, int port, GameSettings gameSettings, String comment) {
    this.serverName = serverName;
    this.port = port;
    this.gameSettings = gameSettings;
    this.comment = comment;
    this.player = new ArrayList<Player>();
    try {
      InetAddress inetAddress = InetAddress.getLocalHost();
      this.ip = inetAddress.getHostAddress();
    } catch (UnknownHostException e1) {
      e1.printStackTrace();
    }
    this.clientConnections = new ArrayList<ClientConnection>();

    try {
      this.serverSocket = new ServerSocket(port);
    } catch (IOException e) {
      e.printStackTrace();
    }
    Thread serverFinderThread = new Thread(ServerFinderThread.getInstance(this));
    serverFinderThread.start();
  }

  /**
   * Dummy constructor to process in the gui.
   * 
   * @author fkleinoe
   * @param serverName of the server
   * @param port of the server
   * @param numPlayer number of players on the server
   * @param maxPlayer maximum number of allowed players
   * @param comment of the server
   */
  public Server(String serverName, int port, int numPlayer, int maxPlayer, String comment) {
    this.serverName = serverName;
    this.port = port;
    this.numPlayer = numPlayer;
    this.maxPlayer = maxPlayer;
    this.comment = comment;
  }

  //////////////////////////////////////////////////////////////////////////////////////////////////
  // Internal Methods
  //////////////////////////////////////////////////////////////////////////////////////////////////
  /**
   * Run server.
   * 
   * @author fkleinoe
   */
  public void run() {
    this.serverRunning = true;
    System.out.println("Server is running");

    while (this.serverRunning) {
      this.listen();
    }
  }

  /**
   * Listen to incoming messages.
   * 
   * @author fkleinoe
   */
  public void listen() {
    try {
      Socket newSocket = this.serverSocket.accept();
      ClientConnection newClientConnection = new ClientConnection(this, newSocket);
      this.clientConnections.add(newClientConnection);
      newClientConnection.start();
      System.out.println("New ClientConnection");
    } catch (SocketException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Stop the server.
   * 
   * @author fkleinoe
   */
  public void stopServer() {
    try {
      if (!this.serverSocket.isClosed()) {
        this.serverSocket.close();
      }
      this.serverRunning = false;
    } catch (SocketException e1) {
      e1.printStackTrace();
    } catch (IOException e2) {
      e2.printStackTrace();
    }
  }

  //////////////////////////////////////////////////////////////////////////////////////////////////
  // Setter-/Getter Methods
  //////////////////////////////////////////////////////////////////////////////////////////////////
  /**
   * Get port.
   * 
   * @author fkleinoe
   * @return int to get
   */
  public int getPort() {
    return this.port;
  }

  /**
   * Get serverName.
   * 
   * @author fkleinoe
   * @return String to get
   */
  public String getServerName() {
    return this.serverName;
  }

  /**
   * Get actual number of player on the server measured by the number of clientconnections.
   * 
   * @author fkleinoe
   * @return int to get
   */
  public int getNumberOfPlayers() {
    return this.clientConnections.size();
  }

  /**
   * Get clientConnections.
   * 
   * @author fkleinoe
   * @return List(ClientConnection) to get
   */
  public List<ClientConnection> getClientConnections() {
    return this.clientConnections;
  }

  /**
   * Set gameSettings.
   * 
   * @author fkleinoe
   * @param gameSettings to set
   */
  public void setGameSettings(GameSettings gameSettings) {
    this.gameSettings = gameSettings;
  }

  /**
   * Get gameSettings.
   * 
   * @author fkleinoe
   * @return GameSettings to get
   */
  public GameSettings getGameSettings() {
    return this.gameSettings;
  }

  /**
   * Set playState.
   * 
   * @author fkleinoe
   * @param playState to set
   */
  public void setPlayState(PlayState playState) {
    this.playState = playState;
  }

  /**
   * Get playState.
   * 
   * @author fkleinoe
   * @return PlayState to get
   */
  public PlayState getPlayState() {
    return this.playState;
  }

  /**
   * Get player.
   * 
   * @author fkleinoe
   * @return List(Payer) to get
   */
  public List<Player> getPlayer() {
    return this.player;
  }

  /**
   * Add player.
   * 
   * @author fkleinoe
   * @param player to add
   */
  public void addPlayer(Player player) {
    this.player.add(player);
  }

  /**
   * Remove player.
   * 
   * @author fkleinoe
   * @param player to remove
   */
  public void removePlayer(Player player) {
    this.player.remove(player);
  }

  /**
   * Remove clientConnection.
   * 
   * @author fkleinoe
   * @param connection to remove
   */
  public void removeClientConnection(ClientConnection connection) {
    System.out
        .println("CC wird entfernt!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    this.clientConnections.remove(connection);
    if (this.clientConnections.size() == 0) {
      this.stopServer();
    }
  }

  /**
   * Get comment.
   * 
   * @author fkleinoe
   * @return String to get
   */
  public String getComment() {
    return this.comment;
  }

  /**
   * Set ip.
   * 
   * @author fkleinoe
   * @param ip to set
   */
  public void setIp(String ip) {
    this.ip = ip;
  }

  /**
   * Get ip.
   * 
   * @author fkleinoe
   * @return String to get
   */
  public String getIp() {
    return this.ip;
  }

  /**
   * Create new playerId.
   * 
   * @author fkleinoe
   * @return int to get
   */
  public int getNewPlayerId() {
    return playerId++;
  }

  /**
   * Get number of player on server as dummy.
   * 
   * @author fkleinoe
   * @return int to get
   */
  public int getNumPlayer() {
    return this.numPlayer;
  }

  /**
   * Get maximum number of player on the server as dummy.
   * 
   * @author fkleinoe
   * @return int to get
   */
  public int getMaxPlayer() {
    return this.maxPlayer;
  }
}
