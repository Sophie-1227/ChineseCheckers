import java.awt.*;
import javax.swing.*;
import javax.swing.text.AttributeSet.ColorAttribute;
import java.awt.color.*;

public class Plansza extends JPanel{

    private boolean[][] dostepnePola;
    private Pole[][] uzywanePola;
    private Pionek podniesionyPionek;

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
        ustawCzerwone();
        ustawZielone();
    }

    private void uzupelnijDostepnePola() {
        //int polaRzedy[] = {1, 2, 3, 4, 13, 12, 11, 10, 9, 10, 11, 12, 13, 4, 3, 2, 1};
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

    //public void podniesPionek(Pionek pionek) {
    //this.podniesionyPionek = pionek;
    //System.out.println("Podnoszę pionek");
    //}

    public void podniesPionek(Pionek pionek) {
        this.podniesionyPionek = pionek;
        //System.out.println("Podnoszę pionek");
    }



    public void odstawPionek() {
        this.podniesionyPionek = null;
        //System.out.println("Odstawiam pionek");
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

    private void ustawCzerwone() {
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

    private void ustawBiale() {
        uzywanePola[0][4].ustawPionek(Color.WHITE);
        uzywanePola[1][5].ustawPionek(Color.WHITE);
        uzywanePola[2][4].ustawPionek(Color.WHITE);
        uzywanePola[2][6].ustawPionek(Color.WHITE);
        uzywanePola[3][5].ustawPionek(Color.WHITE);
        uzywanePola[3][7].ustawPionek(Color.WHITE);
        uzywanePola[4][4].ustawPionek(Color.WHITE);
        uzywanePola[4][6].ustawPionek(Color.WHITE);
        uzywanePola[5][5].ustawPionek(Color.WHITE);
        uzywanePola[6][4].ustawPionek(Color.WHITE);
    }

    private void ustawZielone() {
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


}