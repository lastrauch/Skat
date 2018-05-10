package junit.database;

import logic.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ImplementsGuiInterfaceTest {
  Player player;

  @BeforeEach
  void setUp() throws Exception {
    player = new Player("test");
  }

  @AfterEach
  void tearDown() throws Exception {}

  @Test
  void testInsertPlayer() {
    player.getName();
  }

  @Test
  void testCheckIfPlayerNew() {
    player.getName();
  }

  @Test
  void testGetPlayerPlayer() {
    player.getName();
  }

  @Test
  void testGetPlayerString() {
    player.getName();
  }

  @Test
  void testChangeName() {
    player.getName();
  }

}
