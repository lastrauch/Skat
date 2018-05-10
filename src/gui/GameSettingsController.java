package gui;

import java.net.URL;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logic.CountRule;
import logic.GameMode;
import logic.GameSettings;

public class GameSettingsController implements Initializable {

  /**
   * initilizes all non-FXML attributes.
   * @author lstrauch
   */
  private int[] rounds = new int[1];
  private CountRule[] cr = new CountRule[1];
  private boolean[] en = new boolean[1];
  private boolean[] enT = new boolean[1];
  private int setLimitedTime;
  private int numbOfPl = 0;
  private JFXTextField sec = new JFXTextField();
  protected GameSettings gs = new GameSettings();
  private String ms;
  private GuiController guiCon;
  private GameMode gm;
  private ToggleGroup g1 = new ToggleGroup();
  private ToggleGroup g2 = new ToggleGroup();
  private ToggleGroup g3 = new ToggleGroup();
  private boolean[] selected = new boolean[3];
  private Label round = new Label();
  private Label syst = new Label();
  private Label pl = new Label();

  /**
   * initializes all FXML attributes.
   * @author lstrauch
   */
  @FXML
  private JFXRadioButton r1;
  @FXML
  private JFXRadioButton r3;
  @FXML
  private JFXRadioButton r18;
  @FXML
  private JFXRadioButton r36;
  @FXML
  private JFXRadioButton ssys;
  @FXML
  private JFXRadioButton bsys;
  @FXML
  private JFXRadioButton nsys;
  @FXML
  private JFXToggleButton enKon;
  @FXML
  private JFXToggleButton enTl;
  @FXML
  private JFXRadioButton n3;
  @FXML
  private JFXRadioButton n4;
  @FXML
  private JFXButton subm;
  @FXML
  private AnchorPane pane;
  @FXML
  private JFXTextField message;
  @FXML
  private Label messageLabel;

  /**
   * constructor.
   * @author lstrauch
   */
  public GameSettingsController() {
    this.guiCon = new GuiController();
    GuiController.prevScreen = 3;
  }

  /**
   * sums up all buttonListeners.
   * @author lstrauch
   */
  public void listener() {
    this.numberOfRounds();
    this.countRule();
    this.numberOfPlayers();
    this.enableKontra();
    this.enableLimitedTime();
  }

  /**
   * adds buttonListener to rounds.
   * @author lstrauch
   */
  public void numberOfRounds() {
    r1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        rounds[0] = Integer.parseInt(r1.getText());
      }
    });
    r3.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        rounds[0] = Integer.parseInt(r3.getText());
      }
    });
    r18.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        rounds[0] = Integer.parseInt(r18.getText());
      }
    });
    r36.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        rounds[0] = Integer.parseInt(r36.getText());
      }
    });
  }

  /**
   * adds buttonListener to count rule.
   * @author lstrauch
   */
  public void countRule() {


    ssys.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        cr[0] = CountRule.SEEGERFABIAN;
      }
    });
    bsys.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        cr[0] = CountRule.BIERLACHS;
      }
    });
    nsys.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        cr[0] = CountRule.NORMAL;
      }
    });
  }

  /**
   * adds buttonListener to number of players.
   * @author lstrauch
   */
  public void numberOfPlayers() {
    n3.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        numbOfPl = 3;
      }
    });
    n4.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        numbOfPl = 4;;
      }
    });
  }

  /**
   * adds buttonListener to kontra.
   * @author lstrauch
   */
  public void enableKontra() {
    enKon.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        en[0] = true;
      }
    });
  }

  /**
   * adds buttonListener to limitedTime.
   * @author lstrauch
   */
  public void enableLimitedTime() {
    enTl.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        System.out.println("hhh");
        enT[0] = true;
        showSetTime();
        if (setLimitedTime != 0) {
          setLimitedTime = setLimitedTime();
        } 
      }
    });
  }

  /**
   * sets the limitedTime to the time from textField.
   * @author lstrauch
   * @return limitedTime
   */
  public int setLimitedTime() {
    String s = sec.getText();
    if(s != null) {
      return Integer.parseInt(s);
    } else {
      return 10;
    }
  }

  /**
   * shows a TextField to put in a time.
   * @author lstrauch
   */
  public void showSetTime() {
    Label set = new Label();
    set.setText("Set limited time:");
    set.setPrefHeight(40);
    set.setPrefWidth(170);
    set.setLayoutX(114);
    set.setLayoutY(503);
    set.setFont(Font.font("System", 23));
    set.setTextFill(Color.WHITE);

    sec.setPromptText("Number of seconds");
    sec.setPrefHeight(48);
    sec.setPrefWidth(247);
    sec.setLayoutX(298);
    sec.setLayoutY(499);
    sec.setFocusColor(Color.WHITE);
    sec.setUnFocusColor(Color.WHITE);
    set.setStyle("-fx-prompt-text-fill: white;");

    HBox box = new HBox();
    box.getChildren().add(set);
    box.getChildren().add(sec);
    box.setSpacing(25);
    box.setPrefWidth(442);
    box.setPrefHeight(48);
    box.setLayoutX(224);
    box.setLayoutY(503);

    pane.getChildren().add(box);
  }


  /**
   * sets the gameMode.
   * @author lstrauch
   * @param gm
   */
  public void setGameMode(GameMode gm) {
    this.gm = gm;
  }



  /**
   * sets the Gamesettings if one button of each topic is selected.
   * @author lstrauch
   */
  public void submit() {
    /**
     * Makes sure, that all important options are selected
     */
    if (r1.isSelected() || r3.isSelected() || r18.isSelected() || r36.isSelected()) {
      selected[0] = true;
      pane.getChildren().remove(round);
    } else {
      System.out.println("Please Select the number orf rounds you wanna play");
      if (!pane.getChildren().contains(round)) {
        displayLabelRounds();
      }
    }
    if (ssys.isSelected() || bsys.isSelected() || nsys.isSelected()) {
      selected[1] = true;
      pane.getChildren().remove(syst);
    } else {
      System.out.println("Please Select the System you wanna play");
      if (!pane.getChildren().contains(syst)) {
        displayLabelSystem();
      }
    }
    if (n3.isSelected() || n4.isSelected()) {
      selected[2] = true;
      pane.getChildren().remove(pl);
    } else {
      if (!pane.getChildren().contains(pl)) {
        displayLabelPlayers();
      }
    }


    if (selected[0] == true && selected[1] == true && selected[2] == true) {
      gs.setNumberOfPlays(rounds[0]);
      gs.setNrOfPlayers(numbOfPl);
      gs.setCountRule(cr[0]);
      gs.setEnableKontra(en[0]);
      gs.setLimitedTime(enT[0]);
      if (enT[0]) {
        gs.setTimeLimit(setLimitedTime());
      }
      ms = message.getText();
      if (GuiController.prevScreen != 2) {
        setGameMode(GameMode.SINGLEPLAYER);
      } else {
        setGameMode(GameMode.MULTIPLAYER);
      }
      guiCon.displayLobby();
      if (guiCon.getLobbyCon() != null) {
        System.out
            .println("gs screen Gamesettings: " + gs.getNrOfPlays() + "  " + gs.getCountRule());
        LoginController.interfGL.hostGame(ms, gs);
      }
    }

  }

  /** return the gamesettings.
   * @author lstrauch
   * @return GameSettings
   */
  public GameSettings getGs() {
    return gs;
  }

  /**
   * return the gameMode.
   * @author lstrauch
   * @return GameMode
   */
  public GameMode getGm() {
    return gm;
  }

  /**
   * (non-Javadoc)
   * 
   * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
   * 
   * @author lstrauch
   */
  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    // TODO Auto-generated method stub

    r1.setToggleGroup(g1);
    r3.setToggleGroup(g1);
    r18.setToggleGroup(g1);
    r36.setToggleGroup(g1);
    g1.selectToggle(r3);

    ssys.setToggleGroup(g2);
    bsys.setToggleGroup(g2);
    nsys.setToggleGroup(g2);
    g2.selectToggle(nsys);

    n3.setToggleGroup(g3);
    n4.setToggleGroup(g3);
    g3.selectToggle(n3);

    en[0] = false;
    enT[0] = false;
    rounds[0] = 3;
    numbOfPl = 3;
    cr[0] = CountRule.NORMAL;

    setGm();
    if (gm == GameMode.SINGLEPLAYER) {
      pane.getChildren().remove(message);
      pane.getChildren().remove(messageLabel);
    }



    listener();
  }


  /**
   * displays a Label if no rounds-button was selected.
   * @author lstrauch
   */
  public void displayLabelRounds() {
    round.setLayoutX(10);
    round.setLayoutY(70);
    round.setPrefHeight(33);
    round.setPrefWidth(881);
    round.setText("Please select the number of rounds you want to play!");
    round.setFont(Font.font("System", FontWeight.BOLD, 15));
    round.setStyle("-fx-background-color: white; -fx-text-fill: red");
    round.setAlignment(Pos.CENTER);

    pane.getChildren().add(round);
  }

  /**
   * displays a Label if no system-button was selected.
   * @author lstrauch
   */
  public void displayLabelSystem() {
    syst.setLayoutX(10);
    syst.setLayoutY(140);
    syst.setPrefHeight(33);
    syst.setPrefWidth(881);
    syst.setText("Please select the system you want to play!");
    syst.setFont(Font.font("System", FontWeight.BOLD, 15));
    syst.setStyle("-fx-background-color: white; -fx-text-fill: red");
    syst.setAlignment(Pos.CENTER);

    pane.getChildren().add(syst);

  }

  /**
   * displays a Label if no numberOfPlayers-button was selected.
   * @author lstrauch
   */
  public void displayLabelPlayers() {
    pl.setLayoutX(10);
    pl.setLayoutY(217);
    pl.setPrefHeight(33);
    pl.setPrefWidth(881);
    pl.setText("Please select the number of players you want to play with!");
    pl.setFont(Font.font("System", FontWeight.BOLD, 15));
    pl.setStyle("-fx-background-color: white; -fx-text-fill: red");
    pl.setAlignment(Pos.CENTER);

    pane.getChildren().add(pl);
  }

  /**
   * sets the gameMode.
   * @author lstrauch
   */
  public void setGm() {
    this.gm = guiCon.getChooseGameCon().getGameMode();
  }

}
