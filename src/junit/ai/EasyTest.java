package junit.ai;

import ai.AiController;

import ai.Bot;
import ai.BotDifficulty;
import logic.ClientLogic;
import logic.GameSettings;
import logic.PlayState;
import logic.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class EasyTest {
  AiController aiEasy;
  static ClientLogic logic;
  static GameSettings gameSettings;
  static Bot bot;
  PlayState ps;


  @BeforeEach
  void setUp() throws Exception {
    logic = new ClientLogic(new Player("testname"));
    gameSettings = new GameSettings();
    aiEasy = new AiController(logic, "Name", BotDifficulty.EASY, gameSettings);
    bot = aiEasy.getBot();
    ps = new PlayState(null);
  }

  @Test
  void testAskForBet() {
    aiEasy.askForBet(18, null);
  }

  @Test
  void testAskToTakeUpSkat() {
    aiEasy.askToTakeUpSkat();
  }

  @Test
  void testSwitchSkat() {
    aiEasy.switchSkat(ps);
  }

  @Test
  void testAskToSetPlayState() {
    aiEasy.askToSetPlayState(ps);
  }

  @Test
  void testAskToRekontra() {
    aiEasy.askToRekontra();
  }

}
