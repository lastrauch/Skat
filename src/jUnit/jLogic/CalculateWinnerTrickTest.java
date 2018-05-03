/**
 * 
 */
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

  static Card card1;
  static Card card2;
  static Card card3;

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

    card1 = new Card(Colour.HEARTS, Number.TEN);
    card2 = new Card(Colour.HEARTS, Number.JACK);
    card3 = new Card(Colour.CLUBS, Number.ASS);

    trick.addCard(card1);
    trick.addCard(card2);
    trick.addCard(card3);

    assertEquals(trick.calculateWinner(ps), ps.getGroup()[1]);
    assertNotEquals(trick.calculateWinner(ps), ps.getGroup()[0]);
    assertNotEquals(trick.calculateWinner(ps), ps.getGroup()[2]);
  }

  @Test
  void testGrand() throws LogicException {
    ps.setPlayMode(PlayMode.GRAND);

    card1 = new Card(Colour.HEARTS, Number.TEN);
    card2 = new Card(Colour.HEARTS, Number.JACK);
    card3 = new Card(Colour.CLUBS, Number.JACK);

    trick.addCard(card1);
    trick.addCard(card2);
    trick.addCard(card3);

    assertEquals(trick.calculateWinner(ps), ps.getGroup()[2]);
    assertNotEquals(trick.calculateWinner(ps), ps.getGroup()[0]);
    assertNotEquals(trick.calculateWinner(ps), ps.getGroup()[1]);
  }

  @Test
  void testNull() throws LogicException {
    ps.setPlayMode(PlayMode.NULL);

    card1 = new Card(Colour.HEARTS, Number.TEN);
    card2 = new Card(Colour.HEARTS, Number.ASS);
    card3 = new Card(Colour.CLUBS, Number.JACK);

    trick.addCard(card1);
    trick.addCard(card2);
    trick.addCard(card3);

    assertEquals(trick.calculateWinner(ps), ps.getGroup()[1]);
    assertNotEquals(trick.calculateWinner(ps), ps.getGroup()[0]);
    assertNotEquals(trick.calculateWinner(ps), ps.getGroup()[2]);
  }

}
