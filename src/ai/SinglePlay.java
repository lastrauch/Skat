package ai;

import logic.Colour;
import logic.PlayMode;

public class SinglePlay{
	private PlayMode playMode;
	private Colour colour;
	private double certainty;
	
	public SinglePlay(PlayMode playMode, Colour colour, double certainty){
	  this.playMode = playMode;
	  this.colour = colour;
	  this.certainty = certainty;
	}
	
	public SinglePlay(PlayMode playMode, Colour colour){
		this.playMode = playMode;
		this.colour = colour;
	}
	
	public SinglePlay(PlayMode playMode){
		this.playMode = playMode;
		this.colour = null;
	}
	
	public PlayMode getPlayMode(){
		return this.playMode;
	}
	
	public Colour getColour(){
		return this.colour;
	}
	
	public double getCertainty(){
	  return this.certainty;
	}
}
