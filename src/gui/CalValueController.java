package gui;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;

public class CalValueController {

  @FXML
  private JFXButton back1;
  @FXML
  private JFXButton back2;
  @FXML
  private JFXButton back3;

  private GuiController main;

  /**
   * Constructor.
   * 
   * @author lstrauch
   */
  public CalValueController() {
    this.main = new GuiController();
  }

  /**
   * Displays the previous screen.
   * 
   * @author lstrauch
   */
  @FXML
  public void back() {
    main.displayHelp();
  }

}
