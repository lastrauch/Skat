package database;

import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import interfaces.GuiData;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import logic.Player;

public class ImplementsGuiInterface extends DatabaseHandler implements GuiData {

  /* (non-Javadoc)
   * @see interfaces.GuiData#getImage(java.lang.String, java.lang.String)
   */
  @Override
  public Image getImage(String colour, String number) {
    // TODO Auto-generated method stub
    Image img = null;
    try {
      selectCard.setString(1, colour);
      selectCard.setString(2, number);
      selectCard.execute();
      ResultSet rs = selectCard.executeQuery();
      while (rs.next()) {
        InputStream in = rs.getBinaryStream("image");
        // img = ImageIO.read(in);
        img = SwingFXUtils.toFXImage(ImageIO.read(in), null);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return img;
  }

  /* (non-Javadoc)
   * @see interfaces.GuiData#getImageDarker(java.lang.String, java.lang.String)
   */
  @Override
  public Image getImageDarker(String colour, String number) {
    // TODO Auto-generated method stub
    Image img = null;
    try {
      selectCardDarker.setString(1, colour);
      selectCardDarker.setString(2, number);
      selectCardDarker.execute();
      ResultSet rs = selectCardDarker.executeQuery();
      while (rs.next()) {
        InputStream in = rs.getBinaryStream("image");
        img = SwingFXUtils.toFXImage(ImageIO.read(in), null);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return img;
  }

  /* (non-Javadoc)
   * @see interfaces.GuiData#insertPlayer(logic.Player)
   */
  @Override
  public void insertPlayer(Player player) {
    // TODO Auto-generated method stub
    try {
      insertPlayer.setString(1, player.getName());
      insertPlayer.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    }
    System.out.println("New Player: " + player);
  }

  /* (non-Javadoc)
   * @see interfaces.GuiData#checkIfPlayerNew(java.lang.String)
   */
  @Override
  public boolean checkIfPlayerNew(String username) throws SQLException {
    // TODO Auto-generated method stub
    try {
      selectPlayerName.setString(1, username);
      ResultSet rs = selectPlayerName.executeQuery();
      if (rs.next()) {
        return false;
      } else {
        return true;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return true;
  }

  /* (non-Javadoc)
   * @see interfaces.GuiData#getPlayer(logic.Player)
   */
  @Override
  public Player getPlayer(Player player) {
    // TODO Auto-generated method stub
    try {
      selectPlayerName.setString(1, player.getName());
      selectPlayerName.executeQuery();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return player;
  }

  /* (non-Javadoc)
   * @see interfaces.GuiData#changeName(java.lang.String, logic.Player)
   */
  @Override
  public void changeName(String neu, Player original) {
    // TODO Auto-generated method stub
    try {
      changeName.setString(1, neu);
      changeName.setString(2, original.getName());
      changeName.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    }
    
  }

  /* (non-Javadoc)
   * @see interfaces.GuiData#changeImage(logic.Player, javafx.scene.image.Image)
   */
  @Override
  public void changeImage(Player player, Image image) {
    // TODO Auto-generated method stub
    System.out.println("empty");
  }



}
