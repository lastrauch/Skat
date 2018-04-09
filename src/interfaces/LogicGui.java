package interfaces;

import javafx.scene.image.Image;

//Logic to GUI, implemented by GUI
public interface LogicGui {

	// Bilder der Karten erhalten
	public Image[] getCards();

	// Checken ob Karte gespielt werden darf
	public boolean abletoPlay();

}
