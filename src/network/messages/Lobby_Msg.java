package network.messages;

import logic.GameSettings;
import logic.Player;
import network.server.Server;

public class Lobby_Msg extends Message{
	private static final long serialVersionUID = 1L;
	private Player host;
	private Player[] player;
	private GameSettings gs;
	
	public Lobby_Msg(Player host, Player[] player, GameSettings gs){
		super(MessageType.LOBBY);
		this.host = host;
		this.player = player;
		this.gs = gs;
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
