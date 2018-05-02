package jUnit.jLogic;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.After;
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
 * @author sandfisc
 *
 */
class CheckIfCardPossibleTest {

  static Player p1;
  static Player p2;
  static Player p3;
  static Player[] group;
  static PlayState ps;
  static Card goodCard1;
  static Card goodCard2;
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
    Card firstCard = new Card(Colour.HEARTS, Number.NINE); // <3 9
    ps.setCurrentTrick(new Trick());
    ps.getCurrentTrick().addCard(firstCard);

    // set PlaymMode and Trump
    ps.setPlayMode(PlayMode.NULL);
    ps.setTrump(Colour.HEARTS); // is ignored if the PlayMode is not SUIT

    // good cards because they do serve the first card
    goodCard1 = new Card(Colour.HEARTS, Number.TEN); // HEARTS = Trump
    if (ps.getPlayMode() != PlayMode.NULL) {
      goodCard2 = new Card(Colour.CLUBS, Number.JACK); // JACK = Trump
    }else {
      goodCard2 = new Card(Colour.HEARTS, Number.QUEEN);
    }    

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

  @Test
  void testCheckIfCardPossible() throws LogicException {
    // test player 2 (he has got cards to serve the first card on the trick)
    assertTrue(
        ClientLogic.checkIfCardPossible(goodCard1, ps.getCurrentTrick().getFirstCard(), ps, p2));
    assertTrue(
        ClientLogic.checkIfCardPossible(goodCard2, ps.getCurrentTrick().getFirstCard(), ps, p2));
    assertFalse(
        ClientLogic.checkIfCardPossible(badCard1, ps.getCurrentTrick().getFirstCard(), ps, p2));
    assertFalse(
        ClientLogic.checkIfCardPossible(badCard2, ps.getCurrentTrick().getFirstCard(), ps, p2));

    // test player 3 (he has got no cards to serve the first card on the trick)
    assertTrue(
        ClientLogic.checkIfCardPossible(badCard1, ps.getCurrentTrick().getFirstCard(), ps, p3));
    assertTrue(
        ClientLogic.checkIfCardPossible(badCard2, ps.getCurrentTrick().getFirstCard(), ps, p3));

  }
}
