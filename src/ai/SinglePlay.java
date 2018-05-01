package ai;

import logic.Colour;
import logic.PlayMode;

public class SinglePlay{
	private PlayMode playMode;
	private Colour colour;
	
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
}
