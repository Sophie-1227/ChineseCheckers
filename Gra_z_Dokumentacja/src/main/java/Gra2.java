import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

public class Gra2 extends Gra {
	
	/**
	*Klasa zawierająca grę dla 2 osob
	*Uruchamia sie ja gdy po uruchomieniu klasy menu wybierzemy 2 graczy i odpalimy server
	*/

    Player currentPlayer;
    
	/**
	*Metoda tworzaca plansze i ustawiajaca pionki dla 2 graczy
	*/
	
    public Gra2() {
    	this.plansza = new Plansza();
    	this.plansza.ustawCzerwone();
    	this.plansza.ustawZielone();
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
                    nextPlayer.output.println("OTHER_PLAYER_LEFT");
                }
                try {
                    socket.close();
                } catch (IOException e) {
                }
            }
        }
	    
	    /**
	    *Glowna obsluga gry 2-osobowej
	    *Implementacja poczatku gry i obsluga wiadomosci do gracza
	    */

        private void setup() throws IOException {
        	// Poczatek gry
            input = new Scanner(socket.getInputStream());
            output = new PrintWriter(socket.getOutputStream(), true);
            output.println("WELCOME " + name + " 2");
            if (name == "Czerwony") {
                currentPlayer = this;
                output.println("MESSAGE Oczekiwanie na przeciwników");
            } else {
                nextPlayer = currentPlayer;
                nextPlayer.nextPlayer = this;
                int los = rand.nextInt(2);
                if(los == 0) {
                	nextPlayer.output.println("MESSAGE Twój ruch");
                	output.println("MESSAGE Proszę czekać");
                } else {
                	nextPlayer.output.println("MESSAGE Proszę czekać");
                	output.println("MESSAGE Twój ruch");
                }
                
            }
        }
	    
	    /**
	    *Obsluga komend od klienta
	    *Przetwarzanie sygnalow dotyczących ruchu od klienta
	    */

        private void processCommands() {
            while (input.hasNextLine()) {
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
	*Implementacja metody sprawdzającej poprawnosc ruchu
	@see #sprawdzRuch(int, int, int, int, String)
	*Wyswietlanie komunikatow o stanie gry po zakonczeniu ruchu //Zwycięstwo, przegrana itd.
	*/
        private void processMoveCommand(int x1, int y1, int x2, int  y2) {
            try {
            	if(!sprawdzRuch(x1, y1, x2, y2, name)) {
            		output.println("INVALID_MOVE " + x1 + " " + y1 + " " + x2 + " " + y2);
            		return;
            	}
                move(x1, y1, x2, y2, this);
                output.println("VALID_MOVE");
                nextPlayer.output.println("OPPONENT_MOVED " + x1 + " " + y1 + " " + x2 + " " + y2);
                nextPlayer.output.println("PREVIOUS_OPPONENT_MOVED");
                if (hasWinner()) {
                    output.println("VICTORY");
                    nextPlayer.output.println("DEFEAT");
                } 
            } catch (IllegalStateException e) {
                output.println("MESSAGE " + e.getMessage());
            }
        }
    }
}