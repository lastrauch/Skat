package network.messages;

import logic.Player;

public class ConnectionRequest_Msg extends Message{
	private static final long serialVersionUID = 1L;
	private Player player;
	
	public ConnectionRequest_Msg(Player player){
		super(MessageType.CONNECTION_REQUEST);
		this.player = player;
	}
	
	public Player getPlayer(){
		return this.player;
	}
}
