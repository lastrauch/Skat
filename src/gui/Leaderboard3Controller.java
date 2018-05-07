package gui;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import logic.Player;

public class Leaderboard3Controller implements Initializable{

  /**
   * @author lstrauch
   */
  private GuiController main;
  private Player p1, p2;

  
  @FXML
  private VBox vBoxNr, vBox1, vBox2, vBox3;
  @FXML
  private Text me, player1, player2;
  @FXML
  private Text eins, zwei, drei, vier, fuenf, sechs, sum;
  @FXML
  private Text meEins, meZwei, meDrei, meVier, meFuenf, meSechs, meSum;
  @FXML
  private Text eins1, zwei2, drei2, vier2, fuenf2, sech2, sum2;
  @FXML
  private Text eins2, zwei3, drei3, sum3, vier3, fuenf3, sechs3;
  
  /**
   *@author lstrauch
   */
  public Leaderboard3Controller() {
    this.main = new GuiController();
  }

  /**
   * @author lstrauch
   */
  @FXML
  public void back() {
    main.displayChooseGame();
  }

  /* (non-Javadoc)
   * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
   */
  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    // TODO Auto-generated method stub
    
  }
  
  public void showLeaderboard() {
    List<Label> roundsNr = new ArrayList<Label>();
    List<Label> rounds1 = new ArrayList<Label>();
    List<Label> rounds2 = new ArrayList<Label>();
    List<Label> rounds3 = new ArrayList<Label>();
    
    
    for(int i = 0; i < main.getGameSetCon().getGS().getNrOfPlayers(); i++) {
      roundsNr.add(new Label());
    }
    for(int i = 0; i < main.getGameSetCon().getGS().getNrOfPlays(); i++) {
      rounds1.add(new Label());
    }
    for(int i = 0; i < main.getGameSetCon().getGS().getNrOfPlays(); i++) {
      rounds2.add(new Label());
    }
    for(int i = 0; i < main.getGameSetCon().getGS().getNrOfPlays(); i++) {
      rounds3.add(new Label());
    }
    
    roundsNr.get(0).setText("Nr.");
    rounds1.get(0).setText("Me");
    rounds2.get(0).setText(main.getInGameCon().getPlayer1().getName());
    rounds3.get(0).setText(main.getInGameCon().getPlayer2().getName());
    
    for(int i = 0; i < roundsNr.size(); i++) {
      int count = i+1;
      roundsNr.get(i).setText(String.valueOf(count));
      roundsNr.get(i).setFont(Font.font("System", 33));
      roundsNr.get(i).setTextFill(Color.WHITE);
      AnchorPane.setLeftAnchor(roundsNr.get(i), 0.0);
      AnchorPane.setRightAnchor(roundsNr.get(i), 0.0);
      roundsNr.get(i).setAlignment(Pos.CENTER);
      vBoxNr.getChildren().add(roundsNr.get(i));
      
    }
    for(int i = 0; i < rounds1.size(); i++) {
      rounds1.get(i).setText(String.valueOf(LoginController.interfGL.getPlayer().getGamePoints()));
      rounds1.get(i).setFont(Font.font("System", 33));
      rounds1.get(i).setTextFill(Color.WHITE);
      AnchorPane.setLeftAnchor(rounds1.get(i), 0.0);
      AnchorPane.setRightAnchor(rounds1.get(i), 0.0);
      rounds1.get(i).setAlignment(Pos.CENTER);
      vBox1.getChildren().add(rounds1.get(i));
    }
    for(int i = 0; i < rounds2.size(); i++) {
      rounds2.get(i).setText(String.valueOf(main.getInGameCon().getPlayer1().getGamePoints()));
      rounds2.get(i).setFont(Font.font("System", 33));
      rounds2.get(i).setTextFill(Color.WHITE);
      AnchorPane.setLeftAnchor(rounds2.get(i), 0.0);
      AnchorPane.setRightAnchor(rounds2.get(i), 0.0);
      rounds2.get(i).setAlignment(Pos.CENTER);
      vBox2.getChildren().add(rounds2.get(i));
    }
    for(int i = 0; i < rounds3.size(); i++) {
      rounds3.get(i).setText(String.valueOf(main.getInGameCon().getPlayer2().getGamePoints()));
      rounds3.get(i).setFont(Font.font("System", 33));
      rounds3.get(i).setTextFill(Color.WHITE);
      AnchorPane.setLeftAnchor(rounds3.get(i), 0.0);
      AnchorPane.setRightAnchor(rounds3.get(i), 0.0);
      rounds3.get(i).setAlignment(Pos.CENTER);
      vBox3.getChildren().add(rounds3.get(i));
    }
  }
  
  public void setPlayer1(Player p) {
    this.p1 = p;
  }
  public void setPlayer2(Player p) {
    this.p2 = p;
  }

}
