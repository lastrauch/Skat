package network.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import logic.ClientLogic;
import logic.Player;
import network.messages.BetMsg;
import network.messages.CardPlayedMsg;
import network.messages.ChatMessageMsg;
import network.messages.ClientDisconnectMsg;
import network.messages.ConnectionAnswerMsg;
import network.messages.ConnectionRequestMsg;
import network.messages.DealtCardsMsg;
import network.messages.GameSettingsMsg;
import network.messages.LobbyMsg;
import network.messages.Message;
import network.messages.MessageType;
import network.messages.PlayStateMsg;
import network.server.Server;

public class Client extends Thread {

  /*
   * This class represents a client. Each player is assigned to a client. Incoming messages are
   * handled here. Available methods are:
   */

  // run() : void
  // Client Thread that reads incoming messages.

  // connect() : boolean
  // Tries to connect to server.

  // disconnect() : void
  // Disconnect from server.

  // sendMessage(Message) : void
  // Send message to server.

  // requestConnection() : boolean
  // Request whether client is allowed to join Lobby.

  // receiveMessage(Message) : void
  // Handler of received message.

  private Server server; // The server the client is connected to. May be a dummy.
  private int port; // The port the connection is running on
  private Socket socket; // The socket that is used to comunicate
  private Player owner; // Player the client is representing
  private ObjectOutputStream output; // Output to Server
  private ObjectInputStream input; // Input from Server
  private ClientLogic logic; // Interface to logic

  //////////////////////////////////////////////////////////////////////////////////////////////////
  // Constructor
  //////////////////////////////////////////////////////////////////////////////////////////////////
  /**
   * Constructor of the client. It also tries to connect to the passed server. If the connection
   * could not be established, the client will be closed.
   * 
   * @author fkleinoe
   * @param server to connect to
   * @param player who wants to connect
   * @param port to connect over
   * @param logic interface to logic
   */
  public Client(Server server, Player player, int port, ClientLogic logic) {
    this.setName("Client of " + player.getName());
    this.server = server;
    this.owner = player;
    this.port = port;
    this.logic = logic;

    boolean connectionEstablished = connect();
    if (!connectionEstablished) {
      System.out.println("Sorry: No connection to " + server.getServerName() + ": " + port);
    }
  }

  //////////////////////////////////////////////////////////////////////////////////////////////////
  // Internal Methods
  //////////////////////////////////////////////////////////////////////////////////////////////////
  /**
   * Client Thread that reads incoming messages.
   * 
   * @author fkleinoe
   */
  public void run() {
    try {
      Message message;
      boolean connected = true;
      while (connected && (message = (Message) input.readObject()) != null) {
        if (message.getType() == MessageType.LOBBY) {
          LobbyMsg msg = (LobbyMsg) message;
          System.out.println("Message recieved run " + this.owner.getName() + ": "
              + message.getType() + " (Group size: " + msg.getPlayer().length + ")");
        } else {
          System.out
              .println("Message recieved run " + this.owner.getName() + ": " + message.getType());
        }
        receiveMessage(message);
      }
    } catch (ClassCastException e) {
      System.out.println("Client run(); ClassCastException");
    } catch (ClassNotFoundException e) {
      System.out.println("Client run(): ClassNotFoundException");
      e.printStackTrace();
    } catch (IOException e) {
      System.out.println("Client run(): IOException");
      e.printStackTrace();
    }
  }

  /**
   * Tries to connect to server.
   * 
   * @author fkleinoe
   * @return boolean whether connection was established
   */
  private boolean connect() {
    try {
      this.socket = new Socket(server.getIP(), port);
      this.output = new ObjectOutputStream(socket.getOutputStream());
      this.input = new ObjectInputStream(socket.getInputStream());
      System.out.println("Socket Connection established");
    } catch (UnknownHostException e) {
      e.printStackTrace();
      return false;
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  /**
   * Disconnect from server.
   * 
   * @author fkleinoe
   */
  public void disconnect() {
    System.out.println(this.owner.getName() + " client disconnect.");
    try {
      this.output.writeObject(new ClientDisconnectMsg(this.owner));
      this.output.close();
      this.input.close();
      this.socket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  /**
   * Send message to server.
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
   * Request whether client is allowed to join Lobby.
   * 
   * @author fkleinoe
   * @return boolean whether client is allowed to join
   */
  public boolean requestConnection() {
    try {
      output.writeObject(new ConnectionRequestMsg(this.owner));
      Message serverOutput;
      boolean receivedAnswer = false;
      while (!receivedAnswer && (serverOutput = (Message) input.readObject()) != null) {
        System.out.println("Message recieved requestConnection " + this.owner.getName() + ": "
            + serverOutput.getType());
        if (serverOutput.getType() == MessageType.CONNECTION_ANSWER) {
          receivedAnswer = true;
          ConnectionAnswerMsg m = (ConnectionAnswerMsg) serverOutput;
          this.owner.setId(m.getID());
          this.start();
          return m.getAccepted();
        } else {
          System.out.println("Message from server is invalid!");
        }
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      System.out.println("Input war keine Message (Client)");
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return false;
  }

  /**
   * Handler of received messages.
   * 
   * @author fkleinoe
   * @param message that was received
   */
  private synchronized void receiveMessage(Message message) {
    switch (message.getType()) {
      case CARD_PLAYED:
        CardPlayedMsg msg2 = (CardPlayedMsg) message;
        logic.receiveCardPlayed(msg2.getPlayer(), msg2.getCard());
        break;
      case BET:
        BetMsg msg3 = (BetMsg) message;
        logic.receiveBet(msg3.getPlayer(), msg3.getBet());
        break;
      case CHAT_MESSAGE:
        ChatMessageMsg msg4 = (ChatMessageMsg) message;
        logic.receiveChatMessage(msg4.getPlayer(), msg4.getMsg());
        break;
      case GAME_SETTINGS:
        GameSettingsMsg msg5 = (GameSettingsMsg) message;
        logic.receiveGameSettings(msg5.getGameSettings());
        break;
      case PLAY_STATE:
        PlayStateMsg msg6 = (PlayStateMsg) message;
        logic.receivePlayState(msg6.getPlayState());
        break;
      case DEALT_CARDS:
        DealtCardsMsg msg7 = (DealtCardsMsg) message;
        System.out.println(this.owner.getName() + " received his/her cards.");
        logic.receiveCards(new ArrayList<>(Arrays.asList(msg7.getCards())), msg7.getPlayState());
        break;
      case LOBBY:
        LobbyMsg msg9 = (LobbyMsg) message;
        logic.receiveLobby(new ArrayList<>(Arrays.asList(msg9.getPlayer())),
            msg9.getGameSettings());
        break;
      case START_GAME:
        logic.receiveStartGame();
        break;
      case CLIENT_DISCONNECT:
        ClientDisconnectMsg msg11 = (ClientDisconnectMsg) message;
        logic.receivePlayerDisconnected(msg11.getPlayer());
        break;
      case KONTRA:
        logic.receiveKontra();
        break;
      case REKONTRA:
        logic.receiveRekontra();
        break;
      default:
        break;
    }
  }
}
