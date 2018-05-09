package network.messages;

/*
 * Message send that indicates that the game started.
 */
public class StartGameMsg extends Message {
  private static final long serialVersionUID = 1L;

  /**
   * Constructor.
   * 
   * @author fkleinoe
   */
  public StartGameMsg() {
    super(MessageType.START_GAME);
  }

  /**
   * Class to String method.
   * 
   * @author fkleinoe
   */
  public String toString() {
    return "Game started.";
  }

}
