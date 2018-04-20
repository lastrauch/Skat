package network.messages;

public class ConnectionRequest_Msg extends Message{
	private static final long serialVersionUID = 1L;
	
	public ConnectionRequest_Msg(){
		super(MessageType.CONNECTION_REQUEST);
	}
	
	public String toString(){
		return "Connection requested.";
	}
}
