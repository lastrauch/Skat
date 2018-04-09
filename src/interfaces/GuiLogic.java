package interfaces;

import javafx.scene.image.Image;

//GUI to Logic, implemented by Logic
public interface GuiLogic {

	// Bilder der Karten erhalten
	public Image[] getCards();

	// Checken ob Karte gespielt werden darf
	public boolean abletoPlay();

}
