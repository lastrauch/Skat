package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXButton.ButtonType;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import database.ImplementsGuiInterface;
import interfaces.GuiData;
import interfaces.GuiLogic;
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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logic.Card;
import logic.Colour;
import logic.GameController;
import logic.GameSettings;
import logic.PlayMode;
import logic.PlayState;
import logic.Position;

public class InGameController implements Initializable, InGameInterface {

  /**
   * Initalisierung des Bettingscreens
   */
  AnchorPane paneBet = new AnchorPane();
  HBox box = new HBox();
  Label labelBet = new Label();
  JFXButton qu = new JFXButton();
  JFXButton pass = new JFXButton();
  JFXButton betB = new JFXButton();

  /**
   * Initialize what chooseTrumPScreen
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
  HBox boxWin1 = new HBox();
  HBox boxWin2 = new HBox();
  HBox boxWin3 = new HBox();
  VBox vBoxWin = new VBox();
  Label labelWin = new Label();


  /**
   * Initialisierung Skat nehmen und Karten wechsel
   */
  private AnchorPane skatPane = new AnchorPane();
  private AnchorPane handPane = new AnchorPane();
  private Label skatLabel = new Label();
  private JFXButton yes = new JFXButton();
  private JFXButton no = new JFXButton();
  private HBox skatHbox = new HBox();
  private ImageView sk1 = new ImageView();
  private ImageView sk2 = new ImageView();
  private JFXButton ok = new JFXButton();


  /**
   * Initializies all other attributes
   */
  private GuiController main;
  private int count = 0;
  private Image rueckseite = new Image(getClass().getResource("/rueckseite.jpg").toExternalForm());
  private Image temp;
//  private GuiData inte = new ImplementsGuiInterface();
  private GuiLogic inteGL = new GameController();

  /**
   * Initialize ChatScreen
   */
  private Image pfUnten =
      new Image(getClass().getResource("/icons8-unten-eingekreist-50.png").toExternalForm());
  private Image pfOben =
      new Image(getClass().getResource("/icons8-oben-eingekreist-50.png").toExternalForm());


  /**
   * Initialize all FXML attributes
   */
  @FXML
  private ImageView c1, c2, c3, c4, c5, c6, c7, c8, c9, c10;
  @FXML
  private ImageView r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, stichRechts;
  @FXML
  private ImageView l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, stichLinks;
  @FXML
  private ImageView s1, s2, s3;
  @FXML
  private ImageView extra1, extra2;
  @FXML
  private AnchorPane mainPane;
  @FXML
  private Label position;
  @FXML
  private JFXButton sendB;
  @FXML private ImageView pf;
  @FXML private JFXTextArea chatM;
  @FXML private JFXTextField textM;
  



  /**
   * 
   * 
   * 
   * This part implements my own methods
   * 
   * 
   * 
   * 
   */



  /**
   * Initiliazise GuiController and Implements class
   */
  public InGameController() {
    this.main = new GuiController();
    // this.implements = new ImplementsInGameInterface();
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    // TODO Auto-generated method stub
    askForBet(18);
//    ButtonListenerPlaySettings();
    displayChatClosed();
    chatButtonListener();
    ButtonListenrWantSkat();


  }

  /**
   * Hier wird definiert was passiert wenn man auf eine seiner jeweiligen Spielkarten klickt
   * 
   * ImageView sets Image to null Method AnzStichblatte wird aufgerufen, sodass Karten auf Stich
   * gelegt werden gibt an Logik den Index der geklickten Karte weiter
   * 
   * @author lstrauch
   */
  public int MouseHandler() {
    final int[] ret = new int[1];
    c1.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        temp = c1.getImage();
        // c1.setImage(null);
        AnzStichblatt();
        ret[0] = 0;
      }
    });
    c2.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        temp = c2.getImage();
        // c2.setImage(null);
        AnzStichblatt();
        ret[0] = 1;
      }
    });
    c3.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        temp = c3.getImage();
        // c3.setImage(null);
        AnzStichblatt();
        ret[0] = 2;
      }
    });
    c4.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        temp = c4.getImage();
        // c4.setImage(null);
        AnzStichblatt();
        ret[0] = 3;
      }
    });
    c5.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        temp = c5.getImage();
        // c5.setImage(null);
        AnzStichblatt();
        ret[0] = 4;
      }
    });
    c6.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        temp = c6.getImage();
        // c6.setImage(null);
        AnzStichblatt();
        ret[0] = 5;
      }
    });
    c7.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        temp = c7.getImage();
        // c7.setImage(null);
        AnzStichblatt();
        ret[0] = 6;

      }
    });
    c8.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        temp = c8.getImage();
        // c8.setImage(null);
        AnzStichblatt();
        ret[0] = 7;
      }
    });
    c9.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        temp = c9.getImage();
        // c9.setImage(null);
        AnzStichblatt();
        ret[0] = 8;
      }
    });
    c10.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        temp = c10.getImage();
        // c10.setImage(null);
        AnzStichblatt();
        ret[0] = 9;
      }
    });
    return ret[0];
  }

  /**
   * Diese Methode definiert auf welchen Teil des Stichblattes die ausgew�hlte Karte plaziert wird
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
      }
    });
    pass.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        ret[0] = false;
        displayWannaTakeSkat();
      }
    });
    betB.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        ret[0] = true;
      }
    });
    submit.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {

      }
    });
    return ret[0];
  }

  public void ButtonListenerPlaySettings(PlayState ps) {
    diamonds.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        ps.setPlayMode(PlayMode.SUIT);
        ps.setTrump(Colour.DIAMONDS);
      }
    });
    hearts.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        ps.setPlayMode(PlayMode.SUIT);
        ps.setTrump(Colour.HEARTS);
      }
    });
    clubs.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        ps.setPlayMode(PlayMode.SUIT);
        ps.setTrump(Colour.CLUBS);
      }
    });
    spades.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        ps.setPlayMode(PlayMode.SUIT);
        ps.setTrump(Colour.SPADES);
      }
    });
    grand.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        ps.setPlayMode(PlayMode.GRAND);
      }
    });
    nullG.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        ps.setPlayMode(PlayMode.NULL);
      }
    });
    ouvert.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        ps.setOpen(true);
      }
    });
    schneider.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        ps.setSchneider(true);
      }
    });
    schwarz.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        ps.setSchwarzAnnounced(true);
      }
    });
  }

  public void ButtonListenrWantSkat(/*PlayState ps*/) {
    yes.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
//        ps.setHandGame(true);
        displaySwitchSkat();
      }
    });
    no.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
//        ps.setHandGame(false);
        displayAuctionWinnerScreen();
      }
    });
  }
  
  public void chatButtonListener() {
    sendB.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        sendChat();
      }
    });
    pf.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        if(pf.getImage().equals(pfUnten)) {
          displayChatScreenOpen();
        } else {
          displayChatClosed();
        }
        System.out.println(pf.getImage());
      }
    });
  }

  public void switchSkatListener() {
    c1.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        temp = c1.getImage();
      }
    });
    c2.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        temp = c2.getImage();
      }
    });
    c3.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
      }
    });
    c4.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
      }
    });
    c5.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
      }
    });
    c6.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
      }
    });
    c7.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {

      }
    });
    c8.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
      }
    });
    c9.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
      }
    });
    c10.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
      }
    });
  }


  /**
   * 
   * Deletes the pane on the mainpane, so we can display the new one
   * 
   * @param tpane
   */
  public void deletePane(AnchorPane tpane) {
    mainPane.getChildren().remove(tpane);
  }

  public void showChat() {
    JFXTextField rMes = new JFXTextField();
    rMes.setText((inteGL.getChatText()));
    chatM.appendText(rMes + "\n");

  }

  public void sendChat() {
    String message;
    message = textM.getText();
    inteGL.sendChatText(message);

  }



  /**
   * 
   * 
   * 
   * 
   * The next part implements all the methods from inGameInterface
   * 
   * 
   * 
   * 
   */

  @Override
  public void startPlay(ArrayList<Card> hand, Position position) {
    // TODO Auto-generated method stub
//    c1.setImage(inte.getImage(hand.get(0).getColour().toString().toLowerCase(),
//        (hand.get(0).getNumber().toString().toLowerCase())));
//    c2.setImage(inte.getImage(hand.get(1).getColour().toString().toLowerCase(),
//        (hand.get(1).getNumber().toString().toLowerCase())));
//    c3.setImage(inte.getImage(hand.get(2).getColour().toString().toLowerCase(),
//        (hand.get(2).getNumber().toString().toLowerCase())));
//    c4.setImage(inte.getImage(hand.get(3).getColour().toString().toLowerCase(),
//        (hand.get(3).getNumber().toString().toLowerCase())));
//    c5.setImage(inte.getImage(hand.get(4).getColour().toString().toLowerCase(),
//        (hand.get(4).getNumber().toString().toLowerCase())));
//    c6.setImage(inte.getImage(hand.get(5).getColour().toString().toLowerCase(),
//        (hand.get(5).getNumber().toString().toLowerCase())));
//    c7.setImage(inte.getImage(hand.get(6).getColour().toString().toLowerCase(),
//        (hand.get(6).getNumber().toString().toLowerCase())));
//    c8.setImage(inte.getImage(hand.get(7).getColour().toString().toLowerCase(),
//        (hand.get(7).getNumber().toString().toLowerCase())));
//    c9.setImage(inte.getImage(hand.get(8).getColour().toString().toLowerCase(),
//        (hand.get(8).getNumber().toString().toLowerCase())));
//    c10.setImage(inte.getImage(hand.get(9).getColour().toString().toLowerCase(),
//        (hand.get(9).getNumber().toString().toLowerCase())));
    this.position.setText(position.toString());
    displayChatScreenOpen();

  }


  @Override
  public void showSecoundsLeftToPlayCard(int seconds) {
    // TODO Auto-generated method stub

  }


  @Override
  public void updateHand(ArrayList<Card> hand) {
    // TODO Auto-generated method stub
//    c1.setImage(inte.getImage(hand.get(0).getColour().toString().toLowerCase(),
//        (hand.get(0).getNumber().toString().toLowerCase())));
    c1.setImage(hand.get(0).getImage());
    c2.setImage(hand.get(1).getImage());
    c3.setImage(hand.get(2).getImage());
    c4.setImage(hand.get(3).getImage());
    c5.setImage(hand.get(4).getImage());
    c6.setImage(hand.get(5).getImage());
    c7.setImage(hand.get(6).getImage());
    c8.setImage(hand.get(7).getImage());
    c9.setImage(hand.get(9).getImage());
    c10.setImage(hand.get(9).getImage());

  }

  @Override
  public void setPlaySettings(PlayState ps) {
    // TODO Auto-generated method stub
    deletePane(paneAuc);
    displayAuctionWinnerScreen();
    ButtonListenerPlaySettings(ps);

  }



  @Override
  public int askToPlayCard() {
    // TODO Auto-generated method stub
    return MouseHandler();
  }

  @Override
  public void askToTakeUpSkat(PlayState ps) {
    // TODO Auto-generated method stub
    deletePane(paneBet);
    displayWannaTakeSkat();
//    ButtonListenrWantSkat(ps);

  }

  @Override
  public boolean askForBet(int bet) {
    // TODO Auto-generated method stub
    betB.setText(String.valueOf(bet));
    displayAuctionScreen();
    return ButtonListener();
  }

  @Override
  public void updateTrick(ArrayList<Card> currentTrick) {
    // TODO Auto-generated method stub
//    if (currentTrick.get(0) != null) {
//      s1.setImage(inte.getImage(currentTrick.get(0).getColour().toString().toLowerCase(),
//          (currentTrick.get(1).getNumber().toString().toLowerCase())));
//    } else if (currentTrick.get(0) != null) {
//      s2.setImage(inte.getImage(currentTrick.get(0).getColour().toString().toLowerCase(),
//          (currentTrick.get(0).getNumber().toString().toLowerCase())));
//    } else if (currentTrick.get(2) != null) {
//      s3.setImage(inte.getImage(currentTrick.get(0).getColour().toString().toLowerCase(),
//          (currentTrick.get(0).getNumber().toString().toLowerCase())));
//    }

  }

  /**
   * Not necessary for InGameController
   */
  @Override
  public void setGameSettings(GameSettings gs) {
    // TODO Auto-generated method stub

  }



  /**
   * 
   * 
   * 
   * 
   * Initialize Screens
   * 
   * 
   * 
   * 
   * 
   */



  /**
   * Display Auction part
   */
  public void displayAuctionScreen() {
    paneBet.setLayoutX(475);
    paneBet.setLayoutY(128);
    paneBet.setPrefHeight(315);
    paneBet.setPrefWidth(395);
    paneBet.setStyle(
        "-fx-background-color: peru; -fx-background-radius: 20; -fx-border-color: tan; -fx-border-radius: 20");

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
    betB.setFont(Font.font("System", FontWeight.BOLD, 20));
    betB.setTextFill(Color.WHITE);
    betB.setStyle("-fx-background-color: tan;");

    labelBet.setPrefHeight(53);
    labelBet.setLayoutX(88);
    labelBet.setLayoutY(36);
    labelBet.setText("Midde");
    labelBet.setFont(Font.font("System", FontWeight.BOLD, 36));
    labelBet.setTextFill(Color.WHITE);
    AnchorPane.setLeftAnchor(labelBet, 0.0);
    AnchorPane.setRightAnchor(labelBet, 0.0);
    labelBet.setAlignment(Pos.CENTER);

    box.getChildren().add(qu);
    box.getChildren().add(pass);
    box.getChildren().add(betB);
    box.setSpacing(50);
    box.setPrefWidth(307);
    box.setPrefHeight(44);
    box.setLayoutX(37);
    box.setLayoutY(158);

    paneBet.getChildren().add(box);
    paneBet.getChildren().add(labelBet);

    mainPane.getChildren().add(paneBet);
  }



  /**
   * display AuctionWinnerPart
   */
  public void displayAuctionWinnerScreen() {
    paneAuc.setPrefHeight(315);
    paneAuc.setPrefWidth(582);
    paneAuc.setLayoutX(334);
    paneAuc.setLayoutY(128);
    paneAuc.setStyle(
        "-fx-background-color: peru; -fx-background-radius: 20; -fx-border-color: tan; -fx-border-radius: 20");

    diamonds.setPrefHeight(42);
    diamonds.setPrefWidth(117);
    diamonds.setText("Diamonds");
    diamonds.setFont(Font.font("System", FontWeight.BOLD, 20));
    diamonds.setStyle("-fx-background-color: tan;");
    hearts.setPrefHeight(42);
    hearts.setPrefWidth(117);
    hearts.setText("Hearts");
    hearts.setFont(Font.font("System", FontWeight.BOLD, 20));
    hearts.setStyle("-fx-background-color: tan;");
    spades.setPrefHeight(42);
    spades.setPrefWidth(117);
    spades.setText("Spades");
    spades.setFont(Font.font("System", FontWeight.BOLD, 20));
    spades.setStyle("-fx-background-color: tan;");
    clubs.setPrefHeight(42);
    clubs.setPrefWidth(117);
    clubs.setText("Clubs");
    clubs.setFont(Font.font("System", FontWeight.BOLD, 20));
    clubs.setStyle("-fx-background-color: tan;");

    boxWin1.getChildren().add(diamonds);
    boxWin1.getChildren().add(hearts);
    boxWin1.getChildren().add(spades);
    boxWin1.getChildren().add(clubs);
    boxWin1.setSpacing(20);
    boxWin1.setPrefWidth(528);
    boxWin1.setPrefHeight(42);
    boxWin1.setLayoutX(28);
    boxWin1.setLayoutY(60);

    grand.setPrefHeight(42);
    grand.setPrefWidth(117);
    grand.setText("Grand");
    grand.setFont(Font.font("System", FontWeight.BOLD, 20));
    grand.setStyle("-fx-background-color: tan;");
    nullG.setPrefHeight(42);
    nullG.setPrefWidth(117);
    nullG.setText("Null");
    nullG.setFont(Font.font("System", FontWeight.BOLD, 20));
    nullG.setStyle("-fx-background-color: tan;");

    boxWin2.getChildren().add(grand);
    boxWin2.getChildren().add(nullG);
    boxWin2.setSpacing(158);
    boxWin2.setPrefWidth(528);
    boxWin2.setPrefHeight(42);
    boxWin2.setLayoutX(28);
    boxWin2.setLayoutY(158);

    ouvert.setPrefHeight(21);
    ouvert.setPrefWidth(102);
    ouvert.setText("Ouvert");
    ouvert.setFont(Font.font("System", 15));
    ouvert.setStyle("-fx-background-color: tan;");
    schneider.setPrefHeight(21);
    schneider.setPrefWidth(102);
    schneider.setText("Schneider");
    schneider.setFont(Font.font("System", 15));
    schneider.setStyle("-fx-background-color: tan;");
    schwarz.setPrefHeight(21);
    schwarz.setPrefWidth(102);
    schwarz.setText("Schwarz");
    schwarz.setFont(Font.font("System", 15));
    schwarz.setStyle("-fx-background-color: tan;");

    boxWin3.getChildren().add(ouvert);
    boxWin3.getChildren().add(schneider);
    boxWin3.getChildren().add(schwarz);
    boxWin3.setSpacing(35);
    boxWin3.setPrefWidth(528);
    boxWin3.setPrefHeight(21);
    boxWin3.setLayoutX(28);
    boxWin3.setLayoutY(266);

    vBoxWin.getChildren().add(boxWin1);
    vBoxWin.getChildren().add(boxWin2);
    vBoxWin.getChildren().add(boxWin3);
    vBoxWin.setPrefHeight(185);
    vBoxWin.setPrefWidth(528);
    vBoxWin.setLayoutX(14);
    vBoxWin.setLayoutY(97);
    vBoxWin.setSpacing(40);

    submit.setPrefHeight(33);
    submit.setPrefWidth(69);
    submit.setText("Submit");
    submit.setFont(Font.font("System", 15));
    submit.setStyle("-fx-background-color: tan; -fx-border-color: black;");
    submit.setButtonType(ButtonType.RAISED);
    submit.setLayoutX(499);
    submit.setLayoutY(268);

    labelWin.setPrefHeight(49);
    labelWin.setPrefWidth(401);
    labelWin.setLayoutX(14);
    labelWin.setLayoutY(28);
    labelWin.setText("What do you wanna play?");
    labelWin.setFont(Font.font("System", FontWeight.BOLD, 33));
    labelWin.setTextFill(Color.WHITE);

    paneAuc.getChildren().add(vBoxWin);
    paneAuc.getChildren().add(labelWin);
    paneAuc.getChildren().add(submit);

    mainPane.getChildren().add(paneAuc);
  }



  /**
   * display "Want to take the Skat?"
   */
  public void displayWannaTakeSkat() {
    skatPane.setPrefHeight(315);
    skatPane.setPrefWidth(582);
    skatPane.setLayoutX(334);
    skatPane.setLayoutY(128);
    skatPane.setStyle(
        "-fx-background-color: peru; -fx-background-radius: 20; -fx-border-color: tan; -fx-border-radius: 20");

    skatLabel.setPrefHeight(49);
    skatLabel.setPrefWidth(467);
    skatLabel.setLayoutX(58);
    skatLabel.setLayoutY(48);
    skatLabel.setText("Do you want to take the Skat?");
    skatLabel.setFont(Font.font("System", FontWeight.BOLD, 33));
    skatLabel.setTextFill(Color.WHITE);
    AnchorPane.setLeftAnchor(skatLabel, 0.0);
    AnchorPane.setRightAnchor(skatLabel, 0.0);
    skatLabel.setAlignment(Pos.CENTER);

    yes.setPrefHeight(123);
    yes.setPrefWidth(154);
    yes.setText("Yes");
    yes.setFont(Font.font("System", FontWeight.BOLD, 25));
    yes.setStyle("-fx-background-color: tan;");
    yes.setButtonType(ButtonType.RAISED);
    no.setPrefHeight(123);
    no.setPrefWidth(154);
    no.setText("No");
    no.setFont(Font.font("System", FontWeight.BOLD, 25));
    no.setStyle("-fx-background-color: tan;");
    no.setButtonType(ButtonType.RAISED);

    skatHbox.getChildren().add(yes);
    skatHbox.getChildren().add(no);
    skatHbox.setSpacing(100);
    skatHbox.setPrefWidth(346);
    skatHbox.setPrefHeight(54);
    skatHbox.setLayoutX(118);
    skatHbox.setLayoutY(158);
    
    ok.setPrefHeight(31);
    ok.setPrefWidth(67);
    ok.setLayoutX(501);
    ok.setLayoutY(270);
    ok.setText("OK");
    ok.setFont(Font.font("System", FontWeight.BOLD, 15));
    ok.setButtonType(ButtonType.RAISED);
    
    

    skatPane.getChildren().add(skatLabel);
    skatPane.getChildren().add(skatHbox);
    skatPane.getChildren().add(ok);

    mainPane.getChildren().add(skatPane);
  }

  /**
   * display part in which the player can choose the cards he wants to put on the skat
   */
  public void displaySwitchSkat() {
    handPane.setPrefHeight(315);
    handPane.setPrefWidth(582);
    handPane.setLayoutX(334);
    handPane.setLayoutY(128);
    handPane.setStyle(
        "-fx-background-color: peru; -fx-background-radius: 20; -fx-border-color: tan; -fx-border-radius: 20");

    sk1.setFitHeight(227);
    sk1.setFitWidth(182);
    sk1.setLayoutX(83);
    sk1.setLayoutY(37);
    sk1.setImage(new Image(getClass().getResource("/grey.jpg").toExternalForm()));
    sk1.setStyle("-fx-background-color: black");

    sk2.setFitHeight(227);
    sk2.setFitWidth(182);
    sk2.setLayoutX(326);
    sk2.setLayoutY(37);
    sk2.setImage(new Image(getClass().getResource("/grey.jpg").toExternalForm()));
    sk2.setStyle("-fx-background-color: black");

    handPane.getChildren().add(sk1);
    handPane.getChildren().add(sk2);

    mainPane.getChildren().add(handPane);
  }

  public void displayChatScreenOpen() {
    chatM.setPrefWidth(1280);
    chatM.setPrefHeight(97);
    chatM.setLayoutX(3);
    chatM.setLayoutY(5);
    chatM.setStyle("-fx-background-color: peru");
    chatM.setEditable(false);
    chatM.setUnFocusColor(Color.PERU);
    chatM.setFocusColor(Color.WHITE);
    chatM.setOpacity(0.33);

    textM.setPrefHeight(50);
    textM.setPrefWidth(1280);
    textM.setLayoutX(3);
    textM.setLayoutY(97);
    textM.setStyle("-fx-background-color: peru; -fx-border-color: black; -fx-border-width: 2");
    textM.setOpacity(0.33);

    pf.setImage(pfOben);
    pf.setFitHeight(32);
    pf.setFitWidth(40);
    pf.setLayoutX(624);
    pf.setLayoutY(130);
    pf.toFront();
    
    sendB.setLayoutX(1167);
    sendB.setLayoutY(97);
    sendB.setPrefHeight(50);
    sendB.setPrefWidth(113);
    sendB.setFont(Font.font("System", 20));
    
    
    s1.setLayoutX(533);
    s1.setLayoutY(149);
    s2.setLayoutX(590);
    s2.setLayoutY(184);
    s3.setLayoutX(619);
    s3.setLayoutY(200);
  }
  
  public void displayChatClosed() {
    chatM.setPrefWidth(1280);
    chatM.setPrefHeight(35);
    chatM.setLayoutX(3);
    chatM.setLayoutY(5);
    chatM.setStyle("-fx-background-color: peru");
    chatM.setEditable(false);
    chatM.setUnFocusColor(Color.PERU);
    chatM.setFocusColor(Color.WHITE);
    chatM.setOpacity(0.33);

    textM.setPrefHeight(32);
    textM.setPrefWidth(1280);
    textM.setLayoutX(3);
    textM.setLayoutY(33);
    textM.setStyle("-fx-background-color: peru; -fx-border-color: black; -fx-border-width: 2");
    textM.setOpacity(0.33);

    pf.setImage(pfUnten);
    pf.setFitHeight(32);
    pf.setFitWidth(40);
    pf.setLayoutX(624);
    pf.setLayoutY(52);
    
    sendB.setLayoutX(1167);
    sendB.setLayoutY(33);
    sendB.setPrefHeight(32);
    sendB.setPrefWidth(113);
    sendB.setFont(Font.font("System", 15));
    
    s1.setLayoutX(533);
    s1.setLayoutY(89);
    s2.setLayoutX(590);
    s2.setLayoutY(114);
    s3.setLayoutX(619);
    s3.setLayoutY(140);
  }



  /**
   * 
   * 
   * 
   * 
   * 
   * Methoden um im Men� auf die verschiedenen Buttons clicken zu k�nnen und zum jeweiligen Screen
   * zur�ck zu gelangen
   * 
   * @author lstrauch
   * 
   * 
   * 
   * 
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



}
