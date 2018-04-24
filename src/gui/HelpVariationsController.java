package gui;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;

public class HelpVariationsController {

  @FXML
  private JFXButton back;

  private GuiController main;

  public HelpVariationsController() {
    this.main = new GuiController();
  }

  @FXML
  public void back() {
    main.displayHelp();
  }

}
