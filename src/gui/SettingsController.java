package gui;

import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class SettingsController implements Initializable {

  /**
   * non-FXML attributes.
   * 
   * @author lstrauch
   */
  private GuiController main;
  private boolean trainingmode = false;


  /**
   * FXML attributes.
   * 
   * @author lstrauch
   */
  @FXML
  private JFXButton on;
  @FXML
  private JFXButton off;

  /**
   * Constructor.
   * 
   * @author lstrauch
   */
  public SettingsController() {
    this.main = new GuiController();
  }

  /**
   * goes back to previous screen.
   * 
   * @author lstrauch
   */
  @FXML
  public void back() {
    LoginController.displayPrev();
  }

  /**
   * turns on trainingsmode.
   * 
   * @author lstrauch
   */
  @FXML
  public void on() {
    this.trainingmode = true;
    on.setStyle("-fx-background-color: green");
  }

  /**
   * turns off trainingsmode.
   * 
   * @author lstrauch
   */
  @FXML
  public void off() {
    this.trainingmode = false;
    off.setStyle("-fx-background-color: red");
  }

  public boolean getTrainingsmode() {
    return trainingmode;
  }

  /*
   * (non-Javadoc)
   * 
   * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
   */
  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    // TODO Auto-generated method stub

  }
}
