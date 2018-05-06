package jUnitLogic;

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
  static Card heartsTen;
  static Card diamondsAss;
  static Card heartsKing;
  static Card clubsJack;
  static Card spadesJack;
  static Card heartsQueen;
  static Card clubsNine;
  static Card clubsEight;
  static Card spadesSeven;
  static Card heartsAss;
  static Card heartsJack;
  static Card clubsKing;
  static Card diamondsSeven;
  static Card diamondsJack;
  static Card spadesNine;
  static Card diamondsTen;
  static List<Card> hand;


  @BeforeAll
  static void setUpBeforeClass() {
    ps = new PlayState(new Player[3]);
    player = new Player("player");
    hand = new ArrayList<Card>();
    // hand.add(heartsTen);
    // hand.add(diamondsAss);
    // hand.add(heartsKing);
    // hand.add(clubsJack);
    // hand.add(spadesJack);
    // hand.add(heartsQueen);
    // hand.add(clubsNine);
    // hand.add(clubsEight);
    // hand.add(spadesSeven);
    // hand.add(heartsAss);
  }

  @BeforeEach
  void setUp() throws Exception {
    heartsTen = new Card(Colour.HEARTS, Number.TEN);
    diamondsAss = new Card(Colour.DIAMONDS, Number.ASS);
    heartsKing = new Card(Colour.HEARTS, Number.KING);
    clubsJack = new Card(Colour.CLUBS, Number.JACK);
    spadesJack = new Card(Colour.SPADES, Number.JACK);
    heartsQueen = new Card(Colour.HEARTS, Number.QUEEN);
    clubsNine = new Card(Colour.CLUBS, Number.NINE);
    clubsEight = new Card(Colour.CLUBS, Number.EIGHT);
    spadesSeven = new Card(Colour.SPADES, Number.SEVEN);
    heartsAss = new Card(Colour.HEARTS, Number.ASS);

    heartsJack = new Card(Colour.HEARTS, Number.JACK);
    clubsKing = new Card(Colour.CLUBS, Number.KING);
    diamondsSeven = new Card(Colour.DIAMONDS, Number.SEVEN);
    diamondsJack = new Card(Colour.DIAMONDS, Number.JACK);
    spadesNine = new Card(Colour.SPADES, Number.NINE);
  }

  // DIAMONDS ASS
  // HEARTS JACK
  // HEARTS KING
  // CLUBS EIGHT
  // CLUBS KING
  // DIAMONDS SEVEN
  // DIAMONDS JACK
  // SPADES NINE
  // DIAMONDS TEN
  // SPADES JACK
  // print hand froml:
  // SPADES JACK
  // SPADES JACK
  // HEARTS JACK
  // CLUBS KING
  // CLUBS EIGHT
  // SPADES NINE
  // HEARTS KING
  // DIAMONDS ASS
  // DIAMONDS TEN
  // DIAMONDS SEVEN

  @Test
  void testDoubleJack() {
    hand.add(diamondsAss);
    hand.add(heartsJack);
    hand.add(heartsKing);
    hand.add(clubsEight);
    hand.add(clubsKing);
    hand.add(diamondsSeven);
    hand.add(diamondsJack);
    hand.add(spadesNine);
    hand.add(diamondsTen);
    hand.add(spadesJack);

    List<Card> goodHand = new ArrayList<Card>();
    goodHand.add(spadesJack);
    goodHand.add(heartsJack);
    goodHand.add(diamondsJack);
    goodHand.add(clubsKing);
    goodHand.add(clubsEight);
    goodHand.add(spadesNine);
    goodHand.add(heartsKing);
    goodHand.add(diamondsAss);
    goodHand.add(diamondsTen);
    goodHand.add(diamondsSeven);
    
    this.test(goodHand);
  }

  @Test
  void testNoPlayMode() {
    List<Card> goodHand = new ArrayList<Card>();
    goodHand.add(clubsJack);
    goodHand.add(spadesJack);
    goodHand.add(clubsNine);
    goodHand.add(clubsEight);
    goodHand.add(spadesSeven);
    goodHand.add(heartsAss);
    goodHand.add(heartsTen);
    goodHand.add(heartsKing);
    goodHand.add(heartsQueen);
    goodHand.add(diamondsAss);

    this.test(goodHand);
  }

  @Test
  void testSuit() {
    hand.add(heartsTen);
    hand.add(diamondsAss);
    hand.add(heartsKing);
    hand.add(clubsJack);
    hand.add(spadesJack);
    hand.add(heartsQueen);
    hand.add(clubsNine);
    hand.add(clubsEight);
    hand.add(spadesSeven);
    hand.add(heartsAss);

    ps.setPlayMode(PlayMode.SUIT);
    ps.setTrump(Colour.HEARTS);

    List<Card> goodHand = new ArrayList<Card>();
    goodHand.add(clubsJack);
    goodHand.add(spadesJack);
    goodHand.add(heartsAss);
    goodHand.add(heartsTen);
    goodHand.add(heartsKing);
    goodHand.add(heartsQueen);
    goodHand.add(clubsNine);
    goodHand.add(clubsEight);
    goodHand.add(spadesSeven);
    goodHand.add(diamondsAss);

    this.test(goodHand);
  }

  @Test
  void testGrand() {
    hand.add(heartsTen);
    hand.add(diamondsAss);
    hand.add(heartsKing);
    hand.add(clubsJack);
    hand.add(spadesJack);
    hand.add(heartsQueen);
    hand.add(clubsNine);
    hand.add(clubsEight);
    hand.add(spadesSeven);
    hand.add(heartsAss);

    ps.setPlayMode(PlayMode.GRAND);

    List<Card> goodHand = new ArrayList<Card>();
    goodHand.add(clubsJack);
    goodHand.add(spadesJack);
    goodHand.add(clubsNine);
    goodHand.add(clubsEight);
    goodHand.add(spadesSeven);
    goodHand.add(heartsAss);
    goodHand.add(heartsTen);
    goodHand.add(heartsKing);
    goodHand.add(heartsQueen);
    goodHand.add(diamondsAss);

    this.test(goodHand);
  }

  @Test
  void testNull() {
    // 47859 10 3612
    hand.add(heartsTen);
    hand.add(diamondsAss);
    hand.add(heartsKing);
    hand.add(clubsJack);
    hand.add(spadesJack);
    hand.add(heartsQueen);
    hand.add(clubsNine);
    hand.add(clubsEight);
    hand.add(spadesSeven);
    hand.add(heartsAss);

    ps.setPlayMode(PlayMode.NULL);

    List<Card> goodHand = new ArrayList<Card>();
    goodHand.add(clubsJack);
    goodHand.add(clubsNine);
    goodHand.add(clubsEight);
    goodHand.add(spadesJack);
    goodHand.add(spadesSeven);
    goodHand.add(heartsAss);
    goodHand.add(heartsKing);
    goodHand.add(heartsQueen);
    goodHand.add(heartsTen);
    goodHand.add(diamondsAss);

    this.test(goodHand);
  }


  void test(List<Card> goodHand) {
    player.sortHand(ps);
    for (int i = 0; i < player.getHand().size(); i++) {
      assertEquals(goodHand.get(i), player.getHand().get(i));
    }

  }

}
