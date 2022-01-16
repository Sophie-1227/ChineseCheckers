import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;


import java.util.ArrayList;

public class Gra4 extends Gra {
	
	ArrayList<Player> listaGraczy;
    Player currentPlayer;
    int kolejneMiejsce;

    
    public Gra4() {
    	kolejneMiejsce = 1;
    	this.plansza = new Plansza();
    	this.plansza.ustawCzerwone();
    	this.plansza.ustawPomaranczowy();
    	this.plansza.ustawNiebieskie();
    	this.plansza.ustawZielone();
    	this.listaGraczy = new ArrayList<Player>();
    }
    
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
    


    // Metoda obslugujaca ruch
    public synchronized void move(int x1, int y1, int x2, int y2, Player player) {
        if (player.nextPlayer == null) {
            throw new IllegalStateException("You don't have an opponent yet");
        } 
        //board[location] = currentPlayer;       //Tutaj trzeba sprawić, żeby gra robiła ruch na swojej planszy
        plansza.move(x1, y1, x2, y2);			 //Ta metoda wywołuje ruch TYLKO na planszy gry
        currentPlayer = currentPlayer.nextPlayer;
        //currentPlayer = nastepnyGracz(currentPlayer);
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
                    //nextPlayer.output.println("OTHER_PLAYER_LEFT");
                	wyslijDoWszystkich("OTHER_PLAYER_LEFT");
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
            	
            	//listaGraczy.get(0).output.println("MESSAGE Twój ruch");
            	//listaGraczy.get(1).output.println("MESSAGE Proszę czekać");
            	//listaGraczy.get(2).output.println("MESSAGE Proszę czekać");
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