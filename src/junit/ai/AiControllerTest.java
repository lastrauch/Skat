package junit.ai;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import ai.AiController;
import ai.Bot;
import ai.BotDifficulty;
import ai.SinglePlay;
import java.util.ArrayList;
import java.util.List;
import logic.Card;
import logic.ClientLogic;
import logic.GameSettings;
import logic.PlayMode;
import logic.PlayState;
import logic.Player;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

  @BeforeAll
  static void setUpBeforeClass() throws Exception {
    logic = new ClientLogic(new Player("Felix"));
    gameSettings = new GameSettings();
    controller = new AiController(logic, "Name", BotDifficulty.EASY, gameSettings);
    bot = controller.getBot();
  }

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

  @Test
  void testReceivedNewBet() {
    Player p = new Player("1");
    p.setId(1);
    List<Player> after = new ArrayList<Player>();
    after.add(p);

    assertEquals(after.size() + 1, controller.getPlayer().size());
    controller.askForBet(18, p);
    assertEquals(p.getName(), Integer.toString(controller.getPlayer().get(1).getId()));
  }

  @Test
  void testReceivedNewCard() {
    //playedCard
    //Probabilities
    //hasColour
    //existingTrump
    //hasTrump
  }

  @Test
  void testSetPlaySettingsAfterAuction() {
    //Check partner and opponents
    Player partner = new Player("Partner");
    partner.setId(200);
    Player opponent = new Player("Opponent");
    opponent.setId(300);
    opponent.setDeclarer(true);
    controller.askForBet(18, partner);
    controller.askForBet(20, opponent);
    Player[] group = new Player[3];
    PlayState playState = new PlayState(controller.getPlayer().toArray(group));
    playState.setPlayMode(PlayMode.GRAND);
    
    controller.setPlaySettingsAfterAuction(playState);
    
    assertEquals(partner.getId(), controller.getPartner().getName());
  }

}
