import java.io.IOException;
import java.io.PrintWriter;
import java.awt.*;
import javax.swing.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
*Klasa Klienta zawiera prywatne plansze graczy
*Komunikuje się ona z serverem przekazując wzajemne ruchy między graczami
@SystemRequirment Architektura typu klient-server (klasa client)
*/

public class Client {
	
	/**
	*Parametry klasy Client
	@param plansza obiekt typu Plansza dla klienta
	@param socket 
	@param in Scanner to read insert form user
	@param out PrintWriter to give feedback to user
	@param color
	@param messages Jlabel do wyswietlania wiadomosci
	@param okno Jframe w ktorej wyslwietlamy plansze gracza
	*/
	
    private Plansza plansza;
    private JLabel messages = new JLabel();
    private Socket socket;
    private Scanner in;
    private PrintWriter out;
    private JFrame okno = new JFrame("Chińskie warcaby");
    private Color color;
    
	/**
	*Tworzenie polaczenia klient-server
	@see Server.java
	@see ServerRun
	*/
	
    public Client(String adres) {
    	
    	try {
    		plansza = new Plansza();
    		plansza.setClient(this);
			socket = new Socket(adres, 59090);
	        in = new Scanner(socket.getInputStream());
	        out = new PrintWriter(socket.getOutputStream(), true);
	        
	        plansza.setBackground(Color.WHITE);
	        messages.setBackground(Color.WHITE);
	        messages.setText("...");
	        okno.getContentPane().add(messages, BorderLayout.PAGE_END);
	        //plansza.ustawCzerwone();
	        //plansza.ustawZielone();
	        plansza.blokujWszystkiePola();
	        okno.add(plansza);
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	
    }
	/**
	*Sprawdzanie koloru danego pola na planszy
	@return kolor wybranego pola planszy
	*/
    
    public Color getColor() {
    	return this.color;
    }
	/**
	*Przypisanie danemu klientowi koloru na podstawie losowania
	@SystemRequirement losowość koloru gracza
	*/
    
    private void setColor(String s) {
    	if(s.startsWith("Czerwony")) {
    		this.color = Color.RED;
    	} else if(s.startsWith("Zielony")) {
    		this.color = Color.GREEN;
    	} else if(s.startsWith("Fioletowy")) {
    		this.color = Color.PINK;
    	} else if(s.startsWith("Niebieski")) {
    		this.color = Color.BLUE;
    	} else if(s.startsWith("Brazowy")) {
    		this.color = Color.BLACK;
    	} else if(s.startsWith("Pomaranczowy")) {
    		this.color = Color.ORANGE;
    	}
    }
    
	/**
	*Przesłanie ruchu do servera, który rozsyła go pozostałym graczom
	@see Plansza.java
	@see feature powiadomKlienta
	@see feature move
	@see feature processmoveCommand
	*/
	
    public void wyslijRuch(int x1, int y1, int x2, int y2) {
    	out.println("MOVE " + x1 + " " + y1 + " " + x2 + " " + y2);
    }
	
	/**
	*metody przygotowujące planszę pod różne ilości graczy
	@SystemRequirenment możliwość gry z różną ilością graczy
	@see Plansza.java
	@see feature ustawCzerwone
	@see feature ustawBrazowe 
	@see feature ustawZielone
	@see feature ustawFioletowe
	@see feature ustawNiebieskie
	@see feature ustawPomaranczowy
	*/
    
    private void setup2() {
		plansza.ustawCzerwone();
		plansza.ustawZielone();
    }
    
    private void setup3() {
		plansza.ustawCzerwone();
		plansza.ustawNiebieskie();
		plansza.ustawFioletowe();
    }
    
    private void setup4() {
		plansza.ustawCzerwone();
		plansza.ustawPomaranczowy();
		plansza.ustawNiebieskie();
		plansza.ustawZielone();
    }
    
    private void setup6() {
		plansza.ustawCzerwone();
		plansza.ustawPomaranczowy();
		plansza.ustawNiebieskie();
		plansza.ustawZielone();
		plansza.ustawFioletowe();
		plansza.ustawBrazowe();	
    }
	
	/**
	*Wewnetrzna metoda move
	@see Plansza.java
	@see feature move
	*/
    
    private void move(int x1, int y1, int x2, int y2) {
    	this.plansza.move(x1, y1, x2, y2);
    }
    
    private void setMessage(String messageText) {
    	this.messages.setText(messageText);
    }
    
	/**
	*Główna metoda klasy Client uruchamiająca właściwą grę
	@exception java.net.UnknownHostException;
	*/
	
    public void play() throws Exception {
        try { 
	/**
	*Komunikaty z servera
	*/
        	// komunikat z serwera
            String response = in.nextLine();
            //char mark = response.charAt(8);
            String beg[] = response.split(" ", 0);
            String name = beg[1];
            int n = Integer.parseInt(beg[2]);
            if(n == 2) {
            	setup2();
            } else if(n == 3) {
            	setup3();
            } else if(n == 4) {
            	setup4();
            } else {
            	setup6();
            }
            setColor(name);
            //System.out.println("Jestem " + color);
            okno.setTitle("Chińskie warcaby: Gracz " + name);
            // W zaleznosci jaki komunikat przyszedl
            while (in.hasNextLine()) {
                response = in.nextLine();
                if (response.startsWith("VALID_MOVE")) {
                    setMessage("Ruch poprawny, czekaj na swoją kolej");
                    plansza.blokujWszystkiePola();
                } else if (response.startsWith("OPPONENT_MOVED")) {
                    String arr[] = response.split(" ", 0);
                    int x1 = Integer.parseInt(arr[1]);
                    int y1 = Integer.parseInt(arr[2]);
                    int x2 = Integer.parseInt(arr[3]);
                    int y2 = Integer.parseInt(arr[4]);
                    move(x1, y1, x2, y2);
                    //setMessage("Twój ruch");
                    //plansza.odblokujWszystkiePola();    -----> te dwa mają być po otrzymaniu previous oponnent moved
                    //Tutaj należy wykonać metodę move na planszy (ruszyć się tym pionem, co przeciwnik)
                } else if (response.startsWith("PREVIOUS_OPPONENT_MOVED")){
                	setMessage("Twój ruch");
                	plansza.odblokujWszystkiePola();
                } else if (response.startsWith("MESSAGE Twój ruch")) {
                    setMessage(response.substring(8));
                    plansza.odblokujWszystkiePola();
                } else if (response.startsWith("MESSAGE")) {
                    setMessage(response.substring(8));
                } else if(response.startsWith("INVALID_MOVE")) {
                	setMessage("Ten ruch nie był poprawny");
                	String arr[] = response.split(" ", 0);
                    int x1 = Integer.parseInt(arr[1]);
                    int y1 = Integer.parseInt(arr[2]);
                    int x2 = Integer.parseInt(arr[3]);
                    int y2 = Integer.parseInt(arr[4]);
                    move(x2, y2, x1, y1);		//Wykonujemy odwrotny ruch (Chcemy go cofnąć, bo był niepoprawny)
                }   else if (response.startsWith("VICTORY")) {
                    JOptionPane.showMessageDialog(okno, "Gratulacje, wygrałeś grę");
                    break;
                }	else if (response.startsWith("PLACE")){
                    String arr[] = response.split(" ", 0);
                    int miejsce = Integer.parseInt(arr[1]);
                    JOptionPane.showMessageDialog(okno, "Zająłeś miejsce " + miejsce);
                    break;
                } else if (response.startsWith("DEFEAT")) {
                    JOptionPane.showMessageDialog(okno, "Przegrałeś grę");
                    break;
                } else if (response.startsWith("TIE")) {
                    JOptionPane.showMessageDialog(okno, "Remis");
                    break;
                } else if (response.startsWith("OTHER_PLAYER_LEFT")) {
                    JOptionPane.showMessageDialog(okno, "Przeciwnik opuścił grę");
                    break;
                }
            }
            out.println("QUIT");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            socket.close();
            okno.dispose();
        }
    }
	
	/**
	*Uruchamiamy okno aplikacji dla klienta
	*Ma ono z góry ustalony, niezmienialny rozmiar
	*Poniższa metoda wyświetla nam onko indywidualne gracza
	@exception java.net.UnknownHostException;
	*/
	
    public static void main(String[] args) throws Exception {

        // Tworzenie socketa i podlaczenie do serwra
        //var socket = new Socket(args[0], 59090);
    	Client client = new Client("127.0.0.1");
    	client.okno.setBounds(450, 50, 475, 475);
		client.okno.setResizable(false);
		client.okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		client.okno.setVisible(true);
		client.setMessage("...");
      
        //System.out.println("Server response: " + client.in.nextLine());
		client.play();
    }
}

