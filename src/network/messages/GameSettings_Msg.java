package network.messages;

import logic.GameSettings;

public class GameSettings_Msg extends Message{
	private static final long serialVersionUID = 1L;
	private GameSettings gs;
	
	public GameSettings_Msg(GameSettings gs){
		super(MessageType.GAME_SETTINGS);
		this.gs = gs;
	}
	
	public void setGameSettings(GameSettings gs){
		this.gs = gs;
	}
	
	public GameSettings getGameSettings(){
		return this.gs;
	}
	
	// TODO logic.GameSettings needs a toString Method
	public String toString(){
		return "The Game-Settings are: " + gs.toString();
	}
}
