package gui;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.GroupLayout.Alignment;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXButton.ButtonType;
import ai.BotDifficulty;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
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
  private JFXButton change = new JFXButton();
  private JFXButton start = new JFXButton();
  private JFXButton deleteBot = new JFXButton();
  Label p1 = new Label();
  Label p2 = new Label();
  Label p3 = new Label();
  Label p4 = new Label();
  Label notenoughpl = new Label();
  private GameMode gm;
  private GuiController guiCon;
  private GameSettings gs;
  // private static GameSettings gs;
  private static int nrofplayers = 1;
  // private static List<Player> list = new ArrayList<Player>();

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
  


  public LobbyController() {
    guiCon = new GuiController();
    GuiController.prevScreen = 4;
    // setGameSettingsLabel();
  }

  /*
   * (non-Javadoc)
   * 
   * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
   */
  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    // TODO Auto-generated method stub
    setGM();
    displaydiffentGameModes();
    ButtonListener();
    

  }


  public void displayStartButton() {
    start.setPrefWidth(214.0);
    start.setPrefHeight(41.0);
    start.setLayoutX(550.0);
    start.setLayoutY(151.0);
    start.setText("Start");
    start.setTextFill(Color.WHITE);
    start.setFont(Font.font("System", FontWeight.BOLD, FontPosture.ITALIC, 18.0));
    start.setStyle("-fx-background-color: #CD853F; -fx-border-radius: 20; -fx-background-radius: 20; -fx-border-color: #D2B48C;");
    start.setButtonType(ButtonType.RAISED);
    start.setTextAlignment(TextAlignment.CENTER);

    mainPane.getChildren().add(start);
  }
  public void displayBackButton() {
    back.setPrefWidth(214.0);
    back.setPrefHeight(41.0);
    back.setLayoutX(550.0);
    back.setLayoutY(270.0);
    back.setText("Back");
    back.setTextFill(Color.WHITE);
    back.setFont(Font.font("System", FontWeight.BOLD, FontPosture.ITALIC, 18.0));
    back.setStyle("-fx-background-color: #CD853F; -fx-border-radius: 20; -fx-background-radius: 20; -fx-border-color: #D2B48C;");
    back.setButtonType(ButtonType.RAISED);
    back.setTextAlignment(TextAlignment.CENTER);

    mainPane.getChildren().add(back);
  }

  public void displayAddBotButton() {
    addBot.setPrefWidth(214.0);
    addBot.setPrefHeight(41.0);
    addBot.setLayoutX(85.0);
    addBot.setLayoutY(480.0);
    addBot.setText("Add Bot");
    addBot.setTextFill(Color.WHITE);
    addBot.setFont(Font.font("System", FontWeight.BOLD, FontPosture.ITALIC, 18));
    addBot.setStyle("-fx-background-color: #CD853F; -fx-border-radius: 20; -fx-background-radius: 20; -fx-border-color: #D2B48C;");
    addBot.setButtonType(ButtonType.RAISED);
    addBot.setTextAlignment(TextAlignment.CENTER);

    mainPane.getChildren().add(addBot);
  }

  public void displayDeleteBotButton() {
    deleteBot.setPrefWidth(214);
    deleteBot.setPrefHeight(41);
    deleteBot.setLayoutX(330);
    deleteBot.setLayoutY(480);
    deleteBot.setText("Delte Bot");
    deleteBot.setTextFill(Color.WHITE);
    deleteBot.setFont(Font.font("System", FontWeight.BOLD, FontPosture.ITALIC, 18));
    deleteBot.setStyle("-fx-background-color: #CD853F; -fx-border-radius: 20; -fx-background-radius: 20; -fx-border-color: #D2B48C;");
    deleteBot.setButtonType(ButtonType.RAISED);
    deleteBot.setTextAlignment(TextAlignment.CENTER);

    mainPane.getChildren().add(deleteBot);
  }

  public void displayChangeGamesettingsButton() {
    change.setPrefWidth(214.0);
    change.setPrefHeight(41.0);
    change.setLayoutX(550.0);
    change.setLayoutY(380.0);
    change.setText("Change Gamesettings");
    change.setTextFill(Color.WHITE);
    change.setFont(Font.font("System", FontWeight.BOLD, FontPosture.ITALIC, 18.0));
    change.setStyle("-fx-background-color: #CD853F; -fx-border-radius: 20; -fx-background-radius: 20; -fx-border-color: #D2B48C;");
   
    change.setButtonType(ButtonType.RAISED);
    change.setTextAlignment(TextAlignment.CENTER);

    mainPane.getChildren().add(change);
  }


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
        }
      }

    });
    
  }

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

    vbox1.getChildren().add(p1);
  }

  public void displayTwo(String name1, String name2) {
    displayOne(name1);

    p2.setPrefWidth(213);
    p2.setPrefHeight(51);
    p2.setLayoutX(88);
    p2.setLayoutY(168);
    p2.setText(name2);
    p2.setFont(Font.font("System", FontWeight.BOLD, 23));
    p2.setStyle("-fx-background-color: peru; -fx-font-style: italic; -fx-text-fill: white");
    p2.setAlignment(Pos.CENTER);

    vbox1.getChildren().add(p2);
  }

  public void displayThree(String name1, String name2, String name3) {
    displayTwo(name1, name2);
    p3.setPrefWidth(213);
    p3.setPrefHeight(51);
    p3.setLayoutX(88);
    p3.setLayoutY(230);
    p3.setText(name3);
    p3.setFont(Font.font("System", FontWeight.BOLD, 23));
    p3.setStyle("-fx-background-color: peru; -fx-font-style: italic; -fx-text-fill: white");
    p3.setAlignment(Pos.CENTER);

    vbox1.getChildren().add(p3);
  }

  public void displayFour(String name1, String name2, String name3, String name4) {
    displayThree(name1, name2, name3);
    p4.setPrefWidth(213);
    p4.setPrefHeight(51);
    p4.setLayoutX(88);
    p4.setLayoutY(297);
    p4.setText(name4);
    p4.setFont(Font.font("System", FontWeight.BOLD, 23));
    p4.setStyle("-fx-background-color: peru; -fx-font-style: italic; -fx-text-fill: white");
    p4.setAlignment(Pos.CENTER);

    vbox1.getChildren().add(p4);
  }


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

  public GameSettings getGS() {
    return this.gs;
  }


  public void ButtonListener() {
    start.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
//        if(nrofplayers == 3 || nrofplayers == 4) {
//        LoginController.interfGL.setBot("Bot", BotDifficulty.EASY);
//        LoginController.interfGL.setBot("Bot", BotDifficulty.EASY);
          LoginController.interfGL.startGame(gs); 
//        } else {
//          System.out.println("Not enough players selected");
//        }
      }
    });
    back.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        guiCon.displayLobbyOnline();
      }
    });
    addBot.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        if (nrofplayers < 4) {
          guiCon.displayAI();
        }
      }
    });
//    deleteBot.setOnMouseClicked(new EventHandler<MouseEvent>() {
//
//      @Override
//      public void handle(MouseEvent event) {
//        LoginController.interfGL.deleteBot("Bot");
//      }
//    });
    change.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
      }
    });
    
  }


  /**
   * @author lstrauch
   */

  public void setGM() {
    this.gm = guiCon.getGameSetCon().getGM();
  }
  
  public void displaydiffentGameModes() {
    if(gm == GameMode.SINGLEPLAYER) {
      displayChangeGamesettingsButton();
      displayAddBotButton();
      displayDeleteBotButton();
      displayStartButton();
      mainPane.getChildren().remove(allChat);
      mainPane.getChildren().remove(chatM);
      mainPane.getChildren().remove(textM);
      mainPane.getChildren().remove(sendB);
      rec.setHeight(684);
      start.setLayoutY(262);
      change.setLayoutY(491);
      addBot.setLayoutY(591);
      deleteBot.setLayoutY(591);
      vbox1.setLayoutY(211);
      vbox2.setLayoutY(211);
    }
    
  }
  
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
}
