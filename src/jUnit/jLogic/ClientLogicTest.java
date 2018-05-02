/**
 * 
 */
package jUnit.jLogic;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import logic.Card;
import logic.ClientLogic;
import logic.Colour;
import logic.LogicException;
import logic.Number;
import logic.PlayState;
import logic.Player;
import logic.Position;

/**
 * @author sandfisc
 *
 */
class ClientLogicTest {

  static Player p1;
  static Player p2;
  static Player p3;
  static Player[] group;
  static PlayState ps;

  static ClientLogic clientLogic;

  @BeforeClass
  public static void create() {

  }

  @BeforeAll
  static void setUpBeforeClass() {



  }

  @BeforeEach
  void setUp() {

  }

  @Test
  void testCheckIfCardPossible() throws LogicException {

    // Player
    Player p1 = new Player("P1");
    p1.setPosition(Position.FOREHAND);

    Player p2 = new Player("P2");
    p1.setPosition(Position.MIDDLEHAND);
    clientLogic = new ClientLogic(p2); // ClientLogic

    Player p3 = new Player("P3");
    p1.setPosition(Position.REARHAND);

    // Group
    Player[] group = new Player[] {p1, p2, p3};

    // PlayState
    PlayState ps = new PlayState(group);
    Card firstCard = new Card(Colour.HEARTS, Number.NINE);
    ps.getCurrentTrick().addCard(firstCard);
    clientLogic.setPlayState(ps);

    Card goodCard = new Card(Colour.HEARTS, Number.TEN);
    Card badCard = new Card(Colour.DIAMONDS, Number.NINE);

    System.out.println(ps.getCurrentTrick().getFirstCard().getColour());
    // assertEquals(ps.getCurrentTrick().getFirstCard(), new Card(Colour.HEARTS, Number.NINE));
    assertTrue(
        ClientLogic.checkIfCardPossible(goodCard, ps.getCurrentTrick().getFirstCard(), ps, p2));
  //  assertFalse(
  //     ClientLogic.checkIfCardPossible(badCard, ps.getCurrentTrick().getFirstCard(), ps, p2));
  }

  @After
  public void after() {}

}
