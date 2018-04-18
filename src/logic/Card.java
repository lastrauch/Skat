package logic;

import javafx.scene.image.Image;

public class Card {
  private Colour colour;
  private Number number;
  private Image img;
  private Image imgDarker;
  

  public Card(Colour colour, Number number) {
    this.colour = colour;
    this.number = number;
  }

  public Card(Colour colour, Number number, Image img) {
    this.colour = colour;
    this.number = number;
    this.img = img;
  }

  public int getMatadorValue() {
    int mValue = 0;
    if (this.number == Number.JACK) {
      switch (this.colour) {
        case CLUBS:
          mValue = 0;
          break;
        case SPADES:
          mValue = 1;
          break;
        case HEARTS:
          mValue = 2;
          break;
        case DIAMONDS:
          mValue = 3;
          break;
      }
    } else {
      mValue = this.number.getMatadorValue();
    }
    return mValue;
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

  // returns 0 for same Colour -1
  public int compareColour(Card comp) {
    return this.colour.compareColourIntern(comp.getColour());
  }

  public boolean isHigherAsNorm(Card comp) {
    if (this.number.getRankingNorm() > comp.getNumber().getRankingNorm()) {
      return true;
    } else {
      return false;
    }
  }

  public boolean isLowerAsNorm(Card comp) {
    if (this.number.getRankingNorm() < comp.getNumber().getRankingNorm()) {
      return true;
    } else {
      return false;
    }
  }

  public boolean isHigherAsLowTen(Card comp) {
    if (this.number.getRankingLowTen() > comp.getNumber().getRankingLowTen()) {
      return true;
    } else {
      return false;
    }
  }

  public boolean isLowerAsLowTen(Card comp) {
    if (this.number.getRankingLowTen() < comp.getNumber().getRankingLowTen()) {
      return true;
    } else {
      return false;
    }
  }

  public Image getImgDarker() {
    return imgDarker;
  }

  public void setImgDarker(Image imgDarker) {
    this.imgDarker = imgDarker;
  }

}

