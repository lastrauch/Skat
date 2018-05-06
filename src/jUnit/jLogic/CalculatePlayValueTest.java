package jUnit.jLogic;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import logic.Card;
import logic.ClientLogic;
import logic.Colour;
import logic.Number;
import logic.PlayMode;
import logic.PlayState;
import logic.Player;

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

  void testCalculateMatador() {
    assertEquals(matador, this.clientLogic.calculateMatador());
  }

  void testPlayValueNullOuvertHand() {
    playState.setPlayMode(PlayMode.NULL);
    assertEquals(59, clientLogic.calculatePlayValue());
  }
  
  /*against 3, play hand, schneiderAnnounced and schneider played --> multiplayer 7
    playmode hearts --> 7*10 */
  @Test
  void testCalculateMultiplier() {
    
  }
    @Test
    void testCalculatePlayValue() {
      this.testMatadorAgainst3();
      playState.setHandGame(true);
      playState.setSchneiderAnnounced(true);
      playState.setSchneider(true);
      clientLogic.setPlayState(playState);
      assertEquals(70, clientLogic.calculatePlayValue());
    }

}
