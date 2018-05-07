/**
 * @author lstrauch
 */
package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * @author lstrauch
 *
 */
public class Leaderboard4Controller implements Initializable {

  /**
   * @author lstrauch
   */
  private GuiController main;


  @FXML
  private VBox vBoxNr, vBox1, vBox2, vBox3, vBox4;

  /**
   * @author lstrauch
   */
  public Leaderboard4Controller() {
    this.main = new GuiController();
  }

  /**
   * @author lstrauch
   */
  @FXML
  public void back() {
    main.displayChooseGame();
  }

  /*
   * (non-Javadoc)
   * 
   * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
   */
  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    // TODO Auto-generated method stub
    showLeaderboard();

  }

  public void showLeaderboard() {
    List<Label> roundsNr = new ArrayList<Label>();
    List<Label> rounds1 = new ArrayList<Label>();
    List<Label> rounds2 = new ArrayList<Label>();
    List<Label> rounds3 = new ArrayList<Label>();
    List<Label> rounds4 = new ArrayList<Label>();


    for (int i = 0; i < main.getGameSetCon().getGS().getNrOfPlays() + 1; i++) {
      rounds1.add(new Label());
      rounds2.add(new Label());
      rounds3.add(new Label());
      rounds4.add(new Label());
      roundsNr.add(new Label());
    }

    System.out.println("Name layer1 Leaderboard: " + main.getInGameCon().getPlayer1().getName());
    System.out.println("Name player2 Leaderboard: " + main.getInGameCon().getPlayer2().getName());
    System.out.println(LoginController.interfGL.getPlayer().getPlayScore().get(1));
    roundsNr.get(0).setText("Nr.");
    rounds1.get(0).setText("Me");
    rounds2.get(0).setText(main.getInGameCon().getPlayer1().getName());
    rounds3.get(0).setText(main.getInGameCon().getPlayer2().getName());
    rounds4.get(0).setText(main.getInGameCon().getPlayer3().getName());
    vBoxNr.getChildren().add(roundsNr.get(0));
    vBox1.getChildren().add(rounds1.get(0));
    vBox2.getChildren().add(rounds2.get(0));
    vBox3.getChildren().add(rounds3.get(0));
    vBox4.getChildren().add(rounds4.get(0));

    for (int i = 1; i < roundsNr.size(); i++) {
      int count = i;
      roundsNr.get(i).setText(String.valueOf(count));
      roundsNr.get(i).setFont(Font.font("System", 33));
      roundsNr.get(i).setTextFill(Color.WHITE);
      AnchorPane.setLeftAnchor(roundsNr.get(i), 0.0);
      AnchorPane.setRightAnchor(roundsNr.get(i), 0.0);
      roundsNr.get(i).setAlignment(Pos.CENTER);
      vBoxNr.getChildren().add(roundsNr.get(i));

    }
    if (rounds1.size() > 1) {
      for (int i = 1; i < rounds1.size(); i++) {
        int points = LoginController.interfGL.getPlayer().getPlayScore().get(i);
        rounds1.get(i)
            .setText(String.valueOf(LoginController.interfGL.getPlayer().getPlayScore().get(i)));
        rounds1.get(i).setFont(Font.font("System", 33));
        if (points < 0) {
          rounds1.get(i).setTextFill(Color.RED);
        }
        if (points > 0) {
          rounds1.get(i).setTextFill(Color.GREEN);
        }
        if (points == 0) {
          rounds1.get(i).setTextFill(Color.WHITE);
        }
        AnchorPane.setLeftAnchor(rounds1.get(i), 0.0);
        AnchorPane.setRightAnchor(rounds1.get(i), 0.0);
        rounds1.get(i).setAlignment(Pos.CENTER);
        vBox1.getChildren().add(rounds1.get(i));
      }
    }

    if (rounds2.size() > 1) {
      for (int i = 1; i < rounds2.size(); i++) {
        int points = LoginController.interfGL.getPlayer().getPlayScore().get(i);
        rounds2.get(i)
            .setText(String.valueOf(LoginController.interfGL.getPlayer().getPlayScore().get(i)));
        rounds2.get(i).setFont(Font.font("System", 33));
        if (points < 0) {
          rounds2.get(i).setTextFill(Color.RED);
        }
        if (points > 0) {
          rounds2.get(i).setTextFill(Color.GREEN);
        }
        if (points == 0) {
          rounds2.get(i).setTextFill(Color.WHITE);
        }
        AnchorPane.setLeftAnchor(rounds2.get(i), 0.0);
        AnchorPane.setRightAnchor(rounds2.get(i), 0.0);
        rounds2.get(i).setAlignment(Pos.CENTER);
        vBox2.getChildren().add(rounds2.get(i));
      }
    }

    if (rounds3.size() > 1) {
      for (int i = 1; i < rounds3.size(); i++) {
        int points = LoginController.interfGL.getPlayer().getPlayScore().get(i);
        rounds3.get(i)
            .setText(String.valueOf(LoginController.interfGL.getPlayer().getPlayScore().get(i)));
        rounds3.get(i).setFont(Font.font("System", 33));
        if (points < 0) {
          rounds3.get(i).setTextFill(Color.RED);
        }
        if (points > 0) {
          rounds3.get(i).setTextFill(Color.GREEN);
        }
        if (points == 0) {
          rounds3.get(i).setTextFill(Color.WHITE);
        }

        AnchorPane.setLeftAnchor(rounds3.get(i), 0.0);
        AnchorPane.setRightAnchor(rounds3.get(i), 0.0);
        rounds3.get(i).setAlignment(Pos.CENTER);
        vBox3.getChildren().add(rounds3.get(i));
      }
    }
    if (rounds4.size() > 1) {
      for (int i = 1; i < rounds4.size(); i++) {
        int points = LoginController.interfGL.getPlayer().getPlayScore().get(i);
        rounds4.get(i)
            .setText(String.valueOf(LoginController.interfGL.getPlayer().getPlayScore().get(i)));
        rounds4.get(i).setFont(Font.font("System", 33));
        if (points < 0) {
          rounds4.get(i).setTextFill(Color.RED);
        }
        if (points > 0) {
          rounds3.get(i).setTextFill(Color.GREEN);
        }
        if (points == 0) {
          rounds3.get(i).setTextFill(Color.WHITE);
        }

        AnchorPane.setLeftAnchor(rounds3.get(i), 0.0);
        AnchorPane.setRightAnchor(rounds3.get(i), 0.0);
        rounds3.get(i).setAlignment(Pos.CENTER);
        vBox3.getChildren().add(rounds3.get(i));
      }
    }


  }

}
