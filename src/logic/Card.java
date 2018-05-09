package logic;

import java.io.Serializable;
import javafx.scene.image.Image;

/**
 * this class represents a card.
 */
public class Card implements Serializable {

  private static final long serialVersionUID = 1L;
  private Colour colour;
  private Number number;
  private Image img;
  private Image imgDarker;

  /* ------------------------- CONSTRUCTOR ------------------------------------------- */

  public Card(Colour colour, Number number) {
    this.colour = colour;
    this.number = number;
  }

  public Card(Colour colour, Number number, Image img) {
    this.colour = colour;
    this.number = number;
    this.img = img;
  }

  public String toString() {
    return this.colour + " " + this.number;
  }
  
  /* ------------------------- COMPARISMS -------------------------------------------- */
  
  /**
   * returns true, if card is higher as comp with a high ten.
   * 
   * @param comp
   */
  public boolean isHigherAsNorm(Card comp) {
    if (this.number.getRankingNorm() > comp.getNumber().getRankingNorm()) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * returns true, if card is lower as copm with a high ten.
   * 
   * @param comp
   */
  public boolean isLowerAsNorm(Card comp) {
    if (this.number.getRankingNorm() < comp.getNumber().getRankingNorm()) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * returns true, if card is higher than comp with a low ten.
   * 
   * @param comp
   */
  public boolean isHigherAsLowTen(Card comp) {
    if (this.number.getRankingLowTen() > comp.getNumber().getRankingLowTen()) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * returns true, if card is lower than comp with a low ten.
   * 
   * @param comp
   */
  public boolean isLowerAsLowTen(Card comp) {
    if (this.number.getRankingLowTen() < comp.getNumber().getRankingLowTen()) {
      return true;
    } else {
      return false;
    }
  }
  
  /**
   * returns 0 for same colour -1
   * @param comp
   */
  public int compareColour(Card comp) {
    return this.colour.compareColourIntern(comp.getColour());
  }

  /* ------------------------- GETTER AND SETTER ------------------------------------- */

  /**
   * returns the matador value.
   */
  public int getMatadorValue() {
    int value = 0;
    if (this.number == Number.JACK) {
      switch (this.colour) {
        case CLUBS:
          value = 0;
          break;
        case SPADES:
          value = 1;
          break;
        case HEARTS:
          value = 2;
          break;
        case DIAMONDS:
          value = 3;
          break;
        default:
          break;
      }
    } else {
      value = this.number.getMatadorValue();
    }
    return value;
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

  public Image getImgDarker() {
    return imgDarker;
  }

  public void setImgDarker(Image imgDarker) {
    this.imgDarker = imgDarker;
  }

}

