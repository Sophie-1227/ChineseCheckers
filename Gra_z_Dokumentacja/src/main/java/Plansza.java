import java.awt.*;
import javax.swing.event.*;
import javax.swing.*;
import javax.swing.text.AttributeSet.ColorAttribute;
import java.awt.color.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;

public class Plansza extends JPanel implements MouseInputListener, MouseMotionListener {
	
	/**
	*Tutaj znajduja sie wszystkie metody pozwalajace na tworzenie planszy i kolorowanie odpowiednich pol
	*Jest to wykorzystywane we wlasciwie wszystkich pozostalych klasach
	*/
	
	private boolean[][] dostepnePola;
	protected Pole[][] uzywanePola;
	protected Pionek podniesionyPionek;
	private Ellipse2D ruszanyPionek;
	private int x1, y1, x2, y2;
	private Client client;
	
	public Plansza() {
		podniesionyPionek = null;
		setLayout(new GridLayout(17, 25));
		dostepnePola = new boolean[25][17];
		uzywanePola = new Pole[25][17];
		uzupelnijDostepnePola();
		uzupelnijUzywanePola();
		for(int i=0; i<=16; i++) {
			for(int j=0; j<=24; j++) {
				if(dostepnePola[j][i]) {
					add(uzywanePola[j][i]);
				}
				else {
					add(new JPanel());
				}
			}
		}

		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	/**
	*Pozwala na kopiowanie planszy i przekazywanie jej miedzu graczami i serverem
	 @return nowa metoda zwraca kopie planszy
	*/

	public Plansza kopiujPlansze() {
		
		Plansza nowa = new Plansza();
		
		for(int i=0; i<=24; i++) {
			for(int j=0; j<=16; j++) {
				if(this.uzywanePola[i][j] != null && this.uzywanePola[i][j].zwrocPionek() != null) {
					Color color = this.uzywanePola[i][j].zwrocPionek().getColor();
					nowa.uzywanePola[i][j].ustawPionek(new Pionek(color));
				}
			}
		}
		return nowa;
	}
	
	/**
	*Metody obslugujace pionki i pola na planszy
	@see Pole
	@see feature mouseClicked
	*/
	
	/**
	*Sprawdza czy dane pole jest zajete
	 * @param x wspolrzedna x pola
	 * @param y wspolrzedna y pola
	 * @return bool czy wybrane pole jest juz zajete
	 */
	
	public boolean czyJestPionek(int x, int y) {
		if( x>=0 && x<=24 && y>=0 && y<=16 && this.uzywanePola[x][y] != null && this.uzywanePola[x][y].zwrocPionek() != null) {
			return true;
		}
		return false;
	}

	 /**
	 *Usuwa pionek z danego pola
	 * @param x wspolrzedna x pola
	 * @param y wspolrzedna y pola
	 */
	
	public void usunPionekNaPlanszy(int x, int y) {
		if(this.uzywanePola[x][y] != null) {
			this.uzywanePola[x][y].usunPionek();
		}
	}
	
	
	 /**
	 *Ustawia tymczasowy pionek na nowym polu, co pomaga w sprawdzeniu poprawnosci ruchu
	 * @param x wspolrzedna x pola
	 * @param y wspolrzedna y pola
	 */
	
	public void ustawTymczasowy(int x, int y) {
		if(this.uzywanePola[x][y] != null) {
			uzywanePola[x][y].ustawPionek(Color.WHITE);
		}
	}
	
	public void usunPionki(Color color) {
		for(int i=0; i<=24; i++) {
			for(int j=0; j<=16; j++) {
				if(this.uzywanePola[i][j] != null && this.uzywanePola[i][j].zwrocPionek() != null && this.uzywanePola[i][j].zwrocPionek().getColor() == color) {
					this.uzywanePola[i][j].ustawPionek(Color.WHITE);
				}
			}
		}
	}
	
	public void powiadomKlienta() {
		this.client.wyslijRuch(x1, y1, x2, y2);
	}
	
	public void setClient(Client client) {
		this.client = client;
	}
	
	public Client getClient() {
		return this.client;
	}
	
	public void setStart(int x1, int y1) {
		this.x1 = x1;
		this.y1 = y1;
	}
	
	public void setEnd(int x2, int y2) {
		this.x2 = x2;
		this.y2 = y2;
	}
	
	 /**
	 *Obsluga ruchu na planszy glownej
	 * @param x1 wspolrzedna x pola przed wykonaniem ruchu
	 * @param y1 wspolrzedna y pola przed wykonaniem ruchu
	 * @param x2 wspolrzedna x pola po wykonaniu ruchu
	 * @param y2 wspolrzedna y pola po wykonaniu ruchu
	 */
	
	public void move(int x1, int y1, int x2, int y2) {
		Color color = uzywanePola[x1][y1].zwrocPionek().getColor();
		uzywanePola[x1][y1].usunPionek();
		uzywanePola[x2][y2].ustawPionek(color);
		repaint();
	}
	
	/**
	*Tworzenie pustej planszy
	*/
	
	private void uzupelnijDostepnePola() {
		int poczatekRzedu[] = {12, 11, 10, 9, 0, 1, 2, 3, 4, 3, 2, 1, 0, 9, 10, 11, 12};
		int koniecRzedu[] = {12, 13, 14, 15, 24, 23, 22, 21, 20, 21, 22, 23, 24, 15, 14, 13, 12};
		for(int i=0; i<=16; i++) {
			for(int j=poczatekRzedu[i]; j<=koniecRzedu[i]; j+=2) {
				this.dostepnePola[j][i] = true;
			}
		}
	}
	
	private void uzupelnijUzywanePola() {

		for(int i=0; i<=16; i++) {
			for(int j=0; j<=24; j++) {
				if(this.dostepnePola[j][i]) {
					this.uzywanePola[j][i] = new Pole(this, j, i);  
				}
			}
		}
	}
	
	public void blokujWszystkiePola() {
		
		for(int i=0; i<=16; i++) {
			for(int j=0; j<=24; j++) {
				if(this.uzywanePola[j][i] != null) {
					this.uzywanePola[j][i].removeMouseListener(this.uzywanePola[j][i]);
					
				}
			}
		}
	}
	
	public void odblokujWszystkiePola() {
		
		for(int i=0; i<=16; i++) {
			for(int j=0; j<=24; j++) {
				if(this.uzywanePola[j][i] != null) {
					this.uzywanePola[j][i].addMouseListener(this.uzywanePola[j][i]);
				}
			}
		}
	}

	/**
	 *Podniesienie pionka
	 * @param pionek pionek wybrany przez gracza
	 */
	
	public void podniesPionek(Pionek pionek) {
		this.podniesionyPionek = pionek;
		repaint();
    }

	/**
	 * Odstawienie pionka
	 */

	public void odstawPionek() {
		this.podniesionyPionek = null;
		ruszanyPionek = null;
		repaint();
	}
	
	public Pionek zwrocPodniesiony() {
		return this.podniesionyPionek;
	}

	/**
	 * Sprawdzenie czy pionek juz zostal podniesiony
	 * @return bool czy pionek jest podniesiony
	 */
	
	public boolean czyPionekPodniesiony() {
		if(podniesionyPionek == null) {
			return false;
		}
		else {
			return true;
		}
	}
	
	/**
	*Ustawianie poczatkowego ustawienia pionkow czerwonych na planszy
	@see Client
	@see Main
	@see Gra2#Gra2()
	@see Gra3#Gra3()
	@see Gra4#Gra4()
	@see Gra6#Gra6()
	*/
	
	public void ustawCzerwone() {
		uzywanePola[12][0].ustawPionek(Color.RED);
		uzywanePola[11][1].ustawPionek(Color.RED);
		uzywanePola[13][1].ustawPionek(Color.RED);
		uzywanePola[10][2].ustawPionek(Color.RED);
		uzywanePola[12][2].ustawPionek(Color.RED);
		uzywanePola[14][2].ustawPionek(Color.RED);
		uzywanePola[9][3].ustawPionek(Color.RED);
		uzywanePola[11][3].ustawPionek(Color.RED);
		uzywanePola[13][3].ustawPionek(Color.RED);
		uzywanePola[15][3].ustawPionek(Color.RED);
	}

	/**
	 *Ustawianie poczatkowego ustawienia pionkow brazowych na planszy
	 @see Client
	 @see Main
	 @see Gra2#Gra2()
	 @see Gra3#Gra3()
	 @see Gra4#Gra4()
	 @see Gra6#Gra6()
	 */
	
    public void ustawBrazowe() {
        uzywanePola[0][4].ustawPionek(Color.BLACK);
        uzywanePola[1][5].ustawPionek(Color.BLACK);
        uzywanePola[2][4].ustawPionek(Color.BLACK);
        uzywanePola[2][6].ustawPionek(Color.BLACK);
        uzywanePola[3][5].ustawPionek(Color.BLACK);
        uzywanePola[3][7].ustawPionek(Color.BLACK);
        uzywanePola[4][4].ustawPionek(Color.BLACK);
        uzywanePola[4][6].ustawPionek(Color.BLACK);
        uzywanePola[5][5].ustawPionek(Color.BLACK);
        uzywanePola[6][4].ustawPionek(Color.BLACK);
    }

	/**
	 *Ustawianie poczatkowego ustawienia pionkow zielonych na planszy
	 @see Client
	 @see Main
	 @see Gra2#Gra2()
	 @see Gra3#Gra3()
	 @see Gra4#Gra4()
	 @see Gra6#Gra6()
	 */
	
	public void ustawZielone() {
		uzywanePola[9][13].ustawPionek(Color.GREEN);
		uzywanePola[10][14].ustawPionek(Color.GREEN);
		uzywanePola[11][13].ustawPionek(Color.GREEN);
		uzywanePola[11][15].ustawPionek(Color.GREEN);
		uzywanePola[12][14].ustawPionek(Color.GREEN);
		uzywanePola[12][16].ustawPionek(Color.GREEN);
		uzywanePola[13][13].ustawPionek(Color.GREEN);
		uzywanePola[13][15].ustawPionek(Color.GREEN);
		uzywanePola[14][14].ustawPionek(Color.GREEN);
		uzywanePola[15][13].ustawPionek(Color.GREEN);
	}

	/**
	 *Ustawianie poczatkowego ustawienia pionkow fioletowych na planszy
	 @see Client
	 @see Main
	 @see Gra2#Gra2()
	 @see Gra3#Gra3()
	 @see Gra4#Gra4()
	 @see Gra6#Gra6()
	 */
	
    public void ustawFioletowe(){
    
        uzywanePola[24][12].ustawPionek(Color.PINK);
        uzywanePola[23][11].ustawPionek(Color.PINK);
        uzywanePola[22][10].ustawPionek(Color.PINK);
        uzywanePola[22][12].ustawPionek(Color.PINK);
        uzywanePola[21][11].ustawPionek(Color.PINK);
        uzywanePola[21][9].ustawPionek(Color.PINK);
        uzywanePola[20][10].ustawPionek(Color.PINK);
        uzywanePola[20][12].ustawPionek(Color.PINK);
        uzywanePola[19][11].ustawPionek(Color.PINK);
        uzywanePola[18][12].ustawPionek(Color.PINK);
    }

	/**
	 *Ustawianie poczatkowego ustawienia pionkow niebieskich na planszy
	 @see Client
	 @see Main
	 @see Gra2#Gra2()
	 @see Gra3#Gra3()
	 @see Gra4#Gra4()
	 @see Gra6#Gra6()
	 */

    public void ustawNiebieskie(){
        uzywanePola[0][12].ustawPionek(Color.BLUE);
        uzywanePola[1][11].ustawPionek(Color.BLUE);
        uzywanePola[2][10].ustawPionek(Color.BLUE);
        uzywanePola[2][12].ustawPionek(Color.BLUE);
        uzywanePola[3][11].ustawPionek(Color.BLUE);
        uzywanePola[3][9].ustawPionek(Color.BLUE);
        uzywanePola[4][12].ustawPionek(Color.BLUE);
        uzywanePola[4][10].ustawPionek(Color.BLUE);
        uzywanePola[5][11].ustawPionek(Color.BLUE);
        uzywanePola[6][12].ustawPionek(Color.BLUE);
    }

	/**
	 *Ustawianie poczatkowego ustawienia pionkow pomaranczowych na planszy
	 @see Client
	 @see Main
	 @see Gra2#Gra2()
	 @see Gra3#Gra3()
	 @see Gra4#Gra4()
	 @see Gra6#Gra6()
	 */

    public void ustawPomaranczowy(){
        uzywanePola[24][4].ustawPionek(Color.ORANGE);
        uzywanePola[23][5].ustawPionek(Color.ORANGE);
        uzywanePola[22][4].ustawPionek(Color.ORANGE);
        uzywanePola[22][6].ustawPionek(Color.ORANGE);
        uzywanePola[21][5].ustawPionek(Color.ORANGE);
        uzywanePola[21][7].ustawPionek(Color.ORANGE);
        uzywanePola[20][4].ustawPionek(Color.ORANGE);
        uzywanePola[20][6].ustawPionek(Color.ORANGE);
        uzywanePola[19][5].ustawPionek(Color.ORANGE);
        uzywanePola[18][4].ustawPionek(Color.ORANGE);
    }
	

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub
		
	}

}