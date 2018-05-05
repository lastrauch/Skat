package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import interfaces.GuiLogic;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import logic.GameController;
import logic.GameMode;

public class LobbyOnlineController implements Initializable{

  @FXML
  private JFXButton join;
  @FXML 
  private VBox vboxNr, vboxUser, vboxMessage;
  
  private GuiController main;
  private JFXButton b;

  
  public LobbyOnlineController() {
    this.main = new GuiController();
    GuiController.prevScreen = 2;
  }

  /**
   * @author lstrauch
   */
  @FXML
  public void join() {
//    LoginController.interfGL.joinGame(LoginController.username);
    
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
  public void startNewGame() {
    main.displayGameSettings();
    main.getGameSetCon().setGameMode(GameMode.MULTIPLAYER);
  }
  
  
  public void displayServer() {
    ArrayList<Label> a= new ArrayList<Label>();
    ArrayList<JFXButton> b = new ArrayList<JFXButton>();
    if(LoginController.interfGL.lobbyInformation().size() == 0) {
      System.out.println("NULL!!!!"
          + "");
    }
    System.out.println("Size: " +LoginController.interfGL.lobbyInformation().size());
    for(int i = 0; i < LoginController.interfGL.lobbyInformation().size(); i++) {
      a.add(new Label());
      b.add(new JFXButton());
    }
    for(int i = 0; i < a.size(); i++) {
      a.get(i).setText(String.valueOf(LoginController.interfGL.lobbyInformation().get(i).getPlayer().size()) + "/" + String.valueOf(LoginController.interfGL.lobbyInformation().get(i).getGameSettings().getNrOfPlayers()));
      a.get(i).setFont(Font.font("System", 23));

      vboxNr.getChildren().add(a.get(i));
    }
    
    for(int i = 0; i < a.size(); i++) {
      a.get(i).setText(LoginController.interfGL.lobbyInformation().get(i).getName());
      a.get(i).setFont(Font.font("System", 23));

      vboxUser.getChildren().add(a.get(i));
    }
    
    for(int i = 0; i < b.size(); i++) {
      b.get(i).setPrefWidth(97);
      b.get(i).setPrefHeight(31);
      b.get(i).setText("Join");
      b.get(i).setFont(Font.font("System", FontWeight.BOLD, 15));
      b.get(i).setStyle("-fx-background-color: peru; -fx-text-fill: white; -fx-background-radius: 20");
      b.get(i).setAlignment(Pos.CENTER);
      
      int[] p = new int[1];
      p[0] = i;
      b.get(i).setOnMouseClicked(new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {
//          main.displayLobby(GameMode.MULTIPLAYER);
          LoginController.interfGL.joinGame(LoginController.interfGL.lobbyInformation().get(p[0]).getName());
        }
      });
    }
  }

  /* (non-Javadoc)
   * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
   */
  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    // TODO Auto-generated method stub
    displayServer();
  }
  
  public void displayJoinButton() {
    b.setPrefWidth(97);
    b.setPrefHeight(31);
    b.setText("Join");
    b.setFont(Font.font("System", FontWeight.BOLD, 15));
    b.setStyle("-fx-background-color: peru; -fx-text-fill: white; -fx-background-radius: 20");
    b.setAlignment(Pos.CENTER);
  }

}
