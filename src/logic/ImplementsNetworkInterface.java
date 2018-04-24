package logic;

import java.util.List;

import interfaces.NetworkLogic;

public class ImplementsNetworkInterface extends GameController implements NetworkLogic{

	@Override
	public void receiveConnectionRequestAsnwer(boolean accepted) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receiveLobby(List<Player> player, GameSettings gs) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receiveGameSettings(GameSettings gs) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receiveChatMessage(Player player, String msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receiveStartGame() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receiveCards(Card[] cards) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receiveBet(Player player, int bet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receivePlayState(PlayState ps) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receiveCardPlayed(Player player, Card card) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receiveYourTurn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receivePlayerDisconnected(Player player) {
		// TODO Auto-generated method stub
		
	}


}
