/**
 * @author lstrauch
 */
package gui;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXButton.ButtonType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

/**
 * @author lstrauch
 *
 */
public class LobbyController implements Initializable{

  private JFXButton back;
  private JFXButton addBot;
  private JFXButton change;
  private static Label p2;
  private static Label p3;
  private static Label p4;

  @FXML
  private static Label p1;
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
  private static VBox vbox1;
  @FXML
  private VBox vbox2;



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

  public static void displayPlayers(int size, ArrayList<String> name) {
    switch (size) {
      case 1:
        displayOne(name.get(0));
        break;
      case 2:
        displayTwo(name.get(0), name.get(1));
        break;
      case 3: displayThree(name.get(0), name.get(1), name.get(2));
        break;
      case 4: displayFour(name.get(0), name.get(1), name.get(2), name.get(3));
        break;
    }
  }

  public static void displayOne(String name) {
    p1.setText(name);
  }

  public static void displayTwo(String name1, String name2) {
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

  public static void displayThree(String name1, String name2, String name3) {
    displayOne(name1);
    if(!vbox1.getChildren().contains(p2)) {
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

  public static void displayFour(String name1, String name2, String name3, String name4) {
    displayOne(name1);
    if(!vbox1.getChildren().contains(p2)) {
      displayTwo(name1, name2);
    }
    if(!vbox1.getChildren().contains(p3)) {
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

  /* (non-Javadoc)
   * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
   */
  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    // TODO Auto-generated method stub
    
  }
}
