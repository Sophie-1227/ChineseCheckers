import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {
    public Menu(){
        JFrame frame = new JFrame("Main Menu");
        Container pane = frame.getContentPane();
        pane.setLayout(new GridLayout(4,1));
        JButton play = new JButton("Play");
        //play.setPreferredSize(new Dimension(100, 200));
        JButton language = new JButton("Language");
        JButton rules = new JButton("Rules");
        JButton about = new JButton("About");
        pane.add(play);
        pane.add(language);
        pane.add(rules);
        pane.add(about);
        frame.setSize(400, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        rules.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        JFrame rulesFrame = new JFrame();
                        JOptionPane.showMessageDialog(rulesFrame, "Some rules");
                    }
                }
        );
        about.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        JFrame rulesFrame = new JFrame();
                        JOptionPane.showMessageDialog(rulesFrame, "Some info about creators");
                    }
                }
        );
        play.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        JFrame board = new JFrame();
                        board.setBounds(450, 50, 950, 950);
                        board.add(new Plansza());
                        board.setResizable(false);
                        board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        board.setVisible(true);
                    }
                }
        );
    }
}
