package jUnit.Database;

import logic.Player;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ImplementsGuiInterfaceTest {
  Player player;

  @BeforeAll
  static void setUpBeforeClass() throws Exception {}

  @AfterAll
  static void tearDownAfterClass() throws Exception {}

  @BeforeEach
  void setUp() throws Exception {
    player = new Player("test");
  }

  @AfterEach
  void tearDown() throws Exception {}

  @Test
  void testGetImage() {
    player.getImage();
  }

  @Test
  void testGetImageDarker() {
    player.getImage();
  }

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
  void testChangeName() {
    player.getName();
  }

  @Test
   void testChangeImage() {
    player.getImage();
  }

  @Test
  void testGetPlayerString() {
    player.getName();
  }

}
