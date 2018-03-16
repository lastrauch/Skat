package logic;

public interface GameInterface {
  
  /**
   * @param the pointer points on the forehand. 
   * this has to be changed after a play 
   */
  public void setPointerF(int pointer);
  
  /**  
   * @return 1, 2, 3, or 4 
   */
  public int checkNumberOfPlayers();
  
  /**
   * defines random where the oplayer "sit"
   */
  public void defineSeatingList(Player[] group);
  
  /**
   * @param 1, 3, 18, 36 
   * (1 play is especially for us, to test faster)
   */
  public void setNumberOfPlays(int nr);
  
  /**
   * starts new play if game is not over 
   */
  public void startPLay();
  
  /**
   * updates all positions of the group-members
   */
  public void updatePosition();
}