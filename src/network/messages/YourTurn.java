package network.messages;

public class YourTurn extends Message{
    private static final long serialVersionUID = 1L;

    public YourTurn(){
      super(MessageType.YOUR_TURN);
    }
}
