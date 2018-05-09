package network.messages;

import logic.Player;

/**
 * Message written in chat.
 * 
 * @author fkleinoe
 */
public class ChatMessageMsg extends Message {
  private static final long serialVersionUID = 1L;
  private Player player;
  private String msg;

  /**
   * Constructor.
   * 
   * @author fkleinoe
   * @param player that wrote the message
   * @param msg containing the message
   */
  public ChatMessageMsg(Player player, String msg) {
    super(MessageType.CHAT_MESSAGE);
    this.player = player;
    this.msg = msg;
  }

  /**
   * Get player.
   * 
   * @author fkleinoe
   * @return Player to get
   */
  public Player getPlayer() {
    return this.player;
  }

  /**
   * Get message.
   * 
   * @author fkleinoe
   * @return String to get
   */
  public String getMsg() {
    return this.msg;
  }

}
