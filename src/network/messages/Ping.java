package network.messages;

public class Ping extends Message{
    private static final long serialVersionUID = 1L;

    public Ping() {
        super(MessageType.PING);
    }
}
