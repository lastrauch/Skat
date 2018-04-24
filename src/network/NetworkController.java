package network;

import java.util.List;

import interfaces.LogicNetwork;
import interfaces.NetworkLogic;
import logic.Card;
import logic.Game;
import logic.GameSettings;
import logic.ImplementsNetworkInterface;
import logic.PlayState;
import logic.Player;
import network.client.Client;
import network.messages.Bet_Msg;
import network.messages.CardPlayed_Msg;
import network.messages.ChatMessage_Msg;
import network.messages.ClientDisconnect_Msg;
import network.messages.DealtCards_Msg;
import network.messages.GameSettings_Msg;
import network.messages.Lobby_Msg;
import network.messages.PlayState_Msg;
import network.messages.StartGame_Msg;
import network.messages.YourTurn_Msg;
import network.server.Server;
import network.server.ServerFinder;

public class NetworkController implements LogicNetwork{
	private ImplementsNetworkInterface logic;
	private Player player;
	private boolean isHost = false;
	private Player[] otherPlayers;
	private GameSettings gs;
	private PlayState ps;
	
	private Server server;
	private int port = Settings.PORT;
	private Client client;
	private boolean isInLobby = false;
	private ServerFinder finder;
	
	public NetworkController(ImplementsNetworkInterface logic){
		this.logic = logic;
	}

	public void hostGame(Player player, GameSettings gs) {
		this.player = player;
		this.gs = gs;
		this.isHost = true;
	    this.server = new Server("Server von " + player.getName(), this.port, gs);
	    this.server.run();
	    while(!this.isInLobby){
	    	this.isInLobby = joinLobby(this.server, player);
	    }
	}

	public boolean joinLobby(Server server, Player player) {
		this.client = new Client(server, player, server.getPort(), this.logic);
		if(this.client.requestConnection()){
			return true;
		}else{
			this.client.disconnect();
			this.client = null;
		}
		return false;
	}

	public List<Server> getServer(){
		if(this.finder != null){
			return this.finder.refresh();
		}else{
			this.finder = new ServerFinder(this.port);
			return this.finder.getServers();
		}
	}


	public void sendChatMessage(String message) {
		ChatMessage_Msg msg = new ChatMessage_Msg(this.player, message);
		this.client.sendMessage(msg);
	}
	
	//TODO what if client isn't host?
	public void sendGameSettings(GameSettings gs) {
		GameSettings_Msg msg = new GameSettings_Msg(gs);
		this.client.sendMessage(msg);
	}

	public void startGame() {
		StartGame_Msg msg = new StartGame_Msg();
		this.client.sendMessage(msg);
	}

  	public void dealCards(Player player, List<Card> cards) {
  		DealtCards_Msg msg = new DealtCards_Msg(player, cards);
  		this.client.sendMessage(msg);
  	}

  	public void yourTurn(Player player) {
  		YourTurn_Msg msg = new YourTurn_Msg(player);
  		this.client.sendMessage(msg);
  	}

  	public void bet(int bet) {
  		Bet_Msg msg = new Bet_Msg(this.player, bet);
  		this.client.sendMessage(msg);
  	}

  	public void sendPlayState(PlayState ps) {
  		PlayState_Msg msg = new PlayState_Msg(ps);
  		this.client.sendMessage(msg);
  	}

  	public void sendCardPlayed(Card card) {
  		CardPlayed_Msg msg = new CardPlayed_Msg(this.player, card);
  		this.client.sendMessage(msg);
  	}

  	public void exitGame() {
  		this.client.disconnect();
  	}
}
