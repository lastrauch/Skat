package network.messages;

public class Ping_Msg extends Message {
  private static final long serialVersionUID = 1L;

  public Ping_Msg() {
    super(MessageType.PING);
  }

  public String toString() {
    return "Ping";
  }
}
