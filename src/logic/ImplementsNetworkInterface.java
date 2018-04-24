package logic;

import java.util.List;
import interfaces.NetworkLogic;

public class ImplementsNetworkInterface extends GameController implements NetworkLogic{

  /* (non-Javadoc)
   * @see interfaces.NetworkLogic#receiveConnectionRequestAsnwer(boolean)
   */
  @Override
  public void receiveConnectionRequestAsnwer(boolean accepted) {
    // TODO Auto-generated method stub
    
  }

  /* (non-Javadoc)
   * @see interfaces.NetworkLogic#receiveLobby(java.util.List, logic.GameSettings)
   */
  @Override
  public void receiveLobby(List<Player> player, GameSettings gs) {
    // TODO Auto-generated method stub
    
  }

  /* (non-Javadoc)
   * @see interfaces.NetworkLogic#receiveGameSettings(logic.GameSettings)
   */
  @Override
  public void receiveGameSettings(GameSettings gs) {
    // TODO Auto-generated method stub
    super.receiveGameSettings(gs);
  }

  /* (non-Javadoc)
   * @see interfaces.NetworkLogic#receiveChatMessage(logic.Player, java.lang.String)
   */
  @Override
  public void receiveChatMessage(Player player, String msg) {
    // TODO Auto-generated method stub
    
  }

  /* (non-Javadoc)
   * @see interfaces.NetworkLogic#receiveStartGame()
   */
  @Override
  public void receiveStartGame() {
    // TODO Auto-generated method stub
    
  }

  /* (non-Javadoc)
   * @see interfaces.NetworkLogic#receiveCards(logic.Card[])
   */
  @Override
  public void receiveCards(Card[] cards) {
    // TODO Auto-generated method stub
    
  }

  /* (non-Javadoc)
   * @see interfaces.NetworkLogic#receiveBet(logic.Player, int)
   */
  @Override
  public void receiveBet(Player player, int bet) {
    // TODO Auto-generated method stub
    
  }

  /* (non-Javadoc)
   * @see interfaces.NetworkLogic#receivePlayState(logic.PlayState)
   */
  @Override
  public void receivePlayState(PlayState ps) {
    // TODO Auto-generated method stub
    
  }

  /* (non-Javadoc)
   * @see interfaces.NetworkLogic#receiveCardPlayed(logic.Player, logic.Card)
   */
  @Override
  public void receiveCardPlayed(Player player, Card card) {
    // TODO Auto-generated method stub
    
  }

  /* (non-Javadoc)
   * @see interfaces.NetworkLogic#receiveYourTurn()
   */
  @Override
  public void receiveYourTurn() {
    // TODO Auto-generated method stub
    
  }

  /* (non-Javadoc)
   * @see interfaces.NetworkLogic#receivePlayerDisconnected(logic.Player)
   */
  @Override
  public void receivePlayerDisconnected(Player player) {
    // TODO Auto-generated method stub
    
  }

}
