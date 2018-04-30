package network.messages;

public class ConnectionAnswer_Msg extends Message {
  private static final long serialVersionUID = 1L;
  private boolean accepted;

  public ConnectionAnswer_Msg(boolean accepted) {
    super(MessageType.CONNECTION_ANSWER);
    this.accepted = accepted;
  }

  public void setAccepted(boolean accepted) {
    this.accepted = accepted;
  }

  public boolean getAccepted() {
    return this.accepted;
  }

  public String toString() {
    return "Connection accepted: " + this.accepted;
  }
}
