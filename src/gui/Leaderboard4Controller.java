package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Leaderboard4Controller extends Thread implements Initializable {

  /**
   * initialize non-FXML attributes.
   * 
   * @author lstrauch
   */
  private GuiController main;
  List<Label> roundsNr = new ArrayList<Label>();
  List<Label> rounds1 = new ArrayList<Label>();
  List<Label> rounds2 = new ArrayList<Label>();
  List<Label> rounds3 = new ArrayList<Label>();
  List<Label> rounds4 = new ArrayList<Label>();


  @FXML
  private VBox vboxNr;
  @FXML
  private VBox vbox1;
  @FXML
  private VBox vbox2;
  @FXML
  private VBox vbox3;
  @FXML 
  private VBox vbox4;

  /**
   * Constructor.
   * 
   * @author lstrauch
   */
  public Leaderboard4Controller() {
    this.main = new GuiController();
  }

  /**
   * go back to gameMode- screen.
   * 
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
    // run();
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        showLeaderboard();
      }
    });
  }

  /**
   * shows score.
   * 
   * @author lstrauch
   */
  public void showLeaderboard() {

    for (int i = 0; i < main.getGameSetCon().getGs().getNrOfPlays() + 1; i++) {
      roundsNr.add(new Label());
      rounds1.add(new Label());
      rounds2.add(new Label());
      rounds3.add(new Label());
      rounds4.add(new Label());
    }

    roundsNr.get(0).setText("Nr.");
    roundsNr.get(0).setFont(Font.font("System", 33));
    roundsNr.get(0).setTextFill(Color.WHITE);
    AnchorPane.setLeftAnchor(roundsNr.get(0), 0.0);
    AnchorPane.setRightAnchor(roundsNr.get(0), 0.0);
    roundsNr.get(0).setAlignment(Pos.CENTER);

    rounds1.get(0).setText(main.getInGameCon().getPlayer1().getName());
    rounds1.get(0).setFont(Font.font("System", 33));
    rounds1.get(0).setTextFill(Color.WHITE);
    AnchorPane.setLeftAnchor(rounds1.get(0), 0.0);
    AnchorPane.setRightAnchor(rounds1.get(0), 0.0);
    rounds1.get(0).setAlignment(Pos.CENTER);

    rounds2.get(0).setText(main.getInGameCon().getPlayer2().getName());
    rounds2.get(0).setFont(Font.font("System", 33));
    rounds2.get(0).setTextFill(Color.WHITE);
    AnchorPane.setLeftAnchor(rounds2.get(0), 0.0);
    AnchorPane.setRightAnchor(rounds2.get(0), 0.0);
    rounds2.get(0).setAlignment(Pos.CENTER);


    rounds3.get(0).setText(main.getInGameCon().getPlayer3().getName());
    rounds3.get(0).setFont(Font.font("System", 33));
    rounds3.get(0).setTextFill(Color.WHITE);
    AnchorPane.setLeftAnchor(rounds3.get(0), 0.0);
    AnchorPane.setRightAnchor(rounds3.get(0), 0.0);
    rounds3.get(0).setAlignment(Pos.CENTER);

    rounds4.get(0).setText(main.getInGameCon().getPlayer4().getName());
    rounds4.get(0).setFont(Font.font("System", 33));
    rounds4.get(0).setTextFill(Color.WHITE);
    AnchorPane.setLeftAnchor(rounds4.get(0), 0.0);
    AnchorPane.setRightAnchor(rounds4.get(0), 0.0);
    rounds4.get(0).setAlignment(Pos.CENTER);

    vboxNr.getChildren().add(roundsNr.get(0));
    vbox1.getChildren().add(rounds1.get(0));
    vbox2.getChildren().add(rounds2.get(0));
    vbox3.getChildren().add(rounds3.get(0));
    vbox4.getChildren().add(rounds4.get(0));

    System.out.println("Size: " + main.getInGameCon().getPlayer1().getPlayScore().size());
    for (int i = 0; i < main.getInGameCon().getPlayer1().getPlayScore().size(); i++) {
      System.out.println("in forschleife");
      int count = i + 1;
      roundsNr.get(i).setText(String.valueOf(count));
      roundsNr.get(i + 1).setFont(Font.font("System", 33));
      roundsNr.get(i + 1).setTextFill(Color.WHITE);
      AnchorPane.setLeftAnchor(roundsNr.get(i + 1), 0.0);
      AnchorPane.setRightAnchor(roundsNr.get(i + 1), 0.0);
      roundsNr.get(i + 1).setAlignment(Pos.CENTER);
      vboxNr.getChildren().add(roundsNr.get(i + 1));

      int points11 = LoginController.interfGL.getPlayer().getPlayScore().get(i);
      rounds1.get(i + 1).setText(String.valueOf(points11));
      rounds1.get(i + 1).setFont(Font.font("System", 33));
      if (points11 < 0) {
        rounds1.get(i + 1).setTextFill(Color.RED);
      }
      if (points11 > 0) {
        rounds1.get(i + 1).setTextFill(Color.GREEN);
      }
      if (points11 == 0) {
        rounds1.get(i + 1).setTextFill(Color.WHITE);
      }
      AnchorPane.setLeftAnchor(rounds1.get(i + 1), 0.0);
      AnchorPane.setRightAnchor(rounds1.get(i + 1), 0.0);
      rounds1.get(i + 1).setAlignment(Pos.CENTER);
      vbox1.getChildren().add(rounds1.get(i + 1));

      int points21 = main.getInGameCon().getPlayer1().getPlayScore().get(i);
      rounds2.get(i + 1).setText(String.valueOf(points21));
      rounds2.get(i + 1).setFont(Font.font("System", 33));
      if (points21 < 0) {
        rounds2.get(i + 1).setTextFill(Color.RED);
      }
      if (points21 > 0) {
        rounds2.get(i + 1).setTextFill(Color.GREEN);
      }
      if (points21 == 0) {
        rounds2.get(i + 1).setTextFill(Color.WHITE);
      }
      AnchorPane.setLeftAnchor(rounds2.get(i + 1), 0.0);
      AnchorPane.setRightAnchor(rounds2.get(i + 1), 0.0);
      rounds2.get(i + 1).setAlignment(Pos.CENTER);
      vbox2.getChildren().add(rounds2.get(i + 1));

      int points31 = main.getInGameCon().getPlayer2().getPlayScore().get(i);
      rounds3.get(i + 1).setText(String.valueOf(points31));
      rounds3.get(i + 1).setFont(Font.font("System", 33));
      if (points31 < 0) {
        rounds3.get(i + 1).setTextFill(Color.RED);
      }
      if (points31 > 0) {
        rounds3.get(i + 1).setTextFill(Color.GREEN);
      }
      if (points31 == 0) {
        rounds3.get(i + 1).setTextFill(Color.WHITE);
      }
      AnchorPane.setLeftAnchor(rounds3.get(i + 1), 0.0);
      AnchorPane.setRightAnchor(rounds3.get(i + 1), 0.0);
      rounds3.get(i + 1).setAlignment(Pos.CENTER);
      vbox3.getChildren().add(rounds3.get(i + 1));

      int points41 = main.getInGameCon().getPlayer2().getPlayScore().get(i);
      rounds4.get(i + 1).setText(String.valueOf(points41));
      rounds4.get(i + 1).setFont(Font.font("System", 33));
      if (points41 < 0) {
        rounds4.get(i + 1).setTextFill(Color.RED);
      }
      if (points41 > 0) {
        rounds4.get(i + 1).setTextFill(Color.GREEN);
      }
      if (points41 == 0) {
        rounds4.get(i + 1).setTextFill(Color.WHITE);
      }
      AnchorPane.setLeftAnchor(rounds4.get(i + 1), 0.0);
      AnchorPane.setRightAnchor(rounds4.get(i + 1), 0.0);
      rounds4.get(i + 1).setAlignment(Pos.CENTER);
      vbox4.getChildren().add(rounds4.get(i + 1));
    }



  }

}

