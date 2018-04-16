package gui;

import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class InGameController implements Initializable {

  private GuiController main;
  private int count = 0;
  private ImageView cardId = new ImageView();
  private Image jc = new Image(getClass().getResource("/Jhearts.jpg").toExternalForm());
  private Image rueckseite = new Image(getClass().getResource("/rueckseite.jpg").toExternalForm());
  private Image temp;


  @FXML
  private ImageView c1, c2, c3, c4, c5, c6, c7, c8, c9, c10;
  @FXML
  private ImageView r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, stichRechts;
  @FXML
  private ImageView l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, stichLinks;
  @FXML
  private ImageView s1, s2, s3;
  @FXML
  private AnchorPane mainPane;

  /**
   * @param cc1
   * @param cc2
   * @param cc3
   * @param cc4
   * @param cc5
   * @param cc6
   * @param cc7
   * @param cc8
   * @param cc9
   * @param cc10
   */
  public InGameController(/*
                           * Image cc1, Image cc2, Image cc3, Image cc4, Image cc5, Image cc6, Image
                           * cc7, Image cc8, Image cc9, Image cc10
                           */) {
    // cardId.setImage(cc1);
    // cardId.setImage(cc2);
    // cardId.setImage(cc3);
    // cardId.setImage(cc4);
    // cardId.setImage(cc5);
    // cardId.setImage(cc6);
    // cardId.setImage(cc7);
    // cardId.setImage(cc8);
    // cardId.setImage(cc9);
    // cardId.setImage(cc10);
    this.main = new GuiController();
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    // TODO Auto-generated method stub

    /**
     * Setzt die MouseEvents, sodass Karten ausgewählt und das Spielfeld aktualisiert werden kann
     * 
     * @author: lstrauch
     * 
     */
    MouseHandler();
    displayBetting();

  }

  /**
   * Methoden um im Menü auf die verschiedenen Buttons clicken zu können und zum jeweiligen Screen
   * zurück zu gelangen
   * 
   * @author lstrauch
   */

  @FXML
  public void settings() {
    main.displaySettings();
  }

  @FXML
  public void help() {
    main.displayHelp();
  }

  @FXML
  public void accountSettings() {
    main.displayAccountSettings();
  }

  @FXML
  public void back() {
    main.displayChooseGame();
  }

  /**
   * Hier wird definiert was passiert wenn man auf eine seiner jeweiligen Spielkarten klickt
   * 
   * @author lstrauch
   */
  public void MouseHandler() {
    c1.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        temp = c1.getImage();
        mainPane.getChildren().remove(c1);
        AnzStichblatt();


      }
    });
    c2.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        temp = c2.getImage();
        mainPane.getChildren().remove(c2);
        AnzStichblatt();

      }
    });
    c3.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        temp = c3.getImage();
        mainPane.getChildren().remove(c3);
        AnzStichblatt();

      }
    });
    c4.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        temp = c4.getImage();
        mainPane.getChildren().remove(c4);
        AnzStichblatt();

      }
    });
    c5.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        temp = c5.getImage();
        mainPane.getChildren().remove(c5);
        AnzStichblatt();

      }
    });
    c6.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        temp = c6.getImage();
        mainPane.getChildren().remove(c6);
        AnzStichblatt();

      }
    });
    c7.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        temp = c7.getImage();
        mainPane.getChildren().remove(c7);
        AnzStichblatt();

      }
    });
    c8.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        temp = c8.getImage();
        mainPane.getChildren().remove(c8);
        AnzStichblatt();
      }
    });
    c9.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        temp = c9.getImage();
        mainPane.getChildren().remove(c9);
        AnzStichblatt();

      }
    });
    c10.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        temp = c10.getImage();
        mainPane.getChildren().remove(c10);
        AnzStichblatt();
      }
    });
  }

  /**
   * Diese Methode definiert auf welchen Teil des Stichblattes die ausgewählte Karte plaziert wird
   * 
   * @author lstrauch
   */

  public void AnzStichblatt() {
    switch (count) {
      case 0:
        s1.setImage(temp);
        count++;
        break;
      case 1:
        s2.setImage(temp);
        count++;
        break;
      case 2:
        s3.setImage(temp);
        count++;
        break;
      case 3:
        count = 0;
        s1.setImage(null);
        s2.setImage(null);
        s3.setImage(null);
        stichLinks.setImage(rueckseite);


        AnzStichblatt();
        break;
    }
  }


  // public void whoWon(){
  // winner = GuiLogic.whoWon();
  // }

  public void displayBetting() {
    AnchorPane pane = new AnchorPane();
    HBox box = new HBox();
    Label label = new Label();
    JFXButton qu = new JFXButton();
    JFXButton pass = new JFXButton();
    JFXButton bet = new JFXButton();

    pane.setId("pane");
    pane.setLayoutX(475);
    pane.setLayoutY(128);
    pane.setPrefHeight(315);
    pane.setPrefWidth(395);
    pane.getStylesheets().add(getClass().getResource("/inGame.css").toExternalForm());

    qu.setPrefHeight(44);
    qu.setPrefWidth(69);
    qu.setText("?");
    qu.setFont(Font.font("System", FontWeight.BOLD, 20));
    qu.setTextFill(Color.WHITE);
    qu.setStyle("-fx-background-color: tan;");
    pass.setPrefHeight(44);
    pass.setPrefWidth(69);
    pass.setText("Pass");
    pass.setFont(Font.font("System", FontWeight.BOLD, 20));
    pass.setTextFill(Color.WHITE);
    pass.setStyle("-fx-background-color: tan;");
    bet.setPrefHeight(44);
    bet.setPrefWidth(69);
    bet.setText("bet");
    bet.setFont(Font.font("System", FontWeight.BOLD, 20));
    bet.setTextFill(Color.WHITE);
    bet.setStyle("-fx-background-color: tan;");

    label.setPrefHeight(53);
    label.setLayoutX(88);
    label.setLayoutY(36);
    label.setText("Midde");
    label.setFont(Font.font("System", FontWeight.BOLD, 36));
    label.setTextFill(Color.WHITE);
    AnchorPane.setLeftAnchor(label, 0.0);
    AnchorPane.setRightAnchor(label, 0.0);
    label.setAlignment(Pos.CENTER);

    box.getChildren().add(qu);
    box.getChildren().add(pass);
    box.getChildren().add(bet);
    box.setSpacing(50);
    box.setPrefWidth(307);
    box.setPrefHeight(44);
    box.setLayoutX(37);
    box.setLayoutY(158);

    pane.getChildren().add(box);
    pane.getChildren().add(label);

    mainPane.getChildren().add(pane);
  }

}
