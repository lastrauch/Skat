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
import network.server.Server;

public class LobbyOnlineController implements Initializable {

  @FXML
  private VBox vboxNr;
  @FXML
  private VBox vboxUser;
  @FXML
  private VBox vboxMessage;
  @FXML
  private VBox vboxJoin;

  private GuiController main;
  private ArrayList<Label> nr = new ArrayList<Label>();
  private ArrayList<Label> user = new ArrayList<Label>();
  private ArrayList<Label> message = new ArrayList<Label>();
  private ArrayList<JFXButton> join = new ArrayList<JFXButton>();
  private boolean hostgame = false;
  private ArrayList<Server> server = new ArrayList<Server>();


  public LobbyOnlineController() {
    this.main = new GuiController();
    GuiController.prevScreen = 2;
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
   * display account-settings-screen.
   * 
   * @author lstrauch
   */
  @FXML
  public void accountSettings() {
    main.displayAccountSettings();
  }

  /**
   * starts a new game.
   * 
   * @author lstrauch
   */
  @FXML
  public void startNewGame() {
    this.hostgame = true;
    main.displayGameSettings();
  }

  /**
   * refreshes lobby to display open server.
   * 
   * @author lstrauch
   */
  @FXML
  public void refresh() {
    server = LoginController.interfGL.lobbyInformation();
    if (server.size() > 0) {
      displayServer();
    }
  }


  /**
   * displays open server.
   * 
   * @author lstrauch
   */
  public void displayServer() {
    System.out.println("Size: " + server.size());
    for (int i = 0; i < server.size(); i++) {
      nr.add(new Label());
      user.add(new Label());
      message.add(new Label());
      join.add(new JFXButton());
    }

    for (int i = 0; i < nr.size(); i++) {
      nr.get(i).setText(String.valueOf(server.get(i).getNumPlayer()) + "/"
          + String.valueOf(server.get(i).getMaxPlayer()));
      nr.get(i).setFont(Font.font("System", 18));

      if (!vboxNr.getChildren().contains(nr.get(i))) {
        vboxNr.getChildren().add(nr.get(i));
      }
    }

    for (int i = 0; i < user.size(); i++) {
      user.get(i).setText(server.get(i).getServerName());
      user.get(i).setFont(Font.font("System", 18));

      if (!vboxUser.getChildren().contains(user.get(i))) {
        vboxUser.getChildren().add(user.get(i));
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

      if (!vboxJoin.getChildren().contains(join.get(i))) {
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
