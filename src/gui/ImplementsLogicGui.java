package gui;

import java.util.ArrayList;
import interfaces.LogicGui;
import logic.Card;
import logic.GameSettings;
import logic.Position;

public class ImplementsLogicGui implements LogicGui {

  @Override
  public String startGui() {
    // TODO Auto-generated method stub
    GuiController guiCon = new GuiController();
    return null;
  }

  @Override
  public boolean askForMultiplayer() {
    // TODO Auto-generated method stub

    return false;
  }

  @Override
  public void openSinglePlayerLobby() {
    // TODO Auto-generated method stub

  }

  @Override
  public void openMultiPlayerLobby() {
    // TODO Auto-generated method stub

  }

  @Override
  public void setGameSettings(GameSettings gs) {
    // TODO Auto-generated method stub

  }

  @Override
  public void startPlay(ArrayList<Card> hand, Position position) {
    // TODO Auto-generated method stub

  }

}
