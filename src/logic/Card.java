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

  public Colour getColour() {
    return this.colour;
  }

  public Number getNumber() {
    return this.number;
  }

  public Image getImage() {
    return this.img;
  }

  public int getValue() {
    return this.number.getValue();
  }

  public void setImage(Image img) {
    this.img = img;
  }

}
