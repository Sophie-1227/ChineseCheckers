import java.awt.*;
import javax.swing.event.*;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;

public class Plansza extends JPanel implements MouseInputListener, MouseMotionListener {
	
	private boolean[][] dostepnePola;
	private Pole[][] uzywanePola;
	protected Pionek podniesionyPionek;
	private Ellipse2D ruszanyPionek;
	private int x, y;
	
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
		//ustawCzerwone();
		//ustawZielone();
		//ustawBrazowe();
		//ustawFioletowe();
		//ustawPomaranczowy();
		//ustawNiebieskie();
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
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
					this.uzywanePola[j][i] = new Pole(this);
				}
			}
		}
	}
	
	
	public void podniesPionek(Pionek pionek) {
		this.podniesionyPionek = pionek;
		repaint();
    }
	
	public void odstawPionek() {
		this.podniesionyPionek = null;
		ruszanyPionek = null;
		repaint();
	}
	
	public Pionek zwrocPodniesiony() {
		return this.podniesionyPionek;
	}
	
	public boolean czyPionekPodniesiony() {
		if(podniesionyPionek == null) {
			return false;
		}
		else {
			return true;
		}
	}
	
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
	
    public void ustawBrazowe() {
        uzywanePola[0][4].ustawPionek(new Color(102, 53, 0));
        uzywanePola[1][5].ustawPionek(new Color(102, 53, 0));
        uzywanePola[2][4].ustawPionek(new Color(102, 53, 0));
        uzywanePola[2][6].ustawPionek(new Color(102, 53, 0));
        uzywanePola[3][5].ustawPionek(new Color(102, 53, 0));
        uzywanePola[3][7].ustawPionek(new Color(102, 53, 0));
        uzywanePola[4][4].ustawPionek(new Color(102, 53, 0));
        uzywanePola[4][6].ustawPionek(new Color(102, 53, 0));
        uzywanePola[5][5].ustawPionek(new Color(102, 53, 0));
        uzywanePola[6][4].ustawPionek(new Color(102, 53, 0));
    }
	
	public void ustawZielone() {
		uzywanePola[9][13].ustawPionek(new Color(0, 153, 0));
		uzywanePola[10][14].ustawPionek(new Color(0, 153, 0));
		uzywanePola[11][13].ustawPionek(new Color(0, 153, 0));
		uzywanePola[11][15].ustawPionek(new Color(0, 153, 0));
		uzywanePola[12][14].ustawPionek(new Color(0, 153, 0));
		uzywanePola[12][16].ustawPionek(new Color(0, 153, 0));
		uzywanePola[13][13].ustawPionek(new Color(0, 153, 0));
		uzywanePola[13][15].ustawPionek(new Color(0, 153, 0));
		uzywanePola[14][14].ustawPionek(new Color(0, 153, 0));
		uzywanePola[15][13].ustawPionek(new Color(0, 153, 0));
	}
	
    public void ustawFioletowe(){
        uzywanePola[24][12].ustawPionek(new Color(102, 0, 153));
        uzywanePola[23][11].ustawPionek(new Color(102, 0, 153));
        uzywanePola[22][10].ustawPionek(new Color(102, 0, 153));
        uzywanePola[22][12].ustawPionek(new Color(102, 0, 153));
        uzywanePola[21][11].ustawPionek(new Color(102, 0, 153));
        uzywanePola[21][9].ustawPionek(new Color(102, 0, 153));
        uzywanePola[20][10].ustawPionek(new Color(102, 0, 153));
        uzywanePola[20][12].ustawPionek(new Color(102, 0, 153));
        uzywanePola[19][11].ustawPionek(new Color(102, 0, 153));
        uzywanePola[18][12].ustawPionek(new Color(102, 0, 153));
    }

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
	
	/*public void doDrawing(Graphics g) {
		if(podniesionyPionek != null) {
			
			Graphics2D g2d = (Graphics2D) g;
			float r = uzywanePola[9][13].getHeight();
			
			g2d.setColor(podniesionyPionek.getColor());
			ruszanyPionek = new Ellipse2D.Float(x, y, 2*r/3, 2*r/3);
			g2d.fill(ruszanyPionek);

		}
	}*/
	
	
    /*@Override
    public void paintComponent(Graphics g) {       
        super.paintComponent(g);
        doDrawing(g);
    }*/


	public void mouseClicked(MouseEvent e) {

		//x = e.getX();
		//y = e.getY();
		
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
		
		//x = e.getX();
		//y = e.getY();
		//float r = uzywanePola[9][13].getHeight();
		
		//ruszanyPionek = new Ellipse2D.Float(x, y, 2*r/3, 2*r/3);
		//repaint();
		
	}
	
	
}