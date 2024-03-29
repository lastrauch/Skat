package junit.logic;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import logic.Card;
import logic.ClientLogic;
import logic.Colour;
import logic.Number;
import logic.PlayMode;
import logic.PlayState;
import logic.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * this class tests the method calculatePlayValue in ClientLogic.
 * 
 * @author awesch
 *
 */
class CalculatePlayValueTest {
  Player player;
  ClientLogic clientLogic;
  PlayState playState;
  List<Card> hand;
  Card clubsJack;
  Card spadesJack;
  Card heartsJack;
  Card diamondsJack;
  Card heartsAss;
  Card heartsTen;
  Card heartsKing;
  Card heartsNine;
  Card whatEver1;
  Card whatEver2;
  Card whatEver3;
  Card whatEver4;
  Card whatEver5;
  int matador;
  int playValue;

  /**
   * sets playMode suit, playmode trump and initializes.
   * 
   * @throws Exception if not possible
   */
  @BeforeEach
  void setUp() throws Exception {
    player = new Player("player");
    clientLogic = new ClientLogic(player);

    playState = new PlayState(new Player[3]);
    playState.setPlayMode(PlayMode.SUIT);
    playState.setTrump(Colour.HEARTS);
    clientLogic.setPlayState(playState);

    hand = new ArrayList<Card>();

    clubsJack = new Card(Colour.CLUBS, Number.JACK);
    spadesJack = new Card(Colour.SPADES, Number.JACK);
    heartsJack = new Card(Colour.HEARTS, Number.JACK);
    diamondsJack = new Card(Colour.DIAMONDS, Number.JACK);
    heartsAss = new Card(Colour.HEARTS, Number.ASS);
    heartsTen = new Card(Colour.HEARTS, Number.TEN);
    heartsKing = new Card(Colour.HEARTS, Number.KING);
    heartsNine = new Card(Colour.HEARTS, Number.NINE);
    whatEver1 = new Card(Colour.SPADES, Number.ASS);
    whatEver2 = new Card(Colour.SPADES, Number.TEN);
    whatEver3 = new Card(Colour.SPADES, Number.KING);
    whatEver4 = new Card(Colour.SPADES, Number.QUEEN);
    whatEver5 = new Card(Colour.SPADES, Number.NINE);
  }

  /* Tests for calculateMatador in ClientLogic */

  /**
   * with clubs Jack, but no spades jack --> with 1.
   */
  @Test
  void testMatadorWith1() {

    hand.add(clubsJack);
    hand.add(heartsJack);
    hand.add(diamondsJack);
    hand.add(heartsAss);
    hand.add(heartsTen);
    hand.add(heartsKing);
    hand.add(heartsNine);
    hand.add(whatEver1);
    hand.add(whatEver2);
    hand.add(whatEver3);

    player.setHand(hand);
    matador = 1;

    this.testCalculateMatador();
  }

  /**
   * with clubs and spades jack --> with 2.
   */
  @Test
  void testMatadorWith2() {
    hand.add(clubsJack);
    hand.add(spadesJack);
    hand.add(diamondsJack);
    hand.add(heartsAss);
    hand.add(heartsTen);
    hand.add(heartsKing);
    hand.add(heartsNine);
    hand.add(whatEver1);
    hand.add(whatEver2);
    hand.add(whatEver3);

    player.setHand(hand);
    matador = 2;

    this.testCalculateMatador();
  }

  /**
   * with clubs and all other jacks and 7 trumps in a row --> with 7.
   */
  @Test
  void testMatadorWith7() {

    hand.add(clubsJack);
    hand.add(spadesJack);
    hand.add(heartsJack);
    hand.add(diamondsJack);
    hand.add(heartsAss);
    hand.add(heartsTen);
    hand.add(heartsKing);
    hand.add(heartsNine);
    hand.add(whatEver1);
    hand.add(whatEver2);

    player.setHand(hand);
    matador = 7;

    this.testCalculateMatador();
  }

  /**
   * no clubs jack, but with spades jack --> with 1.
   */
  @Test
  void testMatadorAgainst1() {

    hand.add(spadesJack);
    hand.add(heartsJack);
    hand.add(diamondsJack);
    hand.add(heartsAss);
    hand.add(heartsTen);
    hand.add(heartsKing);
    hand.add(heartsNine);
    hand.add(whatEver1);
    hand.add(whatEver2);
    hand.add(whatEver3);

    player.setHand(hand);
    matador = 1;

    this.testCalculateMatador();
  }

  /**
   * without clubs, spades and hearts jack, but with diamons jack --> without 3.
   */
  @Test
  void testMatadorAgainst3() {

    hand.add(diamondsJack);
    hand.add(heartsAss);
    hand.add(heartsTen);
    hand.add(heartsKing);
    hand.add(heartsNine);
    hand.add(whatEver1);
    hand.add(whatEver2);
    hand.add(whatEver3);
    hand.add(whatEver4);
    hand.add(whatEver5);

    player.setHand(hand);
    matador = 3;

    this.testCalculateMatador();
  }

  /**
   * helps with the other matador methods.
   */
  void testCalculateMatador() {
    assertEquals(matador, this.clientLogic.calculateMatador());
  }

  /**
   * tests fixed values for null.
   */
  @Test
  void testPlayValueNullOuvertHand() {
    playState.setPlayMode(PlayMode.NULL);
    playState.setOpen(true);
    playState.setHandGame(true);

    assertEquals(59, clientLogic.calculatePlayValue());
  }

  /**
   * against 3, play hand, schneiderAnnounced and schneider played --> multiplier 7.
   */
  @Test
  void testCalculateMultiplier1() {
    this.testMatadorAgainst3();
    playState.setHandGame(true);
    playState.setSchneider(true);
    playState.setSchneiderAnnounced(true);

    assertEquals(7, clientLogic.calculateMultiplier());
  }

  /**
   * with 2, open gives you a piont, only if you play hand --> multiplier 3.
   */
  @Test
  void testCalculateMultiplier2() {
    this.testMatadorWith2();
    playState.setOpen(true);

    assertEquals(3, clientLogic.calculateMultiplier());
  }

  /**
   * playmode hearts --> 7* 10.
   */
  @Test
  void testCalculatePlayValueSuit() {
    this.testCalculateMultiplier1();

    assertEquals(70, clientLogic.calculatePlayValue());
  }

  /**
   * playmode grand --> 3*24.
   */
  @Test
  void testCalculatePlayValueGrand() {
    this.testCalculateMultiplier2();
    playState.setPlayMode(PlayMode.GRAND);

    assertEquals(72, clientLogic.calculatePlayValue());
  }

}
