package logic;

import java.io.Serializable;

/**
 * this class represents a card.
 */
public class Card implements Serializable {

  private static final long serialVersionUID = 1L;
  private Colour colour;
  private Number number;

  /* ------------------------- CONSTRUCTOR ------------------------------------------- */

  /**
   * constructor.
   * 
   * @param colour of the card
   * @param number of the card
   */
  public Card(Colour colour, Number number) {
    this.colour = colour;
    this.number = number;
  }

  public String toString() {
    return this.colour + " " + this.number;
  }

  /* ------------------------- COMPARISMS -------------------------------------------- */

  /**
   * returns true, if card is higher as comp with a high ten.
   * 
   * @param comp card to compare with
   * @return true if higher
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
   * @param comp card to compare with
   * @return true if card lower
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
   * @param comp card to compare with
   * @return true if higher
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
   * @param comp card to compare with
   * @return true if lower
   */
  public boolean isLowerAsLowTen(Card comp) {
    if (this.number.getRankingLowTen() < comp.getNumber().getRankingLowTen()) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * returns 0 for same colour -1.
   * 
   * @param comp card to compare with
   * @return higher or smaller
   */
  public int compareColour(Card comp) {
    return this.colour.compareColourIntern(comp.getColour());
  }

  /**
   * Checks if the current instance and the given card are equal.
   * @param card which is compared to current instances
   * @return if the current instance is equal to the parameter
   * @author sandfisc
   */
  public boolean equals(Card card) {
    if (this.colour.equals(card.getColour()) && this.number.equals(card.getNumber())) {
      return true;
    }
    return false;
  }
  
  /* ------------------------- GETTER AND SETTER ------------------------------------- */

  /**
   * returns the matador value.
   * @return matador
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

  public int getValue() {
    return this.number.getValue();
  }
}

