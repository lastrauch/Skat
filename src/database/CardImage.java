package database;

import javafx.scene.image.Image;
import logic.Card;
import logic.Colour;
import logic.Number;

public class CardImage {
  private Card card;
  private boolean dark;
  private Image img;

  /**
   * constructor.
   * 
   * @param colour of the card
   * @param number of the card
   * @param dark or white
   * @param image of the card
   * @author awesch
   */
  public CardImage(String colour, String number, String dark, Image image) {
    Colour col = this.setColour(colour);
    Number nr = this.setNumber(number);
    this.card = new Card(col, nr);
    this.dark = this.setDark(dark);
    this.img = image;
  }

  /**
   * returns the matching colour enum.
   * 
   * @author awesch
   * @param col -our in a string
   */
  public Colour setColour(String col) {
    Colour c = Colour.CLUBS;
    switch (col) {
      case "clubs":
        c = Colour.CLUBS;
        break;
      case "spades":
        c = Colour.SPADES;
        break;
      case "hearts":
        c = Colour.HEARTS;
        break;
      case "diamonds":
        c = Colour.DIAMONDS;
        break;
      default:
        break;
    }
    return c;
  }

  /**
   * returns the matching Number enum.
   * 
   * @author awesch
   * @param nr in a string
   */
  public Number setNumber(String nr) {
    Number number = Number.SEVEN;
    switch (nr) {
      case "seven":
        number = Number.SEVEN;
        break;
      case "eight":
        number = Number.EIGHT;
        break;
      case "nine":
        number = Number.NINE;
        break;
      case "jack":
        number = Number.JACK;
        break;
      case "queen":
        number = Number.QUEEN;
        break;
      case "king":
        number = Number.KING;
        break;
      case "ten":
        number = Number.TEN;
        break;
      case "ass":
        number = Number.ASS;
        break;
      default:
        break;
    }
    return number;
  }

  /**
   * returns the matching boolean.
   * 
   * @author awesch
   * @param dark in a string
   */
  public boolean setDark(String dark) {
    if (dark.equals("dark")) {
      return true;
    }
    return false;
  }

  public Card getCard() {
    return card;
  }

  public boolean isDark() {
    return dark;
  }

  public Image getImg() {
    return img;
  }


}
