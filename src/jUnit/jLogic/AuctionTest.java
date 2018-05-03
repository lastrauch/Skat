package jUnit.jLogic;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import logic.ClientLogic;
import logic.PlayState;
import logic.Player;

class AuctionTest {

  Player player1 = new Player("me");
  ClientLogic cl = new ClientLogic(player1);

  Player player2 = new Player("playerTwo");
  Player player3 = new Player("playerThree");

  Player[] group = {player1, player2, player3};
  PlayState ps = new PlayState(group);

  @Test
  void test() {
    fail("Not yet implemented");
  }

}
