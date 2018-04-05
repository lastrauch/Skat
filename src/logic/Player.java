package logic;

import java.util.ArrayList;

public class Player {
  private int id;
  private String name;
  private Position position;
  private ArrayList<Card> hand = new ArrayList<Card>();
  private int bet;

  public Player(String name) {
    this.name = name;
    this.bet = 0;
  }

  // pos is the position of the card in the hand of the player
  public void playCard(int pos) {

  }

  // We assume the hand to be sorted the first time (before the PlayMode was set)
  public void calculateHighestPossibleBet(ArrayList<Card> hand) {
    // ArrayList<Card> jacks = new ArrayList<Card>();
    // int index = 0;
    // while (hand.get(index).getNumber() == Number.JACK) {
    // jacks.add(hand.get(index));
    // index ++;
    // }
  }

  // methods needed for the auction.. say,

  //maybe something for the logic gui interface???
  public boolean askForBet(int bet) {
    return true;
  }
  
  public int getBet() {
    return this.bet;
  }

  public void setBet(int bet) {
    this.bet = bet;
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

  public void setId(int id) {
    this.id = id;
  }

  public int getId() {
    return this.id;
  }

}
