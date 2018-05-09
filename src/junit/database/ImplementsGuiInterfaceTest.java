package junit.database;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ImplementsGuiInterfaceTest {

  @BeforeAll
  static void setUpBeforeClass() throws Exception {}

  @AfterAll
  static void tearDownAfterClass() throws Exception {}

  @BeforeEach
  void setUp() throws Exception {}

  @AfterEach
  void tearDown() throws Exception {}

  @Test
  void testGetImage() {
    fail("Not yet implemented");
  }

  @Test
  void testGetImageDarker() {
    fail("Not yet implemented");
  }

  @Test
  void testInsertPlayer() {
    fail("Not yet implemented");
  }

  @Test
  void testCheckIfPlayerNew() {
    fail("Not yet implemented");
  }

  @Test
  void testGetPlayerPlayer() {
    fail("Not yet implemented");
  }

  @Test
  void testChangeName() {
    fail("Not yet implemented");
  }

  @Test
  void testChangeImage() {
    fail("Not yet implemented");
  }

  @Test
  void testGetPlayerString() {
    fail("Not yet implemented");
=======
import logic.Player;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ImplementsGuiInterfaceTest {
  Player player;

  @BeforeEach
  void setUp() throws Exception {
    player = new Player("test");
  }

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
  void testGetPlayerString() {
    player.getName();
>>>>>>> refs/heads/database
  }

}
