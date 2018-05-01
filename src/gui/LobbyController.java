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
  private Label p1 = new Label();
  private Label p2 = new Label();
  private Label p3 = new Label();
  private Label p4 = new Label();
  private GuiController guiCon;
  private GameSettings gs;
  private GameMode gm;
//  private static GameSettings gs;
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
//    setGameSettingsLabel();
  }


  public void setGameSettings(GameSettings gs) {
    this.gs = gs;
  }
  
//  public GameSettings getGameSettings() {
//    return this.gs;
//  }
  
  @FXML
  public void back() {
    guiCon.displayLobbyOnline();
  }
  
  @FXML
  public void start() {
//    if(GuiController.prevScreen == 1 )
    LoginController.interfGL.startGame(gs);
//      LoginController.interfGL.hostGame("Hi", gs);
//      guiCon.displayInGame();
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
    switch (size) {
      case 1:
        displayOne(name.get(0).getName());
        nrofplayers = 1;
        break;
      case 2:
        displayTwo(name.get(0).getName(), name.get(1).getName());
        nrofplayers = 2;
        break;
      case 3:
        displayThree(name.get(0).getName(), name.get(1).getName(), name.get(2).getName());
        nrofplayers = 3;
        break;
      case 4:
        displayFour(name.get(0).getName(), name.get(1).getName(), name.get(2).getName(),
            name.get(3).getName());
        nrofplayers = 4;
        break;
    }
  }

  public void displayOne(String name) {
    p1.setPrefWidth(213);
    p1.setPrefHeight(51);
    p1.setLayoutX(88);
    p1.setLayoutY(297);
    p1.setText("Bot");
    p1.setFont(Font.font("System", FontWeight.BOLD, 23));
    p1.setStyle("-fx-background-color: peru; -fx-font-style: italic; -fx-text-fill: white");
    p1.setTextAlignment(TextAlignment.CENTER);

    vbox1.getChildren().add(p4);
    
 
    
    p1.setText(name);
  }

  public void displayTwo(String name1, String name2) {  
    displayOne(name1);
    p1.setText(name1);

    p2.setPrefWidth(213);
    p2.setPrefHeight(51);
    p2.setLayoutX(88);
    p2.setLayoutY(168);
    p2.setText(name2);
    p2.setFont(Font.font("System", FontWeight.BOLD, 23));
    p2.setStyle("-fx-background-color: peru; -fx-font-style: italic; -fx-text-fill: white");
    p2.setTextAlignment(TextAlignment.CENTER);

    vbox1.getChildren().add(p2);
  }

  public void displayThree(String name1, String name2, String name3) {
    displayOne(name1);
    if (!vbox1.getChildren().contains(p2)) {
      displayTwo(name1, name2);
    }
    p3.setPrefWidth(213);
    p3.setPrefHeight(51);
    p3.setLayoutX(88);
    p3.setLayoutY(230);
    p3.setText(name3);
    p3.setFont(Font.font("System", FontWeight.BOLD, 23));
    p3.setStyle("-fx-background-color: peru; -fx-font-style: italic; -fx-text-fill: white");
    p3.setTextAlignment(TextAlignment.CENTER);

    vbox1.getChildren().add(p3);
  }

  public void displayFour(String name1, String name2, String name3, String name4) {
    displayOne(name1);
    if (!vbox1.getChildren().contains(p2)) {
      displayTwo(name1, name2);
    }
    if (!vbox1.getChildren().contains(p3)) {
      displayThree(name1, name2, name3);
    }
    p4.setPrefWidth(213);
    p4.setPrefHeight(51);
    p4.setLayoutX(88);
    p4.setLayoutY(297);
    p4.setText(name4);
    p4.setFont(Font.font("System", FontWeight.BOLD, 23));
    p4.setStyle("-fx-background-color: peru; -fx-font-style: italic; -fx-text-fill: white");
    p4.setTextAlignment(TextAlignment.CENTER);

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
  /*
   * (non-Javadoc)
   * 
   * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
   */
  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    // TODO Auto-generated method stub
    
    setGameSettingsLabel(guiCon.getGameSetCon().getGS());
    setGS();
    if (addedBot) {
      switch (nrofplayers) {
        case 2:                    
          p2.setPrefWidth(213);
          p2.setPrefHeight(51);
          p2.setText("Computer 2");
          p2.setFont(Font.font("System", FontWeight.BOLD, 23));
          p2.setStyle("-fx-background-color: peru; -fx-font-style: italic; -fx-text-fill: white");
          p2.setAlignment(Pos.CENTER);
          
          System.out.println("Bot2: Easy");

          vbox1.getChildren().add(p2);
          break;
        case 3:
          p2.setPrefWidth(213);
          p2.setPrefHeight(51);
          p2.setText("Computer 2");
          p2.setFont(Font.font("System", FontWeight.BOLD, 23));
          p2.setStyle("-fx-background-color: peru; -fx-font-style: italic; -fx-text-fill: white");
          p2.setAlignment(Pos.CENTER);
          
          p3.setPrefWidth(213);
          p3.setPrefHeight(51);
          p3.setText("Computer 3");
          p3.setFont(Font.font("System", FontWeight.BOLD, 23));
          p3.setStyle("-fx-background-color: peru; -fx-font-style: italic; -fx-text-fill: white");
          p3.setTextAlignment(TextAlignment.CENTER);

          vbox1.getChildren().add(p2);
          vbox1.getChildren().add(p3);
          
          System.out.println("Bot 3");
          break;
        case 4:
          p2.setPrefWidth(213);
          p2.setPrefHeight(51);
          p2.setText("Computer 2");
          p2.setFont(Font.font("System", FontWeight.BOLD, 23));
          p2.setStyle("-fx-background-color: peru; -fx-font-style: italic; -fx-text-fill: white");
          p2.setAlignment(Pos.CENTER);
          
          p3.setPrefWidth(213);
          p3.setPrefHeight(51);
          p3.setText("Computer 3");
          p3.setFont(Font.font("System", FontWeight.BOLD, 23));
          p3.setStyle("-fx-background-color: peru; -fx-font-style: italic; -fx-text-fill: white");
          p3.setAlignment(Pos.CENTER);
          
          p4.setPrefWidth(213);
          p4.setPrefHeight(51);
          p4.setText("Computer 4");
          p4.setFont(Font.font("System", FontWeight.BOLD, 23));
          p4.setStyle("-fx-background-color: peru; -fx-font-style: italic; -fx-text-fill: white");
          p4.setAlignment(Pos.CENTER);

          vbox1.getChildren().add(p2);
          vbox1.getChildren().add(p3);
          vbox1.getChildren().add(p4);
          System.out.println("Bot 4");
          break;
      }
    }

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

  public static void setBot(boolean added) {
    addedBot = added;
    nrofplayers++;
    System.out.println(nrofplayers);
  }

  /**
   * @author lstrauch
   */
 
  public void setMode(GameMode gm) {
    this.gm = gm;
  }
}
