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
  private ClientLogic logic;
  private Player player;
  private boolean isHost = false;
  //private Player[] otherPlayers;
  //private GameSettings gs;
  //private PlayState ps;

  private Server server;
  private int port = Settings.PORT;
  private Client client;
  private boolean isInLobby = false;
  private ServerFinder finder;

  public NetworkController(ClientLogic logic) {
    this.logic = logic;
  }

  public Server hostGame(Player player, GameSettings gs, String comment) {
    this.player = player;
    //this.gs = gs;
    this.isHost = true;
    this.server = new Server("Server von " + player.getName(), this.port, gs, comment);
    this.server.start();
    while (!this.isInLobby) {
      this.isInLobby = joinLobby(this.server, player);
    }
    return this.server;
  }

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

  public List<Server> getServer() {
    if (this.finder != null) {
      return this.finder.refresh();
    } else {
      this.finder = new ServerFinder(this.port);
      return this.finder.getServers();
    }
  }


  public void sendChatMessage(String message) {
    ChatMessage_Msg msg = new ChatMessage_Msg(this.player, message);
    this.client.sendMessage(msg);
  }

  public void sendGameSettings(GameSettings gs) {
    if(isHost){
	  GameSettings_Msg msg = new GameSettings_Msg(gs);
	  this.client.sendMessage(msg);
    }
  }

  public void startGame() {
    StartGame_Msg msg = new StartGame_Msg();
    this.client.sendMessage(msg);
  }

  public void dealCards(Player player, List<Card> cards, PlayState ps) {
    DealtCards_Msg msg = new DealtCards_Msg(player, cards, ps);
    this.client.sendMessage(msg);
  }

  public void yourTurn(Player player) {
    YourTurn_Msg msg = new YourTurn_Msg(player);
    this.client.sendMessage(msg);
  }

  public void bet(int bet, Player player) {
    Bet_Msg msg = new Bet_Msg(player, bet);
    this.client.sendMessage(msg);
  }

  public void sendPlayState(PlayState ps) {
    PlayState_Msg msg = new PlayState_Msg(ps);
    this.client.sendMessage(msg);
  }

  public void sendCardPlayed(Card card, Player player) {
    CardPlayed_Msg msg = new CardPlayed_Msg(player, card);
    this.client.sendMessage(msg);
  }

  public void exitGame() {
    this.client.disconnect();
  }

  public void sendKontra() {
    Kontra_Msg msg = new Kontra_Msg();
    this.client.sendMessage(msg);
  }

  public void sendRekontra() {
    Rekontra_Msg msg = new Rekontra_Msg();
    this.client.sendMessage(msg);
  }
}
