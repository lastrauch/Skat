package logic;

public interface TrickInterface {
  Card[] trickCards = new Card[3];

  /**
   * @param player is the one playing the card 
   * @param card is the card which is played by the player
   * 
   * the card is added in the array trickCards
   */
  public void playCard(Player player, Card card);
  
  /**
   * 
   * @return player who won the trick
   */
  public Player calculateWinner();
  
  /**
   * 
   * @param card 
   * @return if it is possible to play this card (trump etc.)
   */
  public boolean checkIfCardIsPossible(Card card);
  
  /**
   * call this method when trickCards is "full", 
   * calculate the winner and start next trick if the game is not over
   */
  public void endTrick();  
}
