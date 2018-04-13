package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

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
  private AnchorPane pane;

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
    // c1.setImage(cardId.getImage());
    // c2.setImage(cardId.getImage());
    // c3.setImage(cardId.getImage());
    // c4.setImage(cardId.getImage());
    // c5.setImage(cardId.getImage());
    // c6.setImage(cardId.getImage());
    // c7.setImage(cardId.getImage());
    // c8.setImage(cardId.getImage());
    // c9.setImage(cardId.getImage());
    // c10.setImage(cardId.getImage());

    /**
     * Setzt die MouseEvents, sodass Karten ausgewählt und das Spielfeld aktualisiert werden kann
     * 
     * @author: lstrauch
     * 
     */
    MouseHandler();

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
        pane.getChildren().remove(c1);
        AnzStichblatt();


      }
    });
    c2.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        temp = c2.getImage();
        pane.getChildren().remove(c2);
        AnzStichblatt();

      }
    });
    c3.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        temp = c3.getImage();
        pane.getChildren().remove(c3);
        AnzStichblatt();

      }
    });
    c4.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        temp = c4.getImage();
        pane.getChildren().remove(c4);
        AnzStichblatt();

      }
    });
    c5.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        temp = c5.getImage();
        pane.getChildren().remove(c5);
        AnzStichblatt();

      }
    });
    c6.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        temp = c6.getImage();
        pane.getChildren().remove(c6);
        AnzStichblatt();

      }
    });
    c7.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        temp = c7.getImage();
        pane.getChildren().remove(c7);
        AnzStichblatt();

      }
    });
    c8.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        temp = c8.getImage();
        pane.getChildren().remove(c8);
        AnzStichblatt();
      }
    });
    c9.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        temp = c9.getImage();
        pane.getChildren().remove(c9);
        AnzStichblatt();

      }
    });
    c10.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        temp = c10.getImage();
        pane.getChildren().remove(c10);
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

}
