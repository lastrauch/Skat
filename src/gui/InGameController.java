package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXButton.ButtonType;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import database.ImplementsGuiInterface;
import interfaces.GuiData;
import interfaces.InGameInterface;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
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
import logic.Card;
import logic.ClientLogic;
import logic.Colour;
import logic.GameSettings;
import logic.LogicException;
import logic.PlayMode;
import logic.PlayState;
import logic.Player;
import logic.Position;

public class InGameController implements Initializable, InGameInterface {

  /**
   * Initialize Betting-screen.
   * 
   * @author lstrauch
   */
  private AnchorPane paneBet;
  private HBox box;
  private Label labelBet;
  private JFXButton pass;
  private JFXButton betB;
  boolean be;
  boolean notpressed;

  /**
   * Initialize AuctionWinnerScreen.
   * 
   * @author lstrauch
   */
  AnchorPane paneAuc;
  JFXRadioButton diamonds;
  JFXRadioButton hearts;
  JFXRadioButton spades;
  JFXRadioButton clubs;
  JFXRadioButton nullG;
  JFXRadioButton grand;
  JFXButton submit;
  JFXRadioButton ouvert;
  JFXRadioButton schneider;
  JFXRadioButton schwarz;
  HBox boxWin1;
  HBox boxWin2;
  HBox boxWin3;
  VBox vboxWin;
  Label labelWin;



  /**
   * Initialize takeSkat and siwtchSkat.
   * 
   * @author lstrauch
   */
  private AnchorPane skatPane;
  private AnchorPane handPane;
  private Label skatLabel;
  private JFXButton yes;
  private JFXButton no;
  private HBox skatHbox;
  private ImageView sk1;
  private ImageView sk2;
  private JFXButton ok;
  List<Card> skatLogic;
  boolean decidepressed;
  boolean wantskat;
  boolean skatpressed;
  boolean setSettings;


  /**
   * Initialize all non-FXML attributes.
   * 
   * @author lstrauch
   */
  private GuiController main;
  private Player pl1;
  private Player pl2;
  private Player pl3;
  private Player pl4;
  private GuiData inte;
  private List<Card> cardlist;
  private Image noCard;
  private Image rueckseite;
  private Image bubbleL;
  private Image bubbleR;
  private Image bubbleU;
  private List<Card> skat;
  private Boolean[] da;
  private boolean clicked;
  int[] ret;
  private int countl;
  private int countr;
  private boolean random;


  /**
   * Initialize all FXML attributes.
   * 
   * @author lstrauch
   */
  @FXML
  private ImageView c1;
  @FXML
  private ImageView c2;
  @FXML
  private ImageView c3;
  @FXML
  private ImageView c4;
  @FXML
  private ImageView c5;
  @FXML
  private ImageView c6;
  @FXML
  private ImageView c7;
  @FXML
  private ImageView c8;
  @FXML
  private ImageView c9;
  @FXML
  private ImageView c10;
  @FXML
  private ImageView stichMid;
  private ImageView[] carray;
  @FXML
  private ImageView r1;
  @FXML
  private ImageView r2;
  @FXML
  private ImageView r3;
  @FXML
  private ImageView r4;
  @FXML
  private ImageView r5;
  @FXML
  private ImageView r6;
  @FXML
  private ImageView r7;
  @FXML
  private ImageView r8;
  @FXML
  private ImageView r9;
  @FXML
  private ImageView r10;
  @FXML
  private ImageView stichRechts;
  private ImageView[] rarray;
  @FXML
  private ImageView l1;
  @FXML
  private ImageView l2;
  @FXML
  private ImageView l3;
  @FXML
  private ImageView l4;
  @FXML
  private ImageView l5;
  @FXML
  private ImageView l6;
  @FXML
  private ImageView l7;
  @FXML
  private ImageView l8;
  @FXML
  private ImageView l9;
  @FXML
  private ImageView l10;
  @FXML
  private ImageView stichLinks;
  private ImageView[] larray;
  @FXML
  private ImageView o1;
  @FXML
  private ImageView o2;
  @FXML
  private ImageView o3;
  @FXML
  private ImageView o4;
  @FXML
  private ImageView o5;
  @FXML
  private ImageView o6;
  @FXML
  private ImageView o7;
  @FXML
  private ImageView o8;
  @FXML
  private ImageView o9;
  @FXML
  private ImageView o10;
  @FXML
  private ImageView stichOben;
  private ImageView[] oarray;
  @FXML
  private ImageView s1;
  @FXML
  private ImageView s2;
  @FXML
  private ImageView s3;
  @FXML
  private ImageView extra1;
  @FXML
  private ImageView extra2;
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
  private Label labelLeft;
  @FXML
  private Label labelRight;
  @FXML
  private Label labelMiddle;
  @FXML
  private Label labelMe;
  @FXML
  private ImageView bubbleLeft;
  @FXML
  private ImageView bubbleRight;
  @FXML
  private ImageView bubbleUp;
  @FXML
  private Label betLeft;
  @FXML
  private Label betUp;
  @FXML
  private Label betRight;
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
   * Initialize GuiController and Implements class.
   * 
   * @author lstrauch
   */
  public InGameController() {
    this.main = new GuiController();
    GuiController.prevScreen = 4;
  }

  /**
   * (non-Javadoc).
   * 
   * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
   * 
   *      initializes attributes and enabeles Buttonlistener
   * @author lstrauch
   **/
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    // TODO Auto-generated method stub
    paneBet = new AnchorPane();
    box = new HBox();
    labelBet = new Label();
    pass = new JFXButton();
    betB = new JFXButton();
    be = false;
    notpressed = true;

    paneAuc = new AnchorPane();
    diamonds = new JFXRadioButton();
    hearts = new JFXRadioButton();
    spades = new JFXRadioButton();
    clubs = new JFXRadioButton();
    nullG = new JFXRadioButton();
    grand = new JFXRadioButton();
    submit = new JFXButton();
    ouvert = new JFXRadioButton();
    schneider = new JFXRadioButton();
    schwarz = new JFXRadioButton();
    boxWin1 = new HBox();
    boxWin2 = new HBox();
    boxWin3 = new HBox();
    vboxWin = new VBox();
    labelWin = new Label();

    skatPane = new AnchorPane();
    handPane = new AnchorPane();
    skatLabel = new Label();
    yes = new JFXButton();
    no = new JFXButton();
    skatHbox = new HBox();
    sk1 = new ImageView();
    sk2 = new ImageView();
    ok = new JFXButton();
    skatLogic = new ArrayList<Card>();
    decidepressed = false;
    wantskat = false;
    skatpressed = false;
    setSettings = false;


    inte = new ImplementsGuiInterface();
    cardlist = new ArrayList<Card>();
    noCard = new Image(getClass().getResource("images/grey.jpg").toExternalForm());
    rueckseite = new Image(getClass().getResource("images/rueckseite.jpg").toExternalForm());
    bubbleL = new Image(getClass().getResource("images/Sprechblase_links.png").toExternalForm());
    bubbleR = new Image(getClass().getResource("images/Sprechblase_rechts.png").toExternalForm());
    bubbleU = new Image(getClass().getResource("images/Sprechblase_oben.png").toExternalForm());
    skat = new ArrayList<Card>();
    da = new Boolean[2];
    clicked = false;
    ret = new int[1];
    countl = 10;
    countr = 10;
    random = false;

    carray = new ImageView[10];
    rarray = new ImageView[10];
    larray = new ImageView[10];
    oarray = new ImageView[10];

    da[0] = true;
    da[1] = true;
    carray[0] = c1;
    carray[1] = c2;
    carray[2] = c3;
    carray[3] = c4;
    carray[4] = c5;
    carray[5] = c6;
    carray[6] = c7;
    carray[7] = c8;
    carray[8] = c9;
    carray[9] = c10;

    larray[0] = l1;
    larray[1] = l2;
    larray[2] = l3;
    larray[3] = l4;
    larray[4] = l5;
    larray[5] = l6;
    larray[6] = l7;
    larray[7] = l8;
    larray[8] = l9;
    larray[9] = l10;

    rarray[0] = r1;
    rarray[1] = r2;
    rarray[2] = r3;
    rarray[3] = r4;
    rarray[4] = r5;
    rarray[5] = r6;
    rarray[6] = r7;
    rarray[7] = r8;
    rarray[8] = r9;
    rarray[9] = r10;

    oarray[0] = o1;
    oarray[1] = o2;
    oarray[2] = o3;
    oarray[3] = o4;
    oarray[4] = o5;
    oarray[5] = o6;
    oarray[6] = o7;
    oarray[7] = o8;
    oarray[8] = o9;
    oarray[9] = o10;



    if (main.getLobbyCon().getGs().getNrOfPlayers() == 4) {
      initialize4();
    } else {
      for (int i = 0; i < 10; i++) {
        oarray[i].setImage(null);
      }
    }
  }


  /**
   * Deletes the pane on the mainpane, so we can display the new one.
   * 
   * @author lstrauch
   * @param tpane AnchorPane
   */
  public void deletePane(AnchorPane tpane) {
    mainPane.getChildren().remove(tpane);
  }


  /**
   * starts the play.
   * 
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
   * updates the hand.
   * 
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
   * displays who won the trick.
   * 
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

  public Player getPlayer4() {
    return this.pl4;
  }



  /**
   * displays rekontra-button.
   * 
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

  /**
   * initializes the gae screen with 4 players.
   * 
   * @author lstrauch
   */
  public void initialize4() {
    if (LoginController.interfGL.getPlayer().getPosition() == Position.MIDDLEHAND) {
      for (int i = 0; i < 10; i++) {
        oarray[i].setImage(null);
      }
    } else if (LoginController.interfGL.getPlayer().getPosition() == Position.FOREHAND) {
      rarray[1].setImage(null);
      for (int i = 0; i < rarray.length; i++) {
        rarray[i].setImage(null);
      }
    } else if (LoginController.interfGL.getPlayer().getPosition() == Position.REARHAND) {
      larray[1].setImage(null);
      for (int i = 0; i < rarray.length; i++) {
        larray[i].setImage(null);
      }
    }
  }

  /**
   * Auction.
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
        displayAuctionScreen();
        betB.setText(String.valueOf(bet));
      }
    });
  }

  /**
   * enables buttonListener.
   * 
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
    System.out.println("ASK FOR BET");
    while (notpressed) {
      buttonListener();
    }
    notpressed = true;
    return be;
  }



  /**
   * Do you want to take the Skat?.
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
   * asks if you want to take the Skat.
   * 
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
      buttonListenerWantSkat();
    }
    decidepressed = false;
    return wantskat;
  }



  /**
   * Declarer Stack.
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
   * enables the siwtchSat-Listener.
   * 
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
   * opens AuctionWinner-screen.
   * 
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
    System.out.println("AUCTIONWINNERSCREEN");
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
   * displays the set trump on the right position.
   * 
   * @author lstrauch
   * @param ps PlayState
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
        if (main.getLobbyCon().getGs().isEnableKontra()
            && !LoginController.interfGL.getPlayer().isDeclarer()) {
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
        if (main.getLobbyCon().getGs().getNrOfPlayers() == 3) {
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
   * turns cards light if it's your turn.
   * 
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
        if (main.getSettingsCon() != null && main.getSettingsCon().getTrainingsmode()) {
          training.setLayoutY(388);
          if (LoginController.interfGL.getPlayer().isDeclarer()
              && LoginController.interfGL.getPlayer().getPosition() != Position.DEALER) {
            displayTraining("1.   Play your trumps! When the opponents lost all their trumps, "
                + "you are unstoppable like a train without brakes.\n"
                + "2.  Be brave! If a color was not played "
                + "yet and you have an Ass, play it and save the points!\n"
                + "3.  Save your own ASS and TEN! And you will be the winner then!\n"
                + "4.  Have you seen ASS and TEN do not play this color again!\n"
                + "5.  Count the Trumps! This is important like"
                + " a condom at a One-Night-Stand.");
            training.toFront();
          } else if (!LoginController.interfGL.getPlayer().isDeclarer()
              && LoginController.interfGL.getPlayer().getPosition() != Position.DEALER) {
            displayTraining("1. Be brave! If a Suit was not played yet and you have an Ass,"
                + " play it and save the points!\n"
                + "2.  Save your own ASS and TEN! And you will be the winner then!\n"
                + "3.  Have you seen ASS and TEN do not play this color again!\n"
                + "4.  Count the Trumps! This is important like a"
                + " condom at a One-Night-Stand.\n" + "5.  Keep the declarer in the middle!");
            training.toFront();
          }
        }
      }

    });
  }


  /**
   * display the new bet from opponent.
   * 
   * @author lstrauch
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
        if (main.getSettingsCon() != null && main.getSettingsCon().getTrainingsmode()) {
          disableTraining();
        }
        System.out.println("RECEIVED NEW BET");
        bubbleLeft.setImage(null);
        bubbleRight.setImage(null);
        bubbleUp.setImage(null);
        betRight.setText(null);
        betLeft.setText(null);
        betUp.setText(null);
        deletePane(paneBet);
        if (main.getLobbyCon().getGs().getNrOfPlayers() == 3) {
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

  /**
   * turns cards dark if it's not possible to play the clicked one.
   * 
   * @author lstrauch
   */
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

  /**
   * displays opponents cards if ouvert was selected.
   * 
   * @author lstrauch
   */
  /*
   * (non-Javadoc)
   * 
   * @see interfaces.InGameInterface#showOpen(logic.Player)
   */
  @Override
  public void showOpen(Player player) {
    // TODO Auto-generated method stub
    if (main.getLobbyCon().getGs().getNrOfPlayers() == 4) {
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

  /**
   * enables MouseHandler to make it able to play a card. plays random card after a certain time if
   * limited time is set.
   * 
   * @author lstrauch
   */
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
      if (main.getLobbyCon().getGs().isLimitedTime()) {
        while (System.currentTimeMillis() / 1000 < end && clicked == false) {
          mouseHandler();
        }
        ret[0] = playRandomCard(ps, LoginController.interfGL.getPlayer());
        random = true;
        clicked = true;
      } else {
        mouseHandler();

      }
    }
    clicked = false;
    return ret[0];
  }


  /**
   * selects a possile random card from hand.
   * 
   * @author lstrauch
   * @param ps PlayState
   * @param player Player
   * @return cardindex of played card
   */
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

  /**
   * displays Leaderboard.
   * 
   * @author lstrauch
   */
  /*
   * (non-Javadoc)
   * 
   * @see interfaces.InGameInterface#showScore(java.util.List)
   */
  @Override
  public void showScore(List<Player> player) {
    // TODO Auto-generated method stub
    System.out.println("SHOW SCORE!!!");
    if (player.size() == 3) {
      this.pl1 = player.get(0);
      this.pl2 = player.get(1);
      this.pl3 = player.get(2);
      main.displayLeaderboard3();
    } else {
      System.out.println("Leaderboard 4");
      this.pl1 = player.get(0);
      this.pl2 = player.get(1);
      this.pl3 = player.get(2);
      this.pl4 = player.get(3);
      main.displayLeaderboard4();
      // main.getLead4Con().start();
    }

  }

  /**
   * displays the just played card on stack.
   * 
   * @author lstrauch
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
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        if (main.getLobbyCon().getGs().getNrOfPlayers() == 3) {
          rearrangeCardsDark(cardlist);
          if (LoginController.interfGL.getPlayer().getPosition() == Position.FOREHAND) {
            if (player.getPosition() == Position.MIDDLEHAND) {
              s1.setImage(inte.getImage(card));
              removeOpponentCardsLeft(countl);
              countl--;
              s1.toFront();
            } else if (player.getPosition() == Position.REARHAND) {
              s3.setImage(inte.getImage(card));
              removeOpponentCardsRight(countr);
              countr--;
              s3.toFront();
            } else {
              s2.setImage(inte.getImage(card));
              s2.toFront();
            }
          } else if (LoginController.interfGL.getPlayer().getPosition() == Position.REARHAND) {
            if (player.getPosition() == Position.MIDDLEHAND) {
              s3.setImage(inte.getImage(card));
              removeOpponentCardsRight(countr);
              countr--;
              s3.toFront();
            } else if (player.getPosition() == Position.FOREHAND) {
              s1.setImage(inte.getImage(card));
              removeOpponentCardsLeft(countl);
              countl--;
              s1.toFront();
            } else {
              s2.setImage(inte.getImage(card));
              s2.toFront();
            }
          } else {
            if (player.getPosition() == Position.REARHAND) {
              s1.setImage(inte.getImage(card));
              removeOpponentCardsLeft(countl);
              countl--;
              s1.toFront();
            } else if (player.getPosition() == Position.FOREHAND) {
              s3.setImage(inte.getImage(card));
              removeOpponentCardsRight(countr);
              countr--;
              s3.toFront();
            } else {
              s2.setImage(inte.getImage(card));
              s2.toFront();
            }
          }


          // 4 Players:

        } else {
          rearrangeCardsDark(cardlist);
          if (LoginController.interfGL.getPlayer().getPosition() == Position.FOREHAND) {
            if (player.getPosition() == Position.MIDDLEHAND) {
              s1.setImage(inte.getImage(card));
              removeOpponentCardsLeft(countl);
              countl--;
              s1.toFront();
            } else if (player.getPosition() == Position.REARHAND) {
              s3.setImage(inte.getImage(card));
              removeOpponentCardsRight(countr);
              countr--;
              s3.toFront();
            } else {
              s2.setImage(inte.getImage(card));
              s2.toFront();
            }
          } else if (LoginController.interfGL.getPlayer().getPosition() == Position.REARHAND) {
            if (player.getPosition() == Position.MIDDLEHAND) {
              s3.setImage(inte.getImage(card));
              removeOpponentCardsRight(countr);
              countr--;
              s3.toFront();
            } else if (player.getPosition() == Position.FOREHAND) {
              s2.setImage(inte.getImage(card));
              removeOpponentCardsLeft(countl);
              countl--;
              s1.toFront();
            } else {
              s2.setImage(inte.getImage(card));
              s2.toFront();
            }
          } else if (LoginController.interfGL.getPlayer().getPosition() == Position.MIDDLEHAND) {
            if (player.getPosition() == Position.FOREHAND) {
              s1.setImage(inte.getImage(card));
              removeOpponentCardsLeft(countl);
              countl--;
              s1.toFront();
            } else if (player.getPosition() == Position.MIDDLEHAND) {
              s2.setImage(inte.getImage(card));
              removeOpponentCardsRight(countr);
              countr--;
              s2.toFront();
            } else {
              s3.setImage(inte.getImage(card));
              s3.toFront();
            }
          } else {
            if (player.getPosition() == Position.REARHAND) {
              s3.setImage(inte.getImage(card));
              removeOpponentCardsLeft(countl);
              countl--;
              s3.toFront();
            } else if (player.getPosition() == Position.FOREHAND) {
              s1.setImage(inte.getImage(card));
              removeOpponentCardsRight(countr);
              countr--;
              s1.toFront();
            } else {
              s2.setImage(inte.getImage(card));
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
        if (main.getLobbyCon().getGs().isEnableKontra()) {
          mainPane.getChildren().remove(kontra);
        }
      }
    });

  }

  /**
   * enables playsettings-listener.
   * 
   * @author lstrauch
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
      buttonListenerPlaySettings(ps);

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
   * displays help screen.
   * 
   * @author lstrauch
   */
  @FXML
  public void help() {
    main.displayHelp();
  }

  /**
   * displays trainingsmode.
   * 
   * @author lstrauch
   * @param s
   */
  public void displayTraining(String s) {
    training.setStyle(
        "-fx-background-color: tan; -fx-background-radius: 10; -fx-border-color: peru; -fx-border-radius: 10");
    training.setText(s);
  }

  /**
   * removes trainingsmode label.
   * 
   * @author lstrauch
   */
  public void disableTraining() {
    training.setStyle(null);
    training.setText(null);
  }

  /**
   * displays kontra-button.
   * 
   * @author lstrauch
   */
  public void displayKontra() {
    kontra.setText("KONTRA");
    kontra.setStyle(
        "-fx-background-color: peru; -fx-background-radius: 10; -fx-border-color: black; -fx-border-radius: 10");
  }

  /**
   * removes kontrabutton.
   * 
   * @author lstrauch
   */
  public void deleteKontra() {
    kontra.setText(null);
    kontra.setStyle(null);
  }

  /**
   * displays rekontra-button.
   * 
   * @author lstrauch
   */
  public void displayRekontra() {
    kontra.setText("KONTRA");
    kontra.setStyle(
        "-fx-background-color: peru; -fx-background-radius: 10; -fx-border-color: black; -fx-border-radius: 10");
  }



  /**
   * displays light cards.
   * 
   * @author lstrauch
   * @param list List
   */
  public void rearrangeCardsLight(List<Card> list) {
    for (int i = 0; i < list.size(); i++) {
      carray[i].setImage(inte.getImage(list.get(i)));
    }
    if (list.size() != 10) {
      for (int i = list.size(); i < 10; i++) {
        carray[i].setImage(null);
      }
    }
  }


  /**
   * displays light dark.
   * 
   * @author lstrauch
   * @param list List
   */
  public void rearrangeCardsDark(List<Card> list) {
    for (int i = 0; i < list.size(); i++) {
      carray[i].setImage(inte.getImageDarker(list.get(i)));
    }
    if (list.size() != 10) {
      for (int i = list.size(); i < 10; i++) {
        carray[i].setImage(null);
      }
    }
  }

  /**
   * displays light on the left side if ouvert.
   * 
   * @author lstrauch
   * @param list List
   */
  public void rearrangeCardsLeft(List<Card> list) {
    for (int i = 0; i < list.size(); i++) {
      larray[i].setImage(inte.getImage(list.get(i)));
    }
    if (list.size() != 10) {
      for (int i = list.size(); i < 10; i++) {
        larray[i].setImage(null);
      }
    }
  }

  /**
   * displays light on the right side if ouvert.
   * 
   * @author lstrauch
   * @param list List
   */
  public void rearrangeCardsRight(List<Card> list) {
    for (int i = 0; i < list.size(); i++) {
      rarray[i].setImage(inte.getImage(list.get(i)));
    }
    if (list.size() != 10) {
      for (int i = list.size(); i < 10; i++) {
        rarray[i].setImage(null);
      }
    }
  }

  /**
   * displays light on the left side if ouvert and 4 players.
   * 
   * @author lstrauch
   * @param list List
   */
  public void rearrangeCardsUp(List<Card> list) {
    for (int i = 0; i < list.size(); i++) {
      oarray[i].setImage(inte.getImage(list.get(i)));
    }
    if (list.size() != 10) {
      for (int i = list.size(); i < 10; i++) {
        oarray[i].setImage(null);
      }
    }
  }

  /**
   * turns the not playable cards dark.
   * 
   * @author lstrauch
   * @param list List
   */
  public void rearrangeCardsNotPossible(List<Card> list) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i) != null) {
        System.out.println("cArray[" + i + "] - Dark: " + carray[i]);
        carray[i].setImage(inte.getImageDarker(list.get(i)));

      } else {
        System.out.println("cArray[" + i + "] - Light: " + carray[i]);
        carray[i].setImage(inte.getImage(cardlist.get(i)));
      }
    }
    if (list.get(list.size() - 1) != null) {
      carray[list.size() - 1].setImage(inte.getImageDarker(list.get(list.size() - 1)));
    } else {
      carray[list.size() - 1].setImage(inte.getImage(list.get(list.size() - 1)));
    }
  }

  /**
   * removes opponentscard on the right side after played.
   * 
   * @author lstrauch
   * @param anz anz
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
      default:
        break;
    }
  }

  /**
   * removes opponentscard on the left side after played.
   * 
   * @author lstrauch
   * @param anz anz
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
      default:
        break;
    }
  }



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
   * adds mouselistener to Imageview.
   * 
   * @author lstrauch
   */

  public void mouseHandler() {
    for (int i = 0; i < carray.length; i++) {
      int t = i;
      carray[i].setOnMousePressed(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
          ret[0] = t;
          clicked = true;
        }
      });
    }
  }



  /**
   * adds buttonlistener to bet-Buttons.
   * 
   * @author lstrauch
   * @return
   */
  public void buttonListener() {
    pass.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        be = false;
        notpressed = false;
      }
    });
    betB.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        notpressed = false;
        be = true;
      }
    });
    submit.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {}
    });
  }


  /**
   * ButtonListener AuctionWinnerScree-buttons.
   * 
   * @author lstrauch
   * @param ps PlayState
   */
  public PlayState buttonListenerPlaySettings(PlayState ps) {
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
   * Button Listener WantSkat-buttons.
   * 
   * @author lstrauch
   * @param ps PlayState
   */
  public void buttonListenerWantSkat() {
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
   * ButtonListener to switch Skat.
   *
   * @author lstrauch
   * @param ps PlayState
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
   * displays Cards for switchSkat.
   * 
   * @author lstrauch
   * @param anz anz
   * @param cardlist2 cardlist
   */
  public void displayCards(int anz, List<Card> cardlist2) {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        switch (anz) {
          case (10):
            extra1.setImage(null);
            c1.setImage(inte.getImage(cardlist2.get(0)));
            c2.setImage(inte.getImage(cardlist2.get(1)));
            c3.setImage(inte.getImage(cardlist2.get(2)));
            c4.setImage(inte.getImage(cardlist2.get(3)));
            c5.setImage(inte.getImage(cardlist2.get(4)));
            c6.setImage(inte.getImage(cardlist2.get(5)));
            c7.setImage(inte.getImage(cardlist2.get(6)));
            c8.setImage(inte.getImage(cardlist2.get(7)));
            c9.setImage(inte.getImage(cardlist2.get(8)));
            c10.setImage(inte.getImage(cardlist2.get(9)));
            extra2.setImage(null);
            break;
          case (11):
            System.out.println("11");
            extra1.setImage(inte.getImage(cardlist2.get(0)));
            c1.setImage(inte.getImage(cardlist2.get(1)));
            c2.setImage(inte.getImage(cardlist2.get(2)));
            c3.setImage(inte.getImage(cardlist2.get(3)));
            c4.setImage(inte.getImage(cardlist2.get(4)));
            c5.setImage(inte.getImage(cardlist2.get(5)));
            c6.setImage(inte.getImage(cardlist2.get(6)));
            c7.setImage(inte.getImage(cardlist2.get(7)));
            c8.setImage(inte.getImage(cardlist2.get(8)));
            c9.setImage(inte.getImage(cardlist2.get(9)));
            c10.setImage(inte.getImage(cardlist2.get(10)));
            extra2.setImage(null);
            break;
          case (12):
            extra1.setImage(inte.getImage(cardlist2.get(0)));
            c1.setImage(inte.getImage(cardlist2.get(1)));
            c2.setImage(inte.getImage(cardlist2.get(2)));
            c3.setImage(inte.getImage(cardlist2.get(3)));
            c4.setImage(inte.getImage(cardlist2.get(4)));
            c5.setImage(inte.getImage(cardlist2.get(5)));
            c6.setImage(inte.getImage(cardlist2.get(6)));
            c7.setImage(inte.getImage(cardlist2.get(7)));
            c8.setImage(inte.getImage(cardlist2.get(8)));
            c9.setImage(inte.getImage(cardlist2.get(9)));
            c10.setImage(inte.getImage(cardlist2.get(10)));
            extra2.setImage(inte.getImage(cardlist2.get(11)));
            break;
          default:
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
   * Display Auction part.
   * 
   * @author lstrauch
   */
  public void displayAuctionScreen() {
    paneBet.setLayoutX(475);
    paneBet.setLayoutY(188);
    paneBet.setPrefHeight(200);
    paneBet.setPrefWidth(395);
    paneBet.setStyle("-fx-background-color: tan; -fx-background-radius: 20; "
        + "-fx-border-color: white; -fx-border-radius: 20");

    pass.setPrefHeight(44);
    pass.setPrefWidth(75);
    pass.setText("Pass");
    pass.setFont(Font.font("System", FontWeight.BOLD, 20));
    pass.setTextFill(Color.WHITE);
    pass.setStyle("-fx-background-color: peru;");
    betB.setPrefHeight(44);
    betB.setPrefWidth(75);
    betB.setFont(Font.font("System", FontWeight.BOLD, 20));
    betB.setTextFill(Color.WHITE);
    betB.setStyle("-fx-background-color: peru;");

    labelBet.setPrefHeight(53);
    labelBet.setLayoutX(88);
    labelBet.setLayoutY(20);
    labelBet.setText("Auction");
    labelBet.setFont(Font.font("System", FontWeight.BOLD, 30));
    labelBet.setTextFill(Color.WHITE);
    AnchorPane.setLeftAnchor(labelBet, 0.0);
    AnchorPane.setRightAnchor(labelBet, 0.0);
    labelBet.setAlignment(Pos.CENTER);

    if (!box.getChildren().contains(betB) && !box.getChildren().contains(pass)) {
      box.getChildren().add(pass);
      box.getChildren().add(betB);
    }
    box.setPrefHeight(44);
    box.setLayoutX(60);
    box.setLayoutY(58);
    box.setSpacing(100);
    box.setAlignment(Pos.CENTER);

    if (!paneBet.getChildren().contains(box) && !paneBet.getChildren().contains(labelBet)) {
      paneBet.getChildren().add(box);
      paneBet.getChildren().add(labelBet);
      AnchorPane.setLeftAnchor(box, 0.0);
      AnchorPane.setRightAnchor(box, 0.0);
      AnchorPane.setTopAnchor(box, 0.0);
      AnchorPane.setBottomAnchor(box, 0.0);
    }

    if (!mainPane.getChildren().contains(paneBet)) {
      mainPane.getChildren().add(paneBet);
    }
    if (main.getSettingsCon() != null && main.getSettingsCon().getTrainingsmode()) {
      displayTraining("1.   Be realistic! You won’t win the next round, if you don’t get at least "
          + "six tricks and you won’t get the girl, when she didn’t drink at least six drinks.\n"
          + "2.  You have more than two Jacks?  You’re either a slut or a winner!\n"
          + "3.  You only have two Jacks? Hopefully they are the blacks.\n"
          + "4.  Know your highest possible bet! Only a noob’s bet and Snoop Dog are higher. ");
      training.toFront();
    }
  }



  /**
   * initializes aucionwinner-pane.
   * 
   * @author lstrauch
   */
  public void displayAuctionWinnerScreen() {
    paneAuc.setPrefHeight(330);
    paneAuc.setPrefWidth(700);
    paneAuc.setLayoutX(334);
    paneAuc.setLayoutY(128);
    paneAuc.setStyle("-fx-background-color: tan; -fx-background-radius: 20; "
        + "-fx-border-color: white; -fx-border-radius: 20");

    clubs.setPrefHeight(130);
    clubs.setPrefWidth(130);
    clubs.setText("Clubs");
    clubs.setTextFill(Color.WHITE);
    clubs.setAlignment(Pos.CENTER);
    clubs.setFont(Font.font("System", FontWeight.BOLD, 16));
    clubs.setStyle("-fx-background-color: peru; -fx-background-radius: 20;");
    spades.setPrefHeight(42);
    spades.setPrefWidth(130);
    spades.setText("Spades");
    spades.setTextFill(Color.WHITE);
    spades.setAlignment(Pos.CENTER);
    spades.setFont(Font.font("System", FontWeight.BOLD, 16));
    spades.setStyle("-fx-background-color: peru; -fx-background-radius: 20;");
    hearts.setPrefHeight(42);
    hearts.setPrefWidth(130);
    hearts.setText("Hearts");
    hearts.setTextFill(Color.WHITE);
    hearts.setAlignment(Pos.CENTER);
    hearts.setFont(Font.font("System", FontWeight.BOLD, 16));
    hearts.setStyle("-fx-background-color: peru; -fx-background-radius: 20;");
    diamonds.setPrefHeight(42);
    diamonds.setPrefWidth(130);
    diamonds.setText("Diamonds");
    diamonds.setTextFill(Color.WHITE);
    diamonds.setFont(Font.font("System", FontWeight.BOLD, 16));
    diamonds.setStyle("-fx-background-color: peru; -fx-background-radius: 20;");
    ToggleGroup g1 = new ToggleGroup();
    diamonds.setToggleGroup(g1);
    hearts.setToggleGroup(g1);
    spades.setToggleGroup(g1);
    clubs.setToggleGroup(g1);
    grand.setToggleGroup(g1);
    nullG.setToggleGroup(g1);

    if (!boxWin1.getChildren().contains(diamonds) && !boxWin1.getChildren().contains(hearts)
        && !boxWin1.getChildren().contains(spades) && !boxWin1.getChildren().contains(clubs)) {
      boxWin1.getChildren().add(clubs);
      boxWin1.getChildren().add(spades);
      boxWin1.getChildren().add(hearts);
      boxWin1.getChildren().add(diamonds);
    }
    boxWin1.setSpacing(20);
    boxWin1.setPrefWidth(650);
    boxWin1.setPrefHeight(42);
    boxWin1.setLayoutX(28);
    boxWin1.setLayoutY(60);

    grand.setPrefHeight(42);
    grand.setPrefWidth(130);
    grand.setText("Grand");
    grand.setTextFill(Color.WHITE);
    grand.setFont(Font.font("System", FontWeight.BOLD, 16));
    grand.setStyle("-fx-background-color: peru; -fx-background-radius: 20;");
    nullG.setPrefHeight(42);
    nullG.setPrefWidth(130);
    nullG.setText("Null");
    nullG.setTextFill(Color.WHITE);
    nullG.setFont(Font.font("System", FontWeight.BOLD, 16));
    nullG.setStyle("-fx-background-color: peru; -fx-background-radius: 20;");

    if (!boxWin2.getChildren().contains(grand) && !boxWin2.getChildren().contains(nullG)) {
      boxWin2.getChildren().add(grand);
      boxWin2.getChildren().add(nullG);
    }
    boxWin2.setSpacing(165);
    boxWin2.setPrefWidth(650);
    boxWin2.setPrefHeight(42);
    boxWin2.setLayoutX(28);
    boxWin2.setLayoutY(158);

    ouvert.setPrefHeight(21);
    ouvert.setPrefWidth(120);
    ouvert.setText("Ouvert");
    ouvert.setTextFill(Color.WHITE);
    ouvert.setFont(Font.font("System", 15));
    ouvert.setStyle("-fx-background-color: peru; -fx-background-radius: 20;");
    schneider.setPrefHeight(21);
    schneider.setPrefWidth(120);
    schneider.setText("Schneider");
    schneider.setTextFill(Color.WHITE);
    schneider.setFont(Font.font("System", 15));
    schneider.setStyle("-fx-background-color: peru; -fx-background-radius: 20;");
    schwarz.setPrefHeight(21);
    schwarz.setPrefWidth(120);
    schwarz.setText("Schwarz");
    schwarz.setTextFill(Color.WHITE);
    schwarz.setFont(Font.font("System", 15));
    schwarz.setStyle("-fx-background-color: peru; -fx-background-radius: 20;");

    if (!boxWin3.getChildren().contains(ouvert) && !boxWin3.getChildren().contains(schneider)
        && !boxWin3.getChildren().contains(schwarz)) {
      boxWin3.getChildren().add(ouvert);
      boxWin3.getChildren().add(schneider);
      boxWin3.getChildren().add(schwarz);
    }
    boxWin3.setSpacing(35);
    boxWin3.setPrefWidth(650);
    boxWin3.setPrefHeight(21);
    boxWin3.setLayoutX(28);
    boxWin3.setLayoutY(266);

    if (!vboxWin.getChildren().contains(boxWin1) && !vboxWin.getChildren().contains(boxWin2)
        && !vboxWin.getChildren().contains(boxWin3)) {
      vboxWin.getChildren().add(boxWin1);
      vboxWin.getChildren().add(boxWin2);
      vboxWin.getChildren().add(boxWin3);
    }
    vboxWin.setPrefHeight(185);
    vboxWin.setPrefWidth(650);
    vboxWin.setLayoutX(14);
    vboxWin.setLayoutY(97);
    vboxWin.setSpacing(40);

    submit.setPrefHeight(33);
    submit.setPrefWidth(112);
    submit.setText("Submit");
    submit.setTextFill(Color.WHITE);
    submit.setFont(Font.font("System", 15));
    submit.setStyle("-fx-background-color: peru; -fx-border-color: black;");
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

    if (!paneAuc.getChildren().contains(vboxWin) && !paneAuc.getChildren().contains(labelWin)
        && !paneAuc.getChildren().contains(submit)) {
      paneAuc.getChildren().add(vboxWin);
      paneAuc.getChildren().add(labelWin);
      paneAuc.getChildren().add(submit);
    }

    if (!mainPane.getChildren().contains(paneAuc)) {
      mainPane.getChildren().add(paneAuc);
    }
  }



  /**
   * initializes wantSkat-pane.
   * 
   * @author lstrauch
   */
  public void displayWannaTakeSkat() {
    skatPane.setPrefHeight(315);
    skatPane.setPrefWidth(582);
    skatPane.setLayoutX(334);
    skatPane.setLayoutY(128);
    skatPane.setStyle("-fx-background-color: tan; -fx-background-radius: 20; "
        + "-fx-border-color: white; -fx-border-radius: 20");

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
    yes.setStyle("-fx-background-color: peru;-fx-background-radius: 20");
    yes.setButtonType(ButtonType.RAISED);
    no.setPrefHeight(123);
    no.setPrefWidth(154);
    no.setText("no");
    no.setFont(Font.font("System", FontWeight.BOLD, 25));
    no.setStyle("-fx-background-color: peru; -fx-background-radius: 20");
    no.setButtonType(ButtonType.RAISED);

    if (!skatHbox.getChildren().contains(yes) && !skatHbox.getChildren().contains(no)) {
      skatHbox.getChildren().add(yes);
      skatHbox.getChildren().add(no);
    }
    skatHbox.setSpacing(100);
    skatHbox.setPrefWidth(346);
    skatHbox.setPrefHeight(54);
    skatHbox.setLayoutX(118);
    skatHbox.setLayoutY(158);


    if (!skatPane.getChildren().contains(skatLabel) && !skatPane.getChildren().contains(skatPane)
        && !mainPane.getChildren().contains(skatHbox)) {
      skatPane.getChildren().add(skatLabel);
      skatPane.getChildren().add(skatHbox);
      mainPane.getChildren().add(skatPane);
    }
  }

  /**
   * display part in which the player can choose the cards he wants to put on the skat.
   *
   * @author lstrauch
   * @param ps PlayState
   */
  public void displaySwitchSkat(PlayState ps) {
    handPane.setPrefHeight(315);
    handPane.setPrefWidth(582);
    handPane.setLayoutX(334);
    handPane.setLayoutY(128);
    handPane.setStyle("-fx-background-color: tan; -fx-background-radius: 20; "
        + "-fx-border-color: white; -fx-border-radius: 20");

    sk1.setFitHeight(227);
    sk1.setFitWidth(182);
    sk1.setLayoutX(83);
    sk1.setLayoutY(37);
    sk2.setImage(inte.getImage(ps.getSkat()[1]));

    sk2.setFitHeight(227);
    sk2.setFitWidth(182);
    sk2.setLayoutX(326);
    sk2.setLayoutY(37);
    sk1.setImage(inte.getImage(ps.getSkat()[0]));

    ok.setPrefHeight(31);
    ok.setPrefWidth(67);
    ok.setLayoutX(501);
    ok.setLayoutY(270);
    ok.setText("OK");
    ok.setFont(Font.font("System", FontWeight.BOLD, 15));
    ok.setButtonType(ButtonType.RAISED);


    if (!handPane.getChildren().contains(sk1) && !handPane.getChildren().contains(sk2)
        && !handPane.getChildren().contains(ok)) {
      handPane.getChildren().add(sk1);
      handPane.getChildren().add(sk2);
      handPane.getChildren().add(ok);
    }

    if (!mainPane.getChildren().contains(handPane)) {
      mainPane.getChildren().add(handPane);
    }
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
   * Not necessary for InGameController.
   * 
   * @author lstrauch
   */
  @Override
  public void setGameSettings(GameSettings gs) {
    // TODO Auto-generated method stub

  }



}


