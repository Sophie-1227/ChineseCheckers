import javax.swing.*;

public class Main {

	public static void main(String args[]) {
		JFrame okno = new JFrame();
		okno.setBounds(450, 50, 950, 950);
		okno.add(new Plansza());
		okno.setResizable(false);
		okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		okno.setVisible(true);
	}
		
}