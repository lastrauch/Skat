package gui;

import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXRadioButton;
import ai.BotDifficulty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logic.GameMode;

public class SetAiController implements Initializable {

  /**
   * non-FXML attributes.
   * 
   * @author lstrauch
   */
  private GuiController main;
  private Label label = new Label();
  private BotDifficulty dif;
  private int countBot = 0;
  private StringBuilder anz = new StringBuilder();


  /**
   * FXML attributes.
   * 
   * @author lstrauch
   */
  @FXML
  private JFXRadioButton easy3;
  @FXML
  private JFXRadioButton med3;
  @FXML
  private JFXRadioButton dis3;
  @FXML
  Label bot3;
  @FXML
  Label l3;
  @FXML
  AnchorPane p;

  /**
   * Constructor.
   * 
   * @author lstrauch
   */
  public SetAiController() {
    this.main = new GuiController();
    anz.append("Bot ");
    GuiController.prevScreen = 5;
  }

  /**
   * display settings-screen.
   * 
   * @author lstrauch
   */
  @FXML
  public void settings() {
    main.displaySettings();
  }

  /**
   * display help-screen.
   * 
   * @author lstrauch
   */
  @FXML
  public void help() {
    main.displayHelp();
  }

  /**
   * display accountSettings-screen.
   * 
   * @author lstrauch
   */
  @FXML
  public void accountSettings() {
    main.displayAccountSettings();
  }

  /**
   * display lobby-screen. sets ai in logic.
   * 
   * @author lstrauch
   */
  @FXML
  public void ok() {
    anz.append(String.valueOf(main.getLobbyCon().countBot() - 1));
    main.displayLobby();
    LoginController.interfGL.setBot(anz.toString(), dif);
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    // TODO Auto-generated method stub
    buttonHandler();
    alreadyClicked();

  }


  /**
   * enables buttonHandler.
   * 
   * @author lstrauch
   */
  public void buttonHandler() {
    easy3.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        if (!(l3.getText().equals("Disabled") || l3.getText().equals(""))) {
          LoginController.interfGL.deleteBot("Bot3");
          System.out.println("Delete b3");
        }
        dif = BotDifficulty.EASY;
        l3.setText("Easy");
      }
    });
    med3.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        if (!(l3.getText().equals("Disabled") || l3.getText().equals(""))) {
          LoginController.interfGL.deleteBot("Bot3");
          System.out.println("Delete b3");
        }
        dif = BotDifficulty.MEDIUM;
        l3.setText("Medium");
      }
    });
    dis3.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        if (!(l3.getText().equals("Disabled") || l3.getText().equals(""))) {
          l3.setText("Disabled");
        }
      }
    });
  }



  /**
   * sets toggle-group.
   * 
   * @author lstrauch
   */
  public void alreadyClicked() {
    ToggleGroup group3 = new ToggleGroup();


    easy3.setToggleGroup(group3);
    med3.setToggleGroup(group3);
    dis3.setToggleGroup(group3);

  }


}
