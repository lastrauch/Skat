package network.messages;

import java.util.List;
import logic.GameSettings;
import logic.Player;

public class Lobby_Msg extends Message {
  private static final long serialVersionUID = 1L;
  private Player[] player;
  private GameSettings gs;

  public Lobby_Msg(List<Player> player, GameSettings gs) {
    super(MessageType.LOBBY);
    this.player = player.toArray(new Player[player.size()]);
    this.gs = gs;
  }

  public Player[] getPlayer() {
    return this.player;
  }

  public GameSettings getGameSettings() {
    return this.gs;
  }
}
