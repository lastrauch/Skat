package network.messages;

/**
 * Message that indicates rekontra was announced by the sender.
 * 
 * @author fkleinoe
 */
public class RekontraMsg extends Message {
  private static final long serialVersionUID = 1L;

  /**
   * Constructor.
   * 
   * @author fkleinoe
   */
  public RekontraMsg() {
    super(MessageType.REKONTRA);
  }

}
