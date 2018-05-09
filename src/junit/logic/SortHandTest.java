package junit.logic;


import logic.Card;
import logic.Colour;
import logic.Number;
import logic.PlayMode;
import logic.PlayState;
import logic.Player;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;

/**
 * this class tests the method sort hand in player.
 * 
 * @author awesch
 *
 */

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


  /**
   * prepares the players hand.
   */
  @BeforeAll
  static void setUpBeforeClass() {
    ps = new PlayState(new Player[3]);
    player = new Player("player");
    hand = new ArrayList<Card>();
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
  }

  /**
   * initializes the cards.
   * 
   * @throws Exception
   */
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

  }

  /**
   * sorts before a playmode is set.
   */
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

  /**
   * sorts with playState = suit and trump = hearts.
   */
  @Test
  void testSuit() {

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

  /**
   * sorts with the playmode grand.
   */
  @Test
  void testGrand() {

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

  /**
   * sorts with the playmode null.
   */
  @Test
  void testNull() {

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

  /**
   * to help with the other methods.
   * 
   * @param goodHand
   */
  void test(List<Card> goodHand) {
    player.sortHand(ps);
    for (int i = 0; i < player.getHand().size(); i++) {
      assertEquals(goodHand.get(i), player.getHand().get(i));
    }

  }

}
