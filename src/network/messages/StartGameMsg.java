package network.messages;

/**
 * Message send that indicates that the game started.
 * 
 * @author fkleinoe
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

}
