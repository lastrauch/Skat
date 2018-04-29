package gui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXTextField;
import com.sun.prism.paint.Color;
import database.ImplementsGuiInterface;
import interfaces.GuiData;
import interfaces.GuiLogic;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logic.GameController;

public class LoginController implements Initializable {

  /**
   * @author lstrauch
   */
  private GuiController main;
  protected static String username;
  protected static ImplementsLogicGui implLG = new ImplementsLogicGui();
  protected GuiData interfGD = new ImplementsGuiInterface();
  protected static GuiLogic interfGL = new GameController(implLG);
  private Label noUsername = new Label();
 


  /**
   * @author lstrauch
   */
  @FXML
  JFXTextField textField;
  @FXML
  ImageView jclubs, jspades, jhearts, jdiamonds;
  @FXML
  private AnchorPane pane;

  
  /**
   *@author lstrauch
   */
  public LoginController() {
    this.main = new GuiController();
  }


  /**
   * @author lstrauch
   */
  @FXML
  public void neuerAccount() {
    main.displayCreateNewAccount();
  }

  /** (non-Javadoc)
   * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
   * 
   * @author lstrauch
   */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    // TODO Auto-generated method stub
    setImages();
  }

  /**
   * @author lstrauch
   */
  @FXML
  public void login() {
    System.out.println("Tada");
    username = textField.getText();
    
    try {
      if (!interfGD.checkIfPlayerNew(username)) {
        main.displayChooseGame();
        interfGL.login(username, null);
      } else {
        displayNoUser();
      }
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
//    main.displayChooseGame();
  }

  /**
   * @author lstrauch
   */
  public void setImages() {
    GuiData inte = new ImplementsGuiInterface();
    jclubs.setImage(inte.getImage("clubs", "jack"));
    jspades.setImage(inte.getImage("spades", "jack"));
    jhearts.setImage(inte.getImage("hearts", "jack"));
    jdiamonds.setImage(inte.getImage("diamonds", "jack"));

  }

  /**
   * @author lstrauch
   */
  public void displayNoUser() {
    noUsername.setLayoutX(14);
    noUsername.setLayoutY(375);
    noUsername.setPrefHeight(44);
    noUsername.setPrefWidth(718);
    noUsername.setText("Username not found!");
    noUsername.setFont(Font.font("System", FontWeight.BOLD, 21));
    noUsername.setStyle("-fx-background-color: white; -fx-text-fill: red");
    noUsername.setAlignment(Pos.CENTER);
    
    pane.getChildren().add(noUsername);
  }

}
