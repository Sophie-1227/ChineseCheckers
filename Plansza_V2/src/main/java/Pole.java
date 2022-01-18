import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;

public class Pole extends JPanel implements MouseInputListener, MouseMotionListener {
	
	/**
	*Klasa odpowiadająca za obsługę pól na planszy 
	@param x współrzędna x pola na planszy
	@param y współrzędna y pola na planszy
	*/
	
	private Pionek pionek;
	private Plansza mojaPlansza;
	private int x;
	private int y;
	
	
	public Pole(Plansza plansza, int x, int y) {
		this.x = x;
		this.y = y;
		this.pionek = null;
		this.mojaPlansza = plansza;
	    addMouseListener(this);
	    addMouseMotionListener(this);
	}
	
	public void ustawPionek(Color color) {
		this.pionek = new Pionek(color);
	}
	
	public void ustawPionek(Pionek pionek) {
		this.pionek = pionek;
	}
	
	public Pionek zwrocPionek() {
		return this.pionek;
	}
	
	public void usunPionek() {
		this.pionek = null;
	}
	
	public boolean czyPuste() {
		if(this.pionek == null) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	*Rysowanie pola na planszy
	*/

	public void doDrawing(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		if(this.pionek == null) {
			float r = this.getWidth()/3;
			Ellipse2D ellipse = new Ellipse2D.Float(r, r, r, r);
			g2d.fill(ellipse);
		}
		else {
			float r = this.getWidth()/3;
			Ellipse2D ellipse = new Ellipse2D.Float(r, r, 2*r, 2*r);
			g2d.setColor(this.pionek.getColor());
			g2d.fill(ellipse);
		}

	}
	
	/**
	*Kolorowanie stworzonego pola
	*/
	
    @Override
    public void paintComponent(Graphics g) {       
        super.paintComponent(g);
        doDrawing(g);
    }
	
	/**
	*Przemalowywanie klikniętego pola
	*Jeśli pionek jest podniesiony, to odstawiamy pionek, czyli malujemy nowe pole na kolor podniesionego pionka
	*Jeśli pionek nie jest podniesiony, to znaczy, że kliknęcie miało na celu jego podniesienie, czyli malujemy pierwotne pole na kolor pola pustego
	@see feature kopiujPionek
	*/

	public void mouseClicked(MouseEvent e) {
		
		if(!mojaPlansza.czyPionekPodniesiony()) {
			if(!this.czyPuste() && this.zwrocPionek().getColor() == mojaPlansza.getClient().getColor()) {
				mojaPlansza.podniesPionek(this.pionek.kopiujPionek());
				mojaPlansza.setStart(x, y);
				usunPionek();
				repaint();
			}
		}
		else if(mojaPlansza.czyPionekPodniesiony()) {
			if(this.czyPuste()) {
				ustawPionek(this.mojaPlansza.zwrocPodniesiony());
				this.mojaPlansza.odstawPionek();
				mojaPlansza.setEnd(x, y);
				mojaPlansza.powiadomKlienta();
				repaint();
			}
		}
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseDragged(MouseEvent e) {
	
		
	}

	public void mouseMoved(MouseEvent e) {
		//mojaPlansza.mouseMoved(e);
	}
	
}
