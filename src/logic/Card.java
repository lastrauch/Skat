package logic;

import javafx.scene.image.Image;

public class Card {
  private Colour colour;
  private Number number;
  private Image img;

  public Card(Colour colour, Number number) {
    this.colour = colour;
    this.number = number;
  }

  public Card(Colour colour, Number number, Image img) {
    this.colour = colour;
    this.number = number;
    this.img = img;
  }

  private Colour getColour() {
    return this.colour;
  }

  private Number getNumber() {
    return this.number;
  }

  private Image getImage() {
    return this.img;
  }

  private int getValue() {
    return this.number.getValue();
  }

  private void setImage(Image img) {
    this.img = img;
  }

}
