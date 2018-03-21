package network.messages;

public class ClientDisconnect extends Message{
	private static final long serialVersionUID = 1L;
	
	public ClientDisconnect() {
		super(MessageType.CLIENT_DISCONNECT);
	}
}
