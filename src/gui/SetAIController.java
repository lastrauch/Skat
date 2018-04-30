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

public class SetAIController implements Initializable {

  /**
   * @author lstrauch
   */
  private GuiController main;
  private int bot;
  private Label label = new Label();
  private boolean addedBot;

  /**
   * @author lstrauch
   */
  @FXML
  private JFXRadioButton easy1, med1, dif1, easy2, med2, dif2, easy3, med3, dif3, dis3;
  @FXML
  Label bot1, bot2, bot3;
  @FXML
  Label l1, l2, l3;
  @FXML
  AnchorPane p;

  /**
   *@author lstrauch
   */
  public SetAIController() {
    this.main = new GuiController();
    GuiController.prevScreen = 2;
  }

  /**
   * @author lstrauch
   */
  @FXML
  public void settings() {
    main.displaySettings();
  }

  /**
   * @author lstrauch
   */
  @FXML
  public void help() {
    main.displayHelp();
  }

  /**
   * @author lstrauch
   */
  @FXML
  public void accountSettings() {
    main.displayAccountSettings();
  }

  /**
   * @author lstrauch
   */
  @FXML
  public void play() {
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    // TODO Auto-generated method stub
    buttonHandler();
    alreadyClicked();

  }


  /**
   * @author lstrauch
   */
  public void buttonHandler() {
    easy3.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        bot = 3;
        if (checkIfBotEnabled()) {
          LoginController.interfGL.deleteBot("Bot3");
          System.out.println("Delete b3");
        }
        LoginController.interfGL.setBot("Bot3", BotDifficulty.EASY);
        l3.setText("Easy");
      }
    });
    med3.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        bot = 3;
        if (checkIfBotEnabled()) {
          LoginController.interfGL.deleteBot("Bot3");
          System.out.println("Delete b3");
        }
        LoginController.interfGL.setBot("Bot3", BotDifficulty.MEDIUM);
        l3.setText("Medium");
      }
    });
    dif3.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        bot = 3;
        if (checkIfBotEnabled()) {
          LoginController.interfGL.deleteBot("Bot3");
          System.out.println("Delete b3");
        }
        LoginController.interfGL.setBot("Bo31", BotDifficulty.HARD);
        l3.setText("Hard");
      }
    });
    dis3.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        bot = 3;
        if (checkIfBotEnabled()) {
          LoginController.interfGL.deleteBot("Bot3");
          System.out.println("Delete b3");
        }
        l3.setText("Disabled");
      }
    });
  }


  /**
   * @author lstrauch
   * @return
   */
  public boolean checkIfBotEnabled() {
    switch (bot) {
      case 1:
        if (!l1.getText().equals("")) {
          return true;
        }
      case 2:
        if (!l2.getText().equals("")) {
          return true;
        }
      case 3:
        if (!(l3.getText().equals("Disabled") || l3.getText().equals(""))) {
          return true;
        }
      default:
        return false;
    }
  }

  /**
   * @author lstrauch
   */
  public void alreadyClicked() {
    ToggleGroup group1 = new ToggleGroup();
    ToggleGroup group2 = new ToggleGroup();
    ToggleGroup group3 = new ToggleGroup();

    easy1.setToggleGroup(group1);
    med1.setToggleGroup(group1);
    dif1.setToggleGroup(group1);

    easy2.setToggleGroup(group2);
    med2.setToggleGroup(group2);
    dif2.setToggleGroup(group2);

    easy3.setToggleGroup(group3);
    med3.setToggleGroup(group3);
    dif3.setToggleGroup(group3);
    dis3.setToggleGroup(group3);

  }
  
  /**
   * @author lstrauch
   */
  public void displayLabel() {
    label.setLayoutX(497);
    label.setLayoutY(206);
    label.setPrefHeight(31);
    label.setPrefWidth(758);
    label.setText("You have to select at least 2 Computers!");
    label.setFont(Font.font("System", FontWeight.BOLD, 15));
    label.setStyle("-fx-background-color: white; -fx-text-fill: red");
    label.setAlignment(Pos.CENTER);
    
    p.getChildren().add(label);
  }

}
