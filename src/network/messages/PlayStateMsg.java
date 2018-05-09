package network.messages;

import logic.PlayState;

/**
 * Message containing the set PlayState.
 * 
 * @author fkleinoe
 */
public class PlayStateMsg extends Message {
  private static final long serialVersionUID = 1L;
  private PlayState playState;

  /**
   * Constructor.
   * 
   * @author fkleinoe
   * @param playState that should be send
   */
  public PlayStateMsg(PlayState playState) {
    super(MessageType.PLAY_STATE);
    this.playState = playState;
  }

  /**
   * Set PlayState.
   * 
   * @author fkleinoe
   * @param playState to set
   */
  public void setPlayState(PlayState playState) {
    this.playState = playState;
  }

  /**
   * Get PlayState.
   * 
   * @author fkleinoe
   * @return PlayState to get
   */
  public PlayState getPlayState() {
    return this.playState;
  }

}
