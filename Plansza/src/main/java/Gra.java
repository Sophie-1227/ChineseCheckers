import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

public class Gra {

    Player currentPlayer;

    // Kiedy zwyciestwo
    public boolean hasWinner() {
        return false;
        //Należy dopisać metodę sprawdzającą, czy jest już zwycięzca
    }


    // Metoda obslugujaca ruch
    public synchronized void move(int location, Player player) {
        if (player.opponent == null) {
            throw new IllegalStateException("You don't have an opponent yet");
        } 
        //board[location] = currentPlayer;       //Tutaj trzeba sprawić, żeby serwer robił ruch na swojej planszy
        currentPlayer = currentPlayer.opponent;
    }


    class Player implements Runnable {
        String name;
        Player opponent;
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
                if (opponent != null && opponent.output != null) {
                    opponent.output.println("OTHER_PLAYER_LEFT");
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
            output.println("WELCOME " + name);
            if (name == "Czerwony") {
                currentPlayer = this;
                output.println("MESSAGE Oczekiwanie na przeciwników");
            } else {
                opponent = currentPlayer;
                opponent.opponent = this;
                opponent.output.println("MESSAGE Twój ruch");
            }
        }

        private void processCommands() {
            while (input.hasNextLine()) {
            	// Obsluga komendy od klienta
                var command = input.nextLine();
                if (command.startsWith("QUIT")) {
                    return;
                } else if (command.startsWith("MOVE")) {
                    processMoveCommand(Integer.parseInt(command.substring(5)));
                }
            }
        }

        // Obsluga komendy po zaznaczeniu czegos
        private void processMoveCommand(int location) {
            try {
                move(location, this);
                output.println("VALID_MOVE");
                opponent.output.println("OPPONENT_MOVED " + location);
                if (hasWinner()) {
                    output.println("VICTORY");
                    opponent.output.println("DEFEAT");
                } 
            } catch (IllegalStateException e) {
                output.println("MESSAGE " + e.getMessage());
            }
        }
    }
}