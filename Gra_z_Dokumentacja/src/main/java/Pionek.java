import javax.swing.*;
import java.awt.*;

public class Pionek {
	
	/**
	*Klasa zawiera metody pozwalajace na obsluge pionka
	*/
	
	private Color color;
	
	public Pionek(Color color) {
		 this.color = color;
	}
	
	/**
	*Kopiowanie pionka
	*/
	
	public Pionek kopiujPionek() {
		Color color = this.getColor();
		Pionek pionek = new Pionek(color);
		return pionek;
	}
	
	/**
	*Pobieranie koloru pionka
	*/
	
	public Color getColor() {
		return this.color;
	}

}