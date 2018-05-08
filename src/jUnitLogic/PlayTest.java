/**
 * 
 */
package jUnitLogic;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import logic.Card;
import logic.Colour;
import logic.CountRule;
import logic.GameSettings;
import logic.LogicException;
import logic.Number;
import logic.Play;
import logic.PlayState;
import logic.Player;

/**
 * @author sandfisc
 *
 */
class PlayTest {

  static PlayState ps;
  static GameSettings gs;

  static Player[] expectedWinner;
  static Player[] expectedLooser;

  static Player[] winner;
  
  static List<Card> winnerCards;
  static List<Card> looserCards;

  static Player p1;
  static Player p2;
  static Player p3;

  @BeforeAll
  static void setUpBeforeClass() {
    
    winnerCards = new ArrayList<Card>();
    winnerCards.add(new Card(Colour.SPADES, Number.ASS));
    winnerCards.add(new Card(Colour.SPADES, Number.TEN));
    winnerCards.add(new Card(Colour.SPADES, Number.KING));

    looserCards = new ArrayList<Card>();
    looserCards.add(new Card(Colour.SPADES, Number.QUEEN));
    looserCards.add(new Card(Colour.SPADES, Number.JACK));
    looserCards.add(new Card(Colour.SPADES, Number.NINE));

    expectedWinner = new Player[2];
    expectedLooser = new Player[2];

    winner = new Player[2];    
    gs = new GameSettings();
  }

  @BeforeEach
  void setUp() {
    p1 = new Player("P1");
    p1.setBet(18);
    p1.setDeclarer(true);

    p2 = new Player("P2");
    p2.setBet(-1);
    p2.setDeclarer(false);

    p3 = new Player("P3");
    p3.setBet(-1);
    p3.setDeclarer(false);

    ps = new PlayState(new Player[] {p1, p2, p3});
    ps.setPlayValue(44);
  }

  @Test 
  void testOverBid(){
    p1.setBet(60);
    assertTrue(Play.checkOverBid(ps));
  }
  
  /* Winner (Standard) Opponents(No OverBid, No Schwarz, No Schneider) */
  @Test
  void testWinnerOpponentsNormal() {
    ps.getDeclarerStack().addCards(looserCards);
    ps.getOpponentsStack().addCards(winnerCards);

    expectedWinner[0] = p2;
    expectedWinner[1] = p3;
    expectedLooser[0] = p1;
    expectedLooser[1] = null;

    this.testWinner();
  }

  /* Winner (Standard) Declarer(No OverBid, No Schwarz, No Schneider) */
  @Test
  void testWinnerDeclarerNormal() {
    ps.getDeclarerStack().addCards(winnerCards);
    ps.getOpponentsStack().addCards(looserCards);

    expectedWinner[0] = p1;
    expectedWinner[1] = null;
    expectedLooser[0] = p2;
    expectedLooser[1] = p3;

    this.testWinner();
  }

  void testWinner() {
    assertFalse(Play.checkOverBid(ps));
 //   winner = Play.calculateWinner(ps);
    assertEquals(winner[0], expectedWinner[0]);
    assertEquals(winner[1], expectedWinner[1]);
//    assertNotEquals(Play.calculateWinner(ps)[0], expectedLooser[0]);
//    assertNotEquals(Play.calculateWinner(ps)[1], expectedLooser[1]);
  }

//  /* Calculate Points Normal (Declarer won) */
//  @Test 
//  void testCalculatePointsNormal1() throws LogicException {
//    gs.setCountRule(CountRule.NORMAL);
//    Play.calculatePoints(ps, gs, true);
//    assertEquals(p1.getGameScore(), ps.getPlayValue());
//    assertEquals(p2.getGameScore(), 0);
//    assertEquals(p3.getGameScore(), 0);
//  }
//  
//  /* Calculate Points Normal (Opponents won) */
//  @Test 
//  void testCalculatePointsNormal2() throws LogicException {
//    gs.setCountRule(CountRule.NORMAL);
//    Play.calculatePoints(ps, gs, false);
//    assertEquals(p1.getGameScore(), ps.getPlayValue() * (-2));
//    assertEquals(p2.getGameScore(), 0);
//    assertEquals(p3.getGameScore(), 0);
//  }
//  
//  /* Calculate Points Bierlachs (Declarer won) */
//  @Test 
//  void testCalculatePointsBierlachs1() throws LogicException {
//    gs.setCountRule(CountRule.BIERLACHS);
//    Play.calculatePoints(ps, gs, true);
//    assertEquals(p1.getGameScore(), 0);
//    assertEquals(p2.getGameScore(), ps.getPlayValue() * (-1));
//    assertEquals(p3.getGameScore(), ps.getPlayValue() * (-1));
//  }
//
//  /* Calculate Points Bierlachs (Opponents won) */
//  @Test
//  void testCalculatePointsBierlachs2() throws LogicException {
//    gs.setCountRule(CountRule.BIERLACHS);
//    Play.calculatePoints(ps, gs, false);
//    assertEquals(p1.getGameScore(), ps.getPlayValue() * (-2));
//    assertEquals(p2.getGameScore(), 0);
//    assertEquals(p3.getGameScore(), 0);
//  }
//  
//  /* Calculate Points Seegerfabian (Opponents won) */
//  @Test
//  void testCalculatePointsSeeger1() throws LogicException {
//    gs.setCountRule(CountRule.SEEGERFABIAN);
//    Play.calculatePoints(ps, gs, true);
//    assertEquals(p1.getGameScore(), ps.getPlayValue() + 50);
//    assertEquals(p2.getGameScore(), 0);
//    assertEquals(p3.getGameScore(), 0);
//  }
//  
//  /* Calculate Points Seegerfabian (Opponents won) */
//  @Test
//  void testCalculatePointsSeeger2() throws LogicException {
//    gs.setCountRule(CountRule.SEEGERFABIAN);
//    Play.calculatePoints(ps, gs, false);
//    assertEquals(p1.getGameScore(), (-1) * (ps.getPlayValue() + 50));
//    assertEquals(p2.getGameScore(), 0);
//    assertEquals(p3.getGameScore(), 0);
//  }
}
