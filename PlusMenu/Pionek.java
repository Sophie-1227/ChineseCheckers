import java.awt.*;

public class Pionek {
	
	private Color color;
	
	public Pionek(Color color) {
		 this.color = color;
	}
	
	public Pionek kopiujPionek() {
		Color color = this.getColor();
		Pionek pionek = new Pionek(color);
		return pionek;
	}
	
	public Color getColor() {
		return this.color;
	}
	
}