import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.Provider.Service;

public class Menu {

    public Menu(){
        JFrame frame = new JFrame("Ilu graczy ma być na serwerze?");
        Container pane = frame.getContentPane();
        pane.setLayout(new GridLayout(4,1));
        JButton two = new JButton("2 graczy");
        JButton three = new JButton("3 graczy");
        JButton four = new JButton("4 graczy");
        JButton six = new JButton("6 graczy");
        frame.add(two);
        frame.add(three);
        frame.add(four);
        frame.add(six);
        frame.setBounds(700, 300, 400, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        two.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    ServerRun server = new ServerRun();
                    server.setLiczbaGraczy(2);
                    frame.setVisible(false);
                    try {
						server.start();
					} catch (IOException e) {
						e.printStackTrace();
					}
                }
            }
        );

        three.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    ServerRun server = new ServerRun();
                    server.setLiczbaGraczy(3);
                	frame.setVisible(false);
                    try {
						server.start();
					} catch (IOException e) {
						e.printStackTrace();
					}
                	
                }
            }
        );

        four.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                	ServerRun server = new ServerRun();
                    server.setLiczbaGraczy(4);
                	frame.setVisible(false);
                    try {
						server.start();
					} catch (IOException e) {
						e.printStackTrace();
					}
                }
            }
        );

        six.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                	ServerRun server = new ServerRun();
                	server.setLiczbaGraczy(6);
                	frame.setVisible(false);
                    try {
						server.start();
					} catch (IOException e) {
						e.printStackTrace();
					}
                }
            }
        );
    }
    
    public static void main(String args[]) {
    	
    	Menu menu = new Menu();
    	
    }
    
}