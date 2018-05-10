package network.messages;

import java.io.Serializable;

/**
 * Message super class.
 * 
 * @author fkleinoe
 */
public abstract class Message implements Serializable {
  private static final long serialVersionUID = 1L;
  private MessageType type;

  /**
   * Constructor.
   * 
   * @author fkleinoe
   * @param type of message
   */
  public Message(MessageType type) {
    this.type = type;
  }

  /**
   * Get MessageType.
   * 
   * @author fkleinoe
   * @return MessageType to get
   */
  public MessageType getType() {
    return this.type;
  }

}
