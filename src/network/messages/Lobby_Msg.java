package network.messages;

import logic.GameSettings;
import logic.Player;
import network.server.Server;

public class Lobby_Msg extends Message{
	private static final long serialVersionUID = 1L;
	private Server server;
	private Player host;
	private Player[] player;
	private GameSettings gs;
	
	public Lobby_Msg(Server server, Player host, Player[] player, GameSettings gs){
		super(MessageType.LOBBY);
		this.server = server;
		this.host = host;
		this.player = player;
		this.gs = gs;
	}
	
	public Server getServer(){
		return this.server;
	}
	
	public Player getHost(){
		return this.host;
	}
	
	public Player[] getPlayer(){
		return this.player;
	}
	
	public GameSettings getGameSettings(){
		return this.gs;
	}
}
