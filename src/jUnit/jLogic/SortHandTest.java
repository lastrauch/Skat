package jUnit.jLogic;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import logic.Card;
import logic.Colour;
import logic.Number;
import logic.PlayMode;
import logic.PlayState;
import logic.Player;

class SortHandTest {
  static Player player;
  static PlayState ps;
  static Card card1;
  static Card card2;
  static Card card3;
  static Card card4;
  static Card card5;
  static Card card6;
  static Card card7;
  static Card card8;
  static Card card9;
  static Card card10;
  static List<Card> hand;


  @BeforeAll
  static void setUpBeforeClass() {
    ps = new PlayState(new Player[3]);
    player = new Player("player");
    hand = new ArrayList<Card>();
    hand.add(card1);
    hand.add(card2);
    hand.add(card3);
    hand.add(card4);
    hand.add(card5);
    hand.add(card6);
    hand.add(card7);
    hand.add(card8);
    hand.add(card9);
    hand.add(card10);
  }

  @BeforeEach
  void setUp() throws Exception {
    card1 = new Card(Colour.HEARTS, Number.TEN);
    card2 = new Card(Colour.DIAMONDS, Number.ASS);
    card3 = new Card(Colour.HEARTS, Number.KING);
    card4 = new Card(Colour.CLUBS, Number.JACK);
    card5 = new Card(Colour.SPADES, Number.JACK);
    card6 = new Card(Colour.HEARTS, Number.QUEEN);
    card7 = new Card(Colour.CLUBS, Number.NINE);
    card8 = new Card(Colour.CLUBS, Number.EIGHT);
    card9 = new Card(Colour.SPADES, Number.SEVEN);
    card10 = new Card(Colour.HEARTS, Number.ASS);
  }

  @Test
  void testNoPlayMode() {
    List<Card> goodHand = new ArrayList<Card>();
    goodHand.add(card4);
    goodHand.add(card5);
    goodHand.add(card7);
    goodHand.add(card8);
    goodHand.add(card9);
    goodHand.add(card10);
    goodHand.add(card1);
    goodHand.add(card3);
    goodHand.add(card6);
    goodHand.add(card2);
    
    this.test(goodHand);
  }

  @Test
  void testSuit() {
    ps.setPlayMode(PlayMode.SUIT);
    ps.setTrump(Colour.HEARTS);

    List<Card> goodHand = new ArrayList<Card>();
    goodHand.add(card4);
    goodHand.add(card5);
    goodHand.add(card10);
    goodHand.add(card1);
    goodHand.add(card3);
    goodHand.add(card6);
    goodHand.add(card7);
    goodHand.add(card8);
    goodHand.add(card9);
    goodHand.add(card2);
    
    this.test(goodHand);
  }

  @Test
  void testGrand() {
    ps.setPlayMode(PlayMode.GRAND);

    List<Card> goodHand = new ArrayList<Card>();
    goodHand.add(card4);
    goodHand.add(card5);
    goodHand.add(card7);
    goodHand.add(card8);
    goodHand.add(card9);
    goodHand.add(card10);
    goodHand.add(card1);
    goodHand.add(card3);
    goodHand.add(card6);
    goodHand.add(card2);
    
    this.test(goodHand);
  }

  @Test
  void testNull() {
    // 47859 10 3612

    ps.setPlayMode(PlayMode.NULL);

    List<Card> goodHand = new ArrayList<Card>();
    goodHand.add(card4);
    goodHand.add(card7);
    goodHand.add(card8);
    goodHand.add(card5);
    goodHand.add(card9);
    goodHand.add(card10);
    goodHand.add(card3);
    goodHand.add(card6);
    goodHand.add(card1);
    goodHand.add(card2);
    
    this.test(goodHand);
  }

  
  void test(List<Card> goodHand) {
    player.sortHand(ps);
    assertEquals(player.getHand(), goodHand);
  }

}
