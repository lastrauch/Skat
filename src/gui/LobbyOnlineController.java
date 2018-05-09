package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class LobbyOnlineController implements Initializable {

  @FXML
  private VBox vboxNr, vboxUser, vboxMessage, vboxJoin;

  private GuiController main;
  ArrayList<Label> nr = new ArrayList<Label>();
  ArrayList<Label> user = new ArrayList<Label>();
  ArrayList<Label> message = new ArrayList<Label>();
  ArrayList<JFXButton> join = new ArrayList<JFXButton>();
  private boolean hostgame = false;


  public LobbyOnlineController() {
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
  public void startNewGame() {
    // LoginController.interfGL.hostGame("Hi", new GameSettings());
    // main.getGameSetCon().setGameMode(GameMode.MULTIPLAYER);
    this.hostgame = true;
    main.displayGameSettings();
  }

  @FXML
  public void refresh() {
    if (LoginController.interfGL.lobbyInformation().size() > 0) {
      displayServer();
    } else {
      System.out.println("No new server");
    }
  }


  public void displayServer() {
    System.out.println("Size: " + LoginController.interfGL.lobbyInformation().size());
    for (int i = 0; i < LoginController.interfGL.lobbyInformation().size(); i++) {
      nr.add(new Label());
      user.add(new Label());
      message.add(new Label());
      join.add(new JFXButton());
    }
    for (int i = 0; i < nr.size(); i++) {
      nr.get(i)
          .setText(String.valueOf(LoginController.interfGL.lobbyInformation().get(i).getNumPlayer())
              + "/"
              + String.valueOf(LoginController.interfGL.lobbyInformation().get(i).getMaxPlayer()));
      nr.get(i).setFont(Font.font("System", 23));

      if(!vboxNr.getChildren().contains(vboxNr.getChildren().get(i))) {
        vboxNr.getChildren().add(nr.get(i));
      }
    }

    for (int i = 0; i < user.size(); i++) {
      user.get(i).setText(LoginController.interfGL.lobbyInformation().get(i).getServerName());
      user.get(i).setFont(Font.font("System", 15));

      if(!vboxUser.getChildren().contains(vboxUser.getChildren().get(i))) {
        vboxNr.getChildren().add(user.get(i));
      }
    }

    for (int i = 0; i < join.size(); i++) {
      join.get(i).setPrefWidth(97);
      join.get(i).setPrefHeight(31);
      join.get(i).setText("Join");
      join.get(i).setFont(Font.font("System", FontWeight.BOLD, 15));
      join.get(i)
          .setStyle("-fx-background-color: peru; -fx-text-fill: white; -fx-background-radius: 20");
      join.get(i).setAlignment(Pos.CENTER);

      if(!vboxJoin.getChildren().contains(vboxJoin.getChildren().get(i))) {
        vboxJoin.getChildren().add(join.get(i));
      }

      int[] p = new int[1];
      p[0] = i;
      join.get(i).setOnMouseClicked(new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {
          main.displayLobby();
          LoginController.interfGL
              .joinGame(LoginController.interfGL.lobbyInformation().get(p[0]).getIp());
          System.out.println("Listener");
        }
      });

    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
   */
  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    // TODO Auto-generated method stub
    // displayServer();

  }
  
  public boolean getHostegame() {
    return hostgame;
  }



}
