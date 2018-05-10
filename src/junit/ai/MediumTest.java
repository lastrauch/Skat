package junit.ai;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import ai.AiController;
import ai.BotDifficulty;
import ai.General;
import ai.Medium;
import java.util.ArrayList;
import java.util.List;
import logic.Card;
import logic.ClientLogic;
import logic.Colour;
import logic.GameSettings;
import logic.Number;
import logic.PlayMode;
import logic.PlayState;
import logic.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests methods of the medium ai.
 * 
 * @author fkleinoe
 */
class MediumTest {
  AiController controller;
  PlayState playState;

  /**
   * Set up before each method.
   * 
   * @author fkleinoe
   * @throws Exception e
   */
  @BeforeEach
  void setUp() throws Exception {
    controller = new AiController(new ClientLogic(new Player("Felix")), "Bot", BotDifficulty.MEDIUM,
        new GameSettings());
    Player partner = new Player("Partner");
    partner.setId(200);
    Player opponent = new Player("Opponent");
    opponent.setId(300);
    opponent.setDeclarer(true);
    // Ask for bet just to set correct ids
    controller.askForBet(18, partner);
    controller.askForBet(20, opponent);
    Player[] group = new Player[3];
    group[0] = controller.getBot();
    group[1] = partner;
    group[2] = opponent;
    PlayState playState = new PlayState(group);
    playState.setPlayMode(PlayMode.SUIT);
    playState.setTrump(Colour.CLUBS);
    Card skat1 = new Card(Colour.DIAMONDS, Number.JACK);
    Card skat2 = new Card(Colour.DIAMONDS, Number.SEVEN);
    Card[] skat = {skat1, skat2};
    playState.setSkat(skat);
    controller.setPlayState(playState);
    Card card1 = new Card(Colour.CLUBS, Number.TEN);
    Card card2 = new Card(Colour.HEARTS, Number.TEN);
    Card card3 = new Card(Colour.HEARTS, Number.NINE);
    Card card4 = new Card(Colour.DIAMONDS, Number.ASS);
    List<Card> hand = new ArrayList<Card>();
    hand.add(card1);
    hand.add(card2);
    hand.add(card3);
    hand.add(card4);
    controller.getBot().setHand(hand);
    controller.setCardProbabilities(General.initializeProbabilities(controller.getBot().getHand()));

    Card serve = new Card(Colour.DIAMONDS, Number.KING);
    List<Card> currentTrick = new ArrayList<Card>();
    currentTrick.add(serve);
    controller.setCurrentTrick(currentTrick);
  }

  /**
   * Check whether the returned skat has size 2.
   * 
   * @author fkleinoe
   */
  @Test
  void testReturnSkat() {
    assertEquals(2, Medium.returnSkat(controller, controller.getPlayState().getPlayMode()).size());
  }

  /**
   * Checks, if the ai plays a card in PlayMode grand.
   * 
   * @author fkleinoe
   */
  @Test
  void testPlayCardGrand() {
    assertTrue(-1 != Medium.playCardGrand(controller));
  }

  /**
   * Checks, if the ai plays a card in PlayMode suit.
   * 
   * @author fkleinoe
   */
  @Test
  void testPlayCardSuit() {
    assertTrue(-1 != Medium.playCardSuit(controller));
  }

  /**
   * Checks, if the ai plays a card in PlayMode null.
   * 
   * @author fkleinoe
   */
  @Test
  void testPlayCardNull() {
    assertTrue(-1 != Medium.playCardNull(controller));
  }

}
