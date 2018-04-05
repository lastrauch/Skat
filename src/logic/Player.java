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
  public void playCard(int pos) {}

  public Card playCard() {
    int index = IOTools.readInteger("index of played card: ");
    Card playedCard = this.hand.get(index);
    return playedCard;
  }
  
  public void removeCard(int index) {
    this.hand.remove(index);
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

  // maybe something for the logic gui interface???
  public boolean askForBet(int bet) {
    int whatTheySaid = IOTools.readInteger(this.name + " " + bet + " or PASS(0)?");
    if (whatTheySaid == bet) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * needs to be changed for the final version
   * probably a method for the logicGui/logicNetwork interface
   * @param ps
   * @author awesch
   */
  public void askForPlaySettings(PlayState ps) {
    
    //set playmode
    String pm = IOTools.readLine("Set the PlayMode (colour, grand, null or nullouvert): ");
    PlayMode playMode;
    switch (pm) {
      case "colour":
        playMode = PlayMode.COLOUR;
        break;
      case "grand":
        playMode = PlayMode.GRAND;
        break;
      case "null":
        playMode = PlayMode.NULL;
        break;
      case "nullouvert":
        playMode = PlayMode.NULLOUVERT;
        break;
      default:
        System.out.println("your PlayMode could not be identificated, you will play Null now.");
        playMode = PlayMode.NULL;
    }
    ps.setPlayMode(playMode);

    //set Trump
    if (playMode == PlayMode.COLOUR) {
      String t = IOTools.readLine("Set the Trump (clubs, spades, hearts, diamonds): ");
      Colour trump;
      switch (t) {
        case "clubs":
          trump = Colour.CLUBS;
          break;
        case "spades":
          trump = Colour.SPADES;
          break;
        case "hearts":
          trump = Colour.HEARTS;
          break;
        case "diamonds":
          trump = Colour.DIAMONDS;
          break;
        default:
          System.out.println("your Trump could not be identificated, you will play Clubs now.");
          trump = Colour.CLUBS;
      }
      ps.setTrump(trump);
    }
    //add other features and settings later
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
