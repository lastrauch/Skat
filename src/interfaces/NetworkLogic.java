package interfaces;

import logic.Card;
import logic.GameSettings;
import logic.PlayState;
import logic.Player;
import network.server.Server;

//Network to Logic, implemented by Logic
public interface NetworkLogic {
	//receive Answer to connection request
	static void receiveConnectionRequestAsnwer(boolean accepted){}
	
	//receive Lobby
	static void receiveLobby(Server server, Player host, Player[] player, GameSettings gs){}
	
	//receive GameSettings
	static void receiveGameSettings(GameSettings gs){}
	
	//receive Chat-Message
	static void receiveChatMessage(Player player, String msg){}
	
	//receive Start Game
	static void receiveStartGame(){}
	
	//receive Cards
	static void receiveCards(Card[] cards){}
	
	//receive Bet
	static void receiveBet(Player player, int bet){}
	
	//receive PlaySettings
	static void receivePlayState(PlayState ps){}
	
	//receive CardPlayed
	static void receiveCardPlayed(Player player, Card card){}
	
	//receive YourTurn
	static void receiveYourTurn(){}
	
	//receive Player disconnecet
	static void receivePlayerDisconnected(Player player){}
}
