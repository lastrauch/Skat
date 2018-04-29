package gui;
import javafx.fxml.FXML;

public class LeaderboardController {

  /**
   * @author lstrauch
   */
  private GuiController main;

  /**
   *@author lstrauch
   */
  public LeaderboardController() {
    this.main = new GuiController();
  }

  /**
   * @author lstrauch
   */
  @FXML
  public void back() {
    main.displayChooseGame();
  }

}
