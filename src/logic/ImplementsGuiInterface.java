package logic;

import interfaces.GuiLogic;
import javafx.scene.image.Image;

public class ImplementsGuiInterface implements GuiLogic {

  // Which order?? we can return an array of "Card" instances where you can get the Image 
  // We only get "Blob" Images from the sqlite database
  @Override
  public Image[] getCards() {
    // TODO Auto-generated method stub
    return null;
  }

  // Which Card? we would need the Card
  @Override
  public boolean abletoPlay() {
    // TODO Auto-generated method stub
    return false;
  }

  // 1 for yourself, 2 for player on the left, 3 for player on the right
  @Override
  public int whoWon() {
    // TODO Auto-generated method stub
    return 0;
  }

  // String ("Middelhand", "Forehand", "Rearhand")
  @Override
  public String position() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean isTurnBetting() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isTurnGame() {
    // TODO Auto-generated method stub
    return false;
  }

  // "isHost"
  @Override
  public boolean ifHost() {
    // TODO Auto-generated method stub
    return false;
  }

  // isAuctionWinner --> lower Case
  @Override
  public boolean IsAcutionWinner() {
    // TODO Auto-generated method stub
    return false;
  }

  // I think the head should be - public void setPlaymode(PlayMode pm); - right?
  @Override
  public PlayMode setPlayMode() {
    // TODO Auto-generated method stub
    return null;
  }

  // here as well - public void setTrump(Colour trump);
  @Override
  public Colour setTrump() {
    // TODO Auto-generated method stub
    return null;
  }

  // maybe also like with a parameter boolean (if the player wants to change his mind) like - public
  // void setPlayHand(boolean hand);
  @Override
  public void setPlayHand() {
    // TODO Auto-generated method stub

  }

  // same as setPlayHand
  @Override
  public void setAnnounceSchneider() {
    // TODO Auto-generated method stub

  }

  // same as setPlayHand
  @Override
  public void setAnnounceSchwarz() {
    // TODO Auto-generated method stub

  }

  // same as setPlayHand
  @Override
  public void setPlayOpen() {
    // TODO Auto-generated method stub

  }

  // here: public void setCountRule(CountRule cr);
  @Override
  public void SetCountRule() {
    // TODO Auto-generated method stub

  }

  // here: public void setEndPointsBierlachs(int endPointsBierlachs);
  @Override
  public void setEndPiontsBierlachs() {
    // TODO Auto-generated method stub

  }

  // here public void setNumberOfPlays(int nrOfPlays);
  @Override
  public void setNumberOfPlays() {
    // TODO Auto-generated method stub

  }

  // i think it should be - public void enableKontra(boolean kontra); - right?
  @Override
  public boolean eanbleKontra() {
    // TODO Auto-generated method stub
    return false;
  }

  // same as enableKontra
  @Override
  public boolean enableLimitedTime() {
    // TODO Auto-generated method stub
    return false;
  }

  // same as setEndPointsBierlachs
  @Override
  public void setTimeLimit() {
    // TODO Auto-generated method stub

  }


  @Override
  public void setLoginPlayer(String username) {
    // TODO Auto-generated method stub

  }

  @Override
  public void setNewAccountUsername(String username) {
    // TODO Auto-generated method stub

  }

  @Override
  public void startNewGame() {
    // TODO Auto-generated method stub

  }

  // gives back the resorted hand
  @Override
  public Image[] updateHand() {
    // TODO Auto-generated method stub
    return null;
  }

  // should also be a classic setter --> public void setCardsUpdated(boolean updated);
  @Override
  public boolean setCardsUpdatet() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean getCardsUpdatet() {
    // TODO Auto-generated method stub
    return false;
  }


}
