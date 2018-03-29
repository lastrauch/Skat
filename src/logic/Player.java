package logic;

import java.util.ArrayList;

public class Player {
  private String name;
  private Position position;
  private ArrayList<Card> hand = new ArrayList<Card>();

  public Player(String name) {
    this.name = name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPosition(Position position) {
    this.position = position;
  }

  public void setHand(ArrayList<Card> hand) {
    this.hand = hand;
  }
  

  public String getName() {
    return this.name;
  }

  public Position getPosition() {
    return this.position;
  }

  public ArrayList<Card> getHand() {
    return this.hand;
  }

}
