import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.Executors;


public class ServerRun {
	
	/**
	*Klasa odpowiadająca za działanie servera głównego
	*Uruchamia grę n-osobową bazyjąc na wyborze gracza po uruchumieniu menu
	@param liczbaGraczy
	@see Menu.java
	*/

	private int liczbaGraczy;
	
	public void setLiczbaGraczy(int n) {
		liczbaGraczy = n;
	}
	
	/**
	*Metoda uruchamiająca grę
	*Wywoływana w klasie Menu
	@see feature Menu
	@exception java.io.IOException 
	*/
	
    public void start() throws IOException {
    	
    	//int liczbaGraczy = 4;
    	
        try (var listener = new ServerSocket(59090)) {
            System.out.println("The server is running...");
            
            var pool = Executors.newFixedThreadPool(200);
            
            if(liczbaGraczy == 2) {
                while (true) {
                    Gra2 gra = new Gra2();
                    pool.execute(gra.new Player(listener.accept(), "Czerwony"));
                    pool.execute(gra.new Player(listener.accept(), "Zielony"));
                }
            } else if(liczbaGraczy == 3) {
            	
                while (true) {
                    Gra3 gra = new Gra3();
                    
                    pool.execute(gra.new Player(listener.accept(), "Czerwony"));
                    pool.execute(gra.new Player(listener.accept(), "Fioletowy"));
                    pool.execute(gra.new Player(listener.accept(), "Niebieski"));

                                        
                }
            } else if (liczbaGraczy == 4) {
            	
            	Gra4 gra = new Gra4();
            	
                pool.execute(gra.new Player(listener.accept(), "Czerwony"));
                pool.execute(gra.new Player(listener.accept(), "Pomaranczowy"));
                pool.execute(gra.new Player(listener.accept(), "Zielony"));
                pool.execute(gra.new Player(listener.accept(), "Niebieski"));
            	
            }	else if(liczbaGraczy == 6) {
            	
            	Gra6 gra = new Gra6();
            	
                pool.execute(gra.new Player(listener.accept(), "Czerwony"));
                pool.execute(gra.new Player(listener.accept(), "Pomaranczowy"));
                pool.execute(gra.new Player(listener.accept(), "Fioletowy"));
                pool.execute(gra.new Player(listener.accept(), "Zielony"));
                pool.execute(gra.new Player(listener.accept(), "Niebieski"));
                pool.execute(gra.new Player(listener.accept(), "Brazowy"));
            	
            }
  
         
        }
    }   
}
