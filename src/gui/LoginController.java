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

  private GuiController main;
  protected String username;
  protected ImplementsLogicGui implLG = new ImplementsLogicGui();
  protected GuiData implGD = new ImplementsGuiInterface();
  protected static GameController gameCon;
  
  private Label noUsername = new Label();
 


  @FXML
  JFXTextField textField;
  @FXML
  ImageView jclubs, jspades, jhearts, jdiamonds;
  @FXML
  private AnchorPane pane;

  public LoginController() {
    this.main = new GuiController();
  }


  @FXML
  public void neuerAccount() {
    // Settingsfeld für neuen Benutzernamen anzeigen
    main.displayCreateNewAccount();

  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    // TODO Auto-generated method stub
    setImages();
  }

  @FXML
  public void login() {
    username = textField.getText();
    
    //Auf Duygus Ändrungen warten
//    try {
//      if(!implGD.checkIfPlayerNew(username)) {
//        main.displayChooseGame();
//        gameCon = new GameController(implLG);
//        GuiLogic interfaceL = gameCon;
//        interfaceL.login(username, null);
//      } else {
//        displayNoUser();
//      }
//    } catch (SQLException e) {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//    }
    
    main.displayChooseGame();
    gameCon = new GameController(implLG);
    GuiLogic interfaceL = gameCon;
    interfaceL.login(username, null);
  }

  public void setImages() {
    GuiData inte = new ImplementsGuiInterface();
    jclubs.setImage(inte.getImage("clubs", "jack"));
    jspades.setImage(inte.getImage("spades", "jack"));
    jhearts.setImage(inte.getImage("hearts", "jack"));
    jdiamonds.setImage(inte.getImage("diamonds", "jack"));

  }

  public String getUsername() {
    return username;
  }
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
