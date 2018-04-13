package gui;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;

public class HelpDealController {

  @FXML
  private JFXButton back;

  private GuiController main;

  public HelpDealController() {
    this.main = new GuiController();
  }

  @FXML
  public void back() {
    main.displayHelp();
  }

}
