package junit.ai;

import static org.junit.Assert.assertEquals;

import ai.AiController;
import ai.BotDifficulty;
import ai.General;
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
 * Tests methods of the class General.
 * 
 * @author fkleinoe
 */
class GeneralTest {
  AiController controller;
  PlayState playState;

  /**
   * Set up before class.
   * 
   * @author fkleinoe
   * @throws Exception e
   */
  @BeforeEach
  void setUp() throws Exception {
    playState = new PlayState(new Player[3]);
    playState.setPlayMode(PlayMode.SUIT);
    playState.setTrump(Colour.CLUBS);
    controller = new AiController(new ClientLogic(new Player("Felix")), "Bot", BotDifficulty.MEDIUM,
        new GameSettings());
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
   * Plays a random card.
   * 
   * @author fkleinoe
   * @throws Exception e
   */
  @Test
  void testPlayRandomCard() {
    assertEquals(3, General.playRandomCard(controller));
  }

  /**
   * Tests the highest possible bet value with the current hand.
   * 
   * @author fkleinoe
   * @throws Exception e
   */
  @Test
  void testGetHighestPossibleBetAiControllerPlayMode() {
    assertEquals(120, General.getHighestPossibleBet(controller));
  }

  /**
   * Tests the game value of the current hand.
   * 
   * @author fkleinoe
   * @throws Exception e
   */
  @Test
  void testGetGameLevel() {
    assertEquals(5, General.getGameLevel(controller));
  }

  /**
   * Tests if the correct index is given back.
   * 
   * @author fkleinoe
   * @throws Exception e
   */
  @Test
  void testCheckIfPossibleAndGetIndex() {
    assertEquals(1, General.checkIfPossibleAndGetIndex(controller.getCardProbabilities(),
        controller.getBot().getHand(), 2, 1, 0));
  }

  /**
   * Tests playing colour.
   * 
   * @author fkleinoe
   * @throws Exception e
   */
  @Test
  void testPlayColour() {
    assertEquals(1, General.playColour(controller.getCardProbabilities(),
        controller.getBot().getHand(), 2, -1, 0, false));
  }

  /**
   * Tests playing trump.
   * 
   * @author fkleinoe
   * @throws Exception e
   */
  @Test
  void testPlayTrump() {
    assertEquals(0, General.playTrump(controller.getPlayState().getPlayMode(),
        controller.getCardProbabilities(), controller.getBot().getHand(), -1, 0, -1, 0, false));
  }

  /**
   * Tests playing value.
   * 
   * @author fkleinoe
   * @throws Exception e
   */
  @Test
  void testPlayValue() {
    assertEquals(3, General.playValue(controller.getCardProbabilities(),
        controller.getBot().getHand(), -1, -1, 0, false));
  }

}
