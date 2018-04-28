package gui;

import java.sql.SQLException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import database.ImplementsGuiInterface;
import interfaces.GuiData;
import interfaces.GuiLogic;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logic.GameController;

public class CreateNewAccountController {

  private String username;
  protected ImplementsLogicGui implLG;
  private GuiData interfGD = new ImplementsGuiInterface();
  private GuiController main;
  private Label userExists = new Label();

  @FXML
  private JFXTextField newUsername;
  @FXML
  private JFXButton profilepicture;
  @FXML
  private AnchorPane pane;


  public CreateNewAccountController() {
    this.main = new GuiController();
    this.implLG = new ImplementsLogicGui();
  }

  @FXML
  public void submit() {
    this.username = newUsername.getText();
    
    //Auf Duygus Änderungen warten
//    try {
//      if(!interfGD.checkIfPlayerNew(username)) {
//        displayUserExists();
//      } else {
//        main.displayChooseGame();
//        GameController gameCon = new GameController(implLG);
//        GuiLogic interfaceL = gameCon;
//        interfaceL.login(username, null);
//      }
//    } catch (SQLException e) {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//    }
    
    main.displayChooseGame();
    GameController gameCon = new GameController(implLG);
    GuiLogic interfaceL = gameCon;
    interfaceL.login(username, null);
  }
  
  public void displayUserExists() {
    userExists.setLayoutX(44);
    userExists.setLayoutY(135);
    userExists.setPrefHeight(44);
    userExists.setPrefWidth(621);
    userExists.setText("Username already exists!");
    userExists.setFont(Font.font("System", FontWeight.BOLD, 21));
    userExists.setStyle("-fx-background-color: white; -fx-text-fill: red");
    userExists.setAlignment(Pos.CENTER);
    
    pane.getChildren().add(userExists);
  }

}
