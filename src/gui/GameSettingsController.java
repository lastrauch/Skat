package gui;

import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
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
  private Label r = new Label();
  private Label s = new Label();
  private Label p = new Label();

  /**
   * @author lstrauch
   */
  @FXML
  private JFXRadioButton r1, r3, r18, r36;
  @FXML
  private JFXRadioButton sSys, bSys, nSys;
  @FXML
  private JFXToggleButton enKon, enTL;
  @FXML
  private JFXRadioButton n3, n4;
  @FXML
  private AnchorPane pane;
  @FXML
  private JFXTextField message;
  @FXML 
  private Label messageLabel;
  /**
   * @author lstrauch
   */
  public GameSettingsController() {
    this.guiCon = new GuiController();
    GuiController.prevScreen = 3;
  }

  /**
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
   * @author lstrauch
   */
  public void countRule() {
    
    
    sSys.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        cr[0] = CountRule.SEEGERFABIAN;
      }
    });
    bSys.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        cr[0] = CountRule.BIERLACHS;
      }
    });
    nSys.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        cr[0] = CountRule.NORMAL;
      }
    });
  }

  /**
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
   * @author lstrauch
   */
  public void enableLimitedTime() {
    enTL.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        System.out.println("hhh");
        enT[0] = true;
        showSetTime();
        if (setLimitedTime != 0) {
          setLimitedTime = setLimitedTime();
        } else {
        }

      }
    });
  }

  /**
   * @author lstrauch
   * @return
   */
  public int setLimitedTime() {
    String s = sec.getText();
    return Integer.parseInt(s);
  }

  /**
   * @author lstrauch
   */
  public void showSetTime() {
    Label set = new Label();
    HBox box = new HBox();

    set.setText("Set limited time:");
    set.setPrefHeight(40);
    set.setPrefWidth(170);
    set.setLayoutX(114);
    set.setLayoutY(503);
    set.setFont(Font.font("System", 23));
    set.setTextFill(Color.WHITE);
    // set.setStyle("-fx-background-color: tan;");

    sec.setPromptText("Number of seconds");
    sec.setPrefHeight(48);
    sec.setPrefWidth(247);
    sec.setLayoutX(298);
    sec.setLayoutY(499);
    sec.setFocusColor(Color.WHITE);
    sec.setUnFocusColor(Color.WHITE);
    set.setStyle("-fx-prompt-text-fill: white;");

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
   * @author lstrauch
   * @param gm
   */
  public void setGameMode(GameMode gm) {
    this.gm = gm;
  }
  


  /**
   * @author lstrauch
   */
  public void submit() {
    /**
     * Makes sure, that all important options are selected
     */
    if (r1.isSelected() || r3.isSelected() || r18.isSelected() || r36.isSelected()) {
      selected[0] = true;
      pane.getChildren().remove(r);
    } else {
      System.out.println("Please Select the number orf rounds you wanna play");
      if (!pane.getChildren().contains(r)) {
        displayLabelRounds();
      }
    }
    if (sSys.isSelected() || bSys.isSelected() || nSys.isSelected()) {
      selected[1] = true;
      pane.getChildren().remove(s);
    } else {
      System.out.println("Please Select the System you wanna play");
      if (!pane.getChildren().contains(s)) {
        displayLabelSystem();
      }
    }
    if (n3.isSelected() || n4.isSelected()) {
      selected[2] = true;
      pane.getChildren().remove(p);
    } else {
      if (!pane.getChildren().contains(p)) {
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
      if(guiCon.getLobbyCon() != null) {
        System.out.println("gs screen Gamesettings: " + gs.getNrOfPlays() + "  " + gs.getCountRule());
        LoginController.interfGL.hostGame(ms, gs);
      }
    }

  }

  public GameSettings getGS() {
    return gs;
  }

  public GameMode getGM() {
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
  
    sSys.setToggleGroup(g2);
    bSys.setToggleGroup(g2);
    nSys.setToggleGroup(g2);
    g2.selectToggle(bSys);

    n3.setToggleGroup(g3);
    n4.setToggleGroup(g3);
    g3.selectToggle(n3);

    en[0] = false;
    enT[0] = false;
    rounds[0] = 3;
    numbOfPl = 3;
    cr[0] = CountRule.BIERLACHS;
    
    setGM();
    if(gm == GameMode.SINGLEPLAYER) {
      pane.getChildren().remove(message);
      pane.getChildren().remove(messageLabel);

    }

    

    listener();
  }

  /**
   * @author lstrauch
   */
  public void allButtonsSet() {
    if (r1.isSelected() || r3.isSelected() || r18.isSelected() || r36.isSelected()) {
      selected[0] = true;
    } else {
      System.out.println("Please Select the number orf rounds you wanna play");
      pane.getChildren().remove(r);
    }
    if (sSys.isSelected() || bSys.isSelected() || nSys.isSelected()) {
      selected[1] = true;
    }
    if (n3.isSelected() || n4.isSelected()) {
      selected[2] = true;
    }
  }

  /**
   * @author lstrauch
   */
  public void displayLabelRounds() {
    r.setLayoutX(10);
    r.setLayoutY(70);
    r.setPrefHeight(33);
    r.setPrefWidth(881);
    r.setText("Please select the number of rounds you want to play!");
    r.setFont(Font.font("System", FontWeight.BOLD, 15));
    r.setStyle("-fx-background-color: white; -fx-text-fill: red");
    r.setAlignment(Pos.CENTER);

    pane.getChildren().add(r);
  }

  /**
   * @author lstrauch
   */
  public void displayLabelSystem() {
    s.setLayoutX(10);
    s.setLayoutY(140);
    s.setPrefHeight(33);
    s.setPrefWidth(881);
    s.setText("Please select the system you want to play!");
    s.setFont(Font.font("System", FontWeight.BOLD, 15));
    s.setStyle("-fx-background-color: white; -fx-text-fill: red");
    s.setAlignment(Pos.CENTER);

    pane.getChildren().add(s);

  }

  /**
   * @author lstrauch
   */
  public void displayLabelPlayers() {
    p.setLayoutX(10);
    p.setLayoutY(217);
    p.setPrefHeight(33);
    p.setPrefWidth(881);
    p.setText("Please select the number of players you want to play with!");
    p.setFont(Font.font("System", FontWeight.BOLD, 15));
    p.setStyle("-fx-background-color: white; -fx-text-fill: red");
    p.setAlignment(Pos.CENTER);

    pane.getChildren().add(p);
  }
  
  public void setGM() {
    this.gm = guiCon.getChooseGameCon().getGameMode();
  }
  
}
