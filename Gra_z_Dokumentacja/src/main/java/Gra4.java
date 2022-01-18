import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;


import java.util.ArrayList;

public class Gra4 extends Gra {
	
	/**
	*Klasa zawierajaca gre dla 4 osob
	*Uruchamia sie ja gdy po uruchomieniu klasy menu wybierzemy 4 graczy i odpalimy server
	*/
	
	ArrayList<Player> listaGraczy;
    Player currentPlayer;
    int kolejneMiejsce;

	/**
	*Metoda tworzaca plansze i ustawiajaca pionki dla 4 graczy
	*/
    
    public Gra4() {
    	kolejneMiejsce = 1;
    	this.plansza = new Plansza();
    	this.plansza.ustawCzerwone();
    	this.plansza.ustawPomaranczowy();
    	this.plansza.ustawNiebieskie();
    	this.plansza.ustawZielone();
    	this.listaGraczy = new ArrayList<Player>();
    }
	
	/**
	*Metody obslugujace poruszanie sie i liste graczy
	*Pozwala na masowe rozsylanie komunikatow do pozostalych graczy
	*Metoda pozwalajaca wyslac komunikat do wszystkich graczy aktywnych (tych na liscie)
	*/
    
    private void usunGracza(Player player) {

    	listaGraczy.remove(player);
    	
    	for(int i=0; i<=listaGraczy.size()-1; i++) {
    		listaGraczy.get(i).nextPlayer = listaGraczy.get((i+1) % (listaGraczy.size()));
    	}	
    }

    
    public void dodajGracza(Player player) {
    	this.listaGraczy.add(player);
    }
    
    private Player nastepnyGracz(Player player) {
    	for(int i=0; i<=listaGraczy.size()-1; i++) {
    		
    		if(listaGraczy.get(i) == player) {
    			return listaGraczy.get((i+1) % listaGraczy.size());
    		}
    	}
    	return null;
    }
    
    private void wyslijDoPrzeciwnikow(Player player, String string) {
    	
    	for(int i=0; i<=listaGraczy.size()-1; i++) {
    		
    		if(listaGraczy.get(i).name != player.name) {
    			listaGraczy.get(i).output.println(string);
    		}
    	}
    }
    
    private void wyslijDoWszystkich(String string) {
    	
    	for(int i=0; i<=listaGraczy.size()-1; i++) {
    		listaGraczy.get(i).output.println(string);
    	}
    }

    /** 
    *Metoda obslugujaca ruch
    *Wywoluje ruch tylko na planszy gry
    *Sprawdza rowniez czy przeciwnik jest juz polaczony
    @exception IOException oznacza to, ze przeciwnik nie jest jeszcze polaczony
    */
	
    public synchronized void move(int x1, int y1, int x2, int y2, Player player) {
        if (player.nextPlayer == null) {
            throw new IllegalStateException("You don't have an opponent yet");
        } 
        plansza.move(x1, y1, x2, y2);			 //Ta metoda wywołuje ruch TYLKO na planszy gry
        currentPlayer = currentPlayer.nextPlayer;
    }

    class Player implements Runnable {
        String name;
        Player nextPlayer;
        Socket socket;
        Scanner input;
        PrintWriter output;

        public Player(Socket socket, String name) {
            this.socket = socket;
            this.name = name;
        }
	    
	    /**
        *Uruchomienie gry
	@exception IOException Sprawdza czy przeciwnik nie opuscil gry
	*/

        @Override
        public void run() {
            try {
                setup();
                processCommands();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (nextPlayer != null && nextPlayer.output != null) {
                	wyslijDoWszystkich("OTHER_PLAYER_LEFT");
                }
                try {
                    socket.close();
                } catch (IOException e) {
                }
            }
        }
	    
	    /**
		*Glowna obsługa gry 4-osobowej
        *Implementacja poczatku gry i obsluga wiadomosci do gracza
		*/

        private void setup() throws IOException {
        	// Poczatek gry
            input = new Scanner(socket.getInputStream());
            output = new PrintWriter(socket.getOutputStream(), true);
            output.println("WELCOME " + name + " 4");
            dodajGracza(this);
            if (name == "Czerwony") {
                currentPlayer = this;
                output.println("MESSAGE Oczekiwanie na przeciwników");
            } else if (name == "Pomaranczowy") {
            	listaGraczy.get(0).nextPlayer = this;
            	output.println("MESSAGE Oczekiwanie na przeciwników");
            } else if (name == "Zielony") {
            	listaGraczy.get(1).nextPlayer = this;
            	output.println("MESSAGE Oczekiwanie na przeciwników");
            } else if (name == "Niebieski") {
            	listaGraczy.get(2).nextPlayer = this;
            	nextPlayer = listaGraczy.get(0);
            	
            	int los = rand.nextInt(4);
            	listaGraczy.get(los).output.println("MESSAGE Twój ruch");
            	
            	for(int j=0; j<=3; j++) {
            		if(j != los) {
            			listaGraczy.get(j).output.println("MESSAGE Proszę czekać");
            		}
            	}
            }
        }
	    
	    /**
	    *Obsluga komend od klienta
	    *Przetwarzanie sygnalow dotyczacych ruchu od klienta
	    */

        private void processCommands() {
            while (input.hasNextLine()) {
            	// Obsluga komendy od klienta
                var command = input.nextLine();
                if (command.startsWith("QUIT")) {
                    return;
                } else if (command.startsWith("MOVE")) {
                	String arr[] = command.split(" ", 0);
                	int x1 = Integer.parseInt(arr[1]);
                	int y1 = Integer.parseInt(arr[2]);
                	int x2 = Integer.parseInt(arr[3]);
                	int y2 = Integer.parseInt(arr[4]);
                	processMoveCommand(x1, y1, x2, y2);
                }
            }
        }

        /**
	*Obsluga komendy po zaznaczeniu czegos
	*Implementacja metody sprawdzajacej czy zaszlo zwyciestwo
	@see #hasWinner()
	*Implementacja metody sprawdzajacej poprawnosc ruchu
	@see #sprawdzRuch(int, int, int, int, String)
	*Wyswietlanie komunikatow o stanie gry po zakonczeniu ruchu //Zwyciestwo, przegrana itd.
	*/
	    
        private void processMoveCommand(int x1, int y1, int x2, int  y2) {
            try {
            	//Tu musi być jeszcze metoda sprawdzająca poprawność ruchu (albo lepiej dopisać to w metodzie move)
            	if(!sprawdzRuch(x1, y1, x2, y2, name)) {
            		output.println("INVALID_MOVE " + x1 + " " + y1 + " " + x2 + " " + y2);
            		return;
            	}
                move(x1, y1, x2, y2, this);
                output.println("VALID_MOVE");
                wyslijDoPrzeciwnikow(this, "OPPONENT_MOVED " + x1 + " " + y1 + " " + x2 + " " + y2);

                nextPlayer.output.println("PREVIOUS_OPPONENT_MOVED");
                if (hasWinner()) {
                	if(kolejneMiejsce == 1) {
                		output.println("VICTORY");
                		plansza.usunPionki(stringToColor(name));
                		usunGracza(this);
                		kolejneMiejsce++;
                	} else if(kolejneMiejsce == 2) {
                		output.println("PLACE 2");
                		plansza.usunPionki(stringToColor(name));
                		usunGracza(this);
                		kolejneMiejsce++;
                	} else if(kolejneMiejsce == 3) {
                		output.println("PLACE 3");
                		wyslijDoPrzeciwnikow(this, "DEFEAT");
                	}
                } 
            } catch (IllegalStateException e) {
                output.println("MESSAGE " + e.getMessage());
            }
        }
    }
}