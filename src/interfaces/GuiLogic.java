package interfaces;

import javafx.scene.image.Image;
import logic.GameMode;

// GUI to Logic, implemented by Logic
public interface GuiLogic {


  // Observer Screen nicht vergessen!!!!!!

  // /**
  // * setzt in Logik den Index der gerade gespielten Karte
  // */
  // public void retCardIndex(int i);

  // /**
  // * Setzt ob auf pass oder bet gedrueckt wurde für "pass" false und für "bet" true
  // */
  // public void setAskForBet(boolean value);

  /**
   * should update the users username or profilepicture after selecting submit
   * 
   * @param username
   * @param im
   */
  public void updateAccount(String oldUsername, String newUsername, Image profilbild);


  /**
   * 
   * @param m
   */
  public void decideGameMode(GameMode m);

  /**
   * 
   * @param username
   */
  public void login(String username, Image profilepicture);

}
