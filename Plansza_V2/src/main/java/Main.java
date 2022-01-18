import java.awt.*;
import javax.swing.*;

/**
*To jest klasa główna aplikacji typu klient-server pozwalającej na grę w chińskie warcaby
*Grać można w 2,3,4 lub 6 osób
*W tej klasie tworzymy podstawowe okno aplikacji
*Okno ma z góry ustalone i niezmienialne wymiary
*Tworzymy planszę i umożliwiamy zamknięcie okna aplikacji

@author Jakub Sokołowski (nr albumu 261706)
@author Zofia Stypułkowska (nr albumu 261720)
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
