package gui;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;

public class HelpCardsController {

  @FXML
  private JFXButton back1;
  @FXML
  private JFXButton back2;
  @FXML
  private JFXButton back3;

  private GuiController main;

  public HelpCardsController() {
    this.main = new GuiController();
  }

  @FXML
  public void back() {
    main.displayHelp();
  }

}
