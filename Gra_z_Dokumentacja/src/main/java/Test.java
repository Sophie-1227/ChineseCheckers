import java.awt.Color;

public class Test {
	
	public static void main(String args[]) {
		
		Color k1 = Color.RED;
		Color k2 = Color.RED;

		
		if(k1 == k2) {
			System.out.println("Tak, k1 = k2");
		}
		
		Color k3 = new Color(1, 2, 3);
		Color k4 = new Color(1, 2, 3);
		
		if(k3 == k4) {
			System.out.println("Tak, k3 = k4");
		}
		
	}
	
}
