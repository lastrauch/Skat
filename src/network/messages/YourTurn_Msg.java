package network.messages;

import logic.Player;

public class YourTurn_Msg extends Message {
  private static final long serialVersionUID = 1L;
  private Player player;

  public YourTurn_Msg(Player player) {
    super(MessageType.YOUR_TURN);
    this.player = player;
  }

  public Player getPlayer() {
    return this.player;
  }

  public String toString() {
    return "Your turn, " + this.player.getName();
  }
}
