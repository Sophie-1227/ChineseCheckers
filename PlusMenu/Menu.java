import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.Provider.Service;

public class Menu {
	
    public Menu(){
        JFrame frame = new JFrame("Main Menu");
        Container pane = frame.getContentPane();
        pane.setLayout(new GridLayout(4,1));
        JButton two = new JButton("2 players");
        JButton three = new JButton("3 players");
        JButton four = new JButton("4 players");
        JButton six = new JButton("6 players");
        frame.add(two);
        frame.add(three);
        frame.add(four);
        frame.add(six);
        frame.setSize(400, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        two.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    Server server = new Server();
                    server.setLiczbaGraczy(2);
                    frame.setVisible(false);
                }
            }
        );

        three.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    Server server = new Server();
                    server.setLiczbaGraczy(3);
                	frame.setVisible(false);
                }
            }
        );

        four.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    Server server = new Server();
                    server.setLiczbaGraczy(4);
                	frame.setVisible(false);
                }
            }
        );

        six.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    Server server = new Server();
                	server.setLiczbaGraczy(6);
                	frame.setVisible(false);
                }
            }
        );
    }
}