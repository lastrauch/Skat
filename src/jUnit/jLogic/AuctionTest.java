package jUnit.jLogic;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import logic.ClientLogic;
import logic.PlayState;
import logic.Player;
import logic.Position;

class AuctionTest {

  Player player1 = new Player("playerOne");
  ClientLogic cl1 = new ClientLogic(player1);

  Player player2 = new Player("playerTwo");
  ClientLogic cl2 = new ClientLogic(player2);
  
  Player player3 = new Player("playerThree");
  ClientLogic cl3 = new ClientLogic(player3);

  Player[] group = {player1, player2, player3};
  PlayState ps = new PlayState(group);
  
  @Test
  void testCheckTurnForehand() {
    player1.setPosition(Position.FOREHAND);
  }
  
  @Test
  void testCheckTurnMiddlehand() {
    player1.setPosition(Position.MIDDLEHAND);
  }
  
  @Test
  void testCheckTurnRearhand() {
    player1.setPosition(Position.REARHAND);
  }
  
  @Test
  void testCheckTurnDealer() {
    player1.setPosition(Position.DEALER);
  }
 
  @Test
  void test() {
    fail("Not yet implemented");
  }

}
