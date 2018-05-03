
package jUnit.jLogic;

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

  @BeforeAll
  static void setUpBeforeClass() {
    ps = new PlayState(new Player[] {new Player("P1"), new Player("P2"), new Player("P3")});
  }

  @BeforeEach
  void setUp() {
    trick = new Trick();
  }

  @Test
  void testSuit() throws LogicException {
    ps.setPlayMode(PlayMode.SUIT);
    ps.setTrump(Colour.HEARTS);

    trick.addCard(new Card(Colour.HEARTS, Number.TEN));
    trick.addCard(new Card(Colour.HEARTS, Number.JACK));
    trick.addCard(new Card(Colour.CLUBS, Number.ASS));

    assertEquals(trick.calculateWinner(ps), ps.getGroup()[1]);
    assertNotEquals(trick.calculateWinner(ps), ps.getGroup()[0]);
    assertNotEquals(trick.calculateWinner(ps), ps.getGroup()[2]);
  }

  @Test
  void testGrand() throws LogicException {
    ps.setPlayMode(PlayMode.GRAND);
    
    trick.addCard(new Card(Colour.HEARTS, Number.TEN));
    trick.addCard(new Card(Colour.HEARTS, Number.JACK));
    trick.addCard(new Card(Colour.CLUBS, Number.JACK));

    assertEquals(trick.calculateWinner(ps), ps.getGroup()[2]);
    assertNotEquals(trick.calculateWinner(ps), ps.getGroup()[0]);
    assertNotEquals(trick.calculateWinner(ps), ps.getGroup()[1]);
  }

  @Test
  void testNull() throws LogicException {
    ps.setPlayMode(PlayMode.NULL);

    trick.addCard(new Card(Colour.HEARTS, Number.TEN));
    trick.addCard(new Card(Colour.HEARTS, Number.ASS));
    trick.addCard(new Card(Colour.CLUBS, Number.JACK));

    assertEquals(trick.calculateWinner(ps), ps.getGroup()[1]);
    assertNotEquals(trick.calculateWinner(ps), ps.getGroup()[0]);
    assertNotEquals(trick.calculateWinner(ps), ps.getGroup()[2]);
  }

}
