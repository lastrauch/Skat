package ai;

import logic.Colour;
import logic.PlayMode;

public class SinglePlay{
	
	// This class represents the PlayMode that the AI wants to play, if it wins the auction.
	
	private PlayMode playMode;
	private Colour colour;
	private double certainty;  //The certainty to play the specified playmode is in [0;10]
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Constructor
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Construct an instance of SinglePlay.
	 * 
	 * @author fkleinoe
	 * @param playMode
	 * @param colour
	 * @param certainty
	 */
	public SinglePlay(PlayMode playMode, Colour colour, double certainty){
	  this.playMode = playMode;
	  this.colour = colour;
	  this.certainty = certainty;
	}
	
	/**
	 * Construct an instance of SinglePlay.
	 * 
	 * @author fkleinoe
	 * @param playMode
	 * @param colour
	 */
	public SinglePlay(PlayMode playMode, Colour colour){
		this.playMode = playMode;
		this.colour = colour;
	}
	
	/**
	 * Construct an instance of SinglePlay.
	 * 
	 * @author fkleinoe
	 * @param playMode
	 */
	public SinglePlay(PlayMode playMode){
		this.playMode = playMode;
		this.colour = null;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Setter- and Getter-Methods
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Get PlayMode.
	 * 
	 * @fkleinoe
	 * @return PlayMode
	 */
	public PlayMode getPlayMode(){
		return this.playMode;
	}
	
	/**
	 * Returns the trump-colour.
	 * 
	 * @author fkleinoe
	 * @return Colour
	 */
	public Colour getColour(){
		return this.colour;
	}
	
	/**
	 * Returns the certainty of the model
	 * 
	 * @author fkleinoe
	 * @return double
	 */
	public double getCertainty(){
	  return this.certainty;
	}
}
