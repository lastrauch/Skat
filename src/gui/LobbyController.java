/**
 * @author lstrauch
 */
package gui;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.GroupLayout.Alignment;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXButton.ButtonType;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import logic.GameMode;
import logic.GameSettings;
import logic.Player;

public class LobbyController implements Initializable {

  private JFXButton back = new JFXButton();
  private JFXButton addBot = new JFXButton();
  private JFXButton change = new JFXButton();
  Label p1 = new Label();
  Label p2 = new Label();
  Label p3 = new Label();
  Label p4 = new Label();
  private GameMode gm;
  private GuiController guiCon;
  private GameSettings gs;
  // private static GameSettings gs;
  private static int nrofplayers = 1;
  // private static List<Player> list = new ArrayList<Player>();
  private static boolean addedBot = false;

  @FXML
  private Label rounds;
  @FXML
  private Label system;
  @FXML
  private Label kontra;
  @FXML
  private Label timelimit;
  @FXML
  private AnchorPane mainPane;
  @FXML
  private VBox vbox1;
  @FXML
  private VBox vbox2;


  public LobbyController() {
    guiCon = new GuiController();
    GuiController.prevScreen = 4;
    // setGameSettingsLabel();
  }

  /*
   * (non-Javadoc)
   * 
   * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
   */
  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    // TODO Auto-generated method stub
    setGS();
    setGM();

  }

  @FXML
  public void back() {
    guiCon.displayLobbyOnline();
  }

  @FXML
  public void start() {
    // if(GuiController.prevScreen == 1 )
    LoginController.interfGL.startGame(gs);
    // LoginController.interfGL.hostGame("Hi", gs);
    // guiCon.displayInGame();
  }

  public void displayBackButton() {
    back.setPrefWidth(214);
    back.setPrefHeight(41);
    back.setLayoutX(550);
    back.setLayoutY(270);
    back.setText("Back");
    back.setFont(Font.font("System", FontWeight.BOLD, 18));
    back.setStyle(
        "-fx-background-color: peru; -fx-font-style: italic; -fx-border-radius: 20; -fx-background-radius: 20; -fx-border-color: 20; -fx-text-fill: white");
    back.setButtonType(ButtonType.RAISED);
    back.setTextAlignment(TextAlignment.CENTER);

    mainPane.getChildren().add(back);
  }

  public void displayAddBotButton() {
    addBot.setPrefWidth(214);
    addBot.setPrefHeight(41);
    addBot.setLayoutX(550);
    addBot.setLayoutY(162);
    addBot.setText("Add Bot");
    addBot.setFont(Font.font("System", FontWeight.BOLD, 18));
    addBot.setStyle(
        "-fx-background-color: peru; -fx-font-style: italic; -fx-border-radius: 20; -fx-background-radius: 20; -fx-border-color: 20; -fx-text-fill: white");
    addBot.setButtonType(ButtonType.RAISED);
    addBot.setTextAlignment(TextAlignment.CENTER);

    mainPane.getChildren().add(addBot);
  }

  public void deleteBotButton() {
    back.setPrefWidth(214);
    back.setPrefHeight(41);
    back.setLayoutX(550);
    back.setLayoutY(270);
    back.setText("Delte Bot");
    back.setFont(Font.font("System", FontWeight.BOLD, 18));
    back.setStyle(
        "-fx-background-color: peru; -fx-font-style: italic; -fx-border-radius: 20; -fx-background-radius: 20; -fx-border-color: 20; -fx-text-fill: white");
    back.setButtonType(ButtonType.RAISED);
    back.setTextAlignment(TextAlignment.CENTER);

    mainPane.getChildren().add(back);
  }

  public void displayChangeGamesettingsButton() {
    change.setPrefWidth(214);
    change.setPrefHeight(41);
    change.setLayoutX(550);
    change.setLayoutY(380);
    change.setText("Change Gamesettings");
    change.setFont(Font.font("System", FontWeight.BOLD, 18));
    change.setStyle(
        "-fx-background-color: peru; -fx-font-style: italic; -fx-border-radius: 20; -fx-background-radius: 20; -fx-border-color: 20; -fx-text-fill: white");
    change.setButtonType(ButtonType.RAISED);
    change.setTextAlignment(TextAlignment.CENTER);

    mainPane.getChildren().add(change);
  }


  public void displayPlayers(int size, List<Player> name) {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        // TODO Auto-generated method stub
        switch (size) {
          case 1:
            displayOne(name.get(0).getName());
            break;
          case 2:
            displayTwo(name.get(0).getName(), name.get(1).getName());
            break;
          case 3:
            displayThree(name.get(0).getName(), name.get(1).getName(), name.get(2).getName());
            nrofplayers = 3;
            break;
          case 4:
            displayFour(name.get(0).getName(), name.get(1).getName(), name.get(2).getName(),
                name.get(3).getName());
            break;
        }
      }

    });
    
  }

  public void displayOne(String name) {
    p1.setPrefWidth(213);
    p1.setPrefHeight(51);
    p1.setLayoutX(88);
    p1.setLayoutY(137);
    p1.setText(name);
    p1.setFont(Font.font("System", FontWeight.BOLD, 23));
    p1.setStyle("-fx-background-color: peru; -fx-font-style: italic; -fx-text-fill: white");
    p1.setAlignment(Pos.CENTER);

    System.out.println("ADDED ONE: " + p1.getText());

    vbox1.getChildren().add(p1);
  }

  public void displayTwo(String name1, String name2) {
    displayOne(name1);

    p2.setPrefWidth(213);
    p2.setPrefHeight(51);
    p2.setLayoutX(88);
    p2.setLayoutY(168);
    p2.setText(name2);
    p2.setFont(Font.font("System", FontWeight.BOLD, 23));
    p2.setStyle("-fx-background-color: peru; -fx-font-style: italic; -fx-text-fill: white");
    p2.setAlignment(Pos.CENTER);

    vbox1.getChildren().add(p2);
  }

  public void displayThree(String name1, String name2, String name3) {
    displayTwo(name1, name2);
    p3.setPrefWidth(213);
    p3.setPrefHeight(51);
    p3.setLayoutX(88);
    p3.setLayoutY(230);
    p3.setText(name3);
    p3.setFont(Font.font("System", FontWeight.BOLD, 23));
    p3.setStyle("-fx-background-color: peru; -fx-font-style: italic; -fx-text-fill: white");
    p3.setAlignment(Pos.CENTER);

    vbox1.getChildren().add(p3);
  }

  public void displayFour(String name1, String name2, String name3, String name4) {
    displayThree(name1, name2, name3);
    p4.setPrefWidth(213);
    p4.setPrefHeight(51);
    p4.setLayoutX(88);
    p4.setLayoutY(297);
    p4.setText(name4);
    p4.setFont(Font.font("System", FontWeight.BOLD, 23));
    p4.setStyle("-fx-background-color: peru; -fx-font-style: italic; -fx-text-fill: white");
    p4.setAlignment(Pos.CENTER);

    vbox1.getChildren().add(p4);
  }


  public void setGameSettingsLabel(GameSettings gs) {
    rounds.setText(String.valueOf(gs.getNrOfPlays()));
    system.setText(gs.getCountRule().toString());
    if (gs.isLimitedTime()) {
      timelimit.setText(String.valueOf(gs.getTimeLimit()));
    } else {
      timelimit.setText("Disabled");
    }
    if (gs.isEnableKontra()) {
      kontra.setText("Enabled");
    } else {
      kontra.setText("Disabled");
    }
  }

  public void setGS() {
    this.gs = guiCon.getGameSetCon().getGS();
  }

  @FXML
  public void addBot() {
    if (nrofplayers < 4) {
      guiCon.displayAI();
    }
  }


  public void changeGameSettings() {

  }

  public void delteBot(String botname) {
    LoginController.interfGL.deleteBot(botname);
  }

  /**
   * @author lstrauch
   */

  public void setGM() {
    this.gm = guiCon.getGameSetCon().getGM();
  }
}
