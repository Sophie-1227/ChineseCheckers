import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu1 implements ActionListener{
    //Ta klasa zajmuje się układem menu głównego i mozliwościami wyboru dostępnymi dla gracza
    Menu1(){
        JFrame frame = new JFrame("ChineseCheckersMainMenu");
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JMenuBar menuBar = new JMenuBar();

        JLabel background = new JLabel(new ImageIcon("C:\\Users\\zstyp\\OneDrive - Politechnika Wroclawska\\Pulpit\\Studia\\Semestr3\\Technologia Programowania\\Lista4\\background.png"));
        frame.add(background);

        JMenu jmPlay = new JMenu("Play");
        JMenuItem twoPalyer = new JMenuItem("Two players");
        JMenuItem threePalyer = new JMenuItem("Three players");
        JMenuItem fourPalyer = new JMenuItem("Four players");
        JMenuItem fivePalyer = new JMenuItem("Five players");
        JMenuItem sixPalyer = new JMenuItem("Six players");
        menuBar.add(jmPlay);
        jmPlay.add(twoPalyer);
        jmPlay.add(threePalyer);
        jmPlay.add(fourPalyer);
        jmPlay.add(fivePalyer);
        jmPlay.add(sixPalyer);

        JMenu jmLanguage = new JMenu("Language");
        JMenuItem polish = new JMenuItem("Polski");
        JMenuItem english = new JMenuItem("English");
        menuBar.add(jmLanguage);
        jmLanguage.add(english);
        jmLanguage.add(polish);

        JMenu jmRules = new JMenu("Game Rules");
        menuBar.add(jmRules);

        JMenu jmAbout = new JMenu("About");
        menuBar.add(jmAbout);

        frame.setJMenuBar(menuBar);
        frame.setVisible(true);

        jmRules.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        JOptionPane.showMessageDialog(null, "Some rules", "Rule Book", JOptionPane.PLAIN_MESSAGE);
                    }
                }
        );
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String comString = actionEvent.getActionCommand();
        System.out.println(comString + "Selected");
    }


}
