import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

public class Gra2 {

    Player currentPlayer;
    Plansza plansza;
    
    public Gra2() {
    	this.plansza = new Plansza();
    	this.plansza.ustawCzerwone();
    	this.plansza.ustawZielone();
    }

    // Kiedy zwyciestwo
    public boolean hasWinner() {
        return false;
        //Należy dopisać metodę sprawdzającą, czy jest już zwycięzca
    }


    // Metoda obslugujaca ruch
    public synchronized void move(int x1, int y1, int x2, int y2, Player player) {
        if (player.nextPlayer == null) {
            throw new IllegalStateException("You don't have an opponent yet");
        } 
        //board[location] = currentPlayer;       //Tutaj trzeba sprawić, żeby gra robiła ruch na swojej planszy
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
                nextPlayer.output.println("MESSAGE Twój ruch");
            }
        }

        private void processCommands() {
            while (input.hasNextLine()) {
            	// Obsluga komendy od klienta
                var command = input.nextLine();
                if (command.startsWith("QUIT")) {
                    return;
                } else if (command.startsWith("MOVE")) {
                    //processMoveCommand(Integer.parseInt(command.substring(5)));
                	String arr[] = command.split(" ", 0);
                	int x1 = Integer.parseInt(arr[1]);
                	int y1 = Integer.parseInt(arr[2]);
                	int x2 = Integer.parseInt(arr[3]);
                	int y2 = Integer.parseInt(arr[4]);
                	processMoveCommand(x1, y1, x2, y2);
                }
            }
        }

        // Obsluga komendy po zaznaczeniu czegos
        private void processMoveCommand(int x1, int y1, int x2, int  y2) {
            try {
            	//Tu musi być jeszcze metoda sprawdzająca poprawność ruchu (albo lepiej dopisać to w metodzie move)
                move(x1, y1, x2, y2, this);
                output.println("VALID_MOVE");
                nextPlayer.output.println("OPPONENT_MOVED " + x1 + " " + y1 + " " + x2 + " " + y2);
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