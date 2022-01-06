import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.Date;

public class Server {
	
	
    public static void main(String[] args) throws IOException {
    	
    	int liczbaGraczy = 4;
    	Plansza plansza = new Plansza();
    	
    	if(liczbaGraczy == 2) {
    		plansza.ustawCzerwone();
    		plansza.ustawZielone();
    	} else if(liczbaGraczy == 3) {
    		plansza.ustawCzerwone();
    		plansza.ustawNiebieskie();
    		plansza.ustawFioletowe();
    	} else if(liczbaGraczy == 4) {
    		plansza.ustawCzerwone();
    		plansza.ustawPomaranczowy();
    		plansza.ustawNiebieskie();
    		plansza.ustawZielone();
    	} else if(liczbaGraczy == 6) {
    		plansza.ustawCzerwone();
    		plansza.ustawPomaranczowy();
    		plansza.ustawNiebieskie();
    		plansza.ustawZielone();
    		plansza.ustawFioletowe();
    		plansza.ustawBrazowe();	
    	}
    	
    	
        try (var listener = new ServerSocket(59090)) {
            System.out.println("The server is running...");
            //Menu menu = new Menu();
            //liczbaGraczy = menu.liczbaGraczy;
            //System.out.println("Wybrano " + liczbaGraczy); 
            while (true) {
            	// Serwer nasluchuje i akceptuje polaczenia klientow
                try (var socket = listener.accept()) {
                	// Ustawienie kanalu komunikacyjnego z serwera do klienta
                    var out = new PrintWriter(socket.getOutputStream(), true);
                    // Wyslanie do klienta aktualnej daty
                    out.println(new Date().toString());
                }
            }
        }
    }
}