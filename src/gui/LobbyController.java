package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXButton.ButtonType;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import logic.GameMode;
import logic.GameSettings;
import logic.Player;


public class LobbyController implements Initializable {

  private JFXButton back = new JFXButton();
  private JFXButton addBot = new JFXButton();
  private JFXButton start = new JFXButton();
  private JFXButton deleteBot = new JFXButton();
  private Label p1 = new Label();
  private Label p2 = new Label();
  private Label p3 = new Label();
  private Label p4 = new Label();
  private Label notenoughpl = new Label();
  private GuiController guiCon;
  private GameSettings gs;
  private GameMode gm;
  private static int nrofplayers = 1;

  @FXML
  private Label rounds;
  @FXML
  private Label system;
  @FXML
  private Label kontra;
  @FXML
  private Label timelimit;
  @FXML
  private AnchorPane mainPane;
  @FXML
  private VBox vbox1;
  @FXML
  private VBox vbox2;
  @FXML
  private Label allChat;
  @FXML
  private JFXTextArea chatM;
  @FXML
  private JFXTextField textM;
  @FXML
  private JFXButton sendB;
  @FXML
  private Rectangle rec;



  /**
   * Constructor.
   * 
   * @author lstrauch
   */
  public LobbyController() {
    guiCon = new GuiController();
    GuiController.prevScreen = 4;
  }

  /*
   * (non-Javadoc)
   * 
   * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
   */
  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    // TODO Auto-generated method stub
    if (guiCon.getGameSetCon() != null) {
      setGm();
      displaydiffentGameModes();
    }
    allbuttonsListener();


  }


  /**
   * defines start-button.
   * 
   * @author lstrauch
   */
  public void displayStartButton() {
    start.setPrefWidth(214.0);
    start.setPrefHeight(41.0);
    start.setLayoutX(550.0);
    start.setLayoutY(151.0);
    start.setText("Start");
    start.setTextFill(Color.WHITE);
    start.setFont(Font.font("System", FontWeight.BOLD, FontPosture.ITALIC, 18.0));
    start.setStyle("-fx-background-color: #CD853F; -fx-border-radius: 20;"
        + " -fx-background-radius: 20; -fx-border-color: #D2B48C;");
    start.setButtonType(ButtonType.RAISED);
    start.setTextAlignment(TextAlignment.CENTER);

    mainPane.getChildren().add(start);
  }

  /**
   * define back-button.
   * 
   * @author lstrauch
   */
  public void displayBackButton() {
    back.setPrefWidth(214.0);
    back.setPrefHeight(41.0);
    back.setLayoutX(550.0);
    back.setLayoutY(375.0);
    back.setText("Back");
    back.setTextFill(Color.WHITE);
    back.setFont(Font.font("System", FontWeight.BOLD, FontPosture.ITALIC, 18.0));
    back.setStyle("-fx-background-color: #CD853F; -fx-border-radius: 20; "
        + "-fx-background-radius: 20; -fx-border-color: #D2B48C;");
    back.setButtonType(ButtonType.RAISED);
    back.setTextAlignment(TextAlignment.CENTER);

    mainPane.getChildren().add(back);
  }

  /**
   * defines add-button.
   * 
   * @author lstrauch
   */
  public void displayAddBotButton() {
    addBot.setPrefWidth(214.0);
    addBot.setPrefHeight(41.0);
    addBot.setLayoutX(85.0);
    addBot.setLayoutY(480.0);
    addBot.setText("Add Bot");
    addBot.setTextFill(Color.WHITE);
    addBot.setFont(Font.font("System", FontWeight.BOLD, FontPosture.ITALIC, 18));
    addBot.setStyle("-fx-background-color: #CD853F; -fx-border-radius: 20;"
        + " -fx-background-radius: 20; -fx-border-color: #D2B48C;");
    addBot.setButtonType(ButtonType.RAISED);
    addBot.setTextAlignment(TextAlignment.CENTER);

    mainPane.getChildren().add(addBot);
  }

  /**
   * define delete-button.
   * 
   * @author lstrauch
   */
  public void displayDeleteBotButton() {
    deleteBot.setPrefWidth(214);
    deleteBot.setPrefHeight(41);
    deleteBot.setLayoutX(330);
    deleteBot.setLayoutY(480);
    deleteBot.setText("Delte Bot");
    deleteBot.setTextFill(Color.WHITE);
    deleteBot.setFont(Font.font("System", FontWeight.BOLD, FontPosture.ITALIC, 18));
    deleteBot.setStyle("-fx-background-color: #CD853F; -fx-border-radius: 20;"
        + " -fx-background-radius: 20; -fx-border-color: #D2B48C;");
    deleteBot.setButtonType(ButtonType.RAISED);
    deleteBot.setTextAlignment(TextAlignment.CENTER);

    mainPane.getChildren().add(deleteBot);
  }


  public void showChatMessage(String text, String playername) {
    chatM.appendText(playername + ": " + text + "\n");
    chatM.setFont(Font.font("System", FontWeight.BOLD, 18.0));
  }

  /**
   * send the written chat message.
   * 
   * @author lstrauch
   */
  @FXML
  public void sendChatMessage() {
    String message;
    message = textM.getText();
    textM.setFont(Font.font("System", FontWeight.BOLD, 18.0));
    LoginController.interfGL.sendChatText(message);
  }

  /**
   * displays the right amount of players.
   * 
   * @author lstrauch
   * @param size number of player
   * @param name playerlist
   */
  public void displayPlayers(int size, List<Player> name) {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        // TODO Auto-generated method stub
        switch (size) {
          case 1:
            displayOne(name.get(0).getName());
            nrofplayers = 1;
            break;
          case 2:
            displayTwo(name.get(0).getName(), name.get(1).getName());
            nrofplayers = 2;
            break;
          case 3:
            displayThree(name.get(0).getName(), name.get(1).getName(), name.get(2).getName());
            nrofplayers = 3;
            break;
          case 4:
            displayFour(name.get(0).getName(), name.get(1).getName(), name.get(2).getName(),
                name.get(3).getName());
            nrofplayers = 4;
            break;
          default:
            break;
        }
      }

    });

  }

  /**
   * defines p1.
   * 
   * @author lstrauch
   * @param name playername
   */
  public void displayOne(String name) {
    p1.setPrefWidth(213);
    p1.setPrefHeight(51);
    p1.setLayoutX(88);
    p1.setLayoutY(137);
    p1.setText(name);
    p1.setFont(Font.font("System", FontWeight.BOLD, 23));
    p1.setStyle("-fx-background-color: peru; -fx-font-style: italic; -fx-text-fill: white");
    p1.setAlignment(Pos.CENTER);

    System.out.println("ADDED ONE: " + p1.getText());

    if (!vbox1.getChildren().contains(p1)) {
      vbox1.getChildren().add(p1);
    }
  }

  /**
   * displays p1, p2.
   * 
   * @author lstrauch
   * @param name1 player1-name
   * @param name2 player2-name
   */
  public void displayTwo(String name1, String name2) {
    if (!vbox1.getChildren().contains(p1)) {
      displayOne(name1);
    }

    p2.setPrefWidth(213);
    p2.setPrefHeight(51);
    p2.setLayoutX(88);
    p2.setLayoutY(168);
    p2.setText(name2);
    p2.setFont(Font.font("System", FontWeight.BOLD, 23));
    p2.setStyle("-fx-background-color: peru; -fx-font-style: italic; -fx-text-fill: white");
    p2.setAlignment(Pos.CENTER);

    if (!vbox1.getChildren().contains(p2)) {
      vbox1.getChildren().add(p2);
    }
  }

  /**
   * define p1,p2,p3.
   * 
   * @author lstrauch
   * @param name1 player1-name
   * @param name2 player2-name
   * @param name3 player3-name
   */
  public void displayThree(String name1, String name2, String name3) {
    if (!vbox1.getChildren().contains(p1) && !vbox2.getChildren().contains(p2)) {
      displayTwo(name1, name2);
    }
    p3.setPrefWidth(213);
    p3.setPrefHeight(51);
    p3.setLayoutX(88);
    p3.setLayoutY(230);
    p3.setText(name3);
    p3.setFont(Font.font("System", FontWeight.BOLD, 23));
    p3.setStyle("-fx-background-color: peru; -fx-font-style: italic; -fx-text-fill: white");
    p3.setAlignment(Pos.CENTER);

    if (!vbox1.getChildren().contains(p3)) {
      vbox1.getChildren().add(p3);
    }
  }

  /**
   * define p1,p2,p3.
   * 
   * @author lstrauch
   * @param name1 player1-name
   * @param name2 player2-name
   * @param name3 player3-name
   * @param name4 player4-name
   */
  public void displayFour(String name1, String name2, String name3, String name4) {
    if (!vbox1.getChildren().contains(p1) && !vbox1.getChildren().contains(p2)
        && !vbox1.getChildren().contains(p3)) {
      displayThree(name1, name2, name3);
    }
    p4.setPrefWidth(213);
    p4.setPrefHeight(51);
    p4.setLayoutX(88);
    p4.setLayoutY(297);
    p4.setText(name4);
    p4.setFont(Font.font("System", FontWeight.BOLD, 23));
    p4.setStyle("-fx-background-color: peru; -fx-font-style: italic; -fx-text-fill: white");
    p4.setAlignment(Pos.CENTER);

    if (!vbox1.getChildren().contains(p4)) {
      vbox1.getChildren().add(p4);
    }
  }


  /**
   * sets/displays current GameSettings ob LobbyScreen.
   * 
   * @author lstrauch
   * @param gs current
   */
  public void setGameSettingsLabel(GameSettings gs) {
    this.gs = gs;
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        // TODO Auto-generated method stub
        rounds.setText(String.valueOf(gs.getNrOfPlays()));
        system.setText(gs.getCountRule().toString());
        if (gs.isLimitedTime()) {
          timelimit.setText(String.valueOf(gs.getTimeLimit()));
        } else {
          timelimit.setText("Disabled");
        }
        if (gs.isEnableKontra()) {
          kontra.setText("Enabled");
        } else {
          kontra.setText("Disabled");
        }
      }
    });
  }

  /**
   * returns GameSettings.
   * 
   * @author lstrauch
   * @return GameSettings
   */
  public GameSettings getGs() {
    return this.gs;
  }


  /**
   * adds Listener to different buttons.
   * 
   * @author lstrauch
   */
  public void allbuttonsListener() {
    start.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        LoginController.interfGL.startGame(gs);
      }
    });
    back.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        guiCon.displayLobbyOnline();
        LoginController.interfGL.exitLobby();
      }
    });
    addBot.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        if (nrofplayers < 4) {
          guiCon.displayAi();
        }
      }
    });
    deleteBot.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        if (vbox1.getChildren().size() - 1 == 2) {
          if (checkIfBot(p2.getText())) {
            LoginController.interfGL.deleteBot(p2.getText());
            vbox1.getChildren().remove(p2);
          }
        } else if (vbox1.getChildren().size() - 1 == 3) {
          if (checkIfBot(p3.getText())) {
            LoginController.interfGL.deleteBot(p3.getText());
            vbox1.getChildren().remove(p3);
          } else if (checkIfBot(p2.getText())) {
            LoginController.interfGL.deleteBot(p2.getText());
            vbox1.getChildren().remove(p2);
          }
        } else if (vbox1.getChildren().size() - 1 == 4) {
          if (!checkIfBot(p4.getText())) {
            LoginController.interfGL.deleteBot(p4.getText());
            vbox1.getChildren().remove(p4);
          } else if (!checkIfBot(p3.getText())) {
            LoginController.interfGL.deleteBot(p3.getText());
            vbox1.getChildren().remove(p3);
          } else if (!checkIfBot(p2.getText())) {
            LoginController.interfGL.deleteBot(p2.getText());
            vbox1.getChildren().remove(p2);
          } else {
            System.out.println("noBot");
          }
        }
      }
    });

  }

  /**
   * checks which label is a bot.
   * 
   * @author lstrauch
   * @param s s
   * @return true or false
   */
  public boolean checkIfBot(String s) {
    if (s.equals("Bot 1") || s.equals("Bot 2") || s.equals("Bot 3)")) {
      return true;
    } else {
      return false;
    }
  }


  public void setGm() {
    this.gm = guiCon.getGameSetCon().getGm();
  }

  /**
   * depending in which mode you are, show different styles.
   * 
   * @author lstrauch
   */
  public void displaydiffentGameModes() {
    if (guiCon.getLobbyOnlineCon() != null) {
      if (guiCon.getLobbyOnlineCon().getHostegame()) {
        displayStartButton();
        displayAddBotButton();
        displayDeleteBotButton();
        displayBackButton();
      } else {
        displayBackButton();
      }
    } else {
      displayStartButton();
      displayAddBotButton();
      displayDeleteBotButton();
      displayBackButton();
      mainPane.getChildren().remove(allChat);
      mainPane.getChildren().remove(chatM);
      mainPane.getChildren().remove(textM);
      mainPane.getChildren().remove(sendB);
      rec.setHeight(684);
      start.setLayoutY(262);
      addBot.setLayoutY(591);
      deleteBot.setLayoutY(591);
      vbox1.setLayoutY(211);
      vbox2.setLayoutY(211);
    }
  }


  /**
   * displays a label if not enough player are added.
   * 
   * @author lstrauch
   */
  public void displayNoUser() {
    notenoughpl.setLayoutX(14);
    notenoughpl.setLayoutY(375);
    notenoughpl.setPrefHeight(44);
    notenoughpl.setPrefWidth(718);
    notenoughpl.setText("Username not found!");
    notenoughpl.setFont(Font.font("System", FontWeight.BOLD, 21));
    notenoughpl.setStyle("-fx-background-color: white; -fx-text-fill: red");
    notenoughpl.setAlignment(Pos.CENTER);

    mainPane.getChildren().add(notenoughpl);
  }

  public int countBot() {
    return vbox1.getChildren().size();
  }

  /**
   * opens Settings-screen.
   * 
   * @author lstrauch
   */
  @FXML
  public void settings() {
    guiCon.displaySettings();
  }

  /**
   * opens Help-screen.
   * 
   * @author lstrauch
   */
  @FXML
  public void help() {
    guiCon.displayHelp();
  }

  /**
   * opens AccountSettings-screen.
   * 
   * @author lstrauch
   */
  @FXML
  public void accountSettings() {
    guiCon.displayAccountSettings();
  }
}
