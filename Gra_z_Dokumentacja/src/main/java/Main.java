import java.awt.*;
import javax.swing.*;

/**
*To jest klasa glowna aplikacji typu klient-server pozwalajacej na gre w chinskie warcaby
*Grac mozna w 2,3,4 lub 6 osob
*W tej klasie tworzymy podstawowe okno aplikacji
*Okno ma z gory ustalone i niezmienialne wymiary
*Tworzymy plansze i umozliwiamy zamkniecie okna aplikacji

@author Jakub Sokolowski (nr albumu 261706)
@author Zofia Stypulkowska (nr albumu 261720)
*/

public class Main {

	public static void main(String args[]) {
		JFrame okno = new JFrame();
		okno.setBounds(450, 50, 950, 950);
		Plansza plansza = new Plansza();
		plansza.ustawCzerwone();
		plansza.ustawZielone();
		plansza.ustawBrazowe();
		plansza.ustawPomaranczowy();
		plansza.ustawFioletowe();
		plansza.ustawNiebieskie();
		Plansza p2 = plansza.kopiujPlansze();
		okno.add(p2);
		okno.setResizable(false);
		okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		okno.setVisible(true);
	}
}