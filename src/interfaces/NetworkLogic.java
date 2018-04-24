package interfaces;

import java.util.List;

import logic.Card;
import logic.GameSettings;
import logic.PlayState;
import logic.Player;

//Network to Logic, implemented by Logic
public interface NetworkLogic {
	//receive Answer to connection request
	void receiveConnectionRequestAsnwer(boolean accepted);
	
	//receive Lobby
	void receiveLobby(List<Player> player, GameSettings gs);
	
	//receive GameSettings
	void receiveGameSettings(GameSettings gs);
	
	//receive Chat-Message
	void receiveChatMessage(Player player, String msg);
	
	//receive Start Game
	void receiveStartGame();
	
	//receive Cards
	void receiveCards(List<Card> cards);
	
	//receive Bet
	void receiveBet(Player player, int bet);
	
	//receive PlaySettings
	void receivePlayState(PlayState ps);
	
	//receive CardPlayed
	void receiveCardPlayed(Player player, Card card);
	
	//receive YourTurn
	void receiveYourTurn();
	
	//receive Player disconnecet
	void receivePlayerDisconnected(Player player);
}
