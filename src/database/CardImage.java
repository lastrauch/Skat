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

}
