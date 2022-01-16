import java.awt.*;
import javax.swing.*;

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
