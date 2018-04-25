package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;

public class LobbyLocalController implements Initializable {

  private GuiController main;

  @FXML
  private MenuItem easy1, med1, dif1, easy2, med2, dif2, easy3, med3, dif3, dis3;
  @FXML
  MenuButton bot1, bot2, bot3;

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
  
  public void buttonHandler() {
//    yes.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
//      @Override
//      public void handle(MouseEvent e) {
//        ps.setHandGame(true);
//      }   
//    });
//    no.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
//      @Override
//      public void handle(MouseEvent e) {
//        ps.setHandGame(false);
//      }
//    });
  }

}
