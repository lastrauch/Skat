package network.messages;

public class StartGame_Msg extends Message{
	private static final long serialVersionUID = 1L;
	
	public StartGame_Msg(){
		super(MessageType.START_GAME);
	}
	
	public String toString(){
		return "Game started.";
	}

}
