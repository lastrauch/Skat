package network;

import logic.Card;
import logic.Game;
import logic.GameSettings;
import logic.PlayState;
import logic.Player;
import network.client.Client;
import network.server.Server;

public class NetworkController implements interfaces.LogicNetwork{
	//TODO Klasse Game muss durch Controller Klasse der Logik ersetzt werden
	private Game logic;
	private Player player;
	private boolean isHost = false;
	private Player[] otherPlayers;
	private GameSettings gs;
	private PlayState ps;
	
	private Server server;
	private int port;
	private Client client;
	private boolean isInLobby = false;
	
	public NetworkController(Game logic){
		this.logic = logic;
	}

	public void hostGame(Player player, GameSettings gs) {
		this.player = player;
		this.gs = gs;
		this.isHost = true;
	    //Create Server
	    this.server = new Server("Server von " + player.getName(), this.port, gs);
	    this.server.run();
	    //Joine der eigenen Lobby
	    while(!this.isInLobby){
	    	this.isInLobby = joinLobby(this.server, player);
	    }
	}

	public boolean joinLobby(Server server, Player player) {
	    //Request Connection
	    //Create Client
	    //Forder GameSettings und andere Spieler an
	    //Sende an alle, dass neuer Client dabei
	    //Fordere letzten Chat an?
		
		//Sendet zurück, ob es geklappt hat;
		return false;
	}

	public Server[] getServer(){  
		return null;
	}


	public void sendChatMessage(String message) {
	//Sende Nachricht an alle
	}

	public void sendGameSettings(GameSettings gs) {
	}

	public void startGame() {
	}

  	public void dealCards(Card[] cards) {
  	}

  	public void yourTurn() {
  	}

  	public void bet(int bet) {
  	}

  	public void sendPlayState(PlayState ps) {
  	}

  	public void sendCardPlayed(Card card) {
  	}

  	public void exitGame() {
  	}

}
