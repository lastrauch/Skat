package gui;

import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class BettingController implements Initializable {

  private GuiController main;
  private String position;
  private int reizwert = 18;
  private boolean bettingDone = false;
  private boolean winner = false;

  @FXML
  private Label labelMitte;
  @FXML
  private JFXTextField betValue;
  @FXML
  private AnchorPane betPane;
  @FXML
  private JFXButton questionmark;
  @FXML
  private JFXButton pass;
  @FXML
  private JFXButton bet;
  @FXML
  private HBox buttonBox;

  public BettingController() {
    this.main = new GuiController();
    position = "Middlehand";
  }



  @FXML
  public void settings() {
    main.displaySettings();
  }

  @FXML
  public void help() {
    main.displayHelp();
  }

  @FXML
  public void accountSettings() {
    main.displayAccountSettings();
  }



  @Override
  public void initialize(URL location, ResourceBundle resources) {
    // TODO Auto-generated method stub
    // LogicGui.getPosition();
    labelMitte.setText(position);
    betValue.setText(String.valueOf(reizwert));
    showBettingValue();

  }

  @FXML
  public boolean bet() {
    // An Logik übergeben, dass auf bet gedrückt wurde
    // position += 2;
    betValue.setText(String.valueOf(reizwert += 2));
    if (reizwert == 22) {
      System.out.println("drin");
      bettingDone = true;
      main.displayInGame();
    }
    return true;
  }

  @FXML
  public boolean pass() {
    // An Logik übergeben, dass gepasst wurde

    if (!bettingDone) {
      System.out.println("drin");
      betPane.getChildren().remove(buttonBox);
    } else {
      if (winner) {

      }
      main.displayInGame();
    }
    return false;
  }

  public void showBettingValue() {
    betValue.setText(String.valueOf(reizwert));
  }

  // Gehört in Interfface!!!

  // public boolean askForBet(){
  // if(bet()){
  // bet();
  // return true;
  // } else{
  // pass();
  // return false;
  // }
  // }

}
