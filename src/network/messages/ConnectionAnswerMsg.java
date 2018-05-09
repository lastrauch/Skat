package network.messages;

/**
 * Answer whether client is allowed to join lobby.
 * 
 * @author fkleinoe
 */
public class ConnectionAnswerMsg extends Message {
  private static final long serialVersionUID = 1L;
  private boolean accepted;
  private int id;

  /**
   * Constructor.
   * 
   * @author fkleinoe
   * @param accepted boolean whether client is allowed to join
   * @param id if it is allowed to join he gets an unique id
   */
  public ConnectionAnswerMsg(boolean accepted, int id) {
    super(MessageType.CONNECTION_ANSWER);
    this.accepted = accepted;
    this.id = id;
  }

  /**
   * Get accepted.
   * 
   * @author fkleinoe
   * @return boolean whether the client is allowed to join lobby
   */
  public boolean getAccepted() {
    return this.accepted;
  }

  /**
   * Get id.
   * 
   * @author fkleinoe
   * @return int to get
   */
  public int getId() {
    return this.id;
  }

}
