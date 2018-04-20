package network.messages;

import logic.PlayState;

public class PlaySettings_Msg extends Message{
	private static final long serialVersionUID = 1L;
	private PlayState ps;
	
	public PlaySettings_Msg(PlayState ps){
		super(MessageType.PLAY_SETTINGS);
		this.ps = ps;
	}
	
	public void setPlayState(PlayState ps){
		this.ps = ps;
	}
	
	public PlayState getPlayState(){
		return this.ps;
	}
	
	// TODO logic.PlayState needs a toString Method
	public String toString(){
		return "The Play-Settings are: " + ps.toString();
	}
}
