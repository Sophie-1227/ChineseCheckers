import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.Date;
import java.util.concurrent.Executors;


public class Server {
	
	static int liczbaGraczy;
	
	public void setLiczbaGraczy(int n) {
		this.liczbaGraczy = n;
	}

	
    public static void main(String[] args) throws IOException {
    	
    	
        	
    	
    	/*if(liczbaGraczy == 2) {
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
    	}*/
    	
    	Menu menu = new Menu();
    	try {
			System.out.println(liczbaGraczy);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        try (var listener = new ServerSocket(59090)) {
            System.out.println("The server is running...");
            
            var pool = Executors.newFixedThreadPool(200);
            // Dodawanie dwoch graczy
            while (true) {
                Gra2 gra = new Gra2();
                pool.execute(gra.new Player(listener.accept(), "Czerwony"));
                pool.execute(gra.new Player(listener.accept(), "Zielony"));
            }
            
            
            /*while (true) {
            	
                try (var socket = listener.accept()) {
             
                    var out = new PrintWriter(socket.getOutputStream(), true);
                  
                    out.println(new Date().toString());
                }
            }*/
        }
    }   
}