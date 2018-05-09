package ai;

import logic.Colour;
import logic.PlayMode;

public class SinglePlay {

  /*
   * This class represents the PlayMode that the AI wants to play, if it wins the auction.
   */

  private PlayMode playMode;
  private Colour colour;
  private double certainty; // The certainty to play the specified play mode is in [0;10]

  //////////////////////////////////////////////////////////////////////////////////////////////////
  // Constructor
  //////////////////////////////////////////////////////////////////////////////////////////////////
  /**
   * Construct an instance of SinglePlay.
   * 
   * @author fkleinoe
   * @param playMode that will be set
   * @param colour that will be trump
   * @param certainty of the AI to play this PlayMode
   */
  public SinglePlay(PlayMode playMode, Colour colour, double certainty) {
    this.playMode = playMode;
    this.colour = colour;
    this.certainty = certainty;
  }

  /**
   * Construct an instance of SinglePlay.
   * 
   * @author fkleinoe
   * @param playMode that will be set
   * @param colour that will be trump
   */
  public SinglePlay(PlayMode playMode, Colour colour) {
    this.playMode = playMode;
    this.colour = colour;
  }

  /**
   * Construct an instance of SinglePlay.
   * 
   * @author fkleinoe
   * @param playMode that will be set
   */
  public SinglePlay(PlayMode playMode) {
    this.playMode = playMode;
    this.colour = null;
  }

  //////////////////////////////////////////////////////////////////////////////////////////////////
  // Setter- and Getter-Methods
  //////////////////////////////////////////////////////////////////////////////////////////////////
  /**
   * Get PlayMode.
   * 
   * @fkleinoe
   * @return PlayMode to get
   */
  public PlayMode getPlayMode() {
    return this.playMode;
  }

  /**
   * Returns the trump-colour.
   * 
   * @author fkleinoe
   * @return Colour to get
   */
  public Colour getColour() {
    return this.colour;
  }

  /**
   * Returns the certainty of the model.
   * 
   * @author fkleinoe
   * @return double to get
   */
  public double getCertainty() {
    return this.certainty;
  }
}
