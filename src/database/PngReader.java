
package database;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;

public class PngReader {

  String[] colours;
  String[] numbers;
  String[] darkLight;
  static String seperator = "_";

  /**
   * constructor.
   * @author sandfisc
   */
  public PngReader() {
    this.fillColours();
    this.fillNumbers();
    this.fillDarkLight();
  }

  /**
   * Colours of a card game are saved in colours.
   * @author sandfisc
   */
  public void fillColours() {
    this.colours = new String[4];
    this.colours[0] = "diamonds";
    this.colours[1] = "hearts";
    this.colours[2] = "spades";
    this.colours[3] = "clubs";
  }

  /**
   * Numbers of skat are saved in numbers.
   * @author sandfisc
   */
  public void fillNumbers() {
    this.numbers = new String[8];
    this.numbers[0] = "seven";
    this.numbers[1] = "eight";
    this.numbers[2] = "nine";
    this.numbers[3] = "ten";
    this.numbers[4] = "jack";
    this.numbers[5] = "queen";
    this.numbers[6] = "king";
    this.numbers[7] = "ass";
  }

  /**
   * darkLight gets filled with the two opportunities cards are shown to the player.
   * @author sandfisc
   */
  public void fillDarkLight() {
    this.darkLight = new String[2];
    this.darkLight[0] = "dark";
    this.darkLight[1] = "light";
  }

  /**
   * Reads all images of the skat cards.
   * @return list of all CardImages.
   * @author sandfisc
   */
  public List<CardImage> readAll() {
    List<CardImage> cards = new ArrayList<CardImage>();
    for (String colour : this.colours) {
      for (String number : this.numbers) {
        for (String darkLight : this.darkLight) {
          cards.add(new CardImage(colour, number, darkLight, new Image(getClass().getResource(
              "cards/" + colour + seperator + number + seperator + darkLight + ".png").toExternalForm())));
        }
      }
    }
    return cards;
  }
}