package interfaces;

import javafx.scene.image.Image;
import logic.Colour;
import logic.PlayMode;

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
   * Checken ob Karte gespielt werden darf
   * 
   * @return true or false
   */
  public boolean abletoPlay();


  /**
   * Damit ich weiß wer gewonnen hat und wo der Stich hingelegt wird
   * 
   * @return 1 for yourself, 2 for player on teh left, 3 for player on the right
   */
  public int whoWon();

  /**
   * Damit im betting label angezeigt werden kann welche Position man gerade ist Bedeutet ich
   * brauche als Outpt einen String mit "Forehand", "Middlehand" oder "Raerhand"
   * 
   * @return String ("Middelhand", "Forehand", "Rearhand")
   */
  public String position();


  /**
   * Checken ob gerade der Screen angezeigt wird, ob man dran ist mit reizen oder nicht
   * 
   * @return true or false
   */

  public boolean isTurnBetting();

  /**
   * Checken ob gerade der Screen angezeigt wird, ob man dran ist mit reizen oder nicht
   * 
   * @return true or false
   */
  public boolean isTurnGame();

  /**
   * Wenn man Host ist wird der Spielsettingsscreen angezeigt
   * 
   * @return true or false
   */
  public boolean ifHost();

  /**
   * Sagt mir ob derjenige gerade die Auktion gewonnen hat oder nicht
   * 
   * @return tru for winner false for not winner
   */
  public boolean IsAcutionWinner();

  /**
   * asks player to set the PlayMode (after he won the auction) supposed to return PlayMode.COLOUR
   * || PlayMode.GRAND || PlayMode.NULL || PlayMode.NULLOUVERT
   * 
   * @param player
   * @return
   */
  public PlayMode setPlayMode();

  /**
   * asks player to set the Trump (after he won the auction AND set the PlayMode = colour) supposed
   * to return Colour.CLUBS || Colour.SPADES || Colour.HEARTS || Colour.DIAMONDS
   * 
   * @param player
   * @return
   */
  public Colour setTrump();

  /**
   * asks player if he wants to play Hand (also after he won the aution) true --> he wants to play
   * hand
   * 
   * @return
   */
  public void setPlayHand();

  /**
   * asks the player if he wants to announce Schneider (after he won the auction) true --> he wants
   * to announce schneider
   * 
   * @return
   */
  public void setAnnounceSchneider();

  /**
   * asks the player if he wants to announce Schwarz (after he won the auction) true --> he wants to
   * announce Schwarz
   * 
   * @return
   */
  public void setAnnounceSchwarz();

  /**
   * asks the player if he wants to play open (after he won the auction) true --> he wants to play
   * open
   * 
   * @return
   */
  public void setPlayOpen();


  /**
   * aks if the player wants to play Bierlachs, Seeger System or Normal
   * 
   * 
   */
  public void SetCountRule();


  /**
   * Setzt die Minuspunkte die erreicht werden können/sollen
   * 
   * @return
   */
  public void setEndPiontsBierlachs();

  /**
   * Setzt wie viele Runden gespielt werden sollen
   * 
   * @return
   */
  public void setNumberOfPlays();

  /**
   * Kontra einschalten
   * 
   * @return true falls eingeschaltet
   */
  public boolean eanbleKontra();

  /**
   * 
   * limitierte Zeit einschalten
   * 
   * @return true falls eingeschaltet
   */
  public boolean enableLimitedTime();

  /**
   * setzt limitierte Zeit
   * 
   * 
   */
  public void setTimeLimit();

  /**
   * eingegebener Username wird übergene
   * 
   * @param username
   */
  public void setLoginPlayer(String username);

  /**
   * 
   * setzt den Usernamen eines neuen Benutzers
   * 
   * @param username
   */
  public void setNewAccountUsername(String username);


  /**
   * 
   * nachdem Host nach Spieleinstellung auf submit gedrückt hat wird neues Spiel gestartet
   * 
   */
  public void startNewGame();

  /**
   * 
   * Gibt die neu sortierte Hand zurück
   * 
   * @return Imgae[] (neu sortierte Hand)
   */
  public Image[] updateHand();

  /**
   * 
   * Setzt cardsUpdatet wieder auf false, falls Hand geupdatet wurde
   * 
   * @return
   */
  public boolean setCardsUpdatet();

  /**
   * 
   * überprüft ob die Hand neu sortiert werden soll
   * 
   * @return true falls neu sortiert werden soll, ansonsten false
   */
  public boolean getCardsUpdatet();


}
