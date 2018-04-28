package gui;

import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import interfaces.GuiLogic;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import logic.CountRule;
import logic.GameMode;
import logic.GameSettings;

public class GameSettingsController implements Initializable{

  private int[] rounds;
  private CountRule countRule;
  private boolean kontra;
  private boolean limitedTime;
  private int setLimitedTime;
  private int numbOfPl;
  private JFXTextField sec = new JFXTextField();
  private GameSettings gs = new GameSettings();
  private String ms;
  private GuiController guiCon;
  private GameMode gm;
  private GuiLogic interf = LoginController.gameCon;
  private ToggleGroup g1 = new ToggleGroup();
  private ToggleGroup g2 = new ToggleGroup();
  private ToggleGroup g3 = new ToggleGroup();

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

  public GameSettingsController() {
    this.guiCon = new GuiController();
  }
  
  public void listener() {
    this.numberOfRounds();
    this.countRule();
    this.numberOfPlayers();
    this.enableKontra();
    this.enableLimitedTime();
  }

  public void numberOfRounds() {
    rounds = new int[1];
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

  public void countRule() {
    final CountRule[] cr = new CountRule[1];
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
  
  public void numberOfPlayers() {
    n3.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        numbOfPl= 3;
      }
    });
    n4.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        numbOfPl = 4;;
      }
    });
  }

  public void enableKontra() {
    final boolean[] en = new boolean[1];
    en[0] = false;
    enKon.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        en[0] = true;
      }
    });
  }

  public void enableLimitedTime() {
    final boolean[] enT = new boolean[1];
    enT[0] = false;
    enTL.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        System.out.println("hhh");
        enT[0] = true;
        showSetTime();
        setLimitedTime = setLimitedTime();

      }
    });
  }

  public int setLimitedTime() {
    String s = sec.getPromptText();
    return Integer.parseInt(s);
  }

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


  public void setMode(GameMode gm) {
    this.gm = gm;
  }

  public void submitOn() {
    gs.setNumberOfPlays(rounds[0]);
    gs.setNrOfPlayers(numbOfPl);
    gs.setCountRule(countRule);
    gs.setEnableKontra(kontra);
    gs.setLimitedTime(limitedTime);
    if (limitedTime) {
      gs.setTimeLimit(setLimitedTime());
    }
    ms = message.getText();
//    interf.startGame(gs);
    guiCon.displayLobbyOnline();
  }

  public void submitOf() {
    gs.setNumberOfPlays(rounds[0]);
    gs.setCountRule(countRule);
    gs.setEnableKontra(kontra);
    gs.setLimitedTime(limitedTime);
    if (limitedTime) {
      gs.setTimeLimit(setLimitedTime());
    }
    ms = message.getText();
//    interf.hostGame(ms, gs);
    guiCon.displayInGame();
  }

  public void screenChooser() {
    if (gm.equals(GameMode.MULTIPLAYER)) {
      submitOn();
    } else {
      submitOf();
    }
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    // TODO Auto-generated method stub
    r1.setToggleGroup(g1);
    r3.setToggleGroup(g1);
    r18.setToggleGroup(g1);
    r36.setToggleGroup(g1);
    
    sSys.setToggleGroup(g2);
    bSys.setToggleGroup(g2);
    nSys.setToggleGroup(g2); 
    
    n3.setToggleGroup(g3);
    n4.setToggleGroup(g3);
    
    this.listener();
  }
}
