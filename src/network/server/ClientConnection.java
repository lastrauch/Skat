package network.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import logic.Player;
import network.messages.ClientDisconnectMsg;
import network.messages.ConnectionAnswerMsg;
import network.messages.ConnectionRequestMsg;
import network.messages.DealtCardsMsg;
import network.messages.GameSettingsMsg;
import network.messages.LobbyMsg;
import network.messages.Message;
import network.messages.PlayStateMsg;

/**
 * Connection to clients on the server. The clients do not communicate with the server directly, but
 * communicate with their own client connection.
 * 
 * @author fkleinoe
 */
public class ClientConnection extends Thread {

  // run() : void
  // Listen to incoming messages.

  // sendMessage(Message) : void
  // Send message to client.

  // disconnect() : void
  // Disconnect this client connection.

  // receiveMessage(Message) : void
  // handle messages that came in.

  // messageHandler(Message) : void
  // Send received message to all clients.

  // connectionRequestHandler(ConnectionRequestMsg) : void
  // Handle the special message of a connection request.

  // clientDisconnectHandler(ClientDisconnectMsg) : void
  // Handle the special message of a disconnection.

  // dealtCardsHandler(DealtCardsMsg) : void
  // Handle the special message of receiving dealt cards.

  private Server server;
  private Socket socket;
  private ObjectInputStream input; // Input from Client
  private ObjectOutputStream output; // Output from Client
  private boolean running;
  private Player player; // Player the connection belongs to

  //////////////////////////////////////////////////////////////////////////////////////////////////
  // Constructor
  //////////////////////////////////////////////////////////////////////////////////////////////////
  /**
   * Constructor.
   * 
   * @author fkleinoe
   * @param server that the connection belongs to
   * @param socket that the communication is running on
   */
  public ClientConnection(Server server, Socket socket) {
    this.setName("ClientConnection");
    this.server = server;
    try {
      this.socket = socket;
      this.output = new ObjectOutputStream(socket.getOutputStream());
      this.input = new ObjectInputStream(socket.getInputStream());
    } catch (IOException e) {
      System.out.println("Connection error");
      e.printStackTrace();
    }
  }

  /**
   * Constructor.
   * 
   * @author fkleinoe
   * @param server that the connection belongs to
   * @param socket that the communication is running on
   * @param player that the connection is dedicated to
   */
  public ClientConnection(Server server, Socket socket, Player player) {
    this.server = server;
    this.player = player;

    try {
      this.socket = socket;
      this.output = new ObjectOutputStream(socket.getOutputStream());
      this.input = new ObjectInputStream(socket.getInputStream());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //////////////////////////////////////////////////////////////////////////////////////////////////
  // Internal Methods
  //////////////////////////////////////////////////////////////////////////////////////////////////
  /**
   * Listen to incoming messages.
   * 
   * @author fkleinoe
   */
  public void run() {
    this.running = true;
    try {
      Message message;
      while (this.running && (message = (Message) input.readObject()) != null) {
        System.out.println("Message empfangen: " + message.getType().name());

        receiveMessage(message);
      }
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Send message to client.
   * 
   * @author fkleinoe
   * @param message to send
   */
  public void sendMessage(Message message) {
    try {
      this.output.writeObject(message);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Disconnect this client connection.
   * 
   * @author fkleinoe
   */
  private void disconnect() {
    if (this.player != null) {
      System.out.println(this.player.getName() + " CC disconnect.");
    }
    this.running = false;
    try {
      output.close();
      input.close();
      socket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Handle messages that came in.
   * 
   * @author fkleinoe
   * @param message that was received
   */
  private synchronized void receiveMessage(Message message) {
    switch (message.getType()) {
      case CARD_PLAYED:
        messageHandler(message);
        break;
      case BET:
        messageHandler(message);
        break;
      case CHAT_MESSAGE:
        messageHandler(message);
        break;
      case START_GAME:
        messageHandler(message);
        break;
      case KONTRA:
        messageHandler(message);
        break;
      case REKONTRA:
        messageHandler(message);
        break;
      case DEALT_CARDS:
        dealtCardsHandler((DealtCardsMsg) message);
        break;
      case CONNECTION_REQUEST:
        connectionRequestHandler((ConnectionRequestMsg) message);
        break;
      case GAME_SETTINGS:
        this.server.setGameSettings(((GameSettingsMsg) message).getGameSettings());
        messageHandler(message);
        break;
      case PLAY_STATE:
        this.server.setPlayState(((PlayStateMsg) message).getPlayState());
        messageHandler(message);
        break;
      case CLIENT_DISCONNECT:
        clientDisconnectHandler((ClientDisconnectMsg) message);
        break;
      default:
    }
  }

  /**
   * Send received message to all clients.
   * 
   * @author fkleinoe
   * @param message to send
   */
  private void messageHandler(Message message) {
    System.out.println("Send Message: " + message.getType() + " to "
        + this.server.getClientConnections().size() + " players.");
    for (int i = 0; i < this.server.getClientConnections().size(); i++) {
      this.server.getClientConnections().get(i).sendMessage(message);
    }
  }

  /**
   * Handle the special message of a connection request.
   * 
   * @author fkleinoe
   * @param message that was received
   */
  private synchronized void connectionRequestHandler(ConnectionRequestMsg message) {
    // �berpr�fe und sende Antwort
    if (this.server.getPlayer().size() < this.server.getGameSettings().getNrOfPlayers()) {
      // Falls ja, f�ge Spieler dem Server hinzu
      // Falls ja, sende GameSettings und andere Spieler an alle
      this.player = message.getPlayer();
      System.out.println(
          "Message send to " + message.getPlayer().getName() + ": CONNECTION_ANSWER(true)");
      this.player.setId(this.server.getNewPlayerId());
      this.sendMessage(new ConnectionAnswerMsg(true, this.player.getId()));
      this.player = message.getPlayer();
      this.server.addPlayer(message.getPlayer());
      System.out.println("Message send because of " + message.getPlayer().getName() + ": LOBBY");
      this.messageHandler(new LobbyMsg(this.server.getPlayer(), this.server.getGameSettings()));
    } else {
      System.out.println(
          "Message send to " + message.getPlayer().getName() + ": CONNECTION_ANSWER(false)");
      this.sendMessage(new ConnectionAnswerMsg(false, 0));
      this.disconnect();
    }

  }

  /**
   * Handle the special message of a disconnection.
   * 
   * @author fkleinoe
   * @param message that was received
   */
  private void clientDisconnectHandler(ClientDisconnectMsg message) {
    this.server.removePlayer(message.getPlayer());
    this.server.removeClientConnection(this);
    this.messageHandler(new ClientDisconnectMsg(message.getPlayer()));
    this.messageHandler(new LobbyMsg(this.server.getPlayer(), this.server.getGameSettings()));
    this.disconnect();
  }

  /**
   * Handle the special message of receiving dealt cards.
   * 
   * @author fkleinoe
   * @param message that was received
   */
  private void dealtCardsHandler(DealtCardsMsg message) {
    System.out.println("Server received cards for: " + message.getPlayer().getName());
    for (int i = 0; i < this.server.getClientConnections().size(); i++) {
      if (this.server.getClientConnections().get(i).getPlayer().getId() == message.getPlayer()
          .getId()) {
        this.server.getClientConnections().get(i).sendMessage(message);
        return;
      }
    }
  }

  //////////////////////////////////////////////////////////////////////////////////////////////////
  // Setter-/Getter Methods
  //////////////////////////////////////////////////////////////////////////////////////////////////
  /**
   * Get player.
   * 
   * @author fkleinoe
   * @return Player to get
   */
  public Player getPlayer() {
    return this.player;
  }
}
