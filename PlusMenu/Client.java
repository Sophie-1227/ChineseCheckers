import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JFrame;

public class Client {
	
	
    public static void main(String[] args) throws IOException {
        //if (args.length != 1) {
            //System.err.println("Pass the server IP as the sole command line argument");
            //return;
        //}
        // Tworzenie socketa i podlaczenie do serwra
        //var socket = new Socket(args[0], 59090);
    	
    	var socket = new Socket("127.0.0.1", 59090);
		JFrame okno = new JFrame();
		okno.setBounds(450, 50, 950, 950);
		Plansza plansza = new Plansza();
		okno.add(plansza);
		okno.setResizable(false);
		okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		okno.setVisible(true);
        // Zdefiniowanie Skanera opartego na streamie wej�ciowym 
        Scanner in = new Scanner(socket.getInputStream());
        System.out.println("Server response: " + in.nextLine());
    }
}
