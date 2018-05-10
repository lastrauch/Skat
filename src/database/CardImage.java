package database;

import javafx.scene.image.Image;
import logic.Card;
import logic.Colour;
import logic.Number;

public class CardImage {
  private Card card;
  private boolean dark;
  private Image img;

  public CardImage(String colour, String number, String dark, Image image) {
    Colour col = this.setColour(colour);
    Number nr = this.setNumber(number);
    this.setCard(new Card(col, nr));
    this.setDark(this.setDark(dark));
    this.setImg(image);
  }

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

  public boolean setDark(String dark) {
    if (dark.equals("dark")) {
      return true;
    }
    return false;
  }

  public Card getCard() {
    return card;
  }

  public void setCard(Card card) {
    this.card = card;
  }

  public boolean isDark() {
    return dark;
  }

  public void setDark(boolean dark) {
    this.dark = dark;
  }

  public Image getImg() {
    return img;
  }

  public void setImg(Image img) {
    this.img = img;
  }
  

}
