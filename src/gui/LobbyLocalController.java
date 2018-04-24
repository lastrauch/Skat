package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

public class LobbyLocalController implements Initializable {

  private GuiController main;
  private MenuItem item;

  @FXML
  private MenuItem easy1, dif1, easy2, dif2, easy3, dif3, dis3;
  @FXML
  MenuButton eins, zwei, drei;

  public LobbyLocalController() {
    this.main = new GuiController();
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

  @FXML
  public void play() {
    main.displayInGame();
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    // TODO Auto-generated method stub

  }

  public void setButtonText1_Easy() {
    eins.setText("Easy");

  }

  public void setButtonText1_Difficult() {
    eins.setText("Difficult");

  }

  public void setButtonText2_Easy() {
    zwei.setText("Easy");
  }

  public void setButtonText2_Difficult() {
    zwei.setText("Difficult");
  }

  public void setButtonText3_Easy() {
    drei.setText("Easy");
  }

  public void setButtonText3_Difficult() {
    drei.setText("Difficult");
  }

  public void setButtonText3_Disabled() {
    drei.setText("Disabled");
  }

}
