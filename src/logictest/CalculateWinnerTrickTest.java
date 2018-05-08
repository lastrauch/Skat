
package logictest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import logic.Card;
import logic.Colour;
import logic.LogicException;
import logic.Number;
import logic.PlayMode;
import logic.PlayState;
import logic.Player;
import logic.Trick;

/**
 * @author sandfisc
 *
 */
class CalculateWinnerTrickTest {

  static PlayState ps;
  static Trick trick;
  static Player p1;
  static Player p2;
  static Player p3;

  @BeforeAll
  static void setUpBeforeClass() {
    p1 = new Player("P1");
    p2 = new Player("P2");
    p3 = new Player("P3");
    
    ps = new PlayState(new Player[] {p1, p2, p3});
  }

  @BeforeEach
  void setUp() {
    trick = new Trick();
  }

  @Test
  void testSuit() throws LogicException {
    ps.setPlayMode(PlayMode.SUIT);
    ps.setTrump(Colour.HEARTS);

    trick.addCard(new Card(Colour.HEARTS, Number.TEN), p1);
    trick.addCard(new Card(Colour.HEARTS, Number.JACK), p2);
    trick.addCard(new Card(Colour.CLUBS, Number.ASS), p3);

    assertEquals(trick.calculateWinner(ps), ps.getGroup()[1]);
    assertNotEquals(trick.calculateWinner(ps), ps.getGroup()[0]);
    assertNotEquals(trick.calculateWinner(ps), ps.getGroup()[2]);
  }

  @Test
  void testGrand() throws LogicException {
    ps.setPlayMode(PlayMode.GRAND);
    
    trick.addCard(new Card(Colour.HEARTS, Number.TEN), p1);
    trick.addCard(new Card(Colour.HEARTS, Number.JACK), p2);
    trick.addCard(new Card(Colour.CLUBS, Number.JACK), p3);

    assertEquals(trick.calculateWinner(ps), ps.getGroup()[2]);
    assertNotEquals(trick.calculateWinner(ps), ps.getGroup()[0]);
    assertNotEquals(trick.calculateWinner(ps), ps.getGroup()[1]);
  }

  @Test
  void testNull() throws LogicException {
    ps.setPlayMode(PlayMode.NULL);

    trick.addCard(new Card(Colour.HEARTS, Number.TEN), p1);
    trick.addCard(new Card(Colour.HEARTS, Number.ASS), p2);
    trick.addCard(new Card(Colour.CLUBS, Number.JACK), p3);

    assertEquals(trick.calculateWinner(ps), ps.getGroup()[1]);
    assertNotEquals(trick.calculateWinner(ps), ps.getGroup()[0]);
    assertNotEquals(trick.calculateWinner(ps), ps.getGroup()[2]);
  }

}
