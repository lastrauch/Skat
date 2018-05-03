package jUnit.jLogic;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import logic.Card;
import logic.ClientLogic;
import logic.Colour;
import logic.LogicException;
import logic.Number;
import logic.PlayMode;
import logic.PlayState;
import logic.Player;
import logic.Trick;

/**
 * This class tests the method of ClientLogic in which it is tested if a card is possible to play.
 * "ClientLogic.checkIfCardPossible" adapts itself to the current playMode and to the first card on the trick
 * 
 * @author sandfisc
 *
 */
// @RunWith(Parameterized.class)
class CheckIfCardPossibleTest {

  static Player p2; // player of second trick card
  static Player p3; // player of third trick card

  static PlayState ps;
  static Card firstCard;

  // cards possible to play
  static Card goodCard1;
  static Card goodCard2;

  // cards not possible to play
  static Card badCard1;
  static Card badCard2;

  @BeforeAll
  static void setUpBeforeClass() {
    ps = new PlayState(new Player[3]);
  }

  @BeforeEach
  void setUp() {
    // PlayState
    // we start with one card on the trick
    firstCard = new Card(Colour.HEARTS, Number.NINE); // <3 9
    ps.setCurrentTrick(new Trick());
    ps.getCurrentTrick().addCard(firstCard);

    // good cards because they do serve the first card
    goodCard1 = new Card(Colour.HEARTS, Number.TEN); // HEARTS = Trump
    goodCard2 = new Card(Colour.CLUBS, Number.JACK); // JACK = Trump

    // bad cards because they do not serve the first card
    badCard1 = new Card(Colour.CLUBS, Number.NINE);
    badCard2 = new Card(Colour.DIAMONDS, Number.NINE);

    // player 2 and his hand
    p2 = new Player("P2.");
    p2.addToHand(goodCard1);
    p2.addToHand(goodCard2);
    p2.addToHand(badCard1);
    p2.addToHand(badCard2);

    // player 3 and his hand
    p3 = new Player("P3");
    p3.addToHand(badCard1);
    p3.addToHand(badCard2);
  }

  /* PlayMode : SUIT */
  @Test
  void testCheckIfCardPossibleColour() throws LogicException {
    ps.setPlayMode(PlayMode.SUIT);
    ps.setTrump(Colour.HEARTS);
    this.test();
  }

  /* PlayMode : GRAND */
  @Test
  void testCheckIfCardPossibleGrand() throws LogicException {
    ps.setPlayMode(PlayMode.GRAND);
    this.test();
  }

  /* PlayMode : NULL */
  @Test
  void testCheckIfCardPossibleNull() throws LogicException {
    ps.setPlayMode(PlayMode.NULL);
    this.test();
  }

  void test() throws LogicException {
    
    // in PlayMode null goodCard2 would be wrong to play -> it has to be changed
    if (ps.getPlayMode() == PlayMode.NULL) {
      goodCard2 = new Card(Colour.HEARTS, Number.QUEEN);
    }

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

