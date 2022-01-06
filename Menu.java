public class Menu {
    public int Menu(){
        JFrame frame = new JFrame("Main Menu");
        Container pane = frame.getContentPane();
        pane.setLayout(new GridLayout(4,1));
        JButton two = new JButton("2 players");
        JButton three = new JButton("3 players");
        JButton four = new JButton("4 players");
        JButton six = new JButton("6 players");
        frame.setSize(400, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        two.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    return 2;
                }
            }
        );

        three.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    return 3;
                }
            }
        );

        four.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    return 4;
                }
            }
        );

        six.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    return 6;
                }
            }
        );
    }
}
