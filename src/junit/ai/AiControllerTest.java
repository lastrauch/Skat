package junit.ai;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import ai.AiController;
import ai.Bot;
import ai.BotDifficulty;
import ai.SinglePlay;
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
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests methods of the AiController class.
 * 
 * @author fkleinoe
 */
class AiControllerTest {
  static AiController controller;
  static ClientLogic logic;
  static GameSettings gameSettings;
  static Bot bot;
  PlayState playState;
  List<Player> player;
  List<Player> opponents;
  Player partner;
  int[] bets;
  int maxBet;
  SinglePlay singlePlay;
  Card[][] playedCards;
  double[][] cardProbability;
  boolean[][] hasColour;
  boolean[] hasTrump;
  int existingTrumps;
  List<Card> currentTrick;

  /**
   * Set up before class.
   * 
   * @author fkleinoe
   * @throws Exception e
   */
  @BeforeAll
  static void setUpBeforeClass() throws Exception {
    logic = new ClientLogic(new Player("Felix"));
    gameSettings = new GameSettings();
    controller = new AiController(logic, "Name", BotDifficulty.EASY, gameSettings);
    bot = controller.getBot();
  }

  /**
   * Set up before each method.
   * 
   * @author fkleinoe
   * @throws Exception e
   */
  @BeforeEach
  void setUp() throws Exception {
    player = new ArrayList<Player>();
    player.add(bot);
    controller.setPlayer(player);

    opponents = new ArrayList<Player>();
    bets = new int[3];
    playedCards = new Card[1][3];
    cardProbability = new double[32][3];
    hasColour = new boolean[4][3];
    hasTrump = new boolean[3];
    currentTrick = new ArrayList<Card>();
  }


  /**
   * Check if player are added correctly.
   * 
   * @author fkleinoe
   */
  @Test
  void testAskForBet() {
    Player p = new Player("1");
    p.setId(1);
    List<Player> after = new ArrayList<Player>();
    after.add(p);

    assertEquals(after.size(), controller.getPlayer().size());
    controller.askForBet(18, p);
    assertEquals(p.getName(), Integer.toString(controller.getPlayer().get(1).getId()));
  }

  /**
   * Check if player are added correctly.
   * 
   * @author fkleinoe
   */
  @Test
  void testReceivedNewBet() {
    Player p = new Player("1");
    p.setId(1);
    List<Player> after = new ArrayList<Player>();
    after.add(p);

    assertEquals(after.size(), controller.getPlayer().size());
    controller.askForBet(18, p);
    assertEquals(p.getName(), Integer.toString(controller.getPlayer().get(1).getId()));
  }

  /**
   * Check for different observations by player p playing card c.
   * 
   * @author fkleinoe
   */
  @Test
  void testReceivedNewCard() {
    Player partner = new Player("Partner");
    partner.setId(200);
    Player opponent = new Player("Opponent");
    opponent.setId(300);
    opponent.setDeclarer(true);
    // Ask for bet just to set correct ids
    controller.askForBet(18, partner);
    controller.askForBet(20, opponent);
    Player[] group = new Player[3];
    group[0] = bot;
    group[1] = partner;
    group[2] = opponent;
    PlayState playState = new PlayState(group);
    playState.setPlayMode(PlayMode.GRAND);
    List<Card> currentTrick = new ArrayList<Card>();
    currentTrick.add(new Card(Colour.HEARTS, Number.TEN));
    controller.setCurrentTrick(currentTrick);
    controller.setExistingTrumps(4);

    Card card = new Card(Colour.SPADES, Number.JACK);
    int colour = 3 - Colour.SPADES.ordinal();
    int number = 7 - Number.JACK.ordinal();
    controller.receivedNewCard(card, opponent);
    // playedCard
    assertEquals(card, controller.getPlayedCards()[controller.getPlayedCards().length - 1][2]);

    // Card probability
    assertEquals(0, controller.getCardProbabilities()[colour * 8 + number][2], 0);

    // hasColour
    int serveColour = 3 - Colour.HEARTS.ordinal();
    assertFalse(controller.getHasColour()[serveColour][2]);

    // hasTrump
    assertTrue(controller.getHasTrump()[2]);
    
    // existingTrumps
    assertEquals(3, controller.getExistingTrumps());
  }

  /**
   * Check if opponent and partner are set correctly.
   * 
   * @author fkleinoe
   */
  @Test
  void testSetPlaySettingsAfterAuction() {
    // Check partner and opponents
    Player partner = new Player("Partner");
    partner.setId(200);
    Player opponent = new Player("Opponent");
    opponent.setId(300);
    opponent.setDeclarer(true);
    Player[] group = new Player[3];
    group[0] = bot;
    group[1] = partner;
    group[2] = opponent;
    // Ask for bet just to set correct ids
    controller.askForBet(18, partner);
    controller.askForBet(20, opponent);

    PlayState playState = new PlayState(group);
    playState.setPlayMode(PlayMode.GRAND);

    controller.setPlaySettingsAfterAuction(playState);
    assertEquals(Integer.toString(partner.getId()), controller.getPartner().getName());
  }

}
