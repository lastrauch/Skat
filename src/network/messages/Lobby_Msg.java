package network.messages;

import java.util.List;

import logic.GameSettings;
import logic.Player;
import network.server.Server;

public class Lobby_Msg extends Message{
	private static final long serialVersionUID = 1L;
	private List<Player> player;
	private GameSettings gs;
	
	public Lobby_Msg(List<Player> player, GameSettings gs){
		super(MessageType.LOBBY);
		this.player = player;
		this.gs = gs;
	}
	
	public List<Player> getPlayer(){
		return this.player;
	}
	
	public GameSettings getGameSettings(){
		return this.gs;
	}
}
