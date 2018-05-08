package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXButton.ButtonType;
import ai.AiController;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import database.ImplementsGuiInterface;
import interfaces.GuiData;
import interfaces.InGameInterface;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;
import logic.Card;
import logic.ClientLogic;
import logic.Colour;
import logic.GameSettings;
import logic.LogicException;
import logic.Number;
import logic.PlayMode;
import logic.PlayState;
import logic.Player;
import logic.Position;

public class InGameController implements Initializable, InGameInterface {

  /**
   * Initalisierung des Bettingscreens
   * 
   * @author lstrauch
   */
  AnchorPane paneBet = new AnchorPane();
  HBox box = new HBox();
  Label labelBet = new Label();
  JFXButton qu = new JFXButton();
  JFXButton pass = new JFXButton();
  JFXButton betB = new JFXButton();
  boolean b = false;
  boolean notpressed = true;

  /**
   * Initialize what auctionWinnerScreen
   * 
   * @author lstrauch
   */
  AnchorPane paneAuc = new AnchorPane();
  JFXRadioButton diamonds = new JFXRadioButton();
  JFXRadioButton hearts = new JFXRadioButton();
  JFXRadioButton spades = new JFXRadioButton();
  JFXRadioButton clubs = new JFXRadioButton();
  JFXRadioButton nullG = new JFXRadioButton();
  JFXRadioButton grand = new JFXRadioButton();
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
  List<Card> skatLogic = new ArrayList<Card>();
  boolean decidepressed = false;
  boolean wantskat = false;
  boolean skatpressed = false;
  boolean setSettings = false;


  /**
   * Initializies all other attributes
   */
  private GuiController main;
  private Player pl1, pl2, pl3;
  private GuiData inte = new ImplementsGuiInterface();
  private List<Card> cardlist = new ArrayList<Card>();
  private Image noCard = new Image(getClass().getResource("/grey.jpg").toExternalForm());
  private Image rueckseite = new Image(getClass().getResource("/rueckseite.jpg").toExternalForm());
  private Image bubbleL =
      new Image(getClass().getResource("/Sprechblase_links.png").toExternalForm());
  private Image bubbleR =
      new Image(getClass().getResource("/Sprechblase_rechts.png").toExternalForm());
  private Image bubbleU =
      new Image(getClass().getResource("/Sprechblase_oben.png").toExternalForm());
  private List<Card> skat = new ArrayList<Card>();
  private Boolean[] da = new Boolean[2];
  private boolean clicked = false;
  int[] ret = new int[1];
  private int countl = 10;
  private int countr = 10;
  private boolean random = false;
  private boolean trainingsmode;


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
  private ImageView[] cArray = new ImageView[10];
  @FXML
  private ImageView r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, stichRechts;
  private ImageView[] rArray = new ImageView[10];
  @FXML
  private ImageView l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, stichLinks;
  private ImageView[] lArray = new ImageView[10];
  @FXML
  private ImageView o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, stichOben;
  private ImageView[] oArray = new ImageView[10];
  @FXML
  private ImageView s1, s2, s3;
  @FXML
  private ImageView extra1, extra2;
  @FXML
  private AnchorPane mainPane;
  @FXML
  private Label pos;
  @FXML
  private JFXButton sendB;
  @FXML
  private ImageView pf;
  @FXML
  private JFXTextArea chatM;
  @FXML
  private JFXTextField textM;
  @FXML
  private Label labelLeft, labelRight, labelMiddle, labelMe;
  @FXML
  private ImageView profilepictureLeft, profilepictureRight;
  @FXML
  private ImageView bubbleLeft, bubbleRight, bubbleUp;
  @FXML
  private Label betLeft, betUp, betRight;
  @FXML
  private Label timeUp;
  @FXML
  private JFXTextArea training;
  @FXML
  private JFXButton kontra;
  



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
    GuiController.prevScreen = 4;
  }

  /**
   * (non-Javadoc)
   * 
   * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
   * 
   *      initializes attributes and enabeles Buttonlistener
   * @author lstrauch
   **/
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    // TODO Auto-generated method stub
    da[0] = true;
    da[1] = true;
    cArray[0] = c1;
    cArray[1] = c2;
    cArray[2] = c3;
    cArray[3] = c4;
    cArray[4] = c5;
    cArray[5] = c6;
    cArray[6] = c7;
    cArray[7] = c8;
    cArray[8] = c9;
    cArray[9] = c10;

    lArray[0] = l1;
    lArray[1] = l2;
    lArray[2] = l3;
    lArray[3] = l4;
    lArray[4] = l5;
    lArray[5] = l6;
    lArray[6] = l7;
    lArray[7] = l8;
    lArray[8] = l9;
    lArray[9] = l10;

    rArray[0] = r1;
    rArray[1] = r2;
    rArray[2] = r3;
    rArray[3] = r4;
    rArray[4] = r5;
    rArray[5] = r6;
    rArray[6] = r7;
    rArray[7] = r8;
    rArray[8] = r9;
    rArray[9] = r10;

    oArray[0] = o1;
    oArray[1] = o2;
    oArray[2] = o3;
    oArray[3] = o4;
    oArray[4] = o5;
    oArray[5] = o6;
    oArray[6] = o7;
    oArray[7] = o8;
    oArray[8] = o9;
    oArray[9] = o10;

    chatButtonListener();

  }


  /**
   * 
   * Deletes the pane on the mainpane, so we can display the new one
   * 
   * @author lstrauch
   * @param tpane
   */
  public void deletePane(AnchorPane tpane) {
    mainPane.getChildren().remove(tpane);
  }



  /**
   * 
   * @author lstrauch
   */
  public void showChat() {
    JFXTextField rMes = new JFXTextField();
    // rMes.setText((LoginController.interfGL.getChatText()));
    chatM.appendText(rMes + "\n");

  }

  /**
   * @author lstrauch
   */
  public void sendChat() {
    String message;
    message = textM.getText();
    LoginController.interfGL.sendChatText(message);

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

  /**
   * @author lstrauch
   */
  /*
   * (non-Javadoc)
   * 
   * @see interfaces.InGameInterface#startPlay(java.util.List, logic.Position)
   * 
   * @author lstrauch
   */
  @Override
  public void startPlay(List<Card> hand, Position position) {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        cardlist = hand;
        rearrangeCardsLight(hand);
        pos.setText(position.toString());
      }
    });

  }


  /**
   * @author lstrauch
   */
  /*
   * (non-Javadoc)
   * 
   * @see interfaces.InGameInterface#updateHand(java.util.List)
   * 
   * @author lstrauch
   */
  @Override
  public void updateHand(List<Card> list) {
    // TODO Auto-generated method stub
    cardlist = list;
    rearrangeCardsLight(list);
  }


  /**
   * @author lstrauch
   */
  /*
   * (non-Javadoc)
   * 
   * @see interfaces.InGameInterface#stopGame(java.lang.String)
   * 
   * @author lstrauch
   */
  @Override
  public void stopGame(String reason) {
    // TODO Auto-generated method stub

  }

  /**
   * @author lstrauch
   */
  /*
   * (non-Javadoc)
   * 
   * @see interfaces.InGameInterface#showWinnerTrick(logic.Player)
   * 
   * @author lstrauch
   */
  @Override
  public void showWinnerTrick(Player player) {
    // TODO Auto-generated method stub
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        // TODO Auto-generated method stub
        s1.setImage(null);
        s2.setImage(null);
        s3.setImage(null);

        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    });
    if (LoginController.interfGL.getPlayer().getPosition() == Position.FOREHAND) {
      if (player.getPosition() == Position.MIDDLEHAND) {
        stichLinks.setImage(rueckseite);
      } else if (player.getPosition() == Position.REARHAND) {
        stichRechts.setImage(rueckseite);
      } else {

      }
    } else if (LoginController.interfGL.getPlayer().getPosition() == Position.REARHAND) {
      if (player.getPosition() == Position.MIDDLEHAND) {
        stichRechts.setImage(rueckseite);
      } else if (player.getPosition() == Position.FOREHAND) {
        stichLinks.setImage(rueckseite);
      } else {

      }
    } else {
      if (player.getPosition() == Position.REARHAND) {
        stichLinks.setImage(rueckseite);
      } else if (player.getPosition() == Position.FOREHAND) {
        stichRechts.setImage(rueckseite);
      } else {

      }
    }


  }


  public Player getPlayer1() {
    return this.pl1;
  }

  public Player getPlayer2() {
    return this.pl2;
  }

  public Player getPlayer3() {
    return this.pl3;
  }



  /**
   * @author lstrauch
   */
  /*
   * (non-Javadoc)
   * 
   * @see interfaces.InGameInterface#askToRekontra()
   */
  @Override
  public void askToRekontra() {
    // TODO Auto-generated method stub
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        displayRekontra();
        kontra.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent e) {
            LoginController.interfGL.announceRekontra();
          }
        });
        
      }
    });
  }

  public void initialize4() {
    if (LoginController.interfGL.getPlayer().getPosition() == Position.DEALER
        || LoginController.interfGL.getPlayer().getPosition() == Position.MIDDLEHAND) {
      for (int i = 0; i < 10; i++) {
        oArray[i].setImage(null);
      }
    } else if (LoginController.interfGL.getPlayer().getPosition() == Position.FOREHAND) {
      rArray[1].setImage(null);
      for (int i = 0; i < rArray.length; i++) {
        rArray[i].setImage(null);
      }
    } else if (LoginController.interfGL.getPlayer().getPosition() == Position.REARHAND) {
      lArray[1].setImage(null);
      for (int i = 0; i < rArray.length; i++) {
        lArray[i].setImage(null);
      }
    }
  }

  /**
   * Auction
   * 
   * @author lstrauch
   */

  /*
   * (non-Javadoc)
   * 
   * @see interfaces.InGameInterface#openAskForBet(int)
   */
  @Override
  public void openAskForBet(int bet) {
    // TODO Auto-generated method stub
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        System.out.println("Position: " + LoginController.interfGL.getPlayer().getPosition());
        if (main.getLobbyCon().getGS().getNrOfPlayers() == 4) {
          initialize4();
        } else {
          for (int i = 0; i < 10; i++) {
            oArray[i].setImage(null);
          }
        }
        displayAuctionScreen();
        betB.setText(String.valueOf(bet));
      }
    });
  }

  /**
   * @author lstrauch
   */
  /*
   * (non-Javadoc)
   * 
   * @see interfaces.InGameInterface#askForBet(int, logic.Player)
   */
  @Override
  public boolean askForBet(int bet, Player lastBet) {
    // deletePane(paneBet);
    while (notpressed) {
      ButtonListener();
    }
    notpressed = true;
    return b;
  }



  /**
   * Do you want to take the Skat?
   * 
   * @author lstrauch
   */
  /*
   * (non-Javadoc)
   * 
   * @see interfaces.InGameInterface#openTakeUpSkat()
   */
  @Override
  public void openTakeUpSkat() {
    // TODO Auto-generated method stub
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        displayWannaTakeSkat();
      }
    });
  }


  /**
   * @author lstrauch
   */
  /*
   * (non-Javadoc)
   * 
   * @see interfaces.InGameInterface#askToTakeUpSkat()
   */
  @Override
  public boolean askToTakeUpSkat() {
    // TODO Auto-generated method stub
    while (decidepressed == false) {
      ButtonListenrWantSkat();
    }
    decidepressed = false;
    return wantskat;
  }



  /**
   * Declarer Stack
   * 
   * @author lstrauch
   */
  /*
   * (non-Javadoc)
   * 
   * @see interfaces.InGameInterface#openSwitchSkat(logic.PlayState)
   */
  @Override
  public void openSwitchSkat(PlayState ps) {
    // TODO Auto-generated method stub
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        deletePane(skatPane);
        displaySwitchSkat(ps);
      }
    });
  }

  /**
   * @author lstrauch
   */
  /*
   * (non-Javadoc)
   * 
   * @see interfaces.InGameInterface#switchSkat(logic.PlayState)
   */
  @Override
  public List<Card> switchSkat(PlayState ps) {
    // TODO Auto-generated method stub
    skat.add(ps.getSkat()[0]);
    skat.add(ps.getSkat()[1]);
    while (skatpressed == false) {
      switchSkatListener(ps);
    }
    skatpressed = false;
    return skatLogic;
  }


  /**
   * @author lstrauch
   */
  /*
   * (non-Javadoc)
   * 
   * @see interfaces.InGameInterface#openAuctionWinnerScreen()
   */
  @Override
  public void openAuctionWinnerScreen() {
    // TODO Auto-generated method stub
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        deletePane(paneBet);
        deletePane(skatPane);
        deletePane(handPane);
        displayAuctionWinnerScreen();
      }
    });
  }


  /**
   * @author lstrauch
   * @param ps
   */
  /*
   * (non-Javadoc)
   * 
   * @see interfaces.InGameInterface#setPlaySettingsAfterAuction(logic.PlayState)
   */
  @Override
  public void setPlaySettingsAfterAuction(PlayState ps) {
    // TODO Auto-generated method stub
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        if(main.getLobbyCon().getGS().isEnableKontra() && !LoginController.interfGL.getPlayer().isDeclarer()) {
          displayKontra();
          kontra.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
              LoginController.interfGL.announceContra();
            }
          });
        }
        disableTraining();
        bubbleLeft.setImage(null);
        bubbleRight.setImage(null);
        bubbleUp.setImage(null);
        betRight.setText(null);
        betLeft.setText(null);
        betUp.setText(null);
        deletePane(paneBet);
        deletePane(skatPane);
        deletePane(handPane);
        deletePane(paneAuc);
        rearrangeCardsDark(cardlist);
        if (main.getLobbyCon().getGS().getNrOfPlayers() == 3) {
          if (ps.getPlayMode() == PlayMode.GRAND || ps.getPlayMode() == PlayMode.NULL) {
            if (LoginController.interfGL.getPlayer().getPosition() == Position.FOREHAND) {
              if (ps.getAuction().getWinner().getPosition() == Position.MIDDLEHAND) {
                labelLeft.setText(ps.getPlayMode().toString());
              } else if (ps.getAuction().getWinner().getPosition() == Position.REARHAND) {
                labelRight.setText(ps.getPlayMode().toString());
              } else {
                labelMe.setText(ps.getPlayMode().toString());
              }
            } else if (LoginController.interfGL.getPlayer().getPosition() == Position.REARHAND) {
              if (ps.getAuction().getWinner().getPosition() == Position.MIDDLEHAND) {
                labelRight.setText(ps.getPlayMode().toString());
              } else if (ps.getAuction().getWinner().getPosition() == Position.FOREHAND) {
                labelLeft.setText(ps.getPlayMode().toString());
              } else {
                labelMe.setText(ps.getPlayMode().toString());
              }
            } else {
              if (ps.getAuction().getWinner().getPosition() == Position.REARHAND) {
                labelRight.setText(ps.getPlayMode().toString());
              } else if (ps.getAuction().getWinner().getPosition() == Position.FOREHAND) {
                labelLeft.setText(ps.getPlayMode().toString());
              } else {
                labelMe.setText(ps.getPlayMode().toString());
              }
            }
          } else {
            if (LoginController.interfGL.getPlayer().getPosition() == Position.FOREHAND) {
              if (ps.getAuction().getWinner().getPosition() == Position.MIDDLEHAND) {
                labelLeft.setText(ps.getTrump().toString());
              } else if (ps.getAuction().getWinner().getPosition() == Position.REARHAND) {
                labelRight.setText(ps.getTrump().toString());
              } else {
                labelMe.setText(ps.getTrump().toString());
              }
            } else if (LoginController.interfGL.getPlayer().getPosition() == Position.REARHAND) {
              if (ps.getAuction().getWinner().getPosition() == Position.MIDDLEHAND) {
                labelRight.setText(ps.getTrump().toString());
              } else if (ps.getAuction().getWinner().getPosition() == Position.FOREHAND) {
                labelLeft.setText(ps.getTrump().toString());
              } else {
                labelMe.setText(ps.getTrump().toString());
              }
            } else {
              if (ps.getAuction().getWinner().getPosition() == Position.REARHAND) {
                labelRight.setText(ps.getTrump().toString());
              } else if (ps.getAuction().getWinner().getPosition() == Position.FOREHAND) {
                labelLeft.setText(ps.getTrump().toString());
              } else {
                labelMe.setText(ps.getTrump().toString());
              }
            }
          }



          // 4 Players
        } else {
          System.out.println("playmode");
          if (ps.getPlayMode() == PlayMode.GRAND || ps.getPlayMode() == PlayMode.NULL) {
            if (LoginController.interfGL.getPlayer().getPosition() == Position.FOREHAND) {
              if (ps.getAuction().getWinner().getPosition() == Position.MIDDLEHAND) {
                labelLeft.setText(ps.getPlayMode().toString());
              } else if (ps.getAuction().getWinner().getPosition() == Position.REARHAND) {
                labelMiddle.setText(ps.getPlayMode().toString());
              } else {
                labelMe.setText(ps.getPlayMode().toString());
              }
            } else if (LoginController.interfGL.getPlayer().getPosition() == Position.REARHAND) {
              if (ps.getAuction().getWinner().getPosition() == Position.MIDDLEHAND) {
                labelRight.setText(ps.getPlayMode().toString());
              } else if (ps.getAuction().getWinner().getPosition() == Position.FOREHAND) {
                labelMiddle.setText(ps.getPlayMode().toString());
              } else {
                labelMe.setText(ps.getPlayMode().toString());
              }
            } else if (LoginController.interfGL.getPlayer().getPosition() == Position.MIDDLEHAND) {
              if (ps.getAuction().getWinner().getPosition() == Position.REARHAND) {
                labelLeft.setText(ps.getPlayMode().toString());
              } else if (ps.getAuction().getWinner().getPosition() == Position.FOREHAND) {
                labelRight.setText(ps.getPlayMode().toString());
              } else {
                labelMe.setText(ps.getPlayMode().toString());
              }
            } else {
              if (ps.getAuction().getWinner().getPosition() == Position.REARHAND) {
                labelRight.setText(ps.getPlayMode().toString());
              } else if (ps.getAuction().getWinner().getPosition() == Position.FOREHAND) {
                labelLeft.setText(ps.getPlayMode().toString());
              } else {
                labelMiddle.setText(ps.getPlayMode().toString());
              }
            }
          } else {
            if (LoginController.interfGL.getPlayer().getPosition() == Position.FOREHAND) {
              if (ps.getAuction().getWinner().getPosition() == Position.MIDDLEHAND) {
                labelLeft.setText(ps.getTrump().toString());
              } else if (ps.getAuction().getWinner().getPosition() == Position.REARHAND) {
                labelMiddle.setText(ps.getTrump().toString());
              } else {
                labelMe.setText(ps.getTrump().toString());
              }
            } else if (LoginController.interfGL.getPlayer().getPosition() == Position.REARHAND) {
              if (ps.getAuction().getWinner().getPosition() == Position.MIDDLEHAND) {
                labelRight.setText(ps.getTrump().toString());
              } else if (ps.getAuction().getWinner().getPosition() == Position.FOREHAND) {
                labelMiddle.setText(ps.getTrump().toString());
              } else {
                labelMe.setText(ps.getTrump().toString());
              }
            } else if (LoginController.interfGL.getPlayer().getPosition() == Position.MIDDLEHAND) {
              if (ps.getAuction().getWinner().getPosition() == Position.REARHAND) {
                labelLeft.setText(ps.getTrump().toString());
              } else if (ps.getAuction().getWinner().getPosition() == Position.FOREHAND) {
                labelRight.setText(ps.getTrump().toString());
              } else {
                labelMe.setText(ps.getTrump().toString());
              }
            } else {
              if (ps.getAuction().getWinner().getPosition() == Position.REARHAND) {
                labelRight.setText(ps.getTrump().toString());
              } else if (ps.getAuction().getWinner().getPosition() == Position.FOREHAND) {
                labelLeft.setText(ps.getTrump().toString());
              } else {
                labelMiddle.setText(ps.getTrump().toString());
              }
            }
          }
        }
      }
    });


  }


  /**
   * @author lstrauch
   */
  /*
   * (non-Javadoc)
   * 
   * @see interfaces.InGameInterface#itsYourTurn()
   */
  @Override
  public void itsYourTurn() {
    // TODO Auto-generated method stub
    Platform.runLater(new Runnable() {

      @Override
      public void run() {
        // TODO Auto-generated method stub
        rearrangeCardsLight(cardlist);
        if(main.getSettingsCon().getTrainingsmode()) {
          training.setLayoutY(388);
          if(LoginController.interfGL.getPlayer().isDeclarer() && LoginController.interfGL.getPlayer().getPosition() != Position.DEALER) {
          displayTraining("1.   Play your trumps! When the opponents lost all their trumps, you are unstoppable like a train without brakes.\n" + 
              "2.  Be brave! If a color was not played yet and you have an Ass, play it and save the points!\n" + 
              "3.  Save your own ASS and TEN! And you will be the winner then!\n" + 
              "4.  Have you seen ASS and TEN do not play this color again!\n" + 
              "5.  Count the Trumps! This is important like a condom at a One-Night-Stand.");
          training.toFront();
          } else if(!LoginController.interfGL.getPlayer().isDeclarer() && LoginController.interfGL.getPlayer().getPosition() != Position.DEALER){
            displayTraining("1. Be brave! If a Suit was not played yet and you have an Ass, play it and save the points!\n" + 
                "2.  Save your own ASS and TEN! And you will be the winner then!\n" + 
                "3.  Have you seen ASS and TEN do not play this color again!\n" + 
                "4.  Count the Trumps! This is important like a condom at a One-Night-Stand.\n" + 
                "5.  Keep the declarer in the middle!");
            training.toFront();
          }
        }
      }

    });
  }


  /**
   * lstrauch
   */

  /*
   * (non-Javadoc)
   * 
   * @see interfaces.InGameInterface#showSecondsLeftToPlayCard(int)
   */
  @Override
  public void showSecondsLeftToPlayCard(int seconds) {
    // TODO Auto-generated method stub

  }

  /**
   * lstrauch
   */
  /*
   * (non-Javadoc)
   * 
   * @see interfaces.InGameInterface#receivedNewBet(int, logic.Player)
   */
  @Override
  public void receivedNewBet(int bet, Player player) {
    // TODO Auto-generated method stub
    Platform.runLater(new Runnable() {

      @Override
      public void run() {
        // TODO Auto-generated method stub
        disableTraining();
        bubbleLeft.setImage(null);
        bubbleRight.setImage(null);
        bubbleUp.setImage(null);
        betRight.setText(null);
        betLeft.setText(null);
        betUp.setText(null);
        deletePane(paneBet);
        if (main.getGameSetCon().getGS().getNrOfPlayers() == 3) {
          if (LoginController.interfGL.getPlayer().getPosition() == Position.FOREHAND) {
            if (player.getPosition() == Position.MIDDLEHAND) {
              bubbleLeft.setImage(bubbleL);
              if (bet != -1) {
                betLeft.setText(String.valueOf(bet));
              } else {
                betLeft.setText("Pass");
              }
            } else if (player.getPosition() == Position.REARHAND) {
              bubbleRight.setImage(bubbleR);
              if (bet != -1) {
                betRight.setText(String.valueOf(bet));
              } else {
                betRight.setText("Pass");
              }
            }
          } else if (LoginController.interfGL.getPlayer().getPosition() == Position.REARHAND) {
            if (player.getPosition() == Position.MIDDLEHAND) {
              bubbleLeft.setImage(bubbleL);
              if (bet != -1) {
                betLeft.setText(String.valueOf(bet));
              } else {
                betLeft.setText("Pass");
              }
            } else if (player.getPosition() == Position.FOREHAND) {
              bubbleRight.setImage(bubbleR);
              if (bet != -1) {
                betRight.setText(String.valueOf(bet));
              } else {
                betRight.setText("Pass");
              }
            }
          } else if (LoginController.interfGL.getPlayer().getPosition() == Position.MIDDLEHAND) {
            if (player.getPosition() == Position.REARHAND) {
              bubbleLeft.setImage(bubbleL);
              if (bet != -1) {
                betLeft.setText(String.valueOf(bet));
              } else {
                betLeft.setText("Pass");
              }
            } else if (player.getPosition() == Position.FOREHAND) {
              bubbleRight.setImage(bubbleR);
              if (bet != -1) {
                betRight.setText(String.valueOf(bet));
              } else {
                betRight.setText("Pass");
              }
            }
          }


          // 4 Players:

        } else {
          if (LoginController.interfGL.getPlayer().getPosition() == Position.FOREHAND) {
            if (player.getPosition() == Position.MIDDLEHAND) {
              bubbleLeft.setImage(bubbleL);
              if (bet != -1) {
                betLeft.setText(String.valueOf(bet));
              } else {
                betLeft.setText("Pass");
              }
            } else if (player.getPosition() == Position.REARHAND) {
              bubbleUp.setImage(bubbleU);
              bubbleUp.toFront();
              if (bet != -1) {
                betUp.setText(String.valueOf(bet));
              } else {
                betUp.setText("Pass");
              }
            }
          } else if (LoginController.interfGL.getPlayer().getPosition() == Position.REARHAND) {
            if (player.getPosition() == Position.MIDDLEHAND) {
              bubbleRight.setImage(bubbleR);
              if (bet != -1) {
                betRight.setText(String.valueOf(bet));
              } else {
                betRight.setText("Pass");
              }
            } else if (player.getPosition() == Position.FOREHAND) {
              bubbleUp.setImage(bubbleU);
              bubbleUp.toFront();
              if (bet != -1) {
                betUp.setText(String.valueOf(bet));
              } else {
                betUp.setText("Pass");
              }
            }
          } else if (LoginController.interfGL.getPlayer().getPosition() == Position.MIDDLEHAND) {
            if (player.getPosition() == Position.FOREHAND) {
              bubbleRight.setImage(bubbleR);
              if (bet != -1) {
                betRight.setText(String.valueOf(bet));
              } else {
                betRight.setText("Pass");
              }
            } else if (player.getPosition() == Position.REARHAND) {
              bubbleLeft.setImage(bubbleL);
              if (bet != -1) {
                betLeft.setText(String.valueOf(bet));
              } else {
                betLeft.setText("Pass");
              }
            }
          } else {
            if (player.getPosition() == Position.REARHAND) {
              bubbleRight.setImage(bubbleR);
              if (bet != -1) {
                betRight.setText(String.valueOf(bet));
              } else {
                betRight.setText("Pass");
              }
            } else if (player.getPosition() == Position.FOREHAND) {
              bubbleLeft.setImage(bubbleL);
              if (bet != -1) {
                betLeft.setText(String.valueOf(bet));
              } else {
                betLeft.setText("Pass");
              } 
              
            } else {
              bubbleUp.setImage(bubbleU);
              bubbleUp.toFront();
              if (bet != -1) {
                betUp.setText(String.valueOf(bet));
              } else {
                betUp.setText("Pass");
              }
            }
          }
        }


      }

    });


  }

  /*
   * (non-Javadoc)
   * 
   * @see interfaces.InGameInterface#showPossibleCards(java.util.List)
   */
  @Override
  public void showPossibleCards(List<Card> cards) {
    // TODO Auto-generated method stub
    rearrangeCardsNotPossible(cards);
  }

  /*
   * (non-Javadoc)
   * 
   * @see interfaces.InGameInterface#showOpen(logic.Player)
   */
  @Override
  public void showOpen(Player player) {
    // TODO Auto-generated method stub
    System.out.println("Open");

    if (main.getGameSetCon().getGS().getNrOfPlayers() == 4) {
      if (LoginController.interfGL.getPlayer().getPosition() == Position.FOREHAND) {
        if (player.getPosition() == Position.MIDDLEHAND) {
          rearrangeCardsLeft(player.getHand());
        } else if (player.getPosition() == Position.REARHAND) {
          rearrangeCardsUp(player.getHand());
        }
      } else if (LoginController.interfGL.getPlayer().getPosition() == Position.REARHAND) {
        if (player.getPosition() == Position.MIDDLEHAND) {
          rearrangeCardsRight(player.getHand());
        } else if (player.getPosition() == Position.FOREHAND) {
          rearrangeCardsUp(player.getHand());
        }
      } else if (LoginController.interfGL.getPlayer().getPosition() == Position.MIDDLEHAND) {
        if (player.getPosition() == Position.FOREHAND) {
          rearrangeCardsRight(player.getHand());
        } else if (player.getPosition() == Position.REARHAND) {
          rearrangeCardsLeft(player.getHand());
        }
      } else {
        if (player.getPosition() == Position.FOREHAND) {
          rearrangeCardsLeft(player.getHand());
        } else if (player.getPosition() == Position.REARHAND) {
          rearrangeCardsRight(player.getHand());
        } else {
          rearrangeCardsUp(player.getHand());
        }
      }
    } else {
      if (LoginController.interfGL.getPlayer().getPosition() == Position.FOREHAND) {
        if (player.getPosition() == Position.MIDDLEHAND) {
          rearrangeCardsLeft(player.getHand());
        } else if (player.getPosition() == Position.REARHAND) {
          rearrangeCardsRight(player.getHand());
        }
      } else if (LoginController.interfGL.getPlayer().getPosition() == Position.REARHAND) {
        if (player.getPosition() == Position.MIDDLEHAND) {
          rearrangeCardsRight(player.getHand());
        } else if (player.getPosition() == Position.FOREHAND) {
          rearrangeCardsLeft(player.getHand());
        }
      } else if (LoginController.interfGL.getPlayer().getPosition() == Position.MIDDLEHAND) {
        if (player.getPosition() == Position.FOREHAND) {
          rearrangeCardsLeft(player.getHand());
        } else if (player.getPosition() == Position.MIDDLEHAND) {
          rearrangeCardsRight(player.getHand());
        }
      }
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see interfaces.InGameInterface#askToPlayCard(int)
   */
  @Override
  public int askToPlayCard(int timeToPlay, PlayState ps) {
    // TODO Auto-generated method stub
    int time = timeToPlay;
    long t = System.currentTimeMillis();
    long end = (t / 1000) + time;

    while (clicked == false) {
      // TODO Auto-generated method stub
      if (main.getLobbyCon().getGS().isLimitedTime()) {
        while (System.currentTimeMillis() / 1000 < end && clicked == false) {
          MouseHandler();
        }
        ret[0] = playRandomCard(ps, LoginController.interfGL.getPlayer());
        random = true;
        clicked = true;
      } else {
        MouseHandler();

      }
    }
    clicked = false;
    return ret[0];
  }


  public static int playRandomCard(PlayState ps, Player player) {
    List<Card> cards = LoginController.interfGL.getPlayer().getHand();
    List<Card> possibleCards = new ArrayList<Card>();


    if (ps.getCurrentTrick().getTrickCards().size() > 0) {
      for (int i = 0; i < cards.size(); i++) {
        try {
          if (ClientLogic.checkIfCardPossible(cards.get(i), ps.getCurrentTrick().getFirstCard(), ps,
              player)) {
            possibleCards.add(cards.get(i));
          }
        } catch (LogicException e) {
          e.printStackTrace();
        }
      }
    } else {
      possibleCards = cards;
    }

    int rnd = (int) (Math.random() * possibleCards.size());
    Card playCard = possibleCards.get(rnd);
    int index = cards.indexOf(playCard);

    return index;
  }

  /*
   * (non-Javadoc)
   * 
   * @see interfaces.InGameInterface#showScore(java.util.List)
   */
  @Override
  public void showScore(List<Player> player) {
    // TODO Auto-generated method stub
    System.out.println("Player name: " + player.get(2).getName());
    if (player.size() == 3) {
      this.pl1 = player.get(1);
      this.pl2 = player.get(2);
      main.displayLeaderboard3();
    } else {
      this.pl1 = player.get(1);
      this.pl2 = player.get(2);
      this.pl3 = player.get(3);
      main.displayLeaderboard4();
      main.getLead4Con().start();
    }

  }

  /**
   * lstrauch
   */
  /*
   * (non-Javadoc)
   * 
   * @see interfaces.InGameInterface#receivedNewCard(logic.Card, logic.Player)
   */
  @Override
  public void receivedNewCard(Card card, Player player) {
    // TODO Auto-generated method stub
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        if (main.getLobbyCon().getGS().getNrOfPlayers() == 3) {
          rearrangeCardsDark(cardlist);
          if (LoginController.interfGL.getPlayer().getPosition() == Position.FOREHAND) {
            if (player.getPosition() == Position.MIDDLEHAND) {
              s1.setImage(inte.getImage(card.getColour().toString().toLowerCase(),
                  card.getNumber().toString().toLowerCase()));
              removeOpponentCardsLeft(countl);
              countl--;
              s1.toFront();
            } else if (player.getPosition() == Position.REARHAND) {
              s3.setImage(inte.getImage(card.getColour().toString().toLowerCase(),
                  card.getNumber().toString().toLowerCase()));
              removeOpponentCardsRight(countr);
              countr--;
              s3.toFront();
            } else {
              s2.setImage(inte.getImage(card.getColour().toString().toLowerCase(),
                  card.getNumber().toString().toLowerCase()));
              s2.toFront();
            }
          } else if (LoginController.interfGL.getPlayer().getPosition() == Position.REARHAND) {
            if (player.getPosition() == Position.MIDDLEHAND) {
              s3.setImage(inte.getImage(card.getColour().toString().toLowerCase(),
                  card.getNumber().toString().toLowerCase()));
              removeOpponentCardsRight(countr);
              countr--;
              s3.toFront();
            } else if (player.getPosition() == Position.FOREHAND) {
              s1.setImage(inte.getImage(card.getColour().toString().toLowerCase(),
                  card.getNumber().toString().toLowerCase()));
              removeOpponentCardsLeft(countl);
              countl--;
              s1.toFront();
            } else {
              s2.setImage(inte.getImage(card.getColour().toString().toLowerCase(),
                  card.getNumber().toString().toLowerCase()));
              s2.toFront();
            }
          } else {
            if (player.getPosition() == Position.REARHAND) {
              s1.setImage(inte.getImage(card.getColour().toString().toLowerCase(),
                  card.getNumber().toString().toLowerCase()));
              removeOpponentCardsLeft(countl);
              countl--;
              s1.toFront();
            } else if (player.getPosition() == Position.FOREHAND) {
              s3.setImage(inte.getImage(card.getColour().toString().toLowerCase(),
                  card.getNumber().toString().toLowerCase()));
              removeOpponentCardsRight(countr);
              countr--;
              s3.toFront();
            } else {
              s2.setImage(inte.getImage(card.getColour().toString().toLowerCase(),
                  card.getNumber().toString().toLowerCase()));
              s2.toFront();
            }
          }


          // 4 Players:

        } else {
          System.out.println("Recieved ne card");
          rearrangeCardsDark(cardlist);
          if (LoginController.interfGL.getPlayer().getPosition() == Position.FOREHAND) {
            if (player.getPosition() == Position.MIDDLEHAND) {
              s1.setImage(inte.getImage(card.getColour().toString().toLowerCase(),
                  card.getNumber().toString().toLowerCase()));
              removeOpponentCardsLeft(countl);
              countl--;
              s1.toFront();
            } else if (player.getPosition() == Position.REARHAND) {
              s3.setImage(inte.getImage(card.getColour().toString().toLowerCase(),
                  card.getNumber().toString().toLowerCase()));
              removeOpponentCardsRight(countr);
              countr--;
              s3.toFront();
            } else {
              s2.setImage(inte.getImage(card.getColour().toString().toLowerCase(),
                  card.getNumber().toString().toLowerCase()));
              s2.toFront();
            }
          } else if (LoginController.interfGL.getPlayer().getPosition() == Position.REARHAND) {
            if (player.getPosition() == Position.MIDDLEHAND) {
              s3.setImage(inte.getImage(card.getColour().toString().toLowerCase(),
                  card.getNumber().toString().toLowerCase()));
              removeOpponentCardsRight(countr);
              countr--;
              s3.toFront();
            } else if (player.getPosition() == Position.FOREHAND) {
              s2.setImage(inte.getImage(card.getColour().toString().toLowerCase(),
                  card.getNumber().toString().toLowerCase()));
              removeOpponentCardsLeft(countl);
              countl--;
              s1.toFront();
            } else {
              s2.setImage(inte.getImage(card.getColour().toString().toLowerCase(),
                  card.getNumber().toString().toLowerCase()));
              s2.toFront();
            }
          } else if (LoginController.interfGL.getPlayer().getPosition() == Position.MIDDLEHAND) {
            if (player.getPosition() == Position.FOREHAND) {
              s1.setImage(inte.getImage(card.getColour().toString().toLowerCase(),
                  card.getNumber().toString().toLowerCase()));
              removeOpponentCardsLeft(countl);
              countl--;
              s1.toFront();
            } else if (player.getPosition() == Position.MIDDLEHAND) {
              s2.setImage(inte.getImage(card.getColour().toString().toLowerCase(),
                  card.getNumber().toString().toLowerCase()));
              removeOpponentCardsRight(countr);
              countr--;
              s2.toFront();
            } else {
              s3.setImage(inte.getImage(card.getColour().toString().toLowerCase(),
                  card.getNumber().toString().toLowerCase()));
              s3.toFront();
            }
          } else {
            if (player.getPosition() == Position.REARHAND) {
              s3.setImage(inte.getImage(card.getColour().toString().toLowerCase(),
                  card.getNumber().toString().toLowerCase()));
              removeOpponentCardsLeft(countl);
              countl--;
              s3.toFront();
            } else if (player.getPosition() == Position.FOREHAND) {
              s1.setImage(inte.getImage(card.getColour().toString().toLowerCase(),
                  card.getNumber().toString().toLowerCase()));
              removeOpponentCardsRight(countr);
              countr--;
              s1.toFront();
            } else {
              s2.setImage(inte.getImage(card.getColour().toString().toLowerCase(),
                  card.getNumber().toString().toLowerCase()));
              s2.toFront();
            }
          }
        }
        if (random) {
          timeUp.setText("Time's up! Random card was played.");
        } else {
          timeUp.setText(null);
        }
        random = false;
        if(main.getLobbyCon().getGS().isEnableKontra()) {
          mainPane.getChildren().remove(kontra);
        }
      }
    });

  }

  /**
   * lstrauch
   */
  /*
   * (non-Javadoc)
   * 
   * @see interfaces.InGameInterface#askToSetPlayState(logic.PlayState)
   */
  @Override
  public PlayState askToSetPlayState(PlayState ps) {
    // TODO Auto-generated method stub
    while (setSettings == false) {
      ButtonListenerPlaySettings(ps);

    }
    return ps;
  }

  /**
   * 
   * 
   * 
   * 
   * 
   * Methoden um im Menü auf die verschiedenen Buttons clicken zu können und zum jeweiligen Screen
   * zurück zu gelangen
   * 
   * @author lstrauch
   * 
   * 
   * 
   * 
   */


  /**
   * @author lstrauch
   */
  @FXML
  public void help() {
    main.displayHelp();
  }
  
  public void displayTraining(String s) {
    training.setStyle(
        "-fx-background-color: tan; -fx-background-radius: 10; -fx-border-color: peru; -fx-border-radius: 10");
    training.setText(s);
  }
  
  public void disableTraining() {
    training.setStyle(null);
    training.setText(null);
  }
  
  public void displayKontra() {
    kontra.setText("KONTRA");
    kontra.setStyle(
        "-fx-background-color: peru; -fx-background-radius: 10; -fx-border-color: back; -fx-border-radius: 10");
  }
  
  public void deleteKontra() {
    kontra.setText(null);
    kontra.setStyle(null);
  }
  
  public void displayRekontra() {
    kontra.setText("KONTRA");
    kontra.setStyle(
        "-fx-background-color: peru; -fx-background-radius: 10; -fx-border-color: back; -fx-border-radius: 10");
  }



  /**
   * @author lstrauch
   * @param size
   * @param list
   */
  public void rearrangeCardsLight(List<Card> list) {
    for (int i = 0; i < list.size(); i++) {
      cArray[i].setImage(inte.getImage(list.get(i).getColour().toString().toLowerCase(),
          (list.get(i).getNumber().toString().toLowerCase())));
    }
    if (list.size() != 10) {
      for (int i = list.size(); i < 10; i++) {
        cArray[i].setImage(null);
      }
    }
  }



  public void rearrangeCardsDark(List<Card> list) {
    for (int i = 0; i < list.size(); i++) {
      cArray[i].setImage(inte.getImageDarker(list.get(i).getColour().toString().toLowerCase(),
          (list.get(i).getNumber().toString().toLowerCase())));
    }
    if (list.size() != 10) {
      for (int i = list.size(); i < 10; i++) {
        cArray[i].setImage(null);
      }
    }
  }

  public void rearrangeCardsLeft(List<Card> list) {
    for (int i = 0; i < list.size(); i++) {
      lArray[i].setImage(inte.getImage(list.get(i).getColour().toString().toLowerCase(),
          (list.get(i).getNumber().toString().toLowerCase())));
    }
    if (list.size() != 10) {
      for (int i = list.size(); i < 10; i++) {
        lArray[i].setImage(null);
      }
    }
  }

  public void rearrangeCardsRight(List<Card> list) {
    for (int i = 0; i < list.size(); i++) {
      rArray[i].setImage(inte.getImage(list.get(i).getColour().toString().toLowerCase(),
          (list.get(i).getNumber().toString().toLowerCase())));
    }
    if (list.size() != 10) {
      for (int i = list.size(); i < 10; i++) {
        rArray[i].setImage(null);
      }
    }
  }

  public void rearrangeCardsUp(List<Card> list) {
    for (int i = 0; i < list.size(); i++) {
      oArray[i].setImage(inte.getImage(list.get(i).getColour().toString().toLowerCase(),
          (list.get(i).getNumber().toString().toLowerCase())));
    }
    if (list.size() != 10) {
      for (int i = list.size(); i < 10; i++) {
        oArray[i].setImage(null);
      }
    }
  }

  public void rearrangeCardsNotPossible(List<Card> list) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i) != null) {
        System.out.println("cArray[" + i + "] - Dark: " + cArray[i]);
        cArray[i].setImage(inte.getImageDarker(list.get(i).getColour().toString().toLowerCase(),
            (list.get(i).getNumber().toString().toLowerCase())));

      } else {
        System.out.println("cArray[" + i + "] - Light: " + cArray[i]);
        cArray[i].setImage(inte.getImage(cardlist.get(i).getColour().toString().toLowerCase(),
            (cardlist.get(i).getNumber().toString().toLowerCase())));
      }
    }
    if (list.get(list.size() - 1) != null) {
      cArray[list.size() - 1].setImage(
          inte.getImageDarker(list.get(list.size() - 1).getColour().toString().toLowerCase(),
              (list.get(list.size() - 1).getNumber().toString().toLowerCase())));
    } else {
      cArray[list.size() - 1]
          .setImage(inte.getImage(list.get(list.size() - 1).getColour().toString().toLowerCase(),
              (list.get(list.size() - 1).getNumber().toString().toLowerCase())));
    }
  }

  /**
   * @author lstrauch
   * @param anz
   */
  public void removeOpponentCardsRight(int anz) {
    switch (anz) {
      case 1:
        mainPane.getChildren().remove(r1);
        break;
      case 2:
        mainPane.getChildren().remove(r2);
        break;
      case 3:
        mainPane.getChildren().remove(r3);
        break;
      case 4:
        mainPane.getChildren().remove(r4);
        break;
      case 5:
        mainPane.getChildren().remove(r5);
        break;
      case 6:
        mainPane.getChildren().remove(r6);
        break;
      case 7:
        mainPane.getChildren().remove(r7);
        break;
      case 8:
        mainPane.getChildren().remove(r8);
        break;
      case 9:
        mainPane.getChildren().remove(r9);
        break;
      case 10:
        mainPane.getChildren().remove(r10);
        break;
    }
  }

  /**
   * @author lstrauch
   * @param anz
   */
  public void removeOpponentCardsLeft(int anz) {
    switch (anz) {
      case 1:
        mainPane.getChildren().remove(l1);
        break;
      case 2:
        mainPane.getChildren().remove(l2);
        break;
      case 3:
        mainPane.getChildren().remove(l3);
        break;
      case 4:
        mainPane.getChildren().remove(l4);
        break;
      case 5:
        mainPane.getChildren().remove(l5);
        break;
      case 6:
        mainPane.getChildren().remove(l6);
        break;
      case 7:
        mainPane.getChildren().remove(l7);
        break;
      case 8:
        mainPane.getChildren().remove(l8);
        break;
      case 9:
        mainPane.getChildren().remove(l9);
        break;
      case 10:
        mainPane.getChildren().remove(l10);
        break;
    }
  }

  /**
   * @author lstrauch
   * @param size
   * @param list
   */


  /**
   * 
   * 
   * 
   * 
   * 
   * 
   * 
   * Different ButtonListener
   * 
   * 
   * 
   * 
   * 
   * 
   * 
   * 
   * @return
   */



  /**
   * Hier wird definiert was passiert wenn man auf eine seiner jeweiligen Spielkarten klickt
   * 
   * ImageView sets Image to null Method AnzStichblatte wird aufgerufen, sodass Karten auf Stich
   * gelegt werden gibt an Logik den Index der geklickten Karte weiter
   * 
   * @author lstrauch
   */

  public void MouseHandler() {
    c1.setOnMousePressed(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        ret[0] = 0;
        System.out.println("C1 DRAUFGEKLICKT");
        clicked = true;
      }
    });
    c2.setOnMousePressed(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        ret[0] = 1;
        clicked = true;
      }
    });
    c3.setOnMousePressed(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        ret[0] = 2;
        clicked = true;
      }
    });
    c4.setOnMousePressed(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        ret[0] = 3;
        clicked = true;
      }
    });
    c5.setOnMousePressed(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        ret[0] = 4;
        clicked = true;
      }
    });
    c6.setOnMousePressed(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        ret[0] = 5;
        clicked = true;
      }
    });
    c7.setOnMousePressed(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        ret[0] = 6;
        clicked = true;

      }
    });
    c8.setOnMousePressed(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        ret[0] = 7;
        clicked = true;
      }
    });
    c9.setOnMousePressed(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        ret[0] = 8;
        clicked = true;
      }
    });
    c10.setOnMousePressed(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        ret[0] = 9;
        clicked = true;
      }
    });
  }



  /**
   * @author lstrauch
   * @return
   */
  public void ButtonListener() {
    qu.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        System.out.println("qu");
      }
    });
    pass.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        b = false;
        notpressed = false;
      }
    });
    betB.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        notpressed = false;
        b = true;
      }
    });
    submit.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {}
    });
  }


  /**
   * ButtonListener AuctionWinnerScree-buttons
   * 
   * @param ps
   */
  /**
   * @author lstrauch
   * @param ps
   */
  public PlayState ButtonListenerPlaySettings(PlayState ps) {
    boolean[] pressed = new boolean[1];
    diamonds.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        ps.setPlayMode(PlayMode.SUIT);
        ps.setTrump(Colour.DIAMONDS);
        pressed[0] = true;
      }
    });
    hearts.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        ps.setPlayMode(PlayMode.SUIT);
        ps.setTrump(Colour.HEARTS);
        pressed[0] = true;
      }
    });
    clubs.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        ps.setPlayMode(PlayMode.SUIT);
        ps.setTrump(Colour.CLUBS);
        pressed[0] = true;
      }
    });
    spades.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        ps.setPlayMode(PlayMode.SUIT);
        ps.setTrump(Colour.SPADES);
        pressed[0] = true;
      }
    });
    grand.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        ps.setPlayMode(PlayMode.GRAND);
        pressed[0] = true;
      }
    });
    nullG.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        ps.setPlayMode(PlayMode.NULL);
        pressed[0] = true;
      }
    });
    ouvert.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        ps.setOpen(true);
      }
    });
    schneider.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        ps.setSchneider(true);
      }
    });
    schwarz.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        ps.setSchwarzAnnounced(true);
      }
    });
    submit.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        if (pressed[0] == true) {
          setSettings = true;
        }
      }
    });

    return ps;
  }


  /**
   * Button Listener WantSkat-buttons
   * 
   * @param ps
   */
  /**
   * @author lstrauch
   * @param ps
   * @return
   */
  public void ButtonListenrWantSkat() {
    yes.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        wantskat = true;
        decidepressed = true;
      }
    });
    no.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        wantskat = false;
        decidepressed = true;
      }
    });
  }


  /**
   * Buttonlistener Chat-button
   */
  /**
   * @author lstrauch
   */
  public void chatButtonListener() {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        sendB.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent e) {
            sendChat();
          }
        });
        pf.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {

          @Override
          public void handle(MouseEvent event) {
            if (pf.getImage().equals(pfUnten)) {
              displayChatScreenOpen();
            } else {
              displayChatClosed();
            }
            System.out.println(pf.getImage());
          }
        });
      }
    });
  }


  /**
   * ButtonListener to switch Skat
   */
  /**
   * @author lstrauch
   * @param ps
   */
  public void switchSkatListener(PlayState ps) {

    c1.setOnMousePressed(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        if (cardlist.size() == 11) {
          // Nur eine Karte auf dem Skat
          if (da[0] == false) {
            sk1.setImage(c1.getImage());
            skat.set(0, cardlist.get(1));
            cardlist.remove(cardlist.get(0));
            LoginController.interfGL.sortHand(ps, cardlist);
            displayCards(cardlist.size(), cardlist);
            da[0] = true;
          } else if (da[1] == false) {
            sk2.setImage(c1.getImage());
            skat.set(1, cardlist.get(0));
            cardlist.remove(cardlist.get(1));
            LoginController.interfGL.sortHand(ps, cardlist);
            displayCards(cardlist.size(), cardlist);
            da[1] = true;
          }
        } else if (cardlist.size() == 12) {
          // Skat leer
          sk1.setImage(c1.getImage());
          skat.set(0, cardlist.get(0));
          cardlist.remove(cardlist.get(0));
          LoginController.interfGL.sortHand(ps, cardlist);
          displayCards(cardlist.size(), cardlist);
          da[0] = true;
        }
      }
    });
    c2.setOnMousePressed(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        if (cardlist.size() == 11) {
          // Nur eine Karte auf dem Skat
          if (da[0] == false) {
            sk1.setImage(c2.getImage());
            skat.set(0, cardlist.get(2));
            cardlist.remove(cardlist.get(2));
            LoginController.interfGL.sortHand(ps, cardlist);
            displayCards(cardlist.size(), cardlist);
            da[0] = true;
          } else if (da[1] == false) {
            sk2.setImage(c2.getImage());
            skat.set(1, cardlist.get(2));
            cardlist.remove(cardlist.get(2));
            LoginController.interfGL.sortHand(ps, cardlist);
            displayCards(cardlist.size(), cardlist);
            da[1] = true;
          }
        } else if (cardlist.size() == 12) {
          // Skat leer
          sk1.setImage(c2.getImage());
          skat.set(0, cardlist.get(2));
          cardlist.remove(cardlist.get(2));
          LoginController.interfGL.sortHand(ps, cardlist);
          displayCards(cardlist.size(), cardlist);
          da[0] = true;
        }
      }
    });
    c3.setOnMousePressed(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        if (cardlist.size() == 11) {
          // Nur eine Karte auf dem Skat
          if (da[0] == false) {
            sk1.setImage(c3.getImage());
            skat.set(0, cardlist.get(3));
            cardlist.remove(cardlist.get(3));
            LoginController.interfGL.sortHand(ps, cardlist);
            displayCards(cardlist.size(), cardlist);
            da[0] = true;
          } else if (da[1] == false) {
            sk2.setImage(c3.getImage());
            skat.set(1, cardlist.get(3));
            cardlist.remove(cardlist.get(3));
            LoginController.interfGL.sortHand(ps, cardlist);
            displayCards(cardlist.size(), cardlist);
            da[1] = true;
          }
        } else if (cardlist.size() == 12) {
          // Skat leer
          sk1.setImage(c3.getImage());
          skat.set(0, cardlist.get(3));
          cardlist.remove(cardlist.get(3));
          LoginController.interfGL.sortHand(ps, cardlist);
          displayCards(cardlist.size(), cardlist);
          da[0] = true;
        }
      }
    });
    c4.setOnMousePressed(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        if (cardlist.size() == 11) {
          // Nur eine Karte auf dem Skat
          if (da[0] == false) {
            sk1.setImage(c4.getImage());
            skat.set(0, cardlist.get(4));
            cardlist.remove(cardlist.get(4));
            LoginController.interfGL.sortHand(ps, cardlist);
            displayCards(cardlist.size(), cardlist);
            da[0] = true;
          } else if (da[1] == false) {
            sk2.setImage(c4.getImage());
            skat.set(1, cardlist.get(4));
            cardlist.remove(cardlist.get(4));
            LoginController.interfGL.sortHand(ps, cardlist);
            displayCards(cardlist.size(), cardlist);
            da[1] = true;
          }
        } else if (cardlist.size() == 12) {
          // Skat leer
          sk1.setImage(c4.getImage());
          skat.set(0, cardlist.get(4));
          cardlist.remove(cardlist.get(4));
          LoginController.interfGL.sortHand(ps, cardlist);
          displayCards(cardlist.size(), cardlist);
          da[0] = true;
        }
      }
    });
    c5.setOnMousePressed(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        if (cardlist.size() == 11) {
          // Nur eine Karte auf dem Skat
          if (da[0] == false) {
            sk1.setImage(c5.getImage());
            skat.set(0, cardlist.get(5));
            cardlist.remove(cardlist.get(5));
            LoginController.interfGL.sortHand(ps, cardlist);
            displayCards(cardlist.size(), cardlist);
            da[0] = true;
          } else if (da[1] == false) {
            sk2.setImage(c5.getImage());
            skat.set(1, cardlist.get(4));
            cardlist.remove(cardlist.get(5));
            LoginController.interfGL.sortHand(ps, cardlist);
            displayCards(cardlist.size(), cardlist);
            da[1] = true;
          }
        } else if (cardlist.size() == 12) {
          // Skat leer
          sk1.setImage(c5.getImage());
          skat.set(0, cardlist.get(5));
          cardlist.remove(cardlist.get(5));
          LoginController.interfGL.sortHand(ps, cardlist);
          displayCards(cardlist.size(), cardlist);
          da[0] = true;
        }
      }
    });
    c6.setOnMousePressed(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        if (cardlist.size() == 11) {
          // Nur eine Karte auf dem Skat
          if (da[0] == false) {
            sk1.setImage(c6.getImage());
            skat.set(0, cardlist.get(6));
            cardlist.remove(cardlist.get(6));
            LoginController.interfGL.sortHand(ps, cardlist);
            displayCards(cardlist.size(), cardlist);
            da[0] = true;
          } else if (da[1] == false) {
            sk2.setImage(c6.getImage());
            skat.set(1, cardlist.get(6));
            cardlist.remove(cardlist.get(6));
            LoginController.interfGL.sortHand(ps, cardlist);
            displayCards(cardlist.size(), cardlist);
            da[1] = true;
          }
        } else if (cardlist.size() == 12) {
          // Skat leer
          sk1.setImage(c6.getImage());
          skat.set(0, cardlist.get(6));
          cardlist.remove(cardlist.get(6));
          LoginController.interfGL.sortHand(ps, cardlist);
          displayCards(cardlist.size(), cardlist);
          da[0] = true;
        }
      }
    });
    c7.setOnMousePressed(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        if (cardlist.size() == 11) {
          // Nur eine Karte auf dem Skat
          if (da[0] == false) {
            sk1.setImage(c7.getImage());
            skat.set(0, cardlist.get(7));
            cardlist.remove(cardlist.get(7));
            LoginController.interfGL.sortHand(ps, cardlist);
            displayCards(cardlist.size(), cardlist);
            da[0] = true;
          } else if (da[1] == false) {
            sk2.setImage(c7.getImage());
            skat.set(1, cardlist.get(7));
            cardlist.remove(cardlist.get(7));
            LoginController.interfGL.sortHand(ps, cardlist);
            displayCards(cardlist.size(), cardlist);
            da[1] = true;
          }
        } else if (cardlist.size() == 12) {
          // Skat leer
          sk1.setImage(c7.getImage());
          skat.set(0, cardlist.get(7));
          cardlist.remove(cardlist.get(7));
          LoginController.interfGL.sortHand(ps, cardlist);
          displayCards(cardlist.size(), cardlist);
          da[0] = true;
        }
      }
    });
    c8.setOnMousePressed(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        if (cardlist.size() == 11) {
          // Nur eine Karte auf dem Skat
          if (da[0] == false) {
            sk1.setImage(c8.getImage());
            skat.set(0, cardlist.get(8));
            cardlist.remove(cardlist.get(8));
            LoginController.interfGL.sortHand(ps, cardlist);
            displayCards(cardlist.size(), cardlist);
            da[0] = true;
          } else if (da[1] == false) {
            sk2.setImage(c8.getImage());
            skat.set(1, cardlist.get(8));
            cardlist.remove(cardlist.get(8));
            LoginController.interfGL.sortHand(ps, cardlist);
            displayCards(cardlist.size(), cardlist);
            da[1] = true;
          }
        } else if (cardlist.size() == 12) {
          // Skat leer
          sk1.setImage(c8.getImage());
          skat.set(0, cardlist.get(8));
          cardlist.remove(cardlist.get(8));
          LoginController.interfGL.sortHand(ps, cardlist);
          displayCards(cardlist.size(), cardlist);
          da[0] = true;
        }
      }
    });
    c9.setOnMousePressed(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        if (cardlist.size() == 11) {
          // Nur eine Karte auf dem Skat
          if (da[0] == false) {
            sk1.setImage(c9.getImage());
            skat.set(0, cardlist.get(9));
            cardlist.remove(cardlist.get(9));
            LoginController.interfGL.sortHand(ps, cardlist);
            displayCards(cardlist.size(), cardlist);
            da[0] = true;
          } else if (da[1] == false) {
            sk2.setImage(c9.getImage());
            skat.set(1, cardlist.get(9));
            cardlist.remove(cardlist.get(9));
            LoginController.interfGL.sortHand(ps, cardlist);
            displayCards(cardlist.size(), cardlist);
            da[1] = true;
          }
        } else if (cardlist.size() == 12) {
          // Skat leer
          sk1.setImage(c9.getImage());
          skat.set(0, cardlist.get(9));
          cardlist.remove(cardlist.get(9));
          LoginController.interfGL.sortHand(ps, cardlist);
          displayCards(cardlist.size(), cardlist);
          da[0] = true;
        }
      }
    });
    c10.setOnMousePressed(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        if (cardlist.size() == 11) {
          // Nur eine Karte auf dem Skat
          if (da[0] == false) {
            sk1.setImage(c10.getImage());
            skat.set(0, cardlist.get(10));
            cardlist.remove(cardlist.get(10));
            LoginController.interfGL.sortHand(ps, cardlist);
            displayCards(cardlist.size(), cardlist);
            da[0] = true;
          } else if (da[1] == false) {
            sk2.setImage(c10.getImage());
            skat.set(1, cardlist.get(10));
            cardlist.remove(cardlist.get(10));
            LoginController.interfGL.sortHand(ps, cardlist);
            displayCards(cardlist.size(), cardlist);
            da[1] = true;
          }
        } else if (cardlist.size() == 12) {
          // Skat leer
          sk1.setImage(c10.getImage());
          skat.set(0, cardlist.get(10));
          cardlist.remove(cardlist.get(10));
          LoginController.interfGL.sortHand(ps, cardlist);
          displayCards(cardlist.size(), cardlist);
          da[0] = true;
        }
      }
    });
    extra1.setOnMousePressed(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        if (cardlist.size() == 11) {
          // Nur eine Karte auf dem Skat
          if (da[0] == false) {
            sk1.setImage(extra1.getImage());
            skat.set(0, cardlist.get(0));
            cardlist.remove(cardlist.get(0));
            LoginController.interfGL.sortHand(ps, cardlist);
            displayCards(cardlist.size(), cardlist);
            da[0] = true;
          } else if (da[1] == false) {
            sk2.setImage(extra1.getImage());
            skat.set(1, cardlist.get(0));
            cardlist.remove(cardlist.get(0));
            LoginController.interfGL.sortHand(ps, cardlist);
            displayCards(cardlist.size(), cardlist);
            da[1] = true;
          }
        } else if (cardlist.size() == 12) {
          // Skat leer
          sk1.setImage(extra1.getImage());
          skat.set(0, cardlist.get(0));
          cardlist.remove(cardlist.get(0));
          LoginController.interfGL.sortHand(ps, cardlist);
          displayCards(cardlist.size(), cardlist);
          da[0] = true;
        }
      }
    });
    extra2.setOnMousePressed(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        if (cardlist.size() == 11) {
          // Nur eine Karte auf dem Skat
          if (da[0] == false) {
            sk2.setImage(extra2.getImage());
            skat.set(0, cardlist.get(10));
            cardlist.remove(cardlist.get(10));
            LoginController.interfGL.sortHand(ps, cardlist);
            displayCards(cardlist.size(), cardlist);
            da[0] = true;
          } else if (da[1] == false) {
            sk2.setImage(extra2.getImage());
            skat.set(1, cardlist.get(10));
            cardlist.remove(cardlist.get(10));
            LoginController.interfGL.sortHand(ps, cardlist);
            displayCards(cardlist.size(), cardlist);
            da[1] = true;
          }
        } else if (cardlist.size() == 12) {
          // Skat leer
          sk1.setImage(extra2.getImage());
          skat.set(0, cardlist.get(11));
          cardlist.remove(cardlist.get(11));
          LoginController.interfGL.sortHand(ps, cardlist);
          displayCards(cardlist.size(), cardlist);
          da[0] = true;
        }
      }
    });
    sk1.setOnMousePressed(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        if (cardlist.size() == 10) {
          // Beide Karten auf dem Skat
          cardlist.add(skat.get(0));
          // cardlist.add(0, skat.get(0));
          LoginController.interfGL.sortHand(ps, cardlist);
          displayCards(cardlist.size(), cardlist);
          sk1.setImage(noCard);
          da[0] = false;
        } else if (cardlist.size() == 11) {
          cardlist.add(skat.get(0));
          // cardlist.add(11, skat.get(0));
          LoginController.interfGL.sortHand(ps, cardlist);
          displayCards(cardlist.size(), cardlist);
          sk1.setImage(noCard);
          da[0] = false;
        }
      }
    });
    sk2.setOnMousePressed(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        if (cardlist.size() == 10) {
          // Beide Karten auf dem Skat
          cardlist.add(skat.get(1));
          // cardlist.add(skat.get(1));
          LoginController.interfGL.sortHand(ps, cardlist);
          displayCards(cardlist.size(), cardlist);
          sk2.setImage(noCard);
          da[1] = false;
        } else if (cardlist.size() == 11) {
          cardlist.add(skat.get(1));
          // cardlist.add(skat.get(1));
          LoginController.interfGL.sortHand(ps, cardlist);
          displayCards(cardlist.size(), cardlist);
          sk2.setImage(noCard);
          da[1] = false;
        }
      }
    });
    ok.setOnMousePressed(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        skatLogic.add(0, skat.get(0));
        skatLogic.add(1, skat.get(1));
        skatpressed = true;
      }
    });

  }

  /**
   * @author lstrauch
   * @param anz
   * @param cardlist2
   */
  public void displayCards(int anz, List<Card> cardlist2) {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        switch (anz) {
          case (10):
            extra1.setImage(null);
            c1.setImage(inte.getImage(cardlist2.get(0).getColour().toString().toLowerCase(),
                (cardlist2.get(0).getNumber().toString().toLowerCase())));
            c2.setImage(inte.getImage(cardlist2.get(1).getColour().toString().toLowerCase(),
                (cardlist2.get(1).getNumber().toString().toLowerCase())));
            c3.setImage(inte.getImage(cardlist2.get(2).getColour().toString().toLowerCase(),
                (cardlist2.get(2).getNumber().toString().toLowerCase())));
            c4.setImage(inte.getImage(cardlist2.get(3).getColour().toString().toLowerCase(),
                (cardlist2.get(3).getNumber().toString().toLowerCase())));
            c5.setImage(inte.getImage(cardlist2.get(4).getColour().toString().toLowerCase(),
                (cardlist2.get(4).getNumber().toString().toLowerCase())));
            c6.setImage(inte.getImage(cardlist2.get(5).getColour().toString().toLowerCase(),
                (cardlist2.get(5).getNumber().toString().toLowerCase())));
            c7.setImage(inte.getImage(cardlist2.get(6).getColour().toString().toLowerCase(),
                (cardlist2.get(6).getNumber().toString().toLowerCase())));
            c8.setImage(inte.getImage(cardlist2.get(7).getColour().toString().toLowerCase(),
                (cardlist2.get(7).getNumber().toString().toLowerCase())));
            c9.setImage(inte.getImage(cardlist2.get(8).getColour().toString().toLowerCase(),
                (cardlist2.get(8).getNumber().toString().toLowerCase())));
            c10.setImage(inte.getImage(cardlist2.get(9).getColour().toString().toLowerCase(),
                (cardlist2.get(9).getNumber().toString().toLowerCase())));
            extra2.setImage(null);
            break;
          case (11):
            System.out.println("11");
            extra1.setImage(inte.getImage(cardlist2.get(0).getColour().toString().toLowerCase(),
                (cardlist2.get(0).getNumber().toString().toLowerCase())));
            c1.setImage(inte.getImage(cardlist2.get(1).getColour().toString().toLowerCase(),
                (cardlist2.get(1).getNumber().toString().toLowerCase())));
            c2.setImage(inte.getImage(cardlist2.get(2).getColour().toString().toLowerCase(),
                (cardlist2.get(2).getNumber().toString().toLowerCase())));
            c3.setImage(inte.getImage(cardlist2.get(3).getColour().toString().toLowerCase(),
                (cardlist2.get(3).getNumber().toString().toLowerCase())));
            c4.setImage(inte.getImage(cardlist2.get(4).getColour().toString().toLowerCase(),
                (cardlist2.get(4).getNumber().toString().toLowerCase())));
            c5.setImage(inte.getImage(cardlist2.get(5).getColour().toString().toLowerCase(),
                (cardlist2.get(5).getNumber().toString().toLowerCase())));
            c6.setImage(inte.getImage(cardlist2.get(6).getColour().toString().toLowerCase(),
                (cardlist2.get(6).getNumber().toString().toLowerCase())));
            c7.setImage(inte.getImage(cardlist2.get(7).getColour().toString().toLowerCase(),
                (cardlist2.get(7).getNumber().toString().toLowerCase())));
            c8.setImage(inte.getImage(cardlist2.get(8).getColour().toString().toLowerCase(),
                (cardlist2.get(8).getNumber().toString().toLowerCase())));
            c9.setImage(inte.getImage(cardlist2.get(9).getColour().toString().toLowerCase(),
                (cardlist2.get(9).getNumber().toString().toLowerCase())));
            c10.setImage(inte.getImage(cardlist2.get(10).getColour().toString().toLowerCase(),
                (cardlist2.get(10).getNumber().toString().toLowerCase())));
            extra2.setImage(null);
            break;
          case (12):
            extra1.setImage(inte.getImage(cardlist2.get(0).getColour().toString().toLowerCase(),
                (cardlist2.get(0).getNumber().toString().toLowerCase())));
            c1.setImage(inte.getImage(cardlist2.get(1).getColour().toString().toLowerCase(),
                (cardlist2.get(1).getNumber().toString().toLowerCase())));
            c2.setImage(inte.getImage(cardlist2.get(2).getColour().toString().toLowerCase(),
                (cardlist2.get(2).getNumber().toString().toLowerCase())));
            c3.setImage(inte.getImage(cardlist2.get(3).getColour().toString().toLowerCase(),
                (cardlist2.get(3).getNumber().toString().toLowerCase())));
            c4.setImage(inte.getImage(cardlist2.get(4).getColour().toString().toLowerCase(),
                (cardlist2.get(4).getNumber().toString().toLowerCase())));
            c5.setImage(inte.getImage(cardlist2.get(5).getColour().toString().toLowerCase(),
                (cardlist2.get(5).getNumber().toString().toLowerCase())));
            c6.setImage(inte.getImage(cardlist2.get(6).getColour().toString().toLowerCase(),
                (cardlist2.get(6).getNumber().toString().toLowerCase())));
            c7.setImage(inte.getImage(cardlist2.get(7).getColour().toString().toLowerCase(),
                (cardlist2.get(7).getNumber().toString().toLowerCase())));
            c8.setImage(inte.getImage(cardlist2.get(8).getColour().toString().toLowerCase(),
                (cardlist2.get(8).getNumber().toString().toLowerCase())));
            c9.setImage(inte.getImage(cardlist2.get(9).getColour().toString().toLowerCase(),
                (cardlist2.get(9).getNumber().toString().toLowerCase())));
            c10.setImage(inte.getImage(cardlist2.get(10).getColour().toString().toLowerCase(),
                (cardlist2.get(10).getNumber().toString().toLowerCase())));
            extra2.setImage(inte.getImage(cardlist2.get(11).getColour().toString().toLowerCase(),
                (cardlist2.get(11).getNumber().toString().toLowerCase())));
            break;
        }
      }
    });
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
   * 
   * @author lstrauch
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
    labelBet.setText("");
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
    if(main.getSettingsCon().getTrainingsmode()) {
      displayTraining("1.   Be realistic! You won’t win the next round, if you don’t get at least six tricks and you won’t get the girl, when she didn’t drink at least six drinks.\n" + 
          "2.  You have more than two Jacks?  You’re either a slut or a winner!\n" + 
          "3.  You only have two Jacks? Hopefully they are the blacks.\n" + 
          "4.  Know your highest possible bet! Only a noob’s bet and Snoop Dog are higher. ");
      training.toFront();
    }
  }



  /**
   * @author lstrauch
   */
  public void displayAuctionWinnerScreen() {
    ToggleGroup g1 = new ToggleGroup();

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
    diamonds.setToggleGroup(g1);
    hearts.setToggleGroup(g1);
    spades.setToggleGroup(g1);
    clubs.setToggleGroup(g1);
    grand.setToggleGroup(g1);
    nullG.setToggleGroup(g1);

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
   * @author lstrauch
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


    skatPane.getChildren().add(skatLabel);
    skatPane.getChildren().add(skatHbox);
    mainPane.getChildren().add(skatPane);
  }

  /**
   * display part in which the player can choose the cards he wants to put on the skat
   */
  /**
   * @author lstrauch
   * @param ps
   */
  public void displaySwitchSkat(PlayState ps) {
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
    sk1.setStyle("-fx-background-color: black");
    sk2.setImage(inte.getImage(ps.getSkat()[1].getColour().toString().toLowerCase(),
        ps.getSkat()[1].getNumber().toString().toLowerCase()));

    sk2.setFitHeight(227);
    sk2.setFitWidth(182);
    sk2.setLayoutX(326);
    sk2.setLayoutY(37);
    sk2.setStyle("-fx-background-color: black");
    sk1.setImage(inte.getImage(ps.getSkat()[0].getColour().toString().toLowerCase(),
        ps.getSkat()[0].getNumber().toString().toLowerCase()));

    ok.setPrefHeight(31);
    ok.setPrefWidth(67);
    ok.setLayoutX(501);
    ok.setLayoutY(270);
    ok.setText("OK");
    ok.setFont(Font.font("System", FontWeight.BOLD, 15));
    ok.setButtonType(ButtonType.RAISED);


    handPane.getChildren().add(sk1);
    handPane.getChildren().add(sk2);
    handPane.getChildren().add(ok);

    mainPane.getChildren().add(handPane);
  }

  /**
   * @author lstrauch
   */
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

  /**
   * @author lstrauch
   */
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

  public void displayBubbleLeft(int bet) {
    bubbleLeft.setImage(bubbleL);
    betLeft.setText(String.valueOf(bet));
  }

  public void displayBubbleRight(int bet) {
    bubbleRight.setImage(bubbleR);
    betRight.setText(String.valueOf(bet));
  }

  public void displayBubbleUp(int bet) {
    bubbleUp.setImage(bubbleU);
    betUp.setText(String.valueOf(bet));
  }


  /**
   * 
   * 
   * 
   * 
   * 
   * not necessary for GUI
   * 
   * 
   * 
   * 
   * 
   */


  /**
   * Not necessary for InGameController
   */
  @Override
  public void setGameSettings(GameSettings gs) {
    // TODO Auto-generated method stub

  }



}


