package logic;

public interface GameInterface {
  
  /**
   * @param the pointer points on the forehand. 
   * this has to be changed after a play 
   */
  public void setPointerF(int pointer);


  /**
   * defines random where the players "sit"
   */
  public void defineSeatingList(Player[] group);
  
 
  /**
   * starts new play if game is not over 
   */
  public void startPLay();
  
  /**
   * updates all positions of the group-members
   */
  public void updatePosition();
  
   /**
    * 
    * @param indexPosition: position of the player in group whos score has to be set
    * @param addThis
    */
  public void setGameScore(int indexPosition, int addThis);
}