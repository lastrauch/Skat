package network.messages;

public class ConnectionAnswerMsg extends Message {
  private static final long serialVersionUID = 1L;
  private boolean accepted;
  private int id;

  public ConnectionAnswerMsg(boolean accepted, int id) {
    super(MessageType.CONNECTION_ANSWER);
    this.accepted = accepted;
    this.id = id;
  }

  public void setAccepted(boolean accepted) {
    this.accepted = accepted;
  }

  public boolean getAccepted() {
    return this.accepted;
  }
  
  public int getID() {
	  return this.id;
  }

  public String toString() {
    return "Connection accepted: " + this.accepted;
  }
}
