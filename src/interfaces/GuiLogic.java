package interfaces;

import javafx.scene.image.Image;

// GUI to Logic, implemented by Logic
public interface GuiLogic {


  // Observer Screen nicht vergessen!!!!!!

  /**
   * Bilder der Karten erhalten
   * 
   * @return Karten Array
   */
  public Image[] getCards();

  /**
   * setzt in Logik den Index der gerade gespielten Karte
   */
  public void retCardIndex(int i);

  /**
   * Setzt ob auf pass oder bet gedrueckt wurde für "pass" false und für "bet" true
   */
  public void setAskForBet(boolean value);
}
