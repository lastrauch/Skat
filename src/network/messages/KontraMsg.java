package network.messages;

/**
 * Message to indicate kontra was announced by the sender.
 * 
 * @author fkleinoe
 */
public class KontraMsg extends Message {
  private static final long serialVersionUID = 1L;

  /**
   * Constructor.
   * 
   * @author fkleinoe
   */
  public KontraMsg() {
    super(MessageType.KONTRA);
  }

}
