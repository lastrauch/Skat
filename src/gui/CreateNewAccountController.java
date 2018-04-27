package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import interfaces.GuiLogic;
import javafx.fxml.FXML;
import logic.GameController;

public class CreateNewAccountController {

  private String username;
  protected ImplementsLogicGui implLG;
  private GuiController main;

  @FXML
  private JFXTextField newUsername;
  @FXML
  private JFXButton profilepicture;


  public CreateNewAccountController() {
    this.main = new GuiController();
    this.implLG = new ImplementsLogicGui();
  }

  @FXML
  public void submit() {
    this.username = newUsername.getText();
    main.displayChooseGame();
    GameController gameCon = new GameController(implLG);
    GuiLogic interfaceL = gameCon;
//    interfaceL.login(username, null);
  }

}
