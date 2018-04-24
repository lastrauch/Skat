package gui;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;

public class HelpOverviewController {

  @FXML
  private JFXButton back;

  private GuiController main;

  public HelpOverviewController() {
    this.main = new GuiController();
  }

  @FXML
  public void back() {
    main.displayHelp();
  }

}
