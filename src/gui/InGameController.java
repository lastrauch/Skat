package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import interfaces.InGameInterface;
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
import logic.Card;
import logic.PlayState;
import logic.Trick;

public class InGameController implements Initializable, InGameInterface {

  /**
   * Initalisierung des BettinGscreens
   */
  AnchorPane pane = new AnchorPane();
  HBox box = new HBox();
  Label label = new Label();
  JFXButton qu = new JFXButton();
  JFXButton pass = new JFXButton();
  JFXButton betB = new JFXButton();

  /**
   * Initialize what chooseTrumScreen
   */
  AnchorPane paneAuc = new AnchorPane();
  JFXButton diamonds = new JFXButton();
  JFXButton hearts = new JFXButton();
  JFXButton spades = new JFXButton();
  JFXButton clubs = new JFXButton();
  JFXButton nullG = new JFXButton();
  JFXButton grand = new JFXButton();
  JFXButton submit = new JFXButton();
  JFXRadioButton ouvert = new JFXRadioButton();
  JFXRadioButton schneider = new JFXRadioButton();
  JFXRadioButton schwarz = new JFXRadioButton();

  private GuiController main;
  private int count = 0;
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


  public InGameController() {
    this.main = new GuiController();
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    // TODO Auto-generated method stub
    askForBet(18);


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
  public int MouseHandler() {
    final int[] ret = new int[1];
    c1.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        temp = c1.getImage();
        c1.setImage(null);
        AnzStichblatt();
        ret[0] = 0;
      }
    });
    c2.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        temp = c2.getImage();
        c2.setImage(null);
        AnzStichblatt();
        ret[0] = 1;

      }
    });
    c3.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        temp = c3.getImage();
        c3.setImage(null);
        AnzStichblatt();
        ret[0] = 2;

      }
    });
    c4.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        temp = c4.getImage();
        c4.setImage(null);
        AnzStichblatt();
        ret[0] = 3;

      }
    });
    c5.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        temp = c5.getImage();
        c5.setImage(null);
        AnzStichblatt();
        ret[0] = 4;

      }
    });
    c6.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        temp = c6.getImage();
        c6.setImage(null);
        AnzStichblatt();
        ret[0] = 5;

      }
    });
    c7.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        temp = c7.getImage();
        c7.setImage(null);
        AnzStichblatt();
        ret[0] = 6;

      }
    });
    c8.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        temp = c8.getImage();
        c8.setImage(null);
        AnzStichblatt();
        ret[0] = 7;
      }
    });
    c9.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        temp = c9.getImage();
        c9.setImage(null);
        AnzStichblatt();
        ret[0] = 8;

      }
    });
    c10.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        temp = c10.getImage();
        c10.setImage(null);
        AnzStichblatt();
        ret[0] = 9;
      }
    });

    return ret[0];
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

  public boolean ButtonListener() {
    final boolean[] ret = new boolean[1];

    qu.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        System.out.println("qu");
        ret[0] = true;
      }
    });
    pass.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        ret[0] = true;
        System.out.println("pass");
      }
    });
    betB.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        ret[0] = true;
        System.out.println("bet");
      }
    });

    return ret[0];
  }

  public void auctionWinnerScreen() {
    paneAuc.setId("pane");
    diamonds.setId("diamonds");
    hearts.setId("hearts");
    spades.setId("spades");
    clubs.setId("cubs");
    nullG.setId("nullG");
    grand.setId("grand");
    submit.setId("submit");
    ouvert.setId("ouvert");
    schneider.setId("schneider");
    schwarz.setId("schwarz");

  }

  @Override
  public int askToPlayCard() {
    // TODO Auto-generated method stub

    return MouseHandler();
  }

  @Override
  public void showSecoundsLeftToPlayCard(int seconds) {
    // TODO Auto-generated method stub

  }

  @Override
  public boolean takeUpSkat() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public int[] askToTakeDownTwoCards() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean askForBet(int bet) {
    // TODO Auto-generated method stub
    pane.setId("pane");
    pane.setLayoutX(475);
    pane.setLayoutY(128);
    pane.setPrefHeight(315);
    pane.setPrefWidth(395);
    pane.getStylesheets().add(getClass().getResource("/inGame.css").toExternalForm());

    qu.setId("?");
    qu.setPrefHeight(44);
    qu.setPrefWidth(69);
    qu.setText("?");
    qu.setFont(Font.font("System", FontWeight.BOLD, 20));
    qu.setTextFill(Color.WHITE);
    qu.setStyle("-fx-background-color: tan;");
    pass.setId("pass");
    pass.setPrefHeight(44);
    pass.setPrefWidth(69);
    pass.setText("Pass");
    pass.setFont(Font.font("System", FontWeight.BOLD, 20));
    pass.setTextFill(Color.WHITE);
    pass.setStyle("-fx-background-color: tan;");
    betB.setId("value");
    betB.setPrefHeight(44);
    betB.setPrefWidth(69);
    betB.setText(String.valueOf(bet));
    betB.setFont(Font.font("System", FontWeight.BOLD, 20));
    betB.setTextFill(Color.WHITE);
    betB.setStyle("-fx-background-color: tan;");

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
    box.getChildren().add(betB);
    box.setSpacing(50);
    box.setPrefWidth(307);
    box.setPrefHeight(44);
    box.setLayoutX(37);
    box.setLayoutY(158);

    pane.getChildren().add(box);
    pane.getChildren().add(label);

    mainPane.getChildren().add(pane);

    return ButtonListener();
  }

  @Override
  public void updateHand(ArrayList<Card> hand) {


    // TODO Auto-generated method stub
    // c1.setImage(hand.get(0).getImage());
    // c2.setImage(hand.get(1).getImage());
    // c3.setImage(hand.get(2).getImage());
    // c4.setImage(hand.get(3).getImage());
    // c5.setImage(hand.get(4).getImage());
    // c6.setImage(hand.get(5).getImage());
    // c7.setImage(hand.get(6).getImage());
    // c8.setImage(hand.get(7).getImage());
    // c9.setImage(hand.get(9).getImage());
    // c10.setImage(hand.get(9).getImage());

  }

  @Override
  public void setPlaySettings(PlayState ps) {
    // TODO Auto-generated method stub

  }

  @Override
  public void showTrick(Trick trick) {
    // TODO Auto-generated method stub

  }



}
