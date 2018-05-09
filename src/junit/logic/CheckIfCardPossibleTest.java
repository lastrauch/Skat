package junit.logic;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import logic.Card;
import logic.ClientLogic;
import logic.Colour;
import logic.LogicException;
import logic.Number;
import logic.PlayMode;
import logic.PlayState;
import logic.Player;
import logic.Trick;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class tests the method of ClientLogic in which it is tested if a card is possible to play.
 * "ClientLogic.checkIfCardPossible" adapts itself to the current playMode and to the first card on
 * the trick.
 * 
 * @author sandfisc
 *
 */
class CheckIfCardPossibleTest {

  static Player p1; // player of first card
  static Player p2; // player of second trick card
  static Player p3; // player of third trick card

  static PlayState ps;
  static Card firstCard;

  // cards possible to play
  static Card goodCard1;
  static Card goodCard2; // initialized depending on the PlayMode/Test

  // cards not possible to play
  static Card badCard1;
  static Card badCard2;

  @BeforeAll
  static void setUpBeforeClass() {
    p1 = new Player("P1");
    p2 = new Player("P2");
    p3 = new Player("P3");

    ps = new PlayState(new Player[] {p1, p2, p3});
  }

  @BeforeEach
  void setUp() {
    // PlayState
    // we start with one card on the trick
    firstCard = new Card(Colour.HEARTS, Number.NINE); // <3 9
    ps.setCurrentTrick(new Trick());
    ps.getCurrentTrick().addCard(firstCard, p1);

    // good cards because they do serve the first card
    goodCard1 = new Card(Colour.HEARTS, Number.TEN); // HEARTS = Trump

    // bad cards because they do not serve the first card
    badCard1 = new Card(Colour.CLUBS, Number.NINE);
    badCard2 = new Card(Colour.DIAMONDS, Number.NINE);

    // player 2 and his hand
    p2.addToHand(goodCard1);
    p2.addToHand(goodCard2);
    p2.addToHand(badCard1);
    p2.addToHand(badCard2);

    // player 3 and his hand
    p3.addToHand(badCard1);
    p3.addToHand(badCard2);
  }

  /* PlayMode : SUIT */
  @Test
  void testSuit() throws LogicException {
    ps.setPlayMode(PlayMode.SUIT);
    ps.setTrump(Colour.HEARTS);
    goodCard2 = new Card(Colour.CLUBS, Number.JACK); // JACK = Trump

    this.test();
  }

  /* PlayMode : GRAND */
  @Test
  void testGrand() throws LogicException {
    ps.setPlayMode(PlayMode.GRAND);
    goodCard2 = new Card(Colour.HEARTS, Number.SEVEN);
    this.test();
  }

  /* PlayMode : NULL */
  @Test
  void testNull() throws LogicException {
    ps.setPlayMode(PlayMode.NULL);
    goodCard2 = new Card(Colour.HEARTS, Number.QUEEN);
    this.test();
  }

  void test() throws LogicException {

    // test player 2 (he has got cards to serve the first card on the trick)
    assertTrue(
        ClientLogic.checkIfCardPossible(goodCard1, ps.getCurrentTrick().getFirstCard(), ps, p2));
    assertTrue(
        ClientLogic.checkIfCardPossible(goodCard2, ps.getCurrentTrick().getFirstCard(), ps, p2));
    assertFalse(
        ClientLogic.checkIfCardPossible(badCard1, ps.getCurrentTrick().getFirstCard(), ps, p2));
    assertFalse(
        ClientLogic.checkIfCardPossible(badCard2, ps.getCurrentTrick().getFirstCard(), ps, p2));

    // test player 3 (he has got no cards to serve the first card on the trick
    // and is allowed to play any card he/she wants)
    assertTrue(
        ClientLogic.checkIfCardPossible(badCard1, ps.getCurrentTrick().getFirstCard(), ps, p3));
    assertTrue(
        ClientLogic.checkIfCardPossible(badCard2, ps.getCurrentTrick().getFirstCard(), ps, p3));
  }
}

